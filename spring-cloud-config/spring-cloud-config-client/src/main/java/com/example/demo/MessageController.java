package com.example.demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
public class MessageController {

    @Value("${message}")
    private String message;

    @GetMapping("/message")
    public String getMessage() {
        return this.message;
    }

    // Run code when the context is refreshed
    @EventListener({ ContextRefreshedEvent.class })
    public void onContextRefreshed() {
        // code to execute when context is refreshed
        System.out.println("\n\n\nContext refreshed, new message value: " + message);
    }
}
