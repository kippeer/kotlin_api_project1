package com.api.demo.repository

import com.api.demo.model.Product
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ProductRepository : JpaRepository<Product, Long> {
    fun findByNameContainingIgnoreCase(name: String): List<Product>
}