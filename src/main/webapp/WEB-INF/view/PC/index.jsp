<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title></title>
    <script src="${pageContext.request.contextPath}/Resources/html/js/jquery.js"></script>
    <style>
        body {
            background: url(Resources/html/images/Welcome.png) no-repeat;
            background-size: cover;
            text-align: center;
        }

        #titdiv {
            text-align: center;
            margin-top: 12%;
            margin-left: 20%;
            margin-bottom: 0;
            color: #F5F3DB;
            border-radius: 5px;
            width: 60%;
            background: rgba(255, 255, 255, 0.2);
            padding: 5% 0;
        }

        #btn {
            width: 10%;
            height: 3em;
            border-radius: 5px;
            border: 0;
            background-color: #B6BF00;
            color: #F5F3DB;
            margin-left: 25%;
            background: rgba(255, 255, 255, 0.2);
        }
        #downdiv {
            margin-top: 10px;
            margin-left: 28%;
            color: #F5F3DB;
        }
    </style>
</head>
<body>
    <div id="titdiv">
        <h1 style="font-size:3em">欢迎进入积分计划管理系统</h1>
        <span><strong style="color: #DD5044; " id="second">10</strong> 秒 后 自 动 进 入 </span>
    </div>
    <div id="downdiv">
      <button id="btn" onclick="window.location.href = 'WeChatRedirect'" style='cursor: pointer;'> 点击进入>> </button>
    </div>
</body>
</html>
<script>
    var iCount = null;
    $(function () {
        iCount = setInterval("change_second()", 1000)
    })

    function change_second() {
        var _second = $("#second").html();
        if (_second > 0) {
            $("#second").html(_second - 1);
        } else {
            clearInterval(iCount);
            window.location.href = "WeChatRedirect";
           
        }
    }
</script>
