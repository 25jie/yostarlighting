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
<title>幻灯片</title>
<link rel="stylesheet" href="backgroundPages/css/common.css">
<link rel="stylesheet" href="backgroundPages/css/main.css">
   <script type="text/javascript" src="common/js/jquery.min.js"></script>
    <script type="text/javascript" src="common/js/util.js"></script>
   <style type="text/css">
     #mytable{
      padding: 0;
       margin: 0;   
      border-collapse:collapse;
      align:center;
      width:80%;
      height:600px; 
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
	 .imgbox{width:242px;height:120px;margin-left:10px;text-align:center;float:left;}
     .imgbox_inner{width:242px;height:98px;border:1px solid #d3dbde;vertical-align:middle;}
      .imgbox p{font-size:14px;height:14px;margin-bottom:0px;}
      
       .slide_img{width:125px;height:72px;margin:4px;}
	.slide{float:left; margin: 15px 0px 0px 8px;}
	.imgBorder{border:1px solid #eaebef; margin:1px 1px 1px 3px;cursor:pointer;_padding-top:4px;_padding-bottom:4px;}
	.imgBorderHover{border:2px solid #7bcaff; margin:0 0 0 2px;_padding-bottom:3px;}
	.imgBorderDown{border:2px solid #7bcaff; margin:0 0 0 2px;_padding-bottom:3px;}
	#arrowSilde .slide_img{ background:url("backgroundPages/images/arrowSlide.jpg");}
	#numSilde .slide_img{background:url("backgroundPages/images/numSlide.jpg");}
	#dotSilde .slide_img{background:url("backgroundPages/images/dotSlide.jpg");}
	.slideLabel{float:left; padding-top:8px; margin-left:45px;_margin-left:25px;cursor:pointer;}
   </style> 
</head>
<body>
  <div class="container">
    <div id="forms" class="mt10">
        <div class="box">
          <div class="box_border">
            <div class="box_top"><b class="pl15">幻灯片配置</b></div>
            <div class="box_center">
              <form action="" class="jqtransform" id="form1" name="form1" encType="multipart/form-data"  method="post" target="hidden_frame">
               <table id="mytable" cellspacing="0" align="center">
                   <tr height="190">
                       <td colspan=3><div id="banerDiv"></div></td>
                   </tr>
                   <tr>
                      <td width="100" class="alt">高度:</td> <td width="100"><input type="text" id="height"></td><td class="td_left">整型 (单位:像素)】焦点图高度，必须设置 缺省则启用默认高度 500</td> 
                   </tr>
                   <tr>
                      <td class="alt">间隔时间:</td> <td><input type="text" id="intervalTime"></td><td class="td_left">【整型 (单位:秒)】焦点图切换间隔时间</td> 
                   </tr>
                   <tr>
                      <td class="alt">切换所需时间:</td> <td><input type="text" id="switchTime"></td><td class="td_left">【整型 (单位:毫秒)】焦点图切换所需时间，若设置为0 则无切换效果 直接切换到下一张</td> 
                   </tr>
                   <tr>
                      <td class="alt">擦除效果:</td> <td><input type="text" id="easing"></td><td class="td_left">【字符型】擦除效果(切换) jQuery自带有 "linear" 和 "swing" ,如需要其他擦除效果请使用 jquery.easing.js 插件</td> 
                   </tr>
                   <tr>
                      <td class="alt">图片对齐方式:</td><td><input type="text" id="imageAlign"></td> <td class="td_left">【字符型】焦点图图片对齐方式,可选值:水平方向left、center、right，垂直方向top、center、bottom 中间以“空格”分隔</td>
                   </tr>
                   <tr><td class="alt">横幅样式:</td>
	                   <td colspan=2>
	                     <input type="hidden" id="buttonStyle">
	                     <div id="basicSetting" >
							<div style="width:100%; clear:both; height:auto; ">
								<div>
									<div class="slide" >
										<div id="numSilde" class="imgBorder"><div class="slide_img" ></div></div>
										<div class="slideLabel">数字轮播</div>
									</div>
									<div class="slide" >
										<div id="dotSilde" class="imgBorder"><div class="slide_img" ></div></div>
										<div class="slideLabel">圆点切换</div>
									</div>
									<div class="slide" >
										<div id="arrowSilde" class="imgBorder"><div class="slide_img" ></div></div>
										<div class="slideLabel">箭头滑动</div>
									</div>
								</div>
								<div class="clearfloat"></div>
							</div>
						</div>
	                   </td>
                   </tr>
                    <tr><td></td><td><input type="button" name="button" id="saveBtn" class="btn btn82 btn_save2" value="保存"> 
                    <input type="reset" name="button" class="btn btn82 btn_res" value="重置"> </td><td></td></tr>
               </table>
               </form>
               <input type="hidden" value="-1" id="kinmaxshow_id">
            </div>
          </div>
        </div>
     </div>
  </div>
</body>
<script type="text/javascript">
$(document).ready(function(){
	initPic();	
	$.get('getKinMaxShow.do',{},function(result){
		var _ob=eval('('+result+')');
		$('#height').val(_ob.height);
		$('#intervalTime').val(_ob.intervalTime);
		$('#switchTime').val(_ob.switchTime);
		$('#easing').val(_ob.easing);
		$('#imageAlign').val(_ob.imageAlign);
		$('#buttonStyle').val(_ob.buttonStyle);
		$('#kinmaxshow_id').val(_ob.kinmaxshow_id);
		var _style=_ob.buttonStyle;
		$(".imgBorder").removeClass("imgBorderDown");
		switch(_style){
		case '1':
			$('#numSilde').addClass("imgBorderDown");break;
		case '2':
			$('#dotSilde').addClass("imgBorderDown");break;
		default:
			$('#arrowSilde').addClass("imgBorderDown");
		}
	});
	
	$('#saveBtn').click(function(){
		$.post('updateKinMaxShow.do',{
			'kinmaxshow_id':$('#kinmaxshow_id').val(),
			'height':$('#height').val(),
			'intervalTime':$('#intervalTime').val(),
			'switchTime':$('#switchTime').val(),
			'easing':$('#easing').val(),
			'imageAlign':$('#imageAlign').val(),
			'buttonStyle':$('#buttonStyle').val()
		},function(result){
			var _ret=eval('('+result+')');
			if(_ret.success){
				alert('保存成功！');
				window.location.href=MyUtil.getContext()+'backgroundPages/jsp/home/huangdengpian.jsp?i='+Math.random();
			}else{
				alert('保存失败！');
			}
		});
	});

	$(".imgBorder").hover(function(){
		$(this).addClass("imgBorderHover");
	},function(){
		$(this).removeClass("imgBorderHover");
	});

	$(".slideLabel").click(function(){
		$(this).prev().click();
	});

	
	$('.imgBorder').click(function(){
		$(".imgBorder").removeClass("imgBorderDown");
		$(this).addClass("imgBorderDown");
		var img_id = $(this).attr("id");
		if(img_id == "numSilde" ){
			save_banner_type = 1;
		}
		else if(img_id == "dotSilde"){
			save_banner_type = 2;
		}else{
			save_banner_type = 0;
		}
		$('#buttonStyle').val(save_banner_type);
	});

});

function initPic(){
	$.post('getPictureCategorys.do',{offset:0,limit:5,'picture_cate_name':'幻灯片'},function(result){
		var picCates=eval('('+result+')');
		if(picCates.length){
			var _id=picCates[0].picture_cate_id;
			initBanner(_id);
		}
	});
}
function initBanner(picture_category_id){
	$.post('getPictures.do',{offset:0,limit:20,'picture_category_id':picture_category_id},function(result){
		var _json=eval('('+result+')');
		var _pics=_json.root;
		var _html='';
		if(_pics.length){
			for(var i=0;i<_pics.length;i++){
				var _p=_pics[i];
				var _showUrl=MyUtil.getContext()+'thumbPicture/'+_p.picture_newname;
				_html+='<div class="imgbox">';
				_html+='<div class="imgbox_inner">';
				_html+='<img src="'+_showUrl+'" />';
				_html+='<p>';
				_html+='<img src="backgroundPages/images/ic_delete.png" title="删除" alt="删除" onclick="deletePic(\''+_p.picture_id+'\')" style="cursor:pointer;">';
				if(_p.picture_showOnIndex=='1'){
				_html+='<img src="backgroundPages/images/ic_lock.png" title="取消展示" alt="取消展示" onclick="cancelZs(\''+_p.picture_id+'\')" style="cursor:pointer;">';
				}else{
				_html+='<img src="backgroundPages/images/ic_unlock.png" title="设置首页展示" alt="设置首页展示" onclick="setZs(\''+_p.picture_id+'\')" style="cursor:pointer;">';	
				}
				_html+='</p>';
				_html+='</div>';
				_html+='</div>';
			}
		}
        $('#banerDiv').html(_html);
        
	});
}
function deletePic(id){
	$.post('deletePicture.do',{'deleteId':id},function(result){
		 var _ret=eval('('+result+')');
		 if(_ret.success){
			 alert('删除成功！');
			 initPic();
		 }else{
			 alert('删除失败');
		 }
	});
}

function cancelZs(id){
	$.post('upddatePictureInfo.do',{'picture_id':id,'picture_showOnIndex':'0'},function(result){
		 var _ret=eval('('+result+')');
		 if(_ret.success){
			 alert('设置成功！');
			 initPic();
		 }else{
			 alert('设置失败');
		 }
	});
}
function setZs(id){
	$.post('upddatePictureInfo.do',{'picture_id':id,'picture_showOnIndex':'1'},function(result){
		 var _ret=eval('('+result+')');
		 if(_ret.success){
			 alert('设置成功！');
			 initPic();
		 }else{
			 alert('设置失败');
		 }
	});
}
</script>
</html>