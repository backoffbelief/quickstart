package com.kael.mapper;

import com.kael.model.Orders;

public interface OrdersMapper {
    int deleteByPrimaryKey(String orderId);

    int insert(Orders record);

    int insertSelective(Orders record);

    Orders selectByPrimaryKey(String orderId);

    int updateByPrimaryKeySelective(Orders record);

    int updateByPrimaryKey(Orders record);
    
    Orders selectOrdersFetchPerson(String oid);
}