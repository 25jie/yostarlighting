package cn.com.jgre.entity;

import cn.com.jgre.common.Pojo;

public class ProductAdvancedProperty extends Pojo{

	private static final long serialVersionUID = 4243051324207653888L;
	
	private String advanced_property_id;
	
	private String product_id;
	
	private String advanced_property_name;
	
	private String content_html;
	
	private String content_text;
	
	private String inputime;
	
	private String author;
	
	public String getAdvanced_property_id() {
		return advanced_property_id;
	}

	public void setAdvanced_property_id(String advanced_property_id) {
		this.advanced_property_id = advanced_property_id;
	}

	public String getProduct_id() {
		return product_id;
	}

	public void setProduct_id(String product_id) {
		this.product_id = product_id;
	}

	public String getAdvanced_property_name() {
		return advanced_property_name;
	}

	public void setAdvanced_property_name(String advanced_property_name) {
		this.advanced_property_name = advanced_property_name;
	}

	public String getContent_html() {
		return content_html;
	}

	public void setContent_html(String content_html) {
		this.content_html = content_html;
	}

	public String getContent_text() {
		return content_text;
	}

	public void setContent_text(String content_text) {
		this.content_text = content_text;
	}

	public String getInputime() {
		return inputime;
	}

	public void setInputime(String inputime) {
		this.inputime = inputime;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}
	
	

}
