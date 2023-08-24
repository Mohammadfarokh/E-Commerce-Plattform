package com.ecommerce.kontensystem.entity

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
data class Transaction(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transaction_id")
    var id: Long? = null,
    var date: LocalDateTime = LocalDateTime.now(),
    var senderIBAN: String = generateSequence { readlnOrNull() ?: "" }
        .firstOrNull { it.matches("^DE\\d{20}$".toRegex()) } ?: "",
    var receiverIBAN: String = generateSequence { readlnOrNull() ?: "" }
        .firstOrNull { it.matches("^DE\\d{20}$".toRegex()) } ?: "",
    var isDeposit: Boolean,
    var amount: Double,
    @ManyToOne
    @JoinColumn(name = "konto_id", nullable = false)
    var konto: Konto
)