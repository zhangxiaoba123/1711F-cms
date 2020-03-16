package com.zxy.cms.service;


import com.github.pagehelper.PageInfo;
import com.zxy.cms.domain.Article;

public interface ArticleService {

	int insert(Article article); 
		
	PageInfo<Article> selects(Article article,Integer page,Integer pageSize);
	/**
	 * 
	 * @Title: select 
	 * @Description: 单个文章
	 * @param id
	 * @return
	 * @return: Article
	 */
	Article select(Integer id);		
	/**
	 * 
	 * @Title: update 
	 * @Description: 更新
	 * @param article
	 * @return
	 * @return: int
	 */
	int update(Article article);
}
