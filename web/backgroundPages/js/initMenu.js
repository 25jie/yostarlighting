
$(document).ready(function(){
	initMuneTab();
	 function initMuneTab(){
		   $.ajax({
			   url:MyUtil.getContext()+"getMenu.do",
			   type:"post",
			   data:{
				  "navigation_type":"HT",
				  "navigation_parent":-1
			   },
	           async:true,
	           success:function(result){
	        	  //$("#sideMenu").html(result);
	        	 var _menus=eval("("+result+")");
	        	 if(_menus.length){
	        		 var _html="";
	        		 for(var i=0;i<_menus.length;i++){
	        			 var _menu=_menus[i];
	        			 var _subMenus=_menu.subMenus;
	        			 _html+="<h3>"+_menu.navigation_name+"</h3>";
	        			 if(_subMenus.length){
	        				 _html+="<ul>";
	        				 for(var k=0;k<_subMenus.length;k++){
	        					 var _subMenu=_subMenus[k];
	        					 _html+="<li   onclick='expendMenu(this,\""+_subMenu.navigation_url+"\")'>"+_subMenu.navigation_name+"</li>";
	        				 }
	        				 _html+="</ul>";
	        			 }
	        		 }
	        		 $("#sideMenu").html(_html);
	        	 }else{
	        		 $("#sideMenu").html("<span>菜单加载错误</span>");
	        	 } 
	        	 
	        	 
	        	  $(".sideMenu").slide({
	        	         titCell:"h3", 
	        	         targetCell:"ul",
	        	         defaultIndex:0, 
	        	         effect:'slideDown', 
	        	         delayTime:'500' , 
	        	         trigger:'click', 
	        	         triggerTime:'150', 
	        	         defaultPlay:true, 
	        	         returnDefault:false,
	        	         easing:'easeInQuint',
	        	         startFun:function(){
	        	        	// initMuneTab(); 
	        	         },
	        	         endFun:function(){
	        	              scrollWW();
	        	         }
	        	       });
	        	
	          }
		   });
		   
	   }

	 
      $(window).resize(function() {
          scrollWW();
      });
      
      
     
});
//给菜单添加点击事件，点击菜单后跳转到相应页面
function  expendMenu(_this,url){
	$("#menu_div li").removeClass("on");
		var menuName=$(_this).html();
		$("#dqwz").html(menuName);
		$("#rightMain").attr("src",url);
		$(_this).addClass("on");
	
}
function scrollWW(){
    if($(".side").height()<$(".sideMenu").height()){
       $(".scroll").show();
       var pos = $(".sideMenu ul:visible").position().top-38;
       $('.sideMenu').animate({top:-pos});
    }else{
       $(".scroll").hide();
       $('.sideMenu').animate({top:0});
       n=1;
    }
  } 

var n=1;
function menuScroll(num){
  var Scroll = $('.sideMenu');
  var ScrollP = $('.sideMenu').position();
  /*alert(n);
  alert(ScrollP.top);*/
  if(num==1){
     Scroll.animate({top:ScrollP.top-38});
     n = n+1;
  }else{
    if (ScrollP.top > -38 && ScrollP.top != 0) { ScrollP.top = -38; }
    if (ScrollP.top<0) {
      Scroll.animate({top:38+ScrollP.top});
    }else{
      n=1;
    }
    if(n>1){
      n = n-1;
    }
  }
}
