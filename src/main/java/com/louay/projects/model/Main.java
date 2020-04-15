package com.louay.projects.model;

import com.louay.projects.model.chains.communications.account.AccountPicture;
import com.louay.projects.model.chains.users.Client;
import com.louay.projects.model.constants.UserGender;
import com.louay.projects.model.constants.UserType;
import com.louay.projects.model.dao.CreateUsersDAO;
import com.louay.projects.model.dao.SelectUsersDAO;
import com.louay.projects.model.util.date.NowDate;
import com.louay.projects.model.util.file.FileProcess;
import com.louay.projects.model.util.pool.MyConnectionPool;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.*;
import java.sql.SQLException;
import java.util.Base64;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.scan("com.louay.projects.model");
        context.refresh();

        Client user = context.getBean(Client.class);
        user.setUsername("louay");
        user.setPassword("123");
        user.setAccountPermission(UserType.CLIENT.getType());
        user.setDateCreate(NowDate.getNowTimestamp());

        user.setFirstName("louay");
        user.setLastName("amr");
        user.setBirthday(java.sql.Date.valueOf("1994-10-08"));
        user.setAge(user.getAgeFromBirthday());
        user.setGender(UserGender.MALE.getGender());
        user.setTelephone("00962");
        user.setEmail("louay@projects");
        user.setCountry("jordan");
        user.setState("az");
        user.setAddress("qatar street");

        CreateUsersDAO createUsersDAO = (CreateUsersDAO) context.getBean("usersDAO");


        FileProcess fileProcess = context.getBean(FileProcess.class);

        byte [] bytes = null;
        try {
            bytes = fileProcess.readAPicture("C:\\Users\\Oday Amr\\Pictures\\Annotation 2020-03-08 210620.png");
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < bytes.length; i++) {
            System.out.print(bytes[i]);
        }
        System.out.println();

        AccountPicture picture = context.getBean(AccountPicture.class);
        picture.setUsername(user.getUsername());
        MyConnectionPool pool = (MyConnectionPool) context.getBean("pool");
        java.sql.Blob blob = pool.initBlob();
        picture.setPicture(blob);

        try {
            int size = (int) picture.getPicture().length();
            for (int i = 0; i <size ; i++) {
                System.out.print(picture.getPicture().getBytes(1, size)[i]);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        picture.setUploadDate(NowDate.getNowTimestamp());
        picture.setPictureName("Annotation 2020-03-08 210620.png");

        File file = new File(String.valueOf(picture.getPicture()));
        file.renameTo(new File("Annotation 2020-03-08 210620.png"));


        SelectUsersDAO insertUserPostDAO = (SelectUsersDAO) context.getBean("usersDAO");
        Set<AccountPicture> set = (Set<AccountPicture>) insertUserPostDAO.findPictureByUsername(picture);
        try (OutputStream out = new BufferedOutputStream(new FileOutputStream("C:\\Users\\Oday Amr\\Desktop\\" + set.iterator().next().getPictureName()))) {
            out.write(set.iterator().next().getPicture().getBytes(1,(int)set.iterator().next().getPicture().length()));
            String f = Base64.getEncoder().encodeToString(set.iterator().next().getPicture().getBytes(1,(int)set.iterator().next().getPicture().length()));
            System.out.println();
            System.out.println(f);


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
