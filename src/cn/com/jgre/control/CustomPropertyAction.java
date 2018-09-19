package cn.com.jgre.control;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.jgre.entity.ProductCustomProperty;
import cn.com.jgre.entity.ProductPropertyValue;
import cn.com.jgre.service.CustomPropertyService;
import cn.com.jgre.service.PropertyValueService;
import cn.com.jgre.util.StringUtil;

import com.alibaba.fastjson.JSON;

@Controller
@RequestMapping("/")
public class CustomPropertyAction {

	@Resource
	private CustomPropertyService customPropertyService;

	@Resource
	private PropertyValueService propertyValueService;

	/**
	 * 新增类别
	 * 
	 * @param
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "editCustomProperty.do", method = RequestMethod.POST)
	public String editCustomProperty(ProductCustomProperty customProperty) {
		String result = "";
		try {
			
			ProductCustomProperty queryObj = new ProductCustomProperty();
			queryObj.setProperty_name(customProperty.getProperty_name());
			queryObj.setProduct_category(customProperty.getProduct_category());
			List<ProductCustomProperty> cps = customPropertyService.queryCustomPropertys(queryObj);
			
			ProductCustomProperty temp = null;
			if (!CollectionUtils.isEmpty(cps)) {
				temp = cps.get(0);
			}
			
			if (StringUtil.isEmpty(customProperty.getCustom_property_id())) {
				if (temp == null) {
					int addResult = customPropertyService.addCustomProperty(customProperty);
					if (addResult > 0) {
						result = "{SUCCESS:true,message:'添加成功',objectId:'"+ customProperty.getCustom_property_id() + "'}";
					} else {
						result = "{SUCCESS:false,message:'添加失败'}";
					}
				} else {
					result = "{SUCCESS:false,message:'参数名称已存在，请重新输入'}";
				}
				
			} else {
				if (temp == null || customProperty.getCustom_property_id().equals(temp.getCustom_property_id())) {
					int updateResult = customPropertyService.updateCustomProperty(customProperty);
					if (updateResult > 0) {
						result = "{SUCCESS:true,message:'修改成功',objectId:'"+ customProperty.getCustom_property_id() + "'}";
					} else {
						result = "{SUCCESS:false,message:'修改失败'}";
					}
				} else {
					result = "{SUCCESS:false,message:'参数名称已存在，请重新输入'}";
				}
			}
		} catch (Exception e) {
			result = "{SUCCESS:false,message:'数据库异常，添加失败!'}";
		}

		return result;
	}

	/**
	 * 分页查询
	 * 
	 * @param
	 * @return
	 */
	@RequestMapping(value = "queryCustomPropertys.do", method = RequestMethod.POST)
	public String queryCustomPropertys(ProductCustomProperty customProperty,
			int offset, int limit, Model model) {
		List<ProductCustomProperty> customPropertys = customPropertyService.getCustomPropertys(customProperty, 0, 10000);
		model.addAttribute("jsonString", JSON.toJSONString(customPropertys));
		return "common/jsp/json";
	}

	/**
	 * 根据产品类别查询自定义属性列表
	 * 
	 * @param customProperty
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "getCustomPropertys.do", method = RequestMethod.POST)
	public String getCustomPropertys(ProductCustomProperty customProperty, Model model) {
		List<ProductCustomProperty> customPropertys = customPropertyService.queryCustomPropertys(customProperty);
		model.addAttribute("jsonString", JSON.toJSONString(customPropertys));
		return "common/jsp/json";
	}

	/*
	 * 删除用户
	 */
	@ResponseBody
	@RequestMapping(value = "deleteCustomPropertys.do")
	public String deleteCustomPropertys(String deleteId) {
		String reutrnStr = "";
		if (!StringUtil.isEmpty(deleteId)) {
			ProductPropertyValue propertyValue = new ProductPropertyValue();
			propertyValue.setProperty_id(deleteId);
			List<ProductPropertyValue> list = propertyValueService.queryPropertyValues(propertyValue);
			if (CollectionUtils.isEmpty(list)) {
				int result = customPropertyService.deleteCustomPropertyById(deleteId);
				if (result < 0) {
					reutrnStr = "{success:false,message:'删除失败！'}";
				} else {
					reutrnStr = "{success:true,message:'删除成功！'}";
				}
			} else {
				reutrnStr = "{success:false,message:'删除失败，产品正在使用该参数！'}";
			}
		} else {
			reutrnStr = "{success:false,message:'参数非法！'}";
		}
		
		return reutrnStr;
	}
	
	/**
	 * 根据ID查询类别信息
	 * 
	 * @param objectId
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "getCustomPropertyById.do", method = RequestMethod.GET)
	public String getCustomPropertyById(@RequestParam String objectId, Model model) {
		ProductCustomProperty customProperty = customPropertyService.getCustomPropertyById(objectId);
		return "{SUCCESS:true, root:" + JSON.toJSONString(customProperty) + "}";
	}
	
	/**
	 * 修改
	 * 
	 * @param
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "updateCusProUsed.do", method = RequestMethod.POST)
	public String updateCusProUsed(ProductCustomProperty customProperty) {
		String result = "";
		try {
			int ret = customPropertyService.updateCustomProperty(customProperty);
			if (ret > 0) {
				result = "{SUCCESS:false,message:'操作成功！'}";
			} else {
				result = "{SUCCESS:false,message:'操作失败！'}";
			}
		} catch (Exception e) {
			result = "{SUCCESS:false,message:'数据库异常，操作失败！'}";
		}

		return result;
	}

	public CustomPropertyService getCustomPropertyService() {
		return customPropertyService;
	}

	public void setCustomPropertyService(
			CustomPropertyService customPropertyService) {
		this.customPropertyService = customPropertyService;
	}

}
