package com.dsc.dao;

import com.dsc.domain.Permission;
import com.dsc.domain.Role;
import com.dsc.domain.Traveller;
import com.dsc.domain.UserInfo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface IRoleDao {
    @Select("SELECT\n" +
            "\troleName\n" +
            "FROM\n" +
            "\t`users` u,\n" +
            "\tusers_role ur,\n" +
            "\trole r,\n" +
            "\trole_permission rp,\n" +
            "\tpermission p \n" +
            "WHERE\n" +
            "\tur.userId = u.id \n" +
            "\tAND ur.roleId = r.id \n" +
            "\tAND r.id = rp.roleId \n" +
            "\tAND rp.permissionId = p.id \n" +
            "\tAND u.username = #{username}")
    List<Role> findByUserName(String username) throws Exception;

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
    List<Permission> findPermissions();

    /**
     * 添加角色对应的权限
     */
    void addPermissionToRole(@Param("roleId") String roleId, @Param("permissionId") String permissionId);

    /**
     * 查询角色拥有的权限
     */
    List<Integer> findPermissionByRoleId(String roleId);
}
