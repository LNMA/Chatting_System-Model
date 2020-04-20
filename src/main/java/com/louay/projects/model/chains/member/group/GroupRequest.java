package com.louay.projects.model.chains.member.group;

import com.louay.projects.model.chains.accounts.group.Groups;
import com.louay.projects.model.chains.member.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;


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
    public String toString() {
        return super.toString()+",GroupRequest{" +
                "sourceGroup=" + sourceGroup +
                '}';
    }
}
