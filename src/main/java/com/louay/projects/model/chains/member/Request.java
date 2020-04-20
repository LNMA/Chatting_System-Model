package com.louay.projects.model.chains.member;

import com.louay.projects.model.chains.accounts.Client;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Timestamp;

public abstract class Request {
    @Autowired
    private Client targetAccount;
    private java.sql.Timestamp requestDate;

    public Request() {
    }

    public Client getTargetAccount() {
        return targetAccount;
    }

    public void setTargetAccount(Client targetAccount) {
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
