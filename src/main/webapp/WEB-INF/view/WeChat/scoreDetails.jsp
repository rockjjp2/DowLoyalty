<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri = "http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
    <title>忠诚度计划</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/Resources/html/css/font-awesome.min.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/Resources/html/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/Resources/html/css/basic.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/Resources/html/css/scoreDetails.css"/>
</head>
<body>
    <header>
        <img src="${pageContext.request.contextPath}/Resources/html/images/backImg.png" alt=""/>
        <h4>积分明细</h4>
    </header>
    <div class="container">
        <div class="clearfix">
        <form method="post" action="">
        
            <div class="col-xs-4 div-select style-deepGray">
                <select name="month" id="selectMonth"  style="padding-right: 0;width: 90%;">
                    <option value="" hidden>请选择月份</option>
                    <c:forEach begin="1" end = "12" var = "i">
                    	<option <c:out value = "${month eq i ? 'selected':''}"/> value = "${i}">${i}月</option>
                    </c:forEach>
                </select>
                <i class="fa fa-sort-down style-green"  style="margin-right: -6%;"></i>
            </div>
            <div class="col-xs-4 div-select style-deepGray"  style="padding-left:4px;">
                <select name="matter" id="selectItem" style="width: 90%;">
                    <option value="" hidden>请选择事项</option>
                    <option <c:out value = "${matter eq '兑换' ? 'selected':''}"/> value="兑换">兑换</option>
                    <option <c:out value = "${matter eq '进货' ? 'selected':''}"/> value="进货">进货</option>
                </select>
                <i class="fa fa-sort-down style-green" style="margin-right: 5%;"></i>
            </div>
            <button id="filter" class="col-xs-3 style-bg-green">筛选</button>
            </form>
        </div>
		
		<c:choose>
		<c:when test="${not empty pointsDetails}">
			<div class="clearfix item">
            <p class="col-xs-3">日期</p>
            <p class="col-xs-3">事项</p>
            <p class="col-xs-4">明细</p>
            <p class="col-xs-2 score">积分变化</p>
            </div>
            <c:forEach var = "details" items="${pointsDetails}">
             <div class="clearfix item">
            <p class="col-xs-3">${details.submitTime}</p>
            <center>
            <c:if test="${details.points lt 0}">
            	<p class="col-xs-3">兑换</p>
            	<p class="col-xs-4 score">${details.name}</p>
            	<p class="col-xs-2 score style-green">${details.points}</p>
            </c:if>
            <c:if test="${details.points gt 0}">
            	<p class="col-xs-3">进货</p>
            	<p class="col-xs-4 score">${details.name}</p>
            	<p class="col-xs-2 score style-green">+${details.points}</p>
            </c:if>
            </center>
         	</div>
            </c:forEach>
		</c:when>
		<c:otherwise>
		未查询到相应积分明细
		</c:otherwise>
		</c:choose>
    </div>
    
    <script src="${pageContext.request.contextPath}/Resources/html/js/jquery-1.8.2.min.js"></script>
    <script type="text/javascript">
		$(function(){
			$("#filter").click(function(){
				$(".pointsDetails").attr("action","${pageContext.request.contextPath}/WeChat/retailer/pointsDetails");
				$(".pointsDetails").submit();
			});
		});
    </script>
</body>

</html>