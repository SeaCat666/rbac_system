package com.dsc.controller;

import com.dsc.domain.Permission;
import com.dsc.domain.Role;
import com.dsc.domain.UserInfo;
import com.dsc.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/role")
public class RoleController {
    @Resource
    private IRoleService roleService;

    @RequestMapping("/findAll.do")
    public ModelAndView findAll() throws Exception {
        List<Role> roleList = roleService.findAll();
        ModelAndView mv = new ModelAndView();
        mv.addObject("roleList", roleList);
        mv.setViewName("role-list");
        return mv;
    }
    /**
     * 添加角色
     */
    @RequestMapping("/save.do")
    public String save(Role role) {
        roleService.save(role);
        return "redirect:findAll.do";
    }

    @RequestMapping("/deleteRole")
    public String deleteRole(@RequestParam("id") String roleId){
        Integer roleUserByRoleId = roleService.findRoleUserByRoleId(roleId);
        if (roleUserByRoleId!=0){
            return "failer";
        }
        roleService.delPermissionByRole(roleId);
        roleService.delRole(roleId);
        return "forward:/role/findAll.do";
    }

    /**
     * 查询角色详情
     */
    @RequestMapping("/findById.do")
    public ModelAndView findById(String id) throws Exception {
        Role role = roleService.findById(id);
        ModelAndView mv = new ModelAndView();
        mv.addObject("role", role);
        mv.setViewName("role-show");
        return mv;
    }

    /**
     * 根据roleId查询role，并查询出可以添加的权限
     */
    @RequestMapping("/findRoleByIdAndAllPermission.do")
    public ModelAndView findRoleByIdAndAllPermission(@RequestParam(name = "id", required = true) String roleId) throws Exception {
        ModelAndView mv = new ModelAndView();
        //根据roleId查询role
        Role role = roleService.findRoleInfoById(roleId);
        //根据roleId查询可以添加的权限
        List<Permission> otherPermissions = roleService.findPermissions(roleId);

        //查询角色对应的权限
        List<Integer> pCheck =roleService.findPermissionByRoleId(roleId);

        mv.addObject("role", role);
        mv.addObject("permissionList", otherPermissions);

        mv.addObject("pCheck", pCheck);
        mv.setViewName("role-permission-add");
        return mv;

    }

    /**
     * 给角色添加权限
     */
    @RequestMapping("/addPermissionToRole")
    public String addPermissionToRole(@RequestParam(name = "roleId", required = true) String roleId, @RequestParam(name = "ids", required = true,defaultValue = "null") String[] permissionIds) throws Exception {
        roleService.delPermissionByRole(roleId);
        if(!permissionIds[0].equals("null")){
            roleService.addPermissionToRole(roleId, permissionIds);
        }
        return "redirect:findAll.do";
    }
}
