package cn.com.jgre.serviceImpl;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import cn.com.jgre.dao.UserDao;

import cn.com.jgre.entity.Userinfo;

import cn.com.jgre.service.UserService;





public class UserServiceImpl implements UserService{
UserDao userDao;
	
	public int addUser(Userinfo u) {
		return userDao.insertRecord(u);
	}

	public int deleteUserById(String id) {
		return userDao.deleteRecordById(id);
	}

	public Userinfo getUserById(String id) {
		
		return (Userinfo) userDao.getRecordById(id);
	}

	public int getUserMaxRecord() {
		
		return userDao.getMaxRecord();
	}

	@SuppressWarnings("unchecked")
    public List<Userinfo> getUsers(Userinfo user) {
		
		return userDao.getRecords(user);
	}

	@SuppressWarnings("unchecked")
    public List<Userinfo> getUsers(Userinfo u, int page, int limit) {
		RowBounds rb=new RowBounds(page,limit);
		return userDao.getRecords(u, rb);
	}

	public int tatolUser(Userinfo u) {
		return userDao.getTotalRecord(u);
	}

	public List<Userinfo> getUserByScole() {
		
		return userDao.getByScole();
	}
	public int updateUser(Userinfo u) {
		return userDao.updateRecord(u);
	}

	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public Userinfo getUserByName(String username) {
		return userDao.getUserByName(username);
	}

	public Userinfo login(Userinfo u) {
		return userDao.login(u);
	}

    @Override
    public List<Userinfo> getShowUser() {
       
        return userDao.getShowUser();
    }

    @Override
    public int getTotalCount(Userinfo u) {
       
        return userDao.getTotalRecord(u);
    }

   



	

	

}
