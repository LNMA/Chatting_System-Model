package com.louay.projects.model.chains.accounts.activity;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Configuration
@Component
@Scope("prototype")
public class AccountStatus {
    private String username;
    private Boolean isSignIn;
    private Boolean isValid;

    public AccountStatus() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Boolean getSignIn() {
        return isSignIn;
    }

    public void setSignIn(Boolean signIn) {
        isSignIn = signIn;
    }

    public Boolean getValid() {
        return isValid;
    }

    public void setValid(Boolean valid) {
        isValid = valid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AccountStatus)) return false;
        AccountStatus that = (AccountStatus) o;
        return getUsername().compareTo(that.getUsername()) == 0 &&
                isSignIn.compareTo(that.isSignIn)  == 0&&
                isValid.compareTo(that.isValid) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUsername(), isSignIn, isValid);
    }

    @Override
    public String toString() {
        return "AccountStatus{" +
                "username='" + username + '\'' +
                ", isSignIn=" + isSignIn +
                ", isValid=" + isValid +
                '}';
    }
}
