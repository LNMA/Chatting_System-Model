package com.louay.projects.model.chains.member;

import java.sql.Timestamp;

public abstract class Request {
    private String targetAccount;
    private java.sql.Timestamp requestDate;

    public Request() {
    }

    public String getTargetAccount() {
        return targetAccount;
    }

    public void setTargetAccount(String targetAccount) {
        this.targetAccount = targetAccount;
    }

    public Timestamp getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(Timestamp requestDate) {
        this.requestDate = requestDate;
    }

    @Override
    public String toString() {
        return "Request{" +
                "targetAccount='" + targetAccount + '\'' +
                ", requestDate=" + requestDate +
                '}';
    }
}
