package com.taskManager.controller;

import com.taskManager.model.entity.User;
import com.taskManager.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class RegistrationController {
    private final UserService userService;

    @GetMapping("/registration")
    public String registerUser(Model model){
        model.addAttribute("userForm", new User());
        return "registration";
    }
    @PostMapping("/registration")
    public String addUser(@ModelAttribute("userForm") @Validated User user, Model model){

        if (!user.getPassword().equals(user.getConfirmPassword())){
            model.addAttribute("passwordError", "Password incorrect!");
            return "registration";
        }
        if (!userService.saveUser(user)){
            model.addAttribute("usernameError", "User with that username already exist");
            return "registration";
        }

        return "redirect:/";
    }
}
