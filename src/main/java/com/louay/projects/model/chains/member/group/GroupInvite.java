package com.louay.projects.model.chains.member.group;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Configuration
@Component
@Scope("prototype")
public class GroupInvite {
    private String sourceIdGroup;
    private String targetAccount;
    private java.sql.Timestamp inviteDate;

    public GroupInvite() {
    }

    public String getSourceIdGroup() {
        return sourceIdGroup;
    }

    public void setSourceIdGroup(String sourceIdGroup) {
        this.sourceIdGroup = sourceIdGroup;
    }

    public String getTargetAccount() {
        return targetAccount;
    }

    public void setTargetAccount(String targetAccount) {
        this.targetAccount = targetAccount;
    }

    public Timestamp getInviteDate() {
        return inviteDate;
    }

    public void setInviteDate(Timestamp inviteDate) {
        this.inviteDate = inviteDate;
    }

    @Override
    public String toString() {
        return "GroupInvite{" +
                "sourceIdGroup='" + sourceIdGroup + '\'' +
                ", targetAccount='" + targetAccount + '\'' +
                ", inviteDate=" + inviteDate +
                '}';
    }
}
