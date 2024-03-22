package com.example.coffee.service;

import com.example.coffee.domain.CreateOrder;
import com.example.coffee.domain.StoreProduct;
import com.example.coffee.repository.OrderRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashMap;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {

    @Mock
    OrderRepository orderRepository;

    @Mock
    StoreService storeService;

    @InjectMocks
    OrderService orderService;

    @Test
    @DisplayName("구매 수량이 재고 수량보다 적을 때, 정상 주문이 가능하다.")
    public void stockQuantityTest_success() {
        // given
        int buyQuantity = 5;
        int stockQuantity = 50;
        HashMap<Integer, Integer> map = new HashMap<>();

        map.put(1, buyQuantity);
        CreateOrder createOrder = CreateOrder.builder()
                .storeId(1)
                .customerId(1)
                .quantityProduct(map)
                .build();

        StoreProduct stock = StoreProduct.builder()
                .stockQuantity(stockQuantity)
                .build();
        when(storeService.getStoreProduct(1, 1)).thenReturn(stock); // 모킹 처리

        // when
        orderService.newOrder(createOrder);

        // then
        assertThat(stock.getStockQuantity()).isEqualTo(stockQuantity - buyQuantity);
    }

    @Test
    @DisplayName("구매 수량이 재고 수량보다 많을 때, 정상 주문은 불가능하다.")
    public void stockQuantityTest_failure() {
        // given
        int buyQuantity = 100;
        int stockQuantity = 50;
        HashMap<Integer, Integer> map = new HashMap<>();

        map.put(1, buyQuantity);
        CreateOrder createOrder = CreateOrder.builder()
                .storeId(1)
                .customerId(1)
                .quantityProduct(map)
                .build();

        StoreProduct stock = StoreProduct.builder()
                .stockQuantity(stockQuantity)
                .build();
        when(storeService.getStoreProduct(1, 1)).thenReturn(stock); // 모킹 처리

        // when & then
        RuntimeException runtimeException = assertThrows(RuntimeException.class, () -> {
            orderService.newOrder(createOrder);
        });

        // then
        assertThat(runtimeException.getMessage()).isEqualTo("재고가 없습니다.");
    }
}
