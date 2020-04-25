package com.louay.projects.model.chains.member.group;

import com.louay.projects.model.chains.accounts.group.Groups;
import com.louay.projects.model.chains.member.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Objects;


@Configuration
@Component
@Scope("prototype")
public class GroupRequest extends Request {
    @Autowired
    private Groups sourceGroup;

    public GroupRequest() {
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
        if (!(o instanceof GroupRequest)) return false;
        if (!super.equals(o)) return false;
        GroupRequest request = (GroupRequest) o;
        return getSourceGroup().compareTo(request.getSourceGroup()) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getSourceGroup());
    }

    @Override
    public String toString() {
        return super.toString()+",GroupRequest{" +
                "sourceGroup=" + sourceGroup +
                '}';
    }
}
