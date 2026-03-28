package com.adonis.pulsedesk;

import lombok.Data;

@Data
public class AIResponse {
    private boolean isTicket;
    private String category;
    private String priority;
    private String summary;
}

