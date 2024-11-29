package org.example.shopperverse_userservice.Services;

import org.example.shopperverse_userservice.Models.Token;
import org.example.shopperverse_userservice.Models.User;
import org.example.shopperverse_userservice.Repositories.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class UserService {
    UserRepository userRepository;
    BCryptPasswordEncoder bCryptPasswordEncoder;
    TokenService tokenService;

    public UserService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder, TokenService tokenService) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.tokenService = tokenService;
    }

    public User signUp(String name, String email, String password) {
        String hashedPassword = bCryptPasswordEncoder.encode(password);
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setHashedPassword(hashedPassword);
        user.setEmailVerified(false);
        user.setRoles(new ArrayList<>());
        return userRepository.save(user);
    }

    public Token login(String email, String password) {
        Optional<User> userOptional = userRepository.findByEmail(email);
        if (userOptional.isEmpty()) {
            throw new RuntimeException("User not found");
        }
        User user = userOptional.get();
        String hashedPassword = user.getHashedPassword();
        if (!bCryptPasswordEncoder.matches(password, hashedPassword)) {
            throw new RuntimeException("Wrong password");
        }
        return tokenService.generateToken(user);
    }


}
