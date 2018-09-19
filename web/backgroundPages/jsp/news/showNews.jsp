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
 <link rel="stylesheet" href="backgroundPages/css/main.css">
 <script type="text/javascript" src="common/js/jquery.min.js"></script>
   <script type="text/javascript" src="backgroundPages/js/colResizable-1.3.min.js"></script>
    <script type="text/javascript" src="common/js/util.js"></script>
     
  <script type="text/javascript" src="backgroundPages/js/news/showNews.js"></script>
<title>新闻</title>
</head>
<body>
      <div class="container">
            <div id="button" class="mt10">
		       <input type="button" name="button" id="addBtn" class="btn btn82 btn_add" value="新增">  
		       <input type="button" name="button" id="editBtn" class="btn btn82 btn_config" value="编辑">    
           </div>
        <div id="abouteus" class="mt10">
        <div class="box">
          <div class="box_border">
            <div class="box_top">标题：<b class="pl15" id="title">新闻</b></div>
            <div class="box_center">
              <div style="MARGIN-RIGHT:auto; MARGIN-LEFT:auto;">
                  <table width="80%" align="center">
                    <tr>
                       <td>发表时间：<span id="pubtime"></span></td><td></td>
                    </tr>
                    <tr>
                      <td colspan=2>
                         <div id="newsHtml">
                         </div>
                      </td>
                    </tr>
                  </table>
                </div>  
            </div>
          </div>
        </div>    
      </div>
   </div> 
    <div style="display:none">
      <input type="text" id="ObjectId" value="-1">
    </div>  
</body>
<script type="text/javascript">

</script>
</html>