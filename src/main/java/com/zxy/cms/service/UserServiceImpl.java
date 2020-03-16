package com.zxy.cms.service;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zxy.cms.dao.UserMapper;
import com.zxy.cms.domain.User;
import com.zxy.cms.util.CMSException;
import com.zxy.cms.util.Md5Util;
import com.zxy.common.utils.StringUtil;

@Service
public class UserServiceImpl implements UserService{

	@Resource
	private UserMapper userMapper;
	
	
	@Override
	public PageInfo<User> selects(User user, Integer page, Integer pageSize) {

		PageHelper.startPage(page,pageSize);
		List<User> list = userMapper.selects(user);
		
		
		return new PageInfo<User>(list);
	}


	@Override
	public int update(User user) {
		// TODO Auto-generated method stub
		return userMapper.update(user);
	}


	@Override
	public int insert(User user) {
		// 1 用户名不能为空
		if (!StringUtil.hasText(user.getUsername()))
			throw new CMSException("用户名不能为空");
		if (!(user.getUsername().length()>=2 && user.getUsername().length()<=10)) 
			throw new CMSException("用户名的长度在2-10之间");
		User findUser = this.selectByUsername(user.getUsername());
		
		if (null!=findUser) {
			throw new CMSException("用户名已经被注册.");
		}
		//2密码校验
		if (!StringUtil.hasText(user.getPassword()))
			throw new CMSException("密码不能为空");
		if (!(user.getPassword().length() >= 6 && user.getPassword().length() <= 10))
			throw new CMSException("密码的长度在6-10之间");
		 
		// 3确认密码 
		if (!StringUtil.hasText(user.getRepassword()))
			throw new CMSException("确认密码不能为空");
		if (!user.getRepassword().equals(user.getPassword()))
			throw new CMSException("两次密码输入不一致");

		// 4 对用户密码进行加密煮处理
		user.setPassword(Md5Util.encode(user.getPassword()));

		user.setCreated(new Date());//注册时间
		user.setNickname(user.getUsername());
		user.setLocked("0");
		
		return userMapper.insert(user);
		
	}


	@Override
	public User selectByUsername(String username) {
		// TODO Auto-generated method stub
		return userMapper.selectByUsername(username);
	}

	public User login(User user) {
		// 1 校验 用户名不能为空"
		if (!StringUtil.hasText(user.getUsername()))
			throw new CMSException("用户名不能为空");
		// 2 检查用户名是否存在
		User u = this.selectByUsername(user.getUsername());
		if (null == u) {
			throw new CMSException("该用户名不存在");
		}
		// 3 比较密码是否一致
		if (!Md5Util.encode(user.getPassword()).equals(u.getPassword()))
			throw new CMSException("密码不正确，请重新录入");
		
		if(u.getLocked().equals("1"))
			throw new CMSException("当前账户被禁用，不能登录");

		return u;

	}
	

}
