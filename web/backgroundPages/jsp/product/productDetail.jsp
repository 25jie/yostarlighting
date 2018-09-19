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
	
	<script type="text/javascript" src="common/js/jquery.min.js"></script>
	<script type="text/javascript" src="backgroundPages/js/colResizable-1.3.min.js"></script>
	<script type="text/javascript" src="backgroundPages/js/common.js"></script>
	<script type="text/javascript" src="common/js/util.js"></script>
	<script type="text/javascript" src="adminJs/mineAjax.js"></script>
	<script type="text/javascript" src="backgroundPages/js/product/productDetail.js"></script>
	
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
			            			<td class="td_right_product">产品名称：</td>
			            			<td class="td_left_product">
			            				<span id="product_name"></span>
			            			</td>
			            		</tr>
								<tr>
									<td class="td_right_product">产品分类：</td>
									<td class="td_left">
										<span id="product_category"></span>
									</td>
								</tr>
			            		<tr>
			            			<td class="td_right_product">产品标题：</td>
			            			<td class="td_left">
			            				<span id="product_title"></span>
			            			</td>
			            		</tr>
								<tr>
									<td class="td_right_product">产品简介：</td>
									<td class="td_left">
										<span id="summary"></span>
									</td>
								</tr>
								<tr>
									<td class="td_right_product">保修期：</td>
									<td class="td_left">
										<span id="warranty"></span>
									</td>
								</tr>
								<tr>
									<td class="td_right_product">产品规格：</td>
									<td class="td_left">
										<span id="product_spec"></span>
									</td>
								</tr>
								<tr>
									<td class="td_right_product">库存：</td>
									<td class="td_left">
										<span id="stock"></span>
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
			            </div> 
	            	</div>
	            	<div id="product_advance" class="advance">
	            		<div class="box_center">
	            			<div class="advance_content">
	            				<span>属性标签：</span>
	            				<span class="inputW250" id="author"></span>
	            			</div>
	            			<div class="advance_content">
	            				<span>录入时间：</span>
	            				<span id="inputTime" class="inputW250"></span>
	            			</div>
	            			<div class="advance_content">详细说明：</div>
	            			<div class="advance_content editContent" style="border: 0px solid gray; width: 95%">
	            				<span id="ctt"></span>
	            			</div>
	            		</div>
	            	</div>
	            	<div class="contentFoot">
                 		<input type="button" value="返回" onclick="location.href='javascript:history.go(-1)'" class="ext_btn"> 
	            	</div>
	            </div>
	        </div>
	    </div>
	</div>
</body>
</html>