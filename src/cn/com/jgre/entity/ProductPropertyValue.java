package cn.com.jgre.entity;

import cn.com.jgre.common.Pojo;

public class ProductPropertyValue extends Pojo{

	private static final long serialVersionUID = -2045835705998835319L;

	private String id;
	
	private String product_id;
	
	private String property_id;
	
	private String property_name;
	
	private String property_value;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getProduct_id() {
		return product_id;
	}

	public void setProduct_id(String product_id) {
		this.product_id = product_id;
	}

	public String getProperty_id() {
		return property_id;
	}

	public void setProperty_id(String property_id) {
		this.property_id = property_id;
	}

	public String getProperty_name() {
		return property_name;
	}

	public void setProperty_name(String property_name) {
		this.property_name = property_name;
	}

	public String getProperty_value() {
		return property_value;
	}

	public void setProperty_value(String property_value) {
		this.property_value = property_value;
	}
	
}
