package com.vamshi.resume_optimizer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.vamshi.resume_optimizer.model.ResumeAnalysis;

public interface ResumeAnalysisRepository extends JpaRepository<ResumeAnalysis, Long> {
}