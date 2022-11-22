package com.dsc.service;

import com.dsc.domain.Permission;

import java.util.List;

public interface IPermissionService {

    /**
     * 查询所有资源权限
     */
    public List<Permission> findAll() throws Exception;
    /**
     * 添加资源权限
     */
    void save(Permission permission) throws Exception;
    /**
     * 根据id查询资源权限详情
     */
    Permission findById(String id) throws Exception;

    /**
     * 根据permissionId查询与role是否关联
     */
    int findRolePermission(String pId) throws Exception;
    /**
     * 删除资源权限
     */
    void deleteById(String pid) throws Exception;
}
