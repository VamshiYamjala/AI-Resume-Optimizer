package com.vamshi.resume_optimizer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import com.vamshi.resume_optimizer.service.GeminiService;
import com.vamshi.resume_optimizer.service.PromptBuilder;

import java.io.IOException;

@RestController
public class ResumeController {

    @Autowired
    private GeminiService geminiService;

    @PostMapping("/api/resume/upload")
    public String uploadResume(@RequestParam("file") MultipartFile file) {

        if (file.isEmpty()) {
            return "No file uploaded!";
        }

        try {
            PDDocument document = Loader.loadPDF(file.getBytes());
            PDFTextStripper stripper = new PDFTextStripper();
            String extractedText = stripper.getText(document);
            document.close();

            return "Extracted text:\n\n" + extractedText;

        } catch (IOException e) {
            return "Error reading PDF: " + e.getMessage();
        }
    }

    @PostMapping("/api/analyze")
    public String analyzeResume(
            @RequestParam("file") MultipartFile file,
            @RequestParam("jobDescription") String jobDescription) {

        if (file.isEmpty()) {
            return "No file uploaded!";
        }

        try {
            PDDocument document = Loader.loadPDF(file.getBytes());
            PDFTextStripper stripper = new PDFTextStripper();
            String resumeText = stripper.getText(document);
            document.close();

            String prompt = PromptBuilder.buildAnalysisPrompt(resumeText, jobDescription);

            String aiResponse = geminiService.askGemini(prompt);

            return aiResponse;

        } catch (IOException e) {
            return "Error reading PDF: " + e.getMessage();
        }
    }

}