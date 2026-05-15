package com.taskmanager.ai.common;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import java.util.Map;

@Component
public class AIModelClient {
  private final RestTemplate restTemplate = new RestTemplate();
  private final String apiKey = System.getenv("OPENAI_API_KEY");
  public String callModel(String prompt) {
    String url = "[api.openai.com](https://api.openai.com/v1/chat/completions)";
    Map<String, Object> body = Map.of(
      "model", "gpt-4-turbo",
      "messages", new Object[]{
        Map.of("role", "system", "content", "Vous êtes un agent IA d'assistance logicielle."),
        Map.of("role", "user", "content", prompt)
      }
    );
    var headers = new org.springframework.http.HttpHeaders();
    headers.set("Authorization", "Bearer " + apiKey);
    headers.set("Content-Type", "application/json");
    var request = new org.springframework.http.HttpEntity<>(body, headers);
    Map<String, Object> response = restTemplate.postForObject(url, request, Map.class);
    return ((Map<String, String>) ((Map<String, Object>) ((java.util.List<?>) response.get("choices")).get(0)).get("message")).get("content");
  }

}
