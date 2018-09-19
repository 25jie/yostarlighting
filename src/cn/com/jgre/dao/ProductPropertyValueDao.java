package cn.com.jgre.dao;

import java.util.List;

import cn.com.jgre.common.BaseDao;
import cn.com.jgre.entity.ProductPropertyValue;

public interface ProductPropertyValueDao extends BaseDao{
    
	public List<ProductPropertyValue> getPropertyValues(ProductPropertyValue propertyValue);
	
	/**
	 * 根据产品ID删除对应属性值
	 * @param productId
	 * @return
	 */
	public int deleteRecordByProductId(String productId);
    
}
