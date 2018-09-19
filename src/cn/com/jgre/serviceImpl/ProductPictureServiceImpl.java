package cn.com.jgre.serviceImpl;

import java.util.List;
import java.util.UUID;

import org.apache.ibatis.session.RowBounds;

import cn.com.jgre.dao.ProductPictureDao;
import cn.com.jgre.entity.ProductPicture;
import cn.com.jgre.service.ProductPictureService;
import cn.com.jgre.util.DateUtil;

public class ProductPictureServiceImpl implements ProductPictureService {
	
	ProductPictureDao productPictureDao;

	@Override
	public ProductPicture getProductPictureById(String id) {
		return (ProductPicture)productPictureDao.getRecordById(id);
	}

	@Override
	public int updateProductPicture(ProductPicture productPicture) {
		return productPictureDao.updateRecord(productPicture);
	}

	@Override
	public int addProductPicture(ProductPicture productPicture) {
		productPicture.setProduct_picture_id(UUID.randomUUID().toString());
		return productPictureDao.insertRecord(productPicture);
	}

	public ProductPictureDao getProductPictureDao() {
		return productPictureDao;
	}

	public void setProductPictureDao(ProductPictureDao productPictureDao) {
		this.productPictureDao = productPictureDao;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ProductPicture> getProductPictures(ProductPicture productPicture, int page, int limit) {
		RowBounds rb=new RowBounds(page,limit);
		return productPictureDao.getRecords(productPicture, rb);
	}

	@Override
	public int getTotalCount(ProductPicture productPicture) {
		return productPictureDao.getTotalRecord(productPicture);
	}
	
	@Override
	public int deleteProductPictureByProductId(String productId) {
		return productPictureDao.deleteRecordByProductId(productId);
	}
	
	@Override
	public int delRecordByPicIdAndProId(ProductPicture productPicture) {
		return productPictureDao.delRecordByPicIdAndProId(productPicture);
	}

	@Override
	public List<ProductPicture> queryProductPictures(ProductPicture productPicture) {
		return productPictureDao.getProductPictures(productPicture);
	}

	@Override
	public int deleteProductPictureById(String id) {
		return productPictureDao.deleteRecordById(id);
	}

}
