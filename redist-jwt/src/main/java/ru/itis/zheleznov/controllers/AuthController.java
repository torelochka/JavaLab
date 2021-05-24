package ru.itis.zheleznov.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.zheleznov.dto.*;
import ru.itis.zheleznov.exceptions.TokenRefreshException;
import ru.itis.zheleznov.models.RefreshToken;
import ru.itis.zheleznov.security.details.RefreshTokenService;
import ru.itis.zheleznov.security.jwt.JwtUtils;
import ru.itis.zheleznov.services.SignInService;
import ru.itis.zheleznov.services.SignUpService;
import ru.itis.zheleznov.services.UserService;

import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

  @Autowired
  PasswordEncoder encoder;

  @Autowired
  RefreshTokenService refreshTokenService;

  @Autowired
  private SignUpService signUpService;

  @Autowired
  private SignInService signInService;

  @PostMapping("/signIn")
  public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {
    Optional<JwtResponse> jwtResponse = signInService.signIn(loginRequest);

    if (jwtResponse.isPresent()) {
      return ResponseEntity.ok(jwtResponse.get());
    }

    return ResponseEntity.status(403).build();
  }

  @PostMapping("/signup")
  public ResponseEntity<?> registerUser(@RequestBody SignupRequest signUpRequest) {

    SignUpForm signUpForm = SignUpForm.builder()
            .email(signUpRequest.getEmail())
            .password(encoder.encode(signUpRequest.getPassword()))
            .passwordAgain(signUpRequest.getPassword())
            .build();

    if (signUpService.signUp(signUpForm)) {
      return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }
    return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already in use!"));
  }

  @PostMapping("/refreshtoken")
  public ResponseEntity<?> refreshtoken(@RequestBody TokenRefreshRequest request) {
    String requestRefreshToken = request.getRefreshToken();

    return ResponseEntity.ok(refreshTokenService.findByToken(requestRefreshToken)
            .orElseThrow(() -> new TokenRefreshException(requestRefreshToken,
                    "Refresh token is not in database!")));
  }
}
