package tech.talci.redditclonespring.services;

import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import tech.talci.redditclonespring.domain.User;
import tech.talci.redditclonespring.domain.VerificationToken;
import tech.talci.redditclonespring.dto.RegisterRequest;
import tech.talci.redditclonespring.repositories.UserRepository;
import tech.talci.redditclonespring.repositories.VerificationTokenRepository;

import java.time.Instant;
import java.util.UUID;

@Service
@AllArgsConstructor
public class AuthService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final VerificationTokenRepository verificationTokenRepository;

    public void signup(RegisterRequest registerRequest){
        User user = new User();
        user.setEmail(registerRequest.getEmail());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        user.setUsername(registerRequest.getUsername());
        user.setCreated(Instant.now());
        user.setEnabled(false);

        userRepository.save(user);

        String token = generateVerificationToken(user);

    }

    private String generateVerificationToken(User user) {
        String token = UUID.randomUUID().toString();

        VerificationToken verificationToken = new VerificationToken();
        verificationToken.setToken(token);
        verificationToken.setUser(user);
        verificationTokenRepository.save(verificationToken);

        return token;
    }

}
