package cn.com.jgre.entity;

import cn.com.jgre.common.Pojo;



public class NewsCategory extends Pojo{
    
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    String category_id;
    String category_name;
    int category_sort;
    String category_detail;
    String extra1;
    String extra2;
    public String getCategory_id() {
        return category_id;
    }
    public void setCategory_id(String category_id) {
        this.category_id = category_id;
    }
    public String getCategory_name() {
        return category_name;
    }
    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }
    public int getCategory_sort() {
        return category_sort;
    }
    public void setCategory_sort(int category_sort) {
        this.category_sort = category_sort;
    }
    public String getCategory_detail() {
        return category_detail;
    }
    public void setCategory_detail(String category_detail) {
        this.category_detail = category_detail;
    }
    public String getExtra1() {
        return extra1;
    }
    public void setExtra1(String extra1) {
        this.extra1 = extra1;
    }
    public String getExtra2() {
        return extra2;
    }
    public void setExtra2(String extra2) {
        this.extra2 = extra2;
    }

    
}
