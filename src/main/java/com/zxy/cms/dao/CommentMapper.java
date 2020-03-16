package com.zxy.cms.dao;

import java.util.List;

import com.zxy.cms.domain.Article;
import com.zxy.cms.domain.Comment;



public interface CommentMapper {
	
	/**
	 * 
	 * @Title: insert 
	 * @Description: 增加评论
	 * @param comment
	 * @return
	 * @return: int
	 */
	int insert(Comment comment);
	/**
	 * 
	 * @Title: selects 
	 * @Description: 根据文章查询文章评论
	 * @param article
	 * @return
	 * @return: List<Comment>
	 */
	List<Comment> selects(Article article);
	
	/**
	 * 
	 * @Title: updateArticle 
	 * @Description: 评论数增加1
	 * @param articleId
	 * @return
	 * @return: int
	 */
	int updateArticle(Integer articleId);
	
	/**
	 * 
	 * @Title: selectsByCommentNum 
	 * @Description: 评论数量排序
	 * @return
	 * @return: List<Article>
	 */
	List<Article> selectsByCommentNum();

}
