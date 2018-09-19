package cn.com.jgre.entity;

import cn.com.jgre.common.Pojo;



public class Pictureinfo extends Pojo{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String picture_id;
	String picture_title;
	String pucture_url;
	String picture_name;
	String picture_newname;
	String picture_detail;
	String picture_uploadtime;
	String picture_author;
	String picture_showOnIndex;
	String picture_category_id;
	String extra1;
	
	public String getPicture_id() {
		return picture_id;
	}
	public void setPicture_id(String picture_id) {
		this.picture_id = picture_id;
	}
	public String getPicture_title() {
		return picture_title;
	}
	public void setPicture_title(String picture_title) {
		this.picture_title = picture_title;
	}
	public String getPucture_url() {
		return pucture_url;
	}
	public void setPucture_url(String pucture_url) {
		this.pucture_url = pucture_url;
	}
	public String getPicture_name() {
		return picture_name;
	}
	public void setPicture_name(String picture_name) {
		this.picture_name = picture_name;
	}
	public String getPicture_detail() {
		return picture_detail;
	}
	public void setPicture_detail(String picture_detail) {
		this.picture_detail = picture_detail;
	}
	public String getPicture_uploadtime() {
		return picture_uploadtime;
	}
	public void setPicture_uploadtime(String picture_uploadtime) {
		this.picture_uploadtime = picture_uploadtime;
	}
	public String getPicture_author() {
		return picture_author;
	}
	public void setPicture_author(String picture_author) {
		this.picture_author = picture_author;
	}
	public String getPicture_showOnIndex() {
		return picture_showOnIndex;
	}
	public void setPicture_showOnIndex(String picture_showOnIndex) {
		this.picture_showOnIndex = picture_showOnIndex;
	}
	public String getPicture_category_id() {
		return picture_category_id;
	}
	public void setPicture_category_id(String picture_category_id) {
		this.picture_category_id = picture_category_id;
	}
    public String getPicture_newname() {
        return picture_newname;
    }
    public void setPicture_newname(String picture_newname) {
        this.picture_newname = picture_newname;
    }
	public String getExtra1() {
		return extra1;
	}
	public void setExtra1(String extra1) {
		this.extra1 = extra1;
	}
}
