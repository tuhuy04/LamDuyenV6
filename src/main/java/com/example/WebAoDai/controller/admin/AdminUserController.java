package com.example.WebAoDai.controller.admin;

import com.example.WebAoDai.entity.User;

import com.example.WebAoDai.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/admin/user")
public class AdminUserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("")
    public String userListPage(Model model,
                               @RequestParam(value = "email", required = false) String emailParam,
                               @RequestParam(value = "page", defaultValue = "0") int page,
                               @RequestParam(value = "size", defaultValue = "5") int size) {

        List<User> allUsers = userRepository.findAll();

        if (emailParam != null && !emailParam.isEmpty()) {
            allUsers = allUsers.stream()
                    .filter(u -> u.getEmail() != null && u.getEmail().toLowerCase().contains(emailParam.toLowerCase()))
                    .collect(Collectors.toList());
        }


        int totalItems = allUsers.size();
        int totalPages = (int) Math.ceil((double) totalItems / size);

        int start = page * size;
        int end = Math.min(start + size, totalItems);

        List<User> users = allUsers.subList(start, end);

        model.addAttribute("users", users);
        model.addAttribute("email", emailParam);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalPages);

        return "admin/user/list";
    }


    @GetMapping("/add")
    public String addUserForm(Model model) {
        model.addAttribute("user", new User());
        return "admin/user/insert";
    }

    @PostMapping("/save")
    public String saveUser(@ModelAttribute("user") User user,
                           @RequestParam("role") String role) {
        userRepository.save(user);
        return "redirect:/admin/user";
    }

    @PostMapping("/update-status/{id}")
    public String updateUserStatus(@PathVariable Long id,
                                   @RequestParam("status") Integer status) {
        Optional<User> optional = userRepository.findById(id);
        if (optional.isPresent()) {
            User user = optional.get();
            user.setStatus(status);
            userRepository.save(user);
        }
        return "redirect:/admin/user";
    }
}
