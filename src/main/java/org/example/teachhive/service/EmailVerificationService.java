package org.example.teachhive.service;

import jakarta.mail.MessagingException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.teachhive.entity.EmailVerificationToken;
import org.example.teachhive.entity.User;
import org.example.teachhive.exception.ErrorCodes;
import org.example.teachhive.exception.RestException;
import org.example.teachhive.repository.EmailVerificationTokenRepository;
import org.example.teachhive.repository.UserRepository;
import org.example.teachhive.util.AppConstants;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmailVerificationService {

    private final EmailSenderService emailSenderService;
    private final EmailVerificationTokenRepository tokenRepository;
    private final UserRepository userRepository;

    @Transactional
    public void sendVerificationEmail(UUID userId) throws MessagingException {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RestException("User not found", ErrorCodes.NotFound));

        String token = UUID.randomUUID().toString();

        log.info("This is the token sent to user => {}", token);
        EmailVerificationToken verificationToken = new EmailVerificationToken();
        verificationToken.setToken(token);
        verificationToken.setUser(user);
        verificationToken.setExpiryDate(LocalDateTime.now().plusMinutes(30));

        tokenRepository.save(verificationToken);

        String verifyLink = "http://localhost:8888"
                + AppConstants.BASE_PATH
                + "/auth/verify?token=" + token;

        String htmlBody = getMessagePage(user, token, verifyLink);

        emailSenderService.sendHtmlEmail(
                user.getEmail(),
                "Email Verification",
                htmlBody
        );

        log.warn("Verification email sent to {}", user.getEmail());
    }


    private static String getMessagePage(User user, String token, String verifyLink) {
        String ignoreLink = "http://localhost:8888" + AppConstants.BASE_PATH + "/auth/ignore?token=" + token;

        return "<html><body>"
                + "<h2>Hello " + user.getFullName() + ",\nWelcome to Teach Hive</h2>"
                + "<p>Click the button below to verify your email:</p>"
                + "<a href='" + verifyLink + "' style='display:inline-block; padding:10px 20px; color:white; background-color:green; text-decoration:none; border-radius:5px;'>Verify Email</a> <br>"
                + "<p>If you didn't register, just ignore this email.</p> <br>"
                + "<a href='" + ignoreLink + "' style='display:inline-block; padding:10px 20px; color:white; background-color:red; text-decoration:none; border-radius:5px; margin-left:10px;'>Ignore</a>"
                + "</body></html>";
    }

    public void verifyEmail(String token) {
        EmailVerificationToken verificationToken = tokenRepository.findByToken(token)
                .orElseThrow(() -> new RestException("Invalid verification token", ErrorCodes.INVALID_CREDENTIALS));

        if (verificationToken.getExpiryDate().isBefore(LocalDateTime.now())) {
            tokenRepository.delete(verificationToken);
            throw new RestException("Token expired", ErrorCodes.Expired_Token);
        }

        User user = verificationToken.getUser();
        user.setIsEnabled(true);
        userRepository.save(user);
    }

    public void ignoreVerification(String token) {
        EmailVerificationToken verificationToken = tokenRepository.findByToken(token)
                .orElseThrow(() -> new RestException("Invalid verification token", ErrorCodes.INVALID_CREDENTIALS));
        tokenRepository.delete(verificationToken);
    }
}

