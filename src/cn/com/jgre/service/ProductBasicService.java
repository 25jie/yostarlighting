package cn.com.jgre.service;

import java.util.List;

import cn.com.jgre.entity.ProductBasic;

public interface ProductBasicService {
	
	/**
	 * 根据ID查询类别
	 * @param id
	 * @return
	 */
	public ProductBasic getProductBasicById(String id);

	/**
	 * 更新类别
	 * @param productBasic
	 * @return
	 */
	public int updateProductBasic(ProductBasic productBasic);
	
	/**
	 * 新增类别
	 * @param productBasic
	 * @return
	 */
	public int addProductBasic(ProductBasic productBasic);
	
    
    /**
     * 分页查询类别
     * @param productBasic
     * @param page
     * @param limit
     * @return
     */
    public List<ProductBasic> getProductBasics(ProductBasic productBasic, int page, int limit);
    
    /**
     * 分页查询类别
     * @param productBasic
     * @param page
     * @param limit
     * @return
     */
    public List<ProductBasic> getProducts(ProductBasic productBasic, int page, int limit);
    
    /**
     * 查询记录总数
     * @param productBasic
     * @return
     */
    public int getTotalCount(ProductBasic productBasic);
    
    /**
     * 查询记录总数
     * @param productBasic
     * @return
     */
    public int getTotalProduct(ProductBasic productBasic);
    
    /**
     * 根据条件查询类别
     * @param productBasic
     * @return
     */
    public List<ProductBasic> queryProductBasics(ProductBasic productBasic);
    
    /**
     * 根据ID删除类别
     * @param id
     * @return
     */
    public int deleteProductBasicById(String id);

	/**
	 * 查询推荐产品
	 * @param productId
	 * @return
	 */
	public List<ProductBasic> getRecomProducts(String productId);
}
