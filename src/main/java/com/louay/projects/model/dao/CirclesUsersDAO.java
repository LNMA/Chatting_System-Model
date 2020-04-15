package com.louay.projects.model.dao;

import com.louay.projects.model.chains.member.Member;
import com.louay.projects.model.chains.member.Request;

public interface CirclesUsersDAO {

    int insertFriendRequest(Request request);

    int insertUserFriends(Member friend);

    int updateFriendRequestByUsernameAndDate(Request request);

    int updateUserFriendsByUsernameAndDate(Member friend);
}
