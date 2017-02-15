<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib uri = "http://java.sun.com/jsp/jstl/core" prefix="c"%>
    <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
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
        <h4>礼品商城</h4>
    </header>

    <div class="container">
        <div class="clearfix">
            <div class="col-xs-2 menu-btn style-btn-gray style-bg-green">全部</div>
            <!--<div class="col-xs-2 menu-btn style-btn-gray">电器</div>-->
            <!--<div class="col-xs-2 menu-btn style-btn-gray">卡券</div>-->
            <!--<div class="col-xs-2 menu-btn style-btn-gray">汽车</div>-->
        </div>
		<c:choose>
		<c:when test="${not empty allGoods}">
		<c:forEach begin="0" end="${fn:length(allGoods)-1}" step="2" var="i">
			<div class="clearfix">
            <div class="col-xs-5 img-div" onclick = "getInfo('${allGoods[i].goods.id}')">
                <img src="${allGoods[i].goods.imagePath}" alt=""/>
                <p>
                    ${allGoods[i].goods.name}<br/>
                    <span class="style-green">${allGoods[i].exchangePoints}</span>
                    积分
                </p>
            </div>
            <c:if test="${i+1 le fn:length(allGoods)-1}">
            <div class="col-xs-5 img-div" onclick = "getInfo('${allGoods[i+1].goods.id}')">
                <img src="${allGoods[i+1].goods.imagePath}" alt=""/>
                <p>
                    ${allGoods[i+1].goods.name}<br/>
                    <span class="style-green">${allGoods[i+1].exchangePoints}</span>
                    积分
                </p>
            </div>
            </c:if>
        </div>
		</c:forEach>
		</c:when>
		<c:otherwise>
		Nothing
		</c:otherwise>
		</c:choose>
    </div>

	<footer class="txt-center style-deepGray">
        <div id="homePage" class="menu"><i class="fa fa-home"></i></div>
        <div id="accountInfo" class="menu style-btnBg-green">账户信息</div>
        <div id="exchangeshop" class="menu">礼品商城</div>
        <div id="pointsDetails" class="menu">积分明细</div>
    </footer>

    <script src="/DowLoyalty/Resources/html/js/jquery-1.8.2.min.js"></script>
    <script type="text/javascript">
    /*点击菜单-页面跳转*/
    $(".menu").click(function(){
       var pageID = $(this).attr("id");
       location.href = "/DowLoyalty/v1/WeChat/retailer/"+pageID;
    });
    
        <!-- 按钮点击后样式改变-->
        $(".menu-btn").click(function(){
            $(".menu-btn").removeClass("style-bg-green");
            $(this).addClass("style-bg-green");
        })
        
        //跳转到具体礼品信息显示页面
        function getInfo(id){
        	location.href = "/DowLoyalty/v1/WeChat/retailer/goodsInfo?goodsId="+id;
        }
    </script>
</body>

</html>