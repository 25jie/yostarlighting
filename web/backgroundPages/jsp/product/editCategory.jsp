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
	<title>编辑产品类别</title>
	
	<link rel="stylesheet" href="backgroundPages/css/common.css">
	<link rel="stylesheet" href="backgroundPages/css/main.css">
	<link rel="stylesheet" type="text/css" href="backgroundPages/css/editor/default.css">
	
	<script type="text/javascript" src="common/js/jquery.min.js"></script>
	<script type="text/javascript" src="backgroundPages/js/colResizable-1.3.min.js"></script>
	<script type="text/javascript" src="backgroundPages/js/common.js"></script>
	<script type="text/javascript" src="common/js/util.js"></script>
	<script type="text/javascript" src="backgroundPages/js/kindeditor-min.js"></script>
	<script type="text/javascript" src="backgroundPages/js/zh_CN.js"></script>
	<script type="text/javascript" src="adminJs/mineAjax.js"></script>
	<script type="text/javascript" src="backgroundPages/js/product/editCategory.js"></script>
	<style type="text/css">
		body {
			padding-left: 10px;
			padding-right: 5px;
		}
	</style>
</head>
<body>
	<div id="forms" class="mt10">
    	<div class="box">
        	<div class="box_border">
	            <div class="box_top"><b class="pl15">产品类别信息</b></div>
	            <div class="box_center">
	            	<form action="addCategory.do" class="jqtransform" id="form_add">
				    	<table class="form_table pt15 pb15" width="100%" border="0" cellpadding="0" cellspacing="0">
				      		<tr>
				       			<td class="td_right">类别名称：</td>
				       			<td class="td_left"> 
				       				<input type="hidden" id="category_id">
				         			<input type="text" id="category_name" class="input-text lh30" size="40">
				         			<span style="color: red">*</span>
				         			<span style="color: red; display: none" id="categoryName_errorMsg">请填写类别名称</span>
				       			</td>
				       			<td class="td_right"></td>
				       			<td class="td_left"> </td>
				     		</tr>
				     		<tr>
				       			<td class="td_right">类别描述：</td>
			                  	<td class="td_left">
			                    	<textarea id="category_remark" cols="30" rows="10" class="textarea"></textarea>
			                  	</td>
				       			<td class="td_right"></td>
				       			<td class="td_left"> </td>
				     		</tr>
				     		<tr>
			                   	<td class="td_right">&nbsp;</td>
			                   	<td class="td_left">
			                     	<input id="save_category" type="button" name="button" class="btn btn82 btn_save2" value="保存"> 
			                    	<input id="reset_category" type="button" name="button" class="btn btn82 btn_res" value="重置"> 
			                   	</td>
			                </tr>
				     	</table>
				    </form>
	            </div>
	        </div>
	    </div>
	</div>
</body>
</html>