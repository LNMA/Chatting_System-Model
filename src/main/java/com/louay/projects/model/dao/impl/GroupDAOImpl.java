package com.louay.projects.model.dao.impl;

import com.louay.projects.model.chains.accounts.Client;
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


import java.sql.*;
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
    public int insertGroupDetail(Groups groups) {
        int result = 0;
        try {
            result = this.pool.updateQuery("INSERT INTO `group_detail`(`idGroup`, `groupPrivacy`, `groupCreateDate`, " +
                            "`groupActivity`) VALUES (?, ?, ?, ?);", groups.getIdGroup(), groups.getGroupPrivacy(),
                    groups.getDateCreate(),groups.getGroupActivity());
        } catch (SQLIntegrityConstraintViolationException e) {
            return -404;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    @Override
    public Long insertGroupTextPost(Post groupPost) {
        if (!(groupPost instanceof GroupTextPost)){
            throw new IllegalArgumentException("Need GroupTextPost.");
        }
        GroupTextPost post = (GroupTextPost) groupPost;
        try {
            ConnectionWrapper wrapper = this.pool.getConnection();
            PreparedStatement insert = wrapper.getConnection().prepareStatement("INSERT INTO `group_post`(`idGroupe`, " +
                    "`username`, `post`, `postDate`) VALUES (?, ?, ?,?);", Statement.RETURN_GENERATED_KEYS);
            insert.setString(1, post.getGroups().getIdGroup());
            insert.setString(2, post.getUser().getUsername());
            insert.setString(3, post.getPost().toString());
            insert.setTimestamp(4, post.getDatePost());
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
    public Long insertGroupImgPost(Post GroupImgPost) {
        if (!(GroupImgPost instanceof GroupImgPost)){
            throw new IllegalArgumentException("Need GroupImgPost.");
        }
        GroupImgPost post = (GroupImgPost) GroupImgPost;
        try {
            ConnectionWrapper wrapper = this.pool.getConnection();
            PreparedStatement insert = wrapper.getConnection().prepareStatement("INSERT INTO `group_img_post`(`idGroup`, " +
                    "`username`, `img`, `fileName`, `dateUpload`) VALUES (?, ?, ?, ?, ?);", Statement.RETURN_GENERATED_KEYS);
            insert.setString(1, post.getGroups().getIdGroup());
            insert.setString(2, post.getUser().getUsername());
            insert.setBlob(3, post.getImage());
            insert.setString(4, post.getFileName());
            insert.setTimestamp(5, post.getDatePost());
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
    public int insertGroupPicture(Groups picture) {
        int result = 0;
        try {
            result = this.pool.updateQuery("INSERT INTO `group_pic`(`idGroup`, `pic`, `picName`, `uploadDate`) " +
                    "values (?, ?, ?, ?);", picture.getIdGroup(), picture.getPicture(), picture.getPictureName(),
                    picture.getDateCreate());
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
                    "(?, ? ,?);", invite.getSourceGroup().getIdGroup(), invite.getTargetAccount().getUsername(),
                    invite.getRequestDate());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    @Override
    public int insertGroupMember(Member groupMembers) {
        if (!(groupMembers instanceof GroupMembers)){
            throw new IllegalArgumentException("Need GroupMembers.");
        }
        GroupMembers members = (GroupMembers) groupMembers;
        int result = 0;
        try {
            result = this.pool.updateQuery("INSERT INTO `group_member`(`idGroup`, `member`, `memberType`, `joinDate`) " +
                            "VALUES (?, ?, ?, ?);", members.getGroup().getIdGroup(), members.getFriendMember().getUsername(),
                             members.getGroupMemberType(), members.getFriendMemberSince());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    @Override
    public int insertGroupRequest(Request groupRequest) {
        if (!(groupRequest instanceof GroupRequest)){
            throw new IllegalArgumentException("Need GroupRequest.");
        }
        GroupRequest request = (GroupRequest) groupRequest;
        int result = 0;
        try {
            result = this.pool.updateQuery("INSERT INTO `group_request`(`idGroup`, `requestTarget`, `sentDate`) " +
                    "VALUES (?, ?, ?);", request.getSourceGroup().getIdGroup(), request.getTargetAccount().getUsername(),
                    request.getRequestDate());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    @Override
    public int updateGroupDetailByIdGroup(Groups groups) {
        int result = 0;
        try {
            result = this.pool.updateQuery("UPDATE `group_detail` SET `groupPrivacy` = ?, `groupCreateDate` = ?, " +
                            "`groupActivity` = ? WHERE `idGroup` = ?;", groups.getGroupPrivacy(),
                    groups.getDateCreate(), groups.getGroupActivity(), groups.getIdGroup());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    @Override
    public int updateGroupTextPostByIdPost(Post groupTextPost) {
        if (!(groupTextPost instanceof GroupTextPost)){
            throw new IllegalArgumentException("Need GroupTextPost.");
        }
        GroupTextPost post = (GroupTextPost) groupTextPost;
        int result = 0;
        try {
            result = this.pool.updateQuery("UPDATE `group_post` SET `idGroupe` = ?, `username` = ?, " +
                            "`post` = ?, `postDate` = ? WHERE `idPost` = ?;", post.getGroups().getIdGroup(),
                    post.getUser().getUsername(), post.getPost().toString(), post.getDatePost(), post.getIdPost());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    @Override
    public int updateGroupImgPostByIdPost(Post groupImgPost) {
        if (!(groupImgPost instanceof GroupImgPost)){
            throw new IllegalArgumentException("Need GroupImgPost.");
        }
        GroupImgPost post = (GroupImgPost) groupImgPost;
        int result = 0;
        try {
            result = this.pool.updateQuery("UPDATE `group_img_post` SET `idGroup` = ?, `username` = ?, " +
                            "`img` = ?, `fileName` = ?, `dateUpload` = ?  WHERE `idPost` = ?;", post.getGroups().getIdGroup()
                    , post.getUser().getUsername(), post.getImage(), post.getFileName(), post.getDatePost(), post.getIdPost());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    @Override
    public int updateGroupPictureByIdGroup(Groups picture) {
        int result = 0;
        try {
            result = this.pool.updateQuery("UPDATE `group_pic` SET `pic` = ?, `picName` = ?, `uploadDate` = ? " +
                            "WHERE `idGroup` = ?;", picture.getPicture(),picture.getPictureName(), picture.getDateCreate(),
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
                    "`inviteDate` = ?;", invite.getTargetAccount().getUsername(), invite.getSourceGroup().getIdGroup(),
                    invite.getRequestDate());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    @Override
    public int updateGroupMemberByIdGroupAndDate(Member groupMembers) {
        if (!(groupMembers instanceof GroupMembers)){
            throw new IllegalArgumentException("Need GroupMembers.");
        }
        GroupMembers members = (GroupMembers) groupMembers;
        int result = 0;
        try {
            result = this.pool.updateQuery("UPDATE `group_member` SET `member` = ? WHERE `idGroup` = ? AND " +
                    "`joinDate` = ?;", members.getFriendMember().getUsername(), members.getGroup().getIdGroup(),
                    members.getFriendMemberSince());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    @Override
    public int updateGroupRequestByUsernameAndDate(Request groupRequest) {
        if (!(groupRequest instanceof GroupRequest)){
            throw new IllegalArgumentException("Need GroupRequest.");
        }
        GroupRequest request = (GroupRequest) groupRequest;
        int result = 0;
        try {
            result = this.pool.updateQuery("UPDATE `group_request` SET `idGroup` = ? WHERE `requestTarget` = ? AND " +
                    "`sentDate` = ?;", request.getSourceGroup().getIdGroup(), request.getTargetAccount().getUsername(),
                    request.getRequestDate());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    private Groups buildGroupDetail(ResultSet resultSet) {
        Groups group = ac.getBean(Groups.class);
        try {
            group.setIdGroup(resultSet.getString(1));
            group.setGroupPrivacy(resultSet.getString(2));
            group.setDateCreate(resultSet.getTimestamp(3));
            group.setGroupActivity(resultSet.getString(4));
            group.setPicture(resultSet.getBlob(5));
            group.setPictureName(resultSet.getString(6));
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return group;
    }

    public void buildGroupDetailContainer(ResultSet resultSet, Collection<Groups> container) {
        try {
            while (resultSet.next()) {
                container.add(buildGroupDetail(resultSet));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public Collection<Groups> findGroupDetailByIdGroup(Groups groups) {
        @SuppressWarnings(value = "unchecked")
        Collection<Groups> container = (Collection<Groups>) ac.getBean("groupContainer");
        try {
            ResultSet resultSet = this.pool.selectResult("SELECT `group_detail`.`idGroup`, " +
                            "`group_detail`.`groupPrivacy`, `group_detail`.`groupCreateDate`, " +
                            "`group_detail`.`groupActivity`, `group_pic`.`pic`, `group_pic`.`picName` FROM " +
                            "`group_detail` INNER JOIN `group_pic` ON `group_pic`.`idGroup` = `group_detail`.`idGroup` " +
                            "WHERE `group_detail`.`idGroup` = ?;", groups.getIdGroup());
            buildGroupDetailContainer(resultSet, container);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return container;
    }

    private Groups buildGroupNoPic(ResultSet resultSet) {
        Groups group = ac.getBean(Groups.class);
        try {
            group.setIdGroup(resultSet.getString(1));
            group.setGroupPrivacy(resultSet.getString(2));
            group.setDateCreate(resultSet.getTimestamp(3));
            group.setGroupActivity(resultSet.getString(4));
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return group;
    }

    public void buildGroupNoPicContainer(ResultSet resultSet, Collection<Groups> container) {
        try {
            while (resultSet.next()) {
                container.add(buildGroupNoPic(resultSet));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public Collection<Groups> findGroupNoPicByIdGroup(Groups groups) {
        @SuppressWarnings(value = "unchecked")
        Collection<Groups> container = (Collection<Groups>) ac.getBean("groupContainer");
        try {
            ResultSet resultSet = this.pool.selectResult("SELECT * FROM `group_detail` " +
                    "WHERE `group_detail`.`idGroup` = ?;", groups.getIdGroup());
            buildGroupNoPicContainer(resultSet, container);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return container;
    }

    private Groups buildGroupSearchPurpose(ResultSet resultSet) {
        Groups groups = ac.getBean(Groups.class);
        try {
            groups.setIdGroup(resultSet.getString(1));
            groups.setDateCreate(resultSet.getTimestamp(2));
            groups.setPicture(resultSet.getBlob(3));
            groups.setPictureName(resultSet.getString(4));
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return groups;
    }

    public void buildGroupSearchPurposeContainer(ResultSet resultSet, Collection<Groups> container) {
        try {
            while (resultSet.next()) {
                container.add(buildGroupSearchPurpose(resultSet));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public Collection<Groups> findGroupDetailByLikeIdGroup(Groups groups) {
        @SuppressWarnings(value = "unchecked")
        Collection<Groups> container = (Collection<Groups>) ac.getBean("groupContainer");
        try {
            ResultSet resultSet = this.pool.selectResult("SELECT `group_detail`.`idGroup`, " +
                    "`group_detail`.`groupCreateDate`, `group_pic`.`pic`, `group_pic`.`picName` FROM `group_detail` " +
                    "INNER JOIN `group_pic` ON `group_detail`.`idGroup` = `group_pic`.`idGroup` WHERE " +
                    "`group_detail`.`groupPrivacy` <> 'hidden' AND `group_detail`.`idGroup` LIKE ?;",
                    (groups.getIdGroup()+"%"));
            buildGroupSearchPurposeContainer(resultSet, container);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return container;
    }

    private GroupTextPost buildGroupTextPost(ResultSet resultSet) {
        GroupTextPost post = ac.getBean(GroupTextPost.class);
        Groups groups = post.getGroups();
        Users users = post.getUser();
        try {
            post.setIdPost(resultSet.getLong(1));
            groups.setIdGroup(resultSet.getString(2));
            users.setUsername(resultSet.getString(3));
            post.setPost(resultSet.getString(4));
            post.setDatePost(resultSet.getTimestamp(5));
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
    public Collection<GroupTextPost> findGroupTextPostByIdPost(Post groupTextPost) {
        if (!(groupTextPost instanceof GroupTextPost)){
            throw new IllegalArgumentException("Need GroupTextPost.");
        }
        GroupTextPost post = (GroupTextPost) groupTextPost;
        @SuppressWarnings(value = "unchecked")
        Collection<GroupTextPost> container = (Collection<GroupTextPost>) ac.getBean("postContainer");
        try {
            ResultSet resultSet = this.pool.selectResult("SELECT * FROM `group_post` WHERE `idPost` = ? ;",
                    post.getIdPost());
            buildGroupTextPostContainer(resultSet, container);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return container;
    }

    @Override
    public Collection<GroupTextPost> findGroupTextPostByIdGroup(Post groupTextPost) {
        if (!(groupTextPost instanceof GroupTextPost)){
            throw new IllegalArgumentException("Need GroupTextPost.");
        }
        GroupTextPost post = (GroupTextPost) groupTextPost;
        @SuppressWarnings(value = "unchecked")
        Collection<GroupTextPost> container = (Collection<GroupTextPost>) ac.getBean("postContainer");
        try {
            ResultSet resultSet = this.pool.selectResult("SELECT * FROM `group_post` WHERE `idGroupe` = ?;",
                    post.getGroups().getIdGroup());
            buildGroupTextPostContainer(resultSet, container);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return container;
    }

    @Override
    public Collection<GroupTextPost> findGroupTextPostByUsername(Post groupTextPost) {
        if (!(groupTextPost instanceof GroupTextPost)){
            throw new IllegalArgumentException("Need GroupTextPost.");
        }
        GroupTextPost post = (GroupTextPost) groupTextPost;
        @SuppressWarnings(value = "unchecked")
        Collection<GroupTextPost> container = (Collection<GroupTextPost>) ac.getBean("postContainer");
        try {
            ResultSet resultSet = this.pool.selectResult("SELECT * FROM `group_post` WHERE `username` = ? ;",
                    post.getUser().getUsername());
            buildGroupTextPostContainer(resultSet, container);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return container;
    }

    @Override
    public Collection<GroupTextPost> findGroupTextPostByUsernameAndIdGroup(Post groupTextPost) {
        if (!(groupTextPost instanceof GroupTextPost)){
            throw new IllegalArgumentException("Need GroupTextPost.");
        }
        GroupTextPost post = (GroupTextPost) groupTextPost;
        @SuppressWarnings(value = "unchecked")
        Collection<GroupTextPost> container = (Collection<GroupTextPost>) ac.getBean("postContainer");
        try {
            ResultSet resultSet = this.pool.selectResult("SELECT * FROM `group_post` WHERE `username` = ? " +
                            "AND `idGroupe` = ?;", post.getUser().getUsername(), post.getGroups().getIdGroup());
            buildGroupTextPostContainer(resultSet, container);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return container;
    }

    private GroupTextPost buildGroupTextPostAndUserGroupInfo(ResultSet resultSet) {
        GroupTextPost post = ac.getBean(GroupTextPost.class);
        Groups groups = post.getGroups();
        Client users = post.getUser();
        try {
            post.setIdPost(resultSet.getLong(1));
            groups.setIdGroup(resultSet.getString(2));
            users.setUsername(resultSet.getString(3));
            post.setPost(resultSet.getString(4));
            post.setDatePost(resultSet.getTimestamp(5));
            users.setFirstName(resultSet.getString(6));
            users.setLastName(resultSet.getString(7));
            users.setPicture(resultSet.getBlob(8));
            groups.setPicture(resultSet.getBlob(9));
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return post;
    }

    public void buildGroupTextPostAndUserGroupInfoContainer(ResultSet resultSet, Collection<GroupTextPost> container) {
        try {
            while (resultSet.next()) {
                container.add(buildGroupTextPostAndUserGroupInfo(resultSet));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public Collection<GroupTextPost> findGroupTextPostAndUserGroupInfoByUsername(Post groupTextPost) {
        if (!(groupTextPost instanceof GroupTextPost)){
            throw new IllegalArgumentException("Need GroupTextPost.");
        }
        GroupTextPost post = (GroupTextPost) groupTextPost;
        @SuppressWarnings(value = "unchecked")
        Collection<GroupTextPost> container = (Collection<GroupTextPost>) ac.getBean("postContainer");
        try {
            ResultSet resultSet = this.pool.selectResult("SELECT `group_post`.`idPost`, " +
                            "`group_post`.`idGroupe`, `group_post`.`username`, `group_post`.`post`, " +
                            "`group_post`.`postDate`, `account_detail`.`firstName`, `account_detail`." +
                            "`lastName`, `account_pic`.`pic`, `group_pic`.`pic` FROM `group_post` " +
                            "INNER JOIN `account_detail` ON `account_detail`.`username` = `group_post`.`username` " +
                            "INNER JOIN `account_pic` ON `account_pic`.`username` = `group_post`.`username` " +
                            "INNER JOIN `group_pic` ON `group_pic`.`idGroup` = `group_post`.`idGroupe` " +
                            "WHERE `group_post`.`username` = ?;",
                             post.getUser().getUsername());
            buildGroupTextPostAndUserGroupInfoContainer(resultSet, container);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return container;
    }

    @Override
    public Collection<GroupTextPost> findUserFiendGroupTextPostAndUserGroupInfoByUsername(Post groupTextPost) {
        if (!(groupTextPost instanceof GroupTextPost)){
            throw new IllegalArgumentException("Need GroupTextPost.");
        }
        GroupTextPost post = (GroupTextPost) groupTextPost;
        @SuppressWarnings(value = "unchecked")
        Collection<GroupTextPost> container = (Collection<GroupTextPost>) ac.getBean("postContainer");
        try {
            ResultSet resultSet = this.pool.selectResult("SELECT `group_post`.`idPost`, " +
                            "`group_post`.`idGroupe`, `group_post`.`username`, `group_post`.`post`, " +
                            "`group_post`.`postDate`, `account_detail`.`firstName`, `account_detail`.`lastName`, " +
                            "`account_pic`.`pic`, `group_pic`.`pic` FROM `user_friend` " +
                            "INNER JOIN `group_post` ON `group_post`.`username` = `user_friend`.`friend` " +
                            "INNER JOIN `account_detail` ON `account_detail`.`username` = `group_post`.`username` " +
                            "INNER JOIN `account_pic` ON `account_pic`.`username` = `group_post`.`username` " +
                            "INNER JOIN `group_pic` ON `group_pic`.`idGroup` = `group_post`.`idGroupe` " +
                            "WHERE `user_friend`.`username` = ?;",
                            post.getUser().getUsername());

            buildGroupTextPostAndUserGroupInfoContainer(resultSet, container);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return container;
    }

    private GroupTextPost buildGroupTextPostAndUserInfo(ResultSet resultSet) {
        GroupTextPost post = ac.getBean(GroupTextPost.class);
        Groups groups = post.getGroups();
        Client users = post.getUser();
        try {
            post.setIdPost(resultSet.getLong(1));
            groups.setIdGroup(resultSet.getString(2));
            users.setUsername(resultSet.getString(3));
            post.setPost(resultSet.getString(4));
            post.setDatePost(resultSet.getTimestamp(5));
            users.setFirstName(resultSet.getString(6));
            users.setLastName(resultSet.getString(7));
            users.setPicture(resultSet.getBlob(8));
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return post;
    }

    public void buildGroupTextPostAndUserInfoContainer(ResultSet resultSet, Collection<GroupTextPost> container) {
        try {
            while (resultSet.next()) {
                container.add(buildGroupTextPostAndUserInfo(resultSet));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public Collection<GroupTextPost> findGroupTextPostAndUserInfoByIdGroup(Post groupTextPost) {
        if (!(groupTextPost instanceof GroupTextPost)){
            throw new IllegalArgumentException("Need GroupTextPost.");
        }
        GroupTextPost post = (GroupTextPost) groupTextPost;
        @SuppressWarnings(value = "unchecked")
        Collection<GroupTextPost> container = (Collection<GroupTextPost>) ac.getBean("postContainer");
        try {
            ResultSet resultSet = this.pool.selectResult("SELECT `group_post`.`idPost`, `group_post`.`idGroupe`, " +
                    "`group_post`.`username`, `group_post`.`post`,`group_post`.`postDate`,  `account_detail`.`firstName`, " +
                    "`account_detail`.`lastName`, `account_pic`.`pic` FROM `group_post`" +
                    "INNER JOIN `account_detail` ON `account_detail`.`username` = `group_post`.`username`" +
                    "INNER JOIN `account_pic` ON `account_pic`.`username` = `group_post`.`username`" +
                    "WHERE `group_post`.`idGroupe` = ?;", post.getGroups().getIdGroup());

            buildGroupTextPostAndUserInfoContainer(resultSet, container);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return container;
    }

    private GroupImgPost buildGroupImgPost(ResultSet resultSet) {
        GroupImgPost post = ac.getBean(GroupImgPost.class);
        Groups groups = post.getGroups();
        Users users = post.getUser();
        try {
            post.setIdPost(resultSet.getLong(1));
            groups.setIdGroup(resultSet.getString(2));
            users.setUsername(resultSet.getString(3));
            post.setImage(resultSet.getBlob(4));
            post.setFileName(resultSet.getString(5));
            post.setDatePost(resultSet.getTimestamp(6));
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return post;
    }

    public void buildGroupImgPostContainer(ResultSet resultSet, Collection<GroupImgPost> container) {
        try {
            while (resultSet.next()) {
                container.add(buildGroupImgPost(resultSet));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public Collection<GroupImgPost> findGroupImgPostByIdPost(Post groupImgPost) {
        if (!(groupImgPost instanceof GroupImgPost)){
            throw new IllegalArgumentException("Need GroupImgPost.");
        }
        GroupImgPost post = (GroupImgPost) groupImgPost;
        @SuppressWarnings(value = "unchecked")
        Collection<GroupImgPost> container = (Collection<GroupImgPost>) ac.getBean("postContainer");
        try {
            ResultSet resultSet = this.pool.selectResult("SELECT * FROM `group_img_post` WHERE `idPost` = ?;",
                    post.getIdPost());
            buildGroupImgPostContainer(resultSet, container);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return container;
    }

    @Override
    public Collection<GroupImgPost> findGroupImgPostByIdGroup(Post groupImgPost) {
        if (!(groupImgPost instanceof GroupImgPost)){
            throw new IllegalArgumentException("Need GroupImgPost.");
        }
        GroupImgPost post = (GroupImgPost) groupImgPost;
        @SuppressWarnings(value = "unchecked")
        Collection<GroupImgPost> container = (Collection<GroupImgPost>) ac.getBean("postContainer");
        try {
            ResultSet resultSet = this.pool.selectResult("SELECT * FROM `group_img_post` WHERE `idGroup` = ?;",
                    post.getGroups().getIdGroup());
            buildGroupImgPostContainer(resultSet, container);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return container;
    }

    @Override
    public Collection<GroupImgPost> findGroupImgPostByUsername(Post groupImgPost) {
        if (!(groupImgPost instanceof GroupImgPost)){
            throw new IllegalArgumentException("Need GroupImgPost.");
        }
        GroupImgPost post = (GroupImgPost) groupImgPost;
        @SuppressWarnings(value = "unchecked")
        Collection<GroupImgPost> container = (Collection<GroupImgPost>) ac.getBean("postContainer");
        try {
            ResultSet resultSet = this.pool.selectResult("SELECT * FROM `group_img_post` WHERE `username` = ?;",
                    post.getUser().getUsername());
            buildGroupImgPostContainer(resultSet, container);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return container;
    }

    private GroupImgPost buildGroupImgPostAndUserGroupInfo(ResultSet resultSet) {
        GroupImgPost post = ac.getBean(GroupImgPost.class);
        Groups groups = post.getGroups();
        Client users = post.getUser();
        try {
            post.setIdPost(resultSet.getLong(1));
            groups.setIdGroup(resultSet.getString(2));
            users.setUsername(resultSet.getString(3));
            post.setImage(resultSet.getBlob(4));
            post.setFileName(resultSet.getString(5));
            post.setDatePost(resultSet.getTimestamp(6));
            users.setFirstName(resultSet.getString(7));
            users.setLastName(resultSet.getString(8));
            users.setPicture(resultSet.getBlob(9));
            groups.setPicture(resultSet.getBlob(10));
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return post;
    }

    public void buildGroupImgPostAndUserGroupInfoContainer(ResultSet resultSet, Collection<GroupImgPost> container) {
        try {
            while (resultSet.next()) {
                container.add(buildGroupImgPostAndUserGroupInfo(resultSet));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public Collection<GroupImgPost> findGroupImgPostAndUserGroupInfoByUsername(Post groupImgPost) {
        if (!(groupImgPost instanceof GroupImgPost)){
            throw new IllegalArgumentException("Need GroupImgPost.");
        }
        GroupImgPost post = (GroupImgPost) groupImgPost;
        @SuppressWarnings(value = "unchecked")
        Collection<GroupImgPost> container = (Collection<GroupImgPost>) ac.getBean("postContainer");
        try {
            ResultSet resultSet = this.pool.selectResult("SELECT `group_img_post`.`idPost`, " +
                            "`group_img_post`.`idGroup`, `group_img_post`.`username`, `group_img_post`.`img`, " +
                            "`group_img_post`.`fileName`, `group_img_post`.`dateUpload`, `account_detail`.`firstName`, " +
                            "`account_detail`.`lastName`, `account_pic`.`pic`, `group_pic`.`pic` FROM `group_img_post` " +
                            "INNER JOIN `account_detail` ON `account_detail`.`username` = `group_img_post`.`username` " +
                            "INNER JOIN `account_pic` ON `account_pic`.`username` = `group_img_post`.`username` " +
                            "INNER JOIN `group_pic` ON `group_pic`.`idGroup` = `group_img_post`.`idGroup` " +
                            "WHERE `group_img_post`.`username` = ?;", post.getUser().getUsername());

            buildGroupImgPostAndUserGroupInfoContainer(resultSet, container);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return container;
    }

    @Override
    public Collection<GroupImgPost> findUserFriendGroupImgPostAndUserGroupInfoByUsername(Post groupImgPost) {
        if (!(groupImgPost instanceof GroupImgPost)){
            throw new IllegalArgumentException("Need GroupImgPost.");
        }
        GroupImgPost post = (GroupImgPost) groupImgPost;
        @SuppressWarnings(value = "unchecked")
        Collection<GroupImgPost> container = (Collection<GroupImgPost>) ac.getBean("postContainer");
        try {
            ResultSet resultSet = this.pool.selectResult("SELECT `group_img_post`.`idPost`, " +
                            "`group_img_post`.`idGroup`, `group_img_post`.`username`,  " +
                            "`group_img_post`.`img`, `group_img_post`.`fileName`, `group_img_post`.`dateUpload`,  " +
                            "`account_detail`.`firstName`, `account_detail`.`lastName`, `account_pic`.`pic`, " +
                            "`group_pic`.`pic` FROM `user_friend` " +
                            "INNER JOIN `group_img_post` ON `group_img_post`.`username` = `user_friend`.`friend` " +
                            "INNER JOIN `account_detail` ON `account_detail`.`username` = `group_img_post`.`username` " +
                            "INNER JOIN `account_pic` ON `account_pic`.`username` = `group_img_post`.`username` " +
                            "INNER JOIN `group_pic` ON `group_pic`.`idGroup` = `group_img_post`.`idGroup` " +
                            "WHERE `user_friend`.`username` = ?;",
                             post.getUser().getUsername());

            buildGroupImgPostAndUserGroupInfoContainer(resultSet, container);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return container;
    }

    private GroupImgPost buildGroupImgPostAndUserInfo(ResultSet resultSet) {
        GroupImgPost post = ac.getBean(GroupImgPost.class);
        Groups groups = post.getGroups();
        Client users = post.getUser();
        try {
            post.setIdPost(resultSet.getLong(1));
            groups.setIdGroup(resultSet.getString(2));
            users.setUsername(resultSet.getString(3));
            post.setImage(resultSet.getBlob(4));
            post.setFileName(resultSet.getString(5));
            post.setDatePost(resultSet.getTimestamp(6));
            users.setFirstName(resultSet.getString(7));
            users.setLastName(resultSet.getString(8));
            users.setPicture(resultSet.getBlob(9));
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return post;
    }

    public void buildGroupImgPostAndUserInfoContainer(ResultSet resultSet, Collection<GroupImgPost> container) {
        try {
            while (resultSet.next()) {
                container.add(buildGroupImgPostAndUserInfo(resultSet));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public Collection<GroupImgPost> findGroupImgPostAndUserInfoByIdGroup(Post groupImgPost) {
        if (!(groupImgPost instanceof GroupImgPost)){
            throw new IllegalArgumentException("Need GroupImgPost.");
        }
        GroupImgPost post = (GroupImgPost) groupImgPost;
        @SuppressWarnings(value = "unchecked")
        Collection<GroupImgPost> container = (Collection<GroupImgPost>) ac.getBean("postContainer");
        try {
            ResultSet resultSet = this.pool.selectResult("SELECT `group_img_post`.`idPost`, " +
                    "`group_img_post`.`idGroup`, `group_img_post`.`username`, `group_img_post`.`img`, " +
                    "`group_img_post`.`fileName`, `group_img_post`.`dateUpload`, `account_detail`.`firstName`, " +
                    "`account_detail`.`lastName`, `account_pic`.`pic` FROM `group_img_post` " +
                    "INNER JOIN `account_detail` ON `account_detail`.`username` = `group_img_post`.`username` " +
                    "INNER JOIN `account_pic` ON `account_pic`.`username` = `group_img_post`.`username` " +
                    "WHERE `group_img_post`.`idGroup` = ?;", post.getGroups().getIdGroup());

            buildGroupImgPostAndUserInfoContainer(resultSet, container);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return container;
    }

    private GroupInvite buildGroupInvite(ResultSet resultSet) {
        GroupInvite invite = ac.getBean(GroupInvite.class);
        Groups groups = invite.getSourceGroup();
        Users users = invite.getTargetAccount();
        try {
            groups.setIdGroup(resultSet.getString(1));
            users.setUsername(resultSet.getString(2));
            invite.setRequestDate(resultSet.getTimestamp(3));
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
        Map<Long, GroupInvite> container = (Map<Long, GroupInvite>) ac.getBean("requestContainer");
        try {
            ResultSet resultSet = this.pool.selectResult("SELECT * FROM `group_invite` WHERE `source` = ? ORDER BY " +
                    "`group_invite`.`inviteDate` DESC;", invite.getSourceGroup().getIdGroup());
            buildGroupInviteContainer(resultSet, container);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return container;
    }

    @Override
    public Map<Long, GroupInvite> findGroupInviteByUsername(GroupInvite invite) {
        @SuppressWarnings(value = "unchecked")
        Map<Long, GroupInvite> container = (Map<Long, GroupInvite>) ac.getBean("requestContainer");
        try {
            ResultSet resultSet = this.pool.selectResult("SELECT * FROM `group_invite` WHERE `target` = ? ORDER BY " +
                    "`group_invite`.`inviteDate` DESC;", invite.getTargetAccount().getUsername());
            buildGroupInviteContainer(resultSet, container);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return container;
    }

    @Override
    public Map<Long, GroupInvite> findGroupInviteByUsernameAndIdGroup(GroupInvite invite) {
        @SuppressWarnings(value = "unchecked")
        Map<Long, GroupInvite> container = (Map<Long, GroupInvite>) ac.getBean("requestContainer");
        try {
            ResultSet resultSet = this.pool.selectResult("SELECT * FROM `group_invite` WHERE `source` = ? AND " +
                    "`target` = ? ORDER BY `group_invite`.`inviteDate` DESC;", invite.getSourceGroup().getIdGroup(),
                    invite.getTargetAccount().getUsername());
            buildGroupInviteContainer(resultSet, container);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return container;
    }

    private GroupInvite buildGroupInviteAndTargetInfo(ResultSet resultSet) {
        GroupInvite invite = ac.getBean(GroupInvite.class);
        Groups groups = invite.getSourceGroup();
        Client target = invite.getTargetAccount();
        try {
            groups.setIdGroup(resultSet.getString(1));
            target.setUsername(resultSet.getString(2));
            invite.setRequestDate(resultSet.getTimestamp(3));
            target.setFirstName(resultSet.getString(4));
            target.setLastName(resultSet.getString(5));
            target.setPicture(resultSet.getBlob(6));
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return invite;
    }

    public void buildGroupInviteAndTargetInfoContainer(ResultSet resultSet, Map<Long, GroupInvite> container) {
        long i = 0;
        try {
            while (resultSet.next()) {
                container.put(i, buildGroupInviteAndTargetInfo(resultSet));
                i++;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public Map<Long, GroupInvite> findGroupInviteAndTargetInfoByUsername(GroupInvite invite) {
        @SuppressWarnings(value = "unchecked")
        Map<Long, GroupInvite> container = (Map<Long, GroupInvite>) ac.getBean("requestContainer");
        try {
            ResultSet resultSet = this.pool.selectResult("SELECT `group_invite`.`source`, `group_invite`.`target`, " +
                            "`group_invite`.`inviteDate`, `account_detail`.`firstName`, `account_detail`.`lastName`, " +
                            "`account_pic`.`pic` FROM `group_invite` INNER JOIN `account_detail` ON " +
                            "`account_detail`.`username` = `group_invite`.`target` INNER JOIN `account_pic` ON " +
                            "`account_pic`.`username` = `group_invite`.`target` WHERE `group_invite`.`target` = ? ;",
                            invite.getTargetAccount().getUsername());

            buildGroupInviteAndTargetInfoContainer(resultSet, container);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return container;
    }

    @Override
    public Map<Long, GroupInvite> findGroupInviteAndTargetInfoByIdGroup(GroupInvite invite) {
        @SuppressWarnings(value = "unchecked")
        Map<Long, GroupInvite> container = (Map<Long, GroupInvite>) ac.getBean("requestContainer");
        try {
            ResultSet resultSet = this.pool.selectResult("SELECT `group_invite`.`source`, `group_invite`.`target`, " +
                            "`group_invite`.`inviteDate`, `account_detail`.`firstName`, `account_detail`.`lastName`, " +
                            "`account_pic`.`pic` FROM `group_invite` INNER JOIN `account_detail` ON " +
                            "`account_detail`.`username` = `group_invite`.`target` INNER JOIN `account_pic` ON " +
                            "`account_pic`.`username` = `group_invite`.`target` WHERE `group_invite`.`source` = ? ;",
                    invite.getSourceGroup().getIdGroup());

            buildGroupInviteAndTargetInfoContainer(resultSet, container);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return container;
    }

    private GroupInvite buildGroupInviteAndGroupPic(ResultSet resultSet) {
        GroupInvite invite = ac.getBean(GroupInvite.class);
        Groups groups = invite.getSourceGroup();
        Client target = invite.getTargetAccount();
        try {
            groups.setIdGroup(resultSet.getString(1));
            target.setUsername(resultSet.getString(2));
            invite.setRequestDate(resultSet.getTimestamp(3));
            groups.setPicture(resultSet.getBlob(4));
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return invite;
    }

    public void buildGroupInviteAndGroupPicContainer(ResultSet resultSet, Map<Long, GroupInvite> container, long startKey) {
        long i = startKey;
        try {
            while (resultSet.next()) {
                container.put(i, buildGroupInviteAndGroupPic(resultSet));
                i++;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public Map<Long, GroupInvite> findGroupInviteAndGroupPicByUsername(GroupInvite invite, long startKey) {
        @SuppressWarnings(value = "unchecked")
        Map<Long, GroupInvite> container = (Map<Long, GroupInvite>) ac.getBean("requestContainer");
        try {
            ResultSet resultSet = this.pool.selectResult("SELECT `group_invite`.`source`, `group_invite`.`target`, " +
                    "`group_invite`.`inviteDate`, `group_pic`.`pic` FROM `group_invite` " +
                    "INNER JOIN `group_pic` ON `group_pic`.`idGroup` = `group_invite`.`source` " +
                    "WHERE `group_invite`.`target` = ?;", invite.getTargetAccount().getUsername());

            buildGroupInviteAndGroupPicContainer(resultSet, container, startKey);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return container;
    }

    private GroupMembers buildGroupMembers(ResultSet resultSet) {
        GroupMembers member = ac.getBean(GroupMembers.class);
        Groups groups = member.getGroup();
        Users users = member.getFriendMember();
        try {
            groups.setIdGroup(resultSet.getString(1));
            users.setUsername(resultSet.getString(2));
            member.setFriendMemberSince(resultSet.getTimestamp(3));
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
    public Map<Long, GroupMembers> findGroupMemberByIdGroup(Member groupMembers) {
        if (!(groupMembers instanceof GroupMembers)){
            throw new IllegalArgumentException("Need GroupMembers.");
        }
        GroupMembers member = (GroupMembers) groupMembers;
        @SuppressWarnings(value = "unchecked")
        Map<Long, GroupMembers> container = (Map<Long, GroupMembers>) ac.getBean("memberContainer");
        try {
            ResultSet resultSet = this.pool.selectResult("SELECT * FROM `group_member` WHERE `idGroup` = ? ORDER " +
                    "BY `group_member`.`joinDate` DESC;", member.getGroup().getIdGroup());
            buildGroupMemberContainer(resultSet, container);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return container;
    }

    @Override
    public Map<Long, GroupMembers> findGroupMemberByUsername(Member groupMembers) {
        if (!(groupMembers instanceof GroupMembers)){
            throw new IllegalArgumentException("Need GroupMembers.");
        }
        GroupMembers member = (GroupMembers) groupMembers;
        @SuppressWarnings(value = "unchecked")
        Map<Long, GroupMembers> container = (Map<Long, GroupMembers>) ac.getBean("memberContainer");
        try {
            ResultSet resultSet = this.pool.selectResult("SELECT * FROM `group_member` WHERE `member` = ? ORDER " +
                    "BY `group_member`.`joinDate` DESC;", member.getFriendMember().getUsername());
            buildGroupMemberContainer(resultSet, container);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return container;
    }

    @Override
    public Map<Long, GroupMembers> findGroupMemberByUsernameAndIdGroup(Member groupMembers) {
        if (!(groupMembers instanceof GroupMembers)){
            throw new IllegalArgumentException("Need GroupMembers.");
        }
        GroupMembers member = (GroupMembers) groupMembers;
        @SuppressWarnings(value = "unchecked")
        Map<Long, GroupMembers> container = (Map<Long, GroupMembers>) ac.getBean("memberContainer");
        try {
            ResultSet resultSet = this.pool.selectResult("SELECT * FROM `group_member` WHERE `member` = ? AND" +
                    "`idGroup` = ? ORDER BY `group_member`.`joinDate` DESC;", member.getFriendMember().getUsername(),
                    member.getGroup().getIdGroup());
            buildGroupMemberContainer(resultSet, container);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return container;
    }

    private GroupMembers buildGroupAndPicAndUser(ResultSet resultSet) {
        GroupMembers members = ac.getBean(GroupMembers.class);
        Groups groups = members.getGroup();
        Client client = members.getFriendMember();
        try {
            groups.setIdGroup(resultSet.getString(1));
            groups.setGroupPrivacy(resultSet.getString(2));
            groups.setGroupActivity(resultSet.getString(3));
            groups.setPicture(resultSet.getBlob(4));
            groups.setPictureName(resultSet.getString(5));
            client.setUsername(resultSet.getString(6));
            members.setGroupMemberType(resultSet.getString(7));
            members.setFriendMemberSince(resultSet.getTimestamp(8));
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return members;
    }

    public void buildGroupAndPicAndUserContainer(ResultSet resultSet, Map<Long, GroupMembers> container) {
        long i = 0;
        try {
            while (resultSet.next()) {
                container.put(i, buildGroupAndPicAndUser(resultSet));
                i++;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public Map<Long, GroupMembers>  findGroupAndPicAndUserByUsername(Users users) {
        @SuppressWarnings(value = "unchecked")
        Map<Long, GroupMembers> container = (Map<Long, GroupMembers>) ac.getBean("memberContainer");
        try {
            ResultSet resultSet = this.pool.selectResult("SELECT `group_detail`.`idGroup`, " +
                    "`group_detail`.`groupPrivacy`, `group_detail`.`groupActivity`, `group_pic`.`pic`, " +
                    "`group_pic`.`picName`, `group_member`.`member`, `group_member`.`memberType`, `group_member`.`joinDate` " +
                    "FROM `group_detail` INNER JOIN `group_member` ON `group_member`.`idGroup` = `group_detail`.`idGroup` " +
                    "INNER JOIN `group_pic` ON `group_pic`.`idGroup` = `group_detail`.`idGroup` " +
                    "WHERE `group_member`.`member` = ?;", users.getUsername());
            buildGroupAndPicAndUserContainer(resultSet, container);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return container;
    }

    private GroupMembers buildGroupMemberAndInfo(ResultSet resultSet) {
        GroupMembers members = ac.getBean(GroupMembers.class);
        Groups groups = members.getGroup();
        Client client = members.getFriendMember();
        try {
            groups.setIdGroup(resultSet.getString(1));
            client.setUsername(resultSet.getString(2));
            members.setGroupMemberType(resultSet.getString(3));
            members.setFriendMemberSince(resultSet.getTimestamp(4));
            client.setFirstName(resultSet.getString(5));
            client.setLastName(resultSet.getString(6));
            client.setPicture(resultSet.getBlob(7));
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return members;
    }

    public void buildGroupMemberAndInfoContainer(ResultSet resultSet, Map<Long, GroupMembers> container) {
        long i = 0;
        try {
            while (resultSet.next()) {
                container.put(i, buildGroupMemberAndInfo(resultSet));
                i++;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public Map<Long, GroupMembers>  findGroupMemberAndInfoByIdGroup(Groups groups) {
        @SuppressWarnings(value = "unchecked")
        Map<Long, GroupMembers> container = (Map<Long, GroupMembers>) ac.getBean("memberContainer");
        try {
            ResultSet resultSet = this.pool.selectResult("SELECT `group_member`.`idGroup`, `group_member`.`member`, " +
                    "`group_member`.`memberType`, `group_member`.`joinDate`,  `account_detail`.`firstName`, " +
                    "`account_detail`.`lastName`, `account_pic`.`pic` FROM `group_member` " +
                    "INNER JOIN `account_detail` ON `group_member`.`member` = `account_detail`.`username` " +
                    "INNER JOIN `account_pic` ON `account_pic`.`username` = `group_member`.`member` " +
                    "WHERE `group_member`.`idGroup` = ?;", groups.getIdGroup());

            buildGroupMemberAndInfoContainer(resultSet, container);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return container;
    }

    private Groups buildGroupPicture(ResultSet resultSet) {
        Groups picture = ac.getBean(Groups.class);
        try {
            picture.setIdGroup(resultSet.getString(1));
            picture.setPicture(resultSet.getBlob(2));
            picture.setPictureName(resultSet.getString(3));
            picture.setDateCreate(resultSet.getTimestamp(4));
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return picture;
    }

    public void buildBuildGroupPictureContainer(ResultSet resultSet, Collection<Groups> container) {
        try {
            while (resultSet.next()) {
                container.add(buildGroupPicture(resultSet));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public Collection<Groups> findGroupPictureByIdGroup(Groups picture) {
        @SuppressWarnings(value = "unchecked")
        Collection<Groups> container = (Collection<Groups>) ac.getBean("groupContainer");
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
        Groups groups = request.getSourceGroup();
        Users users = request.getTargetAccount();
        try {
            groups.setIdGroup(resultSet.getString(1));
            users.setUsername(resultSet.getString(2));
            request.setRequestDate(resultSet.getTimestamp(3));
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
    public Map<Long, GroupRequest> findGroupRequestByIdGroup(Request groupRequest) {
        if (!(groupRequest instanceof GroupRequest)){
            throw new IllegalArgumentException("Need GroupRequest.");
        }
        GroupRequest request = (GroupRequest) groupRequest;
        @SuppressWarnings(value = "unchecked")
        Map<Long, GroupRequest> container = (Map<Long, GroupRequest>) ac.getBean("requestContainer");
        try {
            ResultSet resultSet = this.pool.selectResult("SELECT * FROM `group_request` WHERE `idGroup` = ? ;",
                    request.getSourceGroup().getIdGroup());
            buildGroupRequestContainer(resultSet, container);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return container;
    }

    @Override
    public Map<Long, GroupRequest> findGroupRequestByUsername(Request groupRequest) {
        if (!(groupRequest instanceof GroupRequest)){
            throw new IllegalArgumentException("Need GroupRequest.");
        }
        GroupRequest request = (GroupRequest) groupRequest;
        @SuppressWarnings(value = "unchecked")
        Map<Long, GroupRequest> container = (Map<Long, GroupRequest>) ac.getBean("requestContainer");
        try {
            ResultSet resultSet = this.pool.selectResult("SELECT * FROM `group_request` WHERE `requestTarget` = ? ;",
                    request.getTargetAccount().getUsername());
            buildGroupRequestContainer(resultSet, container);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return container;
    }

    @Override
    public Map<Long, GroupRequest> findGroupRequestByUsernameAndIdGroup(Request groupRequest) {
        if (!(groupRequest instanceof GroupRequest)){
            throw new IllegalArgumentException("Need GroupRequest.");
        }
        GroupRequest request = (GroupRequest) groupRequest;
        @SuppressWarnings(value = "unchecked")
        Map<Long, GroupRequest> container = (Map<Long, GroupRequest>) ac.getBean("requestContainer");
        try {
            ResultSet resultSet = this.pool.selectResult("SELECT * FROM `group_request` WHERE `idGroup` = ? AND " +
                    "`requestTarget` = ?;", request.getSourceGroup().getIdGroup(), request.getTargetAccount().getUsername());
            buildGroupRequestContainer(resultSet, container);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return container;
    }

    private GroupRequest buildGroupRequestAndInfo(ResultSet resultSet) {
        GroupRequest request = ac.getBean(GroupRequest.class);
        Groups groups = request.getSourceGroup();
        Client client = request.getTargetAccount();
        try {
            groups.setIdGroup(resultSet.getString(1));
            client.setUsername(resultSet.getString(2));
            request.setRequestDate(resultSet.getTimestamp(3));
            client.setFirstName(resultSet.getString(4));
            client.setLastName(resultSet.getString(5));
            client.setPicture(resultSet.getBlob(6));
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return request;
    }

    public void buildGroupRequestAndInfoContainer(ResultSet resultSet, Map<Long, GroupRequest> container) {
        long i = 0;
        try {
            while (resultSet.next()) {
                container.put(i, buildGroupRequestAndInfo(resultSet));
                i++;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public Map<Long, GroupRequest> findGroupRequestAndInfoByUsername(Request groupRequest) {
        if (!(groupRequest instanceof GroupRequest)){
            throw new IllegalArgumentException("Need GroupRequest.");
        }
        GroupRequest request = (GroupRequest) groupRequest;
        @SuppressWarnings(value = "unchecked")
        Map<Long, GroupRequest> container = (Map<Long, GroupRequest>) ac.getBean("requestContainer");
        try {
            ResultSet resultSet = this.pool.selectResult("SELECT `group_request`.`idGroup`, " +
                    "`group_request`.`requestTarget`, `group_request`.`sentDate`, `account_detail`.`firstName`, " +
                    "`account_detail`.`lastName`, `account_pic`.`pic` FROM `group_request` " +
                    "INNER JOIN `account_detail` ON `account_detail`.`username` = `group_request`.`requestTarget` " +
                    "INNER JOIN `account_pic` ON `account_pic`.`username` = `group_request`.`requestTarget` " +
                    "WHERE `group_request`.`requestTarget` = ?;", request.getTargetAccount().getUsername());

            buildGroupRequestAndInfoContainer(resultSet, container);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return container;
    }

    @Override
    public Map<Long, GroupRequest> findGroupRequestAndInfoByIdGroup(Request groupRequest) {
        if (!(groupRequest instanceof GroupRequest)){
            throw new IllegalArgumentException("Need GroupRequest.");
        }
        GroupRequest request = (GroupRequest) groupRequest;
        @SuppressWarnings(value = "unchecked")
        Map<Long, GroupRequest> container = (Map<Long, GroupRequest>) ac.getBean("requestContainer");
        try {
            ResultSet resultSet = this.pool.selectResult("SELECT `group_request`.`idGroup`, " +
                    "`group_request`.`requestTarget`, `group_request`.`sentDate`, `account_detail`.`firstName`, " +
                    "`account_detail`.`lastName`, `account_pic`.`pic` FROM `group_request` " +
                    "INNER JOIN `account_detail` ON `account_detail`.`username` = `group_request`.`requestTarget` " +
                    "INNER JOIN `account_pic` ON `account_pic`.`username` = `group_request`.`requestTarget` " +
                    "WHERE `group_request`.`idGroup` = ?;", request.getSourceGroup().getIdGroup());

            buildGroupRequestAndInfoContainer(resultSet, container);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return container;
    }

    private GroupRequest buildGroupRequestAndGroupPic(ResultSet resultSet) {
        GroupRequest request = ac.getBean(GroupRequest.class);
        Groups groups = request.getSourceGroup();
        Client client = request.getTargetAccount();
        try {
            groups.setIdGroup(resultSet.getString(1));
            client.setUsername(resultSet.getString(2));
            request.setRequestDate(resultSet.getTimestamp(3));
            groups.setPicture(resultSet.getBlob(4));
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return request;
    }

    public void buildGroupRequestAndGroupPicContainer(ResultSet resultSet, Map<Long, GroupRequest> container , long startKey) {
        long i = startKey;
        try {
            while (resultSet.next()) {
                container.put(i, buildGroupRequestAndGroupPic(resultSet));
                i++;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public Map<Long, GroupRequest> findGroupRequestAndGroupPicByUsername(Request groupRequest, long startKey) {
        if (!(groupRequest instanceof GroupRequest)){
            throw new IllegalArgumentException("Need GroupRequest.");
        }
        GroupRequest request = (GroupRequest) groupRequest;
        @SuppressWarnings(value = "unchecked")
        Map<Long, GroupRequest> container = (Map<Long, GroupRequest>) ac.getBean("requestContainer");
        try {
            ResultSet resultSet = this.pool.selectResult("SELECT `group_request`.`idGroup`, " +
                    "`group_request`.`requestTarget`, `group_request`.`sentDate`, `group_pic`.`pic` " +
                    "FROM `group_request` INNER JOIN `group_pic` ON `group_pic`.`idGroup` = `group_request`.`idGroup` " +
                    "WHERE `group_request`.`requestTarget` = ?;", request.getTargetAccount().getUsername());

            buildGroupRequestAndGroupPicContainer(resultSet, container, startKey);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return container;
    }

    @Override
    public int deleteGroupDetailByIdGroup(Groups groups) {
        int result = 0;
        try{
            result = this.pool.updateQuery("DELETE FROM `group_detail` WHERE `idGroup` = ?;",
                    groups.getIdGroup());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    @Override
    public int deleteGroupTextPostByIdPost(Post post) {
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
    public int deleteGroupImgPostByIdPost(Post post) {
        int result = 0;
        try {
            result = this.pool.updateQuery("DELETE FROM `group_img_post` WHERE `idPost` = ?;", post.getIdPost());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return result;    }

    @Override
    public int deleteGroupInviteByIdGroupAndUsername(GroupInvite invite) {
        int result = 0;
        try {
            result = this.pool.updateQuery("DELETE FROM `group_invite` WHERE `source` = ? AND `target` = ? ;",
                    invite.getSourceGroup().getIdGroup(), invite.getTargetAccount().getUsername());
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
                    invite.getSourceGroup().getIdGroup(), invite.getRequestDate());
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
                    invite.getTargetAccount().getUsername(), invite.getRequestDate());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    @Override
    public int deleteGroupMemberByIdGroupAndUsername(Member groupMembers) {
        if (!(groupMembers instanceof GroupMembers)){
            throw new IllegalArgumentException("Need GroupMembers.");
        }
        GroupMembers member = (GroupMembers) groupMembers;
        int result = 0;
        try {
            result = this.pool.updateQuery("DELETE FROM `group_member` WHERE `idGroup` = ? AND `member` = ? ;",
                    member.getGroup().getIdGroup(), member.getFriendMember().getUsername());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    @Override
    public int deleteGroupPictureByIdGroup(Groups picture) {
        int result = 0;
        try {
            result = this.pool.updateQuery("DELETE FROM `group_pic` WHERE `idGroup` = ?;", picture.getIdGroup());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    @Override
    public int deleteGroupRequestByIdGroupAndUsername(Request groupRequest) {
        if (!(groupRequest instanceof GroupRequest)){
            throw new IllegalArgumentException("Need GroupRequest.");
        }
        GroupRequest request = (GroupRequest) groupRequest;
        int result = 0;
        try {
            result = this.pool.updateQuery("DELETE FROM `group_request` WHERE `idGroup` = ? AND `requestTarget` = ?;",
                    request.getSourceGroup().getIdGroup(), request.getTargetAccount());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    @Override
    public int deleteGroupRequestByIdGroupAndDate(Request groupRequest) {
        if (!(groupRequest instanceof GroupRequest)){
            throw new IllegalArgumentException("Need GroupRequest.");
        }
        GroupRequest request = (GroupRequest) groupRequest;
        int result = 0;
        try {
            result = this.pool.updateQuery("DELETE FROM `group_request` WHERE `idGroup` = ? AND `sentDate` = ?;",
                    request.getSourceGroup().getIdGroup(), request.getRequestDate());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    @Override
    public int deleteGroupRequestByUsernameAndDate(Request groupRequest) {
        if (!(groupRequest instanceof GroupRequest)){
            throw new IllegalArgumentException("Need GroupRequest.");
        }
        GroupRequest request = (GroupRequest) groupRequest;
        int result = 0;
        try {
            result = this.pool.updateQuery("DELETE FROM `group_request` WHERE `requestTarget` = ? AND `sentDate` = ?;",
                    request.getTargetAccount().getUsername(), request.getRequestDate());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }
}
