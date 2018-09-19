$(document).ready(function(){
    // 查看首页产品详情
    $('.pdt-list').on("click", "li", function(){
    	var $this = $(this);
        switchNav("pdtDetail");
        getPdtDetail($this.attr("data-id"));
        getAdvancedProperty($this.attr("data-id"));
        $("#pdtDetailImg").attr("src", $this.attr("data-img"));

        var level1 = $this.attr("data-level1");
        var level2 = $this.attr("data-level2");
        var level3 = $this.attr("data-level3");
        $("#level1").html(level1);
        $("#level2").html(level2).show();
        if(!level2) $("#level2").hide();
        $("#level3").html(level3);
    });
    
    getRecomProducts();

    getCategoryList();

    $(".page-link").on("click", function(){
        var pageId = $(this).attr("data-pageid");
		switch (pageId){
			case "aboutUs":
                getArticles("ABOUTEUS", pageId);
				break;
            case "services":
                getArticles("CAREER", pageId);
                break;
            case "gallery":
                break;
            case "contactUs":
                getArticles("CONTACTUS", pageId);
                break;
			default:
				break;
		}
        flag = pageId == "pdtList" ? true : false;
        switchNav(pageId, flag);
    });
    
    $("#categoryNav").mouseleave(function(){
    	$(".category-list").slideUp(100).parent().addClass('arrow-up').removeClass('arrow-down').css("color", "");
    });
    $("#categoryNav").mouseenter(function(){
    	$(".category-list").slideDown(150).parent().addClass('arrow-down').removeClass('arrow-up').css("color", "#19ff00");
    });
    $(".category-list").on("click", ".item", function(e){
        $(".category-list").slideUp(100);
        queryProduct({category: $(this).attr("data-categoryid")}, $(this).attr("data-categoryname"));
        switchNav("pdtList");
        $("#listLevel2").html($(this).attr("data-categoryname"));
        if ( e && e.stopPropagation )
            e.stopPropagation();
        else
            window.event.cancelBubble = true;
    });
    $("a.arrow-right").click(function(){
        switchNav($(this).attr("data-pageid"));
    });
    $("#submitMessage").click(function () {
	    if(emptyCheck() || $("#submitMessage").hasClass("disabled")){
	        return false;
	    }
	    var eMail = $.trim($("#eMail").val());
	    var name = $.trim($("#name").val());
	    var phone = $.trim($("#phone").val());
	    var contry = $.trim($("#contry").val());
	    var companyName = $.trim($("#companyName").val());
	    var subject = $.trim($("#subject").val()); // required
	    var message = $.trim($("#message").val()); // required
	    $("#submitMessage").html("Submit...").addClass("disabled");
	    $.ajax({
	        url: MyUtil.getContext() + '/sendMsg.do',
	        type: "post",
	        data: {
	            message_person_email: eMail,
	            message_person: name,
	            message_person_tel: phone,
	            message_person_address: contry,
	            message_person_company: companyName,
	            message_title: subject,
	            message_content: message
	        },
	        async: true,
	        success: function (result) {
	            var data = eval("(" + result + ")");
	            if(data.success && !data.message){
	                $(".message-box input").val("");
	                $(".message-box textarea").val("");
	                $("#submitResult").html("Thanks for contacting us! We will get in touch with you shortly").removeClass("error");
	            } else {
	                $("#submitResult").html(data.message).addClass("error");
	            }
	            $("#submitMessage").html("Submit").removeClass("disabled");
	        }
	    });
	});

    $("#searchBtn").click(function(){
        var searchVal = $.trim($("#searchInput").val());
        if(searchVal !== ""){
            queryProduct({productName: searchVal});
            $("#listLevel2").html(searchVal);
            switchNav("pdtList");
        }
    });
});

function emptyCheck() {
    var isEmpty = false;
    $(".message-box .required").each(function () {
        if (!$.trim($(this).val())) {
            twinkle($(this));
            isEmpty = true;
            return false;
        }
    });
    return isEmpty;
}

function switchNav(pageId, flag){
    $(".page").scrollTop(0);
    var curEle = $(".page-link[data-pageid='"+pageId+"'] > a");
    if(curEle.length > 0){
        $(".page-link a").removeClass("active");
        $(".page-link[data-pageid='"+pageId+"'] > a").addClass("active");
    }
    pageId = flag ? "index" : pageId;
    $("#" + pageId).fadeIn(200).siblings(".content").hide();
}

function twinkle(target) {
    var count = 0;
    var timer = window.setInterval(function () {
        if (count % 2 == 0) {
            target.addClass("input-error");
        } else {
            target.removeClass("input-error");
        }
        count++;
        if(count > 5){
            target.focus();
            window.clearInterval(timer);
        }
    }, 300);
}

function getRecomProducts() {
    if($("#index").attr("data-iscache")) return;
    $.ajax({
        url: MyUtil.getContext() + '/getRecomProducts.do',
        type: "get",
        async: true,
        success: function (result) {
            var data = eval("(" + result + ")");
            if (data.success) {
                var pdtList = data.root;
                var listHtml = '';
                for (var i = 0; i < pdtList.length; i++) {
                    var item = pdtList[i];
                    var imgUrl = MyUtil.getContext() + item.picture_url;
                    var mailParams = "subject=Inquiry, " + item.product_name;
                    listHtml += '<li data-id="' + item.product_id + '" data-img="' + imgUrl + '" data-level1="HOME" data-level3="' + item.product_name + '">\
									<div class="img-box">\
										<img src="' + imgUrl + '" />\
									</div>\
									<div class="msg-box">\
										<div class="line"></div>\
										<p>'+ item.summary +'</p>\
										<span>' + item.product_name + '</span>\
									</div>\
								</li>';
                }
                $("#index .pdt-list").html(listHtml);
                $('#index .pdt-list li').css('height',$('#index .pdt-list li').css('width'));
                $("#index").attr("data-iscache", 1);
            }
        }
    });
}

