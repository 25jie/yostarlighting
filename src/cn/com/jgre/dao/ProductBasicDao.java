package cn.com.jgre.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import cn.com.jgre.common.BaseDao;
import cn.com.jgre.common.Pojo;
import cn.com.jgre.entity.ProductBasic;

public interface ProductBasicDao extends BaseDao{
    
    /**
     * 根据条件查询
     * @param productBasic
     * @return
     */
    public List<ProductBasic> getProductBasics(ProductBasic productBasic);
    
    @SuppressWarnings("rawtypes")
    public abstract List getProducts(Pojo pojo, RowBounds rowbounds);
    
    public int getTotalProduct(ProductBasic productBasic);

    public List<ProductBasic> getRecomProducts(String productId);
}
