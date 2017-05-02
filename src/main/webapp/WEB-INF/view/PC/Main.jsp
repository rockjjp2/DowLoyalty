<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>DOW</title>
    <link href="${pageContext.request.contextPath}/Resources/html/css/HeaderMenu.css" rel="stylesheet" />
    <link href="${pageContext.request.contextPath}/Resources/html/css/Bubble.css" rel="stylesheet" />
    <link href="${pageContext.request.contextPath}/Resources/html/css/mainContent.css" rel="stylesheet" />
    <link href="${pageContext.request.contextPath}/Resources/html/css/Style.css" rel="stylesheet" />
    <script src="${pageContext.request.contextPath}/Resources/html/js/jquery-1.4.2.min.js"></script>
    <script src="${pageContext.request.contextPath}/Resources/html/js/main.js?j=2"></script>
    <script>

        var _Main = null;

        $(function () {
            if (_Main == null) {
                _Main = new $main();
                _Main.Init();
            }

            //iframe自适应高度
            $("#iframeContent").load(function () {

                //1.先获得iframe
                var iframe = document.getElementById("iframeContent");
                //1.先获得iframe页面内容中的高度
                var div_height = $(this).contents().find(".content-flickr").height();
                if (div_height == 0 || div_height == undefined) {
                    div_height = $(iframe).contents().height();
                }
                //将高度设为自己的高度
                $(this).height(div_height+80);

            });
        });
    </script>

</head>
<body>
    <div id="header-flickr" style="border:0px solid red">
        <div class="header-menu">
            <ul class="header-menu-ul">
                <li id="integralitem" style="position:relative" class="menu-active">
                    <span>积分项目</span>
                </li>
                <li id="gift_ExChange">
                    <span>兑换列表</span>
                </li>
                <li id="createproject"><span>创建新项目</span></li>
<!--                 <li id="cardinfo"><span>会员信息</span></li>
                <li id="extrapoints"><span>会员留言</span></li> -->
                <li style="position: relative; " id="Sales">
                    <span>会员管理</span>
                    <div class="Bubble-menu" style="display:none" id="menu-Sales">
                        <ul class="cui-bubble-layer" style="position: absolute; top: 43px;left:-15px">
                            <li data-index="0" data-flag="c" id="cardinfo" class="cui-bubble-layer-action"><span></span><span class="subtit">会员信息</span></li>
                            <li data-index="1" data-flag="c" id="extrapoints"><span></span><span class="subtit">会员留言</span></li>
                        </ul>
                    </div>
                </li>
                <li id="showSaleRecord"><span>销售记录</span></li>
                <li id="header-aount" style="position:relative;">
                    <div id="loginout" style="height:30px">
                        <span id="aount_img"></span>&nbsp;
                        <span id="aount_name">
                            你好, ${USERNAME}
                        </span>
                        <!--<span id="logout_img"></span>-->
                        <div class="Bubble-menu" style="display:none" id="menu-loginout">
                            <ul class="cui-bubble-layer" style="position: absolute; top: 43px; left:60px;width:40%">
                                <li style="width: 100%;"><a class="subtit" href="loginout" id="subtit_a" style="color: #9b9b9b; font-size: 1em">登出</a></li>
                            </ul>
                        </div>
                    </div>
                </li>
            </ul>
        </div>
        <div class="header-image">
            <div id="title">积分项目</div>
        </div>
    </div>

    <!--气泡菜单选项-->

    <div id="content" style="border:0px solid red;height:100%;">
        <iframe src="integralitem" id="iframeContent" frameborder="0" scrolling="no" ></iframe>
    </div>
</body>
</html>
