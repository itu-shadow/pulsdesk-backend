package com.adonis.pulsedesk;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.*;

import java.util.*;

@Service
public class HuggingFaceService {

    @Value("${huggingface.api.token}")
    private String token;

    private final String API_URL = "https://router.huggingface.co/v1/chat/completions";

    public Map<String, String> analyzeComment(String text) {

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(token);

        String prompt = """
                You are an AI system that classifies user comments.

                Respond ONLY with VALID JSON. No explanation, no extra text.

                Format:
                {
                  "isTicket": true or false,
                  "category": "bug" | "feature" | "billing" | "account" | "other",
                  "priority": "low" | "medium" | "high",
                  "summary": "short summary"
                }

                Comment: """ + text;

        Map<String, Object> body = new HashMap<>();
        body.put("model", "openai/gpt-oss-20b");

        List<Map<String, String>> messages = new ArrayList<>();
        Map<String, String> msg = new HashMap<>();
        msg.put("role", "user");
        msg.put("content", prompt);

        messages.add(msg);
        body.put("messages", messages);

        HttpEntity<Map<String, Object>> request = new HttpEntity<>(body, headers);

        Map<String, String> result = new HashMap<>();

        try {
            ResponseEntity<Map<String, Object>> response =
                    restTemplate.postForEntity(API_URL, request, (Class<Map<String, Object>>)(Class<?>)Map.class);

            Map<String, Object> responseBody = response.getBody();

            if (responseBody == null) {
                throw new Exception("Empty response");
            }

            List<Map<String, Object>> choices =
                    (List<Map<String, Object>>) responseBody.get("choices");

            Map<String, Object> firstChoice = choices.get(0);

            Map<String, Object> message =
                    (Map<String, Object>) firstChoice.get("message");

            String content = (String) message.get("content");

            ObjectMapper mapper = new ObjectMapper();

            int start = content.indexOf("{");
            int end = content.lastIndexOf("}");

            if (start != -1 && end != -1) {
                String json = content.substring(start, end + 1);
                AIResponse ai = mapper.readValue(json, AIResponse.class);

                result.put("isTicket", String.valueOf(ai.isTicket()));
                result.put("category", ai.getCategory());
                result.put("priority", ai.getPriority());
                result.put("summary", ai.getSummary());
                return result;
            }

        } catch (Exception e) {
            System.out.println("AI failed, using fallback: " + e.getMessage());
        }

        String lower = text.toLowerCase();

        if (lower.contains("crash") || lower.contains("error") || lower.contains("fail")) {
            result.put("isTicket", "true");
            result.put("category", "bug");
            result.put("priority", "high");
        } else if (lower.contains("charge") || lower.contains("payment") || lower.contains("billing")) {
            result.put("isTicket", "true");
            result.put("category", "billing");
            result.put("priority", "high");
        } else if (lower.contains("feature") || lower.contains("add")) {
            result.put("isTicket", "true");
            result.put("category", "feature");
            result.put("priority", "medium");
        } else {
            result.put("isTicket", "false");
            result.put("category", "other");
            result.put("priority", "low");
        }

        result.put("summary", text.substring(0, Math.min(50, text.length())));

        return result;
    }
}