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
    public String addUser(@ModelAttribute("userForm") @Validated User userForm, Model model){

        if (!userForm.getPassword().equals(userForm.getConfirmPassword())){
            model.addAttribute("passwordError", "Password incorrect!");
            return "registration";
        }
        if (!userService.availableEmail(userForm.getEmail())){
            model.addAttribute("emailError", "User with that e-mail already exist!");
            return "registration";
        }
        if (!userService.saveUser(userForm)){
            model.addAttribute("usernameError", "User with that username already exist");
            return "registration";
        }

        return "redirect:/";
    }
    @GetMapping("/")
    public String homePage(){
        return "index";
    }
}
