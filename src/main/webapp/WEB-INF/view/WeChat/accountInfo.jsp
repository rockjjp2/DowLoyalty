<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
 <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>忠诚度计划</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/Resources/html/css/font-awesome.min.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/Resources/html/css/basic.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/Resources/html/css/accountInfo.css"/>

</head>
<body>
    <header>
        <img src="${pageContext.request.contextPath}/Resources/html/images/accountInfo.png" alt="" style="height:auto"/>
    </header>

    <div class="container txt-center">
        <div class="txt-center clearfix">
            <p>尊敬的<span class="style-green">${accountInfo.lvName}会员</span></p>
            <p><span class="style-green">${accountInfo.provinceName}零售商${accountInfo.reName}</span>，您好！</p>
        </div>
        <div class="txt-center clearfix">
            <p>会员号：<span class="style-green">${accountInfo.reId}</span></p>
            <p>累计积分：<span class="style-green">${accountInfo.totalPoints}</span>分</p>
            <p>可兑换积分：<span class="style-green">${accountInfo.remainPoints}</span>分</p>
        </div>
        <c:choose>
        	<c:when test="${isVisible}">
        <div class="txt-center">
            <div class="cup">
                <i class="fa fa-trophy style-green"></i>
                <div id="numInCup" class="style-white">${accountInfo.curRank}</div>
            </div>
            <c:if test="${not empty accountInfo.rankPercent}">
            <p>您当前累计积分项目内排名为：<span class="style-green">${accountInfo.curRank}</span></p>
            <c:if test="${accountInfo.curRank != 1}">
            <p>距离第<span class="style-green">${accountInfo.nextRank}</span>名只差<span class="style-green">${accountInfo.toUpPersonRemainPoints}</span>分</p>
            </c:if>
             </c:if>
        </div>
        <div class="txt-center">
        <c:if test="${not empty accountInfo.rankPercent}">
            <div class="circle">
                <div class="circle-bl1 style-bg-green"></div>
                <div class="circle-text style-green">${accountInfo.rankPercent}%</div>
            </div>
            <p>已打败<span class="style-green">${accountInfo.rankPercent}%</span>的零售商</p>
        </c:if>
            <c:if test="${not empty accountInfo.nextLv}">
            <p>距离升级<span class="style-green">${accountInfo.nextLv}会员</span>只差<span class="style-green">${accountInfo.toNextLvRemainPoints}</span>积分</p>
            </c:if>
        </div>
        	</c:when>
        </c:choose>
    </div>
	
    <script src="${pageContext.request.contextPath}/Resources/html/js/jquery-1.8.2.min.js"></script>
    <script>
        $(function(){
            //按百分比显示环
            var bl = parseInt($('.circle-text').html())*3.6;
            var rotateNum = bl;
            var blHtml = '';
            if(bl > 180){
                rotateNum = bl - 180;
                blHtml += '<div class="circle-bl2 style-bg-green">';
                blHtml += '<div class="circle-bl4" style="-webkit-transform:rotate(' + rotateNum + 'deg);transform:rotate(' + rotateNum + 'deg);"></div>';
                blHtml += '</div>';
                //$('.circle-bl1').remove($('.circle-bl3'));
                $('.circle-bl1').after(blHtml);
            }else{
                blHtml += '<div class="circle-bl3" style="-webkit-transform:rotate(' + rotateNum + 'deg);transform:rotate(' + rotateNum + 'deg);"></div>';
                $('.circle-bl1').append(blHtml);
            }
        })
        

    </script>

</body>

</html>