package com.texnoera.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user-names")
public class UserNameController {

    @GetMapping("/{name}")
    public void userName(@PathVariable String name) {

    }

}
