package com.louay.projects.model.chains.util;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Configuration
@Component
@Scope("prototype")
public class PictureBase64 {
    String username;
    StringBuilder pictureBase64;


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public StringBuilder getPictureBase64() {
        return pictureBase64;
    }

    public void setPictureBase64(String pictureBase64) {
        this.pictureBase64 = new StringBuilder(pictureBase64);
    }

    @Override
    public String toString() {
        return "PictureBase64{" +
                "username='" + username + '\'' +
                ", pictureBase64='" + pictureBase64 + '\'' +
                '}';
    }
}
