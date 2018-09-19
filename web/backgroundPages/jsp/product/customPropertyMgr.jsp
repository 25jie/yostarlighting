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
<link rel="stylesheet" href="backgroundPages/css/common.css">
<link rel="stylesheet" href="backgroundPages/css/main.css">
<link rel="stylesheet" href="backgroundPages/css/product.css">
<script type="text/javascript" src="common/js/jquery.min.js"></script>
<script type="text/javascript"
	src="backgroundPages/js/colResizable-1.3.min.js"></script>
<script type="text/javascript" src="common/js/util.js"></script>
<script type="text/javascript"
	src="backgroundPages/js/product/initCustomProperty.js"></script>
<script type="text/javascript"
	src="backgroundPages/js/product/customPropertyMgr.js"></script>

<title>产品参数管理</title>
</head>
<body>
	<div id="forms" class="mt10">
		<div class="box">
			<div class="box_border" style="min-height: 450px; width: 100%;">
				<div class="box_title">
					<b class="pl15">产品参数管理</b>
				</div>
				<div id="query_param_div" class="query_param_div">
					<div>
						<span class="span_left">产品分类：</span> 
						<select id="product_category" class="select selectWidth">
						</select>
					</div>
					<div class="add_property_div">
						<a href="javascript:void(0)" class="ext_btn" id="addProperty"><span class="add"></span>添加产品参数
						</a>
					</div>
				</div>
				<div style="margin: 0px 5px;">
					<table width="90%" border="0" cellpadding="0" cellspacing="0"
						class="list_table">
						<tr>
							<th width="70%" align="left" style="padding-left: 10px;">参数项名称</th>
							<th width="8%">启用</th>
							<th width="8%">排序</th>
							<th>操作</th>
						</tr>
						<tbody id="propertyTbody"></tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
	<div class="custom_property_add_main">
		<div class="coverDiv"></div>
		<div class="add_border">
			<div class="add_title">
				<b class="pl15">新增产品参数</b>
			</div>
			<div class="box_content">
				<span> 参数名称： </span>
				<input type="text" id="propertyName" class="input-text lh30 inputWidth">
				<input type="checkbox" id="isUsed" style="margin-left: 50px;"> <label for="isUsed">是否启用</label>
				<input type="hidden" id="propertyId">
			</div>
			<div class="box_foot">
				<input type="submit" id="saveBtn" value="确定" class="ext_btn ext_btn_submit">
       			<input type="button" value="取消" class="ext_btn" id="cancelBtn">	
			</div>
		</div>
	</div>
</body>
</html>