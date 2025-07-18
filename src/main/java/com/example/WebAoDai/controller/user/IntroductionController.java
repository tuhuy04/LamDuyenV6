package com.example.WebAoDai.controller.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IntroductionController {

    @GetMapping("/introduction")
    public String introduction() {
        return "introduction";
    }

    @GetMapping("/collections")
    public String collections() {
        return "collections";
    }
}