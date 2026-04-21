package com.example.interview.rest

import com.example.interview.model.DeliveryEntity
import com.example.interview.service.DeliveryService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/deliveries")
class DeliveryController(private val deliveryService: DeliveryService) {

    @PostMapping
    fun createDelivery(@RequestBody request: CreateDeliveryRequest): DeliveryEntity =
        deliveryService.createDelivery(request.orderId, request.address)

    @PostMapping("/{id}/advance")
    fun advanceDelivery(@PathVariable id: Long): DeliveryEntity =
        deliveryService.advanceDeliveryStatus(id)
}

data class CreateDeliveryRequest(
    val orderId: Long,
    val address: String
)
