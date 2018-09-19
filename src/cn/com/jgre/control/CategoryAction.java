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
import cn.com.jgre.service.CategoryService;
import cn.com.jgre.util.StringUtil;

import com.alibaba.fastjson.JSON;

@Controller
@RequestMapping("/")
public class CategoryAction {
	
	@Resource
	private CategoryService categoryService;
	
	/**
	 * 新增类别
	 * @param category
	 * @return
	 */
	@ResponseBody
    @RequestMapping(value="editCategory.do",method=RequestMethod.POST)
	public String addCategory(Category category){
		String result = "";
		try {
			
			Category qCategory = new Category();
			qCategory.setCategory_name(category.getCategory_name());
			List<Category> categoryList = categoryService.queryCategorys(qCategory);
			
			if (categoryList != null && categoryList.size() == 0) {
				if (StringUtil.isEmpty(category.getCategory_id())) {
					int addResult = categoryService.addCategory(category);
					if (addResult > 0) {
						result = "{SUCCESS:true,message:'添加成功',objectId:'"+category.getCategory_id()+"'}";
					} else {
						result = "{SUCCESS:false,message:'添加失败'}";
					}
				} else {
					int updateResult = categoryService.updateCategory(category);
					if (updateResult > 0) {
						result = "{SUCCESS:true,message:'修改成功',objectId:'"+category.getCategory_id()+"'}";
					} else {
						result = "{SUCCESS:false,message:'修改失败'}";
					}
				}
			} else {
				result = "{SUCCESS:false,message:'类别名称已经存在，请重新输入'}";
			}
		} catch (Exception e) {
			result = "{SUCCESS:false,message:'数据库异常，添加失败!'}";
		}
		
		return result;
	}
	
	/**
	 * 分页查询
	 * @param category
	 * @return
	 */
	@RequestMapping(value="queryCategorys.do",method=RequestMethod.POST)
	public String queryCategorys(Category category,int offset,int limit,Model model){
		List<Category> categories = categoryService.getCategorys(category, offset, limit);
		int totalProperty=categoryService.getTotalCount(category);
		model.addAttribute("totalProperty", totalProperty);
        model.addAttribute("jsonString", JSON.toJSONString(categories));
        return "common/jsp/json";
	}
	
	/**
	 * 查询所有产品类别信息
	 * @param category
	 * @param model
	 * @return
	 */
	@RequestMapping(value="queryCategoryAll.do",method=RequestMethod.POST)
	public String queryCategoryAll(Category category, Model model){
		List<Category> categories = categoryService.queryCategorys(category);
        model.addAttribute("jsonString", JSON.toJSONString(categories));
        return "common/jsp/json";
	}
	
	/**
	 * 根据ID查询类别信息
	 * @param objectId
	 * @param model
	 * @return
	 */
	@ResponseBody
    @RequestMapping(value="getCategroyById.do",method=RequestMethod.GET)
	public String queryCategoryById(@RequestParam String objectId,Model model){
		Category category=categoryService.getCategoryById(objectId);
        return "{SUCCESS:true, root:"+JSON.toJSONString(category)+"}";
	}
	
	/*
     * 删除用户
     */
    @ResponseBody
    @RequestMapping(value="deleteCategorys.do")
    public String deleteCategorys(String deleteId){
        if(deleteId!=null){
           String[] ids=deleteId.split("\\$");
        
           for(String id:ids){
             if(id!=null&&!id.equals("")){ 
             int result=  categoryService.deleteCategoryById(id);
               if(result<0){
                  return "{success:false,message:'删除失败！'}";
               }
             }
           }
        }
        
       return "{success:true}";
    }

	public CategoryService getCategoryService() {
		return categoryService;
	}

	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}
	
}
