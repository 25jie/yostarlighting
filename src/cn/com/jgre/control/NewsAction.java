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

import cn.com.jgre.entity.NewsInfo;
import cn.com.jgre.service.NewsService;
import cn.com.jgre.util.DateUtil;



/**
 * 
 * <p>类描述</p>
 *
 * 新闻
 *
 * @author jgre
 * @version 1.0
 *
 * 创建时间 2014-5-4 上午11:36:13
 */
@Controller
@RequestMapping("/")
public class NewsAction{

    private static final long serialVersionUID = 1L;

    /*
     * 文章接口
     */
    @Resource
    NewsService newsService;
     /*
     * 获取所有新闻
     */
    @RequestMapping(value="getNews.do",method=RequestMethod.POST)
    public String getNews(NewsInfo newsinfo, int offset,int limit,Model model){
        List<NewsInfo> news=newsService.getNews(newsinfo, offset, limit);
        int totalProperty=newsService.getTotalNewsInfo(newsinfo);
        model.addAttribute("totalProperty", totalProperty);
        model.addAttribute("jsonString", JSON.toJSONString(news));
        return "common/jsp/json";
    }
   
    /*
     * 添加或修改新闻
     */
    @RequestMapping(value="saveOrUpdateNews.do",method=RequestMethod.POST)
    public String saveOrUpdateNews(NewsInfo newsInfo,Model model){
        NewsInfo n=newsService.getNewsInfoById(newsInfo.getNews_id());
        if(n==null){
            //添加
            newsInfo.setNews_id(UUID.randomUUID().toString());
            newsInfo.setNews_pubtime(DateUtil.getCurrentTime());
            newsService.addNewsInfo(newsInfo);    
        }else{
            //修改
            newsInfo.setModifytime(DateUtil.getCurrentTime());
            newsService.updateNewsInfo(newsInfo);
        }
        NewsInfo ob= newsService.getNewsInfoById(newsInfo.getNews_id());
        model.addAttribute("jsonString", JSON.toJSONString(ob));
        return "common/jsp/pojo";
    }
    
    /*
     * 根据id获取
     */
    @RequestMapping(value="getNewsById.do",method=RequestMethod.GET)
    public String getNewsById(@RequestParam String objectId,Model model){
        NewsInfo n=newsService.getNewsInfoById(objectId);
        model.addAttribute("jsonString", JSON.toJSONString(n));
        return "common/jsp/pojo";
    }

    /*
     * 设置展示
     */
    @RequestMapping(value="setShowOnIndex.do",method=RequestMethod.POST)
    public String setShowOnIndex(NewsInfo newsInfo,Model model){
        
        NewsInfo n=newsService.getNewsInfoById(newsInfo.getNews_id());
        if(n!=null){
            n.setNews_show(newsInfo.getNews_show());
           int ret=  newsService.updateNewsInfo(n);
           if(ret<0){
               model.addAttribute("errorMsg", "error");
           }
        }else{
            model.addAttribute("errorMsg", "not exist");
        }
        return "common/jsp/json";
    }
    /*
     * 删除
     */
    @RequestMapping(value="deleteNews.do",method=RequestMethod.GET)
    public String deleteNews(@RequestParam String deleteId,Model model){
        if(deleteId!=null){
           String[] ids=deleteId.split("\\$");
           String errorMsg="";
           for(String id:ids){
             if(id!=null&&!id.equals("")){
               NewsInfo ni=new NewsInfo();
               ni.setNews_id(id);
             int result=  newsService.deleteNewsInfo(ni);
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
