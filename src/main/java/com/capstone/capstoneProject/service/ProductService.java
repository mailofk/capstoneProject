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


    public void saveProduct(String prodName, String prodLink, String smlCategory) throws IOException {
        Product product = new Product();
        product.setProductName(prodName);
        product.setLink(prodLink);

        product.setSmlCategory(smlCategory);

        productRepository.save(product);
    }


}
