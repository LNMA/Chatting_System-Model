package com.louay.projects.model.chains.member.group;

import com.louay.projects.model.chains.accounts.Client;
import com.louay.projects.model.chains.accounts.group.Groups;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Configuration
@Component
@Scope("prototype")
public class GroupInvite {
    @Autowired
    private Groups sourceGroup;
    @Autowired
    private Client targetAccount;
    private java.sql.Timestamp inviteDate;

    public GroupInvite() {
    }

    public Groups getSourceGroup() {
        return sourceGroup;
    }

    public void setSourceGroup(Groups sourceGroup) {
        this.sourceGroup = sourceGroup;
    }

    public Client getTargetAccount() {
        return targetAccount;
    }

    public void setTargetAccount(Client targetAccount) {
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
                "sourceGroup=" + sourceGroup +
                ", targetAccount=" + targetAccount +
                ", inviteDate=" + inviteDate +
                '}';
    }
}
