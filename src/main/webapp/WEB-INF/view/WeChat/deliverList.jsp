<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
    <title>DOW</title>
    <link rel="stylesheet" href="/DowLoyalty/Resources/html/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="/DowLoyalty/Resources/html/css/basic.css"/>
    <link rel="stylesheet" href="/DowLoyalty/Resources/html/css/deliverList.css"/>
</head>
<body>
    <header>
        <img src="/DowLoyalty/Resources/html/images/backImg.png" alt=""/>
        <h4>发货列表</h4>
    </header>

    <div class="container">
        <div class="clearfix">
            <div class="menu-btn col-xs-8 col-xs-offset-2">
                <div class="tag col-xs-6 style-btn-green style-bg-green">待发货</div>
                <div class="tag col-xs-6 style-btn-green">已发货</div>
            </div>
        </div>

        <div class="clearfix item">
            <div class="data col-xs-8 col-xs-offset-1">
                <h5>xx省经销商1</h5>

                <p class="prize">iPhone7</p>
            </div>
            <button class="col-xs-3 style-bg-green">发货</button>
        </div>

        <div class="clearfix item">
            <div class="data col-xs-8 col-xs-offset-1">
                <h5>xx省经销商2</h5>

                <p class="prize">滚筒洗衣机</p>
            </div>
            <button class="col-xs-3 style-bg-green">发货</button>
        </div>
    </div>

    <script src="/DowLoyalty/Resources/html/js/jquery-1.8.2.min.js"></script>
    <script type="text/javascript">
        $(".tag").click(function(){
            $(".tag").removeClass("style-bg-green");
            $(this).addClass("style-bg-green");
        })
    </script>
</body>

</html>