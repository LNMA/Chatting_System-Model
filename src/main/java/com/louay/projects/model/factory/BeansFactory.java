package com.louay.projects.model.factory;


import com.louay.projects.model.chains.accounts.group.Groups;
import com.louay.projects.model.chains.communications.Post;
import com.louay.projects.model.chains.communications.account.AccountMessage;
import com.louay.projects.model.chains.member.Member;
import com.louay.projects.model.chains.member.Request;
import com.louay.projects.model.chains.member.group.GroupInvite;
import com.louay.projects.model.chains.accounts.Users;
import com.louay.projects.model.chains.accounts.activity.AccountStatus;
import com.louay.projects.model.chains.accounts.activity.SignInDate;
import com.louay.projects.model.util.pool.ConnectionWrapper;
import com.louay.projects.model.util.pool.DBConnectionConfig;
import com.louay.projects.model.util.queue.MyList;
import com.louay.projects.model.util.queue.MyQueue;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.*;


@Configuration
public class BeansFactory {

    @Bean(name = "queue")
    @Scope("prototype")
    public MyList<ConnectionWrapper> getMyQueue() {
        return new MyQueue<>(10);
    }

    @Bean(name = "dbConnectionWrapper")
    @Scope("prototype")
    public ConnectionWrapper getConnectionWrapper() {
        DBConnectionConfig db = new DBConnectionConfig();
        db.setDriver("jdbc:mysql");
        db.setHost("localhost");
        db.setPort("3306");
        db.setSchema("chatting_system?useSSL=false");
        db.setUsername("root");
        db.setPassword("1729384#General");

        Connection connection = null;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            connection = DriverManager.getConnection(db.getUrl(), db.getUsername(), db.getPassword());
        } catch (ClassNotFoundException | SQLException | IllegalAccessException | InstantiationException e) {
            System.out.println(e.getMessage());
        }

        return new ConnectionWrapper(connection);
    }

    @Bean(name = "buildAnnotationContext")
    @Scope("prototype")
    public ApplicationContext buildContext(){
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.scan("com.louay.projects.model");
        context.refresh();
        return context;
    }

    @Bean(name = "buildAnnotationContextControl")
    @Scope("prototype")
    public ApplicationContext buildControlContext(){
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.scan("com.louay.projects.model", "com.louay.projects.controller");
        context.refresh();
        return context;
    }

    @Bean(name = "usersContainer")
    @Scope("prototype")
    public Collection<Users> getUsersContainer(){
        return new HashSet<>();
    }

    @Bean(name = "postContainer")
    @Scope("prototype")
    public Collection<Post> getPostContainer(){
        return new HashSet<>();
    }

    @Bean(name = "memberContainer")
    @Scope("prototype")
    public Map<Long, Member> getMemberContainer(){
        return new LinkedHashMap<>();
    }

    @Bean(name = "requestContainer")
    @Scope("prototype")
    public Map<Long, Request> getRequestContainer(){
        return new LinkedHashMap<>();
    }

    @Bean(name = "statusContainer")
    @Scope("prototype")
    public Collection<AccountStatus> getStatusContainer(){
        return new HashSet<>();
    }

    @Bean(name = "signInDateContainer")
    @Scope("prototype")
    public Map<Long, SignInDate> getSignInDateContainer(){
        return new LinkedHashMap<>();
    }

    @Bean(name = "accountMessageContainer")
    @Scope("prototype")
    public Collection<AccountMessage> getAccountMessageContainer(){
        return new LinkedHashSet<>();
    }

    @Bean(name = "groupInviteContainer")
    @Scope("prototype")
    public Map<Long, GroupInvite> getGroupInviteContainer(){
        return new LinkedHashMap<>();
    }

    @Bean(name = "groupContainer")
    @Scope("prototype")
    public Collection<Groups> getGroupContainer(){
        return new HashSet<>();
    }








}
