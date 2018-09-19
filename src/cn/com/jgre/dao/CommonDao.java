package cn.com.jgre.dao;

import java.util.List;

import cn.com.jgre.common.BaseDao;
import cn.com.jgre.entity.CompanyBaseInfo;
import cn.com.jgre.entity.KinMaxShow;

public interface CommonDao extends BaseDao{

    
    
    
    public List<CompanyBaseInfo> getCompanyBaseInfo();
    
    public int updateCompanyBaseInfo(CompanyBaseInfo companyBaseInfo);
    
    public List<KinMaxShow> getKinMaxShow();
    
    
    public int updateKinMaxShow(KinMaxShow kinMaxShow);
}
