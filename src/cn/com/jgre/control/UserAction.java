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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;

import cn.com.jgre.entity.Userinfo;
import cn.com.jgre.util.BaseParam;
import cn.com.jgre.util.DateUtil;
import cn.com.jgre.service.UserService;
/**
 * 
 * <p>用户类</p>
 *
 * 类说明
 *
 * @author jgre
 * @version 1.0
 *
 * 创建时间 2014-11-3 上午
 */
@Controller
@RequestMapping("/")
public class UserAction {
    @Resource
    private UserService userService;
    
    
    
    
    /*
     * 用户登录
     */
    @RequestMapping(value="login.do", method=RequestMethod.POST)
    public ModelAndView  login(HttpServletRequest request, Userinfo user,@RequestParam String rand,Model model){
       String ran=(String)request.getSession().getAttribute("rand");
       rand=rand==null?"":rand;
       //验证码支持大小写
       if(!rand.equalsIgnoreCase(ran)){
           System.out.println("验证码不正确");
           model.addAttribute("errMsg", "验证码不正确");
           return new ModelAndView("backgroundPages/jsp/login");
       }
       Userinfo loginUser=userService.login(user);
       //登录不成功
       if(loginUser==null){
           System.out.println("用户名密码不正确");
           model.addAttribute("errMsg", "用户名密码不正确");
           return new ModelAndView("backgroundPages/jsp/login");
       }else{
          // loginUser.setJifen(String.valueOf(Float.parseFloat(loginUser.getJifen())+BaseParam.Scole_Login));
           loginUser.setLastLogin(DateUtil.getCurrentTime());
           userService.updateUser(loginUser);
           request.getSession().setAttribute(BaseParam.LOGINUSER, loginUser);
       }
       return new ModelAndView("backgroundPages/jsp/index");
    }
    
    /*
     * 用户注册
     */
    @ResponseBody
    @RequestMapping(value="regUser.do",method=RequestMethod.POST)
    public String regUser(Userinfo user){
        Userinfo u=userService.getUserByName(user.getUsername());
        String jsonString="";
        if(u==null){
                user.setUserid(UUID.randomUUID().toString());
                user.setRegtime(DateUtil.getCurrentTime());
                user.setJifen("0");
                if(user.getUserpic()==null){
                    user.setUserpic(BaseParam.mrIMGPath);
                }
            int ret=userService.addUser(user);
            if(ret>0){
                jsonString="{SUCCESS:true,message:'注册成功！',objectId:'"+user.getUserid()+"'}";
                
            }else{
                jsonString="{SUCCESS:false,message:'注册失败！'}";
            }
        }else{
            jsonString="{SUCCESS:false,message:'用户名已存在'}";
        }
        
        return jsonString;
    }
    
    /*
     * 用户修改
     */
    @ResponseBody
    @RequestMapping(value="editUser.do",method=RequestMethod.POST)
    public String editUser(HttpServletRequest request,Userinfo user){
       int ret=userService.updateUser(user);
       String result="";
       if(ret>0){
           result="{SUCCESS:true,message:'保存成功！',objectId:'"+user.getUserid()+"'}";
           Userinfo loginUser=userService.getUserById(user.getUserid());
           request.getSession().setAttribute(BaseParam.LOGINUSER, loginUser);
       }else{
           result="{SUCCESS:false,message:'保存失败！'}";
       }
        return result;
    }
    
    
    /*
     * 根据id获取用户
     */
    @RequestMapping(value="getUserById.do",method=RequestMethod.GET)
    public String getUserById(@RequestParam String objectId,Model model){
        Userinfo user=userService.getUserById(objectId);
        model.addAttribute("jsonString", JSON.toJSONString(user));
        return "common/jsp/pojo";
    }
    
    
    /*
     * 获取用户、分页
     */
    
    @RequestMapping(value="getUsers.do",method=RequestMethod.POST)
    public String getUsers(Userinfo user,int offset,int limit,Model model){
        List<Userinfo> users=userService.getUsers(user, offset, limit);
        int totalProperty=userService.getTotalCount(user);
        model.addAttribute("totalProperty", totalProperty);
        model.addAttribute("jsonString", JSON.toJSONString(users));
        return "common/jsp/json";
    }
    
    /*
     * 删除用户
     */
    @ResponseBody
    @RequestMapping(value="deleteUsers.do")
    public String deleteUsers(String deleteId){
        if(deleteId!=null){
           String[] ids=deleteId.split("\\$");
        
           for(String id:ids){
             if(id!=null&&!id.equals("")){ 
             int result=  userService.deleteUserById(id);
               if(result<0){
                  return "{success:false,message:'删除失败！'}";
               }
             }
           }
        }
        
       return "{success:true}";
    }
    
   /*
    * 退出系统
    */
    @RequestMapping(value="extSystem.do")
    public String extSystem(HttpServletRequest request){
        //清空session
        request.getSession().invalidate();
        
        return "backgroundPages/jsp/login";
    }
}
