package cn.com.jgre.service;

import java.util.List;

import cn.com.jgre.entity.ArticleInfo;









public interface ArticleService {

	
	//查询跟分页查
	public List<ArticleInfo> getArticles(ArticleInfo a, int offset, int limit);
	
	//根据id查询
	public ArticleInfo getArticleById(String id);

	//删除文章
	public int deleteArticle(ArticleInfo a);
	
	//更新文章
	public int updateArticle(ArticleInfo a);
	
	//添加文章
	public int addArticle(ArticleInfo a);
	
	//查询总数
	public int getTotalArticle(ArticleInfo a);
	
	//更新所有needshow为n
	public void updateUse(ArticleInfo a);
	
	
	//根据浏览数
	public List<ArticleInfo> getArticleByViews(ArticleInfo a);
	
	//查询跟分页查
    public List<ArticleInfo> getArticlesAll(ArticleInfo a, int offset, int limit);
}
