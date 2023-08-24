package com.ecommerce.kontensystem.repository

import com.ecommerce.kontensystem.entity.Verwaltung
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface VerwaltungRepository: JpaRepository<Verwaltung, Long> {
}