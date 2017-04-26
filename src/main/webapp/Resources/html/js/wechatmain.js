$main = function () {
    var _entity;
    this.Init = function () {
        _entity = this;
        _entity.changeMenu();
    }

    this.changeMenu= function () {
       
        var menus = $(".menu");
        for (var i = 0; i < menus.size(); i++) {
            menus.eq(i).bind("click", function () {
                var Id = $(this).attr("id");
                //清除其他兄弟标签的选择状态
                $(this).siblings().removeAttr("class");
                $(this).siblings().attr("class", "menu");
                //绑定菜单选中状态
                $(this).attr("class", "menu style-btnBg-green");
                $(this).find(".Bubble-menu").css("display", "block");

                //注：此处有个页面所属文件夹的名称，promoter与stockist，如两个文件夹中的页面都需用到，那么请在此处做个判断程序需要进入哪个文件夹
                //或，索性将所有页面放入一个文件夹中
                //window.location.href=Id;
                $("#iframeContent").attr("src", Id);
            })
        }
    }
}