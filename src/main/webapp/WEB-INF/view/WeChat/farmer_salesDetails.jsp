<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri = "http://java.sun.com/jsp/jstl/core" %>
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
    <h4>查询销售记录</h4>
</header>
<div class="container">
    <div class="clearfix">
        <form class = "record" method="post" action="">

            <div class="col-xs-4 div-select style-deepGray" style="margin-left: 8%;margin-right: 18%;">
                <select name="month" id="selectMonth"  style="padding-right: 0;width: 90%;">
                    <option value="" hidden>请选择月份</option>
                    <c:forEach begin="1" end = "12" var = "i">
                    <option <c:out value = "${month eq i ? 'selected':''}"/> value = "${i}">${i}月</option>
                    </c:forEach>
                </select>
                <i class="fa fa-sort-down style-green"  style="margin-right: -6%;"></i>
            </div>
            <button id="filter" class="col-xs-3 style-bg-green">筛选</button>
        </form>
    </div>

    <c:choose>
    <c:when test="${not empty records}">
    <div class="clearfix item">
        <p class="col-xs-6" >添加时间</p>
        <p class="col-xs-6 score" >销售额</p>
    </div>
    <c:forEach var = "record" items="${records}">
    <div class="clearfix item">
        <p class="col-xs-6">${record.submitDate}</p>
        <p class="col-xs-6 score style-green">${record.totalPrice}</p>
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
            $(".record").attr("action","farmersalesrecord");
            $(".record").submit();
        });
    });
</script>
</body>

</html>