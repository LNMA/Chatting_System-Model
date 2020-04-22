package com.louay.projects.model.chains.communications.account;


import com.louay.projects.model.chains.accounts.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Comparator;
import java.util.Objects;

@Configuration
@Component
@Scope("prototype")
public class AccountMessage implements Comparable<AccountMessage>, Comparator<AccountMessage>, Serializable {
    private Long idMessage;
    @Autowired
    private Client sourceUser;
    private StringBuilder message;
    @Autowired
    private Client targetUser;
    private java.sql.Timestamp sentDate;
    private Boolean isSeen;
    private int numberOfSeen;
    private int numberOfAllMessage;

    public AccountMessage() {
    }

    public Long getIdMessage() {
        return idMessage;
    }

    public void setIdMessage(Long idMessage) {
        this.idMessage = idMessage;
    }

    public Client getSourceUser() {
        return sourceUser;
    }

    public void setSourceUser(Client sourceUser) {
        this.sourceUser = sourceUser;
    }

    public StringBuilder getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = new StringBuilder(message);
    }

    public void setMessageStringBuilder(StringBuilder message) {
        this.message =message;
    }

    public Client getTargetUser() {
        return targetUser;
    }

    public void setTargetUser(Client targetUser) {
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

    public int getNumberOfSeen() {
        return numberOfSeen;
    }

    public void setNumberOfSeen(int numberOfSeen) {
        this.numberOfSeen = numberOfSeen;
    }

    public int getNumberOfAllMessage() {
        return numberOfAllMessage;
    }

    public void setNumberOfAllMessage(int numberOfAllMessage) {
        this.numberOfAllMessage = numberOfAllMessage;
    }

    public int getNumOfNotSeen(){
        return this.numberOfAllMessage - this.numberOfSeen;
    }

    @Override
    public int compare(AccountMessage o1, AccountMessage o2) {
        return o1.getSentDate().compareTo(o2.getSentDate());
    }

    @Override
    public int compareTo(AccountMessage o) {
        if (this.sentDate != null && o.getSentDate() != null) {
            java.sql.Timestamp date1 = this.sentDate;
            java.sql.Timestamp date2 = o.getSentDate();

            if (date1.after(date2)) {
                return -1;
            }
            if (date1.before(date2)) {
                return 1;
            }
        }
        return 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AccountMessage)) return false;
        AccountMessage that = (AccountMessage) o;
        return getIdMessage().compareTo(that.getIdMessage()) == 0 &&
                getSourceUser().compareTo(that.getSourceUser()) == 0 &&
                getTargetUser().compareTo(that.getTargetUser()) == 0 &&
                getSentDate().compareTo(that.getSentDate()) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getIdMessage(), getSourceUser(), getTargetUser(), getSentDate());
    }

    @Override
    public String toString() {
        return "AccountMessage{" +
                "idMessage=" + idMessage +
                ", sourceUser=" + sourceUser +
                ", message=" + message +
                ", targetUser=" + targetUser +
                ", sentDate=" + sentDate +
                ", isSeen=" + isSeen +
                ", numberOfSeen=" + numberOfSeen +
                ", numberOfAllMessage=" + numberOfAllMessage +
                '}';
    }
}
