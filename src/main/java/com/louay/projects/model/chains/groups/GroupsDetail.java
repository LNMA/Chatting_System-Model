package com.louay.projects.model.chains.groups;


import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.Objects;

@Component
@Scope("prototype")
public class GroupsDetail {
    private String idGroup;
    private String groupPrivacy;
    private java.sql.Timestamp groupCreateDate;
    private String groupActivity;

    public GroupsDetail() {
    }

    public String getIdGroup() {
        return idGroup;
    }

    public void setIdGroup(String idGroup) {
        this.idGroup = idGroup;
    }

    public String getGroupPrivacy() {
        return groupPrivacy;
    }

    public void setGroupPrivacy(String groupPrivacy) {
        this.groupPrivacy = groupPrivacy;
    }

    public Timestamp getGroupCreateDate() {
        return groupCreateDate;
    }

    public void setGroupCreateDate(Timestamp groupCreateDate) {
        this.groupCreateDate = groupCreateDate;
    }

    public String getGroupActivity() {
        return groupActivity;
    }

    public void setGroupActivity(String groupActivity) {
        this.groupActivity = groupActivity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GroupsDetail)) return false;
        GroupsDetail that = (GroupsDetail) o;
        return getIdGroup().equals(that.getIdGroup());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getIdGroup());
    }

    @Override
    public String toString() {
        return "Groups{" +
                "idGroup='" + idGroup + '\'' +
                ", groupPrivacy=" + groupPrivacy +
                ", groupCreateDate=" + groupCreateDate +
                ", groupActivity='" + groupActivity + '\'' +
                '}';
    }
}
