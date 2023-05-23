package com.ShipperConnect.ShipperConnect.controller

import com.ShipperConnect.ShipperConnect.model.Load
import com.ShipperConnect.ShipperConnect.service.LoadService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import java.util.UUID


@RestController
@RequestMapping("/loads")
class LoadController(@Autowired private val loadService: LoadService)  {

    @PostMapping("")
    fun addLoad(@RequestBody load: Load): String {
        loadService.addLoad(load)
        return "Load details added successfully"
    }

    @GetMapping("")
    fun getAllLoads(): List<Load> {
        return loadService.getAllLoads()
    }

    @GetMapping("")
    fun getLoadsByShipperId(@RequestParam shipperId: UUID): List<Load> {
        return loadService.getLoadsByShipperId(shipperId)
    }

    @GetMapping("/{loadId}")
    fun getLoadById(@PathVariable loadId: Long): Load {
        return loadService.getLoadById(loadId)
    }

    @PutMapping("/{loadId}")
    fun updateLoad(@PathVariable loadId: Long, @RequestBody load: Load): Load {
        return loadService.updateLoad(loadId, load)
    }

    @DeleteMapping("/{loadId}")
    fun deleteLoad(@PathVariable loadId: Long) {
        loadService.deleteLoad(loadId)
    }
}



