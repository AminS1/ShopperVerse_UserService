package org.example.shopperverse_userservice.Controllers;

import org.example.shopperverse_userservice.DTOs.UserResponseDto;
import org.example.shopperverse_userservice.Services.TokenService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/token")
public class TokenController {

    TokenService tokenService;

    public TokenController(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @GetMapping("/validate/{token}")
    public UserResponseDto validate(@PathVariable String token) {
        return UserResponseDto.from(tokenService.validateToken(token));
    }
}
