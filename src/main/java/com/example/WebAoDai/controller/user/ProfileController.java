package com.example.WebAoDai.controller.user;

import com.example.WebAoDai.entity.User;
import com.example.WebAoDai.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/client/profile")
public class ProfileController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @PostMapping("/update")
    public String updateProfile(@ModelAttribute("user") User updatedUser,
                                 HttpSession session,
                                 @RequestParam("password") String newPassword) {
        User sessionUser = (User) session.getAttribute("acc");
        if (sessionUser == null) return "redirect:/login";

      
        User user = userRepository.findById(sessionUser.getId()).orElse(null);
        if (user == null) return "redirect:/login";

        user.setFullName(updatedUser.getFullName());
        user.setAddress(updatedUser.getAddress());
        user.setPhone_Number(updatedUser.getPhone_Number());

        if (newPassword != null && !newPassword.trim().isEmpty()) {
            user.setPassword(bCryptPasswordEncoder.encode(newPassword));
        }

        userRepository.save(user);

        session.setAttribute("acc", user); 
        return "redirect:/client/profile";
    }

    @GetMapping
    public String viewProfile(Model model, HttpSession session) {
        User user = (User) session.getAttribute("acc");
        if (user == null) return "redirect:/login";
        model.addAttribute("user", user);
        return "profile";
    }
}
