package com.louay.projects.model.dao;

import com.louay.projects.model.chains.communications.AccountTextPost;
import com.louay.projects.model.chains.communications.AccountMessage;
import com.louay.projects.model.chains.communications.AccountPicture;
import com.louay.projects.model.chains.member.FriendRequest;
import com.louay.projects.model.chains.member.UserFriend;
import com.louay.projects.model.chains.users.Users;
import com.louay.projects.model.chains.users.activity.AccountStatus;
import com.louay.projects.model.chains.users.activity.SignInDate;

public interface DeleteUserDAO {

    int deleteAccountByUsername(Users user);

    int deleteAccountDetailByUsername(Users user);

    int deleteAccountTextPostByIdPost(AccountTextPost post);

    int deleteAccountMessageByIdMessage(AccountMessage message);

    int deleteAccountPictureByUsername(AccountPicture picture);

    int deleteAccountStatusByUsername(AccountStatus status);

    int deleteSignInDateByUsername(SignInDate signInDate);

    int deleteSignInDateByUsernameAndDate(SignInDate signInDate);

    int deleteFriendRequestBySenderAndReceiver(FriendRequest request);

    int deleteFriendRequestBySenderAndDate(FriendRequest request);

    int deleteFriendRequestByReceiverAndDate(FriendRequest request);

    int deleteUserFriendByUsernameAndFriend(UserFriend friend);

    int deleteUserFriendByUsernameAndDate(UserFriend friend);

    int deleteUserFriendByFriendAndDate(UserFriend friend);

}
