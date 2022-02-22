package com.example.wallet.reversal.entity;

public class ReversalRequestDTO {
    private String requestId;

    public ReversalRequestDTO(String requestId) {
        this.requestId = requestId;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }
}
