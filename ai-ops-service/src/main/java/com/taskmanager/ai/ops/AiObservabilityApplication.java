package com.taskmanager.ai.ops;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@SpringBootApplication(scanBasePackages = "com.taskmanager.ai")
public class AiObservabilityApplication {
  public static void main(String[] args) {
    SpringApplication.run(AiObservabilityApplication.class, args);
  }
}
