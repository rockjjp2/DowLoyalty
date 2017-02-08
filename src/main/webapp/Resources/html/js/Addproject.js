/// <reference path="Common.js" />
/// <reference path="../ajaxjs/jquery.js" />
/// <reference path="JquerySession.js" />

document.write(" <script src='/DowLoyalty/Resources/html/js/Common.js'></script>");
document.write(" <script src='/DowLoyalty/Resources/html/js/JquerySession.js'></script>");


$(function () {
    page.$Init();
});

var page = {
    $Init: function () {
        /*加载时间控件*/
        page.$Time();
        /*选择器*/
        page.$Empty();
        /*提交方法*/
        page.$Submit();
        /*选择人员*/
        page.$People();
        page.$Goods();
        /*关闭*/
        page.$close();
        /*清空缓存*/
        $.session.clear();
    },

    /*加载时间控件*/
    $Time: function () {
        $('#Start_time').datetimepicker({

            timepicker: false,
            format: 'Y/m/d',
            formatDate: 'Y/m/d',
            step: 10
        });

        $('#End_time').datetimepicker({
            timepicker: false,
            format: 'Y/m/d',
            formatDate: 'Y/m/d',
            minDate: '-1970/01/01' // yesterday is minimum date
        });
    },

    /*选择器*/
    $Empty: function () {
        $(".sub-nav").on("click", "li", function () {
            $(".sub-nav li span").addClass('li-Nspan');
            $(".sub-nav li a").addClass('a-Ncolor');
            $(this).children('span').removeClass();
            $(this).children('a').removeClass();
            $(this).children('span').addClass('li-Yspan');
            $(this).children('a').addClass('a-Ycolor');

            $(".actived").removeAttr("style");
            $(".actived").css("display", "none");

            var href = $(this).children('a').attr('href');
            $(href).show();

        });
    },

    /*submit 提交*/
    $Submit: function () {
        $("#Activity_submit").click(function () {
            var Province = $("#Province").find("option:selected").text();//省份
            var Start_time = $.trim($("#Start_time").val());//开始时间
            var End_time = $.trim($("#End_time").val());//结束时间
            var Title = $.trim($("#Title").val());//标题
            var Introduce = $.trim($("#Introduce").val());//介绍

            if (App.Com.isnullorempty(Province) || Province == "请选择省份") { alert("请选择省份！"); return false; }
            if (App.Com.isnullorempty(Province)) { alert("请选择开始时间！"); return false; }
            if (App.Com.isnullorempty(Province)) { alert("请选择结束时间！"); return false; }
            if (App.Com.isnullorempty(Province)) { alert("请选择标题！"); return false; }
            if (App.Com.isnullorempty(Province)) { alert("请选择介绍！"); return false; }

            var Json = '[{"Province":"' + Province + '","Start_time":" ' + Start_time + ' ","End_time":" ' + End_time + ' ","Title":" ' + Title + ' ","Introduce":" ' + Introduce + ' "}]';

            $.session.set("Activity", Json);

            page.$Show("#Person");

        });
    },

    /*选择人员*/
    $People: function () {
        /*显示层*/
        $("#Retailers,Person_Submit").click(function () { $("#layer").css("display", "block"); $("#Person_UL").empty(); });//  

        /*单击行选中 并将选中的数据记录于session*/
        $("#People_Content").on("click", "tbody tr", function () {

            var session = $.session.get("People_Content");
            var id = $(this).find('td').data('id');
            var name = $(this).find('td').data('content');
            var Json = '{"id":"' + id + '","name":" ' + name + ' "}';

            if ($(this).find('input[type=checkbox]').is(":checked")) {

                $(this).find('input[type=checkbox]').prop("checked", false);

                if (!App.Com.isnullorempty(session)) {

                    var array = jQuery.parseJSON(session);
                    var result = [];

                    for (var i = 0; i < array.length; i++) { if (JSON.stringify(array[i]) != Json) result.push(array[i]); }

                    $.session.set("People_Content", JSON.stringify(result) == "[]" ? "" : JSON.stringify(result));
                }
            } else {
                $(this).find('input[type=checkbox]').prop("checked", true);

                var result = [];
                
                if (!App.Com.isnullorempty(session)) { session = session.substring(0, session.length - 1); session = session.substring(1, session.Length); Json = session + ',' + Json; }

                Json = '[' + Json + ']';

                $.session.set("People_Content", Json);
            }
        });

        /*鼠标移动*/
        $("#People_Content tbody tr").mouseover(function () { $(this).css("background", "#f9f9f9"); }).mouseout(function () { $(this).css("background", "#FFFFFF"); });

        /*确定选中零售商*/
        $("#Person_Submit").click(function () {
            /*关闭*/
            page.$close();

            /*显示被选人员*/
            var session = $.session.get("People_Content");

            var _html = '';

            if (!App.Com.isnullorempty(session)) { $(jQuery.parseJSON(session)).each(function (index, item) { _html += "<li><span>" + item.name + "</span></li>"; }); }

            $("#Person_UL").append(_html);

        });

        /*选择发货推广员*/
        $(".li_choose span").click(function () {
            $(".li_choose span").css("color", "#333333");
            $(this).css("color", "#B6BF00");
            $(".field-choose").text($(this).text());
        });

        /*关闭*/
        $(".close").click(function () { page.$close(); });

    },

    
    
    /*关闭*/
    $close: function () {
        $("#layer").css("display", "none");
    },

    /*显示层*/
    $Show: function (Actived) {
        $(Actived).show();
    }
    
}

$(function () {
	/*礼品配置*/
	$(".addGoods").click(function () { $("#layer1").css("display", "block"); });//  
	/*显示弹出框*/
	$("#Goods_Category").change(function(){
		var goods_category = $("#Goods_Category").val();
		if(goods_category != -1)
		{
			$(".Goods_Label").css("display","block");
			$(".Goods_Info").css("display","block");
		}
		else
		{
			$(".Goods_Label").css("display","none");
			$(".Goods_Info").css("display","none");
		}
	});
	
	/*确定选中礼品*/
	$("#Person_Submit1").click(function () {
		$("#layer1").css("display", "none");
	});
	
	/*关闭*/
	$(".close").click(function () { $("#layer1").css("display", "none"); });
	
	/*产品配置*/
	$(".addProduct").click(function () { $("#layer2").css("display", "block"); });//  
	/*显示弹出框*/
	$("#Goods_Category").change(function(){
		var goods_category = $("#Goods_Category").val();
		if(goods_category != -1)
		{
			$(".Goods_Label").css("display","block");
			$(".Goods_Info").css("display","block");
		}
		else
		{
			$(".Goods_Label").css("display","none");
			$(".Goods_Info").css("display","none");
		}
	});
	
	/*确定选中礼品*/
	$("#Person_Submit1").click(function () {
		$("#layer1").css("display", "none");
	});
});
