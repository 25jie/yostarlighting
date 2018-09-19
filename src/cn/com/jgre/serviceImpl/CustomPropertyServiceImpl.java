package cn.com.jgre.serviceImpl;

import java.util.List;
import java.util.UUID;

import org.apache.ibatis.session.RowBounds;

import cn.com.jgre.dao.ProductCustomPropertyDao;
import cn.com.jgre.entity.ProductCustomProperty;
import cn.com.jgre.service.CustomPropertyService;
import cn.com.jgre.util.DateUtil;

public class CustomPropertyServiceImpl implements CustomPropertyService {
	
	ProductCustomPropertyDao productCustomPropertyDao;
	
	@Override
	public ProductCustomProperty getCustomPropertyById(String id) {
		return (ProductCustomProperty)productCustomPropertyDao.getRecordById(id);
	}

	@Override
	public int updateCustomProperty(ProductCustomProperty customProperty) {
		return productCustomPropertyDao.updateRecord(customProperty);
	}

	@Override
	public int addCustomProperty(ProductCustomProperty customProperty) {
		customProperty.setCreate_time(DateUtil.getCurrentTime());
		customProperty.setUpdate_time(DateUtil.getCurrentTime());
		customProperty.setProperty_order(0);
		customProperty.setCustom_property_id(UUID.randomUUID().toString());
		return productCustomPropertyDao.insertRecord(customProperty);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ProductCustomProperty> getCustomPropertys(
			ProductCustomProperty customProperty, int page, int limit) {
		RowBounds rb=new RowBounds(page,limit);
		return productCustomPropertyDao.getRecords(customProperty, rb);
	}

	@Override
	public int getTotalCount(ProductCustomProperty customProperty) {
		return productCustomPropertyDao.getTotalRecord(customProperty);
	}

	@Override
	public int deleteCustomPropertyById(String id) {
		return productCustomPropertyDao.deleteRecordById(id);
	}
	
	@Override
	public List<ProductCustomProperty> queryCustomPropertys(
			ProductCustomProperty customProperty) {
		return productCustomPropertyDao.getCustomPropertys(customProperty);
	}
	
	public ProductCustomPropertyDao getProductCustomPropertyDao() {
		return productCustomPropertyDao;
	}

	public void setProductCustomPropertyDao(
			ProductCustomPropertyDao productCustomPropertyDao) {
		this.productCustomPropertyDao = productCustomPropertyDao;
	}

}
