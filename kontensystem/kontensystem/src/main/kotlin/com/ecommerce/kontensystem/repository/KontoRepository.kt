package com.ecommerce.kontensystem.repository

import org.springframework.data.jpa.repository.JpaRepository
import com.ecommerce.kontensystem.entity.Konto
import org.springframework.stereotype.Repository

@Repository
interface KontoRepository : JpaRepository<Konto, Long> {
}