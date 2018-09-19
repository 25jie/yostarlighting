var tempid=new Array();
$(document).ready(function(){
	
	var _menuType=MyUtil.getRequestParams("menuType");
	var expendStrP=MyUtil.getRequestParams("expendStr");
	  if(typeof(_menuType)=="undefined"||_menuType==null){
		  initNavigation("HT");   
	  }else{
		  $("#select_type").val(_menuType);
		  initNavigation(_menuType); 
	  }
	
	function initNavigation(type){
		$.post("getMenu.do",
				{"navigation_type":type,"navigation_parent":-1},
				function(result){
			    var _menus=eval("("+result+")");
                var _html="<tr><th width='150'>名称</th><th width='50'></th><th width='200'>描述</th><th>连接地址</th><th width='100'>排序</th><th width='200'>操作</th></tr>";
                  //下拉菜单
                var _Select="<option value='-1'>----------</option>";
			    if(_menus.length){
			    	var _len=_menus.length;
			    	for(var i=0;i<_len;i++){
			    		var _menu=_menus[i];
			    		var _mid=_menu.navigation_id;
			    		var _pid=_menu.navigation_parent;
			    		tempid.push(_mid);
			    		_html+="<tr class='tr' id='p_"+_mid+"'>";
			    		_html+="<td class='td_left' style='font-weight:800;'>&nbsp;&nbsp;<img src='backgroundPages/images/elbow-end-plus-nl.gif' style='cursor:pointer;' onclick='expendChild(\""+_mid+"\")'>"+_menu.navigation_name+"</td>";
			    		if(_menu.navigation_other=="1"){
			    			_html+="<td align='center'><img src='backgroundPages/images/ic_lock.png' title='取消展示' alt='取消展示' onclick='cancelZs(\""+_pid+"\",\""+_mid+"\",this)' style='cursor:pointer;'></td>";
			    		}else{
			    			_html+="<td align='center'><img src='backgroundPages/images/ic_unlock.png' title='展示' alt='展示' onclick='setZs(\""+_pid+"\",\""+_mid+"\",this)' style='cursor:pointer;'></td>";
			    		}
			    		_html+="<td>"+_menu.navigation_desc+"</td>";
			    		_html+="<td>"+_menu.navigation_url+"</td>";
			    		_html+="<td class='td_center'>"+(i>0?"<a href='javascript:void(0)'><img src='backgroundPages/images/ic_up.png' title='上移' onclick='moveUp(this)'></a>&nbsp;&nbsp;":"")+(i<_len-1?"<a href='javascript:void(0)' ><img src='backgroundPages/images/ic_down.png' title='下移' onclick='moveDown(this)'></a>":"")+"</td>";
			    		_html+="<td><a href='javascript:void(0)' onclick='editMenu(\""+_mid+"\")'>[编辑]</a><a href='javascript:void(0)'  onclick='delMenu(\""+_mid+"\",this)'>[删除]</a><a href='javascript:void(0)' onclick='addSubMenu(\""+_mid+"\")'>[添加子菜单]</a></td>";
			    		_html+="</tr>";
			    		_Select+="<option value='"+_mid+"'>"+_menu.navigation_name+"</option>";
			    		//添加子菜单
			    		var _subMenus=_menu.subMenus;
			    		if(_subMenus.length){
			    			var _sLen=_subMenus.length;
			    			for(var k=0;k<_sLen;k++){
			    				var _subMenu=_subMenus[k];
			    				var _subMid=_subMenu.navigation_id;
			    				_html+="<tr class='tr "+_mid+"' id='c_"+_subMid+"' style='display:none;'>";
			    				_html+="<td class='td_left' style='font-weight:400;'>&nbsp;&nbsp;&nbsp;&nbsp;<img src='backgroundPages/images/elbow.gif'>"+_subMenu.navigation_name+"</td>";
			    				if(_subMenu.navigation_other=="1"){
					    			_html+="<td align='center'><img src='backgroundPages/images/ic_lock.png' title='取消展示' alt='取消展示' onclick='cancelZs(\""+_mid+"\",\""+_subMid+"\",this)' style='cursor:pointer;'></td>";
					    		}else{
					    			_html+="<td align='center'><img src='backgroundPages/images/ic_unlock.png' title='展示' alt='展示' onclick='setZs(\""+_mid+"\",\""+_subMid+"\",this)' style='cursor:pointer;'></td>";
					    		}
			    				_html+="<td>"+_subMenu.navigation_desc+"</td>";
			    				_html+="<td>"+_subMenu.navigation_url+"</td>";
					    		_html+="<td class='td_center'>"+(k>0?"<a href='javascript:void(0)' ><img src='backgroundPages/images/ic_up.png' title='上移' onclick='moveUp(this)'></a>&nbsp;&nbsp;":"")+(k<_sLen-1?"<a href='javascript:void(0)' ><img src='backgroundPages/images/ic_down.png' title='下移' onclick='moveDown(this)'></a>":"")+"</td>";
					    		_html+="<td><a href='javascript:void(0)' onclick='editMenu(\""+_subMenu.navigation_id+"\")'>[编辑]</a>&nbsp;&nbsp;<a href='javascript:void(0)' onclick='delMenu(\""+_subMenu.navigation_id+"\",this)'>[删除]</a></td>";
			    				_html+="</tr>";
			    			}
			    		}
			    	}
			    	
			    }
			    $("#navTbody").empty().html(_html); 
			    $("#parentMid").empty().html(_Select);
			    
			   // alert(expendStrP);
				if(expendStrP!=null){
					var ids=expendStrP.split("$");
					if(ids.length){
						for(var i=0;i<ids.length;i++){
							expendChild(ids[i]);
						}
					}
				}
			    //表格可以拖动
			    $(".list_table").colResizable({
			          liveDrag:true,
			          gripInnerHtml:"<div class='grip'></div>", 
			          draggingClass:"dragging", 
			          minWidth:30
			        }); 
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

			    
			        
		});
	    
		
	}
	
	//添加选择事件
	$("#select_type").change(function(){
		initNavigation($(this).val());
	});
	
});
var expend_id=new Array();
Array.prototype.indexOf = function(val) {              
    for (var i = 0; i < this.length; i++) {  
        if (this[i] == val) return i;  
    }  
    return -1;  
};  
Array.prototype.remove = function(val) {  
    var index = this.indexOf(val);  
    if (index > -1) {  
        this.splice(index, 1);  
    }  
}; 
//展开子菜单
function expendChild(parentId){
	var _f=true;
	var _img=$("#p_"+parentId).find("img").first();
	$("."+parentId).each(function(){
		if($(this).is(":hidden")){
			$(this).show();
			if(_f){
				_img.attr("src","backgroundPages/images/elbow-minus-nl.gif");
				expend_id.push(parentId);
				_f=false;
			}
		}else{
			$(this).hide();
			if(_f){
				_img.attr("src","backgroundPages/images/elbow-end-plus-nl.gif");
				expend_id.remove(parentId);
				_f=false;
			}
		}
	});
	//alert("1-->"+expend_id);
}
//编辑菜单
function editMenu(objectid){
	resetMenuForm();
	$.get("getMenuById.do",{"objectid":objectid},function(result){
		  var _ob=eval("("+result+")");
		   $("#menuName").val(_ob.navigation_name);
		   $("#parentMid").val(_ob.navigation_parent);
		   $("#menuUrl").val(_ob.navigation_url);
		   $("#menuID").val(_ob.navigation_id);
		   $("#menuDetail").val(_ob.navigation_desc);
		   popWin("detail");
	});
}

