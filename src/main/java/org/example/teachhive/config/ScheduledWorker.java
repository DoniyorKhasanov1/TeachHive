package org.example.teachhive.config;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.teachhive.repository.EmailVerificationTokenRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component("asyncWorker")
@RequiredArgsConstructor
@Slf4j
public class ScheduledWorker {
    private final EmailVerificationTokenRepository tokenRepository;

    @Scheduled(fixedRate = 300_000)
    @Transactional
    public void cleanupExpiredTokens() {
        int deletedCount = tokenRepository.deleteByExpiryDateBefore(LocalDateTime.now());
        log.info("Cleaned up {} expired email verification tokens", deletedCount);
    }
}
