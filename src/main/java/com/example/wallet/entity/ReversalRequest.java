package com.example.wallet.entity;

//@Entity
public class ReversalRequest {
    private String requestId;

    public ReversalRequest() {
    }

    public ReversalRequest(String requestId) {
        this.requestId = requestId;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }
}
