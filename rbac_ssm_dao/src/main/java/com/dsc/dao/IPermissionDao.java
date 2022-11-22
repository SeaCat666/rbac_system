package com.dsc.dao;

import com.dsc.domain.Permission;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface IPermissionDao {
    /**
     * 查询所有资源权限
     */
    List<Permission> findAll() throws Exception;

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
     * 解除和该资源权限和角色的绑定
     */
    void deleteFromRole_Permission(String pid) throws Exception;
    /**
     * 根据pid删除该权限
     */
    void deleteById(String id) throws Exception ;

}
