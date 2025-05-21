package com.clover.controller;

import com.clover.pojo.Order;
import com.clover.pojo.dto.OrderRequest;
import com.clover.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<Order> createOrder(
            @RequestBody OrderRequest request) {
        Order order = orderService.createOrder(
                request.getUserId(),
                request.getScreeningId(),
                request.getSeats()
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(order);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Order>> getUserOrders(
            @PathVariable Long userId) {
        return ResponseEntity.ok(orderService.getUserOrders(userId));
    }
}