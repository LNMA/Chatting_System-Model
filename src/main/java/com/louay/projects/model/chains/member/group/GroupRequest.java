package com.louay.projects.model.chains.member.group;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Component
@Scope("prototype")
public class GroupRequest {
    private String idGroup;
    private String requestTarget;
    private java.sql.Timestamp sentDate;

    public GroupRequest() {
    }

    public String getIdGroup() {
        return idGroup;
    }

    public void setIdGroup(String idGroup) {
        this.idGroup = idGroup;
    }

    public String getRequestTarget() {
        return requestTarget;
    }

    public void setRequestTarget(String requestTarget) {
        this.requestTarget = requestTarget;
    }

    public Timestamp getSentDate() {
        return sentDate;
    }

    public void setSentDate(Timestamp sentDate) {
        this.sentDate = sentDate;
    }

    @Override
    public String toString() {
        return "GroupRequest{" +
                "idGroup='" + idGroup + '\'' +
                ", requestTarget='" + requestTarget + '\'' +
                ", sentDate=" + sentDate +
                '}';
    }
}
