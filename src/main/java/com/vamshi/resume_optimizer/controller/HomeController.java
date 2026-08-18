package com.vamshi.resume_optimizer.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;

import com.vamshi.resume_optimizer.dto.GreetRequest;
import com.vamshi.resume_optimizer.model.GreetLog;
import com.vamshi.resume_optimizer.repository.GreetLogRepository;

@RestController
public class HomeController {

    @Autowired
    private GreetLogRepository greetLogRepository;

    @GetMapping("/api/health")
    public String home() {
        return "Hello Vamshi! Your Resume Optimizer backend is alive.";
    }

    @PostMapping("/greet")
    public String greet(@RequestBody GreetRequest request) {
        GreetLog log = new GreetLog();
        log.setName(request.getName());
        greetLogRepository.save(log);

        return "Hello " + request.getName() + "! Nice to meet you.";
    }

    @Autowired
    private com.vamshi.resume_optimizer.service.GeminiService geminiService;

    @PostMapping("/api/test-ai")
    public String testAI(@RequestBody GreetRequest request) {
        return geminiService.askGemini("Say hello to " + request.getName() + " in a creative way.");
    }

}