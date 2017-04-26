<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>忠诚度计划</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/Resources/html/css/basic.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/Resources/html/css/validateCode.css">
    <script src="${pageContext.request.contextPath}/Resources/html/js/jquery-1.8.2.min.js"></script>
<script type="text/javascript">
$(function(){
	$("#btnSubmit").click(function(){
		$("#btnSubmit").attr("disable",true)
	})
})
</script>
</head>
<body>
    <header>
        <img src="${pageContext.request.contextPath}/Resources/html/images/accountInfo.png" alt="" style="height:auto" />
    </header>
<form action="SignUpDo">

    <div class="container">
        <div class="txt-center">
            <p>请输入您的手机号码进行注册</p>
        </div>
        <div class="txt-center">
            <input type="text" name="mobile" placeholder="${msg}">
        </div>
        <div class="txt-center">
            <button class="style-bg-green" type="submit" id="btnSubmit">确认</button>
        </div>
    </div>
</form>
</body>
</html>