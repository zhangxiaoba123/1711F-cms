package com.zxy.cms.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zxy.cms.domain.User;
import com.zxy.cms.service.UserService;
import com.zxy.cms.util.CMSException;
import com.zxy.cms.util.Result;


@RequestMapping("passport")
@Controller
public class PassportController {
	
	@Resource
	private UserService userService;
	
	@GetMapping("reg")
	public String reg() {
		return "passport/reg";
	}
	
	@PostMapping("reg")
	@ResponseBody
	public Result<User> reg(User user,Model model) {
		Result<User> result=  new Result<User>();
		try {
			 if(userService.insert(user)>0) {
				 result.setCode(200);
				 result.setMsg("注册成功");
			 }
		} catch (CMSException e) {
			 e.printStackTrace();
			 result.setCode(300);
			 result.setMsg("注册失败:"+e.getMessage());
			
		}catch (Exception e) {
			e.printStackTrace();
			 result.setCode(500);
			 result.setMsg("注册失败，系统出现不可预知异常，请联系管理员");
		}
		return result;
	
	}
	/**
	 * 
	 * @Title: login 
	 * @Description: 普通登入
	 * @return
	 * @return: String
	 */
	@GetMapping("login")
	public String login() {
		return "passport/login";
		
	}
	
	/**
	 * 
	 * @Title: login 
	 * @Description: 管理员登入
	 * @return
	 * @return: String
	 */
	@GetMapping("admin/login")
	public String adminLogin() {
		return "passport/adminLogin";
		
	}
	
	/**
	 * 
	 * @Title: login 
	 * @Description:执行登录
	 * @param user
	 * @param model
	 * @return
	 * @return: Result<User>
	 */
	@PostMapping("login")
	@ResponseBody
	public Result<User> login(User formUser,Model model,HttpSession session) {
		Result<User> result=  new Result<User>();
		try {
			
			User user = userService.login(formUser);
			if(null !=user) {
				result.setCode(200);
				result.setMsg("登录成功");
				if(user.getRole()==0) {
					session.setAttribute("user", user);
				}else {
					session.setAttribute("admin", user);
				}
			}
			
		
		} catch (CMSException e) {
			 e.printStackTrace();
			 result.setCode(300);
			 result.setMsg("登录失败:"+e.getMessage());
			
		}catch (Exception e) {
			e.printStackTrace();
			 result.setCode(500);
			 result.setMsg("登录失败，系统出现不可预知异常，请联系管理员");
		}
		return result;
		
	}
	/**
	 * 
	 * @Title: logout 
	 * @Description: 注销用户
	 * @param session
	 * @return
	 * @return: String
	 */
	@GetMapping("logout")
	public String logout(HttpSession session) {
		
		session.invalidate();
		
		return "redirect:/";
		
	}
	/**
	 * 
	 * @Title: checkName 
	 * @Description: 检查注册用户是否存在
	 * @param username
	 * @return
	 * @return: boolean
	 */
	@ResponseBody
	@PostMapping("checkName")
	public boolean checkName(String username) {
		
		return userService.selectByUsername(username) == null;
		
	}
	
}
