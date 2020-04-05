package com.louay.projects.model.chains.member.group;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Component
@Scope("prototype")
public class GroupMembers {
    private String idGroup;
    private String member;
    private java.sql.Timestamp joinDate;

    public GroupMembers() {
    }

    public String getIdGroup() {
        return idGroup;
    }

    public void setIdGroup(String idGroup) {
        this.idGroup = idGroup;
    }

    public String getMember() {
        return member;
    }

    public void setMember(String member) {
        this.member = member;
    }

    public Timestamp getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(Timestamp joinDate) {
        this.joinDate = joinDate;
    }

    @Override
    public String toString() {
        return "GroupMembers{" +
                "idGroup='" + idGroup + '\'' +
                ", member='" + member + '\'' +
                ", joinDate=" + joinDate +
                '}';
    }
}
