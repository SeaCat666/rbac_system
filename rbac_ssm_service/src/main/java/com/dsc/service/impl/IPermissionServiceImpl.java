package com.dsc.service.impl;

import com.dsc.dao.IPermissionDao;
import com.dsc.domain.Permission;
import com.dsc.service.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class IPermissionServiceImpl implements IPermissionService{

    @Autowired
    private IPermissionDao permissionDao;

    /**
     * 查询所有资源权限
     */
    @Override
    public List<Permission> findAll() throws Exception{
        return permissionDao.findAll();
    }
    /**
     * 添加资源权限
     */
    @Override
    public void save(Permission permission) throws Exception{
        permissionDao.save(permission);
    }
    /**
     * 根据id查询资源权限详情
     */
    @Override
    public Permission findById(String id) throws Exception {
        return permissionDao.findById(id);
    }

    /**
     * 根据permissionId查询与role是否关联
     */
    @Override
    public int findRolePermission(String pId) throws Exception{
        return permissionDao.findRolePermission(pId);
    }
    /**
     * 删除资源权限
     */
    @Override
    public void deleteById(String pid) throws Exception {
        //先解除和该资源权限和角色的绑定
        permissionDao.deleteFromRole_Permission(pid);
        //再根据资源权限id删除该权限
        permissionDao.deleteById(pid);
    }
}
