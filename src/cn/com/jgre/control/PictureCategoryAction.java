package cn.com.jgre.control;



import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

import cn.com.jgre.entity.PictureCategory;
import cn.com.jgre.service.PictureCategoryService;

/**
 * 
 * <p>图片分类管理</p>
 *
 * 类说明
 *
 * @author jgre
 * @version 1.0
 *
 * 创建时间 2014-11-7 下午09:39:07
 */

@Controller
public class PictureCategoryAction{

    @Resource
    PictureCategoryService pictureCategoryService;

    
    /*
     * 批量删除id
     */
    String  deleteId;
    String objectId;
    
    
    /*
     * 获取所有
     */
    @RequestMapping(value="getPictureCategorys.do")
    public String getPictureCategorys(PictureCategory pictureCategory,int offset,int limit,Model model){
        List<PictureCategory> cates=pictureCategoryService.getPictureCategorys(pictureCategory, offset, limit);
        model.addAttribute("jsonString", JSON.toJSONString(cates));
        return "common/jsp/pojo";
    }
    
    /*
     * 添加或修改新闻
     */
    @RequestMapping(value="saveOrUpdatePictureCatergory.do")
    public String saveOrUpdatePictureCatergory(PictureCategory pictureCategory,Model model){
        PictureCategory n=pictureCategoryService.getPictureCategoryById(pictureCategory.getPicture_cate_id());
        if(n==null){
            //添加
            pictureCategory.setPicture_cate_id(UUID.randomUUID().toString());
            pictureCategoryService.addPictureCategory(pictureCategory);    
        }else{
            //修改
            pictureCategoryService.updatePictureCategory(pictureCategory);
        }
        PictureCategory ob= pictureCategoryService.getPictureCategoryById(pictureCategory.getPicture_cate_id());
        model.addAttribute("jsonString", JSON.toJSONString(ob));
        return "common/jsp/pojo";
    }
    
    /*
     * 根据id获取
     */
    @RequestMapping(value="getPictureCatergoryById.do")
    public String getPictureCatergoryById(@RequestParam String deleteId,Model model){
        PictureCategory n=pictureCategoryService.getPictureCategoryById(deleteId);
        model.addAttribute("jsonString", JSON.toJSONString(n));
        return "common/jsp/pojo";
    }
   /*
    * 获取最大排序
    */
    @ResponseBody
    @RequestMapping(value="getPicMaxSort.do")
    public String getPicMaxSort(){
        int maxsort=pictureCategoryService.getMaxSort();
        return "{MaxSort:"+maxsort+"}";
    }
    
    /*
     * 批量删除
     */
    @RequestMapping(value="deletePictureCatergory.do")
    public String deletePictureCatergory(@RequestParam String deleteId, Model model){
        if(deleteId!=null){
           String[] ids=deleteId.split("\\$");
           String errorMsg="";
           for(String id:ids){
             if(id!=null&&!id.equals("")){
                 PictureCategory ni=new PictureCategory();
               ni.setPicture_cate_id(id);
             int result=pictureCategoryService.deletePictureCategory(ni);
               if(result<0){
                   errorMsg=errorMsg+id+"$";
                   model.addAttribute("errorMsg", errorMsg);
               }
             }
           }
        }
        
        return "common/jsp/json";
    }
   
    
    

    
    
  
}
