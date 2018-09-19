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
	$.fn.initNews=function(options){
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
				var redUrl=url+_temp[i].news_id;
				if(i%2==0){
				$tbody=$tbody+"<tr class='odd' style='background-color:#FFFCEA;font-size:14px;font-style:normal;font-family:Arial;'>";
				}else{
			    $tbody=$tbody+"<tr class='even' style='background-color:#fff;font-size:14px;font-style:normal;font-family:Arial;' >";	
				}
				$tbody=$tbody+"<td align='center'><input type='checkbox' name='news_id' value='"+_temp[i].news_id+"'></td>";
				if(_temp[i].news_show=='1'){
					$tbody=$tbody+"<td align='center'><img src='backgroundPages/images/ic_lock.png' title='取消展示' alt='取消展示' onclick='cancelZs(\""+_temp[i].news_id+"\",this)' style='cursor:pointer;'></td>";
				}else{
					$tbody=$tbody+"<td align='center'><img src='backgroundPages/images/ic_unlock.png' title='展示' alt='展示' onclick='setZs(\""+_temp[i].news_id+"\",this)' style='cursor:pointer;'></td>";
				}
				$tbody=$tbody+"<td align='center'>"+_temp[i].news_pubtime+"</td>";
				$tbody=$tbody+"<td align='center'>"+_temp[i].news_typeName+"</td>";
				$tbody=$tbody+"<td align='left'>&nbsp;&nbsp;&nbsp;&nbsp;<a href='"+redUrl+"'>"+_temp[i].news_title+"</a></td>";
				$tbody=$tbody+"</tr>";
			}
		}
		 
	  return $tbody;	
	}
	
})(jQuery);