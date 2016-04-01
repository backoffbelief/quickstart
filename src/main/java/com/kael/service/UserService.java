package com.kael.service;

import java.util.List;

import com.kael.model.Person;
import com.kael.model.User;

public interface UserService {

	List<User> getAllUser();

	Person selectPersonFetchOrder(int pid);

}
