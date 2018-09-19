(function($){
	
	$.fn.del=function(options){
		
		var defaults={
				
		};
		var _opts=$.extend({},defaults,options);
		
		return this.each(function(){
			$(this).click(function(){
				//获取选中的checkbox
				var _ckid="";
				 $("input[name='"+_opts.CheckBoxName+"']:checked").each(function () {
	                    _ckid=_ckid+this.value+"$";
	             });
				 if(_ckid==""){
					 alert("请至少选一个");
				 }else{
				 if(window.confirm('你确定删除吗？')){
					 $.ajax({
						 url:_opts.ACTION,
						 type:"post",
						 data:{
							 "deleteId":_ckid
						 },
						 async:true,
				         success:function(result){
				            	var _o=eval("("+result+")");
				            	if(_o.success){
				            		if (typeof(isReload) != 'undefined' && isReload) {
				            			removeImgHtml();
									} else {
										alert("删除成功！");
										window.location.reload();
									}
				            	}else{
				            		alert(_o.message);
				            	}
				          }
					 });
				   }else{
					   return;
				   }
				 }
			});
		});
	};
	
	
})(jQuery);