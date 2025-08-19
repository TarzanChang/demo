package com.example_tarzan.demo.repositories;

import com.example_tarzan.demo.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Integer> {
}
