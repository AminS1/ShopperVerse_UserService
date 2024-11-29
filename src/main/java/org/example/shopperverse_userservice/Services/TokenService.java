package org.example.shopperverse_userservice.Services;

import org.apache.commons.lang3.RandomStringUtils;
import org.example.shopperverse_userservice.Models.Token;
import org.example.shopperverse_userservice.Models.User;
import org.example.shopperverse_userservice.Repositories.TokenRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TokenService {
    TokenRepository tokenRepository;

    public TokenService(TokenRepository tokenRepository) {
        this.tokenRepository = tokenRepository;
    }

    public Token save(Token token) {
        return tokenRepository.save(token);
    }

    public Token generateToken(User user) {
        Token token = new Token();
        token.setUser(user);
        token.setExpiryTime(System.currentTimeMillis() + 36_00_000);
        token.setValue(RandomStringUtils.randomAlphanumeric(10));
        return save(token);
    }

    public User validateToken(String token) {
        Optional<Token> tokenOptional = tokenRepository.findByValueAndExpiredAndExpiryTimeGreaterThan(token, false, System.currentTimeMillis());
        if (tokenOptional.isPresent()) {
            return tokenOptional.get().getUser();
        }
        return null;
    }
}
