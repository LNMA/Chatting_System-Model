package com.louay.projects.model.dao.impl;

import com.louay.projects.model.chains.communications.AccountTextPost;
import com.louay.projects.model.chains.communications.AccountMessage;
import com.louay.projects.model.chains.communications.AccountPicture;
import com.louay.projects.model.chains.member.FriendRequest;
import com.louay.projects.model.chains.member.UserFriend;
import com.louay.projects.model.chains.users.Admin;
import com.louay.projects.model.chains.users.Client;
import com.louay.projects.model.chains.users.Users;
import com.louay.projects.model.chains.users.activity.AccountStatus;
import com.louay.projects.model.chains.users.activity.SignInDate;
import com.louay.projects.model.dao.*;
import com.louay.projects.model.util.pool.ConnectionWrapper;
import com.louay.projects.model.util.pool.MyConnectionPool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.Collection;
import java.util.Map;

@Configuration
@Component("usersDAO")
@Scope("prototype")
@ComponentScan(basePackages = { "com.louay.projects.model"})
public class UsersDAOImpl implements CreateUsersDAO, InsertUserPostDAO, AccountStatusDAO, CirclesUsersDAO, UpdateUsersDAO,
        UpdateUserPostDAO, SelectUsersDAO, DeleteUserDAO {

    @Autowired
    @Qualifier("pool")
    private MyConnectionPool pool;
    @Autowired
    @Qualifier("buildAnnotationContext")
    private ApplicationContext ac;


    @Override
    public int insertUser(Users user) {
        int result = 0;
        try {
            result = this.pool.updateQuery("INSERT INTO `account`(`username`, `password`, `dateCreate`, `accountPermission`) " +
                    "VALUES(?, ?, ?, ?);", user.getUsername(), user.getPassword(), user.getDateCreate(), user.getAccountPermission());
        } catch (SQLIntegrityConstraintViolationException e) {
            return -404;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    @Override
    public int insertClientDetail(Users user) {
        int result = 0;
        if (user instanceof Client) {
            Client client = (Client) user;
            try {
                result = this.pool.updateQuery("INSERT INTO `account_detail`(`username`, `firstName`, `lastName`, " +
                                "`birthday`, `age`, `gender`, `telephone`, `email`, `country`, `state`, `address`) " +
                                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);", client.getUsername(), client.getFirstName(),
                        client.getLastName(), client.getBirthday(), client.getAge(), client.getGender(), client.getTelephone(),
                        client.getEmail(), client.getCountry(), client.getState(), client.getAddress());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            throw new RuntimeException("only client allow to insert its detail.");
        }
        return result;
    }

    @Override
    public Long insertAccountTextPost(AccountTextPost post) {
        try {
            ConnectionWrapper wrapper = this.pool.getConnection();
            PreparedStatement insert = wrapper.getConnection().prepareStatement("INSERT INTO `account_comments`(`username`" +
                    ", `comments`, `commentsDate`) VALUES (?, ?, ?);", Statement.RETURN_GENERATED_KEYS);
            insert.setString(1, post.getIdUsername());
            insert.setString(2, post.getPost().toString());
            insert.setTimestamp(3, post.getPostDate());
            insert.executeUpdate();

            ResultSet resultSet = insert.getGeneratedKeys();
            if (resultSet.next()) {
                post.setIdPost(resultSet.getLong(1));
            }
            this.pool.release(wrapper);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return post.getIdPost();
    }

    @Override
    public Long insertAccountMassage(AccountMessage message) {
        try {
            ConnectionWrapper wrapper = this.pool.getConnection();
            PreparedStatement insert = wrapper.getConnection().prepareStatement("INSERT INTO `account_message`(`source`" +
                    ", `massage`, `target`, `sentDate`, `isSeen`) VALUES (?, ?, ?, ?, ?);", Statement.RETURN_GENERATED_KEYS);
            insert.setString(1, message.getSourceUser());
            insert.setString(2, message.getMessage().toString());
            insert.setString(3, message.getTargetUser());
            insert.setTimestamp(4, message.getSentDate());
            insert.setBoolean(5, message.getSeen());
            insert.executeUpdate();

            ResultSet resultSet = insert.getGeneratedKeys();
            if (resultSet.next()) {
                message.setIdMessage(resultSet.getLong(1));
            }
            this.pool.release(wrapper);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return message.getIdMessage();
    }

    @Override
    public int insertAccountPicture(AccountPicture picture) {
        int result = 0;
        try {
            result = this.pool.updateQuery("INSERT INTO `account_pic`(`username`, `pic`,`picName`, `uploadDate`) " +
                    "VALUES (?, ?, ?, ?);", picture.getUsername(), picture.getPicture(), picture.getPictureName(),
                    picture.getUploadDate());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    @Override
    public int insertAccountStatus(AccountStatus status) {
        int result = 0;
        try {
            result = this.pool.updateQuery("INSERT INTO `account_status`(`username`, `isSignIn`, `isValid`) " +
                    "VALUES(?, ?, ?);", status.getUsername(), status.getSignIn(), status.getValid());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    @Override
    public int insertUserSignInDate(SignInDate signInDate) {
        int result = 0;
        try {
            result = this.pool.updateQuery("INSERT INTO `sign_in_date`(`username`, `signInDate`) VALUES (?, ?);",
                    signInDate.getUsername(), signInDate.getSignInDate());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    @Override
    public int insertFriendRequest(FriendRequest request) {
        int result = 0;
        try {
            result = this.pool.updateQuery("INSERT INTO `friend_request`(`username`, `requestTarget`, `requestDate`) " +
                    "VALUES (?, ?, ?);", request.getUsername(), request.getRequestTarget(), request.getRequestDate());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    @Override
    public int insertUserFriends(UserFriend friend) {
        int result = 0;
        try {
            result = this.pool.updateQuery("INSERT INTO `user_friend`(`username`, `friend`, `friendSince`) VALUES " +
                    "(?, ?, ?);", friend.getUsername(), friend.getFriend(), friend.getFriendSince());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    @Override
    public int updateUserByUsername(Users user) {
        int result = 0;
        try {
            result = this.pool.updateQuery("UPDATE `account` SET `password` = ?, `dateCreate` = ?, " +
                            "`accountPermission` = ? WHERE `username` = ?;", user.getPassword(), user.getDateCreate(),
                    user.getAccountPermission(), user.getUsername());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    @Override
    public int updateClientDetailByUsername(Users user) {
        int result = 0;
        if (user instanceof Client) {
            Client client = (Client) user;
            try {
                result = this.pool.updateQuery("UPDATE `account_detail` SET `firstName` = ?, `lastName` = ?, " +
                        "`birthday` = ?, `age` = ?, `gender` = ?, `telephone` = ?, `email` = ?, `country` = ?, " +
                        "`state` = ?, `address` = ? WHERE `username` = ?;", client.getFirstName(), client.getLastName(),
                        client.getBirthday(), client.getAge(), client.getGender(), client.getTelephone(),
                        client.getEmail(), client.getCountry(), client.getState(),client.getAddress(),
                        client.getUsername());
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        } else {
            throw new RuntimeException("only client allow access to this service.");
        }
        return result;
    }

    @Override
    public int updateAccountTextPostByIdComment(AccountTextPost post) {
        int result = 0;
        try {
            result = this.pool.updateQuery("UPDATE `account_comments` SET `username` = ?, `comments` = ?, " +
                            "`commentsDate` = ? WHERE `idComments` = ?;", post.getIdUsername(), post.getPost().toString(),
                    post.getPostDate(), post.getIdPost());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    @Override
    public int updateAccountMassageByIdMessage(AccountMessage message) {
        int result = 0;
        try {
            result = this.pool.updateQuery("UPDATE `account_message` SET `source` = ?, `massage` = ?, " +
                            "`target` = ?, `sentDate` = ?, `isSeen` = ? WHERE `idMessage` = ?;", message.getSourceUser(),
                    message.getMessage().toString(), message.getTargetUser(), message.getSentDate(), message.getSeen(),
                    message.getIdMessage());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    @Override
    public int updateAccountPictureByUsername(AccountPicture picture) {
        int result = 0;
        try {
            result = this.pool.updateQuery("UPDATE `account_pic` SET `pic` = ?, `picName` = ?,  " +
                            "`uploadDate` = ? WHERE `username` = ?;", picture.getPicture(),
                    picture.getPictureName(), picture.getUploadDate(), picture.getUsername());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    @Override
    public int updateAccountStatusByUsername(AccountStatus status) {
        int result = 0;
        try {
            result = this.pool.updateQuery("UPDATE `account_status` SET `isSignIn` = ?, `isValid` = ? WHERE " +
                    "`username` = ?;", status.getSignIn(), status.getValid(), status.getUsername());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    @Override
    public int updateFriendRequestByUsernameAndDate(FriendRequest request) {
        int result = 0;
        try {
            result = this.pool.updateQuery("UPDATE `friend_request` SET `requestTarget` = ? WHERE `requestDate` = ? " +
                    "AND `username` = ?;", request.getRequestTarget(), request.getRequestDate(), request.getUsername());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    @Override
    public int updateUserFriendsByUsernameAndDate(UserFriend friend) {
        int result = 0;
        try {
            result = this.pool.updateQuery("UPDATE `user_friend` SET `friend` = ? WHERE `friendSince` = ? AND " +
                    "`username` = ?;", friend.getFriend(), friend.getFriendSince(), friend.getUsername());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    private Users buildUser(ResultSet resultSet) {
        Users user = ac.getBean(Admin.class);
        try {
            user.setUsername(resultSet.getString(1));
            user.setPassword(resultSet.getString(2));
            user.setDateCreate(resultSet.getTimestamp(3));
            user.setAccountPermission(resultSet.getString(4));
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return user;
    }

    public void buildUserContainer(ResultSet resultSet, Collection<Users> container) {
        try {
            while (resultSet.next()) {
                container.add(buildUser(resultSet));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public Collection<Users> findUserInAccountByUsername(Users user) {
        @SuppressWarnings(value = "unchecked")
        Collection<Users> container = (Collection<Users>) this.ac.getBean("usersContainer");
        try {
            ResultSet resultSet = this.pool.selectResult("SELECT * FROM `account` WHERE `username` = ?;",
                    user.getUsername());
            buildUserContainer(resultSet, container);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return container;
    }

    @Override
    public Collection<Users> findUserByUsernameAndPassword(Users users) {
        @SuppressWarnings(value = "unchecked")
        Collection<Users> container = (Collection<Users>) this.ac.getBean("usersContainer");
        try {
            ResultSet resultSet = this.pool.selectResult("SELECT * FROM `account` WHERE `username` = ? AND " +
                            "`password` = ?;", users.getUsername(), users.getPassword());
            buildUserContainer(resultSet, container);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return container;    }

    private Client buildClient(ResultSet resultSet) {
        Client user = ac.getBean(Client.class);
        try {
            user.setUsername(resultSet.getString(1));
            user.setFirstName(resultSet.getString(2));
            user.setLastName(resultSet.getString(3));
            user.setBirthday(resultSet.getDate(4));
            user.setAge(resultSet.getString(5));
            user.setGender(resultSet.getString(6));
            user.setTelephone(resultSet.getString(7));
            user.setEmail(resultSet.getString(8));
            user.setCountry(resultSet.getString(9));
            user.setState(resultSet.getString(10));
            user.setAddress(resultSet.getString(11));
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return user;
    }

    public void buildClientContainer(ResultSet resultSet, Collection<Users> container) {
        try {
            while (resultSet.next()) {
                container.add(buildClient(resultSet));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public Collection<Users> findUserInAccountDetailByUsername(Users user) {
        @SuppressWarnings(value = "unchecked")
        Collection<Users> container = (Collection<Users>) this.ac.getBean("usersContainer");
        try {
            ResultSet resultSet = this.pool.selectResult("SELECT * FROM `account_detail` WHERE `username` = ?;",
                    user.getUsername());
            buildClientContainer(resultSet, container);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return container;
    }

    private AccountTextPost buildAccountTextPost(ResultSet resultSet) {
        AccountTextPost comments = ac.getBean(AccountTextPost.class);
        try {
            comments.setIdPost(resultSet.getLong(1));
            comments.setIdUsername(resultSet.getString(2));
            comments.setPost(resultSet.getString(3));
            comments.setPostDate(resultSet.getTimestamp(4));
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return comments;
    }

    public void buildAccountTextPostContainer(ResultSet resultSet, Collection<AccountTextPost> container) {
        try {
            while (resultSet.next()) {
                container.add(buildAccountTextPost(resultSet));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public Collection<AccountTextPost> findUserTextPostByIdPost(AccountTextPost post) {
        @SuppressWarnings(value = "unchecked")
        Collection<AccountTextPost> container = (Collection<AccountTextPost>) ac.getBean("accountTextPostContainer");
        try {
            ResultSet resultSet = this.pool.selectResult("SELECT * FROM `account_comments` WHERE `idComments` = ?;",
                    post.getIdPost());
            buildAccountTextPostContainer(resultSet, container);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return container;
    }

    @Override
    public Collection<AccountTextPost> findUserTextPostByUsername(AccountTextPost post) {
        @SuppressWarnings(value = "unchecked")
        Collection<AccountTextPost> container = (Collection<AccountTextPost>) ac.getBean("accountTextPostContainer");
        try {
            ResultSet resultSet = this.pool.selectResult("SELECT * FROM `account_comments` WHERE `username` = ? " +
                    "ORDER BY `account_comments`.`commentsDate` DESC;", post.getIdUsername());
            buildAccountTextPostContainer(resultSet, container);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return container;
    }

    private AccountMessage buildAccountMessage(ResultSet resultSet) {
        AccountMessage message = ac.getBean(AccountMessage.class);
        try {
            message.setIdMessage(resultSet.getLong(1));
            message.setSourceUser(resultSet.getString(2));
            message.setMessage(resultSet.getString(3));
            message.setTargetUser(resultSet.getString(4));
            message.setSentDate(resultSet.getTimestamp(5));
            message.setSeen(resultSet.getBoolean(6));
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return message;
    }

    public void buildAccountMessageContainer(ResultSet resultSet, Collection<AccountMessage> container) {
        try {
            while (resultSet.next()) {
                container.add(buildAccountMessage(resultSet));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public Collection<AccountMessage> findUserMessageByIdMessage(AccountMessage message) {
        @SuppressWarnings(value = "unchecked")
        Collection<AccountMessage> container = (Collection<AccountMessage>) ac.getBean("accountMessageContainer");
        try {
            ResultSet resultSet = this.pool.selectResult("SELECT * FROM `account_message` WHERE `idMessage` = ?;",
                    message.getIdMessage());
            buildAccountMessageContainer(resultSet, container);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return container;
    }

    @Override
    public Collection<AccountMessage> findUserMessageBySender(AccountMessage message) {
        @SuppressWarnings(value = "unchecked")
        Collection<AccountMessage> container = (Collection<AccountMessage>) ac.getBean("accountMessageContainer");
        try {
            ResultSet resultSet = this.pool.selectResult("SELECT * FROM `account_message` WHERE `source` = ? " +
                    "ORDER BY `account_message`.`sentDate` DESC;", message.getSourceUser());
            buildAccountMessageContainer(resultSet, container);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return container;
    }

    @Override
    public Collection<AccountMessage> findUserMessageByReceiver(AccountMessage message) {
        @SuppressWarnings(value = "unchecked")
        Collection<AccountMessage> container = (Collection<AccountMessage>) ac.getBean("accountMessageContainer");
        try {
            ResultSet resultSet = this.pool.selectResult("SELECT * FROM `account_message` WHERE `target` = ? " +
                    "ORDER BY `account_message`.`sentDate` DESC;", message.getTargetUser());
            buildAccountMessageContainer(resultSet, container);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return container;
    }

    private AccountPicture buildAccountPicture(ResultSet resultSet) {
        AccountPicture picture = ac.getBean(AccountPicture.class);
        try {
            picture.setUsername(resultSet.getString(1));
            picture.setPicture(resultSet.getBlob(2));
            picture.setPictureName(resultSet.getString(3));
            picture.setUploadDate(resultSet.getTimestamp(4));
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return picture;
    }

    public void buildAccountPicture(ResultSet resultSet, Collection<AccountPicture> container) {
        try {
            while (resultSet.next()) {
                container.add(buildAccountPicture(resultSet));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public Collection<AccountPicture> findPictureByUsername(AccountPicture picture) {
        @SuppressWarnings(value = "unchecked")
        Collection<AccountPicture> container = (Collection<AccountPicture>) ac.getBean("accountPictureContainer");
        try {
            ResultSet resultSet = this.pool.selectResult("SELECT * FROM `account_pic` WHERE `username` = ?;",
                                                            picture.getUsername());
            buildAccountPicture(resultSet, container);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return container;
    }

    @Override
    public Collection<AccountPicture> findFriendAndPictureByUsername(Users users) {
        @SuppressWarnings(value = "unchecked")
        Collection<AccountPicture> container = (Collection<AccountPicture>) ac.getBean("accountPictureContainer");
        try {
            ResultSet resultSet = this.pool.selectResult("SELECT `account_pic`.* FROM `account_pic` INNER JOIN `user_friend` ON " +
                            "`account_pic`.`username` = `user_friend`.`friend` WHERE `user_friend`.`username` = ?;"
                            , users.getUsername());
            buildAccountPicture(resultSet, container);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return container;
    }

    private AccountStatus buildAccountStatus(ResultSet resultSet) {
        AccountStatus status = ac.getBean(AccountStatus.class);
        try {
            status.setUsername(resultSet.getString(1));
            status.setSignIn(resultSet.getBoolean(2));
            status.setValid(resultSet.getBoolean(3));
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return status;
    }

    public void buildAccountStatusContainer(ResultSet resultSet, Collection<AccountStatus> container) {
        try {
            while (resultSet.next()) {
                container.add(buildAccountStatus(resultSet));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public Collection<AccountStatus> findUserStatusByUsername(AccountStatus status) {
        @SuppressWarnings(value = "unchecked")
        Collection<AccountStatus> container = (Collection<AccountStatus>) ac.getBean("statusContainer");
        try {
            ResultSet resultSet = this.pool.selectResult("SELECT * FROM `account_status` WHERE `username` = ?;",
                    status.getUsername());
            buildAccountStatusContainer(resultSet, container);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return container;
    }

    private FriendRequest buildFriendRequest(ResultSet resultSet) {
        FriendRequest request = ac.getBean(FriendRequest.class);
        try {
            request.setUsername(resultSet.getString(1));
            request.setRequestTarget(resultSet.getString(2));
            request.setRequestDate(resultSet.getTimestamp(3));
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return request;
    }

    public void buildFriendRequestContainer(ResultSet resultSet, Map<Long, FriendRequest> container) {
        long i = 0;
        try {
            while (resultSet.next()) {
                container.put(i, buildFriendRequest(resultSet));
                i++;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public Map<Long, FriendRequest> findFriendRequestBySender(FriendRequest request) {
        @SuppressWarnings(value = "unchecked")
        Map<Long, FriendRequest> container = (Map<Long, FriendRequest>) ac.getBean("userRequestContainer");
        try {
            ResultSet resultSet = this.pool.selectResult("SELECT * FROM `friend_request` WHERE `username` = ? " +
                    "ORDER BY `friend_request`.`requestDate` DESC;", request.getUsername());
            buildFriendRequestContainer(resultSet, container);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return container;
    }

    @Override
    public Map<Long, FriendRequest> findFriendRequestByReceiver(FriendRequest request) {
        @SuppressWarnings(value = "unchecked")
        Map<Long, FriendRequest> container = (Map<Long, FriendRequest>) ac.getBean("userRequestContainer");
        try {
            ResultSet resultSet = this.pool.selectResult("SELECT * FROM `friend_request` WHERE `requestTarget` = ? " +
                    "ORDER BY `friend_request`.`requestDate` DESC;", request.getRequestTarget());
            buildFriendRequestContainer(resultSet, container);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return container;
    }

    private SignInDate buildSignInDate(ResultSet resultSet) {
        SignInDate signInDate = ac.getBean(SignInDate.class);
        try {
            signInDate.setUsername(resultSet.getString(1));
            signInDate.setSignInDate(resultSet.getTimestamp(2));
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return signInDate;
    }

    public void buildSignInDateContainer(ResultSet resultSet, Map<Long, SignInDate> container) {
        long i = 0;
        try {
            while (resultSet.next()) {
                container.put(i, buildSignInDate(resultSet));
                i++;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public Map<Long, SignInDate> findSignInDateByUsername(SignInDate signInDate) {
        @SuppressWarnings(value = "unchecked")
        Map<Long, SignInDate> container = (Map<Long, SignInDate>) ac.getBean("signInDateContainer");
        try {
            ResultSet resultSet = this.pool.selectResult("SELECT * FROM `sign_in_date` WHERE `username` = ? " +
                    "ORDER BY `sign_in_date`.`signInDate` DESC;", signInDate.getUsername());
            buildSignInDateContainer(resultSet, container);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return container;
    }

    private UserFriend buildUserFriend(ResultSet resultSet) {
        UserFriend friend = ac.getBean(UserFriend.class);
        try {
            friend.setUsername(resultSet.getString(1));
            friend.setFriend(resultSet.getString(2));
            friend.setFriendSince(resultSet.getTimestamp(3));
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return friend;
    }

    public void buildUserFriendContainer(ResultSet resultSet, Map<Long, UserFriend> container) {
        long i = 0;
        try {
            while (resultSet.next()) {
                container.put(i, buildUserFriend(resultSet));
                i++;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public Map<Long, UserFriend> findUserFriendByUsername(UserFriend friend) {
        @SuppressWarnings(value = "unchecked")
        Map<Long, UserFriend> container = (Map<Long, UserFriend>) ac.getBean("userFriendsContainer");
        try {
            ResultSet resultSet = this.pool.selectResult("SELECT * FROM `user_friend` WHERE `username` = ? ORDER " +
                    "BY `user_friend`.`friendSince` DESC;", friend.getUsername());
            buildUserFriendContainer(resultSet, container);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return container;
    }

    @Override
    public Map<Long, UserFriend> findUserFriendByFriend(UserFriend friend) {
        @SuppressWarnings(value = "unchecked")
        Map<Long, UserFriend> container = (Map<Long, UserFriend>) ac.getBean("userFriendsContainer");
        try {
            ResultSet resultSet = this.pool.selectResult("SELECT * FROM `user_friend` WHERE `friend` = ? ORDER " +
                    "BY `user_friend`.`friendSince` DESC;", friend.getUsername());
            buildUserFriendContainer(resultSet, container);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return container;
    }



    @Override
    public int deleteAccountByUsername(Users user) {
        int result = 0;
        try {
            result = this.pool.updateQuery("DELETE FROM `account` WHERE `username` = ?;", user.getUsername());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    @Override
    public int deleteAccountDetailByUsername(Users user) {
        int result = 0;
        try {
            result = this.pool.updateQuery("DELETE FROM `account_detail` WHERE `username` = ?;", user.getUsername());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    @Override
    public int deleteAccountTextPostByIdPost(AccountTextPost post) {
        int result = 0;
        try {
            result = this.pool.updateQuery("DELETE FROM `account_comments` WHERE `idComments` = ?;",
                    post.getIdPost());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    @Override
    public int deleteAccountMessageByIdMessage(AccountMessage message) {
        int result = 0;
        try {
            result = this.pool.updateQuery("DELETE FROM `account_message` WHERE `idMessage` = ?;",
                    message.getIdMessage());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    @Override
    public int deleteAccountPictureByUsername(AccountPicture picture) {
        int result = 0;
        try {
            result = this.pool.updateQuery("DELETE FROM `account_pic` WHERE `username` = ?;", picture.getUsername());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    @Override
    public int deleteAccountStatusByUsername(AccountStatus status) {
        int result = 0;
        try {
            result = this.pool.updateQuery("DELETE FROM `account_status` WHERE `username` = ?;",
                    status.getUsername());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    @Override
    public int deleteSignInDateByUsername(SignInDate signInDate) {
        int result = 0;
        try {
            result = this.pool.updateQuery("DELETE FROM `sign_in_date` WHERE `username` = ?;",
                    signInDate.getUsername());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    @Override
    public int deleteSignInDateByUsernameAndDate(SignInDate signInDate) {
        int result = 0;
        try {
            result = this.pool.updateQuery("DELETE FROM `sign_in_date` WHERE `username` = ? AND `signInDate` = ?;",
                    signInDate.getUsername(), signInDate.getSignInDate());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    @Override
    public int deleteFriendRequestBySenderAndReceiver(FriendRequest request) {
        int result = 0;
        try {
            result = this.pool.updateQuery("DELETE FROM `friend_request` WHERE `username` = ? AND `requestTarget` = ?;",
                    request.getUsername(), request.getRequestTarget());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    @Override
    public int deleteFriendRequestBySenderAndDate(FriendRequest request) {
        int result = 0;
        try {
            result = this.pool.updateQuery("DELETE FROM `friend_request` WHERE `username` = ? AND `requestDate` = ?;",
                    request.getUsername(), request.getRequestDate());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    @Override
    public int deleteFriendRequestByReceiverAndDate(FriendRequest request) {
        int result = 0;
        try {
            result = this.pool.updateQuery("DELETE FROM `friend_request` WHERE `requestTarget` = ? AND " +
                    "`requestDate` = ?;", request.getUsername(), request.getRequestDate());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    @Override
    public int deleteUserFriendByUsernameAndFriend(UserFriend friend) {
        int result = 0;
        try {
            result = this.pool.updateQuery("DELETE FROM `user_friend` WHERE `username` = ? AND `friend` = ?;",
                    friend.getUsername(), friend.getFriend());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    @Override
    public int deleteUserFriendByUsernameAndDate(UserFriend friend) {
        int result = 0;
        try {
            result = this.pool.updateQuery("DELETE FROM `user_friend` WHERE `username` = ? AND `friendSince` = ?;",
                    friend.getUsername(), friend.getFriendSince());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    @Override
    public int deleteUserFriendByFriendAndDate(UserFriend friend) {
        int result = 0;
        try {
            result = this.pool.updateQuery("DELETE FROM `user_friend` WHERE `friend` = ? AND `friendSince` = ?;",
                    friend.getFriend(), friend.getFriendSince());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }


}
