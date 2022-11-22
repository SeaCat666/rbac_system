package com.dsc.dao;

import com.dsc.domain.Role;
import com.dsc.domain.UserInfo;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface IUsersInfoDao {
    UserInfo findByUserName(String userName);

    List<UserInfo> findAll();
    /**
     * 查询用户详情
     */
    UserInfo findById(String id);
    @Insert("insert into users values(null,#{email},#{username},#{password},#{phoneNum},#{status})")
    void insertUserInfo(UserInfo userInfo);

    List<Role> findRoles();

    void addRoleToUser(@Param("userId") String userId,@Param("roleId") String roleId);

    void delOldRole(@Param("userId") String userId);

    List<Integer> findRolesByUserId(String userid);
}
