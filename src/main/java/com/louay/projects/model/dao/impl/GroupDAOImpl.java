package com.louay.projects.model.dao.impl;

import com.louay.projects.model.chains.communications.group.GroupTextPost;
import com.louay.projects.model.chains.communications.group.GroupPicture;
import com.louay.projects.model.chains.groups.GroupsDetail;
import com.louay.projects.model.chains.member.group.GroupInvite;
import com.louay.projects.model.chains.member.group.GroupMembers;
import com.louay.projects.model.chains.member.group.GroupRequest;
import com.louay.projects.model.dao.*;
import com.louay.projects.model.util.pool.ConnectionWrapper;
import com.louay.projects.model.util.pool.MyConnectionPool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;
import java.util.Map;

@Configuration
@Component("groupDAO")
@Scope("prototype")
@ComponentScan(basePackages = { "com.louay.projects.model"})
public class GroupDAOImpl implements CreateGroupsDAO, InsertGroupPostDAO, CirclesGroupDAO, UpdateGroupDAO, UpdateGroupPostDAO,
        SelectGroupDAO, DeleteGroupDAO {

    @Autowired
    @Qualifier("pool")
    private MyConnectionPool pool;
    @Autowired
    @Qualifier("buildAnnotationContext")
    private ApplicationContext ac;

    @Override
    public int insertGroupDetail(GroupsDetail groupsDetail) {
        int result = 0;
        try {
            result = this.pool.updateQuery("INSERT INTO `group_detail`(`idGroup`, `groupPrivacy`, `groupCreateDate`, " +
                            "`groupActivity`) VALUES (?, ?, ?, ?);", groupsDetail.getIdGroup(), groupsDetail.getGroupCreateDate(),
                    groupsDetail.getGroupPrivacy(), groupsDetail.getGroupActivity());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    @Override
    public Long insertGroupTextPost(GroupTextPost post) {
        try {
            ConnectionWrapper wrapper = this.pool.getConnection();
            PreparedStatement insert = wrapper.getConnection().prepareStatement("INSERT INTO `group_post`(`idGroupe`, " +
                    "`username`, `post`, `postDate`) VALUES (?, ?, ?,?);", Statement.RETURN_GENERATED_KEYS);
            insert.setString(1, post.getIdGroup());
            insert.setString(2, post.getUsername());
            insert.setString(3, post.getPost().toString());
            insert.setTimestamp(4, post.getPostDate());
            insert.executeUpdate();

            ResultSet resultSet = insert.getGeneratedKeys();
            if (resultSet.next()) {
                post.setIdPost(resultSet.getLong(1));
            }
            this.pool.release(wrapper);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return post.getIdPost();
    }

    @Override
    public int insertGroupPicture(GroupPicture picture) {
        int result = 0;
        try {
            result = this.pool.updateQuery("INSERT INTO `group_pic`(`idGroup`, `pic`, `picName`, `uploadDate`) " +
                    "values (?, ?, ?, ?);", picture.getIdGroup(), picture.getPicture(), picture.getPictureName(),
                    picture.getUploadDate());
        } catch (SQLException e) {
            System.out.println(e.getMessage());

        }
        return result;
    }

    @Override
    public int insertGroupInvite(GroupInvite invite) {
        int result = 0;
        try {
            result = this.pool.updateQuery("INSERT INTO `group_invite`(`source`, `target`, `inviteDate`) VALUES " +
                    "(?, ? ,?);", invite.getSourceIdGroup(), invite.getTargetAccount(), invite.getInviteDate());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    @Override
    public int insertGroupMember(GroupMembers members) {
        int result = 0;
        try {
            result = this.pool.updateQuery("INSERT INTO `group_member`(`idGroup`, `member`, `joinDate`) VALUES " +
                    "(?, ?, ?);", members.getIdGroup(), members.getMember(), members.getJoinDate());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    @Override
    public int insertGroupRequest(GroupRequest request) {
        int result = 0;
        try {
            result = this.pool.updateQuery("INSERT INTO `group_request`(`idGroup`, `requestTarget`, `sentDate`) " +
                    "VALUES (?, ?, ?);", request.getIdGroup(), request.getRequestTarget(), request.getSentDate());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    @Override
    public int updateGroupDetailByIdGroup(GroupsDetail groupsDetail) {
        int result = 0;
        try {
            result = this.pool.updateQuery("UPDATE `group_detail` SET `groupPrivacy` = ?, `groupCreateDate` = ?, " +
                            "`groupActivity` = ? WHERE `idGroup` = ?;", groupsDetail.getGroupPrivacy(),
                    groupsDetail.getGroupCreateDate(), groupsDetail.getGroupActivity(), groupsDetail.getIdGroup());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    @Override
    public int updateGroupTextPostByIdComment(GroupTextPost post) {
        int result = 0;
        try {
            result = this.pool.updateQuery("UPDATE `group_post` SET `idGroupe` = ?, `username` = ?, " +
                            "`post` = ?, `postDate` = ? WHERE `idPost` = ?;", post.getIdGroup(), post.getUsername(),
                    post.getPost().toString(), post.getPostDate(), post.getIdPost());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    @Override
    public int updateGroupPictureByIdGroup(GroupPicture picture) {
        int result = 0;
        try {
            result = this.pool.updateQuery("UPDATE `group_pic` SET `pic` = ?, `picName` = ?, `uploadDate` = ? " +
                            "WHERE `idGroup` = ?;", picture.getPicture(),picture.getPictureName(), picture.getUploadDate(),
                    picture.getIdGroup());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    @Override
    public int updateGroupInviteByIdGroupAndDate(GroupInvite invite) {
        int result = 0;
        try {
            result = this.pool.updateQuery("UPDATE `group_invite` SET `target` = ? WHERE `source` = ? AND " +
                    "`inviteDate` = ?;", invite.getTargetAccount(), invite.getSourceIdGroup(), invite.getInviteDate());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    @Override
    public int updateGroupMemberByIdGroupAndDate(GroupMembers members) {
        int result = 0;
        try {
            result = this.pool.updateQuery("UPDATE `group_member` SET `member` = ? WHERE `idGroup` = ? AND " +
                    "`joinDate` = ?;", members.getMember(), members.getIdGroup(), members.getJoinDate());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    @Override
    public int updateGroupRequestByUsernameAndDate(GroupRequest request) {
        int result = 0;
        try {
            result = this.pool.updateQuery("UPDATE `group_request` SET `idGroup` = ? WHERE `requestTarget` = ? AND " +
                    "`sentDate` = ?;", request.getIdGroup(), request.getRequestTarget(), request.getSentDate());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    private GroupsDetail buildGroupDetail(ResultSet resultSet) {
        GroupsDetail group = ac.getBean(GroupsDetail.class);
        try {
            group.setIdGroup(resultSet.getString(1));
            group.setGroupPrivacy(resultSet.getString(2));
            group.setGroupCreateDate(resultSet.getTimestamp(3));
            group.setGroupActivity(resultSet.getString(4));
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return group;
    }

    public void buildGroupDetailContainer(ResultSet resultSet, Collection<GroupsDetail> container) {
        try {
            while (resultSet.next()) {
                container.add(buildGroupDetail(resultSet));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public Collection<GroupsDetail> findGroupDetailByIdGroup(GroupsDetail groupsDetail) {
        @SuppressWarnings(value = "unchecked")
        Collection<GroupsDetail> container = (Collection<GroupsDetail>) ac.getBean("groupDetailContainer");
        try {
            ResultSet resultSet = this.pool.selectResult("SELECT * FROM `group_detail` WHERE `idGroup` = ?;",
                    groupsDetail.getIdGroup());
            buildGroupDetailContainer(resultSet, container);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return container;
    }

    private GroupTextPost buildGroupTextPost(ResultSet resultSet) {
        GroupTextPost post = ac.getBean(GroupTextPost.class);
        try {
            post.setIdPost(resultSet.getLong(1));
            post.setIdGroup(resultSet.getString(2));
            post.setUsername(resultSet.getString(3));
            post.setPost(resultSet.getString(3));
            post.setPostDate(resultSet.getTimestamp(4));
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return post;
    }

    public void buildGroupTextPostContainer(ResultSet resultSet, Collection<GroupTextPost> container) {
        try {
            while (resultSet.next()) {
                container.add(buildGroupTextPost(resultSet));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public Collection<GroupTextPost> findGroupTextPostByIdPost(GroupTextPost post) {
        @SuppressWarnings(value = "unchecked")
        Collection<GroupTextPost> container = (Collection<GroupTextPost>) ac.getBean("groupCommentContainer");
        try {
            ResultSet resultSet = this.pool.selectResult("SELECT * FROM `group_post` WHERE `idPost` = ? " +
                    "ORDER BY `group_post`.`postDate` DESC;", post.getIdPost());
            buildGroupTextPostContainer(resultSet, container);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return container;
    }

    @Override
    public Collection<GroupTextPost> findGroupTextPostByIdGroup(GroupTextPost post) {
        @SuppressWarnings(value = "unchecked")
        Collection<GroupTextPost> container = (Collection<GroupTextPost>) ac.getBean("groupCommentContainer");
        try {
            ResultSet resultSet = this.pool.selectResult("SELECT * FROM `group_post` WHERE `idGroupe` = ? " +
                    "ORDER BY `group_post`.`postDate` DESC;", post.getIdGroup());
            buildGroupTextPostContainer(resultSet, container);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return container;
    }

    @Override
    public Collection<GroupTextPost> findGroupTextPostByUsername(GroupTextPost post) {
        @SuppressWarnings(value = "unchecked")
        Collection<GroupTextPost> container = (Collection<GroupTextPost>) ac.getBean("groupCommentContainer");
        try {
            ResultSet resultSet = this.pool.selectResult("SELECT * FROM `group_post` WHERE `username` = ? " +
                    "ORDER BY `group_post`.`postDate` DESC;", post.getUsername());
            buildGroupTextPostContainer(resultSet, container);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return container;
    }

    @Override
    public Collection<GroupTextPost> findGroupTextPostByUsernameAndIdGroup(GroupTextPost post) {
        @SuppressWarnings(value = "unchecked")
        Collection<GroupTextPost> container = (Collection<GroupTextPost>) ac.getBean("groupCommentContainer");
        try {
            ResultSet resultSet = this.pool.selectResult("SELECT * FROM `group_post` WHERE `username` = ? " +
                            "AND `idGroupe` = ? ORDER BY `group_post`.`postDate` DESC;", post.getUsername(),
                    post.getIdGroup());
            buildGroupTextPostContainer(resultSet, container);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return container;
    }

    private GroupInvite buildGroupInvite(ResultSet resultSet) {
        GroupInvite invite = ac.getBean(GroupInvite.class);
        try {
            invite.setSourceIdGroup(resultSet.getString(1));
            invite.setTargetAccount(resultSet.getString(2));
            invite.setInviteDate(resultSet.getTimestamp(3));
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return invite;
    }

    public void buildGroupInviteContainer(ResultSet resultSet, Map<Long, GroupInvite> container) {
        long i = 0;
        try {
            while (resultSet.next()) {
                container.put(i, buildGroupInvite(resultSet));
                i++;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public Map<Long, GroupInvite> findGroupInviteByIdGroup(GroupInvite invite) {
        @SuppressWarnings(value = "unchecked")
        Map<Long, GroupInvite> container = (Map<Long, GroupInvite>) ac.getBean("groupInviteContainer");
        try {
            ResultSet resultSet = this.pool.selectResult("SELECT * FROM `group_invite` WHERE `source` = ? ORDER BY " +
                    "`group_invite`.`inviteDate` DESC;", invite.getSourceIdGroup());
            buildGroupInviteContainer(resultSet, container);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return container;
    }

    @Override
    public Map<Long, GroupInvite> findGroupInviteByUsername(GroupInvite invite) {
        @SuppressWarnings(value = "unchecked")
        Map<Long, GroupInvite> container = (Map<Long, GroupInvite>) ac.getBean("groupInviteContainer");
        try {
            ResultSet resultSet = this.pool.selectResult("SELECT * FROM `group_invite` WHERE `target` = ? ORDER BY " +
                    "`group_invite`.`inviteDate` DESC;", invite.getTargetAccount());
            buildGroupInviteContainer(resultSet, container);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return container;
    }

    private GroupMembers buildGroupMembers(ResultSet resultSet) {
        GroupMembers member = ac.getBean(GroupMembers.class);
        try {
            member.setIdGroup(resultSet.getString(1));
            member.setMember(resultSet.getString(2));
            member.setJoinDate(resultSet.getTimestamp(3));
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return member;
    }

    public void buildGroupMemberContainer(ResultSet resultSet, Map<Long, GroupMembers> container) {
        long i = 0;
        try {
            while (resultSet.next()) {
                container.put(i, buildGroupMembers(resultSet));
                i++;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public Map<Long, GroupMembers> findGroupMemberByIdGroup(GroupMembers member) {
        @SuppressWarnings(value = "unchecked")
        Map<Long, GroupMembers> container = (Map<Long, GroupMembers>) ac.getBean("groupMembersContainer");
        try {
            ResultSet resultSet = this.pool.selectResult("SELECT * FROM `group_member` WHERE `idGroup` = ? ORDER " +
                    "BY `group_member`.`joinDate` DESC;", member.getIdGroup());
            buildGroupMemberContainer(resultSet, container);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return container;
    }

    @Override
    public Map<Long, GroupMembers> findGroupMemberByUsername(GroupMembers member) {
        @SuppressWarnings(value = "unchecked")
        Map<Long, GroupMembers> container = (Map<Long, GroupMembers>) ac.getBean("groupMembersContainer");
        try {
            ResultSet resultSet = this.pool.selectResult("SELECT * FROM `group_member` WHERE `member` = ? ORDER " +
                    "BY `group_member`.`joinDate` DESC;", member.getMember());
            buildGroupMemberContainer(resultSet, container);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return container;
    }

    private GroupPicture buildGroupPicture(ResultSet resultSet) {
        GroupPicture picture = ac.getBean(GroupPicture.class);
        try {
            picture.setIdGroup(resultSet.getString(1));
            picture.setPicture(resultSet.getBlob(2));
            picture.setPictureName(resultSet.getString(3));
            picture.setUploadDate(resultSet.getTimestamp(4));
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return picture;
    }

    public void buildBuildGroupPictureContainer(ResultSet resultSet, Collection<GroupPicture> container) {
        try {
            while (resultSet.next()) {
                container.add(buildGroupPicture(resultSet));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public Collection<GroupPicture> findGroupPictureByIdGroup(GroupPicture picture) {
        @SuppressWarnings(value = "unchecked")
        Collection<GroupPicture> container = (Collection<GroupPicture>) ac.getBean("groupPictureContainer");
        try {
            ResultSet resultSet = this.pool.selectResult("SELECT * FROM `group_pic` WHERE `idGroup` = ? ORDER BY " +
                    "`group_pic`.`uploadDate` DESC;", picture.getIdGroup());
            buildBuildGroupPictureContainer(resultSet, container);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return container;
    }

    private GroupRequest buildGroupRequest(ResultSet resultSet) {
        GroupRequest request = ac.getBean(GroupRequest.class);
        try {
            request.setIdGroup(resultSet.getString(1));
            request.setRequestTarget(resultSet.getString(2));
            request.setSentDate(resultSet.getTimestamp(3));
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return request;
    }

    public void buildGroupRequestContainer(ResultSet resultSet, Map<Long, GroupRequest> container) {
        long i = 0;
        try {
            while (resultSet.next()) {
                container.put(i, buildGroupRequest(resultSet));
                i++;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public Map<Long, GroupRequest> findGroupRequestByIdGroup(GroupRequest request) {
        @SuppressWarnings(value = "unchecked")
        Map<Long, GroupRequest> container = (Map<Long, GroupRequest>) ac.getBean("groupRequestContainer");
        try {
            ResultSet resultSet = this.pool.selectResult("SELECT * FROM `group_request` WHERE `idGroup` = ? ORDER " +
                    "BY `group_request`.`sentDate` DESC;", request.getIdGroup());
            buildGroupRequestContainer(resultSet, container);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return container;
    }

    @Override
    public Map<Long, GroupRequest> findGroupRequestByUsername(GroupRequest request) {
        @SuppressWarnings(value = "unchecked")
        Map<Long, GroupRequest> container = (Map<Long, GroupRequest>) ac.getBean("groupRequestContainer");
        try {
            ResultSet resultSet = this.pool.selectResult("SELECT * FROM `group_request` WHERE `requestTarget` = ? " +
                    "ORDER BY `group_request`.`sentDate` DESC;", request.getRequestTarget());
            buildGroupRequestContainer(resultSet, container);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return container;
    }

    @Override
    public int deleteGroupDetailByIdGroup(GroupsDetail groupsDetail) {
        int result = 0;
        try{
            result = this.pool.updateQuery("DELETE FROM `group_detail` WHERE `idGroup` = ?;",
                    groupsDetail.getIdGroup());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    @Override
    public int deleteGroupTextPostByIdPost(GroupTextPost post) {
        int result = 0;
        try {
            result = this.pool.updateQuery("DELETE FROM `group_post` WHERE `idPost` = ?;",
                    post.getIdPost());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    @Override
    public int deleteGroupInviteByIdGroupAndUsername(GroupInvite invite) {
        int result = 0;
        try {
            result = this.pool.updateQuery("DELETE FROM `group_invite` WHERE `source` = ? AND `target` = ? ;",
                    invite.getSourceIdGroup(), invite.getTargetAccount());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    @Override
    public int deleteGroupInviteByIdGroupAndDate(GroupInvite invite) {
        int result = 0;
        try {
            result = this.pool.updateQuery("DELETE FROM `group_invite` WHERE `source` = ? AND `inviteDate` = ? ;",
                    invite.getSourceIdGroup(), invite.getInviteDate());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    @Override
    public int deleteGroupInviteByUsernameAndDate(GroupInvite invite) {
        int result = 0;
        try {
            result = this.pool.updateQuery("DELETE FROM `group_invite` WHERE `target` = ? AND `inviteDate` = ? ;",
                    invite.getTargetAccount(), invite.getInviteDate());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    @Override
    public int deleteGroupMemberByIdGroupAndUsername(GroupMembers member) {
        int result = 0;
        try {
            result = this.pool.updateQuery("DELETE FROM `group_member` WHERE `idGroup` = ? AND `member` = ? ;",
                    member.getIdGroup(), member.getMember());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    @Override
    public int deleteGroupPictureByIdGroup(GroupPicture picture) {
        int result = 0;
        try {
            result = this.pool.updateQuery("DELETE FROM `group_pic` WHERE `idGroup` = ?;", picture.getIdGroup());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    @Override
    public int deleteGroupRequestByIdGroupAndUsername(GroupRequest request) {
        int result = 0;
        try {
            result = this.pool.updateQuery("DELETE FROM `group_request` WHERE `idGroup` = ? AND `requestTarget` = ?;",
                    request.getIdGroup(), request.getRequestTarget());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    @Override
    public int deleteGroupRequestByIdGroupAndDate(GroupRequest request) {
        int result = 0;
        try {
            result = this.pool.updateQuery("DELETE FROM `group_request` WHERE `idGroup` = ? AND `sentDate` = ?;",
                    request.getIdGroup(), request.getSentDate());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    @Override
    public int deleteGroupRequestByUsernameAndDate(GroupRequest request) {
        int result = 0;
        try {
            result = this.pool.updateQuery("DELETE FROM `group_request` WHERE `requestTarget` = ? AND `sentDate` = ?;",
                    request.getRequestTarget(), request.getSentDate());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }
}
