package com.yastasiuk.shop.repositories.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.yastasiuk.shop.model.Product;

public interface ProductRepository extends CrudRepository<Product, String> {
     List<Product> findAll();
     Product save(Product product);
     Product getProductById(String productId);
     List<Product> getProductsByName(String name);
}
