package com.texnoera.controller;

import com.texnoera.dto.UserDto;
import com.texnoera.model.UserFilter;
import com.texnoera.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/mvc")
@RequiredArgsConstructor
public class UserMvcController {

    private final UserService userService;

    @GetMapping("/users")
    public String listUsers(Model model) {
        List<UserDto> users = userService.getUsers(new UserFilter());
        model.addAttribute("users", users);
        return "user_list_page";
    }

}
