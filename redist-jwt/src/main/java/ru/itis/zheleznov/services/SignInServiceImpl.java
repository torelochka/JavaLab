package ru.itis.zheleznov.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.itis.zheleznov.dto.JwtResponse;
import ru.itis.zheleznov.dto.LoginRequest;
import ru.itis.zheleznov.models.RefreshToken;
import ru.itis.zheleznov.models.User;
import ru.itis.zheleznov.repositories.UserRepository;
import ru.itis.zheleznov.security.details.RefreshTokenService;
import ru.itis.zheleznov.security.jwt.JwtUtils;

import java.util.Optional;

@Service
public class SignInServiceImpl implements SignInService {

    private final UserRepository userRepository;

    private final JwtUtils jwtUtils;

    private final RefreshTokenService refreshTokenService;

    private final PasswordEncoder passwordEncoder;

    private final RedisUserServiceImpl redisUserService;

    @Autowired
    public SignInServiceImpl(UserRepository userRepository, JwtUtils jwtUtils, RefreshTokenService refreshTokenService, PasswordEncoder passwordEncoder, RedisUserServiceImpl redisUserService) {
        this.userRepository = userRepository;
        this.jwtUtils = jwtUtils;
        this.refreshTokenService = refreshTokenService;
        this.passwordEncoder = passwordEncoder;
        this.redisUserService = redisUserService;
    }

    @Override
    public Optional<JwtResponse> signIn(LoginRequest loginRequest) {
        Optional<User> user = userRepository.findByEmail(loginRequest.getEmail());

        if (validUser(user, loginRequest)) {
            RefreshToken refreshToken = refreshTokenService.createRefreshToken(user.get().getId());

            String token = jwtUtils.generateJwtToken(user.get());

            redisUserService.addTokenToUser(user.get(), token);

            return Optional.of(JwtResponse.builder()
                    .refreshToken(refreshToken.getToken())
                    .token(token)
                    .build());
        }
        return Optional.empty();
    }

    private boolean validUser(Optional<User> user, LoginRequest loginRequest) {
        return user.isPresent() && passwordEncoder.matches(loginRequest.getPassword(), user.get().getPassword());
    }
}
