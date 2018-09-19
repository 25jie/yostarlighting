/**
 *  用于显示table与pager
 *  需要与jquery.pager.js插件一起使用
 *  Author:jgre
 *  Time:2014-5-7
 *  Version:1
 */
(function($){
	var _this;
	var _options;
	$.fn.initUsers=function(options){
		_options=options;
		var defaults={
				offset:0,
				limit:20
			};
		var opts=$.extend({},defaults,options);
		
		return this.each(function(){
			_this=$(this);
			getData(_this,opts);
		});
	};
	//获取数据
	function getData(obj,opts){
		$.ajax({
			url:opts.action,
		    type:"post",
		    data:{
            	   offset:opts.offset,
            	   limit:opts.limit,
            },
		    async:true,
            success:function(result){
            	//alert(result);
            	var ob=eval("("+result+")");
            	//初始化table
            	obj.empty().append(createTable(ob,opts.showNews));
            	//初始化分页
            	initPager(ob,opts);
            	//设置表格单双色
			    $(".tr:odd").css("background", "#FFFCEA");
				$(".tr:odd").each(function(){
					$(this).hover(function(){
						$(this).css("background-color", "#FFE1FF");
					}, function(){
						$(this).css("background-color", "#FFFCEA");
					});
				});
				$(".tr:even").each(function(){
					$(this).hover(function(){
						$(this).css("background-color", "#FFE1FF");
					}, function(){
						$(this).css("background-color", "#fff");
					});
				}); 
           }
		});
	}
	//初始化分页
	function initPager(ob,opts){
		 var total=parseInt(ob.totalProperty);
    	 var pagecount=parseInt(total%opts.limit==0?(total/opts.limit):(total/opts.limit+1));
    	$("#pager").pager({
   			pagenumber:(opts.offset/opts.limit+1),
   			pagecount:pagecount,
   			buttonClickCallback:PageClick
   		});
	}
	//分页点击事件
   PageClick = function(pageclickednumber,pagecount){
	   //根据点击的页码数pageclickednumber和传进来的limit,重新计算offset
		var _offset=(pageclickednumber-1)*_options.limit;
		if(pageclickednumber==1){
			_offset=0;
		}
		var _moptions=$.extend({},_options,{offset:_offset});
		getData(_this,_moptions);
	};
  //创建table, url:点击标题转向的地址目录
  //用其他的只需要重写此方法
	function createTable(ob,url){
		var _temp=ob.root;
		var $tbody="";
		if(_temp.length){
			for(var i=0;i<_temp.length;i++){
				var imgUrl=url+_temp[i].userpic;
				$tbody=$tbody+"<tr class='tr'>";
				$tbody=$tbody+"<td align='center'><input type='checkbox' name='user_id' value='"+_temp[i].userid+"'></td>";
				$tbody=$tbody+"<td align='center'><img src="+imgUrl+" width=25 height=25></td>";
				$tbody=$tbody+"<td align='center'>"+_temp[i].username+"</td>";
				$tbody=$tbody+"<td align='center'>"+_temp[i].sex+"</td>";
				$tbody=$tbody+"<td align='center'>"+(typeof(_temp[i].mobilphone)=='undefined'?'':_temp[i].mobilphone)+"</td>";
				$tbody=$tbody+"<td align='center'>"+(typeof(_temp[i].officephone)=='undefined'?'':_temp[i].officephone)+"</td>";
				$tbody=$tbody+"<td align='center'>"+(typeof(_temp[i].qq)=='undefined'?'':_temp[i].qq)+"</td>";
				$tbody=$tbody+"<td align='center'>"+(typeof(_temp[i].jifen)=='undefined'?'':_temp[i].jifen)+"</td>";
				$tbody=$tbody+"<td align='center'>"+(typeof(_temp[i].lastLogin)=='undefined'?'':_temp[i].lastLogin)+"</td>";
				$tbody=$tbody+"<td align='center'>"+(typeof(_temp[i].address)=='undefined'?'':_temp[i].address)+"</td>";
				$tbody=$tbody+"</tr>";
			}
		}
		 
	  return $tbody;	
	}
	
})(jQuery);