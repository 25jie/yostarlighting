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
<title>公司信息</title>
<link rel="stylesheet" href="backgroundPages/css/common.css">
<link rel="stylesheet" href="backgroundPages/css/main.css">
   <script type="text/javascript" src="common/js/jquery.min.js"></script>
   <script type="text/javascript" src="backgroundPages/js/colResizable-1.3.min.js"></script>
   <script type="text/javascript" src="backgroundPages/js/common.js"></script>
    <script type="text/javascript" src="common/js/util.js"></script>
     <script type="text/javascript" src="backgroundPages/js/company/company.js"></script>
     <script type="text/javascript">
     
     $.post('getCompanyBaseInfo.do',{},function(result){
 		var _company=eval('('+result+')');
 		$("#cid").val(_company.company_id);
 		$('#cname').val(_company.company_name);
 		$('#cenname').val(_company.company_enname);
 		$('#ctel').val(_company.company_tel);
 		$('#cfax').val(_company.company_fax);
 		$('#cqq').val(_company.company_qq);
 		$('#cemail').val(_company.company_email);
 		$('#cadd').val(_company.company_address);
 		$('#cym').val(_company.company_yuming);
 		$('#ccontact1').val(_company.company_contactuser1);
 		$('#cphone1').val(_company.company_phone1);
 		$('#cqq1').val(_company.company_qq1);
 		$('#ccontact2').val(_company.company_contactuser2);
 		$('#cphone2').val(_company.company_phone2);
 		$('#cqq2').val(_company.company_qq2);
 	});
     </script>
   <style type="text/css">
     #mytable{
      padding: 0;
       margin: 0;   
      border-collapse:collapse;
      align:center;
      width:80%; 
     }
    #mytable  td {
    border: 1px solid #C1DAD7;   
    background: #fff;
    font-size:14px;
    padding: 6px 6px 6px 12px;
    color: #4f6b72;
    }

	 #mytable td.alt {
	    background: #F5FAFA;
	    color: #797268;
	}
   </style> 
</head>
<body>
  <div class="container">
    <div id="forms" class="mt10">
        <div class="box">
          <div class="box_border">
            <div class="box_top"><b class="pl15">基本配置</b></div>
            <div class="box_center">
              <form action="uploadPicture.action" class="jqtransform" id="form1" name="form1" encType="multipart/form-data"  method="post" target="hidden_frame">
               <table id="mytable" cellspacing="0" align="center">
                 <tr>
                  <td class="td_right" width="20%"><span style="color:red">*</span>公司名称：</td>
                  <td class="td_left" width="40%"> 
                    <input type="text" name="name" id="cname"  class="input-text lh30" size="40">
                  </td>
                   <td rowspan=6>
                           <table align="center" width="100%" style="margin-top:0px;">
                               <tr><td class="td_left">公司logo</td></tr>
                               <tr>
                                  <td class="td_left"><img width=180px height=180px src="backgroundPages/images/mr.jpg" id="headimg"><img src="images/loading.gif" style="display:none" id="loading"></td>
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
                  <td class="td_right"><span style="color:red">*</span>英文名称：</td>
                  <td class=""> 
                    <input type="text" name="name" id="cenname"  class="input-text lh30" size="40">
                    <span style="color:red" id="realnameMsg"></span>
                  </td>
                </tr>
                 <tr>
                  <td class="td_right"><span style="color:red">*</span>公司电话：</td>
                  <td class=""> 
                    <input type="text" name="userpwd" id="ctel"  class="input-text lh30" size="40">
                    <span style="color:red" id="userPwdMsg"></span>
                  </td>
                </tr>
                 <tr>
                  <td class="td_right"><span style="color:red">*</span>公司传真：</td>
                  <td class=""> 
                    <input  type="text" name="pwd" id="cfax"  class="input-text lh30" size="40">
                    <span style="color:red" id="pwdMsg"></span>
                  </td>
              
                </tr>
                 <tr>
                  <td class="td_right"><span style="color:red">*</span>公司邮箱：</td>
                  <td class=""> 
                    <input type="text" type="text" name="mobilphone" id="cemail"  class="input-text lh30" size="40">
                    <span style="color:red" id="mobilphoneMsg"></span>
                  </td>
                
                </tr>
                 <tr>
                  <td class="td_right"><span style="color:red">*</span>公司QQ：</td>
                  <td class=""> 
                    <input type="text" type="text" name="email" id="cqq"  class="input-text lh30" size="40">
                    <span style="color:red" id="emailMsg"></span>
                  </td>
                 
                </tr>
                 <tr>
                  <td class="td_right"><span style="color:red">*</span>公司地址：</td>
                  <td class=""> 
                    <input type="text" type="text" name="officephone" id="cadd"  class="input-text lh30" size="40">
                  </td>
                   <td class="" rowspan=4> 
                    
                  </td>
                </tr>
                <tr>
                  <td class="td_right"><span style="color:red">*</span>域名地址：</td>
                  <td class=""> 
                    <input type="text" type="text" name="officephone" id="cym"  class="input-text lh30" size="40">
                  </td>
                </tr>
                <tr>
                  <td class="td_right">联系方式一</td>
                  <td class=""> 
                           <ul>
                              <li>姓名：<input type="text" type="text" name="officephone" id="ccontact1"  class="input-text lh30" size="30"></li>
                              <li>电话：<input type="text" type="text" name="officephone" id="cphone1"  class="input-text lh30" size="30"></li>
                              <li>QQ：<input type="text" type="text" name="officephone" id="cqq1"  class="input-text lh30" size="30"></li>
                           </ul>
                  </td>
                  
                </tr>
                <tr>
                  <td class="td_right">联系方式二</td>
                  <td class=""> 
                         <ul>
                              <li>姓名：<input type="text" type="text" name="officephone" id="ccontact2"  class="input-text lh30" size="30"></li>
                              <li>电话：<input type="text" type="text" name="officephone" id="cphone2"  class="input-text lh30" size="30"></li>
                              <li>QQ：<input type="text" type="text" name="officephone" id="cqq2"  class="input-text lh30" size="30"></li>
                         </ul>
                  </td>
                </tr>
                 <tr>
                     <td colspan=2>
                     <input type="hidden" id="cid" value="-1">
                      <input type="button" name="button" id="saveBtn" class="btn btn82 btn_save2" value="保存"> 
                    <input type="reset" name="button" class="btn btn82 btn_res" value="重置"> 
                    </td>
                 </tr>
               </table>
               </form>
            </div>
          </div>
        </div>
     </div>
  </div>
</body>
</html>