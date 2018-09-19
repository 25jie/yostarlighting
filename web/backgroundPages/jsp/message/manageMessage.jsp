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
   <link rel="stylesheet" href="backgroundPages/css/main.css">
 <script type="text/javascript" src="common/js/jquery.min.js"></script>
   <script type="text/javascript" src="backgroundPages/js/colResizable-1.3.min.js"></script>
    <script type="text/javascript" src="common/js/util.js"></script>
    <script type="text/javascript" src="common/js/jquery.pager.js"></script>
    <script type="text/javascript" src="backgroundPages/js/message/initMessage.js"></script>
   <script type="text/javascript" src="backgroundPages/js/del.js"></script> 
   <script type="text/javascript" src="backgroundPages/js/message/manageMessage.js"></script>
  <style type="text/css">
  .m_td_c{
    text-align:center;
    cursor:pointer;
  }
  .m_td_l{
    text-align:left;
    cursor:pointer;
  }
 
  </style>
   <title>message</title>
 </head>
 <body>
  <div class="container">
     
     

     <div id="button" class="mt10">  
	  <input type="button" name="button" id="delBtn" class="btn btn82 btn_del" value="删除"> 
     </div>
     <div id="table" class="mt10">
        <div class="box span10 oh">
              <table width="100%" border="0" cellpadding="0" cellspacing="0" class="list_table" >
                  <tr>
                   <th width="70">
                     <input type="button" name="button" id="ckAll" class="btn btn82 btn_checked" value="全选">
                     <input type="button" name="button" id="ckNo" class="btn btn82 btn_nochecked" style="display:none" value="取消">
                   </th>
                    <th width="80"></th>
                   <th width="100">发件人</th>
                   <th>主题</th>
                   <th width="100">时间</th>
                  </tr>
                 <tbody id="messageTbody"></tbody>
              </table>
              <div id="pager"></div>
        </div>
     </div>
   </div> 
 </body>

 </html>