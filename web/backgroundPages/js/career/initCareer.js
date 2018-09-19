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
	$.fn.initCareer=function(options){
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
            	   "article_categoryDM":"CAREER"
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
				$tbody=$tbody+"<tr class='tr'>";
				$tbody=$tbody+"<td align='center'><input type='checkbox' name='article_id' value='"+_temp[i].article_id+"'></td>";
				$tbody=$tbody+"<td align='center'>"+(typeof(_temp[i].article_pubtime)=='undefined'?'':_temp[i].article_pubtime)+"</td>";
				$tbody=$tbody+"<td align='center'>"+(typeof(_temp[i].article_title)=='undefined'?'':_temp[i].article_title)+"</td>";
				$tbody=$tbody+"<td align='center'>"+(typeof(_temp[i].article_author)=='undefined'?'':_temp[i].article_author)+"</td>";
				if(_temp[i].needshow=='Y'){
					$tbody=$tbody+"<td align='center'>已经顶置</td>";
				}else{
					$tbody=$tbody+"<td align='center'><a href='javascript:void(0)' onclick='useArticle(\""+_temp[i].article_id+"\",this)'>顶置</a></td>";	
				}
				$tbody=$tbody+"</tr>";
			}
		}
		 
	  return $tbody;	
	}
	
})(jQuery);