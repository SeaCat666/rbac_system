package com.dsc.controller;

import com.dsc.domain.Orders;
import com.dsc.domain.Product;
import com.dsc.service.IOrdersService;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/orders")
public class OrdersController {
    @Resource
    private IOrdersService ordersService;

    @RequestMapping("findAll.do")
    public ModelAndView findAll(@RequestParam(name = "page", required = true, defaultValue = "1") Integer page,
                                @RequestParam(name = "size", required = true, defaultValue = "3") Integer size) throws Exception {
        //先查询，才能将总条数存到page对象上
        List<Orders> list = ordersService.findAll(page, size);
        PageInfo<Orders> info = new PageInfo<Orders>(list);
        ModelAndView modelAndView = new ModelAndView();
        //查询全部
        modelAndView.addObject("pageInfo", info);
        //跳转到jsp页面，前缀和后缀在springmvc.xml中的视图解析器中设置了
        modelAndView.setViewName("orders-list");
        return modelAndView;
    }

    @RequestMapping("save.do")
    public String save(Orders orders) throws Exception {
        ordersService.insertOrders(orders);
        return "forward:/orders/findAll.do";
    }

    @RequestMapping("delByIds.do")
    public String delByIds(String ids) throws Exception {
        String[] str = ids.split(",");
        for (String s : str
        ) {
            ordersService.delByIds(Integer.parseInt(s));
        }
        return "forward:/orders/findAll.do";
    }

    @RequestMapping("updateById.do")
    public ModelAndView updateByIds(String id) throws Exception {
        ModelAndView mv = new ModelAndView();
        Orders byId = ordersService.findById(Integer.parseInt(id));
        mv.addObject("orders", byId);
        mv.setViewName("orders-update");
        return mv;
    }

    @RequestMapping("doUpdateById.do")
    public String doUpdateById(Orders orders) throws Exception {
        ordersService.updateById(orders);
        return "forward:/orders/findAll.do";
    }

    @RequestMapping("findById.do")
    public ModelAndView findById(Integer id) throws Exception {
        ModelAndView mv = new ModelAndView();
        Orders byId = ordersService.findById(id);
        mv.addObject("orders",byId);
        mv.setViewName("orders-show");
        return mv;
    }
}