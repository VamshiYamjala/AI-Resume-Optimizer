package com.vamshi.resume_optimizer.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class ResumeController {

    @PostMapping("/api/resume/upload")
    public String uploadResume(@RequestParam("file") MultipartFile file) {

        if (file.isEmpty()) {
            return "No file uploaded!";
        }

        String fileName = file.getOriginalFilename();
        long fileSize = file.getSize();
        String fileType = file.getContentType();

        return "Received file: " + fileName +
               " | Size: " + fileSize + " bytes" +
               " | Type: " + fileType;
    }

}