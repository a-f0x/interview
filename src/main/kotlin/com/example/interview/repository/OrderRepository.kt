package com.example.interview.repository

import com.example.interview.model.OrderEntity
import org.springframework.data.repository.CrudRepository

interface OrderRepository : CrudRepository<OrderEntity, Long>
