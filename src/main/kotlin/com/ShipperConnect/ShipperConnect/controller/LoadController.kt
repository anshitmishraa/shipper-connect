package com.ShipperConnect.ShipperConnect.controller

import com.ShipperConnect.ShipperConnect.model.Load
import com.ShipperConnect.ShipperConnect.service.LoadService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/loads")
public class LoadController(
        @Autowired private val loadService: LoadService,
        private val logger: Logger = LoggerFactory.getLogger(LoadController::class.java)
)  {
    @PostMapping("")
    fun addLoad(@RequestBody load: Load): ResponseEntity<String> {
        try {
            logger.info("Adding load: {}", load)
            loadService.addLoad(load)
            return ResponseEntity.ok("Load details added successfully")
        } catch (e: Exception) {
            val errorMessage = "Error adding load"
            logger.error(errorMessage, e)
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage)
        }
    }

    @GetMapping("")
    fun getAllLoads(): ResponseEntity<List<Load>> {
        try {
            logger.info("Fetching all loads")
            val loads = loadService.getAllLoads()
            return ResponseEntity.ok(loads)
        } catch (e: Exception) {
            val errorMessage = "Error fetching all loads"
            logger.error(errorMessage, e)
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build()
        }
    }

    @GetMapping(params = ["shipperId"])
    fun getLoadsByShipperId(@RequestParam("shipperId") shipperId: String): ResponseEntity<*>? {
        return try {
            logger.info("Fetching loads by shipper ID: {}", shipperId)
            val loads = loadService.getLoadsByShipperId(shipperId!!)
            ResponseEntity.ok(loads)
        } catch (e: Exception) {
            val errorMessage = "Error fetching loads by shipper ID: " + e.message
            logger.error(errorMessage)
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage)
        }
    }

    @GetMapping("/{loadId}")
    fun getLoadById(@PathVariable loadId: Long?): ResponseEntity<*>? {
        return try {
            logger.info("Fetching load by ID: {}", loadId)
            val load = loadService.getLoadById(loadId!!)
            ResponseEntity.ok(load)
        } catch (e: java.lang.Exception) {
            val errorMessage = "Error fetching load by ID: " + e.message
            logger.error(errorMessage)
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage)
        }
    }

    @PutMapping("/{loadId}")
    fun updateLoad(@PathVariable loadId: Long, @RequestBody load: Load): ResponseEntity<Load> {
        return try {
            logger.info("Updating load with ID: {}", loadId)
            val updatedLoad = loadService.updateLoad(loadId, load)
            ResponseEntity.ok(updatedLoad)
        } catch (e: Exception) {
            val errorMessage = "Error updating load with ID: $loadId"
            logger.error(errorMessage, e)
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build()
        }
    }

    @DeleteMapping("/{loadId}")
    fun deleteLoad(@PathVariable loadId: Long): ResponseEntity<Unit> {
        return try {
            logger.info("Deleting load with ID: {}", loadId)
            loadService.deleteLoad(loadId)
            ResponseEntity.noContent().build()
        } catch (e: Exception) {
            val errorMessage = "Error deleting load with ID: $loadId"
            logger.error(errorMessage, e)
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build()
        }
    }
}



