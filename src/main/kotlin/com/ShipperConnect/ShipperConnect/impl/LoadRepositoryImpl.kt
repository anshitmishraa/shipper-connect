package com.ShipperConnect.ShipperConnect.impl

import com.ShipperConnect.ShipperConnect.dao.LoadRepository
import com.ShipperConnect.ShipperConnect.model.Load
import jakarta.persistence.EntityManager
import org.springframework.stereotype.Repository
import java.util.*

@Repository
abstract class LoadRepositoryImpl(private val entityManager: EntityManager) : LoadRepository {

    override fun findAllByShipperId(shipperId: UUID): List<Load> {
        return findAll().filter { it.shipperId == shipperId }
    }

    override fun fetchAllLoads(): List<Load> {
        val query = entityManager.createQuery("SELECT l FROM Load l", Load::class.java)
        return query.resultList
    }

}