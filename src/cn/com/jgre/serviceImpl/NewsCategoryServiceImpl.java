package cn.com.jgre.serviceImpl;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import cn.com.jgre.dao.NewsCategoryDao;
import cn.com.jgre.entity.NewsCategory;
import cn.com.jgre.service.NewsCategoryService;



public class NewsCategoryServiceImpl implements NewsCategoryService{
   NewsCategoryDao newsCategoryDao;
   
    @SuppressWarnings("unchecked")
    @Override
    public List<NewsCategory> getNewsCategory(NewsCategory category, int offset, int limit) {
        RowBounds rb=new RowBounds(offset,limit);
        return newsCategoryDao.getRecords(category, rb);
    }

    @Override
    public NewsCategory getNewsCategoryById(String id) {
        return (NewsCategory) newsCategoryDao.getRecordById(id);
    }

    @Override
    public int deleteNewsCategory(NewsCategory category) {

        return newsCategoryDao.deleteRecordById(category.getCategory_id());
    }

    @Override
    public int updateNewsCategory(NewsCategory category) {
        return newsCategoryDao.updateRecord(category);
    }

    @Override
    public int addNewsCategory(NewsCategory category) {
        return newsCategoryDao.insertRecord(category);
    }

    public NewsCategoryDao getNewsCategoryDao() {
        return newsCategoryDao;
    }

    public void setNewsCategoryDao(NewsCategoryDao newsCategoryDao) {
        this.newsCategoryDao = newsCategoryDao;
    }

    @Override
    public int getMaxSort() {
       
        return newsCategoryDao.getMaxRecord();
    }

   
    
}
