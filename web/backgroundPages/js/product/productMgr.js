$(document).ready(function () {
    $("#productTbody").initProducts({
        offset: 0,
        limit: 5,
        action: MyUtil.getContext() + "queryProductBasics.do",
        showNews: MyUtil.getContext()
    });
    $(".list_table").colResizable({
        liveDrag: true,
        gripInnerHtml: "<div class='grip'></div>",
        draggingClass: "dragging",
        minWidth: 30
    });

    //删除
    $("#delBtn").del({
        CheckBoxName: "product_id",
        ACTION: MyUtil.getContext() + "deleteProductBasics.do"
    });
    //新增
    $("#addBtn").click(function () {
        window.location.href = MyUtil.getContext() + "backgroundPages/jsp/product/editProduct.jsp";
    });
    //编辑
    $("#editBtn").click(function () {
        var _ckid = "";
        var productCheckeds = $("input[name='product_id']:checked");
        if (productCheckeds.length < 1) {
            alert("请选择一个对象");
            return;
        }
        if (productCheckeds.length > 1) {
            alert("不支持批量编辑，请确保您只选择了一个对象");
            return;
        }
        $("input[name='product_id']:checked").each(function () {
            _ckid = this.value;
            return;
        });
        if (_ckid == "") {
            alert("请选择一个对象");
            return;
        }
        window.location.href = MyUtil.getContext() + "backgroundPages/jsp/product/editProduct.jsp?objectId=" + _ckid;
    });
    //全选
    $("#ckAll").click(function () {
        $(this).hide();
        $("#ckNo").show();
        $("input[type='checkbox'][name='product_id']").attr("checked", true);
    });
    //取消
    $("#ckNo").click(function () {
        $(this).hide();
        $("#ckAll").show();
        $("input[type='checkbox'][name='product_id']").attr("checked", false);
    });
});

function queryDetail(obj) {
    var productId = $(obj).attr("productId");
    window.location.href = MyUtil.getContext() + "backgroundPages/jsp/product/productDetail.jsp?objectId=" + productId;
}