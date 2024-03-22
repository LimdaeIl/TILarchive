package com.example.coffee.service;

import com.example.coffee.domain.StoreProduct;
import com.example.coffee.repository.StoreProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StoreService {

    private final StoreProductRepository storeProductRepository;

    public StoreService(StoreProductRepository storeProductRepository) {
        this.storeProductRepository = storeProductRepository;
    }

    public StoreProduct getStoreProduct(int storeId, int productId) {
        Optional<StoreProduct> byStoreIdAndProductId =
                storeProductRepository.findByStoreIdAndProductId(storeId, productId);
        if (byStoreIdAndProductId.isEmpty()) {
            throw new RuntimeException("존재하지 않습니다.");
        }
        return byStoreIdAndProductId.get();
    }

    public void saveAll(List<StoreProduct> storeProducts) {
        storeProductRepository.saveAll(storeProducts);
    }
}
