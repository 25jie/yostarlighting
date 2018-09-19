package cn.com.jgre.service;



import java.util.List;

import cn.com.jgre.entity.NewsCategory;




public interface NewsCategoryService {
    
    
  //查询跟分页查
    public List<NewsCategory> getNewsCategory(NewsCategory category, int offset, int limit);
    
    //根据id查询
    public NewsCategory getNewsCategoryById(String id);

    //删除分类
    public int deleteNewsCategory(NewsCategory category);
    
    //更新分类
    public int updateNewsCategory(NewsCategory category);
    
    //添加分类
    public int addNewsCategory(NewsCategory category);
    
    public int getMaxSort();

}
