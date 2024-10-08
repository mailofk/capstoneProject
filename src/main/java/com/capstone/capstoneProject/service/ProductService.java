package com.capstone.capstoneProject.service;

import com.capstone.capstoneProject.domain.Product;
import com.capstone.capstoneProject.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;


    public void saveProduct(String prodName, String prodLink, String smlCategory, String cpu, String ram, String capacity, String weight) throws IOException {
        Product product = new Product();
        product.setProductName(prodName);
        product.setLink(prodLink);
        product.setCpu(cpu);
        product.setRam(ram);
        product.setCapacity(capacity);
        product.setWeight(weight);

        product.setSmlCategory(smlCategory);

        productRepository.save(product);
    }


}
