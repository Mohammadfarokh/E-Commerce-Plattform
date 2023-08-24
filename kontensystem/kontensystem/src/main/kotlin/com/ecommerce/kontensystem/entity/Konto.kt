package com.ecommerce.kontensystem.entity

import jakarta.persistence.*


@Entity
data class Konto(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "konto_id")
    var id: Long? = null,
    var bankName: String,
    var iban: String = generateSequence { readlnOrNull() ?: "" }
        .firstOrNull { it.matches("^DE\\d{20}$".toRegex()) } ?: "",
    var guthaben: Double,
    @ManyToOne
    @JoinColumn(name = "kunden_id", nullable = false)
    var kunden: Kunden,
    @OneToMany(cascade = [CascadeType.ALL])
    var transactions: MutableSet<Transaction> = mutableSetOf()
)