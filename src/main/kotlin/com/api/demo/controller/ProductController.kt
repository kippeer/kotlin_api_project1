package com.api.demo.controller

import com.api.demo.dto.ApiResponse
import com.api.demo.model.Product
import com.api.demo.service.ProductService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/products")
@Tag(name = "Product API", description = "Operations related to product management")
class ProductController(private val productService: ProductService) {

    @GetMapping
    @Operation(summary = "Get all products")
    fun getAllProducts(): ResponseEntity<ApiResponse<List<Product>>> {
        val products = productService.getAllProducts()
        return ResponseEntity.ok(ApiResponse(
            success = true,
            message = "Products retrieved successfully",
            data = products
        ))
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get product by ID")
    fun getProductById(@PathVariable id: Long): ResponseEntity<ApiResponse<Product>> {
        val product = productService.getProductById(id)
        return ResponseEntity.ok(ApiResponse(
            success = true,
            message = "Product retrieved successfully",
            data = product
        ))
    }

    @GetMapping("/search")
    @Operation(summary = "Search products by name")
    fun searchProducts(@RequestParam name: String): ResponseEntity<ApiResponse<List<Product>>> {
        val products = productService.searchProductsByName(name)
        return ResponseEntity.ok(ApiResponse(
            success = true,
            message = "Products retrieved successfully",
            data = products
        ))
    }

    @PostMapping
    @Operation(summary = "Create a new product")
    fun createProduct(@Valid @RequestBody product: Product): ResponseEntity<ApiResponse<Product>> {
        val createdProduct = productService.createProduct(product)
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse(
            success = true,
            message = "Product created successfully",
            data = createdProduct
        ))
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update an existing product")
    fun updateProduct(
        @PathVariable id: Long,
        @Valid @RequestBody productDetails: Product
    ): ResponseEntity<ApiResponse<Product>> {
        val updatedProduct = productService.updateProduct(id, productDetails)
        return ResponseEntity.ok(ApiResponse(
            success = true,
            message = "Product updated successfully",
            data = updatedProduct
        ))
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a product")
    fun deleteProduct(@PathVariable id: Long): ResponseEntity<ApiResponse<Nothing>> {
        productService.deleteProduct(id)
        return ResponseEntity.ok(ApiResponse(
            success = true,
            message = "Product deleted successfully",
            data = null
        ))
    }
}