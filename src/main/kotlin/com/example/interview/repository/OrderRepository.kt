package com.example.interview.repository

import com.example.interview.model.OrderEntity
import org.springframework.data.jdbc.repository.query.Query
import org.springframework.data.repository.CrudRepository

interface OrderRepository : CrudRepository<OrderEntity, Long>
