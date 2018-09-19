var customProListTemp;
var objectId = "";
var isReload = true;
$(document).ready(function(){
	initCategory();
	//保存
	$("#saveBtn").click(function(){
		if ($("#menu_basic").attr("class").indexOf("menu_on") >= 0) {
			saveProductBasicInfo();
		}else if ($("#menu_advance").attr("class").indexOf("menu_on") >= 0) {
			if (objectId == "" || objectId == null || objectId == "undefined") {
				alert("请您先保存产品基本信息!");
				showProductBasic();
			} else {
				saveProductAdvanceInfo();
			}
		}
	});
	
	$("#menu_basic").click(function(){
		showProductBasic();
	});
	
	$("#menu_advance").click(function(){
		showProductAdvance();
		$("#product_advance").find("input").val("");
		$("#product_advance").find("select").val("");
		editor.html("");
		if(!$("#advancedPropertyId").val()){
            initProductAdvance(objectId);
		}
	});
	
	//删除
	$("#delBtn").del({
		CheckBoxName:"checkImg",
		ACTION:MyUtil.getContext()+"deleteProductPicture.do"
	});
	
	$("#closeBtn").click(function(){
		$("#uploadImgDiv").hide();
		$("#uploadForm").remove();
	});

});

function checkBasicNull(){
	if ($("#product_name").val() == "" || $("#product_name").val() == null || $("#product_name").val() == "undefined") {
		alert("请输入产品名称");
		return true;
	}
	if ($("#product_category").val() == "" || $("#product_category").val() == null || $("#product_category").val() == "undefined") {
		alert("请选择产品类别");
		return true;
	}
	return false;
}

function saveProductBasicInfo(){
	if(!checkBasicNull()){
		var propertyValues = new Array();
		if (typeof(customProListTemp) != 'undefined' && customProListTemp != null) {
			for ( var i = 0; i < customProListTemp.length; i++) {
				var customPro = customProListTemp[i];
				var propertyValue = {
						"id" : $("#" + customPro.custom_property_id).attr("pValueId"),
						"product_id" : objectId,
						"property_id" : customPro.custom_property_id,
						"property_name" : customPro.property_name,
						"property_value" : $("#" + customPro.custom_property_id).val()
				};
				propertyValues.push(propertyValue);
			}
		}
		var pics = new Array();
		$("#productImgDiv").find(".imgItem").each(function(index){
			var showIndex = 0;
			if ($(this).find("input[type='radio']:checked").length > 0) {
				showIndex = 1;
			}
			var picId = $(this).find("input[type='hidden']").val();
			var picUrl = $(this).find("img").attr("src");
			var pic = {
					"picture_id" : picId,
					"pucture_url" : picUrl,
					"picture_showOnIndex" : showIndex
			};
			pics.push(pic);
		});
		$.ajax({
			url:MyUtil.getContext()+"editProductBasic.do",
		    type:"post",
		    data:{
		    	"product_id": objectId,
		    	"product_name":$("#product_name").val(),
		    	"product_category":$("#product_category").val(),
		    	"propertyValues": JSON.stringify(propertyValues),
		    	"pictures":JSON.stringify(pics),
                "summary": $("#summary").val(),
                "warranty": $("#warranty").val(),
                "product_title": $("#product_title").val(),
                "product_spec": $("#product_spec").val(),
                "stock": $("#stock").val()
            },
		    async:true,
            success:function(result){
            	var obj=eval("("+result+")");
            	alert(obj.message);
            	if (obj.SUCCESS) {
            		objectId = obj.objectId;
            		showProductAdvance();
            		$("#product_advance").find("input").val("");
            		$("#product_advance").find("select").val("");
            		editor.html(""),
            		initProductAdvance(objectId);
//            		window.location.href=MyUtil.getContext()+"backgroundPages/jsp/product/productMgr.jsp";
				}
            }
		});
	 }
}

function checkAdvanceNull(){
	
}

