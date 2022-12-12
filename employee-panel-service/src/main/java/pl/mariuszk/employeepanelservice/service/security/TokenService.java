package pl.mariuszk.employeepanelservice.service.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;
import pl.mariuszk.employeepanelservice.model.frontend.TokenDto;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;

@Service
@RequiredArgsConstructor
public class TokenService {

    private static final String ISSUER = "self";
    private static final int TIME_AMOUNT_FOR_EXPIRATION = 3;

    private final JwtEncoder jwtEncoder;

    public TokenDto generateToken(Authentication authentication) {
        Instant now = LocalDateTime.now().toInstant(ZoneOffset.UTC);

        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer(ISSUER)
                .issuedAt(now)
                .expiresAt(now.plus(TIME_AMOUNT_FOR_EXPIRATION, ChronoUnit.HOURS))
                .subject(authentication.getName())
                .build();

        String token = this.jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
        return new TokenDto(token);
    }
}
