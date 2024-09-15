package com.ShipperConnect.ShipperConnect.service

import com.ShipperConnect.ShipperConnect.dao.LoadRepository
import com.ShipperConnect.ShipperConnect.model.Load
import org.hibernate.service.spi.ServiceException
import org.springframework.stereotype.Service
import kotlin.NoSuchElementException
import org.slf4j.Logger
import org.slf4j.LoggerFactory

@Service
class LoadService(private val loadRepository: LoadRepository) {

    private val logger: Logger = LoggerFactory.getLogger(LoadService::class.java)

    fun addLoad(load: Load) {
        try {
            logger.info("Adding load: {}", load)
            loadRepository.save(load)
        } catch (e: Exception) {
            logger.error("Error adding load", e)
            throw ServiceException("Error adding load")
        }
    }

    fun getAllLoads(): List<Load> {
        try {
            logger.info("Fetching all loads")
            return loadRepository.findAll()
        } catch (e: Exception) {
            logger.error("Error fetching all loads", e)
            throw ServiceException("Error fetching all loads")
        }
    }

    fun getLoadsByShipperId(shipperId: String): List<Load> {
        try {
            logger.info("Fetching loads by shipperId: {}", shipperId)
            return loadRepository.findAllByShipperId(shipperId)
        } catch (e: Exception) {
            logger.error("Error fetching loads by shipperId: {}", shipperId, e)
            throw ServiceException("Error fetching loads by shipperId")
        }
    }

    fun getLoadById(loadId: Long): Load {
        try {
            logger.info("Fetching load by loadId: {}", loadId)
            return loadRepository.findById(loadId).orElseThrow { NoSuchElementException("Load not found") }
        } catch (e: Exception) {
            logger.error("Error fetching load by loadId: {}", loadId, e)
            throw ServiceException("Error fetching load by loadId")
        }
    }

    fun updateLoad(loadId: Long, load: Load): Load {
        try {
            logger.info("Updating load with loadId: {}", loadId)
            val existingLoad = loadRepository.findById(loadId)
                    .orElseThrow { NoSuchElementException("Load not found") }

            existingLoad.loadingPoint = load.loadingPoint
            existingLoad.unloadingPoint = load.unloadingPoint
            existingLoad.productType = load.productType
            existingLoad.truckType = load.truckType
            existingLoad.noOfTrucks = load.noOfTrucks
            existingLoad.weight = load.weight
            existingLoad.comment = load.comment
            existingLoad.shipperId = load.shipperId
            existingLoad.loadDate = load.loadDate

            // Update other properties as needed
            return loadRepository.save(existingLoad)
        } catch (e: Exception) {
            logger.error("Error updating load with loadId: {}", loadId, e)
            throw ServiceException("Error updating load")
        }
    }

    fun deleteLoad(loadId: Long) {
        try {
            logger.info("Deleting load with loadId: {}", loadId)
            loadRepository.deleteById(loadId)
        } catch (e: Exception) {
            logger.error("Error deleting load with loadId: {}", loadId, e)
            throw ServiceException("Error deleting load")
        }
    }
}
