package cn.com.jgre.entity;

import cn.com.jgre.common.Pojo;

public class ProductBasic extends Pojo{
	private static final long serialVersionUID = 3829332491205670905L;

	private String product_id;
	
	private String product_category;
	
	private String product_name;
	
	private Integer status;
	
	private String create_time;
	
	private String update_time;
	
	private String propertyValues;
	
	private String category_name;
	
	private String pictures;
	
	private String productPictures;
	
	private String picture_url;

	private String warranty;

	private String summary;

	private String product_title;

	private String product_spec;

	private Integer stock;

	public String getProduct_title() {
		return product_title;
	}

	public void setProduct_title(String product_title) {
		this.product_title = product_title;
	}

	public String getProduct_spec() {
		return product_spec;
	}

	public void setProduct_spec(String product_spec) {
		this.product_spec = product_spec;
	}

	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}

	public String getPicture_url() {
		return picture_url;
	}

	public void setPicture_url(String picture_url) {
		this.picture_url = picture_url;
	}

	public String getProduct_id() {
		return product_id;
	}

	public void setProduct_id(String product_id) {
		this.product_id = product_id;
	}

	public String getProduct_category() {
		return product_category;
	}

	public void setProduct_category(String product_category) {
		this.product_category = product_category;
	}

	public String getProduct_name() {
		return product_name;
	}

	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getCreate_time() {
		return create_time;
	}

	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}

	public String getUpdate_time() {
		return update_time;
	}

	public void setUpdate_time(String update_time) {
		this.update_time = update_time;
	}

	public String getPropertyValues() {
		return propertyValues;
	}

	public void setPropertyValues(String propertyValues) {
		this.propertyValues = propertyValues;
	}

	public String getCategory_name() {
		return category_name;
	}

	public void setCategory_name(String category_name) {
		this.category_name = category_name;
	}

	public String getPictures() {
		return pictures;
	}

	public void setPictures(String pictures) {
		this.pictures = pictures;
	}

	public String getProductPictures() {
		return productPictures;
	}

	public void setProductPictures(String productPictures) {
		this.productPictures = productPictures;
	}

	public String getWarranty(){return warranty;}

	public void setWarranty(String warranty){this.warranty = warranty;}

	public String getSummary(){
		return summary;
	}

	public void setSummary(String summary){
		this.summary = summary;
	}
	
}
