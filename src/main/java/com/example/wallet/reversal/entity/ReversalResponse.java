package com.example.wallet.reversal.entity;

public class ReversalResponse {
    private String code;
    private String message;
    private String corebankingResponseId;
    private String otherDetails;

    public ReversalResponse(String code, String message, String corebankingResponseId, String otherDetails) {
        this.code = code;
        this.message = message;
        this.corebankingResponseId = corebankingResponseId;
        this.otherDetails = otherDetails;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCorebankingResponseId() {
        return corebankingResponseId;
    }

    public void setCorebankingResponseId(String corebankingResponseId) {
        this.corebankingResponseId = corebankingResponseId;
    }

    public String getOtherDetails() {
        return otherDetails;
    }

    public void setOtherDetails(String otherDetails) {
        this.otherDetails = otherDetails;
    }

    @Override
    public String toString() {
        return "{" +
                "code='" + code + '\'' +
                ", message='" + message + '\'' +
                ", corebankingResponseId='" + corebankingResponseId + '\'' +
                ", otherDetails='" + otherDetails + '\'' +
                '}';
    }
}
