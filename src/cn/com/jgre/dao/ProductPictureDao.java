package cn.com.jgre.dao;

import java.util.List;

import cn.com.jgre.common.BaseDao;
import cn.com.jgre.entity.ProductPicture;

public interface ProductPictureDao extends BaseDao{
    
    /**
     * 根据条件查询类别
     * @param productPicture
     * @return
     */
    public List<ProductPicture> getProductPictures(ProductPicture productPicture);
    
    /**
     * 根据产品ID删除关联图片
     * @param productId
     * @return
     */
    public int deleteRecordByProductId(String productId);
    
    /**
     * 根据产品ID和图片ID删除记录
     * @param productPicture
     * @return
     */
    public int delRecordByPicIdAndProId(ProductPicture productPicture);
}
