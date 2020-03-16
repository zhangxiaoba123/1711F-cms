package com.zxy.cms.service;

import java.util.List;

import com.zxy.cms.domain.Category;
import com.zxy.cms.domain.Channel;

public interface ChannelService {
	/**
	 * 
	 * @Title: selects 
	 * @Description: 查询所有栏目
	 * @return
	 * @return: List<Channel>
	 */
	List<Channel> selects();

	List<Category> selectsByChannelId(Integer channelId);
}
