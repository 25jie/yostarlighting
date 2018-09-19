package cn.com.jgre.serviceImpl;

import java.util.List;
import java.util.UUID;

import org.apache.ibatis.session.RowBounds;

import cn.com.jgre.dao.CategoryDao;
import cn.com.jgre.entity.Category;
import cn.com.jgre.service.CategoryService;
import cn.com.jgre.util.DateUtil;

public class CategoryServiceImpl implements CategoryService {
	
	CategoryDao categoryDao;

	@Override
	public Category getCategoryById(String id) {
		return (Category)categoryDao.getRecordById(id);
	}

	@Override
	public int updateCategory(Category category) {
		category.setUpdate_time(DateUtil.getCurrentTime());
		return categoryDao.updateRecord(category);
	}

	@Override
	public int addCategory(Category category) {
		category.setCreate_time(DateUtil.getCurrentTime());
		category.setUpdate_time(DateUtil.getCurrentTime());
		category.setCategory_id(UUID.randomUUID().toString());
		category.setCategory_order(0);
		return categoryDao.insertRecord(category);
	}

	public CategoryDao getCategoryDao() {
		return categoryDao;
	}

	public void setCategoryDao(CategoryDao categoryDao) {
		this.categoryDao = categoryDao;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Category> getCategorys(Category category, int page, int limit) {
		RowBounds rb=new RowBounds(page,limit);
		return categoryDao.getRecords(category, rb);
	}

	@Override
	public int getTotalCount(Category category) {
		return categoryDao.getTotalRecord(category);
	}

	@Override
	public List<Category> queryCategorys(Category category) {
		return categoryDao.getCategorys(category);
	}

	@Override
	public int deleteCategoryById(String id) {
		return categoryDao.deleteRecordById(id);
	}

}
