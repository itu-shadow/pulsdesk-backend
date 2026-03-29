package com.adonis.pulsedesk;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class AIResponse {

    @JsonProperty("isTicket")
    private boolean ticket;
    private String category;
    private String priority;
    private String summary;
}