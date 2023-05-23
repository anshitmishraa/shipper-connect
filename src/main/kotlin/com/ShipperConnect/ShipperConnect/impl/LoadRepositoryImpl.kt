package com.ShipperConnect.ShipperConnect.impl

import com.ShipperConnect.ShipperConnect.dao.LoadRepository
import com.ShipperConnect.ShipperConnect.model.Load
import jakarta.persistence.EntityManager
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import java.util.*

abstract class LoadRepositoryImpl(private val entityManager: EntityManager) : LoadRepository {

    override fun findAllByShipperId(shipperId: String): List<Load> {
        return findAll().filter { it.shipperId == shipperId }
    }

}