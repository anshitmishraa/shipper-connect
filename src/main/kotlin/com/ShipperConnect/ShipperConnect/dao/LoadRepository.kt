package com.ShipperConnect.ShipperConnect.dao

import com.ShipperConnect.ShipperConnect.model.Load
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface LoadRepository : JpaRepository<Load, Long> {
    fun findAllByShipperId(shipperId: UUID): List<Load>

    fun fetchAllLoads(): List<Load>
}