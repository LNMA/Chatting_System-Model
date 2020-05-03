package com.louay.projects.model.chains.member.group;

import com.louay.projects.model.chains.accounts.group.Groups;
import com.louay.projects.model.chains.member.Request;
import com.louay.projects.model.chains.member.constant.RequestClassName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Configuration
@Component
@Scope("prototype")
public class GroupInvite extends Request {
    @Autowired
    private Groups sourceGroup;

    public GroupInvite() {
    }

    public Groups getSourceGroup() {
        return sourceGroup;
    }

    public void setSourceGroup(Groups sourceGroup) {
        this.sourceGroup = sourceGroup;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GroupInvite)) return false;
        if (!super.equals(o)) return false;
        GroupInvite invite = (GroupInvite) o;
        return getSourceGroup().compareTo(invite.getSourceGroup()) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getSourceGroup());
    }

    @Override
    public RequestClassName getRequestClassName() {
        return RequestClassName.GROUP_INVITE;
    }

    @Override
    public String toString() {
        return super.toString()+", GroupInvite{" +
                "sourceGroup=" + sourceGroup +
                '}';
    }
}
