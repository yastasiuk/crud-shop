package com.yastasiuk.shop.repositories.dao;

import java.util.List;
import java.util.ArrayList;

import com.yastasiuk.shop.repositories.dao.ProductDao;

import org.springframework.stereotype.Component;

import com.yastasiuk.shop.model.Product;

/**
 * ProductDaoImpl
 */
@Component
public class ProductDaoImpl implements ProductDao {
    private ProductRepository repository;

    public ProductDaoImpl(ProductRepository repository) {
        this.repository = repository;
        // products.add(new Product(1, "Product One", "Series 1", "Manufacturer 1", "VendorCode 1", ""));
    }
    
    @Override
    public List<Product> getAllProducts() {
        return repository.findAll();
    }

    @Override
    public Product getProductById(String productId) {
        return repository.getProductById(productId);
    }

    @Override
    public Product createProduct(Product product) {
        return repository.save(product);
    }
    

    @Override
    public Product updateProduct(Product product) {
        return repository.save(product);
    }

    @Override
    public void deleteProduct(String productId) {
        repository.deleteById(productId);
    }
}
