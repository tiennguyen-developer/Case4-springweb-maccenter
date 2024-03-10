package com.codegym.maccenter.service.impl;

import com.codegym.maccenter.model.Product;
import com.codegym.maccenter.repository.ProductRepository;
import com.codegym.maccenter.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductRepository productRepository;

    @Override
    public void updateProduct(Product product) {
        productRepository.save(product);
    }

    @Override
    public void deleteProductById(long id) {
        productRepository.deleteById(id);
    }

    @Override
    public Optional<Product> getProductById(long id) {
        return productRepository.findById(id);
    }

    @Override
    public List<Product> getAllProductByCategoryId(int id) {
        return productRepository.findAllProductByCategory_Id(id);
    }

    @Override
    public List<Product> getAllProduct() {
        return productRepository.findAll();
    }
   }
