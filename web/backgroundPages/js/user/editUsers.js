  $(document).ready(function(){
	    /*
	   * 如果存在id就初始化用户信息
	   */
	 	var objectId=MyUtil.getRequestParams("objectId");
	 	if(objectId){
	    $("#cmtBtn").hide();
	 	$.get(MyUtil.getContext()+"getUserById.do",{ "objectId":objectId},function(result){
	 		var obj=eval("("+result+")");
			$("#ObjectId").val(obj.userid);
	   	    $("#username").val(obj.username);
	   	    $("#realname").val(obj.realname);
		    $("#userpwd").val(obj.userpwd);
		    $("#pwd").val(obj.userpwd);
		   	$("#mobilphone").val(obj.mobilphone);
		   	$("#officephone").val(obj.officephone);
		   	$("#email").val(obj.email);
		   	$("input[name='sex' value='"+obj.sex+"']").attr("checked",true);
		   	$("#qq").val(obj.qq);
		   	$("#address").val(obj.address);
		   	$("#headimg").attr("src",MyUtil.getContext()+obj.userpic);
		   	$("#headedit").show();
	 	});
	 	}else{
	 		$("#saveBtn").hide();
	 	}
	 	
	   //注册用户
	   $("#cmtBtn").click(function(){
		  if(!checkNull()){
			  $.ajax({
					url:MyUtil.getContext()+"regUser.do",
				    type:"post",
				    data:{
				    	"userid":$("#ObjectId").val(),
				    	"username":$("#username").val(),
				    	"realname":$("#realname").val(),
				    	"userpwd":$("#userpwd").val(),
				    	"mobilphone":$("#mobilphone").val(),
				    	"officephone":$("#officephone").val(),
				    	"email":$("#email").val(),
				    	"sex":$("input[name='sex']:checked").val(),
				    	"qq":$("#qq").val(),
				    	"address":$("#address").val()
		            },
				    async:true,
		            success:function(result){
		            	var rs=eval("("+result+")");
		            	alert(rs.message);
		            	if(rs.SUCCESS){
		            		var _id=rs.objectId;
		            		window.location.href=MyUtil.getContext()+"backgroundPages/jsp/user/editUsers.jsp?objectId="+_id;
		            	}
		           }
				});
		  }
	   });
	   
	   //保存用户
	   $("#saveBtn").click(function(){
			  if(!checkNull()){
				  $.ajax({
						url:MyUtil.getContext()+"editUser.do",
					    type:"post",
					    data:{
					    	"userid":$("#ObjectId").val(),
					    	"username":$("#username").val(),
					    	"realname":$("#realname").val(),
					    	"userpwd":$("#userpwd").val(),
					    	"mobilphone":$("#mobilphone").val(),
					    	"officephone":$("#officephone").val(),
					    	"email":$("#email").val(),
					    	"sex":$("input[name='sex']:checked").val(),
					    	"qq":$("#qq").val(),
					    	"address":$("#address").val()
			            },
					    async:true,
			            success:function(result){
			            	var rs=eval("("+result+")");
			            	alert(rs.message);
			            	if(rs.SUCCESS){
			            		var _id=rs.objectId;
			            		window.location.href=MyUtil.getContext()+"backgroundPages/jsp/user/editUsers.jsp?objectId="+_id;
			            	}
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
   });
   function checkNull(){
	   if($("#username").val()==""){
		   $("#usernameMsg").html("用户名不能为空");
		   return true;
	   }
	   if($("#userpwd").val()==""){
		   $("#userPwdMsg").html("密码不能为空");
		   return true;
	   }
	   if($("#userpwd").val()!=$("#pwd").val()){
		   $("#pwdMsg").html("输入密码不一致");
		   return true;
	   }
	   if($("#email").val()==""){
		   $("#emailMsg").html("邮件地址不能为空");
		   return true;
	   }
	   return false;
   }
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
				url:MyUtil.getContext()+'uploadHeadPicture.action',
				secureuri:false,
				fileElementId:'upload',
				dataType: 'json',
				data:{"picture.picture_category_id":$("#ObjectId").val()}, 
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
					$("#headimg").attr("src",MyUtil.getContext()+data.imgURL);
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
	