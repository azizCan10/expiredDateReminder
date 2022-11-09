package com.example.expireddatereminder.business.concretes;

import com.example.expireddatereminder.business.abstracts.ProductService;
import com.example.expireddatereminder.dataAccess.abstracts.ProductRepository;
import com.example.expireddatereminder.entities.concretes.Product;

import java.util.List;

public class ProductManager implements ProductService {
    private ProductRepository productRepository;

    public ProductManager(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public void add(Product product) {
        productRepository.add(product);
    }

    @Override
    public void update(Product product) {
        productRepository.update(product);
    }

    @Override
    public void delete(int id) {
        productRepository.delete(id);
    }

    @Override
    public List<Product> getAll() {
        return productRepository.getAll();
    }

    @Override
    public Product getById(int id) {
        return productRepository.getById(id);
    }
}
