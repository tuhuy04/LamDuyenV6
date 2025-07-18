package com.example.WebAoDai.controller.auth;

import com.example.WebAoDai.service.EmailSenderService;
import com.example.WebAoDai.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthController {
    private final UserService userService;
    private final EmailSenderService emailSenderService;
    private final PasswordEncoder passwordEncoder;

    public AuthController(UserService userService, EmailSenderService emailSenderService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.emailSenderService = emailSenderService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/login")
    public String showLogin() {
        return "login";
    }

    @GetMapping("/logout")
    public String logoutPage() {
        SecurityContextHolder.getContext().setAuthentication(null);
        return "redirect:/?logout";
    }

    @RequestMapping(value = "register")
    public String addUser(Model model) {
        return "register";
    }

    @RequestMapping(value = "save", method = RequestMethod.POST)
    public String save(@RequestParam String email, @RequestParam String name, @RequestParam String phone, @RequestParam String password, @RequestParam String address, Model model, HttpSession session) {
        if (userService.findByEmail(email).isPresent()) {
            model.addAttribute("mess", "Email đã tồn tại. Hãy nhập Email mới!");
            return "register";
        }
        session.setAttribute("otp-register", otpCode());
        session.setMaxInactiveInterval(360);
        String subject = "Đây là OTP của bạn";
        String mess = "Xin chào @" + " \n" + email + "Đây là OTP của bạn: " + session.getAttribute("otp-register") + " Hãy điền vào form!" + "\n Cảm ơn!";
        this.emailSenderService.sendEmail(email, subject, mess);
        session.setAttribute("email", email);
        session.setAttribute("name", name);
        session.setAttribute("phone", phone);
        session.setAttribute("address", address);
        session.setAttribute("password", password);
        return "redirect:/otp-check";
    }

    @RequestMapping(value = "re-send")
    public String resend(HttpSession session) {
        session.removeAttribute("otp-register");
        session.setAttribute("otp-register", otpCode());
        session.setMaxInactiveInterval(360);
        String subject = "Đây là OTP của bạn!";
        String mess = "Xin chào @" + " \n" + session.getAttribute("email") + "Đây là OTP của bạn: " + session.getAttribute("otp-register") + " Hãy điền vào form!" + "\n Cảm ơn!";
        this.emailSenderService.sendEmail((String) session.getAttribute("email"), subject, mess);
        return "redirect:/otp-check";
    }

    public String otpCode() {
        int code = (int) Math.floor(((Math.random() * 899999) + 100000));
        return String.valueOf(code);
    }


}
