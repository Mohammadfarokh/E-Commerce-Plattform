package com.ecommerce.kontensystem.controller

import com.ecommerce.kontensystem.entity.Konto
import com.ecommerce.kontensystem.entity.Kunden
import com.ecommerce.kontensystem.entity.Transaction
import com.ecommerce.kontensystem.service.KontoService
import com.ecommerce.kontensystem.service.KundenService
import com.ecommerce.kontensystem.service.TransactionService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(value = ["api/kunde"])
class KundenController @Autowired constructor(
    private var kontoService: KontoService,
    private var transactionService: TransactionService,
    private var kundenService: KundenService
) {
    @PostMapping(value = ["/kunde-add"])
    @CrossOrigin("*")
    fun addKunde(@RequestBody request: Kunden): Boolean {
        return try {
            kundenService.saveKunde(
                Kunden(
                    firstname = request.firstname,
                    lastname = request.lastname,
                    password = request.password,
                    street = request.street,
                    city = request.city,
                    postcode = request.postcode,
                    verwaltung = request.verwaltung
                )
            )
            true
        } catch (exception: Exception) {
            exception.printStackTrace()
            false
        }
    }

    @GetMapping(value = ["/kunde-get"])
    @CrossOrigin("*")
    fun getKunden(): List<Kunden>? {
        try {
            return kundenService.getAllKunden()
        } catch (exception: Exception) {
            exception.printStackTrace()
        }
        return null
    }

    @PutMapping(value = ["/kunde-update"])
    @CrossOrigin("*")
    fun updateKunde(@RequestBody request: Kunden): Boolean {
        return try {
            kundenService.updateKunde(request)
            true
        } catch (exception: Exception) {
            exception.printStackTrace()
            false
        }
    }

    @PostMapping(value = ["/konto-add"])
    @CrossOrigin("*")
    fun addKonto(@RequestBody request: Konto): Boolean {
        return try {
            kontoService.saveKonto(
                Konto(
                    bankName = request.bankName, iban = request.iban, guthaben = request.guthaben,
                    kunden = request.kunden
                )
            )
            true
        } catch (exception: Exception) {
            exception.printStackTrace()
            false
        }
    }

    @PutMapping(value = ["/konto-update"])
    @CrossOrigin("*")
    fun updateKonto(@RequestBody request: Konto): Boolean {
        return try {
            kontoService.updateKonto(request)
            true
        } catch (exception: Exception) {
            exception.printStackTrace()
            false
        }
    }

    @GetMapping(value = ["/konto-get"])
    @CrossOrigin("*")
    fun getKonto(): List<Konto>? {
        try {
            return kontoService.getAllKonto()
        } catch (exception: Exception) {
            exception.printStackTrace()
        }
        return null
    }

    @PostMapping(value = ["konto-uebersicht"])
    @CrossOrigin("*")
    fun showTransactions(@RequestBody request: Kunden): List<Transaction>? {
        return try {
            var tmpTra: MutableSet<Transaction> = mutableSetOf<Transaction>()
            var tmp: MutableSet<Konto> = kundenService.getKundeById(request.id!!).kontos
            tmp.forEach{ konto -> konto.transactions.forEach { transaction ->  tmpTra.add(transaction)} }
            println("Transaktionen: $tmpTra")
            tmpTra.toList()
        } catch (exception: Exception) {
            exception.printStackTrace()
            null
        }
    }

    @GetMapping(value = ["/transaction-get"])
    @CrossOrigin("*")
    fun getTransaction(): List<Transaction>? {
        try {
            return transactionService.getAllTransaction()
        } catch (exception: Exception) {
            exception.printStackTrace()
        }
        return null
    }

    @PostMapping(value = ["/ueberweisen"])
    @CrossOrigin("*")
    fun sendTransaction(@RequestBody transaction: Transaction): Boolean {
        if (transaction.amount < 0) {
            println("Summe muss positiv sein")
            return false
        } else if (kundenService.getKontoFromKundeByIBAN(transaction.senderIBAN) == null ||
            kundenService.getKontoFromKundeByIBAN(transaction.receiverIBAN) == null
        ) {
            println("Das Konto ist nicht regestiert")
            return false
        } else {
             kundenService.sendTransaction(transaction)
        }
        return true
    }
}


