package cn.com.jgre.dao;

import java.util.List;

import cn.com.jgre.common.BaseDao;

import cn.com.jgre.entity.Userinfo;






public interface UserDao extends BaseDao{

	public Userinfo getUserByName(String username);
	
	public Userinfo login(Userinfo u);
	
	public List<Userinfo> getByScole();
	
	
	public List<Userinfo> getShowUser();
	

}
