$(document).ready(function(){
    	$("#messageTbody").initMsg({
    		offset:0,
    		limit:20,
    		action:MyUtil.getContext()+"getMessages.do",
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
    		CheckBoxName:"message_id",
    		ACTION:MyUtil.getContext()+"deleteMessage.do"
    	});
    	
    	
    	//全选
    	$("#ckAll").click(function(){
    		$(this).hide();
    		$("#ckNo").show();
    		$("input[type='checkbox'][name='message_id']").attr("checked",true);
    	});
    	//取消
    	$("#ckNo").click(function(){
    		$(this).hide();
    		$("#ckAll").show();
    		$("input[type='checkbox'][name='message_id']").attr("checked",false);
    	});
    });

function seeMail(messageid){
	window.location.href=MyUtil.getContext()+"backgroundPages/jsp/message/message.jsp?message_id="+messageid;
}