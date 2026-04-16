package com.example.interview.model

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import java.time.OffsetDateTime

@Table("delivery")
data class DeliveryEntity (
    @Id
    val id: Long?,
    val orderId: Long,
    val address: String,
    val status: DeliveryStatus,
    val createdAt: OffsetDateTime,
    val updatedAt: OffsetDateTime?
)