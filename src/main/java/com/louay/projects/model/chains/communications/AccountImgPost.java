package com.louay.projects.model.chains.communications;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.sql.Blob;
import java.sql.Timestamp;
import java.util.Objects;

@Configuration
@Component
@Scope("prototype")
public class AccountImgPost {
    Long idPost;
    String username;
    java.sql.Blob Image;
    String fileName;
    java.sql.Timestamp dateUpload;

    public AccountImgPost() {
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

    public Blob getImage() {
        return Image;
    }

    public void setImage(Blob image) {
        Image = image;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Timestamp getDateUpload() {
        return dateUpload;
    }

    public void setDateUpload(Timestamp dateUpload) {
        this.dateUpload = dateUpload;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AccountImgPost)) return false;
        AccountImgPost that = (AccountImgPost) o;
        return getIdPost().compareTo(that.getIdPost()) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getIdPost());
    }

    @Override
    public String toString() {
        return "AccountImgPost{" +
                "idPost=" + idPost +
                ", username='" + username + '\'' +
                ", Image=" + Image +
                ", fileName='" + fileName + '\'' +
                ", dateUpload=" + dateUpload +
                '}';
    }
}
