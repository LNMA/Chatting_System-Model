package com.louay.projects.model.chains.communications.account;


import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.Objects;

@Configuration
@Component
@Scope("prototype")
public class AccountMessage {
    private Long idMessage;
    private String sourceUser;
    private StringBuilder message;
    private String targetUser;
    private java.sql.Timestamp sentDate;
    private Boolean isSeen;

    public AccountMessage() {
    }

    public Long getIdMessage() {
        return idMessage;
    }

    public void setIdMessage(Long idMessage) {
        this.idMessage = idMessage;
    }

    public String getSourceUser() {
        return sourceUser;
    }

    public void setSourceUser(String sourceUser) {
        this.sourceUser = sourceUser;
    }

    public StringBuilder getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = new StringBuilder(message);
    }

    public String getTargetUser() {
        return targetUser;
    }

    public void setTargetUser(String targetUser) {
        this.targetUser = targetUser;
    }

    public Timestamp getSentDate() {
        return sentDate;
    }

    public void setSentDate(Timestamp sentDate) {
        this.sentDate = sentDate;
    }

    public Boolean getSeen() {
        return isSeen;
    }

    public void setSeen(Boolean seen) {
        isSeen = seen;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AccountMessage)) return false;
        AccountMessage that = (AccountMessage) o;
        return getIdMessage().compareTo(that.getIdMessage()) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getIdMessage());
    }

    @Override
    public String toString() {
        return "AccountMessage{" +
                "idMessage=" + idMessage +
                ", sourceUser='" + sourceUser + '\'' +
                ", message=" + message +
                ", targetUser='" + targetUser + '\'' +
                ", sentDate=" + sentDate +
                ", isSeen=" + isSeen +
                '}';
    }
}
