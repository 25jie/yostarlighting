package cn.com.jgre.serviceImpl;

import java.util.List;
import java.util.UUID;

import org.apache.ibatis.session.RowBounds;

import cn.com.jgre.dao.AdvancedPropertyDao;
import cn.com.jgre.entity.ProductAdvancedProperty;
import cn.com.jgre.service.AdvancePropertyService;

public class AdvancePropertyServiceImpl implements AdvancePropertyService {
	
	private AdvancedPropertyDao advancedPropertyDao;

	@Override
	public ProductAdvancedProperty getAdvancedPropertyById(String id) {
		return (ProductAdvancedProperty)advancedPropertyDao.getRecordById(id);
	}

	@Override
	public int updateAdvancedProperty(ProductAdvancedProperty advancedProperty) {
		return advancedPropertyDao.updateRecord(advancedProperty);
	}

	@Override
	public int addAdvancedProperty(ProductAdvancedProperty advancedProperty) {
		advancedProperty.setAdvanced_property_id(UUID.randomUUID().toString());
		return advancedPropertyDao.insertRecord(advancedProperty);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ProductAdvancedProperty> getAdvancedPropertys(
			ProductAdvancedProperty advancedProperty, int page, int limit) {
		RowBounds rb=new RowBounds(page,limit);
		return advancedPropertyDao.getRecords(advancedProperty, rb);
	}

	@Override
	public int getTotalCount(ProductAdvancedProperty advancedProperty) {
		return advancedPropertyDao.getTotalRecord(advancedProperty);
	}

	@Override
	public List<ProductAdvancedProperty> queryAdvancedPropertys(
			ProductAdvancedProperty advancedProperty) {
		return advancedPropertyDao.getAdvancedPropertys(advancedProperty);
	}

	@Override
	public int deleteAdvancedPropertyById(String id) {
		return advancedPropertyDao.deleteRecordById(id);
	}
	
	@Override
	public int deleteAdvancedPropertyByProductId(String productId) {
		return advancedPropertyDao.deleteRecordByProductId(productId);
	}

	public AdvancedPropertyDao getAdvancedPropertyDao() {
		return advancedPropertyDao;
	}

	public void setAdvancedPropertyDao(AdvancedPropertyDao advancedPropertyDao) {
		this.advancedPropertyDao = advancedPropertyDao;
	}

}
