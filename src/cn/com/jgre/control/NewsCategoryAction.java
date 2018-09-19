package cn.com.jgre.control;



import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.alibaba.fastjson.JSON;

import cn.com.jgre.entity.NewsCategory;
import cn.com.jgre.service.NewsCategoryService;




/**
 * 
 * <p>类描述</p>
 *
 * 新闻分类
 *
 * @author jgre
 * @version 1.0
 *
 * 创建时间 2014-5-14 上午11:36:13
 */
@Controller
@RequestMapping("/")
public class NewsCategoryAction{

    private static final long serialVersionUID = 1L;

    /*
     * 文章接口
     */
    @Resource
    NewsCategoryService newsCategoryService;

    /*
     * 获取所有新闻
     */
    @RequestMapping(value="getNewsCategorys.do",method=RequestMethod.POST)
    public String getNewsCategorys(NewsCategory newsCategory,int offset,int limit,Model model){
        List<NewsCategory> news=newsCategoryService.getNewsCategory(newsCategory, offset, limit);
        model.addAttribute("jsonString", JSON.toJSONString(news));
        return "common/jsp/pojo";
    }
    
    /*
     * 添加或修改新闻
     */
    @RequestMapping(value="saveOrUpdateNewsCatergory.do",method=RequestMethod.POST)
    public String saveOrUpdateNewsCatergory(NewsCategory newsCategory,Model model){
        NewsCategory n=newsCategoryService.getNewsCategoryById(newsCategory.getCategory_id());
        if(n==null){
            //添加
            newsCategory.setCategory_id(UUID.randomUUID().toString());
            newsCategoryService.addNewsCategory(newsCategory);    
        }else{
            //修改
            newsCategoryService.updateNewsCategory(newsCategory);
        }
        NewsCategory ob= newsCategoryService.getNewsCategoryById(newsCategory.getCategory_id());
        model.addAttribute("jsonString", JSON.toJSONString(ob));
        return "common/jsp/pojo";
    }
    
    /*
     * 根据id获取
     */
    @RequestMapping(value="getNewsCatergoryById.do")
    public String getNewsCatergoryById(@RequestParam String deleteId,Model model){
        NewsCategory n=newsCategoryService.getNewsCategoryById(deleteId);
        model.addAttribute("jsonString", JSON.toJSONString(n));
        return "common/jsp/pojo";
    }
   /*
    * 获取最大排序
    */
    @RequestMapping(value="getMaxSort.do",method=RequestMethod.GET)
    public String getMaxSort(Model model){
        int maxsort=newsCategoryService.getMaxSort();
        model.addAttribute("jsonString", "{MaxSort:"+maxsort+"}");
        return "common/jsp/pojo";
    }
    
    /*
     * 批量删除
     */
    @RequestMapping(value="deleteNewsCatergory.do")
    public String deleteNewsCatergory(@RequestParam String deleteId,Model model){
        if(deleteId!=null){
           String[] ids=deleteId.split("\\$");
           String errorMsg="";
           for(String id:ids){
             if(id!=null&&!id.equals("")){
               NewsCategory ni=new NewsCategory();
               ni.setCategory_id(id);
             int result=newsCategoryService.deleteNewsCategory(ni);
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
