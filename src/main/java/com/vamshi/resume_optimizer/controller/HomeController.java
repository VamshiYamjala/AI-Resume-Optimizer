package com.vamshi.resume_optimizer.controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import com.vamshi.resume_optimizer.dto.GreetRequest;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/api/health")
    public String home() {
        return "Hello Vamshi! Your Resume Optimizer backend is alive.";
    }
    @PostMapping("/greet")
    public String greet(@RequestBody GreetRequest request) {
    return "Hello " + request.getName() + "! Nice to meet you.";
}

}