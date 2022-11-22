package com.dsc.service.impl;

import com.dsc.dao.IUsersInfoDao;
import com.dsc.domain.Role;
import com.dsc.domain.UserInfo;
import com.dsc.service.IUsersInfoService;
import com.github.pagehelper.PageHelper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional
public class IUsersInfoServiceImpl implements IUsersInfoService {
    @Resource
    private IUsersInfoDao usersInfoDao;
    @Override
    public UserInfo findByUserName(String username) {
        return usersInfoDao.findByUserName(username);
    }

    @Override
    public List<UserInfo> findAll(int page, int size) throws Exception {
        //开启分页
        PageHelper.startPage(page,size);
        return usersInfoDao.findAll();
    }

    @Override
    public void insertUserInfo(UserInfo userInfo) {
        //加密密码
        BCryptPasswordEncoder bcpe = new BCryptPasswordEncoder();
        userInfo.setPassword(bcpe.encode(userInfo.getPassword()));
        usersInfoDao.insertUserInfo(userInfo);
    }

    @Override
    public UserInfo findById(String id) {
        return usersInfoDao.findById(id);
    }

    @Override
    public List<Role> findRoles() {
        return usersInfoDao.findRoles();
    }

    @Override
    public void addRoleToUser(String userId, String[] roleIds) {
        for (String roleId:roleIds
             ) {
            usersInfoDao.addRoleToUser(userId,roleId);
        }
    }

    @Override
    public void delOldRole(String userId) {
            usersInfoDao.delOldRole(userId);
    }

    @Override
    public List<Integer> findRolesByUserId(String userid) {
        return usersInfoDao.findRolesByUserId(userid);
    }

}
