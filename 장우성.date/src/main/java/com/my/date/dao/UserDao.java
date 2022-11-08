package com.my.date.dao;

import java.util.List;

import com.my.date.domain.User;

public interface UserDao {
	List<User> selectUsers();
	User selectUser(User user);
	User selectUser(String id);
	String selectEmail(String email);
	int insertUser(User user);
	void updateUser(User user);
	User selectId(User user);
}
