package pl.mariuszk.frontendcommunicationservice.service.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.stereotype.Service;
import pl.mariuszk.frontendcommunicationservice.model.frontend.TokenDto;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;
import java.util.stream.Collectors;

import static org.elasticsearch.geometry.utils.WellKnownText.SPACE;

@Service
@RequiredArgsConstructor
public class TokenService {

    private static final String ISSUER = "self";
    private static final String SCOPE = "scope";
    private static final int TIME_AMOUNT_FOR_EXPIRATION = 1;

    private final JwtEncoder jwtEncoder;

    public TokenDto generateToken(Authentication authentication) {
        Instant now = LocalDateTime.now().toInstant(ZoneOffset.UTC);
        String scope = getScope(authentication);

        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer(ISSUER)
                .issuedAt(now)
                .expiresAt(now.plus(TIME_AMOUNT_FOR_EXPIRATION, ChronoUnit.HOURS))
                .subject(authentication.getName())
                .claim(SCOPE, scope)
                .build();

        String token = this.jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
        return new TokenDto(token);
    }

    private String getScope(Authentication authentication) {
        return authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(SPACE));
    }
}
