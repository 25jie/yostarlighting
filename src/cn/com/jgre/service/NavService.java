package cn.com.jgre.service;

import java.util.List;

import cn.com.jgre.entity.Navigation;







public interface NavService {
	  /**
     * 
     * 方法描述:查询所有用户信息
     *
     * @return 所有user
     *
     */
    public List<Navigation> getNavigations(Navigation n);
    
    /*
     *方法描述：分页查询
     *
     */
    
    public List<Navigation> getNavigations(Navigation n, int page, int limit);
    
    /*
     * 根据id查询用户
     * @return user对象
     */
    
    public Navigation getNavById(String id);
    
    /*
     * 获取用户最大记录
     * 
     */
    public int getNavMaxOrder();
    
    /*
     * 根据id删除用户
     * 
     */
    public int deleteNavById(String id);
    
    /*
     * 更新用户信息
     * 
     */
    public int updateNav(Navigation u);
    
   /*
    * 
    * 添加用户信息
    */
    public int addNav(Navigation u);
    
  
   public int getTotalCount(Navigation u);
     
}
