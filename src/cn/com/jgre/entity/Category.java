package cn.com.jgre.entity;

import cn.com.jgre.common.Pojo;

public class Category extends Pojo{

	private static final long serialVersionUID = -7108444731609901986L;
	
	private String category_id;
	
	private String category_name;
	
	private String category_remark;
	
	private Integer category_order;
	
	private String create_time;
	
	private String update_time;

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

	public String getCategory_remark() {
		return category_remark;
	}

	public void setCategory_remark(String category_remark) {
		this.category_remark = category_remark;
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

	public Integer getCategory_order() {
		return category_order;
	}

	public void setCategory_order(Integer category_order) {
		this.category_order = category_order;
	}

}
