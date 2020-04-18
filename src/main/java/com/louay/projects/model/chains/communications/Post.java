package com.louay.projects.model.chains.communications;

import com.louay.projects.model.chains.communications.constant.PostClassName;
import com.louay.projects.model.chains.communications.constant.PostType;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Comparator;
import java.util.Objects;

public abstract class Post implements Comparator<Post>, Serializable, Comparable<Post> {
    private Long idPost;
    private String username;
    private java.sql.Timestamp datePost;

    public Post() {
    }

    public Long getIdPost() {
        return idPost;
    }

    public void setIdPost(Long idPost) {
        this.idPost = idPost;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Timestamp getDatePost() {
        return datePost;
    }

    public void setDatePost(Timestamp datePost) {
        this.datePost = datePost;
    }

    public abstract PostType getType();

    public abstract PostClassName getClassName();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Post)) return false;
        Post post = (Post) o;
        return getIdPost().compareTo(post.getIdPost()) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getIdPost());
    }

    @Override
    public int compare(Post o1, Post o2) {
        java.sql.Timestamp date1 = o1.getDatePost();
        java.sql.Timestamp date2 = o2.getDatePost();
        return date1.compareTo(date2);
    }

    @Override
    public int compareTo(Post o) {
        if (this.datePost != null && o.getDatePost() != null) {
            java.sql.Timestamp date1 = this.datePost;
            java.sql.Timestamp date2 = o.getDatePost();

            if (date1.after(date2)) {
                return -1;
            }
            if (date1.before(date2)) {
                return 1;
            }
        }
        return 0;
    }

    @Override
    public String toString() {
        return "Post{" +
                "idPost=" + idPost +
                ", username='" + username + '\'' +
                ", datePost=" + datePost +
                '}';
    }
}