function saveProductAdvanceInfo(){
	if(!checkAdvanceNull()){
		$.ajax({
			url:MyUtil.getContext()+"editAdvancedProperty.do",
		    type:"post",
		    data:{
		    	"advanced_property_id" : $("#advancedPropertyId").val(),
		    	"product_id" : objectId,
		    	"advanced_property_name" : "",
		    	"content_html" : editor.html(),
		    	"content_text" : editor.text(),
		    	"inputime" : $("#inputTime").val(),
		    	"author" : $("#author").val()
            },
		    async:true,
            success:function(result){
            	var obj=eval("("+result+")");
            	alert(obj.message);
            	if (obj.SUCCESS) {
            		window.location.href=MyUtil.getContext()+"backgroundPages/jsp/product/productMgr.jsp";
				}
            }
		});
	 }
}

function initProductAdvance(productId){
	if(!productId){
		return;
	}
    $("#inputTime").val(new Date().getTime());
    $("#saveBtn").attr("disabled", "disabled").addClass("btn_disabled");
	$.ajax({
		url:MyUtil.getContext()+"getAdvancedProperty.do",
	    type:"post",
	    data:{
	    	"product_id" : productId
	    },
	    async:true,
        success:function(result){
            $("#saveBtn").removeAttr("disabled").removeClass("btn_disabled");
        	var obj=eval("("+result+")");
        	var advancedProperty = obj.root;
        	if (advancedProperty != null && advancedProperty != "undefined" && advancedProperty != "") {
				$("#author").val(advancedProperty.author);
				$("#inputTime").val(advancedProperty.inputime);
				editor.html(advancedProperty.content_html);
				$("#advancedPropertyId").val(advancedProperty.advanced_property_id);
			} else {
				$("#advancedPropertyId").val("");
			}
        }
	});
}

function initCategory(){
	$("#saveBtn").attr("disabled", "disabled").addClass("btn_disabled");;
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
				if (i == 0) {
					queryCustomProperty(category.category_id);
				}
			}
        	$("#product_category").html(optionHtml);
        	$("#product_category").unbind().bind("change", function(){
        		queryCustomProperty($(this).val());
        	});
        	initProduct();
        }
	});
}

function initProduct(){
	objectId=MyUtil.getRequestParams("objectId");
	if(objectId){
	 	$.get(MyUtil.getContext()+"getProductBasicById.do",{ "objectId":objectId},function(result){
            $("#saveBtn").removeAttr("disabled").removeClass("btn_disabled");
	 		var obj=eval("("+result+")");
	 		var productBasic = obj.root;
	 		$("#product_name").val(productBasic.product_name);
	 		$("#product_category").val(productBasic.product_category);
            $("#summary").val(productBasic.summary);
            $("#warranty").val(productBasic.warranty);
            $("#product_title").val(productBasic.product_title);
            $("#product_spec").val(productBasic.product_spec);
            $("#stock").val(productBasic.stock);
	 		queryCustomProperty(productBasic.product_category);
	 		if (productBasic) {
				var propertyValues = JSON.parse(productBasic.propertyValues);
				for ( var i = 0; i < propertyValues.length; i++) {
					var propertyValue = propertyValues[i];
					$("#" + propertyValue.property_id).val(propertyValue.property_value);
					$("#" + propertyValue.property_id).attr("pValueId", propertyValue.id);
				}
				var productPics = JSON.parse(productBasic.productPictures);
				for ( var j = 0; j < productPics.length; j++) {
					var showHtml = '';
					var pPic = productPics[j];
					if (pPic.show_index == 1) {
						showHtml = '	  <input id="'+pPic.picture_id + i +'" type="radio" name="imgShowIndex" checked/>默认显示图片';
					} else {
						showHtml = '	  <input id="'+pPic.picture_id + i +'" type="radio" name="imgShowIndex"/>默认显示图片';
					}
					var imgHtml = '<div class="imgItem">';
					imgHtml += '	  <input type="checkbox" id="'+pPic.picture_id+'" class="img_box" name="checkImg" value="'+pPic.picture_id+"&"+pPic.product_id+'"/>';
					imgHtml += '	  <input type="hidden" value="'+pPic.picture_id+'"/>';
					imgHtml += '	  <label for="'+pPic.picture_id+'">';
					imgHtml += '	  <img src="'+pPic.picture_url+'" class="imgContent"/></label>';
					imgHtml += '	  <label for="'+pPic.picture_id + i +'">';
					imgHtml += showHtml + '</label>';
					imgHtml += '</div>';
					$("#productImgDiv").append(imgHtml);
				}
			}
	 	});
	} else {
        $("#saveBtn").removeAttr("disabled").removeClass("btn_disabled");;
	}
}

