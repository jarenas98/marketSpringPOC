package com.jeff.market.domain.service;

import com.jeff.market.domain.Product;
import com.jeff.market.domain.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAll() {
        return this.productRepository.getAll();
    }

    public Optional<Product> getProduct(int productId) {
        return this.productRepository.getProduct(productId);
    }

    public Optional<List<Product>> getByCategory(int categoryId) {
        return this.productRepository.getByCategory(categoryId);
    }

    public Product save(Product product) {
        return this.productRepository.save(product);
    }

    public boolean delete(int productId) {
        return this.getProduct(productId).map(product -> {
            this.productRepository.delete(productId);
            return true;
        }).orElse(false);
    }
}
