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
    <link rel="stylesheet" href="/DowLoyalty/Resources/html/css/giftStore.css"/>
</head>
<body>
    <header>
        <img src="/DowLoyalty/Resources/html/images/backImg.png" alt=""/>
        <h3>礼品商城</h3>
    </header>

    <div class="container">
        <div class="clearfix">
            <div class="col-xs-2 menu-btn style-btn-gray style-bg-green">全部</div>
            <div class="col-xs-2 menu-btn style-btn-gray">电器</div>
            <div class="col-xs-2 menu-btn style-btn-gray">卡券</div>
            <div class="col-xs-2 menu-btn style-btn-gray">汽车</div>
        </div>

        <div class="clearfix">
            <div class="col-xs-5 img-div">
                <img src="/DowLoyalty/Resources/html/images/fuelCard.png" alt=""/>
                <p>
                    1000元加油卡<br/>
                    <span class="style-green">10</span>
                    积分
                </p>
            </div>
            <div class="col-xs-5 img-div">
                <img src="/DowLoyalty/Resources/html/images/fridge.png" alt=""/>
                <p>
                    海尔对开门冰箱<br/>
                    <span class="style-green">23</span>
                    积分
                </p>
            </div>
        </div>

        <div class="clearfix">
            <div class="col-xs-5 img-div">
                <img src="/DowLoyalty/Resources/html/images/washMachine.png" alt=""/>
                <p>
                    海尔变频滚筒洗衣机<br/>
                    <span class="style-green">15</span>
                    积分
                </p>
            </div>
            <div class="col-xs-5 img-div">
                <img src="/DowLoyalty/Resources/html/images/airCondition.png" alt=""/>
                <p>
                    格力1.5匹节能空调<br/>
                    <span class="style-green">20</span>
                    积分
                </p>
            </div>
        </div>
    </div>

    <script src="/DowLoyalty/Resources/html/js/jquery-1.8.2.min.js"></script>
    <script type="text/javascript">
        <!-- 按钮点击后样式改变-->
        $(".menu-btn").click(function(){
            $(".menu-btn").removeClass("style-bg-green");
            $(this).addClass("style-bg-green");
        })
    </script>
</body>

</html>