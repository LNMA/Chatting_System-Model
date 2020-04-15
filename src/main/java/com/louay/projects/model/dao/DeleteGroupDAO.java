package com.louay.projects.model.dao;

import com.louay.projects.model.chains.communications.group.GroupImgPost;
import com.louay.projects.model.chains.communications.group.GroupTextPost;
import com.louay.projects.model.chains.communications.group.GroupPicture;
import com.louay.projects.model.chains.groups.GroupsDetail;
import com.louay.projects.model.chains.member.group.GroupInvite;
import com.louay.projects.model.chains.member.group.GroupMembers;
import com.louay.projects.model.chains.member.group.GroupRequest;

public interface DeleteGroupDAO {

    int deleteGroupDetailByIdGroup(GroupsDetail groupsDetail);

    int deleteGroupTextPostByIdPost(GroupTextPost post);

    int deleteGroupImgPostByIdPost(GroupImgPost post);

    int deleteGroupInviteByIdGroupAndUsername(GroupInvite invite);

    int deleteGroupInviteByIdGroupAndDate(GroupInvite invite);

    int deleteGroupInviteByUsernameAndDate(GroupInvite invite);

    int deleteGroupMemberByIdGroupAndUsername(GroupMembers member);

    int deleteGroupPictureByIdGroup(GroupPicture picture);

    int deleteGroupRequestByIdGroupAndUsername(GroupRequest request);

    int deleteGroupRequestByIdGroupAndDate(GroupRequest request);

    int deleteGroupRequestByUsernameAndDate(GroupRequest request);
}
