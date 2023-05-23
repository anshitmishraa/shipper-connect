package com.ShipperConnect.ShipperConnect.controller

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import org.springframework.http.ResponseEntity

@RestController
class HealthController {
    @GetMapping("/health")
    fun checkHealth(): ResponseEntity<String> {
        // Perform health checks here
        val isHealthy = true

        return if (isHealthy) {
            ResponseEntity.ok().body("Application is healthy")
        } else {
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Application is not healthy")
        }
    }
}
