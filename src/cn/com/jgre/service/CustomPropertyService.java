package cn.com.jgre.service;

import java.util.List;

import cn.com.jgre.entity.ProductCustomProperty;

public interface CustomPropertyService {
	
	/**
	 * 根据ID查询类别
	 * @param id
	 * @return
	 */
	public ProductCustomProperty getCustomPropertyById(String id);

	/**
	 * 更新类别
	 * @param category
	 * @return
	 */
	public int updateCustomProperty(ProductCustomProperty category);
	
	/**
	 * 新增类别
	 * @param category
	 * @return
	 */
	public int addCustomProperty(ProductCustomProperty customProperty);
	
    
    /**
     * 分页查询类别
     * @param category
     * @param page
     * @param limit
     * @return
     */
    public List<ProductCustomProperty> getCustomPropertys(ProductCustomProperty customProperty, int page, int limit);
    
    /**
     * 根据条件查询
     * @param category
     * @param page
     * @param limit
     * @return
     */
    public List<ProductCustomProperty> queryCustomPropertys(ProductCustomProperty customProperty);
    
    /**
     * 查询记录总数
     * @param category
     * @return
     */
    public int getTotalCount(ProductCustomProperty customProperty);
    
    /**
     * 根据ID删除类别
     * @param id
     * @return
     */
    public int deleteCustomPropertyById(String id);
}
