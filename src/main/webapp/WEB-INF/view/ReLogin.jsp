<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib uri = "http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <title>Busy Now</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/Resources/css/bootstrap.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/Resources/css/theme.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/Resources/css/font-awesome.css">
	<script src="${pageContext.request.contextPath}/Resources/js/jquery-1.8.1.min.js" type="text/javascript"></script>
    <style type="text/css">
        #line-chart {
            height:300px;
            width:800px;
            margin: 0px auto;
            margin-top: 1em;
        }
        .brand { font-family: georgia, serif; }
        .brand .first {
            color: #ccc;
            font-style: italic;
        }
        .brand .second {
            color: #fff;
            font-weight: bold;
        }
    </style>
    <script src="${pageContext.request.contextPath}/Resources/js/bootstrap.js"></script>
  </head>
	<body class="http-error"> 
	<div class="row-fluid">
	    <div class="http-error">
	        <h1>Oops!</h1>
	        <p class="info">登陆已经失效，请重新登陆</p>
	        <c:if test="${mx!=null}">
	        
	        <p class="info"><a href="${pageContext.request.contextPath}/Login" target="_parent">返回首页</a></p>
	        </c:if>
	        
	        <p><i class="icon-home"></i></p>
	    </div>
	</div>
	</body>
</html>

