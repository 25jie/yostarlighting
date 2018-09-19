package cn.com.jgre.service;

import java.util.List;

import cn.com.jgre.entity.Pictureinfo;









public interface PictureService {
    
	
	//查询跟分页查
	public List<Pictureinfo> getPictures(Pictureinfo picture, int offset, int limit);
	
	//根据id查询
	public Pictureinfo getPictureById(String id);

	//删除文章
	public int deletePicture(Pictureinfo picture);
	
	//更新文章
	public int updatePicture(Pictureinfo picture);
	
	//添加文章
	public int addPicture(Pictureinfo picture);
	
	
	//查询总数
	public int getTotal(Pictureinfo picture);
}
