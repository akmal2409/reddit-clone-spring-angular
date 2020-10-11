package tech.talci.redditclonespring.controllers;

import com.sun.mail.iap.Response;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.talci.redditclonespring.domain.RefreshToken;
import tech.talci.redditclonespring.dto.AuthenticationResponse;
import tech.talci.redditclonespring.dto.LoginRequest;
import tech.talci.redditclonespring.dto.RefreshTokenRequest;
import tech.talci.redditclonespring.dto.RegisterRequest;
import tech.talci.redditclonespring.repositories.RefreshTokenRepository;
import tech.talci.redditclonespring.services.AuthService;
import tech.talci.redditclonespring.services.RefreshTokenService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final RefreshTokenService refreshTokenService;

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody RegisterRequest registerRequest){
        authService.signup(registerRequest);

        return new ResponseEntity<>("User registration was successful!", HttpStatus.OK);
    }

    @GetMapping("/accountVerification/{token}")
    public ResponseEntity<String> verifyAccount(@PathVariable String token){
        authService.verifyAccount(token);

        return new ResponseEntity<>("Account was activated", HttpStatus.OK);
    }


    @PostMapping("/login")
    public AuthenticationResponse login(@RequestBody LoginRequest loginRequest) {
        return authService.login(loginRequest);
    }

    @PostMapping("/refresh/token")
    @ResponseStatus(HttpStatus.OK)
    public AuthenticationResponse refreshToken(@Valid @RequestBody RefreshTokenRequest refreshTokenRequest) {
        return authService.refreshToken(refreshTokenRequest);
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(@Valid @RequestBody RefreshTokenRequest refreshTokenRequest) {
        refreshTokenService.deleteRefreshToken(refreshTokenRequest.getRefreshToken());

        return ResponseEntity.status(HttpStatus.OK).body("You have successfully logged out!");
    }
}
