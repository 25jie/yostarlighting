$(document).ready(function(){
	var categoryId=MyUtil.getRequestParams("categoryId");
	initCategory(categoryId);
	
	$("#addProperty").click(function(){
		if ($("#product_category").val() == "" || $("#product_category").val() == null) {
			alert("请先选择产品类别");
			return;
		} 
		$("#propertyName").val("");
		$("#isUsed").attr("checked", false);
		$(".custom_property_add_main").show();
		$("#propertyId").val("");
	});
	$("#cancelBtn").click(function(){
		$(".custom_property_add_main").hide();
	});
	$("#saveBtn").click(function(){
		if (checkNull()) {
			save();
		}
	});
});

function checkNull(){
	if ($("#propertyName").val() == "" || $("#propertyName").val() == null || $("#propertyName").val() == "undefined") {
		alert("请输入参数名称！");
		return false;
	}
	return true;
}

function initCategory(categoryId){
	$.ajax({
		url:MyUtil.getContext()+"queryCategoryAll.do",
	    type:"post",
	    data:{},
	    async:true,
        success:function(result){
        	var obj=eval("("+result+")");
        	var optionHtml = "";
        	var categoryList = obj.root;
        	for ( var i = 0; i < categoryList.length; i++) {
				var category = categoryList[i];
				optionHtml += "<option value='"+category.category_id+"'>"+category.category_name+"</option>";
			}
        	$("#product_category").html(optionHtml);
        	if (categoryId != "undefined" && categoryId != null && categoryId != "") {
        		$("#product_category").val(categoryId);
			}
        	queryCustomProperty($("#product_category").val());
        	$("#product_category").unbind().bind("change", function(){
        		queryCustomProperty($(this).val());
        	});
        }
	});
}

function queryCustomProperty(category){
	$.ajax({
		url:MyUtil.getContext()+"getCustomPropertys.do",
	    type:"post",
	    data:{
	    	"product_category" : category
	    },
	    async:true,
        success:function(result){
        	var obj=eval("("+result+")");
        	var trHtml = "";
        	var customPropertyList = obj.root;
        	for ( var i = 0; i < customPropertyList.length; i++) {
				var customProperty = customPropertyList[i];
				var opHtml = "";
				var isUsed = "";
				var sortHtml = "<a href='javascript:void(0)'>[上移]</a> <a href='javascript:void(0)'>[下移]</a>";
				if (i == 0) {
					sortHtml = "<a href='javascript:void(0)'>[下移]</a>";
				} else if (i == customPropertyList.length -1) {
					sortHtml = "<a href='javascript:void(0)'>[上移]</a>";
				}
				
				if (customProperty.property_use == 1) {
					isUsed = "已启用";
					opHtml = "<a href='javascript:void(0)' onclick='initUpdate(this)' pcpId='"+customProperty.custom_property_id+"'>编辑</a> " +
							"<a href='javascript:void(0)' onclick='updateUsed(this, 0)' pcpId='"+customProperty.custom_property_id+"'>禁用</a> " +
							"<a href='javascript:void(0)' onclick='delProperty(this)' pcpId='"+customProperty.custom_property_id+"'>删除</a>";
				} else {
					isUsed = "未启用";
					opHtml = "<a href='javascript:void(0)' onclick='initUpdate(this)' pcpId='"+customProperty.custom_property_id+"'>编辑</a> " +
							"<a href='javascript:void(0)' onclick='updateUsed(this, 1)' pcpId='"+customProperty.custom_property_id+"'>启用</a> " +
							"<a href='javascript:void(0)' onclick='delProperty(this)' pcpId='"+customProperty.custom_property_id+"'>删除</a>";
				}
				
				trHtml += '<tr>'+
						'	<td class="td_left" style="padding-left:20px;">'+customProperty.property_name+'</td>'+
						'	<td class="td_center">'+isUsed+'</td>'+
						'	<td class="td_center">'+sortHtml+'</td>'+
						'	<td class="td_center">'+opHtml+'</td>'+
						'<tr>';	
			}
        	$("#propertyTbody").html(trHtml);
        }
	});
}

function initUpdate(obj){
	$(".custom_property_add_main").show();
	$.get(MyUtil.getContext()+"getCustomPropertyById.do",{ "objectId": $(obj).attr("pcpId")},function(result){
 		var obj=eval("("+result+")");
 		if (obj.root != null) {
 			$("#propertyName").val(obj.root.property_name);
 			if (obj.root.property_use == 1) {
 				$("#isUsed").attr("checked", "checked");
			} else {
				$("#isUsed").attr("checked", false);
			}
 			$("#propertyId").val(obj.root.custom_property_id);
		} else {
			alert("查询失败！");
		}
 	});
}

function updateUsed(obj, param){
	$.ajax({
		url : MyUtil.getContext()+"updateCusProUsed.do",
		type : "post",
		data : {
			"product_category" : $("#product_category").val(),
			"custom_property_id" : $(obj).attr("pcpId"),
	    	"property_use" : param
		},
		async : true,
		success : function(result) {
			var _o = eval("(" + result + ")");
				alert(_o.message);
				window.location.href=MyUtil.getContext()+"backgroundPages/jsp/product/customPropertyMgr.jsp?categoryId=" + $("#product_category").val();
		}
	});
}

function delProperty(obj) {
	if(window.confirm('你确定删除吗？')){
		$.ajax({
			url : MyUtil.getContext()+"deleteCustomPropertys.do",
			type : "post",
			data : {
				"deleteId" : $(obj).attr("pcpId")
			},
			async : true,
			success : function(result) {
				var _o = eval("(" + result + ")");
				if (_o.success) {
					alert("删除成功！");
					window.location.href=MyUtil.getContext()+"backgroundPages/jsp/product/customPropertyMgr.jsp?categoryId=" + $("#product_category").val();
				} else {
					alert(_o.message);
				}
			}
		});
	}
}

function save(){
	var objId = $("#propertyId").val();
	$.ajax({
		url:MyUtil.getContext()+"editCustomProperty.do",
	    type:"post",
	    data:{
	    	"custom_property_id" : objId,
	    	"product_category" : $("#product_category").val(),
	    	"property_name" : $("#propertyName").val(),
	    	"property_use" : $("#isUsed").attr("checked") == "checked" ? 1 : 0
	    },
	    async:true,
        success:function(result){
        	var obj=eval("("+result+")");
        	alert(obj.message);
        	if (obj.SUCCESS) {
        		$(".custom_property_add_main").hide();
        		queryCustomProperty($("#product_category").val());
			}
        }
	});
}