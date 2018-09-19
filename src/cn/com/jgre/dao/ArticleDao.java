package cn.com.jgre.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import cn.com.jgre.common.BaseDao;
import cn.com.jgre.entity.ArticleInfo;





public interface ArticleDao extends BaseDao{
    
    public void updateUse(ArticleInfo a);
    public List<ArticleInfo> getArticleByViews(ArticleInfo a);
    public List<ArticleInfo> getArticlesAll(ArticleInfo a, RowBounds rb);
}
