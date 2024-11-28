package com.brasileiras.ecommerce.repository;

import com.brasileiras.ecommerce.model.ProductModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<ProductModel, Long> {

}
