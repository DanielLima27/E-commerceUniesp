package com.brasileiras.ecommerce.model;


import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
public class SaleModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String saleNumber;
    private LocalDate saleDate;
    private double totalAmount;

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private ClientModel client;

    @ManyToMany
    @JoinTable(
            name = "sale_products",
            joinColumns = @JoinColumn(name = "sale_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private List<ProductModel> products;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "sale_id")
    private List<PaymentModel> payments;

    @Enumerated(EnumType.STRING)
    private SaleStatus status;
}
