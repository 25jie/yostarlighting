 $(document).ready(function(){
	 
	 
	    //初始化类别
	 $.post("getMenu.do",{"navigation_name":"新闻中心","navigation_type":"QT"},function(result){
		 var _obj=eval("("+result+")");
		 if(_obj.length){
			 var _subMenus=_obj[0].subMenus;
			 if(_subMenus.length){
				 var _option="";
				 for(var i=0;i<_subMenus.length;i++){
					 var _subMenu=_subMenus[i];
					 _option+="<option value=\""+_subMenu.navigation_id+"\">"+_subMenu.navigation_name+"</option>";
				 }
				 $("#cate").html(_option);
				//初始化编辑界面
         		
			 }
		 }else{
			 $("#cate").html('新闻');
		 }
		 var objectId=MyUtil.getRequestParams("objectId");
  		if(objectId){
  	 	$.get("getNewsById.do",{"objectId":objectId},function(result){
  	 		doResult(result);
  	 	});
  	 	}
	 });
		/*initCateSelect();
	 	//初始化类别
	    function initCateSelect(){
	 	   $.ajax({
	     	   url:MyUtil.getContext()+"getNewsCategorys.do",
	 		    type:"post",
	 		    data:{offset:0,limit:20},
	            async:true,
	           success:function(result){
	         	  var ob=eval("("+result+")");
		            	if(ob.length){
		            		var _s="";
		            		for(var i=0;i<ob.length;i++){
		            			_b=ob[i];	
		            			_s=_s+"<option value='"+_b.category_id+"'>"+_b.category_name+"</option>";
		            		}
		            		$("#cate").empty().html(_s);
		            		//初始化编辑界面
		            		var objectId=MyUtil.getRequestParams("objectId");
		            		if(objectId){
		            	 	$.get("getNewsById.do",{"objectId":objectId},function(result){
		            	 		doResult(result);
		            	 	});
		            	 	}
		            	}   
	         	 }
	           });
	 	   
	    }*/
	 	
	 
	   //保存
	   $("#cmtBtn").click(function(){
		  if(!checkNull()){
			  $.ajax({
					url:MyUtil.getContext()+"saveOrUpdateNews.do",
				    type:"post",
				    data:{
				    	"news_id":$("#ObjectId").val(),
				    	"news_title":$("#title").val(),
				    	"news_content":editor.text(),
				    	"news_html":editor.html(),
				    	"news_type":$("#cate").val(),
				    	"news_show":$("input[name='status']:checked").val()
		            },
				    async:true,
		            success:function(result){
		            	alert("保存成功");
		            	var obj=eval("("+result+")");
		            	window.location.href=MyUtil.getContext()+"backgroundPages/jsp/news/showNews.jsp?objectId="+obj.news_id;
		           }
				});
		  }
	   });
	   //重置
	   $("#resBtn").click(function(){
		   $("#title").val("");
		   clearContent();
	   });
	   $("#up").click(function(){
    	   ajaxFileUpload();
       });
	   $("#cate").change(function(){
    	   initPicture($(this).val());
       });
   });
 
	function doResult(result){
 		var obj=eval("("+result+")");
 	   $("#ObjectId").val(obj.news_id);
 	   $("#title").val(obj.news_title);
 	   $("#cate option[value='"+obj.news_type+"']").attr("selected", true);
 	   $("input[type='radio'][name='status'][value='"+obj.news_show+"']").attr("checked",true);
 	   editor.html(obj.news_html);
 	}

   function checkNull(){
	   if($("#title").val()==""){
		   alert("请填写主题");
		   return true;
	   }
	   if(editor.text()==""){
		   alert("内容不能为空");
		   return true;
	   }
	   return false;
   }
  