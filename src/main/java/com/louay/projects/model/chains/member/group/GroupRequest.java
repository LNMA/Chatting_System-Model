package com.louay.projects.model.chains.member.group;

import com.louay.projects.model.chains.member.Request;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;


@Configuration
@Component
@Scope("prototype")
public class GroupRequest extends Request {
    private String idGroup;

    public GroupRequest() {
    }

    public String getIdGroup() {
        return idGroup;
    }

    public void setIdGroup(String idGroup) {
        this.idGroup = idGroup;
    }

    @Override
    public String toString() {
        return super.toString()+", GroupRequest{" +
                "idGroup='" + idGroup + '\'' +
                '}';
    }
}
