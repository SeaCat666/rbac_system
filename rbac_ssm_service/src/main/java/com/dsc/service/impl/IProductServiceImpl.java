package com.dsc.service.impl;

import com.dsc.dao.IProductDao;
import com.dsc.domain.Product;
import com.dsc.service.IProductService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional
public class IProductServiceImpl implements IProductService {
    @Resource
    private IProductDao productDao;

    @Override
    public List<Product> findAll(int page,int size) {
        //开启分页
        PageHelper.startPage(page,size);
        return productDao.findAll();
    }

    @Override
    public void insertProduct(Product product) {
        productDao.insertProduct(product);
    }


    @Override
    public void delByIds(int id) {
        productDao.delByIds(id);
    }

    @Override
    public Product findById(Integer id) {
        return productDao.findById(id);
    }

    @Override
    public void updateById(Product product) {
        productDao.updateById(product);
    }
}