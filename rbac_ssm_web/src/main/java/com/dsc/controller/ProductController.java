package com.dsc.controller;

import com.dsc.domain.Product;
import com.dsc.service.IProductService;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {
    @Resource
    private IProductService productService;

    @RequestMapping("findAll.do")
    public ModelAndView findAll(@RequestParam(name = "page", required = true, defaultValue = "1") Integer page,
                                @RequestParam(name = "size", required = true, defaultValue = "3") Integer size) throws Exception {
        //先查询，才能将总条数存到page对象上
        List<Product> list = productService.findAll(page, size);
        PageInfo<Product> info = new PageInfo<Product>(list);
        ModelAndView modelAndView = new ModelAndView();
        //查询全部
        modelAndView.addObject("pageInfo", info);
        //跳转到jsp页面，前缀和后缀在springmvc.xml中的视图解析器中设置了
        modelAndView.setViewName("product-list1");
        return modelAndView;
    }

    @RequestMapping("save.do")
    public String save(Product product) throws Exception {
        productService.insertProduct(product);
        return "forward:/product/findAll.do";
    }

    @RequestMapping("delByIds.do")
    public String delByIds(String ids) throws Exception {
        String[] str = ids.split(",");
        for (String s : str
        ) {
            productService.delByIds(Integer.parseInt(s));
        }
        return "forward:/product/findAll.do";
    }

    @RequestMapping("updateById.do")
    public ModelAndView updateByIds(String id) throws Exception {
        ModelAndView mv = new ModelAndView();
        Product byId = productService.findById(Integer.parseInt(id));
        mv.addObject("product", byId);
        mv.setViewName("product-update");
        return mv;
    }

    @RequestMapping("doUpdateById.do")
    public String doUpdateById(Product product) throws Exception {
        productService.updateById(product);
        return "forward:/product/findAll.do";
    }

}