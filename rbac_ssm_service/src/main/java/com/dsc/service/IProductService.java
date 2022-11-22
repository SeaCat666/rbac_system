package com.dsc.service;
import com.dsc.domain.Product;
import java.util.List;

public interface IProductService {

    List<Product> findAll(int page,int size) throws Exception;

    void insertProduct(Product product);

    void delByIds(int ids);

    Product findById(Integer id);

    void updateById(Product product);
}