package com.louay.projects.model.chains.communications.group;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.Objects;

@Configuration
@Component
@Scope("prototype")
public class GroupTextPost {
    private Long idPost;
    private String idGroup;
    private String username;
    private StringBuilder post;
    private java.sql.Timestamp postDate;

    public GroupTextPost() {
    }

    public Long getIdPost() {
        return idPost;
    }

    public void setIdPost(Long idPost) {
        this.idPost = idPost;
    }

    public String getIdGroup() {
        return idGroup;
    }

    public void setIdGroup(String idGroup) {
        this.idGroup = idGroup;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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
        if (!(o instanceof GroupTextPost)) return false;
        GroupTextPost that = (GroupTextPost) o;
        return getIdPost().equals(that.getIdPost());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getIdPost());
    }

    @Override
    public String toString() {
        return "GroupTextPost{" +
                "idPost=" + idPost +
                ", idGroup='" + idGroup + '\'' +
                ", username='" + username + '\'' +
                ", post=" + post +
                ", postDate=" + postDate +
                '}';
    }
}
