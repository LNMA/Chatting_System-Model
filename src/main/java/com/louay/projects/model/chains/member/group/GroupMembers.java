package com.louay.projects.model.chains.member.group;

import com.louay.projects.model.chains.accounts.group.Groups;
import com.louay.projects.model.chains.member.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Objects;


@Configuration
@Component
@Scope("prototype")
public class GroupMembers extends Member {
    @Autowired
    private Groups group;
    private String groupMemberType;


    public GroupMembers() {
    }

    public Groups getGroup() {
        return group;
    }

    public void setGroup(Groups group) {
        this.group = group;
    }

    public String getGroupMemberType() {
        return groupMemberType;
    }

    public void setGroupMemberType(String groupMemberType) {
        this.groupMemberType = groupMemberType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GroupMembers)) return false;
        if (!super.equals(o)) return false;
        GroupMembers members = (GroupMembers) o;
        return getGroup().compareTo(members.getGroup()) == 0&&
                getGroupMemberType().compareTo(members.getGroupMemberType()) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getGroup(), getGroupMemberType());
    }

    @Override
    public String toString() {
        return super.toString()+",GroupMembers{" +
                "group=" + group +
                ", groupMemberType='" + groupMemberType + '\'' +
                '}';
    }
}

