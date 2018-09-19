package cn.com.jgre.service;

import java.util.List;

import cn.com.jgre.entity.ProductPropertyValue;

public interface PropertyValueService {
	
	/**
	 * 根据ID查询
	 * @param id
	 * @return
	 */
	public ProductPropertyValue getPropertyValueById(String id);

	/**
	 * 更新
	 * @param category
	 * @return
	 */
	public int updatePropertyValue(ProductPropertyValue propertyValue);
	
	/**
	 * 新增
	 * @param category
	 * @return
	 */
	public int addPropertyValue(ProductPropertyValue propertyValue);
    
    /**
     * 根据条件查询
     * @param category
     * @param page
     * @param limit
     * @return
     */
    public List<ProductPropertyValue> queryPropertyValues(ProductPropertyValue propertyValue);
    
    /**
     * 根据ID删除
     * @param id
     * @return
     */
    public int deletePropertyValueById(String id);
    
    /**
     * 根据产品ID删除
     * @param id
     * @return
     */
    public int deletePropertyValueByProductId(String productId);
}
