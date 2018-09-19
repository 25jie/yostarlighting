package cn.com.jgre.control;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.jgre.entity.Category;
import cn.com.jgre.entity.Pictureinfo;
import cn.com.jgre.entity.ProductBasic;
import cn.com.jgre.entity.ProductPicture;
import cn.com.jgre.entity.ProductPropertyValue;
import cn.com.jgre.service.AdvancePropertyService;
import cn.com.jgre.service.CategoryService;
import cn.com.jgre.service.PictureService;
import cn.com.jgre.service.ProductBasicService;
import cn.com.jgre.service.ProductPictureService;
import cn.com.jgre.service.PropertyValueService;
import cn.com.jgre.util.StringUtil;

import com.alibaba.fastjson.JSON;

@Controller
@RequestMapping("/")
public class ProductBasicAction {

	@Resource
	private ProductBasicService productBasicService;

	@Resource
	private PropertyValueService propertyValueService;

	@Resource
	private AdvancePropertyService advancePropertyService;
	
	@Resource
	private ProductPictureService productPictureService;
	
	@Resource
	private PictureService pictureService;
	
	@Resource
	private CategoryService categoryService;

	/**
	 * 新增and修改
	 * 
	 * @param productBasic
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "editProductBasic.do", method = RequestMethod.POST)
	public String addProductBasic(ProductBasic productBasic, HttpServletRequest request) {
		String result = "";
		try {

			ProductBasic qProductBasic = new ProductBasic();
			qProductBasic.setProduct_name(productBasic.getProduct_name());
			
			// 保存图片信息
			List<Pictureinfo> pictureinfos = JSON.parseArray(productBasic.getPictures(), Pictureinfo.class);
			
			if (StringUtil.isEmpty(productBasic.getProduct_id())) {
				List<ProductBasic> productBasicList = productBasicService.queryProductBasics(qProductBasic);
				if (productBasicList != null && productBasicList.size() == 0) {
					int addResult = productBasicService.addProductBasic(productBasic);
					if (addResult > 0) {
						// 保存产品属性值
						editPropertyValue(productBasic);

						savePictures(pictureinfos, productBasic.getProduct_id(), request);
						result = "{SUCCESS:true,message:'添加成功',objectId:'"+ productBasic.getProduct_id() + "'}";
					} else {
						result = "{SUCCESS:false,message:'添加失败'}";
					}
				} else {
					result = "{SUCCESS:false,message:'产品名称已经存在，请重新输入'}";
				}
			} else {
				int updateResult = productBasicService.updateProductBasic(productBasic);
				if (updateResult > 0) {
					// 修改产品属性值
					editPropertyValue(productBasic);

					// 修改图片信息
					savePictures(pictureinfos, productBasic.getProduct_id(), request);
					result = "{SUCCESS:true,message:'修改成功',objectId:'"+ productBasic.getProduct_id() + "'}";
				} else {
					result = "{SUCCESS:false,message:'修改失败'}";
				}
			}
		} catch (Exception e) {
			result = "{SUCCESS:false,message:'数据库异常，添加失败!'}";
		}

		return result;
	}
	
	private void savePictures(List<Pictureinfo> pictureinfos, String productId,
			HttpServletRequest request) {
		if (StringUtil.isEmpty(productId)
				|| CollectionUtils.isEmpty(pictureinfos)) {
			return;
		}
		
		//先删除记录再新增
		productPictureService.deleteProductPictureByProductId(productId);
		
		for (Pictureinfo pictureinfo : pictureinfos) {
			ProductPicture pp = new ProductPicture();
			pp.setProduct_id(productId);
			pp.setPicture_id(pictureinfo.getPicture_id());
			pp.setPicture_url(pictureinfo.getPucture_url());
			pp.setShow_index(Integer.valueOf(pictureinfo.getPicture_showOnIndex()));
			productPictureService.addProductPicture(pp);
		}
		
	}
	
	private int editPropertyValue(ProductBasic productBasic) {
		int result = 0;
		if (productBasic == null) {
			return result;
		}

		List<ProductPropertyValue> propertyValues = JSON.parseArray(productBasic.getPropertyValues(), ProductPropertyValue.class);
		for (ProductPropertyValue productPropertyValue : propertyValues) {
			productPropertyValue.setProduct_id(productBasic.getProduct_id());
			// 如果属性为新增属性值则增加，否则为修改
			if (StringUtil.isEmpty(productPropertyValue.getId())) {
				result += propertyValueService.addPropertyValue(productPropertyValue);
			} else {
				result += propertyValueService.updatePropertyValue(productPropertyValue);
			}
		}

		return result;
	}

	/**
	 * 分页查询
	 * 
	 * @param productBasic
	 * @return
	 */
	@RequestMapping(value = "queryProductBasics.do", method = RequestMethod.POST)
	public String queryProductBasics(ProductBasic productBasic, int offset,int limit, Model model) {
		List<ProductBasic> productBasics = productBasicService.getProductBasics(productBasic, offset, limit);
		int totalProperty = productBasicService.getTotalCount(productBasic);
		model.addAttribute("totalProperty", totalProperty);
		model.addAttribute("jsonString", JSON.toJSONString(productBasics));
		return "common/jsp/json";
	}
	
