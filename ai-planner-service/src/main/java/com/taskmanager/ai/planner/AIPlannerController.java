package com.taskmanager.ai.planner;

import com.taskmanager.ai.common.AIModelClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/ai/planner")
public class AIPlannerController {
  private final AIModelClient aiClient;
  public AIPlannerController(AIModelClient aiClient) {
    this.aiClient = aiClient;
  }
  @PostMapping("/analyze")
  public String analyzeTask(@RequestBody Map<String, String> task) {
    String prompt = "Analyse cette tâche et propose une planification agile: " + task.get("description");
    return aiClient.callModel(prompt);
  }
}
