package com.ecommerce.kontensystem.controller

import com.ecommerce.kontensystem.entity.Transaction
import com.ecommerce.kontensystem.entity.Verwaltung
import com.ecommerce.kontensystem.service.TransactionService
import com.ecommerce.kontensystem.service.VerwaltungService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import java.time.LocalDate

@RestController
@RequestMapping(value = ["api/verwaltung"])
class VerwaltungController @Autowired constructor(
    private var verwaltungService: VerwaltungService,
    private var transactionService: TransactionService
) {

    @PostMapping(value = ["/verwaltung-add"])
    @CrossOrigin("*")
    fun addVerwaltung(): Boolean {
        try {
            verwaltungService.saveVerwaltung(Verwaltung())
        } catch (exception: Exception) {
            exception.printStackTrace()
            return false
        }
        return true
    }

    @GetMapping(value = ["/verwaltung-get"])
    @CrossOrigin("*")
    fun getVerwaltung(): List<Verwaltung>? {
        try {
            return verwaltungService.getAllVerwaltung()
        } catch (exception: Exception) {
            exception.printStackTrace()
        }
        return null
    }

    @PostMapping(value = ["/verwaltung-get-with-id"])
    @CrossOrigin("*")
    fun getVerwaltungWithId(@RequestBody id: Long): Verwaltung? {
        try {
            return verwaltungService.getVerwaltungById(id)
        } catch (exception: Exception) {
            exception.printStackTrace()
        }
        return null
    }

    @GetMapping(value = ["/verwaltung-get-all-transactions"])
    @CrossOrigin("*")
    fun getAllTransactions(): List<Transaction>? {
        try {
            val tmp: List<Transaction> = transactionService.getAllTransaction().stream().filter { transaction ->
                transaction.date.year.equals(
                    LocalDate.now().year
                ) && transaction.date.dayOfYear.equals(LocalDate.now().dayOfYear)
            }.toList()
            println("Die Anzahl der Transaktionen für heute ist: " + tmp.count())
            println("Die Anzahl von Einzahlungen ist: " + tmp.count { transaction -> transaction.isDeposit })
            println("Die Anzahl von Auszahlungen ist: " + tmp.count { transaction -> !transaction.isDeposit })
            println("Die Summe von den Transaktinen für heute ist: " + tmp.sumOf { transaction -> transaction.amount })
            return tmp
        } catch (exception: Exception) {
            exception.printStackTrace()
        }
        return null
    }


}
