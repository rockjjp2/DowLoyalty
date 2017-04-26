<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri = "http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>忠诚度计划</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/Resources/html/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/Resources/html/css/font-awesome.min.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/Resources/html/css/basic.css"/>
    <script src="${pageContext.request.contextPath}/Resources/html/js/jquery.js"></script>
    <script src="${pageContext.request.contextPath}/Resources/html/js/wechatmain.js"></script>
    <script>
    $(function () {
        if (1${menunum} == 13) { 
            $(".menu").not("#homePage").css("width", "45%");//此处是只有3个菜单情况的宽度
        } else {
            $(".menu").not("#homePage").css("width", "30%"); //4个菜单情况的宽度    
        }
    })
	</script>
</head>

<body>
    <iframe src="homePage" id="iframeContent" frameborder="0" scrolling="no" width="100%" height="100%"></iframe> 
    <footer class="txt-center style-deepGray">
        <div id="homePage" class="menu style-btnBg-green"><i class="fa fa-home"></i></div>

        <c:forEach items="${map}" var="entry">
	        <div id="${entry.key}" class="menu">${entry.value}</div>
	    </c:forEach> 
    </footer>
    <script>
        var _Main = null;
        if (_Main == null) {
            _Main = new $main();
            _Main.Init();
        }

    </script>

</body>

</html>
