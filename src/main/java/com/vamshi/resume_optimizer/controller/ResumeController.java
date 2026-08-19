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

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vamshi.resume_optimizer.dto.AnalysisResult;

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
    public AnalysisResult analyzeResume(
            @RequestParam("file") MultipartFile file,
            @RequestParam("jobDescription") String jobDescription) throws IOException {

        if (file.isEmpty()) {
            throw new IOException("No file uploaded!");
        }

        PDDocument document = Loader.loadPDF(file.getBytes());
        PDFTextStripper stripper = new PDFTextStripper();
        String resumeText = stripper.getText(document);
        document.close();

        String prompt = PromptBuilder.buildAnalysisPrompt(resumeText, jobDescription);
        String aiResponseJson = geminiService.askGemini(prompt);

        ObjectMapper objectMapper = new ObjectMapper();
        AnalysisResult result = objectMapper.readValue(aiResponseJson, AnalysisResult.class);

        return result;
    }

}