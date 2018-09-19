package cn.com.jgre.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import cn.com.jgre.entity.Userinfo;
import cn.com.jgre.util.BaseParam;

/**
 * 
 * <p>检测用户是否登陆，如果没有登录，则直接跳到登录页面</p>
 *
 * 类说明
 *
 * @author jgre
 * @version 1.0
 *
 * 创建时间 2014-11-21 上午10:09:39
 */
public class SingleLoginInterceptor implements HandlerInterceptor{

    String[] allowUrls;//不拦截的地址
    /**
     * 
     * 整个请求处理完毕回调方法，即在视图渲染完毕时回调，如性能监控中我们可以在此记录结束时间并输出消耗时间，
     * 还可以进行一些资源清理，类似于try-catch-finally中的finally，
     * 但仅调用处理器执行链中preHandle返回true的拦截器的afterCompletion。 
     * {@inheritDoc}
     * @see org.springframework.web.servlet.HandlerInterceptor#afterCompletion(HttpServletRequest, HttpServletResponse, Object, Exception)
     */
    @Override
    public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2,
            Exception arg3) throws Exception {
        
    }

    /*
     * 后处理回调方法，实现处理器的后处理（但在渲染视图之前），
     * 此时我们可以通过modelAndView（模型和视图对象）对模型数据进行处理或对视图进行处理，
     * modelAndView也可能为null。
     */
    @Override
    public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2,
            ModelAndView arg3) throws Exception {
        
    }
  /*
   * 预处理回调方法，实现处理器的预处理（如登录检查），第三个参数为响应的处理器
   */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object arg2)
            throws Exception {
        String baseUri = request.getContextPath();
        System.out.println("pre handler....");
        String requestUrl = request.getRequestURI().replace(request.getContextPath(), "");  
        System.out.println("requestUrl："+requestUrl);
        if(requestUrl!=null&&requestUrl.length()>0){
            for(String url:allowUrls){
                if(requestUrl.contains(url)){
                    return true;
                }
            }
        }
        Userinfo user = (Userinfo) request.getSession().getAttribute(BaseParam.LOGINUSER); 
        if(user!=null){
            return true;
        }else{
           // response.sendRedirect(baseUri+"");
            return false;
        }
       
    }

    public String[] getAllowUrls() {
        return allowUrls;
    }

    public void setAllowUrls(String[] allowUrls) {
        this.allowUrls = allowUrls;
    }

    
    
}
