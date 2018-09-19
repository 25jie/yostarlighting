package cn.com.jgre.dao;

import java.util.List;

import cn.com.jgre.common.BaseDao;
import cn.com.jgre.entity.ProductAdvancedProperty;
import cn.com.jgre.entity.ProductBasic;

public interface AdvancedPropertyDao extends BaseDao{
    
    /**
     * 根据条件查询类别
     * @param category
     * @return
     */
    public List<ProductAdvancedProperty> getAdvancedPropertys(ProductAdvancedProperty advancedProperty);
    
    /**
     * 根据产品ID删除对应的记录
     * @param productId
     * @return
     */
    public int deleteRecordByProductId(String productId);
}
