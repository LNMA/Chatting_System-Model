package com.louay.projects.model.chains.accounts.group;


import com.louay.projects.model.chains.accounts.constant.AccountClassName;
import com.louay.projects.model.chains.accounts.constant.AccountType;
import com.louay.projects.model.chains.accounts.Accounts;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Comparator;
import java.util.Objects;

@Configuration
@Component
@Scope("prototype")
public class Groups extends Accounts implements Comparator<Groups>, Serializable {
    private String idGroup;
    private String groupPrivacy;
    private String groupActivity;

    public Groups() {
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

    public String getGroupActivity() {
        return groupActivity;
    }

    public void setGroupActivity(String groupActivity) {
        this.groupActivity = groupActivity;
    }

    @Override
    public AccountType getAccountType() {
        return AccountType.GROUP;
    }

    @Override
    public AccountClassName getAccountClassName() {
        return AccountClassName.GROUPS;
    }

    @Override
    public int compare(Groups o1, Groups o2) {
        return o1.getIdGroup().compareTo(o2.getIdGroup());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Groups)) return false;
        Groups that = (Groups) o;
        return getIdGroup().compareTo(that.getIdGroup()) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getIdGroup());
    }

    @Override
    public String toString() {
        return super.toString()+", Groups{" +
                "idGroup='" + idGroup + '\'' +
                ", groupPrivacy='" + groupPrivacy + '\'' +
                ", groupActivity='" + groupActivity + '\'' +
                '}';
    }
}
