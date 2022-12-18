package com.example.expireddatereminder.repository;

import com.example.expireddatereminder.entity.Product;

import java.util.List;

public interface ProductRepository {
    void add(Product product);
    void update(Product product);
    void delete(int id);
    List<Product> getAll();
    Product getById(int id);
}
