package com.taskmanager.ai.review;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@SpringBootApplication(scanBasePackages = "com.taskmanager.ai")
public class AiReviewServiceApplication {
  public static void main(String[] args) {
    SpringApplication.run(AiReviewServiceApplication.class, args);
  }
}
