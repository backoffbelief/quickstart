package com.kael.mapper;

import com.kael.model.Person;

public interface PersonMapper {
    int deleteByPrimaryKey(String personId);

    int insert(Person record);

    int insertSelective(Person record);

    Person selectByPrimaryKey(String personId);

    int updateByPrimaryKeySelective(Person record);

    int updateByPrimaryKey(Person record);
    
    Person selectPersonFetchOrder(int pid);
}