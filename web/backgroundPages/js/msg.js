$(document).ready(function(){
	
	//window.setInterval(getMsg, 6000);
	getMsg();
	function getMsg(){
		$.post('getMessages.do',{'isLook':'0',offset:0,limit:5},function(result){
			var _obj=eval('('+result+')');
			var _num=_obj.totalProperty;
			var _mes=_obj.root;
			var _html='<ul>';
			if(_mes.length){
				for(var i=0;i<_mes.length;i++){
					var _m=_mes[i];
					_html+='<li><dl class="num">'+(i+1)+'</dl><a href="javascript:void(0)" onclick="tz(\''+_m.message_id+'\')">'+MyUtil.subString(_m.message_content,20)+'</a><span>'+MyUtil.getDateShowStr(_m.message_time)+'</span></li>';
				}
			}
			_html+='</ul>';
			$('#notice').html(_num);
			$('#messageBox').html(_html);
		});
	}

});

function tz(mid){
	$("#rightMain").attr("src",MyUtil.getContext()+"backgroundPages/jsp/message/message.jsp?message_id="+mid);
}