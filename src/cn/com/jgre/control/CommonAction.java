package cn.com.jgre.control;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.com.jgre.entity.CompanyBaseInfo;
import cn.com.jgre.entity.KinMaxShow;
import cn.com.jgre.service.CommonService;

import com.alibaba.fastjson.JSON;


/**
 * 
 * <p>类描述</p>
 *
 * 一些简单的类
 *
 * @author jgre
 * @version 1.0
 *
 * 创建时间 2014-11-17 下午04:19:17
 */
@Controller
public class CommonAction {

    @Resource
    CommonService commonService;
    /*
     * 公司基本信息的查询
     */
   @RequestMapping(value="getCompanyBaseInfo.do")
   public String getCompanyBaseInfo(Model model){
     CompanyBaseInfo cbaseInfo= commonService.getCompanyBaseInfo();
     model.addAttribute("jsonString", JSON.toJSONString(cbaseInfo));
       return "common/jsp/pojo";
   }
    
   
   /*
    * 公司基本信息修改
    */
    @RequestMapping(value="updateCompanyBaseInfo.do",method=RequestMethod.POST)
    public String updateCompanyBaseInfo(CompanyBaseInfo cbaseInfo,Model model){
      int ret= commonService.updateCompanyBaseInfo(cbaseInfo);
      if(ret<0){
          model.addAttribute("errMsg", "system error");
      }
        return "common/jsp/json";
    }
    
    /*
     * 获取幻灯片基本配置
     */
   @RequestMapping(value="getKinMaxShow.do")
   public String getKinMaxShow(Model model){
     List<KinMaxShow> k= commonService.getKinMaxShow();
     if(k!=null&&k.size()>0){
         model.addAttribute("jsonString", JSON.toJSONString(k.get(0)));
     }
       return "common/jsp/pojo";
   }
   /*
    * 修改信息
    */
   @RequestMapping(value="updateKinMaxShow.do",method=RequestMethod.POST)
   public String updateKinMaxShow(KinMaxShow kin,Model model){
     int ret= commonService.updateKinMaxShow(kin);
     if(ret<0){
         model.addAttribute("errMsg", "system error");
     }
       return "common/jsp/json";
   }
   
    
}
