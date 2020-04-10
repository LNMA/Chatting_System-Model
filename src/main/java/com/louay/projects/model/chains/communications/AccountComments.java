package com.louay.projects.model.chains.communications;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.Objects;

@Configuration
@Component
@Scope("prototype")
public class AccountComments {
    private Long idComment;
    private String idUsername;
    private StringBuilder comments;
    private java.sql.Timestamp commentsDate;

    public AccountComments() {
    }

    public Long getIdComment() {
        return idComment;
    }

    public void setIdComment(Long idComment) {
        this.idComment = idComment;
    }

    public String getIdUsername() {
        return idUsername;
    }

    public void setIdUsername(String idUsername) {
        this.idUsername = idUsername;
    }

    public StringBuilder getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = new StringBuilder(comments);
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
        if (!(o instanceof AccountComments)) return false;
        AccountComments that = (AccountComments) o;
        return getIdComment().compareTo(that.getIdComment()) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getIdComment());
    }

    @Override
    public String toString() {
        return "AccountComments{" +
                "idComment=" + idComment +
                ", idUsername='" + idUsername + '\'' +
                ", comments=" + comments +
                ", commentsDate=" + commentsDate +
                '}';
    }
}
