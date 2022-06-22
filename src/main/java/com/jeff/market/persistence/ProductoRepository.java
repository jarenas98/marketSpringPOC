package com.jeff.market.persistence;

import com.jeff.market.domain.Product;
import com.jeff.market.domain.repository.ProductRepository;
import com.jeff.market.persistence.crud.ProductoCrudRepository;
import com.jeff.market.persistence.entity.Producto;
import com.jeff.market.persistence.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ProductoRepository implements ProductRepository {
    @Autowired
    private ProductoCrudRepository productoCrudRepository;

    @Autowired
    private ProductMapper mapper;

    @Override
    public List<Product> getAll() {
        List<Producto> productos = (List<Producto>) productoCrudRepository.findAll();
        return this.mapper.toProducts(productos);
    }

    @Override
    public Optional<List<Product>> getByCategory(int categoryId) {
        List<Producto> productos = productoCrudRepository.findByIdCategoriaOrderByNombreAsc(categoryId);
        return Optional.of(mapper.toProducts(productos));
    }

    @Override
    public Optional<List<Product>> getScarceProducts(int quantity) {
        Optional<List<Producto>> productos = this.productoCrudRepository.findByCantidadStockIsLessThanAndEstado(quantity, true);

        return productos.map(prods -> mapper.toProducts(prods));
    }

    @Override
    public Optional<Product> getProduct(int productId) {
        return this.productoCrudRepository.findById(productId).map(producto -> mapper.toProduct(producto));
    }

    @Override
    public Product save(Product newProduct) {
        Producto producto = this.mapper.toProducto(newProduct);
        return this.mapper.toProduct(this.productoCrudRepository.save(producto));
    }

    @Override
    public void delete(int productId) {
        this.productoCrudRepository.deleteById(productId);
    }
}
