$(document).ready(function(){
	var objectId=MyUtil.getRequestParams("objectId");
	if(objectId){
	 	$.get(MyUtil.getContext()+"getCategroyById.do",{ "objectId":objectId},function(result){
	 		var obj=eval("("+result+")");
	 		if (obj.root != null) {
	 			$("#category_id").val(obj.root.category_id);
				$("#category_name").val(obj.root.category_name);
		   	    $("#category_remark").val(obj.root.category_remark);
			} else {
				$("#category_id").val("");
				$("#category_name").val("");
		   	    $("#category_remark").val("");
			}
	 	});
	}
	$("#save_category").click(function(){
		$("#categoryName_errorMsg").hide();
		if(!checkNull()){
			$.ajax({
				url:MyUtil.getContext()+"editCategory.do",
			    type:"post",
			    data:{
			    	"category_id":$("#category_id").val(),
			    	"category_name":$("#category_name").val(),
			    	"category_remark":$("#category_remark").val()
	            },
			    async:true,
	            success:function(result){
	            	var obj=eval("("+result+")");
	            	alert(obj.message);
	            	if (obj.SUCCESS) {
	            		window.location.href=MyUtil.getContext()+"backgroundPages/jsp/product/categoryMgr.jsp";
					}
	            }
			});
		  }
	});
	//重置
	$("#reset_category").click(function(){
		$("#category_name").val("");
		$("#category_remark").val("");
	});
});

function checkNull(){
	if($("#category_name").val() == ""){
		$("#categoryName_errorMsg").show();
		return true;
	}
	return false;
}
