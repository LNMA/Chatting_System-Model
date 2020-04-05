package com.louay.projects.model.dao;

import com.louay.projects.model.chains.member.group.GroupInvite;
import com.louay.projects.model.chains.member.group.GroupMembers;
import com.louay.projects.model.chains.member.group.GroupRequest;

public interface CirclesGroupDAO {

    int insertGroupInvite(GroupInvite invite);

    int insertGroupMember(GroupMembers members);

    int insertGroupRequest(GroupRequest request);

    int updateGroupInviteByIdGroupAndDate(GroupInvite invite);

    int updateGroupMemberByIdGroupAndDate(GroupMembers members);

    int updateGroupRequestByUsernameAndDate(GroupRequest request);
}
