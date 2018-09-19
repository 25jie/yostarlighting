package cn.com.jgre.service;

import java.util.List;

import cn.com.jgre.entity.ProductPicture;

public interface ProductPictureService {
	
	/**
	 * 根据ID查询
	 * @param id
	 * @return
	 */
	public ProductPicture getProductPictureById(String id);

	/**
	 * 更新
	 * @param productPicture
	 * @return
	 */
	public int updateProductPicture(ProductPicture productPicture);
	
	/**
	 * 新增
	 * @param productPicture
	 * @return
	 */
	public int addProductPicture(ProductPicture productPicture);
	
    
    /**
     * 分页查询
     * @param productPicture
     * @param page
     * @param limit
     * @return
     */
    public List<ProductPicture> getProductPictures(ProductPicture productPicture, int page, int limit);
    
    /**
     * 查询记录总数
     * @param productPicture
     * @return
     */
    public int getTotalCount(ProductPicture productPicture);
    
    /**
     * 根据条件查询
     * @param productPicture
     * @return
     */
    public List<ProductPicture> queryProductPictures(ProductPicture productPicture);
    
    /**
     * 根据ID删除
     * @param id
     * @return
     */
    public int deleteProductPictureById(String id);
    
    /**
     * 根据产品ID删除
     * @param id
     * @return
     */
    public int deleteProductPictureByProductId(String productId);
    
    /**
     * 根据产品ID和图片ID删除记录
     * @param productPicture
     * @return
     */
    public int delRecordByPicIdAndProId(ProductPicture productPicture);

}
