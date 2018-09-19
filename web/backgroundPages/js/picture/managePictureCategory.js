$(document).ready(function(){
    	//初始化
    	$.ajax({
    		url:MyUtil.getContext()+"getPictureCategorys.do",
    		data:{
    		  offset:0,
    		  limit:20
    		},
    		type:"post",
    		async:true,
            success:function(result){
            	var ob=eval("("+result+")");
            	 if(ob.length){
            		 var $tbody="";
            		 for(var i=0;i<ob.length;i++){
      			        if(i%2==0){
      					$tbody=$tbody+"<tr class='odd' style='background-color:#FFFCEA;font-size:14px;font-style:normal;font-family:Arial;'>";
      					}else{
      				    $tbody=$tbody+"<tr class='even' style='background-color:#fff;font-size:14px;font-style:normal;font-family:Arial;' >";	
      					}
      					$tbody=$tbody+"<td align='center'><input type='checkbox' name='news_id' value='"+ob[i].picture_cate_id+"'></td>";
      					$tbody=$tbody+"<td align='center'>"+ob[i].picture_cate_name+"</td>";
      					$tbody=$tbody+"<td align='center'>"+ob[i].picture_cate_sort+"</td>";
      					$tbody=$tbody+"<td align='center'>"+(typeof(ob[i].picture_cate_describe)=='undefined'?'':ob[i].picture_cate_describe)+"</td>";
      					$tbody=$tbody+"<td align='center'><a href='javascript:void(0)' onclick='edit(this)'>编辑</a></td>";
      					$tbody=$tbody+"</tr>";	 
            		 }
            		 $("#Tbody").append($tbody);
            	 }
            }
    	});
    	
    	$("#addBtn").click(function(){
    		 //先获取最大sort
    		 $.ajax({
    			 url:MyUtil.getContext()+"getPicMaxSort.do",
    			 data:{},
    			 type:"post",
    	      	 async:true,
    	         success:function(result){
    	        	var re=eval("("+result+")");
    	        	var rs=re.MaxSort;
    	        	if(rs==null||rs=="null"){
    	        		rs=0;
    	        	}
    	        	var maxSort=rs+1;
    	        	var ss="<tr><td><input type='checkbox' name='news_id' value='-1'></td><td><input type='text' name='ctname' class='input-text lh30'></td><td><input type='text' name='ctsort' value='"+maxSort+"' class='input-text lh30' size='10'></td><td><input type='text' name='ctdetail' class='input-text lh30' size='100'></td><td><a href='javascript:void(0)' onclick='save(this)'>保存</a></td></tr>";
    	    		 $("#Tbody").append(ss);
    	   	    	}
    		 });	
    	});
    	//删除
    	$("#delBtn").del({
    		CheckBoxName:"news_id",
    		ACTION:MyUtil.getContext()+"deletePictureCatergory.do"
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
    //点击保存
    function save(obj){
    	var _otr=$(obj).parent().parent();
    	var cid=_otr.find("input[name='news_id']").val();
    	var ctname=_otr.find("input[name='ctname']").val();
   	    var ctsort=_otr.find("input[name='ctsort']").val();
   	    var ctdetail=_otr.find("input[name='ctdetail']").val();
   	    if(ctname==""){
   	    	alert("名称不能为空！");
   	    	return;
   	    }
   	    $.ajax({
   	    	url:MyUtil.getContext()+"saveOrUpdatePictureCatergory.do",
   	    	data:{
   	    	 "picture_cate_id":cid,
      		 "picture_cate_name":ctname,
      		 "picture_cate_sort":ctsort,
      		 "picture_cate_describe":ctdetail
      		},
      		type:"post",
      		async:true,
            success:function(result){
   	    		var ob=eval("("+result+")");
   	    		var _tr="";
   	    		//计算第几个tr
   	    		var i=$("#Tbody").find("tr").length;
   	    		if(i%2!=0){
 	    		 _tr=_tr+"<tr class='odd' style='background-color:#FFFCEA;font-size:14px;font-style:normal;font-family:Arial;'>";
				}else{
				_tr=_tr+"<tr class='even' style='background-color:#fff;font-size:14px;font-style:normal;font-family:Arial;' >";	
				}
   	    		_tr=_tr+"<td align='center'><input type='checkbox' name='news_id' value='"+ob.picture_cate_id+"'></td>";
   	    		_tr=_tr+"<td align='center' width='100'>"+ob.picture_cate_name+"</td>";
   	    		_tr=_tr+"<td align='center' width='100'>"+ob.picture_cate_sort+"</td>";
   	    		_tr=_tr+"<td align='center'>"+ob.picture_cate_describe+"</td>";
   	    		_tr=_tr+"<td align='center' width='100'><a href='javascript:void(0)' onclick='edit(this)'>编辑</a></td>";
   	    		_tr=_tr+"</tr>";
   	    	  //先把自己移除
   	    	//	_otr.remove();
   	    	// $("#Tbody").append(_tr);
   	    	 //替换
   	    	 _otr.replaceWith(_tr);
   	    	}
   	    });
    }
  //点击编辑
    function edit(obj){
 	   var _otr=$(obj).parent().parent();
 	   var _td1= _otr.find("td:eq(1)");
 	   var _td2= _otr.find("td:eq(2)");
 	   var _td3= _otr.find("td:eq(3)");
 	   var _td4= _otr.find("td:eq(4)");
 	   var t1=_td1.text();
 	   var t2=_td2.text();
 	   var t3=_td3.text();
 	  _td1.empty().html("<input type='text' name='ctname' value='"+t1+"' class='input-text lh30' size='15'>");
 	  _td2.empty().html("<input type='text' name='ctsort' value='"+t2+"' class='input-text lh30' size='10'>");
 	  _td3.empty().html("<input type='text' name='ctdetail' value='"+t3+"' class='input-text lh30' size='100'>");
 	  _td4.empty().html("<a href='javascript:void(0)' onclick='save(this)'>保存</a>");
    }