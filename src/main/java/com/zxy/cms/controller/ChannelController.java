package com.zxy.cms.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zxy.cms.domain.Category;
import com.zxy.cms.domain.Channel;
import com.zxy.cms.service.ChannelService;

@Controller
@RequestMapping("channel")
public class ChannelController {
	@Resource
	ChannelService channelService;
	
	/**
	 * 
	 * @Title: channels 
	 * @Description: 查询所有的栏目
	 * @return
	 * @return: List<Channel>
	 */
	@ResponseBody
	@RequestMapping("channels")
	public List<Channel> channels(){
		return channelService.selects();
	}

	/**
	 * 
	 * @Title: selectsByChannelId 
	 * @Description: 根据栏目查询分类
	 * @return
	 * @return: List<Channel>
	 */
	@ResponseBody
	@RequestMapping("selectsByChannelId")
	public List<Category> selectsByChannelId(Integer channelId){
		return channelService.selectsByChannelId(channelId);
	}
}
