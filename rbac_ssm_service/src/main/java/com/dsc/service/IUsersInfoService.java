package com.dsc.service;

import com.dsc.domain.Role;
import com.dsc.domain.UserInfo;

import java.util.List;

public interface IUsersInfoService {
    UserInfo findByUserName(String username);

    List<UserInfo> findAll(int page,int size) throws Exception;

    void insertUserInfo(UserInfo userInfo);    /**
     * 查询用户详情
     */
    UserInfo findById(String id);

    List<Role> findRoles();

    void addRoleToUser(String userId, String[] roleIds);

    void delOldRole(String userId);

    List<Integer> findRolesByUserId(String userid);
}
