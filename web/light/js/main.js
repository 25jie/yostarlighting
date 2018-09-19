getNewProduct();
getRecomProducts();
getCategoryList();
setNavStep();
// getPictures('1001');

var imageCategoryId = {
    index: '1001',
    gallery: '1002'
}
$("#menuBox").on('click', "li", function () {
    $(this).addClass("active").siblings("li").removeClass("active");
    setNavStep("pdtListContainer");
    $("#listTitle p").html($(this).text());
    queryProduct({category: $(this).attr("data-categoryid")});
});

$("#navBox li").click(function () {
    $(this).addClass("active").siblings("li").removeClass("active");
    setNavStep($(this).attr("data-nav"));
    getArticles($(this).attr("data-article"));
    if ($(this).attr("data-nav") == "galleryContainer") {
        getPictures(imageCategoryId.gallery);
    } else if($(this).attr("data-nav") == "contactUsContainer"){
        $("#submitResult").html("");
        $(".message-box input").val("");
        $(".message-box textarea").val("");
    }
});

$(".main-pdt-list").on("click", ".pdt-item", function () {
    setNavStep("pdtDetailContainer");
    var pdtId = $(this).attr("data-id");
    var imgUrl = $(this).attr("data-img");
    $("#pdtDetailImg").attr("src", imgUrl);
    getPdtDetail(pdtId);
    getAdvancedProperty(pdtId);
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

function setNavStep(curContainer) {
    $(".tab-box").hide();
    if (curContainer) {
        $("#navBox").attr("data-nav", curContainer);
    }
    $("#" + $("#navBox").attr("data-nav")).show();
}

function getNewProduct() {
    $.ajax({
        url: MyUtil.getContext() + '/queryProducts.do',
        type: "post",
        data: {
            offset: 0,
            limit: 1
        },
        async: true,
        success: function (result) {
            var data = eval("(" + result + ")");
            if (data.success) {
                var pdtList = data.root;
                if (pdtList.length > 0) {
                    var pdt = pdtList[0];
                    var imgUrl = MyUtil.getContext() + pdt.picture_url;
                    $("#newpdtimg").attr("src", MyUtil.getContext() + pdt.picture_url);
                    $("#newpdtsummary").html(pdt.summary);
                    $("#newpdtwarranty").html(pdt.product_spec);
                    $(".new-pdt").attr("data-id", pdt.product_id).attr("data-img", imgUrl);
                } else {
                    $("#newpdtimg").attr("src", "");
                    $("#newpdtsummary").html("暂无产品~");
                    $("#newpdtwarranty").html("");
                }
            }
        }
    });
}

function getRecomProducts() {
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
                    listHtml += '<li class="row">\
									<div class="col-md-4 img-box"><span><img src="' + imgUrl + '"/></span></div>\
									<div class="col-md-8 pdt-detail">\
										<div class="title">' + item.summary + '<br>' + item.product_spec + '<br></div>\
										<div class="price"><a href="mailto:info@yostarlighting.com?' + mailParams + '">Get price</a></div>\
									    <p class="pdt-item" data-id="' + item.product_id + '" data-img="' + imgUrl + '">Model: ' + item.product_name + ' <span>View Details</span></p>\
									</div>\
								</li>';
                }
                $("#recomPdtList").html(listHtml || "<br>暂无产品~");
            }
        }
    });
}

function queryProduct(params) {
    $.ajax({
        url: MyUtil.getContext() + '/queryProducts.do',
        type: "post",
        data: {
            offset: params.offset || 0,
            limit: params.limit || 1000,
            product_category: params.category || ""
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
                    listHtml += '<li class="row">\
									<div class="col-md-4 img-box"><span><img src="' + imgUrl + '"/></span></div>\
									<div class="col-md-8 pdt-detail">\
										<div class="title">' + item.summary + '<br>' + item.product_spec + '<br></div>\
										<div class="price"><a href="mailto:info@yostarlighting.com?' + mailParams + '">Get price</a></div>\
									    <p class="pdt-item" data-id="' + item.product_id + '" data-img="' + imgUrl + '">Model: ' + item.product_name + ' <span>View Details</span></p>\
									<div>\
								</li>';
                }
                $("#outherPdtList").html(listHtml || "<br>暂无产品~");
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
                    listHtml += '<li data-categoryid="' + item.category_id + '">' + item.category_name + '</li>';
                }
                $("#menuBox").html(listHtml);
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
                    customProperty += "<br>" + cp[i].property_value
                }
                var warranty = pdt.warranty ? "Warranty: " + pdt.warranty : "";
                $("#pdtName").html("Model No: " + pdt.product_name);
                $("#inStock").html("In Stock: " + stock);
                $(".detail-head p").html(warranty);
                $("#detailTitle p").html(pdt.product_title);
                $("#customProperty").html(pdt.summary + "<br>" + pdt.product_spec + customProperty);
                var mailParams = "subject=Inquiry, " + pdt.product_name;
                $("#getPrice").html('<a href="mailto:info@yostarlighting.com?' + mailParams + '">Get price</a>');
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
                $(".detail-more").html(ap.content_html);
            }
        }
    });
}

function getArticles(articleCategoryId) {
    if (!articleCategoryId) return;
    $.ajax({
        url: MyUtil.getContext() + '/getArticles.do',
        type: "post",
        data: {
            article_categoryDM: articleCategoryId,
            // needshow: 1,
            offset: 0,
            limit: 1
        },
        async: true,
        success: function (result) {
            var data = eval("(" + result + ")");
            if (data.success) {
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

var mySwiper = null;
function getPictures(categoryId) {
    if (mySwiper) return;
    $.ajax({
        url: MyUtil.getContext() + '/getPictures.do',
        type: "post",
        data: {
            picture_category_id: categoryId,
            offset: 0,
            limit: 100
        },
        async: true,
        success: function (result) {
            var data = eval("(" + result + ")");
            if (data.success) {
                var dataList = data.root;
                if (categoryId == imageCategoryId.index) {
                    var item = dataList.length > 0 ? dataList[0] : "";
                    $("#mainrecomimg").attr("src", MyUtil.getContext() + item.pucture_url);
                } else if (categoryId == imageCategoryId.gallery) {
                    var listHtml = '';
                    for (var i = 0; i < dataList.length; i++) {
                        var item = dataList[i];
                        listHtml += '<div class="swiper-slide">\
                                        <div class="img-container">\
                                            <img src="' + MyUtil.getContext() + item.pucture_url + '">\
                                        </div>\
                                    </div>';
                    }
                    $(".swiper-wrapper").html(listHtml);
                    if (listHtml) {
                        $("#galleryContent").show();
                        initSwiper();
                    } else {
                        $("#galleryContent").hide();
                    }
                }
            }
        }
    });
}

function initSwiper() {
    if (mySwiper) {
        return;
    }
    mySwiper = new Swiper('.swiper-container', {
        // direction: 'vertical',
        loop: true,
        autoplay: true,
        // 如果需要分页器
        pagination: {
            el: '.swiper-pagination'
        }
        // 如果需要前进后退按钮
        // navigation: {
        //     nextEl: '.swiper-button-next',
        //     prevEl: '.swiper-button-prev'
        // },
        // 如果需要滚动条
        // scrollbar: {
        //     el: '.swiper-scrollbar'
        // }
    })
}