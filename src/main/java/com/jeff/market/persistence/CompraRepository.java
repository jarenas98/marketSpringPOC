package com.jeff.market.persistence;

import com.jeff.market.domain.Purchase;
import com.jeff.market.domain.repository.PurchaseRepository;
import com.jeff.market.persistence.crud.CompraCrudRepository;
import com.jeff.market.persistence.entity.Compra;
import com.jeff.market.persistence.mapper.PurchaseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CompraRepository implements PurchaseRepository {

    @Autowired
    private CompraCrudRepository compraCrudRepository;

    @Autowired
    private PurchaseMapper purchaseMapper;

    @Override
    public List<Purchase> getAll() {
        return this.purchaseMapper.toPurchases((List<Compra>) this.compraCrudRepository.findAll());
    }

    @Override
    public Optional<List<Purchase>> getByClient(String clientId) {
        return this.compraCrudRepository.findByIdCliente(clientId)
                .map(compras -> this.purchaseMapper.toPurchases(compras));
    }

    @Override
    public Purchase save(Purchase purchase) {
        Compra compra = this.purchaseMapper.toCompra(purchase);
        compra.getComprasProductos().forEach(comprasProducto -> comprasProducto.setCompra(compra));

        return this.purchaseMapper.toPurchase(this.compraCrudRepository.save(compra));
    }
}
