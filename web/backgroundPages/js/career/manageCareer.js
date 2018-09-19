$(document).ready(function(){
    	$("#careerTbody").initCareer({
    		offset:0,
    		limit:5,
    		action:MyUtil.getContext()+"getArticles.do",
    		showNews:MyUtil.getContext()
    	});
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
    		window.location.href=MyUtil.getContext()+"backgroundPages/jsp/career/addCareer.jsp";
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
    		window.location.href=MyUtil.getContext()+"backgroundPages/jsp/career/addCareer.jsp?objectId="+_ckid;
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
    });

function useArticle(id,obj){
	$.post('useArticle.do',{'article_id':id,'article_categoryDM':'CAREER','needshow':'Y'},function(result){
		var _ret=eval('('+result+')');
		if(_ret.success){
			alert('顶置成功！');
			$(obj).html('已经顶置');
			
		}else{
			alert('顶置失败！');
		}
	});
}