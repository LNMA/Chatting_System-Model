package com.louay.projects.model.chains.communications;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.Objects;

@Configuration
@Component
@Scope("prototype")
public class AccountTextPost {
    private Long idPost;
    private String idUsername;
    private StringBuilder post;
    private java.sql.Timestamp postDate;

    public AccountTextPost() {
    }

    public Long getIdPost() {
        return idPost;
    }

    public void setIdPost(Long idPost) {
        this.idPost = idPost;
    }

    public String getIdUsername() {
        return idUsername;
    }

    public void setIdUsername(String idUsername) {
        this.idUsername = idUsername;
    }

    public StringBuilder getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = new StringBuilder(post);
    }

    public Timestamp getPostDate() {
        return postDate;
    }

    public void setPostDate(Timestamp postDate) {
        this.postDate = postDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AccountTextPost)) return false;
        AccountTextPost that = (AccountTextPost) o;
        return getIdPost().compareTo(that.getIdPost()) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getIdPost());
    }

    @Override
    public String toString() {
        return "AccountTextPost{" +
                "idPost=" + idPost +
                ", idUsername='" + idUsername + '\'' +
                ", post=" + post +
                ", postDate=" + postDate +
                '}';
    }
}
