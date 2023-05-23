package com.ShipperConnect.ShipperConnect.service

import com.ShipperConnect.ShipperConnect.dao.LoadRepository
import com.ShipperConnect.ShipperConnect.model.Load
import org.springframework.stereotype.Service
import java.util.*
import kotlin.NoSuchElementException

@Service
class LoadService(private val loadRepository: LoadRepository) {

    fun addLoad(load: Load) {
        loadRepository.save(load)
    }

    fun getAllLoads() : List<Load> {
        return loadRepository.findAll()
    }

    fun getLoadsByShipperId(shipperId: String): List<Load> {
        // Retrieve loads by shipperId from the repository
        return loadRepository.findAllByShipperId(shipperId)
    }

    fun getLoadById(loadId: Long): Load {
        // Retrieve a load by loadId from the repository
        return loadRepository.findById(loadId).orElseThrow { NoSuchElementException("Load not found") }
    }

    fun updateLoad(loadId: Long, load: Load): Load {
        // Update a load in the repository
        val existingLoad = loadRepository.findById(loadId).orElseThrow { NoSuchElementException("Load not found") }

        existingLoad.loadingPoint = load.loadingPoint
        existingLoad.unloadingPoint = load.unloadingPoint
        existingLoad.productType = load.productType
        existingLoad.truckType = load.truckType
        existingLoad.noOfTrucks = load.noOfTrucks
        existingLoad.weight = load.weight
        existingLoad.comment = load.comment
        existingLoad.shipperId = load.shipperId

        // Update other properties as needed
        return loadRepository.save(existingLoad)
    }

    fun deleteLoad(loadId: Long) {
        // Implement the logic to delete a load from the repository
        loadRepository.deleteById(loadId)
    }
}
