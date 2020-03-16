package com.zxy.cms.service;


import com.github.pagehelper.PageInfo;
import com.zxy.cms.domain.User;

public interface UserService {

	/**
	 * 
	 * @Title: selects 
	 * @Description: 用户列表
	 * @param user
	 * @return
	 * @return: List<User>
	 */
	PageInfo<User> selects(User user,Integer page,Integer pageSize);

	/**
	 * 
	 * @Title: updateUser 
	 * @Description: 更新用户
	 * @param user
	 * @return
	 * @return: int
	 */
	int update(User user);

	int insert(User user);
	
	User selectByUsername(String username);
	
	User login(User user);
}
