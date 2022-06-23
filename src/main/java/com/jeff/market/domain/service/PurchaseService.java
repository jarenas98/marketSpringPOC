package com.jeff.market.domain.service;

import com.jeff.market.domain.Purchase;
import com.jeff.market.domain.repository.PurchaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PurchaseService {

    @Autowired
    private PurchaseRepository purchaseRepository;

    public List<Purchase> getAll() {
        return this.purchaseRepository.getAll();
    }

    public Optional<List<Purchase>> getByClient(String clientId) {
        return this.purchaseRepository.getByClient(clientId);
    }

    public Purchase save(Purchase purchase) {
        return this.purchaseRepository.save(purchase);
    }

}
