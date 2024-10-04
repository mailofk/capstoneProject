package com.capstone.capstoneProject.repository;

import com.capstone.capstoneProject.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
