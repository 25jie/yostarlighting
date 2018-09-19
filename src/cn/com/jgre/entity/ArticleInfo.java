package cn.com.jgre.entity;

import java.util.List;

import cn.com.jgre.common.Pojo;



/**
 * 
 * <p>类描述</p>
 *
 * 招聘信息
 *
 * @author jgre
 * @version 1.0
 *
 * 创建时间 2014-5-4 上午10:41:16
 */
public class ArticleInfo extends Pojo{

    private static final long serialVersionUID = 1L;
    String article_id;//id
    String article_title;//主题
    String article_content;//内容
    String article_pubtime;//发布时间
    String article_category;//分类
    String article_categoryDM;//分类标志
    String article_html;//html内容
    String article_author;//作者
    int article_view_count;//浏览次数
    int article_discess_count;//评论次数
    String needshow;//是否展示
    String last_modifytime;//最后修改时间
    List<Pictureinfo> pictures;
    public String getArticle_id() {
        return article_id;
    }
    public void setArticle_id(String article_id) {
        this.article_id = article_id;
    }
    public String getArticle_title() {
        return article_title;
    }
    public void setArticle_title(String article_title) {
        this.article_title = article_title;
    }
    public String getArticle_content() {
        return article_content;
    }
    public void setArticle_content(String article_content) {
        this.article_content = article_content;
    }
    public String getArticle_pubtime() {
        return article_pubtime;
    }
    public void setArticle_pubtime(String article_pubtime) {
        this.article_pubtime = article_pubtime;
    }
    public String getArticle_author() {
        return article_author;
    }
    public void setArticle_author(String article_author) {
        this.article_author = article_author;
    }
    
    public String getArticle_category() {
        return article_category;
    }
    public void setArticle_category(String article_category) {
        this.article_category = article_category;
    }
    public String getArticle_html() {
        return article_html;
    }
    public void setArticle_html(String article_html) {
        this.article_html = article_html;
    }
    
    public int getArticle_view_count() {
        return article_view_count;
    }
    public void setArticle_view_count(int article_view_count) {
        this.article_view_count = article_view_count;
    }
    public int getArticle_discess_count() {
        return article_discess_count;
    }
    public void setArticle_discess_count(int article_discess_count) {
        this.article_discess_count = article_discess_count;
    }
    public String getNeedshow() {
        return needshow;
    }
    public void setNeedshow(String needshow) {
        this.needshow = needshow;
    }
    public String getLast_modifytime() {
        return last_modifytime;
    }
    public void setLast_modifytime(String last_modifytime) {
        this.last_modifytime = last_modifytime;
    }
    public List<Pictureinfo> getPictures() {
        return pictures;
    }
    public void setPictures(List<Pictureinfo> pictures) {
        this.pictures = pictures;
    }
    public String getArticle_categoryDM() {
        return article_categoryDM;
    }
    public void setArticle_categoryDM(String article_categoryDM) {
        this.article_categoryDM = article_categoryDM;
    }
    
}
