<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%@taglib uri = "http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
    <title>DOW</title>
    <link rel="stylesheet" href="/DowLoyalty/Resources/html/css/font-awesome.min.css"/>
    <link rel="stylesheet" href="/DowLoyalty/Resources/html/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="/DowLoyalty/Resources/html/css/basic.css"/>
    <link rel="stylesheet" href="/DowLoyalty/Resources/html/css/stockist/scoreDetails.css"/>
</head>
<body>
    <header>
        <img src="/DowLoyalty/Resources/html/images/backImg.png" alt=""/>
        <h4>积分明细</h4>
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
        <form class = "pointsDetails" method="post" action="">
            <div class="col-xs-4 div-select style-deepGray">
                <select name="month" id="selectMonth">
                    <option value="" hidden>请选择月份</option>
                    <c:forEach begin="1" end = "12" var = "i">
                    	<option <c:out value = "${month eq i ? 'selected':''}"/> value = "${i}">${i}月</option>
                    </c:forEach>
                </select>
                <i class="fa fa-sort-down style-green"></i>
            </div>
            <div class="col-xs-4 div-select style-deepGray">
                <select name="matter" id="selectItem">
                    <option value="" hidden>请选择事项</option>
                    <option <c:out value = "${matter eq '兑换' ? 'selected':''}"/> value="兑换">兑换</option>
                    <option <c:out value = "${matter eq '进货' ? 'selected':''}"/> value="进货">进货</option>
                </select>
                <i class="fa fa-sort-down style-green"></i>
            </div>
            </form>
            <button id="filter" class="col-xs-3 style-bg-green">筛选</button>
        </div>
		
		<c:choose>
		<c:when test="${not empty pointsDetails}">
			<div class="clearfix item">
            <p class="col-xs-3">日期</p>
            <p class="col-xs-3">事项</p>
            <p class="col-xs-3">明细</p>
            <p class="col-xs-3">积分变化</p>
            </div>
            <c:forEach var = "details" items="${pointsDetails}">
             <div class="clearfix item">
            <p class="col-xs-3">${details.submitTime}</p>
            <c:if test="${details.points lt 0}">
            	<p class="col-xs-3">兑换</p>
            </c:if>
            <c:if test="${details.points gt 0}">
            	<p class="col-xs-3">进货</p>
            </c:if>
            <p class="col-xs-4">${details.name}</p>
            <p class="col-xs-2 score style-green">${details.points}</p>
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
		$(function(){
			$("#filter").click(function(){
				$(".pointsDetails").attr("action","/DowLoyalty/v1/WeChat/retailer/pointsDetails");
				$(".pointsDetails").submit();
			});
		});
    </script>
</body>

</html>