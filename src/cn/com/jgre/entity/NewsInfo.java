package cn.com.jgre.entity;

import cn.com.jgre.common.Pojo;



public class NewsInfo extends Pojo{

    /**
     * 新闻信息
     */
    private static final long serialVersionUID = 1L;

    
    String news_id;
    String news_title;
    String news_content;//内容
    String news_desc;//描述
    String news_flag;//标签
    String news_html;//html样式
    int news_views;//阅读次数
    int news_order;//排序
    String news_show;//是否前台展示
    String news_pubtime;
    String news_type;//分类
    String news_typeName;
    String modifytime;
    String LastUpdate;
    String extra1;
    String extra2;
    String extra3;
    String extra4;
    String extra5;
    public String getNews_id() {
        return news_id;
    }
    public void setNews_id(String news_id) {
        this.news_id = news_id;
    }
    public String getNews_title() {
        return news_title;
    }
    public void setNews_title(String news_title) {
        this.news_title = news_title;
    }
    public String getNews_content() {
        return news_content;
    }
    public void setNews_content(String news_content) {
        this.news_content = news_content;
    }
    public String getNews_desc() {
        return news_desc;
    }
    public void setNews_desc(String news_desc) {
        this.news_desc = news_desc;
    }
    public String getNews_flag() {
        return news_flag;
    }
    public void setNews_flag(String news_flag) {
        this.news_flag = news_flag;
    }
    public String getNews_html() {
        return news_html;
    }
    public void setNews_html(String news_html) {
        this.news_html = news_html;
    }
    public int getNews_views() {
        return news_views;
    }
    public void setNews_views(int news_views) {
        this.news_views = news_views;
    }
    public int getNews_order() {
        return news_order;
    }
    public void setNews_order(int news_order) {
        this.news_order = news_order;
    }
    public String getNews_show() {
        return news_show;
    }
    public void setNews_show(String news_show) {
        this.news_show = news_show;
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
    public String getExtra3() {
        return extra3;
    }
    public void setExtra3(String extra3) {
        this.extra3 = extra3;
    }
    public String getExtra4() {
        return extra4;
    }
    public void setExtra4(String extra4) {
        this.extra4 = extra4;
    }
    public String getExtra5() {
        return extra5;
    }
    public void setExtra5(String extra5) {
        this.extra5 = extra5;
    }
    public String getNews_pubtime() {
        return news_pubtime;
    }
    public void setNews_pubtime(String news_pubtime) {
        this.news_pubtime = news_pubtime;
    }
    public String getModifytime() {
        return modifytime;
    }
    public void setModifytime(String modifytime) {
        this.modifytime = modifytime;
    }
    public String getLastUpdate() {
        return LastUpdate;
    }
    public void setLastUpdate(String lastUpdate) {
        LastUpdate = lastUpdate;
    }
    public String getNews_type() {
        return news_type;
    }
    public void setNews_type(String news_type) {
        this.news_type = news_type;
    }
    public String getNews_typeName() {
        return news_typeName;
    }
    public void setNews_typeName(String news_typeName) {
        this.news_typeName = news_typeName;
    }
    
}
