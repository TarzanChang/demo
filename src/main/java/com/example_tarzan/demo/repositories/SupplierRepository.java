package com.example_tarzan.demo.repositories;

import com.example_tarzan.demo.models.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SupplierRepository extends JpaRepository<Supplier,Integer> {
}
