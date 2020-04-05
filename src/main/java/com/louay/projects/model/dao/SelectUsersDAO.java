package com.louay.projects.model.dao;

import com.louay.projects.model.chains.communications.AccountComments;
import com.louay.projects.model.chains.communications.AccountMessage;
import com.louay.projects.model.chains.communications.AccountPicture;
import com.louay.projects.model.chains.member.FriendRequest;
import com.louay.projects.model.chains.member.UserFriend;
import com.louay.projects.model.chains.users.Users;
import com.louay.projects.model.chains.users.activity.AccountStatus;
import com.louay.projects.model.chains.users.activity.SignInDate;

import java.util.Collection;
import java.util.Map;

public interface SelectUsersDAO {

    Collection<Users> findUserInAccountByUsername(Users user);

    Collection<Users> findUserInAccountDetailByUsername(Users user);

    Collection<AccountComments> findUserCommentByIdComment(AccountComments comment);

    Collection<AccountComments> findUserCommentByUsername(AccountComments comment);

    Collection<AccountMessage> findUserMessageByIdMessage(AccountMessage message);

    Collection<AccountMessage> findUserMessageBySender(AccountMessage message);

    Collection<AccountMessage> findUserMessageByReceiver(AccountMessage message);

    Collection<AccountPicture> findPictureByIdPicture(AccountPicture picture);

    Collection<AccountPicture> findPictureByUsername(AccountPicture picture);

    Collection<AccountStatus> findUserStatusByUsername(AccountStatus status);

    Map<Long, FriendRequest> findFriendRequestBySender(FriendRequest request);

    Map<Long, FriendRequest>  findFriendRequestByReceiver(FriendRequest request);

    Map<Long, SignInDate> findSignInDateByUsername(SignInDate signInDate);

    Map<Long ,UserFriend> findUserFriendByUsername(UserFriend friend);

    Map<Long ,UserFriend> findUserFriendByFriend(UserFriend friend);
}
