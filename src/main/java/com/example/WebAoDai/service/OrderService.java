package com.example.WebAoDai.service;

import com.example.WebAoDai.entity.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface OrderService {

    public void saveOrder(Order order);

    List<Order> getAllOrderByUser_Id(Long id);

    Order findById(int id);

    List<Order> findAll();

    void updateOrderStatus(int orderId, String status);

    Page<Order> getOrders(PageRequest pageRequest, String keyword);

    List<Order> findTop5RecentOrder();

    List<String> findTop5RecentCustomer();

    Page<Order> findAll(Pageable pageable);

    void deleteById(int id);

    List<Order> findAllByPayment_Method(String payment_Method);

    List<Order> findTop5OrderByPaymentMethod(String payment_method);

    Order getOrderById(int orderId);

    // Thêm các hàm doanh thu
    List<Object[]> getRevenueByDay();
    List<Object[]> getRevenueByMonth();
    List<Object[]> getRevenueByYear();
}