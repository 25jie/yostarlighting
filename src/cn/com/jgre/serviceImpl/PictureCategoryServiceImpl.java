package cn.com.jgre.serviceImpl;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import cn.com.jgre.dao.PictureCategoryDao;
import cn.com.jgre.entity.PictureCategory;
import cn.com.jgre.service.PictureCategoryService;







public class PictureCategoryServiceImpl implements PictureCategoryService{
   PictureCategoryDao pictureCategoryDao;
	 
	public int addPictureCategory(PictureCategory pictureCategory) {
		return pictureCategoryDao.insertRecord(pictureCategory);
	}

	public int deletePictureCategory(PictureCategory pictureCategory) {
		return pictureCategoryDao.deleteRecordById(pictureCategory.getPicture_cate_id());
	}

	public PictureCategory getPictureCategoryById(String id) {
		return (PictureCategory) pictureCategoryDao.getRecordById(id);
	}

	@SuppressWarnings("unchecked")
    public List<PictureCategory> getPictureCategorys(PictureCategory pictureCategory,
			int offset, int limit) {
		RowBounds rb=new RowBounds(offset,limit);
		return pictureCategoryDao.getRecords(pictureCategory, rb);
	}

	public int updatePictureCategory(PictureCategory pictureCategory) {
		return pictureCategoryDao.updateRecord(pictureCategory);
	}
   public int getMaxSort(){
       return pictureCategoryDao.getMaxRecord();
   }
	public PictureCategoryDao getPictureCategoryDao() {
		return pictureCategoryDao;
	}

	public void setPictureCategoryDao(PictureCategoryDao pictureCategoryDao) {
		this.pictureCategoryDao = pictureCategoryDao;
	}

	
	
}
