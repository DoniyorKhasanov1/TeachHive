package org.example.teachhive.repository;

import jakarta.transaction.Transactional;
import org.example.teachhive.entity.EmailVerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface EmailVerificationTokenRepository extends JpaRepository<EmailVerificationToken, Long> {

    Optional<EmailVerificationToken> findByToken(String token);

    @Modifying
    @Transactional
    @Query("DELETE FROM VerifyToken vt WHERE vt.expiryDate < :localTime")
    int deleteByExpiryDateBefore(@Param("localTime") LocalDateTime localTime);

}
