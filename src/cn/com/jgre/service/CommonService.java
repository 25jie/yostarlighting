package cn.com.jgre.service;

import java.util.List;

import cn.com.jgre.entity.CompanyBaseInfo;
import cn.com.jgre.entity.KinMaxShow;

public interface CommonService {

    
    /*
     * 获取公司基本信息
     * 
     */
    public CompanyBaseInfo getCompanyBaseInfo();
    
    /*
     * 修改信息
     */
    public int updateCompanyBaseInfo(CompanyBaseInfo companyBaseInfo);
    
    
    
    public List<KinMaxShow> getKinMaxShow();
    
    
    public int updateKinMaxShow(KinMaxShow kinMaxShow);
}
