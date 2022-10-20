package pl.mariuszk.paymentservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.servlet.context.ServletWebServerApplicationContext;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payment")
@RequiredArgsConstructor
public class PaymentController {

    private final ServletWebServerApplicationContext webServerAppCtxt;

    @GetMapping("/status")
    public ResponseEntity<String> getStatus() {
        int serverPort = webServerAppCtxt.getWebServer().getPort();
        return ResponseEntity.ok(String.format("PaymentService: RUNNING (port %s)", serverPort));
    }
}
