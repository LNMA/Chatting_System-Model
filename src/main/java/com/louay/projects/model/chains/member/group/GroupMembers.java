package com.louay.projects.model.chains.member.group;

import com.louay.projects.model.chains.accounts.group.Groups;
import com.louay.projects.model.chains.member.Member;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;


@Configuration
@Component
@Scope("prototype")
public class GroupMembers extends Member {
    private Groups group;

    public GroupMembers() {
    }

    public Groups getGroup() {
        return group;
    }

    public void setGroup(Groups group) {
        this.group = group;
    }

    @Override
    public String toString() {
        return super.toString()+",GroupMembers{" +
                "group=" + group +
                '}';
    }
}

