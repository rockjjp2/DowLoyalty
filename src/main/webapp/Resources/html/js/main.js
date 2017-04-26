$main = function () {

    var _entity;
    this.Init = function () {
        _entity = this;
        _entity.MenuChange();
        _entity.subMenuChange();
       // _entity.height();再次处调用只会执行一次,切换列表时不能重新获得iframe内的高度,这样不可行
      

        ///*浏览器放大缩小事件*/
        //$(window).resize(function () { _entity.height(); });
    }
   

    //主菜单切换
    this.MenuChange = function () {
        var menu_lis = $(".header-menu-ul >li");
        for (var i = 0; i < menu_lis.size() - 1; i++) {
            menu_lis.eq(i).bind("click", function () { //click
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
                    $("#iframeContent").attr("src", Id );
                }
            });
        }


        //子菜单的控制(销售记录)
        $("#Sales").bind("mouseover", function () {
            $("#menu-Sales").css("display", "block");
        })
        $("#menu-Sales").bind("mouseout", function () {
            $("#menu-Sales").css("display", "none");
        })
        $("#Sales").bind("mouseout", function () {
            $("#menu-Sales").css("display", "none");
        })
      
      
        //子菜单的控制(账户登出)

        $("#loginout").bind("mouseover", function () {
            $("#menu-loginout").css("display", "block");
        })

        //$("#menu-loginout").bind("mouseout", function () {
        //    $("#menu-loginout").css("display", "none");
        //})
        //$("#haount_name").bind("mouseout", function () {
        //    $("menu-loginout").css("display", "none");
        //})

        $("#loginout").bind("mouseout", function () {
            $("#menu-loginout").css("display", "none");
        })
    
    }
    //子菜单切换
    this.subMenuChange = function () {
        var menu_lis = $(".cui-bubble-layer >li");
        for (var i = 0; i < menu_lis.size()-1 ; i++) { //-1排除了登出部分
            menu_lis.eq(i).bind("click", function () {
               




                var _innerhtml = $(this).find(".subtit").html();
                var sibling_innerhtml = $(this).siblings().find(".subtit").html();
                $(this).siblings().html("<span  class='subtit'>" + sibling_innerhtml + "</span>");
                $(this).html("<span>&gt;</span><span  class='subtit'>" + _innerhtml + "</span>");
                if (_innerhtml == "积分项目") {
                    _innerhtml = "积分计划管理系统";
                }
                $("#title").html(_innerhtml);
                var Id = $(this).attr("id");
                
                //设置iframe跳转页面
                $("#iframeContent").attr("src", Id);
            });
        }
    }

    this.height = function () {
        var height = $("body").height() - $("#header-flickr").height();
       
        var iframe = document.getElementById("iframeContent");
        height = height < 600 ? document.documentElement.clientHeight : height;
    //   alert("..:"+ iframe.contentDocument.body);
        alert("jg:"+document.documentElement.clientHeight);
      //  $("#iframeContent").height(height);
    };
}