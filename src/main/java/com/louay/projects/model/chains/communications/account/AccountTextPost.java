package com.louay.projects.model.chains.communications.account;

import com.louay.projects.model.chains.communications.AccountPost;
import com.louay.projects.model.chains.communications.Post;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;


@Configuration
@Component
@Scope("prototype")
public class AccountTextPost extends AccountPost {
    private StringBuilder post;

    public AccountTextPost() {
    }

    public StringBuilder getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = new StringBuilder(post);
    }

    @Override
    public String toString() {
        return super.toString()+", AccountTextPost{" +
                "post=" + post +
                '}';
    }
}
