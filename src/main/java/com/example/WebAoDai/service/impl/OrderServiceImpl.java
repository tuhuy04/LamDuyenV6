package com.example.WebAoDai.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.WebAoDai.entity.Order;
import com.example.WebAoDai.repository.OrderRepository;
import com.example.WebAoDai.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderRepository orderRepository;

    @Override
    public void saveOrder(Order order) {
        orderRepository.save(order);
    }

    public Order getOrderById(int orderId) {
        return orderRepository.findById(orderId);
    }

    @Override
    public List<Order> getAllOrderByUser_Id(Long id) {
        return orderRepository.findAllByUserId(id);
    }

    @Override
    public Order findById(int id) {
        return orderRepository.findById(id);
    }

    @Override
    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    @Override
    public List<Order> findTop5RecentOrder() {
        return orderRepository.findTop5RecentOrder();
    }

    @Override
    public List<String> findTop5RecentCustomer() {
        return orderRepository.findTop5RecentCustomer();
    }

    @Override
    public Page<Order> findAll(Pageable pageable) {
        return orderRepository.findAllOrderByBookingDateDesc(pageable);
    }

    @Override
    public void deleteById(int id) {
        orderRepository.deleteById(id);
    }

    @Override
    public List<Order> findAllByPayment_Method(String payment_Method) {
        return orderRepository.findAllByPayment_Method(payment_Method);
    }

    @Override
    public List<Order> findTop5OrderByPaymentMethod(String payment_method) {
        return orderRepository.findTop5OrderByPaymentMethod(payment_method);
    }

    public Page<Order> getOrders(PageRequest pageRequest, String keyword) {
        if (keyword.isEmpty()) {
            return orderRepository.findAll(pageRequest);
        } else {
            return orderRepository.findByFullnameContainingOrEmailContaining(keyword, pageRequest);
        }
    }

    public void updateOrderStatus(int orderId, String status) {
        Order order = orderRepository.findById(orderId);
        order.setStatus(status);
        orderRepository.save(order);
    }

    // Thêm các hàm doanh thu
    @Override
    public List<Object[]> getRevenueByDay() {
        return orderRepository.getRevenueByDay();
    }

    @Override
    public List<Object[]> getRevenueByMonth() {
        return orderRepository.getRevenueByMonth();
    }

    @Override
    public List<Object[]> getRevenueByYear() {
        return orderRepository.getRevenueByYear();
    }
}