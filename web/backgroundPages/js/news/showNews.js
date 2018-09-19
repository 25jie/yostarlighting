 $(document).ready(function(){
	 
	 var objectId=MyUtil.getRequestParams("objectId");
		if(objectId){
	 	$.get("getNewsById.do",{"objectId":objectId},function(result){
	 		doResult(result);
	 	});
	 	}

	//新增
 	$("#addBtn").click(function(){
 		window.location.href=MyUtil.getContext()+"backgroundPages/jsp/news/editNews.jsp";
 	});
 	//编辑
 	$("#editBtn").click(function(){
 		var _ckid=$("#ObjectId").val();
 		window.location.href=MyUtil.getContext()+"backgroundPages/jsp/news/editNews.jsp?objectId="+_ckid;
 	});
 	
 	 function doResult(result){
		   var obj=eval("("+result+")");
	 	   $("#ObjectId").val(obj.news_id);
	 	   $("#title").html(obj.news_title);
	 	  // $("#cate option[value='"+obj.news_type+"']").attr("selected", true);
	 	  // $("input[type='radio'][name='status'][value='"+obj.news_show+"']").attr("checked",true);
	 	   
	 	   $("#newsHtml").html(obj.news_html);
	 	   $("#pubtime").html(obj.news_pubtime);
	   }
 });