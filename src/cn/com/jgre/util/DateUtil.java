package cn.com.jgre.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 
 * <p>类描述</p>
 *
 * 时间工具
 *
 * @author jgre
 * @version 1.0
 *
 * 创建时间 2014-5-4 上午11:50:25
 */
public class DateUtil {
    
    
    /*
     *获取现在的时间 ：yyyy-MM-dd HH:mm:ss
     */
    
   public static String getCurrentTime(){
       SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
       Date date=new Date();
       return sdf.format(date);
   }
   
   public static String formatDateyyMMdd(){
       SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmss");
       return sdf.format(new Date());
   }
   public static void main(String[] args){
       System.out.println(getCurrentTime());
   }
}
