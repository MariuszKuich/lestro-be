package pl.mariuszk.employeepanelservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.mariuszk.employeepanelservice.model.frontend.TokenDto;
import pl.mariuszk.employeepanelservice.service.security.TokenService;

@RestController
@RequestMapping("/lestro-panel")
@RequiredArgsConstructor
public class EmployeePanelController {

    private final TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity<TokenDto> authenticateAndReturnJwtToken(Authentication authentication) {
        return ResponseEntity.ok(tokenService.generateToken(authentication));
    }
}
