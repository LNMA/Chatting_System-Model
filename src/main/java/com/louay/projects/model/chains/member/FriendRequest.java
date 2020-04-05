package com.louay.projects.model.chains.member;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Component
@Scope("prototype")
public class FriendRequest {
    private String username;
    private String requestTarget;
    private java.sql.Timestamp requestDate;

    public FriendRequest() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRequestTarget() {
        return requestTarget;
    }

    public void setRequestTarget(String requestTarget) {
        this.requestTarget = requestTarget;
    }

    public Timestamp getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(Timestamp requestDate) {
        this.requestDate = requestDate;
    }

    @Override
    public String toString() {
        return "FriendRequest{" +
                "username='" + username + '\'' +
                ", requestTarget='" + requestTarget + '\'' +
                ", requestDate=" + requestDate +
                '}';
    }
}
