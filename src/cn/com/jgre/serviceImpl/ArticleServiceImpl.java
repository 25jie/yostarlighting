package cn.com.jgre.serviceImpl;

import java.util.List;

import org.apache.ibatis.session.RowBounds;


import cn.com.jgre.dao.ArticleDao;
import cn.com.jgre.entity.ArticleInfo;

import cn.com.jgre.service.ArticleService;







public class ArticleServiceImpl implements ArticleService{
    ArticleDao articleDao;
	public int deleteArticle(ArticleInfo a) {
		return articleDao.deleteRecordById(a.getArticle_id());
	}

	public ArticleInfo getArticleById(String id) {
		return (ArticleInfo)articleDao.getRecordById(id);
	}

	@SuppressWarnings("unchecked")
    public List<ArticleInfo> getArticles(ArticleInfo a, int offset, int limit) {
		RowBounds rb=new RowBounds(offset,limit);
		return  articleDao.getRecords(a, rb);
	}

	public int addArticle(ArticleInfo a) {
	
		return articleDao.insertRecord(a);
	}

	public int updateArticle(ArticleInfo a) {
		
		return articleDao.updateRecord(a);
	}
	
	public ArticleDao getArticleDao() {
		return articleDao;
	}

	public void setArticleDao(ArticleDao articleDao) {
		this.articleDao = articleDao;
	}

    @Override
    public int getTotalArticle(ArticleInfo a) {
       
        return articleDao.getTotalRecord(a);
    }

    @Override
    public void updateUse(ArticleInfo a) {
        
        articleDao.updateUse(a);
    }

    @Override
    public List<ArticleInfo> getArticleByViews(ArticleInfo a) {
        return articleDao.getArticleByViews(a);
    }

    @Override
    public List<ArticleInfo> getArticlesAll(ArticleInfo a, int offset, int limit) {
        RowBounds rb=new RowBounds(offset,limit);
        return  articleDao.getArticlesAll(a, rb);
    }

	

	
}
