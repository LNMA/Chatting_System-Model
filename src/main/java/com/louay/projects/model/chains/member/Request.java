package com.louay.projects.model.chains.member;

import com.louay.projects.model.chains.accounts.Client;
import com.louay.projects.model.chains.member.constant.RequestClassName;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Comparator;
import java.util.Objects;

public abstract class Request implements Comparator<Request> , Serializable, Comparable<Request>{
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

    public abstract RequestClassName getRequestClassName();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Request)) return false;
        Request request = (Request) o;
        return getTargetAccount().compareTo(request.getTargetAccount()) == 0 &&
                getRequestDate().compareTo(request.getRequestDate()) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTargetAccount(), getRequestDate());
    }

    @Override
    public int compare(Request o1, Request o2) {
        java.sql.Timestamp date1 = o1.getRequestDate();
        java.sql.Timestamp date2 = o2.getRequestDate();
        return date1.compareTo(date2);
    }

    @Override
    public int compareTo(Request o) {
        if (this.requestDate != null && o.getRequestDate() != null) {
            java.sql.Timestamp date1 = this.requestDate;
            java.sql.Timestamp date2 = o.getRequestDate();

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
    public String toString() {
        return "Request{" +
                "targetAccount='" + targetAccount + '\'' +
                ", requestDate=" + requestDate +
                '}';
    }
}
