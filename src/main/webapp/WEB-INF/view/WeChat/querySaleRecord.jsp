<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
    <title>忠诚度计划</title>
    <script src="${pageContext.request.contextPath}/Resources/html/js/WdatePicker.js"></script>
    <script src="${pageContext.request.contextPath}/Resources/html/js/jquery-1.8.2.min.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/Resources/html/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/Resources/html/css/basic.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/Resources/html/css/font-awesome.min.css"/>

</head>
<body>
    <header >
        <img  src="${pageContext.request.contextPath}/Resources/html/images/backImg.png" alt=""/>
        <h4>查询销售记录</h4>
    </header>

<div class="content-flickr">
    <div class="container">
        <form action="showSaleRecord" method="post" >
        <table border="0" style="width:100%;"cellpadding="2" >
            <tr>
                <td >开始时间：
                    <input id="startdate" name="startdate" class="Wdate" onfocus="WdatePicker({readOnly:true,maxDate:'%y-%M-%d'})" style=" border: 1px solid #B6BF00; margin-bottom: 10px; margin-top: 10px; height: 22px; width: 60%" />
                </td>
            </tr>
            <tr>
                <td>结束时间：
                <input id="enddate" name="enddate" class="Wdate" onfocus="WdatePicker({readOnly:true,maxDate:'%y-%M-%d'})" style="border: 1px solid #B6BF00; margin-bottom: 10px; height: 22px; width: 60%; " />
                </td>
            </tr>
            <tr>
                <td>零售商名：
                <input id="txtCardName" name = "retailername" type="text" style="border: 1px solid #B6BF00; margin-bottom: 10px; height: 22px;width:60% " /></td>
                <td><input type="submit" value="查询" style="border: 1px solid #B6BF00; margin-bottom: 10px; height: 22px; border-radius: 3px; color: #fff; background-color: #B6BF00" /></td>
            </tr>
        </table>
        </form>
        
        <c:choose>
		<c:when test="${not empty salesRecord}">
        <div class="clearfix item">
            <p class="col-xs-4">添加时间</p>
            <p class="col-xs-4">零售商</p>
            <p class="col-xs-4">销售额</p>
        </div>

        <c:forEach items="${salesRecord }" var="s" >     
        <div class="clearfix item">
            <p class="col-xs-3 addTime">${s.submitDate}</p>
            <p class="col-xs-5 dealer">${s.retaileName }</p>
            <p class="col-xs-4 sales">${s.totalPrice }</p>
        </div>
      </c:forEach>
      </c:when>
    <c:when test="${msgs == -1 }">
      未查询到相应销售记录
     </c:when>
      </c:choose>
    </div>
</div>
</body>

</html>