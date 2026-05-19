package com.taskmanager.ai.review;

import com.taskmanager.ai.common.AIModelClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/ai/review")
public class AICodeReviewController {
  private final AIModelClient aiClient;
  public AICodeReviewController(AIModelClient aiClient) {
    this.aiClient = aiClient;
  }
  @PostMapping("/review")
  public String reviewCode(@RequestBody String diff) {
    String prompt = "Analyse ce diff de code Java et propose des suggestions d'amélioration:\n" + diff;
    return aiClient.callModel(prompt);
  }
}

