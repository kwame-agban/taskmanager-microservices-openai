package com.taskmanager.ai.planner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@SpringBootApplication(scanBasePackages = "com.taskmanager.ai")
public class AiPlannerServiceApplication {
  public static void main(String[] args) {
    SpringApplication.run(AiPlannerServiceApplication.class, args);
  }
}
