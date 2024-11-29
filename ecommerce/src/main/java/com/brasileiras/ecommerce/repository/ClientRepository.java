package com.brasileiras.ecommerce.repository;

import com.brasileiras.ecommerce.model.ClientModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<ClientModel, Long> {
}
