package com.example.WebAoDai.controller.admin;

import com.example.WebAoDai.repository.AnalyticsEventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.example.WebAoDai.entity.AnalyticsEvent;
import com.example.WebAoDai.repository.AnalyticsEventRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;


@Controller
public class AdminAnalyticsController {

    @Autowired
    private AnalyticsEventRepository analyticsEventRepository;

    @GetMapping("/admin/analytics")
    public String viewAnalytics(Model model) {
        List<AnalyticsEvent> events = analyticsEventRepository.findAll();
        model.addAttribute("events", events);

        // Đếm số lượt theo loại sự kiện
        long totalVisits = events.stream().filter(e -> "truy cập trang web".equalsIgnoreCase(e.getEventType())).count();
        long totalViews = events.stream().filter(e -> "xem sản phẩm".equalsIgnoreCase(e.getEventType())).count();
        long totalAddToCart = events.stream().filter(e -> "thêm vào giỏ hàng".equalsIgnoreCase(e.getEventType())).count();
        long totalTryOn = events.stream().filter(e -> "thử đồ ảo".equalsIgnoreCase(e.getEventType())).count();


        model.addAttribute("totalVisits", totalVisits);
        model.addAttribute("totalViews", totalViews);
        model.addAttribute("totalAddToCart", totalAddToCart);
        model.addAttribute("totalTryOn", totalTryOn);

        // Biểu đồ line: sự kiện theo ngày
        Map<String, Long> eventsByDay = events.stream()
                .collect(Collectors.groupingBy(
                        e -> new SimpleDateFormat("yyyy-MM-dd").format(e.getEventTime()), // Đổi từ dd-MM sang yyyy-MM-dd
                        TreeMap::new,
                        Collectors.counting()
                ));

        model.addAttribute("eventsByDayData", convertToChartJsData(eventsByDay));

        // Biểu đồ bar: loại sự kiện
        Map<String, Long> eventTypeMap = events.stream()
                .collect(Collectors.groupingBy(AnalyticsEvent::getEventType, Collectors.counting()));
        model.addAttribute("eventTypeData", convertToChartJsData(eventTypeMap));

        // Biểu đồ doughnut: nguồn truy cập
        Map<String, Long> sourceMap = events.stream()
                .filter(e -> e.getSource() != null)
                .collect(Collectors.groupingBy(AnalyticsEvent::getSource, Collectors.counting()));
        model.addAttribute("sourceData", convertToChartJsData(sourceMap));

        // Biểu đồ sản phẩm được tương tác nhiều
        Map<String, Long> productMap = events.stream()
                .filter(e -> e.getProductName() != null)
                .collect(Collectors.groupingBy(e ->  e.getProductName(), Collectors.counting()));
        model.addAttribute("productStatsData", convertToChartJsData(productMap));


        // Biểu đồ miền: Truy cập trang web theo ngày
        Map<String, Long> visitByDay = events.stream()
                .filter(e -> "truy cập trang web".equalsIgnoreCase(e.getEventType()))
                .collect(Collectors.groupingBy(
                        e -> new SimpleDateFormat("yyyy-MM-dd").format(e.getEventTime()),
                        TreeMap::new,
                        Collectors.counting()
                ));

        model.addAttribute("visitByDayAreaChart", convertToChartJsAreaData(visitByDay));


        return "admin/analytics";
    }

    // Helper chuyển map -> JSON cho Chart.js
    private Map<String, Object> convertToChartJsData(Map<String, Long> map) {
        Map<String, Object> result = new HashMap<>();
        result.put("labels", map.keySet());
        Map<String, Object> dataset = new HashMap<>();
        dataset.put("label", "Số lượng");
        dataset.put("data", map.values());
        dataset.put("backgroundColor", List.of("#4e73df", "#1cc88a", "#36b9cc", "#f6c23e", "#e74a3b"));
        result.put("datasets", List.of(dataset));
        return result;
    }

    private Map<String, Object> convertToChartJsAreaData(Map<String, Long> map) {
        Map<String, Object> result = new HashMap<>();
        result.put("labels", map.keySet());
        Map<String, Object> dataset = new HashMap<>();
        dataset.put("label", "Truy cập trang web");
        dataset.put("data", map.values());
        dataset.put("fill", true);
        dataset.put("tension", 0.4); // Đường cong mượt
        dataset.put("backgroundColor", "rgba(78, 115, 223, 0.3)");
        dataset.put("borderColor", "rgba(78, 115, 223, 1)");
        result.put("datasets", List.of(dataset));
        return result;
    }



}
