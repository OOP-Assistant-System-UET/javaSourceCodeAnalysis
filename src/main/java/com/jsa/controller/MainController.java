package com.jsa.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {
    @RequestMapping(value = { "/login", "/" })
    public String login(@RequestParam(value = "error", required = false) final String error,
                        final Model model) {
        if (error != null) {
            model.addAttribute("message", "Login Failed!");
        }
        return "login";
    }
    @RequestMapping("/home")
    public String home() {
        return "home";
    }
}
