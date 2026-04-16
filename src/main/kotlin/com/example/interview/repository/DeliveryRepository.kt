package com.example.interview.repository

import com.example.interview.model.DeliveryEntity
import org.springframework.data.jdbc.repository.query.Query
import org.springframework.data.repository.CrudRepository

interface DeliveryRepository : CrudRepository<DeliveryEntity, Long>
