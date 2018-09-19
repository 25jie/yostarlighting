var customProListTemp;
var objectId = "";
$(document).ready(function(){
	initProduct();
	
	$("#menu_basic").click(function(){
		showProductBasic();
	});
	
	$("#menu_advance").click(function(){
		showProductAdvance();
		initProductAdvance(objectId);
	});
	
});

function initProductAdvance(productId){
	$.ajax({
		url:MyUtil.getContext()+"getAdvancedProperty.do",
	    type:"post",
	    data:{
	    	"product_id" : productId
	    },
	    async:true,
        success:function(result){
        	var obj=eval("("+result+")");
        	var advancedProperty = obj.root;
        	if (advancedProperty != null && advancedProperty != "undefined" && advancedProperty != "") {
				$("#author").html(advancedProperty.author);
				$("#inputTime").html(advancedProperty.inputime);
				$("#ctt").html(advancedProperty.content_html);
			}
        }
	});
}

function initProduct(){
	objectId=MyUtil.getRequestParams("objectId");
	if(objectId){
	 	$.get(MyUtil.getContext()+"getProductBasicById.do",{ "objectId":objectId},function(result){
	 		var obj=eval("("+result+")");
	 		var productBasic = obj.root;
	 		$("#product_name").html(productBasic.product_name);
	 		$("#product_category").html(productBasic.category_name);
            $("#summary").html(productBasic.summary);
            $("#warranty").html(productBasic.warranty);
            $("#product_title").html(productBasic.product_title);
            $("#product_spec").html(productBasic.product_spec);
            $("#stock").html(productBasic.stock);
	 		queryCustomProperty(productBasic.product_category);
	 		if (productBasic) {
				var propertyValues = JSON.parse(productBasic.propertyValues);
				for ( var i = 0; i < propertyValues.length; i++) {
					var propertyValue = propertyValues[i];
					$("#" + propertyValue.property_id).html(propertyValue.property_value);
					$("#" + propertyValue.property_id).attr("pValueId", propertyValue.id);
				}
				
				var productPics = JSON.parse(productBasic.productPictures);
				for ( var j = 0; j < productPics.length; j++) {
					var pPic = productPics[j];
					var imgHtml = '<div class="imgItem">';
					imgHtml += '	  <input type="hidden" value="'+pPic.picture_id+'"/>';
					imgHtml += '	  <img src="'+pPic.picture_url+'" class="imgContent"/>';
					imgHtml += '</div>';
					$("#productImgDiv").append(imgHtml);
				}
			}
	 	});
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
				tBodyHtml += '<tr>'+
							'	<td class="td_right_product">'+customPro.property_name+'：</td>'+
							'	<td class="td_left_product">'+
							'		<span pValueId="" id="'+customPro.custom_property_id+'"></span>'+
							'	</td></tr>';
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
