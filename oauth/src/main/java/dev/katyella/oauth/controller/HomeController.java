package dev.katyella.oauth.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "Welcome to our public landing page!";
    }

    @GetMapping("/secured")
    public String secured() {
        return "Welcome to our secured, authenticated landing page!";
    }
}
