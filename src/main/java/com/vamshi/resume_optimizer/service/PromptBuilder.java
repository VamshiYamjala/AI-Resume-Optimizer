package com.vamshi.resume_optimizer.service;

public class PromptBuilder {

    public static String buildAnalysisPrompt(String resumeText, String jobDescription) {

        return """
            You are an expert ATS (Applicant Tracking System) resume analyzer.

            Analyze the following resume against the job description.

            Resume:
            %s

            Job Description:
            %s

            Respond ONLY with valid JSON in exactly this format, with no extra text, no markdown, no explanations outside the JSON:

            {
              "atsScore": <number between 0 and 100>,
              "matchingSkills": [<list of skills from the resume that match the job description>],
              "missingSkills": [<list of important skills in the job description missing from the resume>],
              "suggestions": [<list of specific, actionable suggestions to improve the resume for this job>]
            }
            """.formatted(resumeText, jobDescription);
    }

}