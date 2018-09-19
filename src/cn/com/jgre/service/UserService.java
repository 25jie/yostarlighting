package cn.com.jgre.service;

import java.util.List;

import cn.com.jgre.entity.Userinfo;







public interface UserService {
	  /**
     * 
     * 方法描述:查询所有用户信息
     *
     * @return 所有user
     *
     */
    public List<Userinfo> getUsers(Userinfo user);
    
    /*
     *方法描述：分页查询
     *
     */
    
    public List<Userinfo> getUsers(Userinfo u, int page, int limit);
    
    /*
     * 根据id查询用户
     * @return user对象
     */
    
    public Userinfo getUserById(String id);
    
    /*
     * 获取用户最大记录
     * 
     */
    public int getUserMaxRecord();
    
    /*
     * 根据id删除用户
     * 
     */
    public int deleteUserById(String id);
    
    /*
     * 更新用户信息
     * 
     */
    public int updateUser(Userinfo u);
    
   /*
    * 
    * 添加用户信息
    */
    public int addUser(Userinfo u);
    /*
     * 
     * 获取用户总数
     */
    public int tatolUser(Userinfo u);
    /*
     * 根据用户名去查找
     */
    public Userinfo getUserByName(String username);
   
    /*
     * 登录
     */
    public Userinfo login(Userinfo u);
    /*
     * 获取根据积分获取
     */
   public List<Userinfo> getUserByScole();
   
   /*
    * 获取首页展示用户
    */
   
   public List<Userinfo> getShowUser();
   
   public int getTotalCount(Userinfo u);
     
   
 
}
