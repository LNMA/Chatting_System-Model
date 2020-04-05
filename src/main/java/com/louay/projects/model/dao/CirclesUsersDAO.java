package com.louay.projects.model.dao;

import com.louay.projects.model.chains.member.FriendRequest;
import com.louay.projects.model.chains.member.UserFriend;

public interface CirclesUsersDAO {

    int insertFriendRequest(FriendRequest request);

    int insertUserFriends(UserFriend friend);

    int updateFriendRequestByUsernameAndDate(FriendRequest request);

    int updateUserFriendsByUsernameAndDate(UserFriend friend);
}
