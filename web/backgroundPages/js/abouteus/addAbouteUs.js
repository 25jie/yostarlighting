 $(document).ready(function(){
    //初始化类别
	 $.post("getMenu.do",{"navigation_name":"关于我们","navigation_type":"QT"},function(result){
		 var _obj=eval("("+result+")");
		 if(_obj.length){
			 var _subMenus=_obj[0].subMenus;
			 if(_subMenus.length){
				 var _option="";
				 for(var i=0;i<_subMenus.length;i++){
					 var _subMenu=_subMenus[i];
					 _option+="<option value=\""+_subMenu.navigation_id+"\">"+_subMenu.navigation_name+"</option>";
				 }
				 $("#category").html(_option);
			 }
			
		 }
		 init();
	 });
	 function init(){
       var objectId=MyUtil.getRequestParams("objectId");
       if(objectId=="null"||objectId==null){
    	   
       }else{
       $.ajax({
    	   url:MyUtil.getContext()+"getArticleById.do",
		    type:"post",
		    data:{
		    	"objectId":objectId
		    },
           async:true,
          success:function(result){
        	  var ob=eval("("+result+")");
        	  $("#ObjectId").val(ob.article_id);
        	  $("#title").val(ob.article_title);
        	  editor.text(ob.article_content);
        	  editor.html(ob.article_html);
        	  $("#category").val(ob.article_category);
        	  $("input[name='status'][value='"+ob.needshow+"']").attr("checked",true);
          }
       });
       }
	 }
	 
	 

    	//保存
 	   $("#cmtBtn").click(function(){
 		  if(!checkNull()){
 			  $.ajax({
 					url:MyUtil.getContext()+"saveOrUpdateArticle.do",
 				    type:"post",
 				    data:{
 				    	"article_id":$("#ObjectId").val(),
 				    	"article_title":$("#title").val(),
 				    	"article_content":editor.text(),
 				    	"article_html":editor.html(),
 				    	"article_category":$("#category").val(),
 				    	"needshow":$("input[name='status']:checked").val(),
 				    	"article_categoryDM":"ABOUTEUS"
 		            },
 				    async:true,
 		            success:function(result){
 		            	alert("保存成功");
 		            	var obj=eval("("+result+")");
 		            	window.location.href=MyUtil.getContext()+"backgroundPages/jsp/abouteus/manageAboutus.jsp?objectId="+obj.article_id;
 		           }
 				});
 		  }
 	   });
    	
    	$("#ylBtn").click(function(){
    		$("#editContent").hide();
    		$("#cthmtl").html(editor.html());
    		$("#glanceContent").show();
    	});
       $("#editBtn").click(function(){
    	   $("#editContent").show();
    	   $("#glanceContent").hide();
       }); 	
       $("#rcmtBtn").click(function(){
    	   $("#ylBtn").click();
       });
       $("#up").click(function(){
    	   ajaxFileUpload();
       });
       function checkNull(){
    	   if($("#title").val()==""){
    		   alert("请填写标签");
    		   return true;
    	   }
    	   if(editor.text()==""){
    		   alert("内容不能为空");
    		   return true;
    	   }
    	   return false;
       }
       
    });
 
