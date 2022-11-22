package com.dsc.service.impl;

import com.dsc.dao.IOrdersDao;
import com.dsc.dao.IProductDao;
import com.dsc.domain.Orders;
import com.dsc.domain.Product;
import com.dsc.service.IOrdersService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional
public class IOrdersServiceImpl implements IOrdersService {
    @Resource
    private IOrdersDao ordersDao;
    @Override
    public List<Orders> findAll(int page, int size) throws Exception {
        //开启分页
        PageHelper.startPage(page,size);
        return ordersDao.findAll();
    }

    @Override
    public void insertOrders(Orders orders) {
        ordersDao.insertOrders(orders);
    }


    @Override
    public void delByIds(int id) {
        ordersDao.delByIds(id);
    }

    @Override
    public Orders findById(Integer id) throws Exception {
        return ordersDao.findById(id);
    }

    @Override
    public void updateById(Orders orders) {
        ordersDao.updateById(orders);
    }
}
