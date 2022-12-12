package pl.mariuszk.employeepanelservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import pl.mariuszk.employeepanelservice.model.frontend.TokenDto;
import pl.mariuszk.employeepanelservice.service.security.TokenService;

@RestController
@RequestMapping("/lestro-panel")
@CrossOrigin(origins = "http://localhost:4202")
@RequiredArgsConstructor
public class EmployeePanelController {

    private final TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity<TokenDto> authenticateAndReturnJwtToken(Authentication authentication) {
        return ResponseEntity.ok(tokenService.generateToken(authentication));
    }
}