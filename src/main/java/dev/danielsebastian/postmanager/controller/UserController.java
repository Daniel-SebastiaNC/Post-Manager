package dev.danielsebastian.postmanager.controller;

import dev.danielsebastian.postmanager.dto.user.UserDto;
import dev.danielsebastian.postmanager.dto.user.UserRegisterRequest;
import dev.danielsebastian.postmanager.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<UserDto> register(@RequestBody UserRegisterRequest userRegisterRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(
            userService.registerUser(userRegisterRequest)
        );
    }
}
