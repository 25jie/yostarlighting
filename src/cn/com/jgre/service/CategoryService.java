package cn.com.jgre.service;

import java.util.List;

import cn.com.jgre.entity.Category;

public interface CategoryService {
	
	/**
	 * 根据ID查询类别
	 * @param id
	 * @return
	 */
	public Category getCategoryById(String id);

	/**
	 * 更新类别
	 * @param category
	 * @return
	 */
	public int updateCategory(Category category);
	
	/**
	 * 新增类别
	 * @param category
	 * @return
	 */
	public int addCategory(Category category);
	
    
    /**
     * 分页查询类别
     * @param category
     * @param page
     * @param limit
     * @return
     */
    public List<Category> getCategorys(Category category, int page, int limit);
    
    /**
     * 查询记录总数
     * @param category
     * @return
     */
    public int getTotalCount(Category category);
    
    /**
     * 根据条件查询类别
     * @param category
     * @return
     */
    public List<Category> queryCategorys(Category category);
    
    /**
     * 根据ID删除类别
     * @param id
     * @return
     */
    public int deleteCategoryById(String id);
}
