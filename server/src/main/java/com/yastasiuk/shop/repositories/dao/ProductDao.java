package com.yastasiuk.shop.repositories.dao;

import java.util.List;

import com.yastasiuk.shop.model.Product;

/**
 * ProductDao
 */
public interface ProductDao {
    List<Product> getAllProducts();
    Product createProduct(Product product);
    Product getProductById(String product);
    List<Product> getProductsByName(String name);
    Product updateProduct(Product product);
    void deleteProduct(String productId);
}
