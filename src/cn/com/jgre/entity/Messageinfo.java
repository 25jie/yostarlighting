package cn.com.jgre.entity;

import cn.com.jgre.common.Pojo;



public class Messageinfo extends Pojo {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	String message_id;
	String message_time;
	String message_person;
	String message_person_id;
	String message_person_pic;
	//后面添加
	String message_person_tel;
	String message_person_email;
	String message_person_company;
	String message_person_address;
	
	String message_title;
	int message_type;
	String message_type_id;
	String message_type_name;
	String message_content;
	String message_content_html;
	String isLook;
	//后面添加


	
	public String getMessage_id() {
		return message_id;
	}

	public void setMessage_id(String message_id) {
		this.message_id = message_id;
	}

	public String getMessage_time() {
		return message_time;
	}

	public void setMessage_time(String message_time) {
		this.message_time = message_time;
	}

	public String getMessage_person() {
		return message_person;
	}

	public void setMessage_person(String message_person) {
		this.message_person = message_person;
	}

	public String getMessage_title() {
		return message_title;
	}

	public void setMessage_title(String message_title) {
		this.message_title = message_title;
	}

	

	public int getMessage_type() {
        return message_type;
    }

    public void setMessage_type(int message_type) {
        this.message_type = message_type;
    }

    public String getMessage_type_id() {
		return message_type_id;
	}

	public void setMessage_type_id(String message_type_id) {
		this.message_type_id = message_type_id;
	}

	public String getMessage_content() {
		return message_content;
	}

	public void setMessage_content(String message_content) {
		this.message_content = message_content;
	}

	public String getMessage_content_html() {
		return message_content_html;
	}

	public void setMessage_content_html(String message_content_html) {
		this.message_content_html = message_content_html;
	}

	public String getMessage_person_id() {
		return message_person_id;
	}

	public void setMessage_person_id(String message_person_id) {
		this.message_person_id = message_person_id;
	}

	public String getMessage_person_pic() {
		return message_person_pic;
	}

	public void setMessage_person_pic(String message_person_pic) {
		this.message_person_pic = message_person_pic;
	}

    public String getMessage_type_name() {
        return message_type_name;
    }

    public void setMessage_type_name(String message_type_name) {
        this.message_type_name = message_type_name;
    }

    public String getMessage_person_tel() {
        return message_person_tel;
    }

    public void setMessage_person_tel(String message_person_tel) {
        this.message_person_tel = message_person_tel;
    }

    public String getMessage_person_email() {
        return message_person_email;
    }

    public void setMessage_person_email(String message_person_email) {
        this.message_person_email = message_person_email;
    }

    public String getMessage_person_company() {
        return message_person_company;
    }

    public void setMessage_person_company(String message_person_company) {
        this.message_person_company = message_person_company;
    }

    public String getMessage_person_address() {
        return message_person_address;
    }

    public void setMessage_person_address(String message_person_address) {
        this.message_person_address = message_person_address;
    }

    public String getIsLook() {
        return isLook;
    }

    public void setIsLook(String isLook) {
        this.isLook = isLook;
    }

	
}
