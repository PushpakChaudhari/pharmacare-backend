package com.jspm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.jspm.model.PasswordResetToken;
import java.util.Optional;
import com.jspm.model.User;
public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetToken, Long> {
    Optional<PasswordResetToken> findByToken(String token);
    void deleteByUser(User user);
    Optional<PasswordResetToken> findByUser(User user);


}