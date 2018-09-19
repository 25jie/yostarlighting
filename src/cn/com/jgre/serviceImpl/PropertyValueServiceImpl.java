package cn.com.jgre.serviceImpl;

import java.util.List;
import java.util.UUID;

import cn.com.jgre.dao.ProductPropertyValueDao;
import cn.com.jgre.entity.ProductPropertyValue;
import cn.com.jgre.service.PropertyValueService;

public class PropertyValueServiceImpl implements PropertyValueService {
	
	ProductPropertyValueDao productPropertyValueDao;

	@Override
	public ProductPropertyValue getPropertyValueById(String id) {
		return (ProductPropertyValue)productPropertyValueDao.getRecordById(id);
	}

	@Override
	public int updatePropertyValue(ProductPropertyValue propertyValue) {
		return productPropertyValueDao.updateRecord(propertyValue);
	}

	@Override
	public int addPropertyValue(ProductPropertyValue propertyValue) {
		propertyValue.setId(UUID.randomUUID().toString());
		return productPropertyValueDao.insertRecord(propertyValue);
	}

	@Override
	public List<ProductPropertyValue> queryPropertyValues(
			ProductPropertyValue propertyValue) {
		return productPropertyValueDao.getPropertyValues(propertyValue);
	}

	@Override
	public int deletePropertyValueById(String id) {
		return productPropertyValueDao.deleteRecordById(id);
	}
	
	@Override
	public int deletePropertyValueByProductId(String productId) {
		return productPropertyValueDao.deleteRecordByProductId(productId);
	}

	public ProductPropertyValueDao getProductPropertyValueDao() {
		return productPropertyValueDao;
	}

	public void setProductPropertyValueDao(
			ProductPropertyValueDao productPropertyValueDao) {
		this.productPropertyValueDao = productPropertyValueDao;
	}

}
