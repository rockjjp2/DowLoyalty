$main = function () {
    var _entity;
    this.Init = function () {
        _entity = this;
        _entity.MenuChange();
        _entity.subMenuChange();
        _entity.height();

        ///*浏览器放大缩小事件*/
        //$(window).resize(function () { _entity.height(); });
    }

    //主菜单切换
    this.MenuChange = function () {
        var menu_lis = $(".header-menu-ul >li");
        for (var i = 0; i < menu_lis.size() - 1; i++) {
            menu_lis.eq(i).bind("click", function () {
                //清除其他兄弟标签的选择状态
                $(this).siblings().removeAttr("class");
                $(this).siblings().find(".Bubble-menu").css("display", "none");
                //绑定菜单选中状态
                $(this).attr("class", "menu-active");
                $(this).find(".Bubble-menu").css("display", "block");

              
                //判断当前主菜单下是否有子菜单(无)
                if ($(this).find("li").size() <= 0) {
                    //获得当前选中菜单的名称
                    var title = $(this).find("span").html();
                    $("#title").html(title);
                    var Id = "";
                    Id = $(this).attr("id");
                    //设置iframe跳转页面
                    $("#iframeContent").attr("src", Id + ".html");
                }
                
            });
        }
    }
    //子菜单切换
    this.subMenuChange = function () {
        var menu_lis = $(".cui-bubble-layer >li");
        for (var i = 0; i < menu_lis.size() ; i++) {
            menu_lis.eq(i).bind("click", function () {
                var _innerhtml = $(this).find(".subtit").html();
                $(this).siblings().html("<span  class='subtit'>" + _innerhtml + "</span>");
                $(this).html("<span>&gt;</span><span  class='subtit'>" + _innerhtml + "</span>");
                var Id = $(this).attr("id");
                
                //设置iframe跳转页面
                $("#iframeContent").attr("src", Id + ".html");
            });
        }
    }

    this.height = function () {
        var height = $("body").height() - $("#header-flickr").height();
        height = height < 600 ? 600 : height;
        $("#content").height(height);
    };
}