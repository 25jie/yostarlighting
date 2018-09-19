<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
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
    <title>文件上传</title>
    <link rel="stylesheet" href="/zyUpload/control/css/zyUpload.css" type="text/css" media="screen"/>
    <script type='text/javascript' src='/common/js/jquery.min.js'></script>
    <script type="text/javascript" src="/common/js/util.js"></script>
    <script type="text/javascript">
        $.post("getPictureCategorys.do", {offset: 0, limit: 20}, function (result) {
            var pCates = eval("(" + result + ")");
            var _hmtl = "";
            if (pCates.length) {
                for (var i = 0; i < pCates.length; i++) {
                    var _pictureCate = pCates[i];
                    _hmtl += "<option value='" + _pictureCate.picture_cate_id + "'>" + _pictureCate.picture_cate_name + "</option>";
                }
            }
            $("#pictureCate").html(_hmtl);
        });
    </script>
    <!-- demo zyUpload -->
    <!-- 引用核心层插件 -->
    <script type="text/javascript" src="/zyUpload/core/zyFile.js"></script>
    <!-- 引用控制层插件 -->
    <script type="text/javascript" src="/zyUpload/control/js/zyUpload.js"></script>
    <!-- 引用初始化JS -->
    <script type="text/javascript" src="/zyUpload/demo.js"></script>

</head>
<body>

<div style="margin:0 auto;width:700px;">
    图片类型：
    <select id="pictureCate" style="width:100px;"></select>
    关联产品：<input id="extra1"/>
    <span style="float:right">
        宽度<input type="text" id="iHeight" value="242" style="width:40px;">px&nbsp;&nbsp;
        高度<input type="text" id="iWidth" value="155" style="width:40px;">px
    </span>
</div>
<div id="demo" class="demo"></div>

</body>
</html>