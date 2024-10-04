package com.capstone.capstoneProject.service;

import com.capstone.capstoneProject.domain.Category;
import com.capstone.capstoneProject.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public void saveCategory(String bigCategory, String midCategory, String smlCategory) {
        Category category = new Category();
        category.setBigCategory(bigCategory);
        category.setMidCategory(midCategory);
        category.setSmlCategory(smlCategory);

        categoryRepository.save(category);
    }

    public Category findOneCategory(String smlCategory) {
        return categoryRepository.findBySmlCategory(smlCategory);
    }

    public List<Category> findAllCategory() {
        return categoryRepository.findAll();
    }


}
