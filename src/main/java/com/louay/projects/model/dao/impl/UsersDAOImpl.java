package com.louay.projects.model.dao.impl;

import com.louay.projects.model.chains.communications.Post;
import com.louay.projects.model.chains.communications.account.AccountImgPost;
import com.louay.projects.model.chains.communications.account.AccountTextPost;
import com.louay.projects.model.chains.communications.account.AccountMessage;
import com.louay.projects.model.chains.member.Member;
import com.louay.projects.model.chains.member.Request;
import com.louay.projects.model.chains.member.account.FriendRequest;
import com.louay.projects.model.chains.member.account.UserFriend;
import com.louay.projects.model.chains.accounts.Admin;
import com.louay.projects.model.chains.accounts.Client;
import com.louay.projects.model.chains.accounts.Users;
import com.louay.projects.model.chains.accounts.activity.AccountStatus;
import com.louay.projects.model.chains.accounts.activity.SignInDate;
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
@ComponentScan(basePackages = {"com.louay.projects.model"})
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
    public Long insertAccountTextPost(Post post) {
        if (!(post instanceof AccountTextPost)) {
            throw new IllegalStateException("Need AccountTextPost.");
        }
        AccountTextPost textPost = (AccountTextPost) post;
        try {
            ConnectionWrapper wrapper = this.pool.getConnection();
            PreparedStatement insert = wrapper.getConnection().prepareStatement("INSERT INTO `account_post`(`username`" +
                    ", `post`, `postDate`) VALUES (?, ?, ?);", Statement.RETURN_GENERATED_KEYS);
            insert.setString(1, textPost.getUsername());
            insert.setString(2, textPost.getPost().toString());
            insert.setTimestamp(3, textPost.getDatePost());
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
    public Long insertAccountImgPost(Post post) {
        if (!(post instanceof AccountImgPost)) {
            throw new IllegalStateException("Need AccountTextPost.");
        }
        AccountImgPost imgPost = (AccountImgPost) post;
        try {
            ConnectionWrapper wrapper = this.pool.getConnection();
            PreparedStatement insert = wrapper.getConnection().prepareStatement("INSERT INTO `account_img_post`" +
                    "(`username`, `img`, `fileName`, `dateUpload`) VALUES (?, ?, ?, ?);", Statement.RETURN_GENERATED_KEYS);
            insert.setString(1, imgPost.getUsername());
            insert.setBlob(2, imgPost.getImage());
            insert.setString(3, imgPost.getFileName());
            insert.setTimestamp(4, imgPost.getDatePost());
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
            PreparedStatement insert = wrapper.getConnection().prepareStatement("INSERT INTO `account_message`" +
                            "(`source`, `massage`, `target`, `sentDate`, `isSeen`) VALUES (?, ?, ?, ?, ?);",
                    Statement.RETURN_GENERATED_KEYS);
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
    public int insertAccountPicture(Users picture) {
        int result = 0;
        try {
            result = this.pool.updateQuery("INSERT INTO `account_pic`(`username`, `pic`,`picName`, `uploadDate`) " +
                            "VALUES (?, ?, ?, ?);", picture.getUsername(), picture.getPicture(), picture.getPictureName(),
                    picture.getDateCreate());
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
    public int insertFriendRequest(Request request) {
        if (!(request instanceof FriendRequest)) {
            throw new IllegalArgumentException("Need FriendRequest.");
        }
        FriendRequest friendRequest = (FriendRequest) request;
        int result = 0;
        try {
            result = this.pool.updateQuery("INSERT INTO `friend_request`(`username`, `requestTarget`, `requestDate`) " +
                    "VALUES (?, ?, ?);", friendRequest.getUsername(), friendRequest.getTargetAccount(), request.getRequestDate());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    @Override
    public int insertUserFriends(Member friend) {
        if (!(friend instanceof UserFriend)) {
            throw new IllegalArgumentException("Need FriendRequest.");
        }
        UserFriend friendRequest = (UserFriend) friend;
        int result = 0;
        try {
            result = this.pool.updateQuery("INSERT INTO `user_friend`(`username`, `friend`, `friendSince`) VALUES " +
                    "(?, ?, ?);", friendRequest.getUsername(), friendRequest.getFriendMember(), friend.getFriendMemberSince());
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
                        client.getEmail(), client.getCountry(), client.getState(), client.getAddress(),
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
    public int updateAccountTextPostByIdPost(Post post) {
        if (!(post instanceof AccountTextPost)) {
            throw new IllegalArgumentException("Need AccountTextPost.");
        }
        AccountTextPost textPost = (AccountTextPost) post;
        int result = 0;
        try {
            result = this.pool.updateQuery("UPDATE `account_post` SET `username` = ?, `post` = ?, " +
                            "`postDate` = ? WHERE `idPost` = ?;", textPost.getUsername(), textPost.getPost().toString(),
                    textPost.getDatePost(), textPost.getIdPost());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    @Override
    public int updateAccountImgPostByIdPost(Post post) {
        if (!(post instanceof AccountImgPost)) {
            throw new IllegalArgumentException("Need AccountImgPost.");
        }
        AccountImgPost imgPost = (AccountImgPost) post;
        int result = 0;
        try {
            result = this.pool.updateQuery("UPDATE `account_img_post` SET `username` = ?, `img` = ?, " +
                            "`fileName` = ?, `dateUpload` = ?  WHERE `idPost` = ?;", imgPost.getUsername(), imgPost.getImage(),
                    imgPost.getFileName(), imgPost.getDatePost(), imgPost.getIdPost());
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
    public int updateAccountPictureByUsername(Users picture) {
        int result = 0;
        try {
            result = this.pool.updateQuery("UPDATE `account_pic` SET `pic` = ?, `picName` = ?,  " +
                            "`uploadDate` = ? WHERE `username` = ?;", picture.getPicture(),
                    picture.getPictureName(), picture.getDateCreate(), picture.getUsername());
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
    public int updateFriendRequestByUsernameAndDate(Request request) {
        if (!(request instanceof FriendRequest)) {
            throw new IllegalArgumentException("Need FriendRequest.");
        }
        FriendRequest friendRequest = (FriendRequest) request;
        int result = 0;
        try {
            result = this.pool.updateQuery("UPDATE `friend_request` SET `requestTarget` = ? WHERE `requestDate` = ? " +
                            "AND `username` = ?;", friendRequest.getTargetAccount(), friendRequest.getRequestDate(),
                    friendRequest.getUsername());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    @Override
    public int updateUserFriendsByUsernameAndDate(Member friend) {
        if (!(friend instanceof UserFriend)) {
            throw new IllegalArgumentException("Need UserFriend.");
        }
        UserFriend userFriend = (UserFriend) friend;
        int result = 0;
        try {
            result = this.pool.updateQuery("UPDATE `user_friend` SET `friend` = ? WHERE `friendSince` = ? AND " +
                            "`username` = ?;", userFriend.getFriendMember(), userFriend.getFriendMemberSince(),
                    userFriend.getUsername());
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

    private Client buildClientSearchPurpose(ResultSet resultSet) {
        Client user = ac.getBean(Client.class);
        try {
            user.setUsername(resultSet.getString(1));
            user.setDateCreate(resultSet.getTimestamp(2));
            user.setPicture(resultSet.getBlob(3));
            user.setPictureName(resultSet.getString(4));
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return user;
    }

    public void buildClientSearchPurposeContainer(ResultSet resultSet, Collection<Users> container) {
        try {
            while (resultSet.next()) {
                container.add(buildClientSearchPurpose(resultSet));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


    @Override
    public Collection<Users> findUserInAccountByLikeUsername(Users users) {
        @SuppressWarnings(value = "unchecked")
        Collection<Users> container = (Collection<Users>) this.ac.getBean("usersContainer");
        try {
            ResultSet resultSet = this.pool.selectResult("SELECT `account`.`username`, `account`.`dateCreate`, " +
                            "`account_pic`.`pic`, `account_pic`.`picName` FROM `account` INNER JOIN `account_pic` ON " +
                            "`account`.`username` = `account_pic`.`username` WHERE `account`.`accountPermission` = 'client'" +
                            " AND `account`.`username` LIKE ?;", (users.getUsername()+"%"));
            buildClientSearchPurposeContainer(resultSet, container);
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
        return container;
    }

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
        AccountTextPost post = ac.getBean(AccountTextPost.class);
        try {
            post.setIdPost(resultSet.getLong(1));
            post.setUsername(resultSet.getString(2));
            post.setPost(resultSet.getString(3));
            post.setDatePost(resultSet.getTimestamp(4));
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return post;
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
    public Collection<AccountTextPost> findUserTextPostByIdPost(Post post) {
        @SuppressWarnings(value = "unchecked")
        Collection<AccountTextPost> container = (Collection<AccountTextPost>) ac.getBean("postContainer");
        try {
            ResultSet resultSet = this.pool.selectResult("SELECT * FROM `account_post` WHERE `idPost` = ?;",
                    post.getIdPost());
            buildAccountTextPostContainer(resultSet, container);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return container;
    }

    @Override
    public Collection<AccountTextPost> findUserTextPostByUsername(Post post) {
        if (!(post instanceof AccountTextPost)) {
            throw new IllegalArgumentException("Need AccountTextPost.");
        }
        AccountTextPost textPost = (AccountTextPost) post;
        @SuppressWarnings(value = "unchecked")
        Collection<AccountTextPost> container = (Collection<AccountTextPost>) ac.getBean("postContainer");
        try {
            ResultSet resultSet = this.pool.selectResult("SELECT * FROM `account_post` WHERE `username` = ?;",
                    textPost.getUsername());
            buildAccountTextPostContainer(resultSet, container);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return container;
    }

    @Override
    public Collection<AccountTextPost> findUserFriendTextPostByUsername(Post post) {
        if (!(post instanceof AccountTextPost)) {
            throw new IllegalArgumentException("Need AccountTextPost.");
        }
        AccountTextPost textPost = (AccountTextPost) post;
        @SuppressWarnings(value = "unchecked")
        Collection<AccountTextPost> container = (Collection<AccountTextPost>) ac.getBean("postContainer");
        try {
            ResultSet resultSet = this.pool.selectResult("SELECT `account_post`.`idPost`, `account_post`.`username`, " +
                    "`account_post`.`post`, `account_post`.`postDate` FROM `account_post` INNER JOIN `user_friend` ON " +
                    "`account_post`.`username` = `user_friend`.`friend` WHERE `user_friend`.`username` = ?;",
                    textPost.getUsername());
            buildAccountTextPostContainer(resultSet, container);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return container;
    }

    private AccountImgPost buildAccountImgPost(ResultSet resultSet) {
        AccountImgPost post = ac.getBean(AccountImgPost.class);
        try {
            post.setIdPost(resultSet.getLong(1));
            post.setUsername(resultSet.getString(2));
            post.setImage(resultSet.getBlob(3));
            post.setFileName(resultSet.getString(4));
            post.setDatePost(resultSet.getTimestamp(5));
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return post;
    }

    public void buildAccountImgPostContainer(ResultSet resultSet, Collection<AccountImgPost> container) {
        try {
            while (resultSet.next()) {
                container.add(buildAccountImgPost(resultSet));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public Collection<AccountImgPost> findUserImgPostByIdPost(Post post) {
        if (!(post instanceof AccountImgPost)) {
            throw new IllegalArgumentException("Need AccountImgPost.");
        }
        AccountImgPost imgPost = (AccountImgPost) post;
        @SuppressWarnings(value = "unchecked")
        Collection<AccountImgPost> container = (Collection<AccountImgPost>) ac.getBean("postContainer");
        try {
            ResultSet resultSet = this.pool.selectResult("SELECT * FROM `account_img_post` WHERE `idPost` = ?;",
                    imgPost.getIdPost());
            buildAccountImgPostContainer(resultSet, container);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return container;
    }

    @Override
    public Collection<AccountImgPost> findUserImgPostByUsername(Post post) {
        if (!(post instanceof AccountImgPost)) {
            throw new IllegalArgumentException("Need AccountImgPost.");
        }
        AccountImgPost imgPost = (AccountImgPost) post;
        @SuppressWarnings(value = "unchecked")
        Collection<AccountImgPost> container = (Collection<AccountImgPost>) ac.getBean("postContainer");
        try {
            ResultSet resultSet = this.pool.selectResult("SELECT * FROM `account_img_post` WHERE `username` = ?;",
                    imgPost.getUsername());
            buildAccountImgPostContainer(resultSet, container);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return container;
    }

    @Override
    public Collection<AccountImgPost> findUserFriendImgPostByUsername(Post post) {
        if (!(post instanceof AccountImgPost)) {
            throw new IllegalArgumentException("Need AccountImgPost.");
        }
        AccountImgPost imgPost = (AccountImgPost) post;
        @SuppressWarnings(value = "unchecked")
        Collection<AccountImgPost> container = (Collection<AccountImgPost>) ac.getBean("postContainer");
        try {
            ResultSet resultSet = this.pool.selectResult("SELECT `account_img_post`.`idPost`, " +
                    "`account_img_post`.`username`, `account_img_post`.`img`, `account_img_post`.`fileName`, " +
                    "`account_img_post`.`dateUpload` FROM `account_img_post` INNER JOIN `user_friend` ON " +
                    "`account_img_post`.`username` = `user_friend`.`friend` WHERE `user_friend`.`username` = ?;",
                    imgPost.getUsername());
            buildAccountImgPostContainer(resultSet, container);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return container;    }

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

    private Users buildAccountPicture(ResultSet resultSet) {
        Users picture = ac.getBean(Admin.class);
        try {
            picture.setUsername(resultSet.getString(1));
            picture.setPicture(resultSet.getBlob(2));
            picture.setPictureName(resultSet.getString(3));
            picture.setDateCreate(resultSet.getTimestamp(4));
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return picture;
    }

    public void buildAccountPicture(ResultSet resultSet, Collection<Users> container) {
        try {
            while (resultSet.next()) {
                container.add(buildAccountPicture(resultSet));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public Collection<Users> findPictureByUsername(Users picture) {
        @SuppressWarnings(value = "unchecked")
        Collection<Users> container = (Collection<Users>) ac.getBean("usersContainer");
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
    public Collection<Users> findFriendAndPictureByUsername(Users users) {
        @SuppressWarnings(value = "unchecked")
        Collection<Users> container = (Collection<Users>) ac.getBean("usersContainer");
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
            request.setTargetAccount(resultSet.getString(2));
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
    public Map<Long, FriendRequest> findFriendRequestBySender(Request request) {
        if (!(request instanceof FriendRequest)) {
            throw new IllegalArgumentException("Need FriendRequest.");
        }
        FriendRequest friendRequest = (FriendRequest) request;
        @SuppressWarnings(value = "unchecked")
        Map<Long, FriendRequest> container = (Map<Long, FriendRequest>) ac.getBean("requestContainer");
        try {
            ResultSet resultSet = this.pool.selectResult("SELECT * FROM `friend_request` WHERE `username` = ? " +
                    "ORDER BY `friend_request`.`requestDate` DESC;", friendRequest.getUsername());
            buildFriendRequestContainer(resultSet, container);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return container;
    }

    @Override
    public Map<Long, FriendRequest> findFriendRequestByReceiver(Request request) {
        if (!(request instanceof FriendRequest)) {
            throw new IllegalArgumentException("Need FriendRequest.");
        }
        FriendRequest friendRequest = (FriendRequest) request;
        @SuppressWarnings(value = "unchecked")
        Map<Long, FriendRequest> container = (Map<Long, FriendRequest>) ac.getBean("requestContainer");
        try {
            ResultSet resultSet = this.pool.selectResult("SELECT * FROM `friend_request` WHERE `requestTarget` = ? " +
                    "ORDER BY `friend_request`.`requestDate` DESC;", friendRequest.getTargetAccount());
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
            friend.setFriendMember(resultSet.getString(2));
            friend.setFriendMemberSince(resultSet.getTimestamp(3));
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
    public Map<Long, UserFriend> findUserFriendByUsername(Member friend) {
        if (!(friend instanceof UserFriend)) {
            throw new IllegalArgumentException("Need Member.");
        }
        UserFriend member = (UserFriend) friend;
        @SuppressWarnings(value = "unchecked")
        Map<Long, UserFriend> container = (Map<Long, UserFriend>) ac.getBean("memberContainer");
        try {
            ResultSet resultSet = this.pool.selectResult("SELECT * FROM `user_friend` WHERE `username` = ? ORDER " +
                    "BY `user_friend`.`friendSince` DESC;", member.getUsername());
            buildUserFriendContainer(resultSet, container);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return container;
    }

    @Override
    public Map<Long, UserFriend> findUserFriendByFriend(Member friend) {
        if (!(friend instanceof UserFriend)) {
            throw new IllegalArgumentException("Need Member.");
        }
        UserFriend member = (UserFriend) friend;
        @SuppressWarnings(value = "unchecked")
        Map<Long, UserFriend> container = (Map<Long, UserFriend>) ac.getBean("memberContainer");
        try {
            ResultSet resultSet = this.pool.selectResult("SELECT * FROM `user_friend` WHERE `friend` = ? ORDER " +
                    "BY `user_friend`.`friendSince` DESC;", member.getUsername());
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
    public int deleteAccountTextPostByIdPost(Post post) {
        int result = 0;
        try {
            result = this.pool.updateQuery("DELETE FROM `account_post` WHERE `idPost` = ?;",
                    post.getIdPost());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    @Override
    public int deleteAccountImgPostByIdPost(Post post) {
        int result = 0;
        try {
            result = this.pool.updateQuery("DELETE FROM `account_img_post` WHERE `idPost` = ?;",
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
    public int deleteAccountPictureByUsername(Users picture) {
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
    public int deleteFriendRequestBySenderAndReceiver(Request request) {
        if (!(request instanceof FriendRequest)) {
            throw new IllegalArgumentException("Need FriendRequest.");
        }
        FriendRequest friendRequest = (FriendRequest) request;
        int result = 0;
        try {
            result = this.pool.updateQuery("DELETE FROM `friend_request` WHERE `username` = ? AND `requestTarget` = ?;",
                    friendRequest.getUsername(), friendRequest.getTargetAccount());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    @Override
    public int deleteFriendRequestBySenderAndDate(Request request) {
        if (!(request instanceof FriendRequest)) {
            throw new IllegalArgumentException("Need FriendRequest.");
        }
        FriendRequest friendRequest = (FriendRequest) request;
        int result = 0;
        try {
            result = this.pool.updateQuery("DELETE FROM `friend_request` WHERE `username` = ? AND `requestDate` = ?;",
                    friendRequest.getUsername(), friendRequest.getRequestDate());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    @Override
    public int deleteFriendRequestByReceiverAndDate(Request request) {
        if (!(request instanceof FriendRequest)) {
            throw new IllegalArgumentException("Need FriendRequest.");
        }
        FriendRequest friendRequest = (FriendRequest) request;
        int result = 0;
        try {
            result = this.pool.updateQuery("DELETE FROM `friend_request` WHERE `requestTarget` = ? AND " +
                    "`requestDate` = ?;", friendRequest.getUsername(), friendRequest.getRequestDate());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    @Override
    public int deleteUserFriendByUsernameAndFriend(Member friend) {
        if (!(friend instanceof UserFriend)) {
            throw new IllegalArgumentException("Need UserFriend.");
        }
        UserFriend userFriend = (UserFriend) friend;
        int result = 0;
        try {
            result = this.pool.updateQuery("DELETE FROM `user_friend` WHERE `username` = ? AND `friend` = ?;",
                    userFriend.getUsername(), userFriend.getFriendMember());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    @Override
    public int deleteUserFriendByUsernameAndDate(Member friend) {
        if (!(friend instanceof UserFriend)) {
            throw new IllegalArgumentException("Need UserFriend.");
        }
        UserFriend userFriend = (UserFriend) friend;
        int result = 0;
        try {
            result = this.pool.updateQuery("DELETE FROM `user_friend` WHERE `username` = ? AND `friendSince` = ?;",
                    userFriend.getUsername(), userFriend.getFriendMember());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    @Override
    public int deleteUserFriendByFriendAndDate(Member friend) {
        if (!(friend instanceof UserFriend)) {
            throw new IllegalArgumentException("Need UserFriend.");
        }
        UserFriend userFriend = (UserFriend) friend;
        int result = 0;
        try {
            result = this.pool.updateQuery("DELETE FROM `user_friend` WHERE `friend` = ? AND `friendSince` = ?;",
                    userFriend.getFriendMember(), userFriend.getFriendMemberSince());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }
}
