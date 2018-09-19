package cn.com.jgre.serviceImpl;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import cn.com.jgre.dao.PictureDao;
import cn.com.jgre.entity.Pictureinfo;
import cn.com.jgre.service.PictureService;







public class PictureServiceImpl implements PictureService{
    PictureDao pictureDao;
	public int addPicture(Pictureinfo picture) {
		return pictureDao.insertRecord(picture);
	}

	public int deletePicture(Pictureinfo picture) {
		return pictureDao.deleteRecordById(picture.getPicture_id());
	}

	@SuppressWarnings("unchecked")
    public List<Pictureinfo> getPictures(Pictureinfo picture, int offset,int limit) {
		RowBounds rb=new RowBounds(offset,limit);
		return pictureDao.getRecords(picture, rb);
	}

	public Pictureinfo getPictureById(String id) {
		return (Pictureinfo) pictureDao.getRecordById(id);
	}

	public int updatePicture(Pictureinfo picture) {
		return pictureDao.updateRecord(picture);
	}

	 @Override
	    public int getTotal(Pictureinfo picture) {
	        
	        return pictureDao.getTotalRecord(picture);
	    }

	public PictureDao getPictureDao() {
		return pictureDao;
	}

	public void setPictureDao(PictureDao pictureDao) {
		this.pictureDao = pictureDao;
	}

   
	
}
