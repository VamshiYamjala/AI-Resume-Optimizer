package com.vamshi.resume_optimizer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.vamshi.resume_optimizer.model.GreetLog;

public interface GreetLogRepository extends JpaRepository<GreetLog, Long> {
}