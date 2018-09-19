package cn.com.jgre.serviceImpl;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import cn.com.jgre.dao.NavDao;
import cn.com.jgre.entity.Navigation;
import cn.com.jgre.service.NavService;



public class NavServiceImpl implements NavService{

    NavDao navDao;
    @SuppressWarnings("unchecked")
    @Override
    public List<Navigation> getNavigations(Navigation n) {
       
        return navDao.getRecords(n);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Navigation> getNavigations(Navigation n, int page, int limit) {
       RowBounds rb=new RowBounds(page,limit);
        return navDao.getRecords(n, rb);
    }

    @Override
    public Navigation getNavById(String id) {
     
        return (Navigation) navDao.getRecordById(id);
    }

    @Override
    public int getNavMaxOrder() {
        return navDao.getMaxRecord();
    }

    @Override
    public int deleteNavById(String id) {
        return navDao.deleteRecordById(id);
    }

    @Override
    public int updateNav(Navigation u) {
        return navDao.updateRecord(u);
    }

    @Override
    public int addNav(Navigation u) {
        return navDao.insertRecord(u);
    }

    @Override
    public int getTotalCount(Navigation u) {
      
        return navDao.getTotalRecord(u);
    }

    public NavDao getNavDao() {
        return navDao;
    }

    public void setNavDao(NavDao navDao) {
        this.navDao = navDao;
    }

    
}
