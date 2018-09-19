package cn.com.jgre.dao;

import java.util.List;

import cn.com.jgre.common.BaseDao;
import cn.com.jgre.entity.Category;

public interface CategoryDao extends BaseDao{
    
    /**
     * 根据条件查询类别
     * @param category
     * @return
     */
    public List<Category> getCategorys(Category category);
}
