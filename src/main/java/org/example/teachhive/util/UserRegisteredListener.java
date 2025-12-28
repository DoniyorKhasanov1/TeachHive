package org.example.teachhive.util;

import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.example.teachhive.service.EmailVerificationService;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
@RequiredArgsConstructor
public class UserRegisteredListener {

    private final EmailVerificationService emailVerificationService;

    @Async
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void onUserRegistered(UserRegisteredEvent event) throws MessagingException {
        emailVerificationService.sendVerificationEmail(event.userId());
    }
}

