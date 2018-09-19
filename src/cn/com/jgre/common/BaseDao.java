package cn.com.jgre.common;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import cn.com.jgre.common.Pojo;
public interface BaseDao
{

    public abstract Pojo getRecordById(String s);

    public abstract int getTotalRecord(Pojo pojo);

    public abstract Integer getMaxRecord();

    @SuppressWarnings("rawtypes")
    public abstract List getRecords(Pojo pojo);

    @SuppressWarnings("rawtypes")
    public abstract List getRecords(Pojo pojo, RowBounds rowbounds);

    public abstract int insertRecord(Pojo pojo);

    public abstract int updateRecord(Pojo pojo);

    public abstract int deleteRecordById(String s);
}