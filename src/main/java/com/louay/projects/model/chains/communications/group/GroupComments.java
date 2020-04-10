package com.louay.projects.model.chains.communications.group;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.Objects;

@Configuration
@Component
@Scope("prototype")
public class GroupComments {
    private Long idComment;
    private String idGroup;
    private String username;
    private StringBuilder comment;
    private java.sql.Timestamp commentsDate;

    public GroupComments() {
    }

    public Long getIdComment() {
        return idComment;
    }

    public void setIdComment(Long idComment) {
        this.idComment = idComment;
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

    public StringBuilder getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = new StringBuilder(comment);
    }

    public Timestamp getCommentsDate() {
        return commentsDate;
    }

    public void setCommentsDate(Timestamp commentsDate) {
        this.commentsDate = commentsDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GroupComments)) return false;
        GroupComments that = (GroupComments) o;
        return getIdComment().compareTo(that.getIdComment()) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getIdComment());
    }

    @Override
    public String toString() {
        return "GroupComments{" +
                "idComment=" + idComment +
                ", idGroup='" + idGroup + '\'' +
                ", username='" + username + '\'' +
                ", comment=" + comment +
                ", commentsDate=" + commentsDate +
                '}';
    }
}
