package com.example.WebAoDai.controller;


import com.example.WebAoDai.entity.Product;
import com.example.WebAoDai.entity.User;
import com.example.WebAoDai.repository.ProductRepository;
import com.example.WebAoDai.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/")
    private String indexHome(Model model, HttpSession session) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Collection<? extends GrantedAuthority> authorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities();

        if (authorities.stream().anyMatch(authority -> "ROLE_ADMIN".equals(authority.getAuthority()))) {
            User user = userRepository.findByEmail(username).get();
            session.setAttribute("acc", user);
            model.addAttribute("email", username);
            return "redirect:/admin/dashboard";
        } else if (authorities.stream().anyMatch(authority -> "ROLE_USER".equals(authority.getAuthority()))) {
            User user = userRepository.findByEmail(username).get();
            session.setAttribute("acc", user);
            model.addAttribute("email", username);
            List<Product> products = productRepository.findAll().subList(0, 3);
            model.addAttribute("products", products);
            return "index";
        } else {
            List<Product> products = productRepository.findAll().subList(0, 4);
            System.out.println(products);
            model.addAttribute("products", products);
            return "index";
        }
    }

    @GetMapping("/home")
    public String homePage(Model model, HttpSession session) {
        return indexHome(model, session);
    }

    @GetMapping("/tryOn")
    public String tryOn() {
        return "tryOn";
    }

    @GetMapping("/display360")
    public String display360Page() {
        return "display360";
    }

    @GetMapping("/index")
    public String index(Model model, HttpSession session) {
        return indexHome(model, session);
    }

    @GetMapping("/product/list")
    public String list(@RequestParam(value = "search", required = false) String search,
                       @RequestParam(value = "categoryId", required = false) Integer categoryId,
                       @RequestParam(value = "sortby", required = false) String sortBy,
                       Model model) {

        List<Product> products = productRepository.findByProductNameOrCategoryName(search);

        if (sortBy != null) {
            switch (sortBy) {
                case "price-ascending":
                    products.sort(Comparator.comparing(Product::getPrice));
                    break;
                case "price-descending":
                    products.sort((p1, p2) -> Double.compare(p2.getPrice(), p1.getPrice()));
                    break;
                case "title-ascending":
                    products.sort(Comparator.comparing(Product::getProduct_Name));
                    break;
                case "title-descending":
                    products.sort((p1, p2) -> p2.getProduct_Name().compareTo(p1.getProduct_Name()));
                    break;
                case "created-ascending":
                    products.sort(Comparator.comparing(Product::getCreated_At));
                    break;
                case "created-descending":
                    products.sort((p1, p2) -> p2.getCreated_At().compareTo(p1.getCreated_At()));
                    break;
                case "quantity-descending":
                    products.sort((p1, p2) -> Long.compare(p2.getQuantity(), p1.getQuantity()));
                    break;
                default:
                    break;
            }
        }

        model.addAttribute("products", products);
        model.addAttribute("search", search);
        model.addAttribute("categoryId", categoryId);

        return "list";
    }



    @GetMapping("/product/{id}")
    public String showProductDetail(@PathVariable("id") Integer id, Model model) {
        Product product = productRepository.findById(id).orElse(null);
        if (product != null) {
            model.addAttribute("product", product);
        }
        return "detail";
    }
}