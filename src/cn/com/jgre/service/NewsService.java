package cn.com.jgre.service;



import java.util.List;

import cn.com.jgre.entity.NewsInfo;







public interface NewsService {

    
    //查询跟分页查
    public List<NewsInfo> getNews(NewsInfo newsInfo, int offset, int limit);
    
    //根据id查询
    public NewsInfo getNewsInfoById(String id);

    //删除文章
    public int deleteNewsInfo(NewsInfo a);
    
    //更新文章
    public int updateNewsInfo(NewsInfo a);
    
    //添加文章
    public int addNewsInfo(NewsInfo a);
    
    //查询总数
    public int getTotalNewsInfo(NewsInfo a);
}
