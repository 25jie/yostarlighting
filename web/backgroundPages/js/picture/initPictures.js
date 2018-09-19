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
	$.fn.initPictures=function(options){
		_options=options;
		var defaults={
				offset:0,
				limit:8
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
            	   "picture_category_id":opts.picture_category_id
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
				var _pic=_temp[i];
				var imgUrl=MyUtil.getContext()+_pic.pucture_url;
				var showUrl=imgUrl.replace("uploadPicture","thumbPicture");
				$tbody+="<div class='imgbox'>";
				$tbody+="<div class='imgbox_inner'>";
				$tbody+="<img src='"+showUrl+"'>";
				$tbody+="</div>";
				$tbody+="<p><a href='javascript:void(0)' class='cut' onclick='copy_clip(\""+imgUrl+"\")' title='复制图片地址'></a><a class='big' href='"+imgUrl+"' title='浏览图片' target='blank'></a><input type='checkbox' name='picture_id' value='"+_pic.picture_id+"'></p>";
				$tbody+="</div>";
			}
		}
		 
	  return $tbody;	
	}
	
})(jQuery);