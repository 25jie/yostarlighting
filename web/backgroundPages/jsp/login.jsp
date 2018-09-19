<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
 <%
    String path = request.getContextPath();
    String serverIp =  request.getServerName();
    String basePath = request.getScheme() + "://"+ serverIp + ":" + request.getServerPort()+ path + "/";
  %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
 <base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
 <link rel="stylesheet" href="backgroundPages/css/login.css">
 <script type="text/javascript" src="common/js/jquery.min.js"></script>
 <script type="text/javascript">
    $(function(){
    	$("#sub_btn").click(function(){
    		var name=$("#uname").val();
    		var pwd=$("#upwd").val();
    		var random=$("#random").val();
    		if(name==""){
    			 alert("请输入登录名称");
    			return;
    		}
    		if(pwd==""){
    			alert("请输入密码");
    			return;
    		}
    		if(random==""){
    			alert("请输入验证码");
    			return;
    		}
    		$("#subBtn").click();
    	});
    });
 </script>
	<title>后台登陆</title>

</head>
<body>
	<div id="login_top">
		<div id="welcome">
			欢迎使用LED管理系统
		</div>
		<div id="back">
			<a href="#">返回首页</a>&nbsp;&nbsp; | &nbsp;&nbsp;
			<a href="#">帮助</a>
		</div>
	</div>
	<div id="login_center">
		<div id="login_area">
			<div id="login_form">
				<form action="login.do" method="post" name="fm" id="fm">
					<div id="login_tip">
						用户登录&nbsp;&nbsp;<span id="msg" style="color:red">${errMsg}</span>
					</div>
					<div><input type="text" class="username" id="uname" name="username"></div>
					<div><input type="password" class="pwd" id="upwd" name="userpwd"></div>
					<div id="btn_area">
						<input type="button" name="submit" id="sub_btn" value="登&nbsp;&nbsp;录">&nbsp;&nbsp;
						<input type="text" class="verify" name="rand" id="random">
						<input type="submit" id="subBtn" style="display:none">
						<img src="Rondom.do" alt="" width="80" height="40">
					</div>
				</form>
			</div>
		</div>
	</div>
	<div id="login_bottom">
		版权所有
	</div>
</body>
</html>