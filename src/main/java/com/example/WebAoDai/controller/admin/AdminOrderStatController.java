package com.example.WebAoDai.controller.admin;

import com.example.WebAoDai.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/admin/api/order")
public class AdminOrderStatController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/revenue/day")
    public List<Map<String, Object>> getRevenueByDay() {
        List<Object[]> result = orderService.getRevenueByDay();
        List<Map<String, Object>> list = new ArrayList<>();
        for (Object[] obj : result) {
            Map<String, Object> map = new HashMap<>();
            map.put("time", obj[0]);
            map.put("revenue", obj[1]);
            list.add(map);
        }
        return list;
    }

    @GetMapping("/revenue/month")
    public List<Map<String, Object>> getRevenueByMonth() {
        List<Object[]> result = orderService.getRevenueByMonth();
        List<Map<String, Object>> list = new ArrayList<>();
        for (Object[] obj : result) {
            Map<String, Object> map = new HashMap<>();
            map.put("year", obj[0]);
            map.put("month", obj[1]);
            map.put("revenue", obj[2]);
            list.add(map);
        }
        return list;
    }

    @GetMapping("/revenue/year")
    public List<Map<String, Object>> getRevenueByYear() {
        List<Object[]> result = orderService.getRevenueByYear();
        List<Map<String, Object>> list = new ArrayList<>();
        for (Object[] obj : result) {
            Map<String, Object> map = new HashMap<>();
            map.put("year", obj[0]);
            map.put("revenue", obj[1]);
            list.add(map);
        }
        return list;
    }
}