package cn.com.jgre.service;



import java.util.List;

import cn.com.jgre.entity.PictureCategory;









public interface PictureCategoryService {
    
    //查询跟分页查
    public List<PictureCategory> getPictureCategorys(PictureCategory pictureCategory, int offset, int limit);
    
    //根据id查询
    public PictureCategory getPictureCategoryById(String id);

    //删除文章分类
    public int deletePictureCategory(PictureCategory pictureCategory);
    
    //更新文章分类
    public int updatePictureCategory(PictureCategory pictureCategory);
    
    //添加文章分类
    public int addPictureCategory(PictureCategory pictureCategory);

    public int getMaxSort();
}
