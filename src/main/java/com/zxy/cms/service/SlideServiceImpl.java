package com.zxy.cms.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zxy.cms.dao.SlideMapper;
import com.zxy.cms.domain.Slide;

@Service
public class SlideServiceImpl implements SlideService{

	@Resource
	SlideMapper slideMapper;
	
	@Override
	public List<Slide> selects() {
		// TODO Auto-generated method stub
		return slideMapper.selects();
	}

}
