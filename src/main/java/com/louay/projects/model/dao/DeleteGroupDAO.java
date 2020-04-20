package com.louay.projects.model.dao;

import com.louay.projects.model.chains.accounts.group.Groups;
import com.louay.projects.model.chains.communications.Post;
import com.louay.projects.model.chains.member.Member;
import com.louay.projects.model.chains.member.Request;
import com.louay.projects.model.chains.member.group.GroupInvite;

public interface DeleteGroupDAO {

    int deleteGroupDetailByIdGroup(Groups groups);

    int deleteGroupTextPostByIdPost(Post post);

    int deleteGroupImgPostByIdPost(Post post);

    int deleteGroupInviteByIdGroupAndUsername(GroupInvite invite);

    int deleteGroupInviteByIdGroupAndDate(GroupInvite invite);

    int deleteGroupInviteByUsernameAndDate(GroupInvite invite);

    int deleteGroupMemberByIdGroupAndUsername(Member member);

    int deleteGroupPictureByIdGroup(Groups picture);

    int deleteGroupRequestByIdGroupAndUsername(Request request);

    int deleteGroupRequestByIdGroupAndDate(Request request);

    int deleteGroupRequestByUsernameAndDate(Request request);
}
