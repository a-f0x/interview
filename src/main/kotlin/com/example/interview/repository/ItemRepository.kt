package com.example.interview.repository

import com.example.interview.model.ItemEntity
import org.springframework.data.repository.CrudRepository

interface ItemRepository : CrudRepository<ItemEntity, Long>
