package cn.com.jgre.control;

import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.alibaba.fastjson.JSON;

import cn.com.jgre.entity.ArticleInfo;
import cn.com.jgre.entity.Userinfo;

import cn.com.jgre.service.ArticleService;
import cn.com.jgre.util.BaseParam;
import cn.com.jgre.util.DateUtil;

/**
 * 
 * <p>图像文字类</p>
 *
 *
 * 文章类包括：关于我们：联系我们：招聘信息
 * 根据类别的不同查询
 * @author jgre
 * @version 1.0
 *
 * 创建时间 2014-11-5 下午03:56:36
 */
@Controller
@RequestMapping("/")
public class ArticleAction {

   
    @Resource
    private ArticleService articleService;
    
    /*
     * 获取所有的，分页
     */
   @RequestMapping(value="getArticles.do",method=RequestMethod.POST)
   public String getArticles(ArticleInfo article,int offset,int limit,Model model){
      List<ArticleInfo> articles= articleService.getArticles(article, offset, limit);
       int totalProperty=articleService.getTotalArticle(article);
       model.addAttribute("totalProperty", totalProperty);
       model.addAttribute("jsonString", JSON.toJSONString(articles));
       return "common/jsp/json";
   }
   
   
   
   /*
    *添加或修改
    */
   @RequestMapping(value="saveOrUpdateArticle.do",method=RequestMethod.POST)
   public String saveOrUpdateArticle(ArticleInfo article,HttpServletRequest request,Model model){
       Userinfo user=(Userinfo)request.getSession().getAttribute(BaseParam.LOGINUSER);
      ArticleInfo a=articleService.getArticleById(article.getArticle_id());
      //如果使用侧提前
        if("Y".equals(article.getNeedshow())){
            ArticleInfo temp=new ArticleInfo();
            temp.setArticle_category(article.getArticle_category());
            temp.setLast_modifytime(DateUtil.getCurrentTime()); 
            articleService.updateUse(temp);
        }
       if(a==null){
           //添加
           article.setArticle_id(UUID.randomUUID().toString());
           article.setArticle_author(user==null?"nouser":user.getUsername());
           article.setArticle_pubtime(DateUtil.getCurrentTime());
           article.setLast_modifytime(DateUtil.getCurrentTime());
           articleService.addArticle(article);
       }else{
           article.setLast_modifytime(DateUtil.getCurrentTime()); 
           articleService.updateArticle(article);
       }
       ArticleInfo temp=articleService.getArticleById(article.getArticle_id());
       model.addAttribute("jsonString", JSON.toJSONString(temp));
       return "common/jsp/pojo";
   }
   
   
   /*
    * 删除
    */
   @RequestMapping(value="deleteArticle.do",method=RequestMethod.POST)
   public String deleteArticle(@RequestParam String deleteId,Model model){
       if(deleteId!=null){
           String errorMsg="";
           String[] ids=deleteId.split("\\$");
           for(String id:ids){
               ArticleInfo a=new ArticleInfo();
               a.setArticle_id(id);
              int ret=articleService.deleteArticle(a);
              if(ret<0){
                  errorMsg=errorMsg+id;
                  model.addAttribute("errorMsg", errorMsg);
              }
           }
       }
       return "common/jsp/json";
   }
   
   /*
    * 根据id获取
    */
   @RequestMapping(value="getArticleById.do")
   public String getArticleById(@RequestParam String objectId,Model model){
    
     ArticleInfo a= articleService.getArticleById(objectId);
     model.addAttribute("jsonString", JSON.toJSONString(a));
       return "common/jsp/pojo";
   }
   
   /*
    * 使用的时候
    */
   @RequestMapping(value="useArticle.do")
   public String useArticle(ArticleInfo article,Model model){
       //首先更新所有的needshow为Y改为no
       ArticleInfo temp=new ArticleInfo();
       if(article.getArticle_category()!=null){
       temp.setArticle_category(article.getArticle_category());
       }
       if(article.getArticle_categoryDM()!=null){
           temp.setArticle_categoryDM(article.getArticle_categoryDM());  
       }
       temp.setLast_modifytime(DateUtil.getCurrentTime()); 
       articleService.updateUse(temp);
       //在更新选中的
       ArticleInfo a=articleService.getArticleById(article.getArticle_id());
       a.setNeedshow("Y");
       int ret= articleService.updateArticle(a);
       if(ret<0){
           model.addAttribute("errorMsg", "error");
       }
       return "common/jsp/json";
   }
   
   
}
