package cn.com.jgre.entity;

import cn.com.jgre.common.Pojo;

public class ProductPicture extends Pojo{

	private static final long serialVersionUID = -829842716845405105L;
	
	private String product_picture_id;
	
	private String product_id;
	
	private String picture_id;
	
	private String picture_url;
	
	private int show_index;

	public int getShow_index() {
		return show_index;
	}

	public void setShow_index(int show_index) {
		this.show_index = show_index;
	}

	public String getProduct_picture_id() {
		return product_picture_id;
	}

	public void setProduct_picture_id(String product_picture_id) {
		this.product_picture_id = product_picture_id;
	}

	public String getProduct_id() {
		return product_id;
	}

	public void setProduct_id(String product_id) {
		this.product_id = product_id;
	}

	public String getPicture_id() {
		return picture_id;
	}

	public void setPicture_id(String picture_id) {
		this.picture_id = picture_id;
	}

	public String getPicture_url() {
		return picture_url;
	}

	public void setPicture_url(String picture_url) {
		this.picture_url = picture_url;
	}
	

}