function queryCustomProperty(proCategoryId){
	$.ajax({
		url:MyUtil.getContext()+"getCustomPropertys.do",
	    type:"post",
	    data:{
	    	"product_category" : proCategoryId,
	    	"property_use" : 1
	    },
	    async:false,
        success:function(result){
        	var obj=eval("("+result+")");
        	var tBodyHtml = "";
        	var customProList = obj.root;
        	customProListTemp = customProList;
        	for ( var i = 0; i < customProList.length; i++) {
				var customPro = customProList[i];
				if (i%2 == 0) {
					tBodyHtml += '<tr>'+
								'	<td class="td_right">'+customPro.property_name+'：</td>'+
								'	<td class="td_left">'+
								'		<input type="text" pValueId="" id="'+customPro.custom_property_id+'" class="input-text lh30 inputWidth">'+
								'	</td>';
				} else {
					tBodyHtml += '	<td class="td_right">'+customPro.property_name+'：</td>'+
								'	<td class="td_left">'+
								'		<input type="text" pValueId="" id="'+customPro.custom_property_id+'" class="input-text lh30 inputWidth">'+
								'	</td></tr>';
				}
			}
        	$("#customPropertyTbody").html(tBodyHtml);
        }
	});
}

function showProductBasic(){
	$("#product_advance").hide();
	$("#product_basic").show();
	$("#menu_advance").removeClass("menu_on").addClass("menu_off");
	$("#menu_basic").removeClass("menu_off").addClass("menu_on");
}

function showProductAdvance(){
	$("#product_advance").show();
	$("#product_basic").hide();
	$("#menu_basic").removeClass("menu_on").addClass("menu_off");
	$("#menu_advance").removeClass("menu_off").addClass("menu_on");
}

var picIndex = 1;
function uploadImgSuccess(result){
	var newPictures = JSON.parse(result);
	for ( var i = 0; i < newPictures.length; i++) {
		var pic = newPictures[i];
		var showHtml = '';
		if ($("#productImgDiv").find(".imgItem").length == 0 && i == 0) {
			showHtml = '	  <input id="'+pic.picture_id + i +'" type="radio" name="imgShowIndex" checked/>默认显示图片';
		} else {
			showHtml = '	  <input id="'+pic.picture_id + i +'" type="radio" name="imgShowIndex"/>默认显示图片';
		}
		var imgHtml = '<div class="imgItem">';
		imgHtml += '	  <input type="checkbox" id="'+pic.picture_id+'" class="img_box" name="checkImg" value="'+pic.picture_id+"&"+pic.product_id+'"/>';
		imgHtml += '	  <input type="hidden" value="'+pic.picture_id+'"/>';
		imgHtml += '	  <label for="'+pic.picture_id+'">';
		imgHtml += '	  <img src="'+pic.pucture_url+'" class="imgContent"/></label>';
		imgHtml += '	  <label for="'+pic.picture_id + i +'">';
		imgHtml += showHtml + "</label>";
		imgHtml += '</div>';
		$("#productImgDiv").append(imgHtml);
	}
	$("#uploadImgDiv").hide();
	$("#uploadForm").remove();
}

function removeImgHtml(){
	 $("input[name='checkImg']:checked").each(function () {
         $(this).parent().remove();
	 });
}
