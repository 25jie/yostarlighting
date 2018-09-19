package cn.com.jgre.service;

import java.util.List;

import cn.com.jgre.entity.ProductAdvancedProperty;

public interface AdvancePropertyService {
	
	/**
	 * 根据ID查询 
	 * @param id
	 * @return
	 */
	public ProductAdvancedProperty getAdvancedPropertyById(String id);

	/**
	 * 更新 
	 * @param advancedProperty
	 * @return
	 */
	public int updateAdvancedProperty(ProductAdvancedProperty advancedProperty);
	
	/**
	 * 新增 
	 * @param advancedProperty
	 * @return
	 */
	public int addAdvancedProperty(ProductAdvancedProperty advancedProperty);
	
    
    /**
     * 分页查询 
     * @param advancedProperty
     * @param page
     * @param limit
     * @return
     */
    public List<ProductAdvancedProperty> getAdvancedPropertys(ProductAdvancedProperty advancedProperty, int page, int limit);
    
    /**
     * 查询记录总数
     * @param advancedProperty
     * @return
     */
    public int getTotalCount(ProductAdvancedProperty advancedProperty);
    
    /**
     * 根据条件查询 
     * @param advancedProperty
     * @return
     */
    public List<ProductAdvancedProperty> queryAdvancedPropertys(ProductAdvancedProperty advancedProperty);
    
    /**
     * 根据ID删除 
     * @param id
     * @return
     */
    public int deleteAdvancedPropertyById(String id);
    
    /**
     * 根据产品ID删除
     * @param productId
     * @return
     */
    public int deleteAdvancedPropertyByProductId(String productId);
}
