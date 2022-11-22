package com.dsc.controller;

import com.dsc.domain.Product;
import com.dsc.domain.Role;
import com.dsc.domain.UserInfo;
import com.dsc.service.IProductService;
import com.dsc.service.IUsersInfoService;
import com.github.pagehelper.PageInfo;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.support.HttpRequestHandlerServlet;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.net.http.HttpRequest;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserInfoController {
    @Resource
    private IUsersInfoService usersInfoService;

    @RequestMapping("findAll.do")
    @PreAuthorize("hasAuthority('超级管理员')")
    public ModelAndView findAll(@RequestParam(name = "page", required = true, defaultValue = "1") Integer page,
                                @RequestParam(name = "size", required = true, defaultValue = "3") Integer size) throws Exception {
        //先查询，才能将总条数存到page对象上
        List<UserInfo> list = usersInfoService.findAll(page, size);
        PageInfo<UserInfo> info = new PageInfo<UserInfo>(list);
        ModelAndView modelAndView = new ModelAndView();
        //查询全部
        modelAndView.addObject("pageInfo", info);
        //跳转到jsp页面，前缀和后缀在springmvc.xml中的视图解析器中设置了
        modelAndView.setViewName("user-list");
        return modelAndView;
    }

    @RequestMapping("save.do")
    public String save(UserInfo userInfo) throws Exception {
        usersInfoService.insertUserInfo(userInfo);
        return "redirect:/user/findAll.do";
    }
    /**
     * 查询用户详情
     */
    @RequestMapping("/findById.do")
    public ModelAndView findById(String id) throws Exception {
        UserInfo userInDb = usersInfoService.findById(id);
        ModelAndView mv = new ModelAndView();
        mv.addObject("user", userInDb);
        mv.setViewName("user-show");
        return mv;
    }

    /**
     * 查询用户以及用户可以添加的角色
     */
    @RequestMapping("/findUserByIdAndAllRole.do")
    public ModelAndView findUserByIdAndAllRole(@RequestParam(name = "id") String userid) throws Exception {
        ModelAndView mv = new ModelAndView();
        //1.根据用户id查询用户
        UserInfo userInfo = usersInfoService.findById(userid);
        //2.查询角色
        List<Role> otherRoles = usersInfoService.findRoles();
        //3.查询用户已有的角色
        List<Integer> UserRoles = usersInfoService.findRolesByUserId(userid);
        mv.addObject("user", userInfo);
        mv.addObject("UserRoles", UserRoles);
        mv.addObject("roleList", otherRoles);
        mv.setViewName("user-role-add");
        return mv;
    }

    /**
     * 给用户添加角色
     */
    @RequestMapping("/addRoleToUser.do")
    public String addRoleToUser(@RequestParam(name = "userId", required = true) String userId, @RequestParam(name = "ids", required = true) String[] roleIds) {
        usersInfoService.delOldRole(userId);
        usersInfoService.addRoleToUser(userId, roleIds);
        return "redirect:findAll.do";
    }

}
