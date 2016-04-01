package com.kael.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kael.mapper.OrdersMapper;
import com.kael.mapper.PersonMapper;
import com.kael.mapper.UserMapper;
import com.kael.model.Person;
import com.kael.model.User;
import com.kael.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService{

	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private PersonMapper personMapper;
	
	@Autowired
	private OrdersMapper ordersMapper;
	
	@Override
	public List<User> getAllUser(){
		return userMapper.getAllUser();
	}
	
	@Override
	public Person selectPersonFetchOrder(int pid){
		return personMapper.selectPersonFetchOrder(pid);
	}
}
