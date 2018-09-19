<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<%
	String path = request.getContextPath();
	String serverIp = request.getServerName();
	String basePath = request.getScheme() + "://" + serverIp + ":"
			+ request.getServerPort() + path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<base href="<%=basePath%>">
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title>编辑产品</title>
	
	<link rel="stylesheet" href="backgroundPages/css/common.css">
	<link rel="stylesheet" href="backgroundPages/css/main.css">
	<link rel="stylesheet" type="text/css" href="backgroundPages/css/editor/default.css">
	<link rel="stylesheet" type="text/css" href="backgroundPages/css/product.css">
	<link rel="stylesheet" href="zyUpload/control/css/zyUpload.css" type="text/css" media="screen" />
	
	<script type="text/javascript" src="common/js/jquery.min.js"></script>
	<script type="text/javascript" src="backgroundPages/js/colResizable-1.3.min.js"></script>
	<script type="text/javascript" src="backgroundPages/js/common.js"></script>
	<script type="text/javascript" src="common/js/util.js"></script>
	<script type="text/javascript" src="backgroundPages/js/kindeditor-min.js"></script>
	<script type="text/javascript" src="backgroundPages/js/zh_CN.js"></script>
	<script type="text/javascript" src="backgroundPages/js/product/editProduct.js"></script>
	<script type="text/javascript" src="backgroundPages/js/del.js"></script>
	<!-- demo zyUpload -->
	<!-- 引用核心层插件 -->
	<script type="text/javascript" src="zyUpload/core/zyFile.js" ></script>
	<!-- 引用控制层插件 -->
	<script type="text/javascript" src="zyUpload/control/js/zyUpload.js" ></script>
	<!-- 引用初始化JS -->
	<script type="text/javascript" src="backgroundPages/js/product/uploadImg.js"></script>
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
	<div id="forms" class="mt10">
    	<div class="box">
        	<div class="box_border">
	            <div class="box_title"><b class="pl15">编辑产品</b></div>
	            <div class="main_content">
	            	<div class="content_menu">
	            		<div class="menu_on" id="menu_basic">
	            			常规
	            		</div>
	            		<div class="menu_off" id="menu_advance">
	            			高级
	            		</div>
	            	</div>
	            	<div id="product_basic" class="basic">
	            		<div class="box_center contentLeft">
			            	<table width="100%" border="0" cellpadding="0" cellspacing="0">
			            		<tr>
			            			<td class="td_right">产品名称：</td>
			            			<td class="td_left">
			            				<input type="text" id="product_name" class="input-text lh30 inputWidth">
			            			</td>
			            			<td class="td_right">产品分类：</td>
			            			<td class="td_left">
			            				<select id="product_category" class="select selectWidth">
				                    	</select>
			            			</td>
			            		</tr>
								<tr>
									<td class="td_right">产品标题：</td>
									<td class="td_left"colspan="3">
										<textarea type="text" id="product_title" class="lh30 inputWidth"></textarea>
									</td>
								</tr>
								<tr>
									<td class="td_right">产品简介：</td>
									<td class="td_left">
										<input type="text" id="summary" class="input-text lh30 inputWidth">
									</td>
									<td class="td_right">产品规格：</td>
									<td class="td_left">
										<input type="text" id="product_spec" class="input-text lh30 inputWidth">
									</td>
								</tr>
								<tr>
									<td class="td_right">保修期：</td>
									<td class="td_left">
										<input type="text" id="warranty" class="input-text lh30 inputWidth">
									</td>
									<td class="td_right">库存量：</td>
									<td class="td_left">
										<input type="text" id="stock" class="input-text lh30 inputWidth">
									</td>
								</tr>
			            		<tr>
			            			<td colspan="4" class="td_left">
			            				<div class="td_title" >
			            					<b>产品参数</b>	
			            				</div>
			            			</td>
			            		</tr>
			            		<tbody id="customPropertyTbody"></tbody>
			            	</table>
			            </div>
			            <div class="box_center contentRight" id="productImgDiv">
			            	<div class="uploadBtn">
			            		<input type="button" name="uploadBtn" id="uploadBtn" class="ext_btn" style="width:100px;height:34px;" value="上传图片">
			            		<input type="button" name="delBtn" id="delBtn" class="ext_btn" style="width:100px;height:34px;" value="删除图片">
			            	</div>
			            </div> 
	            	</div>
	            	<div id="product_advance" class="advance">
	            		<div class="box_center">
	            			<div class="advance_content">
	            				<input type="hidden" id="advancedPropertyId"/>
	            				<span>产品标签：</span>
	            				<select class="select inputW250" id="author">
	            					<option value="1">最新</option>
	            					<option value="2">推荐</option>
	            				</select>
	            			</div>
	            			<div class="advance_content">
	            				<span>录入时间：</span>
	            				<input type="text" id="inputTime" class="input-text lh30 inputW250" readonly>
	            			</div>
	            			<div class="advance_content">详细说明：</div>
	            			<div class="advance_content editContent">
	            				<textarea name="ctt" id="ctt" style="width:80%;height:250px;visibility:hidden;"></textarea>
	            			</div>
	            		</div>
	            	</div>
	            	<div class="contentFoot">
	            		<input type="submit" id="saveBtn" value="确定" class="ext_btn ext_btn_submit">
                 		<input type="button" value="返回" onclick="location.href='javascript:history.go(-1)'" class="ext_btn"> 
	            	</div>
	            </div>
	        </div>
	    </div>
	</div>
	<div id="uploadImgDiv">
		<div class="closeImg"><span style="cursor: pointer;" id="closeBtn">X</span></div>
		<div class="coverDiv"></div>
		<div id="productImg" class="demo"></div>	
	</div>
</body>
</html>