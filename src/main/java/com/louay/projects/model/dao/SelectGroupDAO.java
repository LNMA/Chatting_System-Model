package com.louay.projects.model.dao;

import com.louay.projects.model.chains.communications.Post;
import com.louay.projects.model.chains.communications.group.GroupImgPost;
import com.louay.projects.model.chains.communications.group.GroupTextPost;
import com.louay.projects.model.chains.communications.group.GroupPicture;
import com.louay.projects.model.chains.groups.GroupsDetail;
import com.louay.projects.model.chains.member.Member;
import com.louay.projects.model.chains.member.Request;
import com.louay.projects.model.chains.member.group.GroupInvite;
import com.louay.projects.model.chains.member.group.GroupMembers;
import com.louay.projects.model.chains.member.group.GroupRequest;

import java.util.Collection;
import java.util.Map;

public interface SelectGroupDAO {

    Collection<GroupsDetail> findGroupDetailByIdGroup(GroupsDetail groupsDetail);

    Collection<GroupTextPost> findGroupTextPostByIdPost(Post post);

    Collection<GroupTextPost> findGroupTextPostByIdGroup(Post post);

    Collection<GroupTextPost> findGroupTextPostByUsername(Post post);

    Collection<GroupTextPost> findGroupTextPostByUsernameAndIdGroup(Post post);

    Collection<GroupImgPost> findGroupImgPostByUsername(Post post);

    Collection<GroupImgPost> findGroupImgPostByIdPost(Post post);

    Map<Long, GroupInvite> findGroupInviteByIdGroup(GroupInvite invite);

    Map<Long, GroupInvite> findGroupInviteByUsername(GroupInvite invite);

    Map<Long, GroupMembers> findGroupMemberByIdGroup(Member member);

    Map<Long, GroupMembers> findGroupMemberByUsername(Member member);

    Collection<GroupPicture> findGroupPictureByIdGroup(GroupPicture picture);

    Map<Long, GroupRequest> findGroupRequestByIdGroup(Request request);

    Map<Long, GroupRequest> findGroupRequestByUsername(Request request);
}
