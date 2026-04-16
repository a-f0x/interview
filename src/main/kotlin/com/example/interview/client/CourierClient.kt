package com.example.interview.client

import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate

@Component
class CourierClient(private val restTemplate: RestTemplate) {

    fun sendDeliveryNotification(orderId: Long, address: String) {
        val request = mapOf(
            "orderId" to orderId,
            "address" to address
        )
        restTemplate.postForEntity(
            "http://courier-service/api/notifications",
            request,
            Void::class.java
        )
    }
}
