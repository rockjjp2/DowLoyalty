<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
 <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>忠诚度计划</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/Resources/html/css/font-awesome.min.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/Resources/html/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/Resources/html/css/basic.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/Resources/html/css/deliverInfo.css"/>

</head>
<body>
    <header>
        <img src="${pageContext.request.contextPath}/Resources/html/images/backImg.png" alt=""/>
        <h4>发货详情</h4>
    </header>
    <div class="container">
        <div class="clearfix">
            <div class="img-div col-xs-6 col-xs-offset-3">
                <img src="${pageContext.request.contextPath}/Resources/html/images/deliverInfo.png" alt=""/>
            </div>
        </div>
        <div class="clearfix">
            <i class="fa fa-calendar-minus-o col-xs-1 col-xs-offset-1"></i>
            <label for="status" class="col-xs-5">状态</label>
            <p id="status" class="col-xs-4 style-green">已发货</p>
        </div>
        <div class="clearfix">
            <i class="fa fa-user-o col-xs-1 col-xs-offset-1"></i>
            <label for="retailerName" class="col-xs-5">零售商名称</label>
            <p id="retailerName" class="col-xs-4">XX省零售商</p>
        </div>
        <div class="clearfix">
            <i class="fa fa-gift col-xs-1 col-xs-offset-1"></i>
            <label for="convertGift" class="col-xs-5">兑换礼品</label>
            <p id="convertGift" class="col-xs-4">加油卡</p>
        </div>
        <div class="clearfix">
            <i class="fa fa-clock-o col-xs-1 col-xs-offset-1"></i>
            <label for="convertTime" class="col-xs-5">兑换时间</label>
            <p id="convertTime" class="col-xs-4">2016.1.1</p>
        </div>
        <div class="clearfix">
            <i class="fa fa-truck col-xs-1 col-xs-offset-1"></i>
            <label for="sendTime" class="col-xs-5">发货时间</label>
            <p id="sendTime" class="col-xs-4">2016.2.1</p>
        </div>
    </div>
</body>

</html>