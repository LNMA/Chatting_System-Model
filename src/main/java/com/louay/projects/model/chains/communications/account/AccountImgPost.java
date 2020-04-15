package com.louay.projects.model.chains.communications.account;

import com.louay.projects.model.chains.communications.AccountPost;
import com.louay.projects.model.chains.communications.Post;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.sql.Blob;

@Configuration
@Component
@Scope("prototype")
public class AccountImgPost extends AccountPost {
    java.sql.Blob Image;
    String fileName;

    public AccountImgPost() {
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

    @Override
    public String toString() {
        return super.toString()+", AccountImgPost{" +
                "Image=" + Image +
                ", fileName='" + fileName + '\'' +
                '}';
    }
}
