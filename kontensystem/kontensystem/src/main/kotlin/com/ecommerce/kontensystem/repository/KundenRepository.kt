package com.ecommerce.kontensystem.repository

import org.springframework.stereotype.Repository
import org.springframework.data.jpa.repository.JpaRepository
import com.ecommerce.kontensystem.entity.Kunden

@Repository
interface KundenRepository: JpaRepository<Kunden, Long> {
}