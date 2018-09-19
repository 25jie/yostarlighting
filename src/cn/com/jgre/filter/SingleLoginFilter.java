package cn.com.jgre.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.com.jgre.entity.Userinfo;
import cn.com.jgre.util.BaseParam;

public class SingleLoginFilter implements Filter{

   String unCheckedUrl;
    @Override
    public void destroy() {
      
    }

    @Override
    public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest request=(HttpServletRequest)arg0;
        HttpServletResponse reponse=(HttpServletResponse)arg1;
        //String baseUri = request.getContextPath();
        String requestUrl = request.getRequestURI().replace(request.getContextPath(), "");  
        System.out.println("requestUrl："+requestUrl);
        if(requestUrl!=null&&requestUrl.contains(unCheckedUrl)){
            chain.doFilter(arg0, arg1);
        }else{
            Userinfo user = (Userinfo) request.getSession().getAttribute(BaseParam.LOGINUSER); 
            if(user!=null){
                chain.doFilter(arg0, arg1);
            }else{
                reponse.sendRedirect("http://"+request.getHeader("Host")+"/portal/backgroundPages/jsp/login.jsp");
            }
       }
    }

    @Override
    public void init(FilterConfig config) throws ServletException {
        unCheckedUrl=config.getInitParameter("unCheckedUrl");
    }


    
}
