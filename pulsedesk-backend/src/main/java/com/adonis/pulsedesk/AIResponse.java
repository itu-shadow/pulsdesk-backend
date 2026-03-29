package com.adonis.pulsedesk;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AIResponse {

    @JsonProperty("isTicket")
    private boolean ticket;
    private String category;
    private String priority;
    private String summary;

    public boolean isTicket() {
        return ticket;
    }

    public void setTicket(boolean ticket) {
        this.ticket = ticket;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }
}