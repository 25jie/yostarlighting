$(document).ready(function(){
    	$("#newsTbody").initNews({
    		offset:0,
    		limit:10,
    		action:MyUtil.getContext()+"getNews.do",
    		showNews:MyUtil.getContext()+"backgroundPages/jsp/news/showNews.jsp?objectId="
    	});
    	$(".list_table").colResizable({
            liveDrag:true,
            gripInnerHtml:"<div class='grip'></div>", 
            draggingClass:"dragging", 
            minWidth:30
          }); 
    	//删除
    	$("#delBtn").del({
    		CheckBoxName:"news_id",
    		ACTION:MyUtil.getContext()+"deleteNews.action"
    	});
    	//新增
    	$("#addBtn").click(function(){
    		window.location.href=MyUtil.getContext()+"backgroundPages/jsp/news/editNews.jsp";
    	});
    	//编辑
    	$("#editBtn").click(function(){
    		var _ckid="";
			 $("input[name='news_id']:checked").each(function (){
                   _ckid=this.value;
                   return;
            });
			 if(_ckid==""){
				 alert("请选择一个对象");
				 return;
			 }
    		window.location.href=MyUtil.getContext()+"backgroundPages/jsp/news/editNews.jsp?objectId="+_ckid;
    	});
    	//全选
    	$("#ckAll").click(function(){
    		$(this).hide();
    		$("#ckNo").show();
    		$("input[type='checkbox'][name='news_id']").attr("checked",true);
    	});
    	//取消
    	$("#ckNo").click(function(){
    		$(this).hide();
    		$("#ckAll").show();
    		$("input[type='checkbox'][name='news_id']").attr("checked",false);
    	});
    	
    	
    });

function cancelZs(id,obj){
	$.post('setShowOnIndex.do',{'news_id':id,'news_show':'0'},function(result){
		 var _ret=eval('('+result+')');
		 if(_ret.success){
			 alert('设置成功！');
			$(obj).attr('src','backgroundPages/images/ic_unlock.png');
		 }else{
			 alert('设置失败');
		 }
	});
}
function setZs(id,obj){
	$.post('setShowOnIndex.do',{'news_id':id,'news_show':'1'},function(result){
		 var _ret=eval('('+result+')');
		 if(_ret.success){
			 alert('设置成功！');
			 $(obj).attr('src','backgroundPages/images/ic_lock.png');
		 }else{
			 alert('设置失败');
		 }
	});
}