package com.ShipperConnect.ShipperConnect.dao

import com.ShipperConnect.ShipperConnect.model.Load
import org.springframework.context.annotation.ComponentScan
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
@ComponentScan
interface LoadRepository : JpaRepository<Load, Long> {
    fun findAllByShipperId(shipperId: String): List<Load>
}