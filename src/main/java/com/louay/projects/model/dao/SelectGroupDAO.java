package com.louay.projects.model.dao;

import com.louay.projects.model.chains.accounts.Users;
import com.louay.projects.model.chains.communications.Post;
import com.louay.projects.model.chains.communications.group.GroupImgPost;
import com.louay.projects.model.chains.communications.group.GroupTextPost;
import com.louay.projects.model.chains.accounts.group.Groups;
import com.louay.projects.model.chains.member.Member;
import com.louay.projects.model.chains.member.Request;
import com.louay.projects.model.chains.member.group.GroupInvite;
import com.louay.projects.model.chains.member.group.GroupMembers;
import com.louay.projects.model.chains.member.group.GroupRequest;

import java.util.Collection;
import java.util.Map;

public interface SelectGroupDAO {

    Collection<Groups> findGroupDetailByIdGroup(Groups groups);

    Collection<Groups> findGroupDetailByLikeIdGroup(Groups groups);

    Collection<GroupTextPost> findGroupTextPostByIdPost(Post post);

    Collection<GroupTextPost> findGroupTextPostByIdGroup(Post post);

    Collection<GroupTextPost> findGroupTextPostByUsername(Post post);

    Collection<GroupTextPost> findGroupTextPostByUsernameAndIdGroup(Post post);

    Collection<GroupTextPost> findGroupTextPostAndUserInfoByIdGroup(Post groupTextPost);

    Collection<GroupImgPost> findGroupImgPostByUsername(Post post);

    Collection<GroupImgPost> findGroupImgPostByIdPost(Post post);

    Collection<GroupImgPost> findGroupImgPostAndUserInfoByIdGroup(Post groupImgPost);

    Map<Long, GroupInvite> findGroupInviteByIdGroup(GroupInvite invite);

    Map<Long, GroupInvite> findGroupInviteByUsername(GroupInvite invite);

    Map<Long, GroupInvite> findGroupInviteByUsernameAndIdGroup(GroupInvite invite);

    Map<Long, GroupInvite> findGroupInviteAndTargetInfoByUsername(GroupInvite invite);

    Map<Long, GroupInvite> findGroupInviteAndTargetInfoByIdGroup(GroupInvite invite);

    Map<Long, GroupInvite> findGroupInviteAndGroupPicByUsername(GroupInvite invite, long startKey);

    Map<Long, GroupMembers> findGroupMemberByIdGroup(Member member);

    Map<Long, GroupMembers> findGroupMemberByUsername(Member member);

    Map<Long, GroupMembers>  findGroupMemberAndInfoByIdGroup(Groups groups);

    Map<Long, GroupMembers>  findGroupAndPicAndUserByUsername(Users users);

    Map<Long, GroupMembers> findGroupMemberByUsernameAndIdGroup(Member groupMembers);

    Collection <Groups> findGroupPictureByIdGroup(Groups picture);

    Map<Long, GroupRequest> findGroupRequestByIdGroup(Request request);

    Map<Long, GroupRequest> findGroupRequestByUsername(Request request);

    Map<Long, GroupRequest> findGroupRequestByUsernameAndIdGroup(Request groupRequest);

    Map<Long, GroupRequest> findGroupRequestAndInfoByUsername(Request groupRequest);

    Map<Long, GroupRequest> findGroupRequestAndInfoByIdGroup(Request groupRequest);
}
