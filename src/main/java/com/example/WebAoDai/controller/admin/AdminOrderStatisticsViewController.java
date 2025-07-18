package com.example.WebAoDai.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/order")
public class AdminOrderStatisticsViewController {

    @GetMapping("/statistics")
    public String statisticsPage() {
        // Trả về file templates/admin/order/statistics.html
        return "admin/order/statistics";
    }
}