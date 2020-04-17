package com.louay.projects.model.chains.accounts.activity;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Configuration
@Component
@Scope("prototype")
public class SignInDate {
    private String username;
    private java.sql.Timestamp signInDate;

    public SignInDate() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Timestamp getSignInDate() {
        return signInDate;
    }

    public void setSignInDate(Timestamp signInDate) {
        this.signInDate = signInDate;
    }

    @Override
    public String toString() {
        return "SignInDate{" +
                "username='" + username + '\'' +
                ", signInDate=" + signInDate +
                '}';
    }
}
