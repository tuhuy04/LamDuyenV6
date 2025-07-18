package com.example.WebAoDai.controller.auth;

import com.example.WebAoDai.config.Constants;
import com.example.WebAoDai.entity.*;
import com.example.WebAoDai.service.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@Controller

public class OtpController {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final EmailSenderService emailSenderService;

    public OtpController(UserService userService, PasswordEncoder passwordEncoder, EmailSenderService emailSenderService) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.emailSenderService = emailSenderService;
    }

    @RequestMapping(value = "otp-check", method = RequestMethod.GET)
    public String indexOtp() {
        return "otpConfirm";
    }

    @RequestMapping(value = "confirm-otp", method = RequestMethod.POST)
    public String checkOtp(HttpSession session, @RequestParam("otp") String otp, Model model) {
        String otpRegister = (String) session.getAttribute("otp-register");
        if (otp.equals(otpRegister)) {
            User userEntity = new User();
            userEntity.setEmail((String) session.getAttribute("email"));
            userEntity.setPassword(passwordEncoder.encode((String) session.getAttribute("password")));
            String phone = (String) session.getAttribute("phone");
            String fullName = (String) session.getAttribute("name");
            String address = (String) session.getAttribute("address");
            userEntity.setRole("USER");
            userEntity.setStatus(1);
            userEntity.setPhone_Number(phone);
            userEntity.setAddress(address);
            userEntity.setFullName(fullName);
            userService.saveUser(userEntity);
            return "redirect:/";
        }
        model.addAttribute("mess", "OTP is not correct! Please check your email.");
        return "otpConfirm";
    }

}