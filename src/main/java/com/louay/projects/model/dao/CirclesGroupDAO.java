package com.louay.projects.model.dao;

import com.louay.projects.model.chains.member.Member;
import com.louay.projects.model.chains.member.Request;
import com.louay.projects.model.chains.member.group.GroupInvite;

public interface CirclesGroupDAO {

    int insertGroupInvite(GroupInvite invite);

    int insertGroupMember(Member members);

    int insertGroupRequest(Request request);

    int updateGroupInviteByIdGroupAndDate(GroupInvite invite);

    int updateGroupMemberByIdGroupAndDate(Member members);

    int updateGroupRequestByUsernameAndDate(Request request);
}