//保存菜单
function saveMenu(){
	var expendStr="";
	if(expend_id.length>0){
		expendStr=expend_id.join("$");
	}
	if($("#menuName").val()==""){
		alert("名称不能为空！");
		return;
	}
	$.post("saveMenu.do",
	   {"navigation_name":$("#menuName").val(),
		"navigation_parent":$("#parentMid").val(),
		"navigation_url":$("#menuUrl").val(),
		"navigation_id":$("#menuID").val(),
		"navigation_type":$("#select_type").val(),
		"navigation_desc":$("#menuDetail").val()
	    },function(result){
		var _ret=eval("("+result+")");
		alert(_ret.message);
		if(_ret.SUCCESS){
			$("#detail").hide();
			$("#maskLayer").remove();
			window.location.href=MyUtil.getContext()+"backgroundPages/jsp/navigation/navigation.jsp?expendStr="+expendStr+"&menuType="+$("#select_type").val();
		}
	});
	
}

//删除菜单
function delMenu(objectid,e){
	
	if(window.confirm("将会把子菜单一起删除，确定还要删除吗？")){
		$.get("delMenu.do",{"objectid":objectid},function(result){
			var ret=eval("("+result+")");
			alert(ret.message);
			if(ret.SUCCESS){
				$(e).parent().parent().remove();
				//把子元素也移除
				$("."+objectid).each(function(){
					$(this).remove();
				});
			}
		});
		
	}
}

