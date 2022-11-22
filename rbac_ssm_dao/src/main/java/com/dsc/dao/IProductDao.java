package com.dsc.dao;


import com.dsc.domain.Product;
import org.apache.ibatis.annotations.Insert;

import java.util.List;

public interface IProductDao {
    List<Product> findAll();

    @Insert("insert into product(productNum,productName,cityName,departureTime,productPrice,productDesc,productStatus) values(#{productNum},#{productName},#{cityName},#{departureTime},#{productPrice},#{productDesc},#{productStatus})")
    void insertProduct(Product product);

    void delByIds(int id);

    Product findById(Integer id);

    void updateById(Product product);
}