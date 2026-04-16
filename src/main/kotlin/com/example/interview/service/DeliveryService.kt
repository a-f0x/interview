package com.example.interview.service

import com.example.interview.client.CourierClient
import com.example.interview.model.DeliveryEntity
import com.example.interview.model.DeliveryStatus
import com.example.interview.model.OrderStatus
import com.example.interview.repository.DeliveryRepository
import com.example.interview.repository.OrderRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.OffsetDateTime

@Service
class DeliveryService(
    private val deliveryRepository: DeliveryRepository,
    private val orderRepository: OrderRepository,
    private val courierClient: CourierClient
) {

    fun getDelivery(id: Long): DeliveryEntity =
        deliveryRepository.findById(id).orElseThrow { RuntimeException("Delivery not found: $id") }

    @Transactional(readOnly = true)
    fun createDelivery(orderId: Long, address: String): DeliveryEntity {
        orderRepository.findById(orderId)
            .orElseThrow { RuntimeException("Order not found: $orderId") }

        val delivery = DeliveryEntity(
            id = null,
            orderId = orderId,
            address = address,
            status = DeliveryStatus.NEW,
            createdAt = OffsetDateTime.now(),
            updatedAt = null
        )
        return deliveryRepository.save(delivery)
    }

    @Transactional
    fun advanceDeliveryStatus(id: Long): DeliveryEntity {
        val delivery = getDelivery(id)
        val nextStatus = getNextDeliveryStatus(delivery.status)
        val updated = delivery.copy(status = nextStatus, updatedAt = OffsetDateTime.now())
        val saved = deliveryRepository.save(updated)

        if (nextStatus == DeliveryStatus.READY) {
            courierClient.sendDeliveryNotification(delivery.orderId, delivery.address)
        }

        if (nextStatus == DeliveryStatus.FINISHED) {
            val order = orderRepository.findById(delivery.orderId)
                .orElseThrow { RuntimeException("Order not found: ${delivery.orderId}") }
            orderRepository.save(order.copy(status = OrderStatus.FINISHED, updatedAt = OffsetDateTime.now()))
        }

        return saved
    }

    private fun getNextDeliveryStatus(current: DeliveryStatus): DeliveryStatus = when (current) {
        DeliveryStatus.NEW -> DeliveryStatus.READY
        DeliveryStatus.READY -> DeliveryStatus.IN_PROGRESS
        DeliveryStatus.IN_PROGRESS -> DeliveryStatus.FINISHED
        DeliveryStatus.FINISHED -> throw IllegalStateException("Delivery already finished")
    }
}
