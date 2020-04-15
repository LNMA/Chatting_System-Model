package com.louay.projects.model.dao;

import com.louay.projects.model.chains.communications.group.GroupImgPost;
import com.louay.projects.model.chains.communications.group.GroupTextPost;
import com.louay.projects.model.chains.communications.group.GroupPicture;
import com.louay.projects.model.chains.groups.GroupsDetail;
import com.louay.projects.model.chains.member.group.GroupInvite;
import com.louay.projects.model.chains.member.group.GroupMembers;
import com.louay.projects.model.chains.member.group.GroupRequest;

import java.util.Collection;
import java.util.Map;

public interface SelectGroupDAO {

    Collection<GroupsDetail> findGroupDetailByIdGroup(GroupsDetail groupsDetail);

    Collection<GroupTextPost> findGroupTextPostByIdPost(GroupTextPost post);

    Collection<GroupTextPost> findGroupTextPostByIdGroup(GroupTextPost post);

    Collection<GroupTextPost> findGroupTextPostByUsername(GroupTextPost post);

    Collection<GroupTextPost> findGroupTextPostByUsernameAndIdGroup(GroupTextPost post);

    Collection<GroupImgPost> findGroupImgPostByUsername(GroupImgPost post);

    Map<Long, GroupInvite> findGroupInviteByIdGroup(GroupInvite invite);

    Map<Long, GroupInvite> findGroupInviteByUsername(GroupInvite invite);

    Map<Long, GroupMembers> findGroupMemberByIdGroup(GroupMembers member);

    Map<Long, GroupMembers> findGroupMemberByUsername(GroupMembers member);

    Collection<GroupPicture> findGroupPictureByIdGroup(GroupPicture picture);

    Map<Long, GroupRequest> findGroupRequestByIdGroup(GroupRequest request);

    Map<Long, GroupRequest> findGroupRequestByUsername(GroupRequest request);
}
