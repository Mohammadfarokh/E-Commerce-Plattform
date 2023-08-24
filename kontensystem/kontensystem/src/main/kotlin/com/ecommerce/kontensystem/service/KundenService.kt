package com.ecommerce.kontensystem.service

import com.ecommerce.kontensystem.entity.Konto
import com.ecommerce.kontensystem.entity.Kunden
import com.ecommerce.kontensystem.entity.Transaction
import com.ecommerce.kontensystem.repository.KontoRepository
import com.ecommerce.kontensystem.repository.KundenRepository
import com.ecommerce.kontensystem.repository.TransactionRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class KundenService @Autowired constructor(
    private var kundenRepository: KundenRepository,
    private var kontoRepository: KontoRepository, private var transactionRepository: TransactionRepository
) {

    fun saveKunde(kunde: Kunden) {
        kundenRepository.save(kunde)
    }

    fun updateKunde(kunde: Kunden) {
        val existingKunde = kundenRepository.findById(kunde.id!!)
        if (existingKunde.isPresent) {
            kundenRepository.save(kunde)
        }
    }

    fun getKundeById(id: Long): Kunden {
        return kundenRepository.findById(id).orElse(null)
    }

    fun getAllKunden(): List<Kunden> {
        return kundenRepository.findAll()
    }

    fun isIban(iban: String): Boolean {
        return kundenRepository.findAll().any { kunde -> kunde.kontos.any { konto -> konto.iban == iban } }
    }
    //ref: https://docs.spring.io/spring-data/commons/docs/current/api/org/springframework/data/repository/ListCrudRepository.html#findAll()
    fun getKundeByIBAN(iban: String): Kunden? {
        return kundenRepository.findAll().find { kunde -> kunde.kontos.any { konto -> konto.iban == iban } }
    }

    fun getKontoFromKundeByIBAN(iban: String): Konto? {
        return getKundeByIBAN(iban)?.kontos?.find { konto -> konto.iban == iban }
    }

    fun sendTransaction(transaction: Transaction): Boolean {
        return try {
            var kontoSender: Konto? = getKontoFromKundeByIBAN(transaction.senderIBAN)
            if (kontoSender != null) {
                transaction.isDeposit = true
                kontoSender.guthaben -= transaction.amount
                kontoSender.transactions.add(transaction)
                kontoRepository.save(kontoSender)
                transactionRepository.save(transaction)
            }
            var kontoReceiver: Konto? = getKontoFromKundeByIBAN(transaction.receiverIBAN)
            if (kontoReceiver != null) {
                transaction.isDeposit = false
                kontoReceiver.guthaben += transaction.amount
                kontoReceiver.transactions.add(transaction)
                transaction.id = transaction.id?.plus(10000)
                transactionRepository.save(transaction)
            }
            true
        } catch (exception: Exception) {
            exception.printStackTrace()
            false
        }
    }
}