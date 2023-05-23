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
        return loadRepository.fetchAllLoads()
    }

    fun getLoadsByShipperId(shipperId: UUID): List<Load> {
        // Implement the logic to retrieve loads by shipperId from the repository
        return loadRepository.findAllByShipperId(shipperId)
    }

    fun getLoadById(loadId: Long): Load {
        // Implement the logic to retrieve a load by loadId from the repository
        return loadRepository.findById(loadId).orElseThrow { NoSuchElementException("Load not found") }
    }

    fun updateLoad(loadId: Long, load: Load): Load {
        // Implement the logic to update a load in the repository
        val existingLoad = loadRepository.findById(loadId).orElseThrow { NoSuchElementException("Load not found") }

        existingLoad.loadingPoint = load.loadingPoint
        existingLoad.unloadingPoint = load.unloadingPoint
        existingLoad.productType = load.productType
        existingLoad.truckType = load.truckType
        existingLoad.noOfTrucks = load.noOfTrucks
        existingLoad.weight = load.weight
        existingLoad.comment = load.comment
        existingLoad.setLoadShipperId(load.shipperId)

        // Update other properties as needed
        return loadRepository.save(existingLoad)
    }

    fun deleteLoad(loadId: Long) {
        // Implement the logic to delete a load from the repository
        loadRepository.deleteById(loadId)
    }
}
