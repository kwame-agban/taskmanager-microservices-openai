package com.taskmanager.ai.ops;

import com.taskmanager.ai.common.AIModelClient;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AIOpsController {
  private final AIModelClient aiClient;
  private final RestTemplate restTemplate = new RestTemplate();
  public AIOpsController(AIModelClient aiClient) {
    this.aiClient = aiClient;
  }
  @Scheduled(fixedRate = 1800000)
  public void analyzeMetrics() {
    String metrics = restTemplate.getForObject("[prometheus](http://prometheus:9090/api/v1/query?query=up)", String.class);
    aiClient.callModel("Analyse ces métriques système et détecte les anomalies:\n" + metrics);
  }
}

