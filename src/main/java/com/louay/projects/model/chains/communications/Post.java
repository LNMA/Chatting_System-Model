package com.louay.projects.model.chains.communications;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Comparator;
import java.util.Objects;

public abstract class Post implements Comparator<Post>, Serializable {
    private Long idPost;
    private java.sql.Timestamp datePost;

    public Post() {
    }

    public Long getIdPost() {
        return idPost;
    }

    public void setIdPost(Long idPost) {
        this.idPost = idPost;
    }

    public Timestamp getDatePost() {
        return datePost;
    }

    public void setDatePost(Timestamp datePost) {
        this.datePost = datePost;
    }

    abstract String getUsername();

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
    public String toString() {
        return "Post{" +
                "idPost=" + idPost +
                ", datePost=" + datePost +
                '}';
    }
}
