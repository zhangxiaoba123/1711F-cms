package com.zxy.cms.service;

import java.awt.color.CMMException;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zxy.cms.dao.CollectMapper;
import com.zxy.cms.domain.Collect;
import com.zxy.common.utils.StringUtil;



@Service
public class CollectServiceImpl  implements CollectService{
	@Resource
	CollectMapper collectMapper;

	@Override
	public int insert(Collect collect) {
		if(!StringUtil.isHttpUrl(collect.getUrl()))
			throw new CMMException("不是合法的url");
		return collectMapper.insert(collect);
	}

	@Override
	public int delete(Integer id) {
		// TODO Auto-generated method stub
		return collectMapper.delete(id);
	}

	@Override
	public List<Collect> selects(Integer userId) {
		// TODO Auto-generated method stub
		return collectMapper.selects(userId);
	}

	@Override
	public Collect selectByTitleAndUserId(String title, Integer userId) {
		// TODO Auto-generated method stub
		return collectMapper.selectByTitleAndUserId(title, userId);
	}

}
