package cn.com.jgre.serviceImpl;

import java.util.List;
import java.util.UUID;

import org.apache.ibatis.session.RowBounds;

import cn.com.jgre.dao.ProductBasicDao;
import cn.com.jgre.entity.ProductBasic;
import cn.com.jgre.service.ProductBasicService;
import cn.com.jgre.util.DateUtil;

public class ProductBasicServiceImpl implements ProductBasicService {
	
	ProductBasicDao productBasicDao;

	@Override
	public ProductBasic getProductBasicById(String id) {
		return (ProductBasic)productBasicDao.getRecordById(id);
	}

	@Override
	public int updateProductBasic(ProductBasic productBasic) {
		productBasic.setUpdate_time(DateUtil.getCurrentTime());
		return productBasicDao.updateRecord(productBasic);
	}

	@Override
	public int addProductBasic(ProductBasic productBasic) {
		productBasic.setCreate_time(DateUtil.getCurrentTime());
		productBasic.setUpdate_time(DateUtil.getCurrentTime());
		productBasic.setProduct_id(UUID.randomUUID().toString());
		productBasic.setStatus(1);
		return productBasicDao.insertRecord(productBasic);
	}

	public ProductBasicDao getProductBasicDao() {
		return productBasicDao;
	}

	public void setProductBasicDao(ProductBasicDao productBasicDao) {
		this.productBasicDao = productBasicDao;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ProductBasic> getProductBasics(ProductBasic productBasic, int page, int limit) {
		RowBounds rb=new RowBounds(page,limit);
		return productBasicDao.getRecords(productBasic, rb);
	}

	@Override
	public int getTotalCount(ProductBasic productBasic) {
		return productBasicDao.getTotalRecord(productBasic);
	}
	
	@Override
	public int getTotalProduct(ProductBasic productBasic) {
		return productBasicDao.getTotalProduct(productBasic);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<ProductBasic> getProducts(ProductBasic productBasic, int page, int limit) {
		RowBounds rb=new RowBounds(page,limit);
		return productBasicDao.getProducts(productBasic, rb);
	}

	@Override
	public List<ProductBasic> queryProductBasics(ProductBasic productBasic) {
		return productBasicDao.getProductBasics(productBasic);
	}

	@Override
	public int deleteProductBasicById(String id) {
		return productBasicDao.deleteRecordById(id);
	}

	@Override
	public List<ProductBasic> getRecomProducts(String id) {
		return productBasicDao.getRecomProducts(id);
	}

}
