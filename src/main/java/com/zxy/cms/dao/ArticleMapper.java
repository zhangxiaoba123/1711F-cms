package com.zxy.cms.dao;

import java.util.List;

import com.zxy.cms.domain.Article;

public interface ArticleMapper {

	int insert(Article article); 
	
	List<Article> selects(Article article);
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
