package com.example.interview.model

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import java.time.OffsetDateTime

@Table("items")
data class ItemEntity (
    @Id
    val id: Long?,
    val name: String,
    val status: ItemStatus,
    val createdAt: OffsetDateTime,
    val updatedAt: OffsetDateTime?
)