//添加菜单
function addMenu(){
	resetMenuForm();
	popWin("detail");
}
//添加子菜单
function addSubMenu(objectid){
	resetMenuForm();
	 $("#parentMid").val(objectid);
	 popWin("detail");
}
//重置表单
function resetMenuForm(){
	   $("#menuName").val("");
	   $("#parentMid").val("-1");
	   $("#menuUrl").val("");
	   $("#menuID").val("");
}
//取消
function hide(){
	resetMenuForm();
	$("#detail").hide();
	$("#maskLayer").remove();
}

//上移
function moveUp(obj){
	//暂时容器
	var _tr=$(obj).parent().parent().parent();
	var _id=_tr.attr("id");
	var _a=_id.split("_");
	var _type=_a[0];//类型用来区分是父亲还是孩子
	var _mid=_a[1];//id
	
	if(_type=="p"){
		var _t=tempid.indexOf(_mid);
		var _oid=tempid[_t-1];
		replaceNav(_mid,_oid);
	}else if(_type=="c"){
		var _preTr=_tr.prev();
		var _tid=_preTr.attr("id");
		var _oid=_tid.split("_")[1];
		replaceNav(_mid,_oid);
	}
	
}

//下移
function moveDown(obj){
	//暂时容器
	var _tr=$(obj).parent().parent().parent();
	var _id=_tr.attr("id");
	var _a=_id.split("_");
	var _type=_a[0];//类型用来区分是父亲还是孩子
	var _mid=_a[1];//id
	if(_type=="p"){
		var _t=tempid.indexOf(_mid);
		var _oid=tempid[_t+1];
		replaceNav(_mid,_oid);
	}else if(_type=="c"){
		var _preTr=_tr.next();
		var _tid=_preTr.attr("id");
		var _oid=_tid.split("_")[1];
		replaceNav(_mid,_oid);
	}
}
/*
 * id1,id2:要交换的元素id
 */
function replaceNav(id1,id2){
	var expendStr="";
	if(expend_id.length>0){
		expendStr=expend_id.join("$");
	}
	//alert(expendStr);
	window.location.href=MyUtil.getContext()+"replaceNavigetion.do?firstNav="+id1+"&secendNav="+id2+"&expendStr="+expendStr+"&menuType="+$("#select_type").val();
	//$.get('replaceNavigetion.do',{'firstNav':id1,'secendNav':id2,'expendStr':expendStr},function(result){});
}

function cancelZs(pid,id,obj){
	$.post('saveMenu.do',{'navigation_parent':pid,'navigation_id':id,'navigation_other':'0'},function(result){
		 var _ret=eval('('+result+')');
		 if(_ret.SUCCESS){
			 alert('设置成功！');
			$(obj).attr('src','backgroundPages/images/ic_unlock.png');
		 }else{
			 alert('设置失败');
		 }
	});
}
function setZs(pid,id,obj){
	$.post('saveMenu.do',{'navigation_parent':pid,'navigation_id':id,'navigation_other':'1'},function(result){
		 var _ret=eval('('+result+')');
		 if(_ret.SUCCESS){
			 alert('设置成功！');
			 $(obj).attr('src','backgroundPages/images/ic_lock.png');
		 }else{
			 alert('设置失败');
		 }
	});
}