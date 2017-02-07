<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
 <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>DOW</title>
    <link rel="stylesheet" href="/DowLoyalty/Resources/html/css/font-awesome.min.css"/>
    <link rel="stylesheet" href="/DowLoyalty/Resources/html/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="/DowLoyalty/Resources/html/css/basic.css"/>
    <link rel="stylesheet" href="/DowLoyalty/Resources/html/css/scoreDetails.css"/>
</head>
<body>
    <header>
        <img src="/DowLoyalty/Resources/html/images/backImg.png" alt=""/>
        <h3>积分明细</h3>
    </header>
    <div class="container">
        <!-- todo 用div重写select样式-->
        <!--<div class="clearfix">-->
            <!--<div id="selectMonth" class="col-xs-4 select">-->
                <!--<input type="text" placeholder="请选择月份"/>-->
                <!--<i class="fa fa-sort-down style-green"></i>-->
            <!--</div>-->
            <!--<div id="selectItem" class="col-xs-4 select">-->
                <!--<input type="text" placeholder="请选择事项"/>-->
                <!--<i class="fa fa-sort-down style-green"></i>-->
            <!--</div>-->
            <!--<button id="filter" class="col-xs-3 style-bg-green">筛选</button>-->
        <!--</div>-->
        <div class="clearfix">
            <div class="col-xs-4 div-select style-deepGray">
                    <select name="" id="selectMonth">
                        <option value="" hidden>请选择月份</option>
                        <option value="">1月</option>
                        <option value="">2月</option>
                        <option value="">3月</option>
                    </select>
                <i class="fa fa-sort-down style-green"></i>
            </div>

            <div class="col-xs-4 div-select style-deepGray">
                    <select name="" id="selectItem">
                        <option value="" hidden>请选择事项</option>
                        <option value="">兑换</option>
                        <option value="">进货</option>
                    </select>
                <i class="fa fa-sort-down style-green"></i>
            </div>

            <button id="filter" class="col-xs-3 style-bg-green">筛选</button>
        </div>

        <div class="clearfix item">
            <p class="col-xs-3">日期</p>
            <p class="col-xs-3">事项</p>
            <p class="col-xs-3">明细</p>
            <p class="col-xs-3">积分变化</p>
        </div>

        <div class="clearfix item">
            <p class="col-xs-3">2017.1</p>
            <p class="col-xs-3">兑换</p>
            <p class="col-xs-4">iPhone7</p>
            <p class="col-xs-2 score style-green">-20</p>
        </div>

        <div class="clearfix item">
            <p class="col-xs-3">2017.2</p>
            <p class="col-xs-3">进货</p>
            <p class="col-xs-4">杀虫剂</p>
            <p class="col-xs-2 score style-green">+10</p>
        </div>

        <div class="clearfix item">
            <p class="col-xs-3">2017.1</p>
            <p class="col-xs-3">兑换</p>
            <p class="col-xs-4">iPhone7</p>
            <p class="col-xs-2 score style-green">-20</p>
        </div>

        <div class="clearfix item">
            <p class="col-xs-3">2017.2</p>
            <p class="col-xs-3">进货</p>
            <p class="col-xs-4">杀虫剂</p>
            <p class="col-xs-2 score style-green">+10</p>
        </div>
    </div>

    <script src="/DowLoyalty/Resources/html/js/jquery-1.8.2.min.js"></script>
    <script type="text/javascript">
        <!-- select框 -->
//        $(".select").click(function(){
//            var selectList = $("<ul class='txt-center'>" +
//                    "<li>1月</li>" +
//                    "<li>2月</li>" +
//                    "<li>3月</li>" +
//                    "<li>4月</li>" +
//                    "</ul>");
//            $(this).append(selectList);
//        })
    </script>
</body>

</html>