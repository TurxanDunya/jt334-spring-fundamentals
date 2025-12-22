package com.texnoera.controller;

import com.texnoera.dao.entity.User;
import com.texnoera.dto.UserDto;
import com.texnoera.model.UserFilter;
import com.texnoera.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "User controller endpoints")
@Validated
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    @Operation(summary = "Get all user by filter params")
    public List<UserDto> getUsers(@Valid UserFilter filter) {
        return userService.getUsers(filter);
    }

    @GetMapping("/names/{name}")
    public ResponseEntity<User> getUserByName(@PathVariable String name) {
        return ResponseEntity.ok(userService.getByName(name));
    }

    @GetMapping("/surname/{surname}")
    public User getUserBySurname(@PathVariable String surname) {
        return userService.getBySurName(surname);
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> createUser(@Valid @RequestBody UserDto userDto) {
        userService.add(userDto);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{id}")
    public void updateUser(@PathVariable("id") Long userId,
                           @RequestBody UserDto userDto) {
        userService.update(userId, userDto);
    }

    @PutMapping("/{id}")
    public void updateUserPartially(@PathVariable("id") Long userId,
                                    @RequestBody User user) {
        System.out.println("User updated");
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable("id") Long userId) {
        System.out.println("User deleted");
    }
}
