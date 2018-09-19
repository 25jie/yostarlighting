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
    <title>图片管理</title>
    <link rel="stylesheet" href="backgroundPages/css/common.css">
    <link rel="stylesheet" href="backgroundPages/css/main.css">
    <script type="text/javascript" src="common/js/jquery.min.js"></script>
    <script type="text/javascript" src="common/js/util.js"></script>

    <script type="text/javascript" src="common/js/jquery.pager.js"></script>
    <script type="text/javascript" src="backgroundPages/js/picture/initPictures.js"></script>
    <script type="text/javascript" src="backgroundPages/js/del.js"></script>
    <script type="text/javascript" src="backgroundPages/js/picture/managePicture.js"></script>
    <style type="text/css">
        #image_content {
            width: 100%;
            margin: 10px auto;
            height: 630px;
        }

        #image_content .imgbox {
            width: 242px;
            height: 205px;
            margin-left: 24px;
            text-align: center;
            float: left;
        }

        #image_content .imgbox_inner {
            width: 242px;
            height: 180px;
            border: 1px solid #d3dbde;
            vertical-align: middle;
            display: table-cell;
        }

        #image_content .imgbox_inner img{
            max-width: 100%;
        }

        #image_content .imgbox p {
            font-size: 14px;
            height: 40px;
            margin-bottom: 0px;
        }

        #image_content .imgbox p .cut {
            width: 16px;
            height: 16px;
            background: url(/backgroundPages/images/ic_empty_document.png);
            display: block;
            float: left;
        }

        #image_content .imgbox p .big {
            width: 16px;
            height: 16px;
            background: url(/backgroundPages/images/ic_fullscreen.png);
            display: block;
            float: left;
        }
    </style>
</head>
<body>
<div class="container">
    <div id="button" class="mt10">
        显示： <select id="pictureCate"></select>&nbsp;&nbsp;
        <input type="button" name="button" id="uploadBtn" class="ext_btn" style="width:100px;height:34px;" value="上传图片">
        <input type="button" name="button" id="delBtn" class="btn btn82 btn_del" value="删除">
    </div>
    <div id="forms" class="mt10">
        <div class="box">
            <div class="box_border">
                <div class="box_top"><b class="pl15">图片管理</b></div>
                <div class="box_center">
                    <div id="image_content" class="img_content">

                    </div>
                </div>
            </div>
            <div id="pager"></div>
        </div>
    </div>
</div>
</body>
</body>
</html>