package com.sm.console.controller;

import com.sm.console.dto.UserDto;
import com.sm.console.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@AllArgsConstructor
public class UserController {

    private UserService userService;

    @GetMapping("/")
    public String index() {
        return "/home/index";
    }

    @GetMapping("/signUp")
    public String signUp(Model model) {
        model.addAttribute("userDto", new UserDto());

        return "/user/signUp";
    }

    @PostMapping("/signUp")
    public String signUp(UserDto userDto) {
        userService.createUser(userDto);

        return "redirect:/login";
    }

    @GetMapping("/login")
    public String login() {
        return "/user/login";
    }
}
