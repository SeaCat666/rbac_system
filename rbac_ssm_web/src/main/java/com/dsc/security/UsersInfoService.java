package com.dsc.security;

import com.dsc.dao.IUsersInfoDao;
import com.dsc.domain.Permission;
import com.dsc.domain.Role;
import com.dsc.domain.UserInfo;
import com.dsc.service.IUsersInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
@Service
public class UsersInfoService implements UserDetailsService {
    @Resource
    private IUsersInfoService usersInfoService;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        //2.从数据库查找
        UserInfo users = usersInfoService.findByUserName(userName);
        if (null!= users){
            //2 定义用户权限集合
            List<GrantedAuthority> authorityList = new ArrayList<GrantedAuthority>();
            SimpleGrantedAuthority authority = null;
            //3 获取用户所拥有的角色
            List<Role> roles = users.getRoles();
            if (null!=roles){
                for (Role role:users.getRoles()
                ) {
                    //3.1 授权角色：角色关键字授予角色,hasRole、hasAnyRole
                    authority = new SimpleGrantedAuthority("ROLE_"+role.getRoleName());
                    authorityList.add(authority);
                    //4 获取角色下的所有权限
                    List<Permission> permissions = role.getPermissions();
                    if(null != permissions){
                        for (Permission permission : permissions) {
                            //4.1 授予权限：权限关键字授予权限,hasAuthority、hasAnyAuthority
                            authority = new SimpleGrantedAuthority(permission.getPermissionName());
                            authorityList.add(authority);
                        }
                    }
                }
            }
            //5 返回security需要的Userdetails登录信息，会保存到session
            return new User(userName,users.getPassword(),authorityList);
        }
        return null;
    }

}
