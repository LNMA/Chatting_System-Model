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
            result = this.pool.updateQuery("INSERT INTO `account`(`username`, `password`, `dateCreate`, " +
                    "`accountPermission`) VALUES(?, ?, ?, ?);", user.getUsername(), user.getPassword(),
                    user.getDateCreate(), user.getAccountPermission());
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
            insert.setString(1, textPost.getUser().getUsername());
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
            insert.setString(1, imgPost.getUser().getUsername());
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
            insert.setString(1, message.getSourceUser().getUsername());
            insert.setString(2, message.getMessage().toString());
            insert.setString(3, message.getTargetUser().getUsername());
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
                    "VALUES (?, ?, ?);", friendRequest.getSourceAccount().getUsername(),
                    friendRequest.getTargetAccount().getUsername(), request.getRequestDate());
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
                    "(?, ?, ?);", friendRequest.getUser().getUsername(), friendRequest.getFriendMember().getUsername(),
                    friend.getFriendMemberSince());
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
                            "`postDate` = ? WHERE `idPost` = ?;", textPost.getUser().getUsername(),
                    textPost.getPost().toString(), textPost.getDatePost(), textPost.getIdPost());
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
                            "`fileName` = ?, `dateUpload` = ?  WHERE `idPost` = ?;", imgPost.getUser().getUsername(),
                    imgPost.getImage(), imgPost.getFileName(), imgPost.getDatePost(), imgPost.getIdPost());
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
                            "`target` = ?, `sentDate` = ?, `isSeen` = ? WHERE `idMessage` = ?;",
                    message.getSourceUser().getUsername(), message.getMessage().toString(), message.getTargetUser().getUsername(),
                    message.getSentDate(), message.getSeen(), message.getIdMessage());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    @Override
    public int updateAccountMassageSeenBySenderAndReceiver(AccountMessage message) {
        int result = 0;
        try {
            result = this.pool.updateQuery("UPDATE `account_message` SET `isSeen` = ? WHERE `source` = ? " +
                            "AND `target` = ? ;", message.getSeen(), message.getSourceUser().getUsername(),
                    message.getTargetUser().getUsername());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return result;    }

    @Override
    public int updateAccountPictureByUsername(Users picture) {
        int result = 0;
        try {
            result = this.pool.updateQuery("UPDATE `account_pic` SET `pic` = ?, `picName` = ?,  " +
                            "`uploadDate` = ? WHERE `username` = ?;", picture.getPicture(), picture.getPictureName(),
                    picture.getDateCreate(), picture.getUsername());
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
                            "AND `username` = ?;", friendRequest.getTargetAccount().getUsername(), friendRequest.getRequestDate());
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
                            "`username` = ?;", userFriend.getFriendMember().getUsername(), userFriend.getFriendMemberSince(),
                    userFriend.getUser().getUsername());
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
            user.setFirstName(resultSet.getString(5));
            user.setLastName(resultSet.getString(6));
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
                    "`account_pic`.`pic`, `account_pic`.`picName`, `account_detail`.`firstName`, " +
                    "`account_detail`.`lastName` FROM `account` INNER JOIN `account_pic` ON " +
                    "`account`.`username` = `account_pic`.`username` INNER JOIN `account_detail` ON " +
                    "`account`.`username` = `account_detail`.`username` WHERE `account`.`accountPermission` = 'client' " +
                    "AND `account`.`username` LIKE ?;", (users.getUsername()+"%"));
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
            user.setDateCreate(resultSet.getTimestamp(2));
            user.setFirstName(resultSet.getString(3));
            user.setLastName(resultSet.getString(4));
            user.setBirthday(resultSet.getDate(5));
            user.setAge(resultSet.getString(6));
            user.setGender(resultSet.getString(7));
            user.setTelephone(resultSet.getString(8));
            user.setEmail(resultSet.getString(9));
            user.setCountry(resultSet.getString(10));
            user.setState(resultSet.getString(11));
            user.setAddress(resultSet.getString(12));
            user.setPicture(resultSet.getBlob(13));
            user.setPictureName(resultSet.getString(14));
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
            ResultSet resultSet = this.pool.selectResult("SELECT `account`.`username`, `account`.`dateCreate`, " +
                            "`account_detail`.`firstName`, `account_detail`.`lastName`, `account_detail`.`birthday`, " +
                            "`account_detail`.`age`, `account_detail`.`gender`, `account_detail`.`telephone`, " +
                            "`account_detail`.`email`, `account_detail`.`country`, `account_detail`.`state`, " +
                            "`account_detail`.`address`, `account_pic`.`pic`, `account_pic`.`picName` FROM `account` " +
                            "INNER JOIN `account_detail` ON `account_detail`.`username` = `account`.`username` " +
                            "INNER JOIN `account_pic` ON `account_pic`.`username` = `account`.`username` WHERE " +
                            "`account`.`accountPermission` = 'client' AND `account`.`username` = ?;", user.getUsername());
            buildClientContainer(resultSet, container);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return container;
    }

    private AccountTextPost buildAccountTextPost(ResultSet resultSet) {
        AccountTextPost post = ac.getBean(AccountTextPost.class);
        Users users = post.getUser();
        try {
            post.setIdPost(resultSet.getLong(1));
            users.setUsername(resultSet.getString(2));
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
                    textPost.getUser().getUsername());
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
                    textPost.getUser().getUsername());
            buildAccountTextPostContainer(resultSet, container);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return container;
    }

    private AccountImgPost buildAccountImgPost(ResultSet resultSet) {
        AccountImgPost post = ac.getBean(AccountImgPost.class);
        Users users = post.getUser();
        try {
            post.setIdPost(resultSet.getLong(1));
            users.setUsername(resultSet.getString(2));
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
                    imgPost.getUser().getUsername());
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
                    imgPost.getUser().getUsername());
            buildAccountImgPostContainer(resultSet, container);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return container;    }

    private AccountMessage buildAccountMessage(ResultSet resultSet) {
        AccountMessage message = ac.getBean(AccountMessage.class);
        Client sourceUser = message.getSourceUser();
        Client targetUser = message.getTargetUser();

        try {
            message.setIdMessage(resultSet.getLong(1));
            sourceUser.setUsername(resultSet.getString(2));
            message.setMessage(resultSet.getString(3));
            targetUser.setUsername(resultSet.getString(4));
            message.setSentDate(resultSet.getTimestamp(5));
            message.setSeen(resultSet.getBoolean(6));
            targetUser.setFirstName(resultSet.getString(7));
            targetUser.setLastName(resultSet.getString(8));
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
    public Collection<AccountMessage> findUserMessageBySenderAndReceiver(AccountMessage message) {
        @SuppressWarnings(value = "unchecked")
        Collection<AccountMessage> container = (Collection<AccountMessage>) ac.getBean("accountMessageContainer");
        try {
            ResultSet resultSet = this.pool.selectResult("SELECT `account_message`.`idMessage`, " +
                            "`account_message`.`source`, `account_message`.`massage`, `account_message`.`target`, " +
                            "`account_message`.`sentDate`, `account_message`.`isSeen`, `account_detail`.`firstName`, " +
                            "`account_detail`.`lastName` FROM `account_message` INNER JOIN `account_detail` " +
                            "ON `account_detail`.`username` = `account_message`.`source` WHERE " +
                            "`account_message`.`source` = ? AND `account_message`.`target` = ?;",
                             message.getSourceUser().getUsername(), message.getTargetUser().getUsername());

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
            ResultSet resultSet = this.pool.selectResult("SELECT `account_message`.`idMessage`, " +
            "`account_message`.`source`, `account_message`.`massage`, `account_message`.`target`, " +
                    "`account_message`.`sentDate`, `account_message`.`isSeen`, `account_detail`.`firstName`, " +
                    "`account_detail`.`lastName` FROM `account_message` INNER JOIN `account_detail` " +
                    "ON `account_detail`.`username` = `account_message`.`target` WHERE " +
                    "`account_message`.`target` = ?;", message.getTargetUser().getUsername());
            buildAccountMessageContainer(resultSet, container);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return container;
    }

    private AccountMessage buildAccountMessageAndPic(ResultSet resultSet) {
        AccountMessage message = ac.getBean(AccountMessage.class);
        Client sourceUser = message.getSourceUser();
        Client targetUser = message.getTargetUser();

        try {
            message.setIdMessage(resultSet.getLong(1));
            sourceUser.setUsername(resultSet.getString(2));
            message.setMessage(resultSet.getString(3));
            targetUser.setUsername(resultSet.getString(4));
            message.setSentDate(resultSet.getTimestamp(5));
            message.setSeen(resultSet.getBoolean(6));
            sourceUser.setPicture(resultSet.getBlob(7));
            sourceUser.setFirstName(resultSet.getString(8));
            sourceUser.setLastName(resultSet.getString(9));
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return message;
    }

    public void buildAccountMessageAndPicContainer(ResultSet resultSet, Collection<AccountMessage> container) {
        try {
            while (resultSet.next()) {
                container.add(buildAccountMessageAndPic(resultSet));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public Collection<AccountMessage> findUserMessageAndTargetPicBySenderAndReceiver(AccountMessage message) {
        @SuppressWarnings(value = "unchecked")
        Collection<AccountMessage> container = (Collection<AccountMessage>) ac.getBean("accountMessageContainer");
        try {
            ResultSet resultSet = this.pool.selectResult("SELECT `account_message`.`idMessage`, " +
                            "`account_message`.`source`, `account_message`.`massage`, `account_message`." +
                            "`target`, `account_message`.`sentDate`, `account_message`.`isSeen`,  `account_pic`.`pic` ," +
                            "`account_detail`.`firstName`, `account_detail`.`lastName` FROM `account_message` " +
                            "Inner join `account_pic` ON `account_message`.`source` = `account_pic`.`username` " +
                            "Inner join `account_detail` ON `account_message`.`source` = `account_detail`.`username` " +
                            "WHERE `account_message`.`source` = ? AND `account_message`.`target` = ?;",
                    message.getSourceUser().getUsername(), message.getTargetUser().getUsername());

            buildAccountMessageAndPicContainer(resultSet, container);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return container;
    }

    private AccountMessage buildAccountMessageAndNumNotSeen(ResultSet resultSet) {
        AccountMessage message = ac.getBean(AccountMessage.class);
        Client sourceUser = message.getSourceUser();
        Client targetUser = message.getTargetUser();

        try {
            message.setIdMessage(resultSet.getLong(1));
            sourceUser.setUsername(resultSet.getString(2));
            targetUser.setUsername(resultSet.getString(3));
            sourceUser.setFirstName(resultSet.getString(4));
            sourceUser.setLastName(resultSet.getString(5));
            message.setNumberOfAllMessage(resultSet.getInt(6));
            message.setNumberOfSeen(resultSet.getInt(7));
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return message;
    }

    public void buildAccountMessageAndNumNotSeenContainer(ResultSet resultSet, Collection<AccountMessage> container) {
        try {
            while (resultSet.next()) {
                container.add(buildAccountMessageAndNumNotSeen(resultSet));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public Collection<AccountMessage> findUserMessageAndNumNotSeenByReceiver(AccountMessage message) {
        @SuppressWarnings(value = "unchecked")
        Collection<AccountMessage> container = (Collection<AccountMessage>) ac.getBean("accountMessageContainer");
        try {
            ResultSet resultSet = this.pool.selectResult("SELECT `account_message`.`idMessage`, " +
                    "`account_message`.`source`, `account_message`.`target`, `account_detail`.`firstName`, " +
                    "`account_detail`.`lastName`, COUNT(*), SUM(`isSeen`)  FROM `account_message` INNER JOIN " +
                    "`account_detail` ON `account_detail`.`username` = `account_message`.`source` WHERE " +
                    "`account_message`.`target` = ? GROUP BY `account_message`.`source`",
                    message.getTargetUser().getUsername());
            buildAccountMessageAndNumNotSeenContainer(resultSet, container);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return container;
    }

    @Override
    public Collection<AccountMessage> findUserMessageAndNumNotSeenBySender(AccountMessage message) {
        @SuppressWarnings(value = "unchecked")
        Collection<AccountMessage> container = (Collection<AccountMessage>) ac.getBean("accountMessageContainer");
        try {
            ResultSet resultSet = this.pool.selectResult("SELECT `account_message`.`idMessage`," +
                    "`account_message`.`source`, `account_message`.`target`, `account_detail`.`firstName`, " +
                    "`account_detail`.`lastName`, COUNT(*), SUM(`isSeen`)  FROM `account_message` INNER JOIN " +
                    "`account_detail` ON `account_detail`.`username` = `account_message`.`target` WHERE " +
                    "`account_message`.`source` = ? GROUP BY `account_message`.`target`",
                    message.getSourceUser().getUsername());
            buildAccountMessageAndNumNotSeenContainer(resultSet, container);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return container;
    }

    @Override
    public Collection<AccountMessage> findUserMessageAndNumNotSeenBySenderAndReceiver(AccountMessage message) {
        @SuppressWarnings(value = "unchecked")
        Collection<AccountMessage> container = (Collection<AccountMessage>) ac.getBean("accountMessageContainer");
        try {
            ResultSet resultSet = this.pool.selectResult("SELECT `account_message`.`idMessage`, " +
                    "`account_message`.`source`, `account_message`.`target`, `account_detail`.`firstName`," +
                    "`account_detail`.`lastName`, COUNT(*), SUM(`isSeen`)  FROM `account_message` INNER JOIN " +
                    "`account_detail` ON `account_detail`.`username` = `account_message`.`target` WHERE " +
                    "`account_message`.`source` = ? AND `account_message`.`target` = ? GROUP BY `account_message`.`target`",
                    message.getSourceUser().getUsername(), message.getTargetUser().getUsername());
            buildAccountMessageAndNumNotSeenContainer(resultSet, container);
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
        Users sourceAccount = request.getSourceAccount();
        Users targetAccount = request.getTargetAccount();
        try {
            sourceAccount.setUsername(resultSet.getString(1));
            targetAccount.setUsername(resultSet.getString(2));
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
                    "ORDER BY `friend_request`.`requestDate` DESC;", friendRequest.getSourceAccount().getUsername());
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
                    "ORDER BY `friend_request`.`requestDate` DESC;", friendRequest.getTargetAccount().getUsername());
            buildFriendRequestContainer(resultSet, container);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return container;
    }

    @Override
    public Map<Long, FriendRequest> findFriendRequestBySenderAndReceiver(Request request) {
        if (!(request instanceof FriendRequest)) {
            throw new IllegalArgumentException("Need FriendRequest.");
        }
        FriendRequest friendRequest = (FriendRequest) request;
        @SuppressWarnings(value = "unchecked")
        Map<Long, FriendRequest> container = (Map<Long, FriendRequest>) ac.getBean("requestContainer");
        try {
            ResultSet resultSet = this.pool.selectResult("SELECT * FROM `friend_request` WHERE `username` = ? AND" +
                    "`requestTarget` = ? ORDER BY `friend_request`.`requestDate` DESC;",
                    friendRequest.getSourceAccount().getUsername(), friendRequest.getTargetAccount().getUsername());
            buildFriendRequestContainer(resultSet, container);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return container;
    }

    private FriendRequest buildFriendRequestAndPic(ResultSet resultSet) {
        FriendRequest request = ac.getBean(FriendRequest.class);
        Client sourceAccount = request.getSourceAccount();
        Client targetAccount = request.getTargetAccount();
        try {
            sourceAccount.setUsername(resultSet.getString(1));
            targetAccount.setUsername(resultSet.getString(2));
            request.setRequestDate(resultSet.getTimestamp(3));
            sourceAccount.setFirstName(resultSet.getString(4));
            sourceAccount.setLastName(resultSet.getString(5));
            sourceAccount.setPicture(resultSet.getBlob(6));
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return request;
    }

    public void buildFriendRequestAndPicContainer(ResultSet resultSet, Map<Long, FriendRequest> container) {
        long i = 0;
        try {
            while (resultSet.next()) {
                container.put(i, buildFriendRequestAndPic(resultSet));
                i++;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public Map<Long, FriendRequest> findFriendRequestAndPicByReceiver(Request request) {
        if (!(request instanceof FriendRequest)) {
            throw new IllegalArgumentException("Need FriendRequest.");
        }
        FriendRequest friendRequest = (FriendRequest) request;
        @SuppressWarnings(value = "unchecked")
        Map<Long, FriendRequest> container = (Map<Long, FriendRequest>) ac.getBean("requestContainer");
        try {
            ResultSet resultSet = this.pool.selectResult("SELECT `friend_request`.`username`, " +
                    "`friend_request`.`requestTarget`, `friend_request`.`requestDate`, " +
                    "`account_detail`.`firstName`, `account_detail`.`lastName`, `account_pic`.`pic` " +
                    "FROM `friend_request` INNER JOIN `account_detail` ON " +
                    "`account_detail`.`username` = `friend_request`.`username` INNER JOIN `account_pic` ON " +
                    "`account_pic`.`username` = `friend_request`.`username` WHERE `friend_request`.`requestTarget` = ?;",
                    friendRequest.getTargetAccount().getUsername());

            buildFriendRequestAndPicContainer(resultSet, container);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return container;
    }

    @Override
    public Map<Long, FriendRequest> findFriendRequestAndPicBySender(Request request) {
        if (!(request instanceof FriendRequest)) {
            throw new IllegalArgumentException("Need FriendRequest.");
        }
        FriendRequest friendRequest = (FriendRequest) request;
        @SuppressWarnings(value = "unchecked")
        Map<Long, FriendRequest> container = (Map<Long, FriendRequest>) ac.getBean("requestContainer");
        try {
            ResultSet resultSet = this.pool.selectResult("SELECT `friend_request`.`username`, " +
                            "`friend_request`.`requestTarget`, `friend_request`.`requestDate`, " +
                            "`account_detail`.`firstName`, `account_detail`.`lastName`, `account_pic`.`pic` " +
                            "FROM `friend_request` INNER JOIN `account_detail` ON " +
                            "`account_detail`.`username` = `friend_request`.`requestTarget` INNER JOIN `account_pic` ON " +
                            "`account_pic`.`username` = `friend_request`.`requestTarget` WHERE " +
                            "`friend_request`.`username` = ?;",friendRequest.getSourceAccount().getUsername());

            buildFriendRequestAndPicContainer(resultSet, container);
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
        Users users = friend.getUser();
        Users friendUser = friend.getFriendMember();
        try {
            users.setUsername(resultSet.getString(1));
            friendUser.setUsername(resultSet.getString(2));
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
                    "BY `user_friend`.`friendSince` DESC;", member.getUser().getUsername());
            buildUserFriendContainer(resultSet, container);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return container;
    }

    @Override
    public Map<Long, UserFriend> findUserFriendByUserAndFriend(Member friend){
        if (!(friend instanceof UserFriend)) {
            throw new IllegalArgumentException("Need Member.");
        }
        UserFriend member = (UserFriend) friend;
        @SuppressWarnings(value = "unchecked")
        Map<Long, UserFriend> container = (Map<Long, UserFriend>) ac.getBean("memberContainer");
        try {
            ResultSet resultSet = this.pool.selectResult("SELECT * FROM `user_friend` WHERE `username` = ? AND " +
                    "`friend` = ? ORDER BY `user_friend`.`friendSince` DESC;", member.getUser().getUsername(),
                    member.getFriendMember().getUsername());
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
                    "BY `user_friend`.`friendSince` DESC;", member.getFriendMember().getUsername());
            buildUserFriendContainer(resultSet, container);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return container;
    }

    private UserFriend buildUserFriendAndInfo(ResultSet resultSet) {
        UserFriend friend = ac.getBean(UserFriend.class);
        Client users = friend.getUser();
        Client friendUser = friend.getFriendMember();
        try {
            users.setUsername(resultSet.getString(1));
            friendUser.setUsername(resultSet.getString(2));
            friend.setFriendMemberSince(resultSet.getTimestamp(3));
            friendUser.setFirstName(resultSet.getString(4));
            friendUser.setLastName(resultSet.getString(5));
            friendUser.setPicture(resultSet.getBlob(6));
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return friend;
    }

    public void buildUserFriendAndInfoContainer(ResultSet resultSet, Map<Long, UserFriend> container) {
        long i = 0;
        try {
            while (resultSet.next()) {
                container.put(i, buildUserFriendAndInfo(resultSet));
                i++;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public Map<Long, UserFriend> findUserFriendAndInfoByUsername(Member friend) {
        if (!(friend instanceof UserFriend)) {
            throw new IllegalArgumentException("Need Member.");
        }
        UserFriend member = (UserFriend) friend;
        @SuppressWarnings(value = "unchecked")
        Map<Long, UserFriend> container = (Map<Long, UserFriend>) ac.getBean("memberContainer");
        try {
            ResultSet resultSet = this.pool.selectResult("SELECT `user_friend`.`username`, `user_friend`.`friend`, " +
                            "`user_friend`.`friendSince`, `account_detail`.`firstName`, `account_detail`.`lastName`, " +
                            "`account_pic`.`pic` FROM `user_friend` " +
                            "INNER JOIN `account_detail` ON `account_detail`.`username` = `user_friend`.`friend` " +
                            "INNER JOIN `account_pic` ON `account_pic`.`username` = `user_friend`.`friend` " +
                            "WHERE `user_friend`.`username` = ?;"
                            , member.getUser().getUsername());
            buildUserFriendAndInfoContainer(resultSet, container);
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
                    friendRequest.getSourceAccount().getUsername(), friendRequest.getTargetAccount().getUsername());
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
                    friendRequest.getSourceAccount().getUsername(), friendRequest.getRequestDate());
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
                    "`requestDate` = ?;", friendRequest.getTargetAccount().getUsername(), friendRequest.getRequestDate());
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
                    userFriend.getUser().getUsername(), userFriend.getFriendMember().getUsername());
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
                    userFriend.getUser().getUsername(), userFriend.getFriendMemberSince());
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
                    userFriend.getFriendMember().getUsername(), userFriend.getFriendMemberSince());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }
}
