package com.example.WebAoDai.controller.admin;

import com.example.WebAoDai.entity.Order;
import com.example.WebAoDai.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/order")
public class AdminOrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("")
    public String viewOrders(@RequestParam(defaultValue = "0") int page,
                             @RequestParam(defaultValue = "") String keyword,
                             Model model) {
        Page<Order> orders = orderService.getOrders(PageRequest.of(page, 5), keyword);
        model.addAttribute("orders", orders);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", orders.getTotalPages());
        model.addAttribute("keyword", keyword);
        return "admin/order/list";
    }


    @PostMapping("/updateStatus/{id}")
    public String updateStatus(@PathVariable("id") int id, @RequestParam("status") String status) {
        orderService.updateOrderStatus(id, status);
        return "redirect:/admin/order";
    }

    @GetMapping("/{id}")
    public String orderDetail(@PathVariable("id") int id, Model model) {
        Order order = orderService.getOrderById(id);

        model.addAttribute("order", order);

        return "admin/order/detail";
    }
}