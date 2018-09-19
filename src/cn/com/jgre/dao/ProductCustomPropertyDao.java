package cn.com.jgre.dao;

import java.util.List;

import cn.com.jgre.common.BaseDao;
import cn.com.jgre.entity.ProductCustomProperty;

public interface ProductCustomPropertyDao extends BaseDao{
    
	public List<ProductCustomProperty> getCustomPropertys(ProductCustomProperty customProperty);
    
}
