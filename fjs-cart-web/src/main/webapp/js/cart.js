var CART = {
    itemNumChange: function () {
        $(".increment").click(function () {//＋
            var _thisInput = $(this).siblings("input");
            _thisInput.val(eval(_thisInput.val()) + 1);
            $.post("/cart/update/num/" + _thisInput.attr("itemId") + "/" + _thisInput.val() + ".action", function (data) {
                CART.refreshTotalPrice();
            });
        });
        $(".decrement").click(function () {//-
            var _thisInput = $(this).siblings("input");
            if (eval(_thisInput.val()) == 1) {
                return;
            }
            _thisInput.val(eval(_thisInput.val()) - 1);
            $.post("/cart/update/num/" + _thisInput.attr("itemId") + "/" + _thisInput.val() + ".action", function (data) {
                CART.refreshTotalPrice();
            });
        });
        $(".itemnum").change(function () {
            var _thisInput = $(this);
            $.post("/cart/update/num/" + _thisInput.attr("itemId") + "/" + _thisInput.val() + ".action", function (data) {
                CART.refreshTotalPrice();
            });
        });
    },
    refreshTotalPrice: function () { //重新计算总价
        var total = 0;
        $(".itemnum").each(function (i, e) {
            var _this = $(e);
            total += (eval(_this.attr("itemPrice")) * 10000 * eval(_this.val())) / 10000;
        });
        $("#allMoney2").html(new Number(total / 100).toFixed(2)).priceFormat({ //价格格式化插件
            prefix: '¥',
            thousandsSeparator: ',',
            centsLimit: 2
        });
    }
};

$(function () {
    CART.itemNumChange();
});

$(function () {
    //实现全选
//1.获取第一个cb
    document.getElementById("Zall").onclick = function () {
        //2.获取下边列表中所有的cb
        var cbs = document.getElementsByName("cart_list");
        var cbs2 = document.getElementsByName("cart_list_yx");
        //3.遍历
        for (var i = 0; i < cbs.length; i++) {
            //4.设置这些cbs[i]中的checke状态= firstCb.checked
            cbs[i].checked = this.checked;
        }
        for (var i = 0; i < cbs2.length; i++) {
            //4.设置这些cbs[i]中的checke状态= firstCb.checked
            cbs2[i].checked = this.checked;
        }
    }
});