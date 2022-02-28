package com.example.wallet.reversal.entity;

import javax.persistence.Column;
import javax.persistence.Entity;

//@Entity
public class ReversalRequest {
//    @Column(name = "requestId", nullable = false)
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
