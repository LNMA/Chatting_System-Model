package com.louay.projects.model.chains.communications.group;

import com.louay.projects.model.chains.communications.Post;
import com.louay.projects.model.chains.communications.PostClassName;
import com.louay.projects.model.chains.communications.PostType;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;


@Configuration
@Component
@Scope("prototype")
public class GroupTextPost extends Post {
    private String idGroup;
    private StringBuilder post;

    public GroupTextPost() {
    }

    public String getIdGroup() {
        return idGroup;
    }

    public void setIdGroup(String idGroup) {
        this.idGroup = idGroup;
    }

    public void setPost(StringBuilder post) {
        this.post = post;
    }

    public StringBuilder getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = new StringBuilder(post);
    }

    @Override
    public PostClassName getClassName() {
        return PostClassName.GROUP_TEXT_POST;
    }

    @Override
    public PostType getType() {
        return PostType.TEXT_POST;
    }

    @Override
    public String toString() {
        return super.toString()+",GroupTextPost{" +
                "idGroup='" + idGroup + '\'' +
                ", post=" + post +
                '}';
    }
}
