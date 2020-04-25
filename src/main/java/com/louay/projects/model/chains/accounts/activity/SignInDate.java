package com.louay.projects.model.chains.accounts.activity;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.Objects;

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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SignInDate)) return false;
        SignInDate that = (SignInDate) o;
        return getUsername().compareTo(that.getUsername()) == 0&&
                getSignInDate().compareTo(that.getSignInDate()) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUsername(), getSignInDate());
    }

    @Override
    public String toString() {
        return "SignInDate{" +
                "username='" + username + '\'' +
                ", signInDate=" + signInDate +
                '}';
    }
}
