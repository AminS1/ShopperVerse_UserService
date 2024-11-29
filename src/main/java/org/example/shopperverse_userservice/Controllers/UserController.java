package org.example.shopperverse_userservice.Controllers;

import org.example.shopperverse_userservice.DTOs.LoginRequestDto;
import org.example.shopperverse_userservice.DTOs.SignUpRequestDto;
import org.example.shopperverse_userservice.DTOs.UserResponseDto;
import org.example.shopperverse_userservice.Models.Token;
import org.example.shopperverse_userservice.Models.User;
import org.example.shopperverse_userservice.Services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/signup")
    public ResponseEntity<UserResponseDto> signUp(@RequestBody SignUpRequestDto signUpRequestDto) {
        User user = userService.signUp(
                signUpRequestDto.getName(),
                signUpRequestDto.getEmail(),
                signUpRequestDto.getPassword()
        );
        return ResponseEntity.ok(UserResponseDto.from(user));
    }

    @PostMapping("/login")
    public ResponseEntity<Token> login(@RequestBody LoginRequestDto loginRequestDto) {
        Token token = userService.login(loginRequestDto.getEmail(), loginRequestDto.getPassword());
        return ResponseEntity.ok(token);
    }
}
