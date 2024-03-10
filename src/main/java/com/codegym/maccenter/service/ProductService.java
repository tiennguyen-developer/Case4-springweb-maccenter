package com.codegym.maccenter.service;

import com.codegym.maccenter.model.Product;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface ProductService {

	List<Product> getAllProductByCategoryId(int id);

	Optional<Product> getProductById(long id);

	void deleteProductById(long id);

	void updateProduct(Product product);

	List<Product> getAllProduct();
    
}
