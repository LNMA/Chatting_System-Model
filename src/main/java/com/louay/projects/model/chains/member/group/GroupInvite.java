package com.louay.projects.model.chains.member.group;

import com.louay.projects.model.chains.accounts.group.Groups;
import com.louay.projects.model.chains.member.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.Objects;

@Configuration
@Component
@Scope("prototype")
public class GroupInvite extends Request {
    @Autowired
    private Groups sourceGroup;
    private java.sql.Timestamp inviteDate;

    public GroupInvite() {
    }

    public Groups getSourceGroup() {
        return sourceGroup;
    }

    public void setSourceGroup(Groups sourceGroup) {
        this.sourceGroup = sourceGroup;
    }

    public Timestamp getInviteDate() {
        return inviteDate;
    }

    public void setInviteDate(Timestamp inviteDate) {
        this.inviteDate = inviteDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GroupInvite)) return false;
        if (!super.equals(o)) return false;
        GroupInvite invite = (GroupInvite) o;
        return getSourceGroup().equals(invite.getSourceGroup());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getSourceGroup());
    }

    @Override
    public String toString() {
        return super.toString()+", GroupInvite{" +
                "sourceGroup=" + sourceGroup +
                ", inviteDate=" + inviteDate +
                '}';
    }
}
