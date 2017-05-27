<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
    <!--<meta name="viewport" content="width=device-width, initial-scale=1">-->
    <meta name="viewport" content="width=device-width,initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no"/>
    <meta name="format-detection" content="telephone=no, email=no"/>
    <title>忠诚度计划</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/Resources/html/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/Resources/html/css/font-awesome.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/Resources/html/css/farmer_register.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/Resources/html/css/basic.css"/>

    <script src="${pageContext.request.contextPath}/Resources/html/js/jquery-1.8.2.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/Resources/html/js/farmer_register.js"></script>

    <style>
        select {
            border: 1px solid #B6BF00;
            width: 30%;
            height: 25px;
            border-radius: 4px;
        }

        /*弹出层*/
        .output {
            position: absolute;
            top: 15%;
            left: 7%;
            width: 80%;
            border: 15px solid #EBF7B3;
            z-index: 1002;
            border-radius: 16px;
            height: 35%;
        }

        .Dialog-close {
            float: right;
            font-size: 26px;
            font-weight: 100;
            line-height: 1;
            color: #fff;
            text-shadow: 0 1px 0 #fff;
            background: #ACB317;
            height: 35px;
            width: 35px;
            border-radius: 100%;
            padding-bottom: 5px;
            margin: 0;
            margin-top: -30px;
            margin-right: -30px;
        }

        .hide {
            position: absolute;
            top: 0%;
            left: 0%;
            width: 100%;
            height: 100%;
            background-color: #f5f5f5;
            z-index: 1001;
            -moz-opacity: 0.8;
            opacity: .80;
        }

        .line {
            border: 2px dashed #C9CB92;
            background-color: #FFFFFF;
            text-align: center;
            margin-left: -4px;
            height: 100%;
        }

        #People_Content tr th {
            text-align: center;
            /*border-bottom:1px solid #CCC;*/
        }

        /*#People_Content tr td {
         border-bottom:1px dashed #CCC
        }*/

        #People_header {
            margin: 2%;
        }
    </style>
</head>
<body>
<header>
    <img src="${pageContext.request.contextPath}/Resources/html/images/backImg.png" alt=""/>
    <h4>注册</h4>
</header>


<div class="container">
    <form action="FarmerRegister" class = "myForm" method="post">
        <div class="clearfix item">
            <ul>
                <li class="fa fa-user-o"></li>
            </ul>
            <span id="name_span">*</span>
            <input type="text" class="name" name="name" placeholder="请输入姓名"/>
        </div>
        <div class="clearfix item">
            <ul id="mobile_ul">
                <li class="fa fa-mobile"></li>
            </ul>
            <span id="mobile_span">*</span>
            <input type="text" class="mobile" name="mobile" placeholder="请输入11位手机号"/>
        </div>
        <div class="clearfix item">
            <ul>
                <li class="fa fa-map-o"></li>
            </ul>
            <span id="project_span">*</span>
            <select class="project" name="province">
            <option hidden>请选择所在的省份</option>
                <c:if test="${not empty provinces}">
                <c:forEach items="${provinces}" var = "province">
	                <option value="${province.id}">${province.name}</option>
                </c:forEach>
                </c:if>
            </select>
        </div>
        <div class="clearfix item">
            <ul>
                <li class="fa fa-shield"></li>
            </ul>
            <span id="code_span">*</span>
            <input type="text" class="code" name="code" placeholder="请输入验证码"/>
            <img src="" id="code_img"/>
        </div>
        <div class="clearfix item">
            <ul>
                <li class="fa fa-tree"></li>
            </ul>
            <input type="text" class="area" name="area" placeholder="请输入土地面积"/>
        </div>
        <div class="clearfix" style=" margin-left: 2em;">
            <button class="col-xs-4 col-xs-offset-4 style-bg-green" id="register">注册</button>
        </div>
    </form>
</div>

<script src="${pageContext.request.contextPath}/Resources/html/js/jquery-1.8.2.min.js"></script>
<script type="text/javascript">
    /*点击菜单-页面跳转*/
    $(".menu").click(function () {
        var pageID = $(this).attr("id");
        location.href = pageID;
    });
</script>
</body>
</html>