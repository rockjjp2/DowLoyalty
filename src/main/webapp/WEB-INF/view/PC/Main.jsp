<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
    <link href="/DowLoyalty/Resources/html/css/HeaderMenu.css" rel="stylesheet" />
    <link href="/DowLoyalty/Resources/html/css/Bubble.css" rel="stylesheet" />
    <link href="/DowLoyalty/Resources/html/css/mainContent.css" rel="stylesheet" />
    <link href="/DowLoyalty/Resources/html/css/Style.css" rel="stylesheet" />
    <script src="/DowLoyalty/Resources/html/js/jquery-1.4.2.min.js"></script>
    <script src="/DowLoyalty/Resources/html/js/main.js"></script>
    <script>

        var _Main = null;

        $(function () {
            if (_Main == null) {
                _Main = new $main();
                _Main.Init();
            }
        });

        ////计算iframe的高度
        //function iFrameHeight() {

        //    var ifm = document.getElementById("iframeContent");

        //    ////  var subWeb = document.frames ? document.frames["iframeContent"].document : ifm.contentDocument;
        //    //var subWeb = window.frames["iframeContent"] ? window.frames["iframeContent"].document : ifm.contentDocument;

        //    var subWeb =   document.getElementById('iframeContent').contentWindow.document

        //    if (ifm != null && subWeb != null) {

        //        ifm.height = subWeb.body.scrollHeight;

        //    }

        //}
    </script>

</head>
<body>
    <div id="header-flickr" style="border:0px solid red">
        <div class="header-menu">
            <ul class="header-menu-ul">
                <li id="project">
                    <span> 积分项目</span>
                </li>
                <li style="position:relative" id="gift_ExChange" class="menu-active">
                    <span>兑换列表</span>
                    <!--<div class="Bubble-menu" style="display:none">
                        <ul class="cui-bubble-layer" style="position: absolute; top: 43px;left:-25px">
                            <li data-index="0" data-flag="c" class="cui-bubble-layer-action"><span>&gt;</span>  礼品配置</li>
                            <li data-index="1" data-flag="c"><span>&nbsp;&nbsp;</span> 兑换列表</li>
                        </ul>
                    </div>-->
                </li>
                <li id="Addproject"><span>创建新项目</span></li>
                <li id="CardInfo"><span>会员信息</span></li>
                <li style="position:relative">
                    <span>销售记录</span>
                    <div class="Bubble-menu" style="display:none">
                        <ul class="cui-bubble-layer" style="position: absolute; top: 43px;left:-25px">
                            <li data-index="0" data-flag="c" id="HistorySalesRecord" class="cui-bubble-layer-action"><span>&gt;</span><span class="subtit">历史销售记录</span></li>
                            <li data-index="1" data-flag="c" id="ImportSalesRecord"><span>&nbsp;&nbsp;</span><span class="subtit">导入销售记录</span></li>
                        </ul>
                    </div>

                </li>
                <li id="header-aount">
                    <span id="aount_img"></span>&nbsp;
                    <span id="aount_name">
                        你好,Becky
                    </span>
                    <span id="logout_img"></span>
                </li>
            </ul>
        </div>
        <div class="header-image">
            <div id="title">礼品商城</div>
        </div>
    </div>

    <!--气泡菜单选项-->

    <div id="content" style="border:0px solid red;height:100%;">
        <iframe src=" addproject.html" id="iframeContent" frameborder="0" scrolling="no"  style="height:100%;">
        </iframe>
    </div>

</body>

</html>