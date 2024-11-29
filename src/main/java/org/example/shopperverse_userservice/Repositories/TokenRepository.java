package org.example.shopperverse_userservice.Repositories;

import org.example.shopperverse_userservice.Models.Token;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TokenRepository extends JpaRepository<Token, Long> {
    Optional<Token> findByValueAndExpiredAndExpiryTimeGreaterThan(String value, Boolean expired, Long expiryTime);
}
