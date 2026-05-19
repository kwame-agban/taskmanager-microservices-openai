package com.taskmanager.ai.assistant;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication(scanBasePackages = "com.taskmanager.ai")
public class AIAssistantApplication {
  public static void main(String[] args) {
    SpringApplication.run(AIAssistantApplication.class, args);
  }
}
