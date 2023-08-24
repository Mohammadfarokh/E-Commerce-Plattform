package com.ecommerce.kontensystem.entity

import jakarta.persistence.*

@Entity
data class Kunden(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "kunden_id")
    var id: Long? = null,
    var firstname: String,
    var lastname: String,
    var password: String,
    var street: String,
    var city: String,
    var postcode: Int,
    @ManyToOne
    @JoinColumn(name = "verwaltung_id", nullable = false)
    var verwaltung: Verwaltung,
    @OneToMany(cascade = [CascadeType.ALL])
    var kontos: MutableSet<Konto> = mutableSetOf()
)