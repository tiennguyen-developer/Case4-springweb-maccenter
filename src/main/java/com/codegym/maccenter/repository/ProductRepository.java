package com.codegym.maccenter.repository;

import com.codegym.maccenter.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findAllProductByCategory_Id(int id);
}