	/**
	 * 分页查询
	 * 
	 * @param productBasic
	 * @return
	 */
	@RequestMapping(value = "queryProducts.do", method = RequestMethod.POST)
	public String queryProducts(ProductBasic productBasic, int offset,int limit, Model model) {
		
		String categoryId = productBasic.getProduct_category();
		
//		if (!StringUtil.isEmpty(categoryId)) {
			List<ProductBasic> productBasics = productBasicService.getProducts(productBasic, offset, limit);
			int totalProperty = productBasicService.getTotalProduct(productBasic);
			model.addAttribute("totalProperty", totalProperty);
			model.addAttribute("jsonString", JSON.toJSONString(productBasics));
//		}
//		else
//		{
//			Category category = new Category();
//			List<Category> categoryList = categoryService.queryCategorys(category);
//			List<ProductBasic> productBasics = new ArrayList<ProductBasic>();
//			for (Category ct : categoryList) {
//				ProductBasic pb = new ProductBasic();
//				pb.setProduct_category(ct.getCategory_id());
//				List<ProductBasic> list1 = productBasicService.getProducts(pb, 0, 1);
//				if (!CollectionUtils.isEmpty(list1)) {
//					productBasics.add(list1.get(0));
//				}
//			}
//			model.addAttribute("totalProperty", productBasics.size());
//			model.addAttribute("jsonString", JSON.toJSONString(productBasics));
//		}
		
		return "common/jsp/json";
	}

	/**
	 * 查询所有产品
	 * 
	 * @param productBasic
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "queryProductBasicAll.do", method = RequestMethod.POST)
	public String queryProductBasicAll(ProductBasic productBasic, Model model) {
		List<ProductBasic> categories = productBasicService.queryProductBasics(productBasic);
		model.addAttribute("jsonString", JSON.toJSONString(categories));
		return "common/jsp/json";
	}
	/**
	 * 查询推荐产品
	 *
	 * @param productBasic
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "getRecomProducts.do", method = RequestMethod.GET)
	public String getRecomProducts(ProductBasic productBasic, Model model) {
		List<ProductBasic> categories = productBasicService.getRecomProducts(productBasic.getProduct_id());
		model.addAttribute("jsonString", JSON.toJSONString(categories));
//		response.setHeader("Access-Control-Allow-Origin", "*");
		return "common/jsp/json";
	}


	/**
	 * 根据ID查询类别信息
	 * 
	 * @param objectId
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "getProductBasicById.do", method = RequestMethod.GET)
	public String queryProductBasicById(@RequestParam String objectId, Model model) {
		ProductBasic productBasic = productBasicService.getProductBasicById(objectId);

		ProductPropertyValue propertyValue = new ProductPropertyValue();
		propertyValue.setProduct_id(productBasic.getProduct_id());
		List<ProductPropertyValue> propertyValues = propertyValueService.queryPropertyValues(propertyValue);

		productBasic.setPropertyValues(JSON.toJSONString(propertyValues));
		
		ProductPicture productPicture = new ProductPicture();
		productPicture.setProduct_id(objectId);
		List<ProductPicture> productPictures = productPictureService.queryProductPictures(productPicture);
		productBasic.setProductPictures(JSON.toJSONString(productPictures));
		
		return "{SUCCESS:true, root:" + JSON.toJSONString(productBasic) + "}";
	}

	@ResponseBody
	@RequestMapping(value = "deleteProductBasics.do")
	public String deleteProductBasics(String deleteId) {
		if (deleteId != null) {
			String[] ids = deleteId.split("\\$");

			for (String id : ids) {
				if (id != null && !id.equals("")) {
					int result = productBasicService.deleteProductBasicById(id);
					advancePropertyService.deleteAdvancedPropertyByProductId(id);
					propertyValueService.deletePropertyValueByProductId(id);
					productPictureService.deleteProductPictureByProductId(id);
					if (result < 0) {
						return "{success:false,message:'删除失败！'}";
					}
				}
			}
		}

		return "{success:true}";
	}

	public ProductBasicService getProductBasicService() {
		return productBasicService;
	}

	public void setProductBasicService(ProductBasicService productBasicService) {
		this.productBasicService = productBasicService;
	}

	public PropertyValueService getPropertyValueService() {
		return propertyValueService;
	}

	public void setPropertyValueService(PropertyValueService propertyValueService) {
		this.propertyValueService = propertyValueService;
	}

	public AdvancePropertyService getAdvancePropertyService() {
		return advancePropertyService;
	}

	public void setAdvancePropertyService(
			AdvancePropertyService advancePropertyService) {
		this.advancePropertyService = advancePropertyService;
	}

	public ProductPictureService getProductPictureService() {
		return productPictureService;
	}

	public CategoryService getCategoryService() {
		return categoryService;
	}

	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	public void setProductPictureService(ProductPictureService productPictureService) {
		this.productPictureService = productPictureService;
	}

	public PictureService getPictureService() {
		return pictureService;
	}

	public void setPictureService(PictureService pictureService) {
		this.pictureService = pictureService;
	}

}
