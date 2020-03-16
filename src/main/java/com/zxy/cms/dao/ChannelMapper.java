package com.zxy.cms.dao;
/**
 * 
 * @ClassName: ChannelMapper 
 * @Description: 栏目
 * @author: admin
 * @date: 2020年3月5日 下午4:39:48
 */


import java.util.List;

import com.zxy.cms.domain.Category;
import com.zxy.cms.domain.Channel;

public interface ChannelMapper {
	/**
	 * 
	 * @Title: selects 
	 * @Description: 查询所有栏目
	 * @return
	 * @return: List<Channel>
	 */
	List<Channel> selects();
	/**
	 * 
	 * @Title: selectsByChannelId 
	 * @Description: 根据栏目查询分类
	 * @param channelId
	 * @return
	 * @return: List<Category>
	 */
	List<Category> selectsByChannelId(Integer channelId);

}
