package com.ecommerce.kontensystem.service

import com.ecommerce.kontensystem.entity.Transaction
import com.ecommerce.kontensystem.repository.TransactionRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class TransactionService @Autowired constructor(private var transactionRepository: TransactionRepository) {

    fun saveTransaction(transaction: Transaction) {
        transactionRepository.save(transaction)
    }

    fun updateTransaction(transaction: Transaction) {
        val existingTransaction = transactionRepository.findById(transaction.id!!)
        if (existingTransaction.isPresent) {
            transactionRepository.save(transaction)
        }
    }

    fun getTransactionById(id: Long): Transaction {
        return transactionRepository.findById(id).orElse(null)
    }

    fun getAllTransaction(): List<Transaction> {
        return transactionRepository.findAll()
    }
}