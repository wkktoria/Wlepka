package io.github.wkktoria.wlepka.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/logging")
@Slf4j
public class LoggingController {

    @GetMapping
    public ResponseEntity<String> testLogging() {
        log.trace("\uD83D\uDD0E TRACE: This is very detailed trace log. Used for tracking execution flow.");
        log.debug("\uD83D\uDC1B DEBUG: This is debug message. Used for debugging.");
        log.info("ℹ️ INFO: This is information message. Application events.");
        log.warn("⚠️ WARN: This is warning. Something might go wrong.");
        log.error("\uD83D\uDEA8 ERROR: An error occurred. This needs immediate attention.");
        return ResponseEntity.status(HttpStatus.OK)
                .body("Logging tested successfully");
    }

}
