package com.texnoera.controller;

import com.texnoera.dto.UserDto;
import com.texnoera.mvc.model.UserLogin;
import com.texnoera.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class LoginController {

    private final UserService userService;

    @GetMapping("/login")
    public String login() {
        return "login_page";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute UserLogin userLogin) {
        UserDto userDto = new UserDto();
        userDto.setFullName(userLogin.getUsername());
        userDto.setPassword(userLogin.getPassword());
        userDto.setAge(userLogin.getAge());
        userService.add(userDto);
        return "redirect:/mvc/users";
    }

}
