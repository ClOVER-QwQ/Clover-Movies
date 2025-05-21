package com.clover.service;

import com.clover.dao.OrderRepository;
import com.clover.dao.UserRepository;
import com.clover.pojo.Order;
import com.clover.pojo.Screening;
import com.clover.pojo.User;
import com.clover.pojo.dto.OrderStatus;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final ScreeningRepository screeningRepository;

    @Transactional
    public Order createOrder(Long userId, Long screeningId, List<String> seats) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        Screening screening = screeningRepository.findById(screeningId)
                .orElseThrow(() -> new EntityNotFoundException("Screening not found"));

        Order order = new Order();
        order.setUser(user);
        order.setScreening(screening);
        order.setSeatNumbers(seats);
        order.setTotalAmount(screening.getPrice().multiply(BigDecimal.valueOf(seats.size())));
        order.setStatus(OrderStatus.PENDING);

        return orderRepository.save(order);
    }

    public List<Order> getUserOrders(Long userId) {
        return orderRepository.findByUser_Id(userId); // 使用正确的查询方法
    }
}