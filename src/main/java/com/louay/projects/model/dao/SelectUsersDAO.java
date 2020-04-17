package com.louay.projects.model.dao;

import com.louay.projects.model.chains.communications.Post;
import com.louay.projects.model.chains.communications.account.AccountImgPost;
import com.louay.projects.model.chains.communications.account.AccountTextPost;
import com.louay.projects.model.chains.communications.account.AccountMessage;
import com.louay.projects.model.chains.communications.account.AccountPicture;
import com.louay.projects.model.chains.member.Member;
import com.louay.projects.model.chains.member.Request;
import com.louay.projects.model.chains.member.account.FriendRequest;
import com.louay.projects.model.chains.member.account.UserFriend;
import com.louay.projects.model.chains.users.Users;
import com.louay.projects.model.chains.users.activity.AccountStatus;
import com.louay.projects.model.chains.users.activity.SignInDate;

import java.util.Collection;
import java.util.Map;

public interface SelectUsersDAO {

    Collection<Users> findUserInAccountByUsername(Users user);

    Collection<Users> findUserInAccountDetailByUsername(Users user);

    Collection<Users> findUserByUsernameAndPassword(Users users);

    Collection<AccountTextPost> findUserTextPostByIdPost(Post post);

    Collection<AccountTextPost> findUserTextPostByUsername(Post post);

    Collection<AccountTextPost> findUserFriendTextPostByUsername(Post post);

    Collection<AccountImgPost> findUserImgPostByUsername(Post post);

    Collection<AccountImgPost> findUserImgPostByIdPost(Post post);

    Collection<AccountImgPost> findUserFriendImgPostByUsername(Post post);

    Collection<AccountMessage> findUserMessageByIdMessage(AccountMessage message);

    Collection<AccountMessage> findUserMessageBySender(AccountMessage message);

    Collection<AccountMessage> findUserMessageByReceiver(AccountMessage message);

    Collection<AccountPicture> findPictureByUsername(AccountPicture picture);

    Collection<AccountStatus> findUserStatusByUsername(AccountStatus status);

    Collection<AccountPicture> findFriendAndPictureByUsername(Users users);

    Map<Long, FriendRequest> findFriendRequestBySender(Request request);

    Map<Long, FriendRequest>  findFriendRequestByReceiver(Request request);

    Map<Long, SignInDate> findSignInDateByUsername(SignInDate signInDate);

    Map<Long ,UserFriend> findUserFriendByUsername(Member friend);

    Map<Long ,UserFriend> findUserFriendByFriend(Member friend);

}
