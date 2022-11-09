package com.example.expireddatereminder.business.abstracts;

import com.example.expireddatereminder.entities.concretes.Product;

import java.util.List;

public interface ProductService {
    void add(Product product);
    void update(Product product);
    void delete(int id);
    List<Product> getAll();
    Product getById(int id);
}
