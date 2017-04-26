<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri = "http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
    <title>忠诚度计划</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/Resources/html/css//bootstrap.min.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/Resources/html/css/font-awesome.min.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/Resources/html/css/basic.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/Resources/html/css/homePage.css"/>

</head>
<body>
	<div class="container" style="padding-right:0px;padding-left: 0px;">
	<c:choose>
		<c:when test="${not empty imageURL}">
        <img src="data:image/jpg;base64,${imageURL}" alt="正在加载图片信息，请稍后。。。"/>
		</c:when>
		<c:otherwise>
        <img src="${pageContext.request.contextPath}/Resources/html/images/IMG_8359.JPG" alt=""/>
		</c:otherwise>
	</c:choose>
    </div>
</body>
</html>