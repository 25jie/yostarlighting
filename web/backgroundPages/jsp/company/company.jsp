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
 		$("#cid").html(_company.company_id);
 		$('#cname').html(_company.company_name);
 		$('#cenname').html(_company.company_enname);
 		$('#ctel').html(_company.company_tel);
 		$('#cfax').html(_company.company_fax);
 		$('#cqq').html(_company.company_qq);
 		$('#cemail').html(_company.company_email);
 		$('#cadd').html(_company.company_address);
 		$('#cym').html(_company.company_yuming);
 		$('#ccontact1').html(_company.company_contactuser1);
 		$('#cphone1').html(_company.company_phone1);
 		$('#cqq1').html(_company.company_qq1);
 		$('#ccontact2').html(_company.company_contactuser2);
 		$('#cphone2').html(_company.company_phone2);
 		$('#cqq2').html(_company.company_qq2);
 		
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
    #mytable .td_left{
       font-size:12px;
       align:left;
       color: #797268;
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
                     <span id="cname"></span>
                  </td>
                   <td rowspan=6 width="40%">
                           <table align="center" width="100%" style="margin-top:0px;">
                               <tr><td class="td_left">LOGO</td></tr>
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
                  <td class="td_left"> 
                    <span id="cenname"></span>
                  </td>
                </tr>
                 <tr>
                  <td class="td_right"><span style="color:red">*</span>公司电话：</td>
                  <td class="td_left"> 
                    <span id="ctel"></span>
                  </td>
                </tr>
                 <tr>
                  <td class="td_right"><span style="color:red">*</span>公司传真：</td>
                  <td class="td_left"> 
                  <span id="cfax"></span>
                  </td>
              
                </tr>
                 <tr>
                  <td class="td_right"><span style="color:red">*</span>公司邮箱：</td>
                  <td class="td_left"> 
                   <span id="cemail"></span>
                  </td>
                
                </tr>
                 <tr>
                  <td class="td_right"><span style="color:red">*</span>公司QQ：</td>
                  <td class="td_left"> 
                    <span id="cqq"></span>
                  </td>
                 
                </tr>
                 <tr>
                  <td class="td_right"><span style="color:red">*</span>公司地址：</td>
                  <td class="td_left"> 
                    <span id="cadd"></span>
                  </td>
                  <td class="" rowspan=4> 
                    
                  </td>
                </tr>
                <tr>
                  <td class="td_right"><span style="color:red">*</span>域名地址：</td>
                  <td class="td_left"> 
                    <a href="javascript:void(0)"  id="aym"><span id="cym"></span></a>
                  </td>
                </tr>
                <tr>
                  <td class="td_right">联系方式一</td>
                  <td class="td_left"> 
                           <ul>
                              <li>姓名：<span id="ccontact1"></span></li>
                              <li>电话：<span id="cphone1"></span></li>
                              <li>Q&nbsp;&nbsp;Q：<span id="cqq1"></span></li>
                           </ul>
                  </td>
                  
                </tr>
                <tr>
                  <td class="td_right">联系方式二</td>
                  <td class="td_left"> 
                         <ul>
                              <li>姓名：<span id="ccontact2"></span></li>
                              <li>电话：<span id="cphone2"></span></li>
                              <li>Q&nbsp;&nbsp;Q：<span id="cqq2"></span></li>
                         </ul>
                  </td>
                </tr>
                 <tr>
                     <td colspan=3>
                      <input type="hidden" id="cid" value="-1">
                     <input type="button" name="button" id="eidtBtn" class="btn btn82 btn_save2" value="编辑"> 
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