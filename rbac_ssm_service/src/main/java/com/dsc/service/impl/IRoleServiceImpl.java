package com.dsc.service.impl;

import com.dsc.dao.IRoleDao;
import com.dsc.domain.Permission;
import com.dsc.domain.Role;
import com.dsc.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
@Service
 public class IRoleServiceImpl implements IRoleService {
    @Resource
    private IRoleDao iRoleDao;

    @Override
    public List<Role> findAll() {
        return iRoleDao.findAll();
    }

    /**
     * 添加角色
     */
    @Override
    public void save(Role role) {
        iRoleDao.save(role);
    }


    @Override
    public Integer findRoleUserByRoleId(String roleId) {
        return iRoleDao.findRoleUserByRoleId(roleId);
    }

    @Override
    public void delPermissionByRole(String roleId) {
        iRoleDao.delPermissionByRole(roleId);
    }

    @Override
    public void delRole(String roleId) {
        iRoleDao.delRole(roleId);
    }

    @Override
    public Role findById(String id) {
        return iRoleDao.findById(id);
    }


    /**
     * 根据角色id查询对应角色
     */
    @Override
    public Role findRoleInfoById(String roleId) {
        return iRoleDao.findRoleInfoById(roleId);
    }
    /**
     * 查询角色可以添加的所有权限
     */
    @Override
    public List<Permission> findPermissions(String roleId) {
        return iRoleDao.findPermissions();
    }

    /**
     * 添加角色对应的权限
     */
    @Override
    public void addPermissionToRole(String roleId, String[] permissionIds) {    //1.先解除角色所对应的权限
        for (String permissionId : permissionIds) {
            iRoleDao.addPermissionToRole(roleId,permissionId);
        }
    }
    /**
     * 查询角色拥有的权限
     */
    @Override
    public List<Integer> findPermissionByRoleId(String roleId) {
        return iRoleDao.findPermissionByRoleId(roleId);
    }
}
