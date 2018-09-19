package cn.com.jgre.serviceImpl;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import cn.com.jgre.dao.NewsDao;
import cn.com.jgre.entity.NewsInfo;
import cn.com.jgre.service.NewsService;








public class NewsServiceImpl implements NewsService{
  NewsDao newsDao;
  @Override
	public int deleteNewsInfo(NewsInfo a) {
		return newsDao.deleteRecordById(a.getNews_id());
	}
	  @Override
	public NewsInfo getNewsInfoById(String id) {
		
		return (NewsInfo) newsDao.getRecordById(id);
	}
	  
	@SuppressWarnings("unchecked")
    @Override
	public List<NewsInfo> getNews(NewsInfo a, int offset, int limit) {
		RowBounds rb=new RowBounds(offset,limit);
		return newsDao.getRecords(a, rb);
	}
	  @Override
	public int addNewsInfo(NewsInfo a) {
	
		return newsDao.insertRecord(a);
	}
	  @Override
	public int updateNewsInfo(NewsInfo a) {
		
		return newsDao.updateRecord(a);
	}
	

    @Override
    public int getTotalNewsInfo(NewsInfo a) {
       
        return newsDao.getTotalRecord(a);
    }
    public NewsDao getNewsDao() {
        return newsDao;
    }
    public void setNewsDao(NewsDao newsDao) {
        this.newsDao = newsDao;
    }

	

	
}
