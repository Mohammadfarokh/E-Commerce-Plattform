package com.ecommerce.kontensystem

import com.ecommerce.kontensystem.entity.Konto
import com.ecommerce.kontensystem.entity.Kunden
import com.ecommerce.kontensystem.entity.Transaction
import com.ecommerce.kontensystem.entity.Verwaltung
import com.ecommerce.kontensystem.repository.VerwaltungRepository
import com.ecommerce.kontensystem.service.VerwaltungService
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class KontensystemApplication

fun main(args: Array<String>) {
	runApplication<KontensystemApplication>(*args)

}
