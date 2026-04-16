package com.example.interview.model

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import java.time.OffsetDateTime

@Table("orders")
data class OrderEntity (
    @Id
    val id: Long?,
    val status: OrderStatus,
    val createdAt: OffsetDateTime,
    val updatedAt: OffsetDateTime?
)