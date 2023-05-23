package com.ShipperConnect.ShipperConnect.model

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import java.util.*

@Entity
data class Load(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long = 0,

        var loadingPoint: String,
        var unloadingPoint: String,
        var productType: String,
        var truckType: String,
        var noOfTrucks: Integer,
        var weight: Integer,
        var comment: String? = null,
        var shipperId: UUID,
        var loadDate: Date
) {
    // Getter and Setter for shipperId
    fun getLoadShipperId(): UUID {
        return shipperId
    }

    fun setLoadShipperId(shipperId: UUID) {
        this.shipperId = shipperId
    }
}
