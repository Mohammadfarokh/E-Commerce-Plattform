package com.ecommerce.kontensystem.service

import com.ecommerce.kontensystem.entity.Verwaltung
import com.ecommerce.kontensystem.repository.VerwaltungRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class VerwaltungService @Autowired constructor(private var verwaltungRepository: VerwaltungRepository) {

    fun saveVerwaltung(verwaltung: Verwaltung) {
        verwaltungRepository.save(verwaltung)
    }

    fun updateVerwaltung(verwaltung: Verwaltung) {
        val existingVerwaltung = verwaltungRepository.findById(verwaltung.id!!)
        if (existingVerwaltung.isPresent) {
            verwaltungRepository.save(verwaltung)
        }
    }

    fun getVerwaltungById(id: Long): Verwaltung {
        return verwaltungRepository.findById(id).orElse(null)
    }

    fun getAllVerwaltung(): List<Verwaltung> {
        return verwaltungRepository.findAll()
    }
}