package com.dsc.service;

import com.dsc.domain.Permission;
import com.dsc.domain.Role;

import java.util.List;

public interface IRoleService {
    List<Role> findAll();
    /**
     * 添加角色
     */
    void save(Role role);

    Integer findRoleUserByRoleId(String roleId);
    void delPermissionByRole(String roleId);
    void delRole(String roleId);

    Role findById(String id);

    /**
     * 根据角色id查询对应角色
     */
    Role findRoleInfoById(String roleId);
    /**
     * 查询角色可以添加的所有权限
     */
    List<Permission> findPermissions(String roleId);

    /**
     * 添加角色对应的权限
     */
    void addPermissionToRole(String roleId, String[] permissionIds);

    /**
     * 查询用户拥有的权限
     */
    List<Integer> findPermissionByRoleId(String roleId);
}
