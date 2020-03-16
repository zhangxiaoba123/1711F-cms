package com.zxy.cms.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zxy.cms.dao.ArticleMapper;
import com.zxy.cms.domain.Article;

@Service
public class ArticleServiceImpl implements ArticleService{

	@Resource
	private ArticleMapper articlemapper;
	
	@Override
	public int insert(Article article) {
		// TODO Auto-generated method stub
		return articlemapper.insert(article);
	}

	@Override
	public PageInfo<Article> selects(Article article, Integer page, Integer pageSize) {

		PageHelper.startPage(page, pageSize);
		List<Article> list=articlemapper.selects(article);
		
		return new PageInfo<Article>(list);
	}

	@Override
	public Article select(Integer id) {
		// TODO Auto-generated method stub
		return articlemapper.select(id);
	}

	@Override
	public int update(Article article) {
		// TODO Auto-generated method stub
		return articlemapper.update(article);
	}

}
