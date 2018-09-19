package cn.com.jgre.control;

import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;



import com.alibaba.fastjson.JSON;

import cn.com.jgre.entity.Navigation;
import cn.com.jgre.service.NavService;
/**
 * 
 * <p>导航类</p>
 *
 * 类说明
 *
 * @author jgre
 * @version 1.0
 *
 * 创建时间 2014-11-4 上午10:44:30
 */

@RequestMapping("/")
@Controller
public class NavigationAction {
    
    @Resource
    private NavService navService;
    
  /*
   * 获取所有菜单
   */
    @RequestMapping(value="getMenu.do")
    public String getMenus(Navigation navigation,Model model){  
       List<Navigation> menus=navService.getNavigations(navigation);
       String jsonString=JSON.toJSONString(menus);
        model.addAttribute("jsonString", jsonString);
        return "common/jsp/pojo";
    }
    
    /*
     * 根据id获取menu对象
     */
    @RequestMapping(value="getMenuById.do",method=RequestMethod.GET)
    public String getMenuById(@RequestParam String objectid,Model model){
        Navigation n=navService.getNavById(objectid);
        model.addAttribute("jsonString", JSON.toJSONString(n));
        return "common/jsp/pojo";
    }
    
    /*
     * 保存或添加菜单
     */
    @ResponseBody
    @RequestMapping(value="saveMenu.do",method=RequestMethod.POST)
    public String saveMenu(Navigation navigation){
        Navigation n=navService.getNavById(navigation.getNavigation_id());
        
        if(n==null){
            navigation.setNavigation_id(UUID.randomUUID().toString());
            navigation.setNavigation_order(navService.getNavMaxOrder()+10);
           int ret= navService.addNav(navigation);
           if(ret<0){
               return "{SUCCESS:false,message:'保存失败！'}";
           }
        }else{
           int ret=navService.updateNav(navigation);
           if(ret<0){
               return "{SUCCESS:false,message:'保存失败！'}";
           }
        }
        
          return "{SUCCESS:true,message:'保存成功！'}";
    }
    
    /*
     * 删除菜单
     */
    @ResponseBody
    @RequestMapping(value="delMenu.do",method=RequestMethod.GET)
    public String delMenu(@RequestParam String objectid){
          int ret=navService.deleteNavById(objectid);
          if(ret<0){
              return "{SUCCESS:false,message:'删除失败！'}";
          }
          return "{SUCCESS:true,message:'删除成功！'}";
    }
    
    /*
     * 交换菜单
     */
    @RequestMapping(value="replaceNavigetion.do")
    public String replaceNavigetion(@RequestParam String firstNav,@RequestParam String secendNav,String expendStr,String menuType,Model model){
           Navigation firstN=navService.getNavById(firstNav);
           Navigation secendN=navService.getNavById(secendNav);
           int forder=firstN.getNavigation_order();
           int sorder=secendN.getNavigation_order();
           //交换序号
           firstN.setNavigation_order(sorder);
           secendN.setNavigation_order(forder);
           navService.updateNav(firstN);
           navService.updateNav(secendN);
           model.addAttribute("expendStr", expendStr);
           model.addAttribute("menuType", menuType);
        return "backgroundPages/jsp/navigation/navigation";
    }
}
