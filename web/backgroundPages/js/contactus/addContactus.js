 $(document).ready(function(){
    //初始化类别
	 $.post("getMenu.do",{"navigation_name":"联系我们","navigation_type":"QT"},function(result){
		 var _obj=eval("("+result+")");
		 if(_obj.length){
			 var _id=_obj[0].navigation_id;
			$("#category").val(_id);
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
 				    	"needshow":$("input[name='status']:checked").val(),
 				    	"article_categoryDM":"CONTACTUS",
 				    	"article_category":$("#category").val()
 		            },
 				    async:true,
 		            success:function(result){
 		            	alert("保存成功");
 		            	//var obj=eval("("+result+")");
 		            	window.location.href=MyUtil.getContext()+"backgroundPages/jsp/contactus/manageContactus.jsp";
 		           }
 				});
 		  }
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
 
 function ajaxFileUpload()
	{
/*		$("#loading")
		.ajaxStart(function(){
			$(this).show();
		})
		.ajaxComplete(function(){
			$(this).hide();
		});*/

		$.ajaxFileUpload
		({
				url:MyUtil.getContext()+'uploadPicture.do',
				secureuri:false,
				fileElementId:'upload',
				dataType: 'json',
				data:{"picture_category_id":$("#category").val()}, 
				beforeSend:function()
				{
					$("#loading").show();
					$("#headimg").hide();
				},
				complete:function()
				{    $("#headimg").show();
					$("#loading").hide();
				},				
				success: function (data, status)
				{
					alert(data.msg);
					var imgSrc=MyUtil.getContext()+data.imgURL;
					var _cont="<div class='imgf'>"+
                                   "<img alt='' src='"+data.imgURL+"'>"+
                                   "<a onclick='copy_clip(\""+imgSrc+"\");alert(\"复制成功！\");'>复制地址</a>/<a onclick='deletePicture(\""+data.pid+"\")'>删除</a>"+
                                   "</div>";
					$("#pictureList").prepend(_cont);
					//$("#headimg").attr("src",MyUtil.getContext()+data.imgURL);
					if(typeof(data.error) != 'undefined')
					{
						alert(data.msg); 
					}
				},
				error: function (data, status, e)
				{
					alert(e);
				}
			}
		);
		
		return false;

	}
 
 function deletePicture(pid){
	 if(window.confirm('你确定删除吗？')){ 
	 $.ajax({
	  	   url:MyUtil.getContext()+"deletePicture.do",
			    type:"post",
			    data:{
			    	 "picture.picture_id":pid
			    },
	         async:true,
	        success:function(result){
	        	 var ob=eval("("+result+")");
                    if(ob.success){
                    	alert("删除成功！");
                    	initPicture($("#category").val());
                    }else{
                    	alert(ob.message);
                    }
	        	}
	        });
	 }
 }
 
//加载图片
 function initPicture(id){
	   
	   $.ajax({
  	   url:MyUtil.getContext()+"getPictures.do",
		    type:"post",
		    data:{
		    	 "picture_category_id":id,
		    	 offset:0,
	             limit:100
		    },
         async:true,
        success:function(result){
      	  var ob=eval("("+result+")");
          	if(ob.length){
          		var _s="";
          		for(var i=0;i<ob.length;i++){
          			_b=ob[i];	
          			var imgSrc=MyUtil.getContext()+_b.pucture_url;
          			_s=_s+"<div class='imgf'>"+
                      "<img alt='' src='"+_b.pucture_url+"'>"+
                      "<a onclick='copy_clip(\""+imgSrc+"\");alert(\"复制成功！\");'>复制地址</a>/<a onclick='deletePicture(\""+_b.picture_id+"\")'>删除</a>"+
                      "</div>";
          		}
          		$("#pictureList").empty().html(_s);
          	
          	}else{
          		$("#pictureList").empty();
          	}   
      	 }
        });
 }

 //js复制
 function copy_clip(txt) {
     if (window.clipboardData) {
             window.clipboardData.clearData();
             window.clipboardData.setData("Text", txt);
     } else if (navigator.userAgent.indexOf("Opera") != -1) {
             window.location = txt;
     } else if (window.netscape) {
             try {
                     netscape.security.PrivilegeManager.enablePrivilege("UniversalXPConnect");
             } catch (e) {
                     alert("您的firefox安全限制限制您进行剪贴板操作，请在新窗口的地址栏里输入'about:config'然后找到'signed.applets.codebase_principal_support'设置为true'");
                     return false;
             }
             var clip = Components.classes["@mozilla.org/widget/clipboard;1"].createInstance(Components.interfaces.nsIClipboard);
             if (!clip)
                     return;
             var trans = Components.classes["@mozilla.org/widget/transferable;1"].createInstance(Components.interfaces.nsITransferable);
             if (!trans)
                     return;
             trans.addDataFlavor('text/unicode');
             var str = new Object();
             var len = new Object();
             var str = Components.classes["@mozilla.org/supports-string;1"].createInstance(Components.interfaces.nsISupportsString);
             var copytext = txt;
             str.data = copytext;
             trans.setTransferData("text/unicode", str, copytext.length * 2);
             var clipid = Components.interfaces.nsIClipboard;
             if (!clip)
                     return false;
             clip.setData(trans, null, clipid.kGlobalClipboard);
     }
	}
 
    