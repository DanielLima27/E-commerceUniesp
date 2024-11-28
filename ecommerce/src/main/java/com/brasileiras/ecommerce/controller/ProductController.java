package com.brasileiras.ecommerce.controller;

import com.brasileiras.ecommerce.model.ProductModel;
import com.brasileiras.ecommerce.model.ProductModel;
import com.brasileiras.ecommerce.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping
    public List<ProductModel> listProducts() {
        return productRepository.findAll();
    }

    @PostMapping
    public ProductModel createProduct(@RequestBody ProductModel product) {
        return productRepository.save(product);
    }

    @PutMapping("/{id}")
    public ProductModel updateProduct(@PathVariable Long id, @RequestBody ProductModel product) {
        ProductModel existingProduct = productRepository.findById(id).orElseThrow();
        existingProduct.setDescription(product.getDescription());
        existingProduct.setPurchaseValue(product.getPurchaseValue());
        existingProduct.setSaleValue(product.getSaleValue());
        existingProduct.setStock(product.getStock());
        existingProduct.setSupplier(product.getSupplier());
        return productRepository.save(existingProduct);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id) {
        productRepository.deleteById(id);
    }
}
