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
<title>显示信息</title>
<script type="text/javascript" src="common/js/jquery.min.js"></script>
 <script type="text/javascript" src="common/js/util.js"></script>
 <style type="text/css">
 .content{
    width:800px;
    margin:auto;
 }
	ul{
	list-style:none;
	}
	ul li{
	font-size:12px;
	float:left; margin-right:0px; display:inline; width:250px;
	}
	
	 .feedback{line-height:35px;width:95%; margin-left:20px;border-left:1px dashed #CCC;border-top:1px dashed #CCC;}
.feedback td{border-bottom:1px dashed #CCC;padding-left:10px;border-right:1px dashed #CCC;}
.feedback .tdleft{text-align:right;background-color:#F5F5F5;}
.feedback .order-title{background-color:#F5F5F5;font-weight:bold;color:#6AB22A}
   </style> 
</head>
<body>
<div class="content">
    <table  class="feedback">
          <tr>
            <td height="25"><a href="backgroundPages/jsp/message/manageMessage.jsp">返回</a></td>
          </tr>
          <tr>
            <td height="50" class="order-title"><span id="title"></span><br><span id="time" style="font-size:12px;color:blue">时间：</span></td>
          </tr>
           <tr>
            <td height="100">
                  <ul>
                     <li>姓名：<span id="person"></span></li>
                     <li>公司：<span id="company"></span></li>
                     <li>电话：<span id="tel"></span></li>
                     <li>邮件：<span id="email"></span></li>
                     <li>地址：<span id="address"></span></li>
                  </ul>
            </td>
          </tr>
           <tr>
            <td height="300" class="order-title"><span id="content"></span></td>
          </tr>
    </table>
</div>
<script type="text/javascript">
   var message_id=MyUtil.getRequestParams("message_id");
   if(message_id){
      $.post('getMsgByid.do',{'messageid':message_id},function(result){
    	   var _obj=eval('('+result+')');
    	   $('#title').html(_obj.message_title);
    	   $('#person').html(_obj.message_person);
    	   $('#company').html(_obj.message_person_company);
    	   $('#tel').html(_obj.message_person_tel);
    	   $('#email').html(_obj.message_person_email);
    	   $('#address').html(_obj.message_person_address);
    	   $('#content').html(_obj.message_content);
    	   $('#time').append(_obj.message_time);
      });
   }
</script>
</body>
</html>