package cn.com.jgre.entity;

import java.util.List;

import cn.com.jgre.common.Pojo;



public class Navigation extends Pojo{

    private static final long serialVersionUID = 1L;
    String navigation_id;
    String navigation_parent;
    String navigation_name;
    String navigation_url;
    String navigation_desc;
    String navigation_type;
    String navigation_icon;
    int navigation_order;
    int navigation_level;
    String navigation_other;
    List<Navigation> subMenus;
    public String getNavigation_id() {
        return navigation_id;
    }
    public void setNavigation_id(String navigation_id) {
        this.navigation_id = navigation_id;
    }
    public String getNavigation_parent() {
        return navigation_parent;
    }
    public void setNavigation_parent(String navigation_parent) {
        this.navigation_parent = navigation_parent;
    }
    public String getNavigation_name() {
        return navigation_name;
    }
    public void setNavigation_name(String navigation_name) {
        this.navigation_name = navigation_name;
    }
    public String getNavigation_url() {
        return navigation_url;
    }
    public void setNavigation_url(String navigation_url) {
        this.navigation_url = navigation_url;
    }
    public String getNavigation_desc() {
        return navigation_desc;
    }
    public void setNavigation_desc(String navigation_desc) {
        this.navigation_desc = navigation_desc;
    }
    public String getNavigation_type() {
        return navigation_type;
    }
    public void setNavigation_type(String navigation_type) {
        this.navigation_type = navigation_type;
    }
    public String getNavigation_icon() {
        return navigation_icon;
    }
    public void setNavigation_icon(String navigation_icon) {
        this.navigation_icon = navigation_icon;
    }
    public int getNavigation_order() {
        return navigation_order;
    }
    public void setNavigation_order(int navigation_order) {
        this.navigation_order = navigation_order;
    }
    public int getNavigation_level() {
        return navigation_level;
    }
    public void setNavigation_level(int navigation_level) {
        this.navigation_level = navigation_level;
    }
    public String getNavigation_other() {
        return navigation_other;
    }
    public void setNavigation_other(String navigation_other) {
        this.navigation_other = navigation_other;
    }
    public List<Navigation> getSubMenus() {
        return subMenus;
    }
    public void setSubMenus(List<Navigation> subMenus) {
        this.subMenus = subMenus;
    }
   
    
    
}
