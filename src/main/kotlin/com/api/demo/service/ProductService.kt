package com.api.demo.service

import com.api.demo.exception.ResourceNotFoundException
import com.api.demo.model.Product
import com.api.demo.repository.ProductRepository
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class ProductService(private val productRepository: ProductRepository) {

    fun getAllProducts(): List<Product> = productRepository.findAll()

    fun getProductById(id: Long): Product = productRepository.findById(id)
        .orElseThrow { ResourceNotFoundException("Product not found with id: $id") }

    fun searchProductsByName(name: String): List<Product> = 
        productRepository.findByNameContainingIgnoreCase(name)

    fun createProduct(product: Product): Product = productRepository.save(product)

    fun updateProduct(id: Long, productDetails: Product): Product {
        val existingProduct = getProductById(id)
        
        existingProduct.name = productDetails.name
        existingProduct.description = productDetails.description
        existingProduct.price = productDetails.price
        existingProduct.updatedAt = LocalDateTime.now()
        
        return productRepository.save(existingProduct)
    }

    fun deleteProduct(id: Long) {
        val product = getProductById(id)
        productRepository.delete(product)
    }
}