function queryProduct(params, categoryName) {
    $("#pdtList .pdt-list").html("");
    $.ajax({
        url: MyUtil.getContext() + '/queryProducts.do',
        type: "post",
        data: {
            offset: params.offset || 0,
            limit: params.limit || 1000,
            product_category: params.category || "",
            product_name: params.productName || ""
        },
        async: true,
        success: function (result) {
            var data = eval("(" + result + ")");
            if (data.success) {
                var pdtList = data.root;
                var listHtml = '';
                for (var i = 0; i < pdtList.length; i++) {
                    var item = pdtList[i];
                    var imgUrl = MyUtil.getContext() + item.picture_url;
                    var mailParams = "subject=Inquiry, " + item.product_name;
                    listHtml += '<li data-id="' + item.product_id + '" data-img="' + imgUrl + '" data-level1="PRODUCTS" data-level2="'+categoryName+'" data-level3="' + item.product_name + '">\
									<div class="img-box">\
										<img src="' + imgUrl + '" />\
									</div>\
									<div class="msg-box">\
										<div class="line"></div>\
										<p>'+ item.summary +'</p>\
										<span>' + item.product_name + '</span>\
									</div>\
								</li>';
                }
                $("#pdtList .pdt-list").html(listHtml);
                $('#pdtList .pdt-list li').css('height',$('#pdtList .pdt-list li').css('width'));
            }
        }
    });
}

function getArticles(articleCategoryId, pageId) {
    if (!articleCategoryId || $("#" + pageId).attr("data-iscache")) return;
    $.ajax({
        url: MyUtil.getContext() + '/getArticles.do',
        type: "post",
        data: {
            article_categoryDM: articleCategoryId,
            offset: 0,
            limit: 1
        },
        async: true,
        success: function (result) {
            var data = eval("(" + result + ")");
            if (data.success) {
                $("#" + pageId).attr("data-iscache", 1);
                var dataList = data.root;
                var item = dataList.length > 0 ? dataList[0] : "";
                switch (articleCategoryId) {
                    case "CONTACTUS":
                        $("#contactUsContent").html(item.article_html);
                        break;
                    case "ABOUTEUS":
                        $("#aboutUsContent").html(item.article_html);
                        break;
                    case "CAREER":
                        $("#servicesContent").html(item.article_html);
                        break;
                    default:
                        break;
                }
            }
        }
    });
}

function getCategoryList() {
    $.ajax({
        url: MyUtil.getContext() + '/queryCategoryAll.do',
        type: "post",
        data: {},
        async: true,
        success: function (result) {
            var data = eval("(" + result + ")");
            if (data.success) {
                var cagetoryList = data.root;
                var listHtml = '';
                for (var i = 0; i < cagetoryList.length; i++) {
                    var item = cagetoryList[i];
                    listHtml += '<div data-categoryid="' + item.category_id + '" class="item" data-categoryname="' + item.category_name + '">' +
                                    '<a href="javascript:void(0)">' + item.category_name + '</a>' +
                                '</div>';
                }
                $(".category-list").html(listHtml);
            }
        }
    });
}

function getPdtDetail(pdtId) {
    $("#pdtName").html("Model No: ");
    $("#inStock").html("In Stock: ");
    $(".detail-head p").html("");
    $("#detailTitle p").html("");
    $("#customProperty").html("");
    $("#getPrice").html('');
    $.ajax({
        url: MyUtil.getContext() + '/getProductBasicById.do?objectId=' + pdtId,
        type: "get",
        async: true,
        success: function (result) {
            var data = eval("(" + result + ")");
            if (data.SUCCESS) {
                var pdt = data.root;
                var stock = pdt.stock > 0 ? "Yes" : "No";
                var cp = JSON.parse(pdt.propertyValues);
                var customProperty = "";
                for (var i = 0; i < cp.length; i++) {
                    customProperty += cp[i].property_value;
                }
                var warranty = pdt.warranty ? "Warranty: " + pdt.warranty : "";
                $("#pdtName").html("Model No: " + pdt.product_name);
                $("#inStock").html("In Stock: " + stock);
                $("#warranty").html(warranty);
                $("#detailTitle").html(pdt.product_title);
                if(pdt.summary){
                    $("#summary").html(pdt.summary).show();
                } else {
                    $("#summary").html("&nbsp;").hide();
                }
                if(pdt.product_spec){
                    $("#productSpec").html(pdt.product_spec).show();
                } else {
                    $("#productSpec").html("&nbsp;").hide();
                }
                if(customProperty){
                    $("#customProperty").html(customProperty).show();
                } else {
                    $("#customProperty").html("&nbsp;").hide();
                }
                var mailParams = "subject=Inquiry, " + pdt.product_name;
                $("#getPrice").html('<a href="mailto:info@yostarlighting.com?' + mailParams + '">GET PRICE</a>');
            }
        }
    });
}

function getAdvancedProperty(pdtId) {
    $.ajax({
        url: MyUtil.getContext() + '/getAdvancedProperty.do',
        type: "post",
        data: {product_id: pdtId},
        async: true,
        success: function (result) {
            var data = eval("(" + result + ")");
            if (data.success) {
                var ap = data.root;
                $(".detail .advance").html(ap.content_html);
            }
        }
    });
}

