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
<link rel="stylesheet" href="backgroundPages/css/common.css">
  <link rel="stylesheet" href="backgroundPages/css/style.css">
  <script type="text/javascript" src="common/js/jquery.min.js"></script>
  <script type="text/javascript" src="backgroundPages/js/jquery.SuperSlide.js"></script>
  <script type="text/javascript" src="common/js/util.js"></script>
  <script type="text/javascript" src="backgroundPages/js/initMenu.js" ></script>
   <script type="text/javascript" src="backgroundPages/js/msg.js" ></script>
  <script type="text/javascript">
  $(document).ready(function(){
	  $('.info_center').mouseenter(function(){
		  $('#messageBox').slideDown();
	  });
	  $('#messageBox').mouseleave(function(){
		  $('#messageBox').slideUp();
	  });
  });
  </script>
  <style type="text/css">
  	#menu_div li{
  		cursor: pointer;
  	}
  	#messageBox{
  	 background: #fff;
	 width: 220px;
	 position: fixed;
	 top: 72px;
	 right: 28px;
	 z-index: 99;
	 border:1px dashed #CCC;
	 min-height:200px;
	 padding:10px;
	 display:none;
	 opacity: 0.8;
	filter:alpha(opacity=80); 
	border-radius: 3px;
	-webkit-border-radius: 3px;
    -moz-border-radius: 3px;
	}
	.num{
	background: blue;
	display: inline-block;
	width: 20px;
	height: 20px;
	line-height: 20px;
	text-align: center;
	margin-left: 4px;
	opacity: 0.8;
	filter:alpha(opacity=80); 
	border-radius: 6px;
	-webkit-border-radius: 6px;
    -moz-border-radius: 6px;
    color: #fff;
	}
  	#messageBox ul li a{
  	  	line-height: 25px;
  	    color:black;
  	    font-size:14px;
  	    font-family: "Microsoft YaHei","微软雅黑","sans-serif" ;
  	}
  	#messageBox ul li span{
  	  	float:right;
  	    color:grey;
  	    font-size:11px;
  	}
  	#messageBox ul li a:hover{
  	  color:red;
  	  text-decoration: underline;
  	}
  </style>
  
  <title>后台首页</title>
</head>
<body>
    <div class="top">
      <div id="top_t">
        <div id="logo" class="fl"></div>
        <div id="photo_info" class="fr">
          <div id="photo" class="fl">
            <a href="#"><img src="${USER.userpic}" alt="" width="60" height="60"></a>
          </div>
          <div id="base_info" class="fr">
            <div class="help_info">
              <a href="javascript:void(0)" id="hp">&nbsp;</a>
              <a href="javascript:void(0)" id="gy">&nbsp;</a>
              <a href="extSystem.do" id="out">&nbsp;</a>
            </div>
            <div class="info_center">
              ${USER.username}
              <span id="nt">通知</span><span><a href="javascript:void(0)" id="notice">0</a></span>
            </div>
            <div id="messageBox">
              
            </div>
          </div>
        </div>
      </div>
      <div id="side_here">
        <div id="side_here_l" class="fl"></div>
        <div id="here_area" class="fl">当前位置：<span id="dqwz">网站配置</span></div>
      </div>
    </div>
    <div class="side" id="menu_div">
        <div class="sideMenu" style="margin:0 auto" id="sideMenu">
            
       </div>
    </div>
    <div class="main">
       <iframe name="right" id="rightMain" src="backgroundPages/jsp/product/productMgr.jsp" frameborder="no" scrolling="auto" width="100%" height="auto" allowtransparency="true"></iframe>
    </div>
    <div class="bottom">
      <div id="bottom_bg">版权</div>
    </div>
    <div class="scroll">
          <a href="javascript:;" class="per" title="使用鼠标滚轴滚动侧栏" onclick="menuScroll(1);"></a>
          <a href="javascript:;" class="next" title="使用鼠标滚轴滚动侧栏" onclick="menuScroll(2);"></a>
    </div>
</body>
</html>