package cn.com.jgre.serviceImpl;

import java.util.List;

import cn.com.jgre.dao.CommonDao;
import cn.com.jgre.entity.CompanyBaseInfo;
import cn.com.jgre.entity.KinMaxShow;
import cn.com.jgre.service.CommonService;

public class CommonServiceImpl implements CommonService{

    CommonDao commonDao;
    @Override
    public CompanyBaseInfo getCompanyBaseInfo() {
           List<CompanyBaseInfo> c= commonDao.getCompanyBaseInfo();
           if(c.size()>0){
               return c.get(0);
           }
        return null;
    }

    @Override
    public int updateCompanyBaseInfo(CompanyBaseInfo companyBaseInfo) {
        return commonDao.updateCompanyBaseInfo(companyBaseInfo);
    }
    
    @Override
    public List<KinMaxShow> getKinMaxShow() {
        return commonDao.getKinMaxShow();
    }

    @Override
    public int updateKinMaxShow(KinMaxShow kinMaxShow) {
       
        return commonDao.updateKinMaxShow(kinMaxShow);
    }
    

    public CommonDao getCommonDao() {
        return commonDao;
    }

    public void setCommonDao(CommonDao commonDao) {
        this.commonDao = commonDao;
    }

    
    
}
