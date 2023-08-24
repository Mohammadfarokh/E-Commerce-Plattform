package com.ecommerce.kontensystem.service

import com.ecommerce.kontensystem.entity.Konto
import com.ecommerce.kontensystem.repository.KontoRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class KontoService @Autowired constructor(private var kontoRepository: KontoRepository) {

    fun saveKonto(konto: Konto) {
        kontoRepository.save(konto)
    }

    fun updateKonto(konto: Konto) {
        val existingKonto = kontoRepository.findById(konto.id!!)
        if (existingKonto.isPresent) {
            kontoRepository.save(konto)
        }
    }

    fun getKontoById(id: Long): Konto {
        return kontoRepository.findById(id).orElse(null)
    }

    fun getAllKonto(): List<Konto> {
        return kontoRepository.findAll()
    }
}