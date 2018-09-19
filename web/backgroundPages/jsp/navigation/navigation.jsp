<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%
    String path = request.getContextPath();
    String serverIp = request.getServerName();
    String basePath = request.getScheme() + "://" + serverIp + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <base href="<%=basePath%>">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <link rel="stylesheet" href="backgroundPages/css/common.css">
    <link rel="stylesheet" href="backgroundPages/css/main.css">
    <link rel="stylesheet" href="backgroundPages/css/popWin.css">
    <script type="text/javascript" src="common/js/jquery.min.js"></script>
    <script type="text/javascript" src="backgroundPages/js/colResizable-1.3.min.js"></script>
    <script type="text/javascript" src="common/js/util.js"></script>
    <script type="text/javascript" src="backgroundPages/js/tc.min.js"></script>
    <script type="text/javascript" src="backgroundPages/js/navigation/navigation.js"></script>
    <title>菜单管理</title>
</head>
<body>
<div class="container">
    <div id="button" class="mt10">
        <select name="" class="select" id="select_type">
            <option value="HT">后台菜单管理</option>
            <option value="QT">前台菜单管理</option>
        </select>
        <a href="javascript:void(0)" onclick="addMenu()" class="ext_btn" style="float: right; margin-right: 20px;">
            <span class="add"></span>添加菜单
        </a>
    </div>
    <div id="table" class="mt10">
        <div class="box span10 oh">
            <table width="100%" border="0" cellpadding="0" cellspacing="0" class="list_table" id="navTbody">
            </table>
        </div>
    </div>
</div>
<div id="detail">
    <div class="tit">
        <i class="close">关闭</i>
    </div>
    <table width=100% height="80%" align="center">
        <tr>
            <td class="td_center">菜单名称:</td>
            <td>
                <input type="text" id="menuName" style="width: 200px; height: 20px;">
            </td>
        </tr>
        <tr>
            <td class="td_center">父亲菜单:</td>
            <td>
                <select id="parentMid" style="width: 200px; height: 20px;"></select>
            </td>
        </tr>
        <tr>
            <td class="td_center">简单描述:</td>
            <td>
                <input type="text" id="menuDetail" style="width: 200px; height: 20px;">
            </td>
        </tr>
        <tr>
            <td class="td_center">连接地址:</td>
            <td>
                <textarea id="menuUrl" cols="30" rows="3"></textarea>
            </td>
        </tr>
        <tr>
            <td colspan=2 align="center">
                <input type="button" value="保存" onclick="saveMenu()">&nbsp;&nbsp;
                <input type="button" value="取消" onclick="hide()">
            </td>
        </tr>
    </table>
    <div style="display: none">
        <input type="text" id="menuID" value="">
    </div>
</div>
</body>

</html>