
$(document).ready(function(){
	
	
	//初始化图片下来框
	$.post("getPictureCategorys.do",{offset:0,limit:20},function(result){
            var pCates=eval("("+result+")");
            var _hmtl="<option value='all'>全部</option>";
            if(pCates.length){
            	for(var i=0;i<pCates.length;i++){
            		var _pictureCate=pCates[i];
            		_hmtl+="<option value='"+_pictureCate.picture_cate_id+"'>"+_pictureCate.picture_cate_name+"</option>";
            	}
            }
            $("#pictureCate").html(_hmtl);
            getPictures($("#pictureCate").val());
	});
	
	//获取图片
	function getPictures(cateid){
		$("#image_content").initPictures({
    		offset:0,
    		limit:12,
    		picture_category_id:cateid,
    		action:MyUtil.getContext()+"getPictures.do",
    		showNews:MyUtil.getContext()
    	});
		
	}
	
	//删除
	$("#delBtn").del({
		CheckBoxName:"picture_id",
		ACTION:MyUtil.getContext()+"deletePicture.do"
	});
	
	//添加select事件
	$("#pictureCate").change(function(){
		getPictures($(this).val());
	});
	
	//上传图片
	$("#uploadBtn").click(function(){
		window.location.href=MyUtil.getContext()+"backgroundPages/jsp/picture/upload.jsp";
	});
	
	
});


//复制图片地址
function copy_clip(txt) {
       if (window.clipboardData) {
               window.clipboardData.clearData();
               window.clipboardData.setData("Text", txt);
       } else if (navigator.userAgent.indexOf("Opera") != -1) {
               window.location = txt;
       } else if (window.netscape) {
               try {
                       netscape.security.PrivilegeManager.enablePrivilege("UniversalXPConnect");
               } catch (e) {
                       alert("您的firefox安全限制限制您进行剪贴板操作，请在新窗口的地址栏里输入'about:config'然后找到'signed.applets.codebase_principal_support'设置为true'");
                       return false;
               }
               var clip = Components.classes["@mozilla.org/widget/clipboard;1"].createInstance(Components.interfaces.nsIClipboard);
               if (!clip)
                       return;
               var trans = Components.classes["@mozilla.org/widget/transferable;1"].createInstance(Components.interfaces.nsITransferable);
               if (!trans)
                       return;
               trans.addDataFlavor('text/unicode');
               var str = new Object();
               var len = new Object();
               var str = Components.classes["@mozilla.org/supports-string;1"].createInstance(Components.interfaces.nsISupportsString);
               var copytext = txt;
               str.data = copytext;
               trans.setTransferData("text/unicode", str, copytext.length * 2);
               var clipid = Components.interfaces.nsIClipboard;
               if (!clip)
                       return false;
               clip.setData(trans, null, clipid.kGlobalClipboard);
       }
  	}