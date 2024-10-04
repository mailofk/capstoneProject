package com.capstone.capstoneProject.repository;

import com.capstone.capstoneProject.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category findBySmlCategory(String smlCategory);
}
