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
    java.sql.Blob image;
    String fileName;

    public AccountImgPost() {
    }

    public Blob getImage() {
        return image;
    }

    public void setImage(Blob image) {
        this.image = image;
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
                "Image=" + image +
                ", fileName='" + fileName + '\'' +
                '}';
    }
}
