package com.api.demo.service

import com.api.demo.exception.ResourceNotFoundException
import com.api.demo.model.Product
import com.api.demo.repository.ProductRepository
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.kotlin.any
import org.mockito.kotlin.whenever
import java.math.BigDecimal
import java.util.*

@ExtendWith(MockitoExtension::class)
class ProductServiceTest {

    @Mock
    private lateinit var productRepository: ProductRepository

    @InjectMocks
    private lateinit var productService: ProductService

    private lateinit var product: Product

    @BeforeEach
    fun setup() {
        product = Product(
            id = 1L,
            name = "Test Product",
            description = "Test Description",
            price = BigDecimal("19.99")
        )
    }

    @Test
    fun `getAllProducts should return all products`() {
        // Given
        whenever(productRepository.findAll()).thenReturn(listOf(product))

        // When
        val result = productService.getAllProducts()

        // Then
        assertEquals(1, result.size)
        assertEquals("Test Product", result[0].name)
        verify(productRepository, times(1)).findAll()
    }

    @Test
    fun `getProductById should return product when found`() {
        // Given
        whenever(productRepository.findById(1L)).thenReturn(Optional.of(product))

        // When
        val result = productService.getProductById(1L)

        // Then
        assertEquals("Test Product", result.name)
        verify(productRepository, times(1)).findById(1L)
    }

    @Test
    fun `getProductById should throw exception when product not found`() {
        // Given
        whenever(productRepository.findById(1L)).thenReturn(Optional.empty())

        // When/Then
        val exception = assertThrows(ResourceNotFoundException::class.java) {
            productService.getProductById(1L)
        }
        
        assertEquals("Product not found with id: 1", exception.message)
        verify(productRepository, times(1)).findById(1L)
    }

    @Test
    fun `searchProductsByName should return matching products`() {
        // Given
        whenever(productRepository.findByNameContainingIgnoreCase("Test")).thenReturn(listOf(product))

        // When
        val result = productService.searchProductsByName("Test")

        // Then
        assertEquals(1, result.size)
        assertEquals("Test Product", result[0].name)
        verify(productRepository, times(1)).findByNameContainingIgnoreCase("Test")
    }

    @Test
    fun `createProduct should save and return the product`() {
        // Given
        whenever(productRepository.save(any())).thenReturn(product)

        // When
        val result = productService.createProduct(product)

        // Then
        assertEquals("Test Product", result.name)
        verify(productRepository, times(1)).save(product)
    }

    @Test
    fun `updateProduct should update and return the product when found`() {
        // Given
        val updatedProduct = Product(
            id = 1L,
            name = "Updated Product",
            description = "Updated Description",
            price = BigDecimal("29.99")
        )
        
        whenever(productRepository.findById(1L)).thenReturn(Optional.of(product))
        whenever(productRepository.save(any())).thenReturn(updatedProduct)

        // When
        val result = productService.updateProduct(1L, updatedProduct)

        // Then
        assertEquals("Updated Product", result.name)
        verify(productRepository, times(1)).findById(1L)
        verify(productRepository, times(1)).save(any())
    }

    @Test
    fun `deleteProduct should delete the product when found`() {
        // Given
        whenever(productRepository.findById(1L)).thenReturn(Optional.of(product))
        doNothing().`when`(productRepository).delete(product)

        // When
        productService.deleteProduct(1L)

        // Then
        verify(productRepository, times(1)).findById(1L)
        verify(productRepository, times(1)).delete(product)
    }
}