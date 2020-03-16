package com.zxy.cms.controller;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.zxy.cms.domain.Article;
import com.zxy.cms.domain.Category;
import com.zxy.cms.domain.Channel;
import com.zxy.cms.domain.Collect;
import com.zxy.cms.domain.Comment;
import com.zxy.cms.domain.Slide;
import com.zxy.cms.domain.User;
import com.zxy.cms.service.ArticleService;
import com.zxy.cms.service.ChannelService;
import com.zxy.cms.service.CollectService;
import com.zxy.cms.service.CommentService;
import com.zxy.cms.service.SlideService;
/**
 * 
 * @ClassName: IndexController 
 * @Description: 系统首页入口
 * @author: admin
 * @date: 2020年3月9日 下午4:04:32
 */
@Controller
public class IndexController {

	@Resource
	private ArticleService articleService;
	@Resource
	private ChannelService channelService;
	
	@Resource
	private SlideService slideService;
	
	@Resource
	private CommentService commentService;
	
	@Resource
	private CollectService collectService;
	
	@RequestMapping(value = {"","/","index"})
	public String index(Model m,Article article ,@RequestParam(defaultValue = "1")Integer page,@RequestParam(defaultValue = "5")Integer pageSize) {
		
		article.setStatus(1);
		article.setDeleted(0);
		m.addAttribute("article", article);
		
		//查询左
		List<Channel> channels = channelService.selects();
		
		m.addAttribute("channels", channels);
		
		if (article.getChannelId()!=null) {
			List<Category> categorys = channelService.selectsByChannelId(article.getChannelId());			
			m.addAttribute("categorys", categorys);
			
		}
		
		if (article.getChannelId()==null) {
			article.setHot(1);
			List<Slide> slides = slideService.selects();
			m.addAttribute("slides", slides);
		}
		//所有文章
		PageInfo<Article> info = articleService.selects(article, page, pageSize);
		m.addAttribute("info", info);
		
		Article article2 = new Article();
		article2.setStatus(1);//
		article2.setDeleted(0);
		PageInfo<Article> lastArticle = articleService.selects(article2, 1, 10);
		m.addAttribute("lastArticle", lastArticle);
		return "index/index";
		
	} 
	/**
	 * 
	 * @Title: articleDetail 
	 * @Description: 文章详情
	 * @param id
	 * @return
	 * @return: String
	 */
	@RequestMapping("articleDetail")
	public String articleDetail(HttpSession session ,Integer id,Model model,@RequestParam(defaultValue = "1") Integer page,@RequestParam(defaultValue = "5")Integer pageSize) {
		Article article = articleService.select(id);
		model.addAttribute("article", article);
		// 查询出当前文章的评论信息
		PageInfo<Comment> info = commentService.selects(article, page, pageSize);
		
		// 查询所有文章的评论
		PageInfo<Article> info2 = commentService.selectsByCommentNum(1, 10);
		model.addAttribute("info", info);
		model.addAttribute("info2", info2);
		//查询该文章是否被收藏过
		
		
		User user = (User) session.getAttribute("user");
		  Collect collect =null;
		if(null !=user) {//如果用户已经登录，则查询是否没收藏过
		   collect = collectService.selectByTitleAndUserId(article.getTitle(), user.getId());
		}
		model.addAttribute("collect", collect);
		
		
		
		return "index/articleDetail";
	}

	
	//收藏文章
		@ResponseBody
		@RequestMapping("deleteCollect")
		public boolean collect(Integer id) {
			return collectService.delete(id) >0;
		}
	
	
	
	//收藏文章
	@ResponseBody
	@RequestMapping("collect")
	public boolean collect(Collect collect,HttpSession session) {
		User user = (User) session.getAttribute("user");
		if(null ==user)
		return false;//没有登录的用户不能收藏
		collect.setUser(user);
		collect.setCreated(new Date());
		return collectService.insert(collect)>0;
	}
	
	//增加评论
	@ResponseBody
	@RequestMapping("addComment")
	public boolean addComment(Comment comment,Integer articleId,HttpSession session) {
		User user = (User) session.getAttribute("user");
		if(null ==user)
		return false;//没有登录的用户不能评论
		comment.setUserId(user.getId());
		comment.setArticleId(articleId);
		comment.setCreated(new Date());
		
		return commentService.insert(comment)>0;
	}
}
