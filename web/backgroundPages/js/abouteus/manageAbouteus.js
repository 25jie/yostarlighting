$(document).ready(function(){
	
	   
	//初始化类型
	
	 $.post("getMenu.do",{"navigation_name":"关于我们","navigation_type":"QT"},function(result){
		 var _obj=eval("("+result+")");
		 if(_obj.length){
			 var _subMenus=_obj[0].subMenus;
			 if(_subMenus.length){
				 var _option="";
				 for(var i=0;i<_subMenus.length;i++){
					 var _subMenu=_subMenus[i];
					 _option+="<option value=\""+_subMenu.navigation_id+"\">"+_subMenu.navigation_name+"</option>";
				 }
				 $("#cate").html(_option);
			 }
			
		 }
		 init($("#cate").val());
	 });
	  function init(id){
		  
		  $("#abouteTbody").initAboutes({
	    		offset:0,
	    		limit:5,
	    		action:MyUtil.getContext()+"getArticles.do",
	    		article_category:id,
	    		showNews:MyUtil.getContext()
	    	});
	  }
    	
    	$(".list_table").colResizable({
            liveDrag:true,
            gripInnerHtml:"<div class='grip'></div>", 
            draggingClass:"dragging", 
            minWidth:30
          }); 
    	
    	//删除
    	$("#delBtn").del({
    		CheckBoxName:"article_id",
    		ACTION:MyUtil.getContext()+"deleteArticle.do"
    	});
    	//新增
    	$("#addBtn").click(function(){
    		window.location.href=MyUtil.getContext()+"backgroundPages/jsp/abouteus/addAbouteUs.jsp";
    	});
    	//编辑
    	$("#editBtn").click(function(){
    		var _ckid="";
			 $("input[name='article_id']:checked").each(function (){
                   _ckid=this.value;
                   return;
            });
			 if(_ckid==""){
				 alert("请选择一个对象");
				 return;
			 }
    		window.location.href=MyUtil.getContext()+"backgroundPages/jsp/abouteus/addAbouteUs.jsp?objectId="+_ckid;
    	});
    	//全选
    	$("#ckAll").click(function(){
    		$(this).hide();
    		$("#ckNo").show();
    		$("input[type='checkbox'][name='article_id']").attr("checked",true);
    	});
    	//取消
    	$("#ckNo").click(function(){
    		$(this).hide();
    		$("#ckAll").show();
    		$("input[type='checkbox'][name='article_id']").attr("checked",false);
    	});
    	
    	//添加select事件
    	$("#cate").change(function(){
    		init($(this).val());
    	});
    });

function useArticle(id,obj){
	$.post('useArticle.do',{'article_id':id,'article_category':$("#cate").val(),'needshow':'Y'},function(result){
		var _ret=eval('('+result+')');
		if(_ret.success){
			alert('顶置成功！');
			$(obj).html('已经顶置');
			
		}else{
			alert('顶置失败！');
		}
	});
}
