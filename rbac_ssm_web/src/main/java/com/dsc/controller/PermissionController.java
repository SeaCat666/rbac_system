package com.dsc.controller;

import com.dsc.domain.Permission;
import com.dsc.service.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/permission")
public class PermissionController {

    @Autowired
    private IPermissionService permissionService;

    /**
     * 查询所有资源权限
     */
    @RequestMapping("/findAll")
    public ModelAndView findAll() throws Exception {
        ModelAndView mv=new ModelAndView();
        List<Permission> permissionList = permissionService.findAll();
        mv.addObject("permissionList",permissionList);
        mv.setViewName("permission-list");
        return mv;
    }
    /**
     * 添加资源权限
     */
    @RequestMapping("/save")
    public String save(Permission permission) throws Exception {
        permissionService.save(permission);
        return "redirect:findAll.do";
    }

    /**
     * 根据id查询资源权限详情
     */
    @RequestMapping("/findById")
    public ModelAndView findById(String id) throws Exception {
        Permission permission=  permissionService.findById(id);
        ModelAndView mv=new ModelAndView();
        mv.setViewName("permission-show");
        mv.addObject("permission",permission);
        return mv;
    }
    /**
     * 删除资源权限
     */
    @RequestMapping("/deletePermission")
    public String deletePermission(@RequestParam(name = "id", required = true) String pId) throws Exception {
        //根据权限id先查询该权限是否被角色关联
        int count = permissionService.findRolePermission(pId);
        if (count>0){
            return "redirect:/permission-delete-failer.jsp";
        }
        permissionService.deleteById(pId);
        return "redirect:findAll.do";
    }
}
