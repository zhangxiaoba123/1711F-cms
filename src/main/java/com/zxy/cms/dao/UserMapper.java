package com.zxy.cms.dao;

import java.util.List;

import com.zxy.cms.domain.User;

public interface UserMapper {
	/**
	 * 
	 * @Title: selects 
	 * @Description: 用户列表
	 * @param user
	 * @return
	 * @return: List<User>
	 */
	List<User> selects(User user);
	/**
	 * 
	 * @Title: updateUser 
	 * @Description: 更新用户
	 * @param user
	 * @return
	 * @return: int
	 */
	int update(User user);
	
	/**
	 * 
	 * @Title: insert 
	 * @Description: 注册
	 * @param user
	 * @return
	 * @return: int
	 */
	int insert(User user);
	/**
	 * 
	 * @Title: selectByUsername 
	 * @Description: TODO
	 * @param username
	 * @return
	 * @return: User
	 */
	User selectByUsername(String username);
}
