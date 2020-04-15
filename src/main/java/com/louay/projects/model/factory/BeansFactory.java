package com.louay.projects.model.factory;


import com.louay.projects.model.chains.communications.AccountImgPost;
import com.louay.projects.model.chains.communications.AccountTextPost;
import com.louay.projects.model.chains.communications.AccountMessage;
import com.louay.projects.model.chains.communications.AccountPicture;
import com.louay.projects.model.chains.communications.group.GroupImgPost;
import com.louay.projects.model.chains.communications.group.GroupTextPost;
import com.louay.projects.model.chains.communications.group.GroupPicture;
import com.louay.projects.model.chains.groups.GroupsDetail;
import com.louay.projects.model.chains.member.FriendRequest;
import com.louay.projects.model.chains.member.UserFriend;
import com.louay.projects.model.chains.member.group.GroupInvite;
import com.louay.projects.model.chains.member.group.GroupMembers;
import com.louay.projects.model.chains.member.group.GroupRequest;
import com.louay.projects.model.chains.users.Users;
import com.louay.projects.model.chains.users.activity.AccountStatus;
import com.louay.projects.model.chains.users.activity.SignInDate;
import com.louay.projects.model.chains.util.PictureBase64;
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

    @Bean(name = "userFriendsContainer")
    @Scope("prototype")
    public Map<Long ,UserFriend> getUserFriendsContainer(){
        return new LinkedHashMap<>();
    }

    @Bean(name = "userRequestContainer")
    @Scope("prototype")
    public Map<Long, FriendRequest> getUserRequestContainer(){
        return new LinkedHashMap<>();
    }

    @Bean(name = "accountPictureContainer")
    @Scope("prototype")
    public Collection<AccountPicture> getAccountPictureContainer(){
        return new HashSet<>();
    }

    @Bean(name = "accountMessageContainer")
    @Scope("prototype")
    public Collection<AccountMessage> getAccountMessageContainer(){
        return new LinkedHashSet<>();
    }

    @Bean(name = "accountTextPostContainer")
    @Scope("prototype")
    public Collection<AccountTextPost> getAccountTextPostContainer(){
        return new LinkedHashSet<>();
    }

    @Bean(name = "accountImgPostContainer")
    @Scope("prototype")
    public Collection<AccountImgPost> getAccountImgPostContainer(){
        return new LinkedHashSet<>();
    }

    @Bean(name = "groupInviteContainer")
    @Scope("prototype")
    public Map<Long, GroupInvite> getGroupInviteContainer(){
        return new LinkedHashMap<>();
    }

    @Bean(name = "groupMembersContainer")
    @Scope("prototype")
    public Map<Long, GroupMembers> getGroupMembersContainer(){
        return new LinkedHashMap<>();
    }

    @Bean(name = "groupRequestContainer")
    @Scope("prototype")
    public Map<Long, GroupRequest> getGroupRequestContainer(){
        return new LinkedHashMap<>();
    }

    @Bean(name = "groupDetailContainer")
    @Scope("prototype")
    public Collection<GroupsDetail> getGroupDetailContainer(){
        return new HashSet<>();
    }

    @Bean(name = "groupTextPostContainer")
    @Scope("prototype")
    public Collection<GroupTextPost> getGroupCommentContainer(){
        return new LinkedHashSet<>();
    }

    @Bean(name = "groupImgPostContainer")
    @Scope("prototype")
    public Collection<GroupImgPost> getGroupImgPostContainer(){
        return new LinkedHashSet<>();
    }

    @Bean(name = "groupPictureContainer")
    @Scope("prototype")
    public Collection<GroupPicture> getGroupPictureContainer(){
        return new LinkedHashSet<>();
    }

    @Bean(name = "friendUserImgList")
    @Scope("prototype")
    public List<PictureBase64> getPicturePathImgList(){
        return new ArrayList<>();
    }







}
