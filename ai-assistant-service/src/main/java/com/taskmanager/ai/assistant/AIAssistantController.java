package com.taskmanager.ai.assistant;

import com.taskmanager.ai.common.AIModelClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/ai/assistant")
public class AIAssistantController {
  private final AIModelClient aiClient;
  public AIAssistantController(AIModelClient aiClient) {
    this.aiClient = aiClient;
  }
  @PostMapping("/ask")
  public String askAssistant(@RequestBody Map<String, String> body) {
    String question = body.get("question");
    return aiClient.callModel("Réponds en tant qu'assistant du gestionnaire de tâches: " + question);
  }
}

