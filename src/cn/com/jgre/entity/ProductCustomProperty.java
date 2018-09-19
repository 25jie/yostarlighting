package cn.com.jgre.entity;

import cn.com.jgre.common.Pojo;

public class ProductCustomProperty extends Pojo{

	private static final long serialVersionUID = -7835958113684553816L;

	private String custom_property_id;
	
	private String product_category;
	
	private String property_name;
	
	private String property_value;
	
	private Integer property_use;
	
	private Integer property_order;
	
	private String create_time;
	
	private String update_time;

	public String getCustom_property_id() {
		return custom_property_id;
	}

	public void setCustom_property_id(String custom_property_id) {
		this.custom_property_id = custom_property_id;
	}

	public String getProduct_category() {
		return product_category;
	}

	public void setProduct_category(String product_category) {
		this.product_category = product_category;
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

	public Integer getProperty_use() {
		return property_use;
	}

	public void setProperty_use(Integer property_use) {
		this.property_use = property_use;
	}

	public Integer getProperty_order() {
		return property_order;
	}

	public void setProperty_order(Integer property_order) {
		this.property_order = property_order;
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
}
