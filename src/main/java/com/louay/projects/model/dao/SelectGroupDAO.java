package com.louay.projects.model.dao;

import com.louay.projects.model.chains.communications.group.GroupComments;
import com.louay.projects.model.chains.communications.group.GroupPicture;
import com.louay.projects.model.chains.groups.GroupsDetail;
import com.louay.projects.model.chains.member.group.GroupInvite;
import com.louay.projects.model.chains.member.group.GroupMembers;
import com.louay.projects.model.chains.member.group.GroupRequest;

import java.util.Collection;
import java.util.Map;

public interface SelectGroupDAO {

    Collection<GroupsDetail> findGroupDetailByIdGroup(GroupsDetail groupsDetail);

    Collection<GroupComments> findGroupCommentsByIdComments(GroupComments comments);

    Collection<GroupComments> findGroupCommentsByIdGroup(GroupComments comments);

    Collection<GroupComments> findGroupCommentsByUsername(GroupComments comments);

    Collection<GroupComments> findGroupCommentsByUsernameAndIdGroup(GroupComments comments);

    Map<Long, GroupInvite> findGroupInviteByIdGroup(GroupInvite invite);

    Map<Long, GroupInvite> findGroupInviteByUsername(GroupInvite invite);

    Map<Long, GroupMembers> findGroupMemberByIdGroup(GroupMembers member);

    Map<Long, GroupMembers> findGroupMemberByUsername(GroupMembers member);

    Collection<GroupPicture> findGroupPictureByIdGroup(GroupPicture picture);

    Map<Long, GroupRequest> findGroupRequestByIdGroup(GroupRequest request);

    Map<Long, GroupRequest> findGroupRequestByUsername(GroupRequest request);
}
