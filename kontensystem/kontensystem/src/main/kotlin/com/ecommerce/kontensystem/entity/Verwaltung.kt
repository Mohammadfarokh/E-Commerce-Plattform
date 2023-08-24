package com.ecommerce.kontensystem.entity

import jakarta.persistence.*

@Entity
data class Verwaltung(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "verwaltung_id")
    var id: Long? = null,
    @OneToMany(cascade = [CascadeType.ALL])
    var kunden: MutableSet<Kunden> = mutableSetOf()
)