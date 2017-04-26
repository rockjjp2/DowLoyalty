$IntegralItem = function () {
    var _entity;
    this.Init = function () {
        _entity = this;
        var lis = $("#IntegralItemHome>ul li a");
        for (var i = 0; i < lis.size(); i++) {
            lis.eq(i).bind("mouseover", function () {
                _entity.ItemEvent_over(this);
            })

            lis.eq(i).bind("mouseout", function () {
                _entity.ItemEvent_out(this);
            })
        }
        _entity.changetitle();
    }

    this.ItemEvent_over = function (_this) {
        $(_this).attr("class", "checkimg");
        $(_this).find(".Sales-img").css("background-image", "url(../Resources/html/images/smallImg/Sales_checked.png)");
        $(_this).find(".detail-img").css("background-image", "url(../Resources/html/images/smallImg/Detail_checked.png)");
    }
    this.ItemEvent_out = function (_this) {
        $(_this).removeAttr("class", "checkimg");
        $(_this).find(".Sales-img").css("background-image", "url(../Resources/html/images/smallImg/Sales_before.png)");
        $(_this).find(".detail-img").css("background-image", "url(../Resources/html/images/smallImg/Detail.png)");
    }
    this.changetitle = function () {
        var itemps = $("#IntegralItemHome").find("p").not('.integralitem-content-title');
        var _Main = null;
        if (_Main == null) {
            _Main = new $main();
        }
        for (var i = 0; i < itemps.size(); i++) {
            itemps.eq(i).bind("click", function () {
                var tit = $(this).parent().find(".integralitem-content-title").html();
                $('#title', window.parent.document).html(tit);//为父页面的title赋值
            })
        }
      //  alert($(_this).parent("integralitem-content").html());
    }
   
}