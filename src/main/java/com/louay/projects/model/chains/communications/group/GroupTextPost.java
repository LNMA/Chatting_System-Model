package com.louay.projects.model.chains.communications.group;

import com.louay.projects.model.chains.communications.GroupPost;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;


@Configuration
@Component
@Scope("prototype")
public class GroupTextPost extends GroupPost {
    private StringBuilder post;

    public GroupTextPost() {
    }

    public StringBuilder getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = new StringBuilder(post);
    }

    @Override
    public String toString() {
        return super.toString()+", GroupTextPost{" +
                "post=" + post +
                '}';
    }
}
