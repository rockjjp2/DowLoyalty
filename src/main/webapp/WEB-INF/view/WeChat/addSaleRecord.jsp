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
    <link rel="stylesheet" href="/DowLoyalty/Resources/html/css/promoter/addSaleRecord.css"/>
</head>
<body>
    <header>
        <img src="/DowLoyalty/Resources/html/images/backImg.png" alt=""/>
        <h4>增加销售记录</h4>
    </header>

    <div class="container">
        <div class="clearfix item">
            <label for="project" class="col-xs-4 col-xs-offset-1">项目</label>
            <input id="project" type="text" class="col-xs-7" placeholder="请选择项目"/>
        </div>
        <div class="clearfix item">
            <label for="dealer" class="col-xs-4 col-xs-offset-1">经销商</label>
            <input id="dealer" type="text" class="col-xs-7" placeholder="请选择经销商"/>
        </div>
        <div class="clearfix item">
            <label for="product" class="col-xs-4 col-xs-offset-1">产品</label>
            <input id="product" type="text" class="col-xs-7" placeholder="请选择产品"/>
        </div>
        <div class="clearfix item">
            <label for="norms" class="col-xs-4 col-xs-offset-1">规格</label>
            <input id="norms" type="text" class="col-xs-7" placeholder="请选择规格"/>
        </div>
        <div class="clearfix item">
            <label for="nums" class="col-xs-4 col-xs-offset-1">数量</label>
            <input id="nums" type="text" class="col-xs-7" placeholder="请选择数量"/>
        </div>
        <div class="clearfix">
            <button class="col-xs-4 col-xs-offset-4 style-bg-green">增加记录</button>
        </div>

    </div>
</body>

</html>