package dev.danielsebastian.postmanager.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import dev.danielsebastian.postmanager.dto.user.JWTUserData;
import dev.danielsebastian.postmanager.model.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Optional;

@Component
public class TokenService {
    private final Algorithm algorithm;

    public TokenService(@Value("${post-manager.security.secret}") String secret) {
        this.algorithm = Algorithm.HMAC256(secret);
    }

    public String generateToken(User user) {
        return JWT.create()
                .withSubject(user.getUsername())
                .withClaim("id", user.getId())
                .withExpiresAt(Instant.now().plusSeconds(86400))
                .withIssuedAt(Instant.now())
                .sign(this.algorithm);
    }

    public Optional<JWTUserData> verifyToken(String token) {
        try {
            DecodedJWT jwt = JWT
                    .require(this.algorithm)
                    .build()
                    .verify(token);

            return Optional.of(
                    new JWTUserData(
                            jwt.getClaim("id").asLong(),
                            jwt.getSubject()
                    )
            );

        } catch (JWTVerificationException e) {
            return Optional.empty();
        }
    }
}
