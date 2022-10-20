package pl.mariuszk.productservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.servlet.context.ServletWebServerApplicationContext;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {

    private final ServletWebServerApplicationContext webServerAppCtxt;

    @GetMapping("/status")
    public ResponseEntity<String> getStatus() {
        int serverPort = webServerAppCtxt.getWebServer().getPort();
        return ResponseEntity.ok(String.format("ProductService: RUNNING (port %s)", serverPort));
    }
}
