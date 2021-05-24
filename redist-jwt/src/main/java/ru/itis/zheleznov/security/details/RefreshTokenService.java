package ru.itis.zheleznov.security.details;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.itis.zheleznov.dto.TokenRefreshResponse;
import ru.itis.zheleznov.exceptions.TokenRefreshException;
import ru.itis.zheleznov.models.RefreshToken;
import ru.itis.zheleznov.models.User;
import ru.itis.zheleznov.repositories.RefreshTokenRepository;
import ru.itis.zheleznov.repositories.UserRepository;
import ru.itis.zheleznov.security.jwt.JwtUtils;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Service
public class RefreshTokenService {

  @Value("${jwt.jwtRefreshExpirationMs}")
  private Long refreshTokenDurationMs;

  @Autowired
  private RefreshTokenRepository refreshTokenRepository;

  @Autowired
  private JwtUtils jwtUtils;

  @Autowired
  private UserRepository userRepository;

  public Optional<TokenRefreshResponse> findByToken(String token) {
    Optional<RefreshToken> refreshToken = refreshTokenRepository.findByToken(token);
    if (refreshToken.isPresent() && verifyExpiration(refreshToken.get())) {
      User user = refreshToken.get().getUser();

      String newToken = jwtUtils.generateJwtToken(user);
      return Optional.of(TokenRefreshResponse.builder()
              .token(newToken)
              .refreshToken(token)
              .build());
    }

    return Optional.empty();
  }

  public RefreshToken createRefreshToken(Long userId) {
    RefreshToken refreshToken = new RefreshToken();

    refreshToken.setUser(userRepository.findById(userId).get());
    refreshToken.setExpiryDate(Instant.now().plusMillis(refreshTokenDurationMs));
    refreshToken.setToken(UUID.randomUUID().toString());

    refreshToken = refreshTokenRepository.save(refreshToken);
    return refreshToken;
  }

  public boolean verifyExpiration(RefreshToken token) {
    if (token.getExpiryDate().compareTo(Instant.now()) < 0) {
      refreshTokenRepository.delete(token);
      throw new TokenRefreshException(token.getToken(), "Refresh token was expired. Please make a new signin request");
    }

    return true;
  }

  @Transactional
  public int deleteByUserId(Long userId) {
    return refreshTokenRepository.deleteByUser(userRepository.findById(userId).get());
  }
}
