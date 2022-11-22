package com.dsc.service;

import com.dsc.domain.Orders;
import com.dsc.domain.Product;

import java.util.List;

public interface IOrdersService {
    List<Orders> findAll(int page,int size) throws Exception;

    void insertOrders(Orders orders);

    void delByIds(int ids);

    Orders findById(Integer id) throws Exception;

    void updateById(Orders orders);
}