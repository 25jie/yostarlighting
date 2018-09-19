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
<title>编辑人员</title>
<link rel="stylesheet" href="backgroundPages/css/common.css">
   <link rel="stylesheet" href="backgroundPages/css/main.css">
   <script type="text/javascript" src="common/js/jquery.min.js"></script>
   <script type="text/javascript" src="backgroundPages/js/colResizable-1.3.min.js"></script>
   <script type="text/javascript" src="backgroundPages/js/common.js"></script>
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
        <div class="box">
          <div class="box_border">
            <div class="box_top"><b class="pl15">人员编辑</b></div>
            <div class="box_center">
              <form action="uploadPicture.action" class="jqtransform" id="form1" name="form1" encType="multipart/form-data"  method="post" target="hidden_frame">
               <table class="form_table pt15 pb15" width="80%" border="0" cellpadding="0" cellspacing="0">
                 <tr>
                  <td class="td_right"><span style="color:red">*</span>用户名：</td>
                  <td class=""> 
                    <input type="text" name="name" id="username"  class="input-text lh30" size="40">
                    <span style="color:red" id="usernameMsg"></span>
                  </td>
                   <td rowspan=8>
                       
                           <table align="center" width="100%" id="headedit" style="display:none">
                               <tr><td class="td_left">设置头像</td></tr>
                               <tr>
                                  <td class="td_left"><img width=180px height=180px src="images/mr.jpg" id="headimg"><img src="images/loading.gif" style="display:none" id="loading"></td>
                               </tr>
                               <tr>
                                  <td class="td_left"><input type="file" id="upload" name="upload" class="input-text lh30"/><input type="button" value="开始上传" id="up" class="ext_btn ext_btn_submit"/></td>
                               </tr>
                               <tr>
                                  <td> <font color="red">支持bmp,png,gif,jpeg,pjpeg文件的上传</font><iframe name='hidden_frame' id="hidden_frame" style='display:none'></iframe></td>
                               </tr>
                               <tr>
                                  <td>
                                     <div style="display:none">
                                         <input type="text" name="picture.picture_category_id" id="ObjectId" value="-1">
                                     </div>
                                  </td>
                               </tr>
                           </table>
                       
                   </td>
                </tr>
                <tr>
                  <td class="td_right"><span style="color:red">*</span>真实姓名：</td>
                  <td class=""> 
                    <input type="text" name="name" id="realname"  class="input-text lh30" size="40">
                    <span style="color:red" id="realnameMsg"></span>
                  </td>
                </tr>
                 <tr>
                  <td class="td_right"><span style="color:red">*</span>密&nbsp;&nbsp;&nbsp;码：</td>
                  <td class=""> 
                    <input type="password" name="userpwd" id="userpwd"  class="input-text lh30" size="40">
                    <span style="color:red" id="userPwdMsg"></span>
                  </td>
                </tr>
                 <tr>
                  <td class="td_right"><span style="color:red">*</span>确认密码：</td>
                  <td class=""> 
                    <input  type="password" name="pwd" id="pwd"  class="input-text lh30" size="40">
                    <span style="color:red" id="pwdMsg"></span>
                  </td>
              
                </tr>
                 <tr>
                  <td class="td_right"><span style="color:red">*</span>手机号码：</td>
                  <td class=""> 
                    <input type="text" type="text" name="mobilphone" id="mobilphone"  class="input-text lh30" size="40">
                    <span style="color:red" id="mobilphoneMsg"></span>
                  </td>
                
                </tr>
                 <tr>
                  <td class="td_right"><span style="color:red">*</span>邮箱：</td>
                  <td class=""> 
                    <input type="text" type="text" name="email" id="email"  class="input-text lh30" size="40">
                    <span style="color:red" id="emailMsg"></span>
                  </td>
                 
                </tr>
                 <tr>
                  <td class="td_right">办公电话：</td>
                  <td class=""> 
                    <input type="text" type="text" name="officephone" id="officephone"  class="input-text lh30" size="40">
                  </td>
                </tr>
                <tr>
                  <td class="td_right">性别：</td>
                  <td class="">
                    <input type="radio" name="sex" value="男" checked> 男
                    <input type="radio" name="sex" value="女"> 女
                  </td>
                  
                </tr>
                <tr>
                  <td class="td_right">QQ：</td>
                  <td class=""> 
                    <input type="text" type="text" id="qq"  class="input-text lh30" size="40">
                  </td>
                  
                </tr>
                <tr>
                  <td class="td_right">联系地址：</td>
                  <td class=""> 
                    <input type="text" type="text" name="address" id="address"  class="input-text lh30" size="40">
                  </td>
               
                </tr>
                 <tr>
                     <td></td>
                     <td> 
                     <input type="button" name="button" id="cmtBtn" class="btn btn82 btn_save2" value="注册"> 
                      <input type="button" name="button" id="saveBtn" class="btn btn82 btn_save2" value="保存"> 
                    <input type="reset" name="button" class="btn btn82 btn_res" value="重置"> 
                    </td>
                     <td></td>
                 </tr>
               
               </table>
               </form>
            </div>
          </div>
        </div>
     </div>
</div>
</body>
 <script type="text/javascript" src="backgroundPages/js/user/editUsers.js"></script>
</html>