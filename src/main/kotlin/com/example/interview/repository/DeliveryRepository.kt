package com.example.interview.repository

import com.example.interview.model.DeliveryEntity
import org.springframework.data.repository.CrudRepository

interface DeliveryRepository : CrudRepository<DeliveryEntity, Long>
