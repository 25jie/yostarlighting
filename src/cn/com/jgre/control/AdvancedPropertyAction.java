package cn.com.jgre.control;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.jgre.entity.Category;
import cn.com.jgre.entity.ProductAdvancedProperty;
import cn.com.jgre.service.AdvancePropertyService;
import cn.com.jgre.util.StringUtil;

import com.alibaba.fastjson.JSON;

@Controller
@RequestMapping("/")
public class AdvancedPropertyAction {
	
	@Resource
	private AdvancePropertyService advancePropertyService;
	
	/**
	 * 新增类别
	 * @param category
	 * @return
	 */
	@ResponseBody
    @RequestMapping(value="editAdvancedProperty.do",method=RequestMethod.POST)
	public String addAdvancedProperty(ProductAdvancedProperty advancedProperty){
		String result = "";
		try {
			if (StringUtil.isEmpty(advancedProperty.getAdvanced_property_id())) {
				int addResult = advancePropertyService.addAdvancedProperty(advancedProperty);
				if (addResult > 0) {
					result = "{SUCCESS:true,message:'添加成功'}";
				} else {
					result = "{SUCCESS:false,message:'添加失败'}";
				}
			} else {
				int updateResult = advancePropertyService.updateAdvancedProperty(advancedProperty);
				if (updateResult > 0) {
					result = "{SUCCESS:true,message:'修改成功'}";
				} else {
					result = "{SUCCESS:false,message:'修改失败'}";
				}
			}
		} catch (Exception e) {
			result = "{SUCCESS:false,message:'数据库异常，添加失败!'}";
		}
		
		return result;
	}
	
	/**
	 * 
	 * @param objectId
	 * @param model
	 * @return
	 */
    @RequestMapping(value="getAdvancedProperty.do",method=RequestMethod.POST)
	public String getAdvancedProperty(ProductAdvancedProperty advancedProperty, Model model){
    	String jsonString = "";
    	if (!StringUtil.isEmpty(advancedProperty.getProduct_id())) {
    		List<ProductAdvancedProperty> advancedProperties = advancePropertyService.queryAdvancedPropertys(advancedProperty);
    		if (advancedProperties != null && advancedProperties.size() != 0) {
    			jsonString = JSON.toJSONString(advancedProperties.get(0));
    		}
		}
		model.addAttribute("jsonString", jsonString);
        return "common/jsp/json";
	}

	public AdvancePropertyService getAdvancePropertyService() {
		return advancePropertyService;
	}

	public void setAdvancePropertyService(
			AdvancePropertyService advancePropertyService) {
		this.advancePropertyService = advancePropertyService;
	}
	
	
}
