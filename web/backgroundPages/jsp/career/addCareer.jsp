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
<title>添加招聘信息</title>
<link rel="stylesheet" href="backgroundPages/css/common.css">
   <link rel="stylesheet" href="backgroundPages/css/main.css">
 <script type="text/javascript" src="common/js/jquery.min.js"></script>
   <script type="text/javascript" src="backgroundPages/js/colResizable-1.3.min.js"></script>
    <script type="text/javascript" src="common/js/util.js"></script>
 <!-- 编辑器 -->  
  <link rel="stylesheet" type="text/css" href="backgroundPages/css/editor/default.css">
  <script type="text/javascript" src="backgroundPages/js/kindeditor-min.js"></script>
  <script type="text/javascript" src="backgroundPages/js/zh_CN.js"></script>
   <script type="text/javascript">
   //初始化editor
   var editor;
 	KindEditor.ready(function(K) {
 		editor = K.create('textarea[name="ctt"]', {
 			resizeType : 1,
 			allowPreviewEmoticons : false,
 			allowImageUpload : false,
 			items : [
 				'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor', 'bold', 'italic', 'underline',
 				'removeformat', '|', 'justifyleft', 'justifycenter', 'justifyright', 'insertorderedlist',
 				'insertunorderedlist', '|', 'emoticons', 'image', 'link']
 		});
 	});
 	//清空editor内容
 	function clearContent(){
 	      if(editor!=null){
 	         editor.text('');
 	      }else{
 	      alert('初始化编辑器失败');
 	      }
 	}
   </script>
</head>
<body>
<div class="container">
   <div id="forms" class="mt10">
       <div class="box_border">
            <div class="box_top"><b class="pl15">添加联系</b></div>
            <div class="box_center" id="editContent">
                    <table class="form_table pt15 pb15" width="90%" border="0" cellpadding="0" cellspacing="0" align="center">
                          <tr>
                             <td>
                             </td>
                             <td align="left">
                             <textarea name="ctt" id="ctt" style="width:100%;height:600px;visibility:hidden;"></textarea>
                             </td>
                          </tr>
                            <tr>
                             <td></td>
                              <td align="left"> 标签:<input type="text" name="title" id="title" class="input-text lh30" size="100"></td>
                          </tr>
                          <tr><td>&nbsp;</td><td></td></tr>
                           <tr>
                                <td>
                                   &nbsp;                                           
                                </td>
                                <td align="left">
                                                                                         是否使用：
                                   <input type="radio" name="status" value="Y"> 是
                                   <input type="radio" name="status" value="N" checked> 否
                                </td>
                           </tr>
                          <tr>
                             <td colspan=2 align="center">
                              <input type="button" name="button" id="cmtBtn" class="btn btn82 btn_save2" value="保存"> 
                              <input type="button" name="button" class="btn btn82 btn_res" value="重置">
                              
                             </td>
                          </tr>
                    </table>
            </div>
             <div  id="glanceContent" style="display:none;width:100%;min-height:600px;word-wrap: break-word;word-break: break-all;overflow: hidden;">
                 <input type="button" value="保存" id="rcmtBtn" class="ext_btn ext_btn_submit">
                 <input type="button" value="继续" id="editBtn" class="ext_btn ext_btn_submit">
                  <pre id="cthmtl"></pre>
            </div>
        </div>    
   </div>
</div>
<div style="display:none">
   <input type="text" id="ObjectId" value="-1">
   <input type="text" id="category" value="-1">
</div>
</body>
 <script type="text/javascript" src="backgroundPages/js/career/addCareer.js"></script>
</html>