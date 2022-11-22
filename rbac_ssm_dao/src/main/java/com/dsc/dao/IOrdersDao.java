package com.dsc.dao;


import com.dsc.domain.Member;
import com.dsc.domain.Orders;
import com.dsc.domain.Product;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface IOrdersDao {
    List<Orders> findAll();

    @Insert("insert into orders values(null,#{orderNum},#{orderTime},#{peopleCount},#{orderDesc},#{payType},#{orderStatus})")
    void insertOrders(Orders orders);

    void delByIds(int id);

    //通过订单主键ID查询订单详情(多表关联查询)
    @Select("select * from ORDERS where id = #{ordersId}" )  //oracle数据库TABLE名不区分大小写
    @Results({
            //为了网页显示的后缀Str类型的实体类属性不用对应出来
            @Result(property ="id",column = "id",id = true ),  //主键声明id = true
            @Result(property ="orderNum",column = "orderMum"),
            @Result(property ="orderTime",column = "orderTime"),
            @Result(property ="orderStatus",column = "orderStatus"),
            @Result(property ="peopleCount",column = "peopleCount"),
            @Result(property ="payType",column = "payType"),
            @Result(property ="orderDesc",column = "orderDesc"),
            //多表关联查询，声明“引用实体类”的封装通过：javaType= Xxx实体类.class
            @Result(property ="product",column = "productId",javaType = Product.class ,one =@One(select = "com.dsc.dao.IProductDao.findById")),
            @Result(property ="member",column = "memberId",javaType = Member.class ,one =@One(select = "com.dsc.dao.IMemberDao.findById")),
            //通过中间表查询多对多关系，返回一个其它实体类的List集合
            @Result(property = "travellers",column ="id",javaType = java.util.List.class,many = @Many(select = "com.dsc.dao.ITravellerDao.findByOrdersId"))
    })
    Orders findById(Integer ordersId)throws Exception;

    void updateById(Orders orders);
}