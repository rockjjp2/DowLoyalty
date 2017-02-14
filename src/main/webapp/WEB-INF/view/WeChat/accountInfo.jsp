<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
 <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>DOW</title>
    <link rel="stylesheet" href="/DowLoyalty/Resources/html/css/font-awesome.min.css"/>
    <link rel="stylesheet" href="/DowLoyalty/Resources/html/css/basic.css"/>
    <link rel="stylesheet" href="/DowLoyalty/Resources/html/css/accountInfo.css"/>

</head>
<body>
    <header>
        <img src="/DowLoyalty/Resources/html/images/accountInfo.png" alt=""/>
    </header>

    <div class="container txt-center">
        <div class="txt-center clearfix">
            <p>尊敬的<span class="style-green">${accountInfo.lvName}会员</span></p>
            <p><span class="style-green">${accountInfo.provinceName}经销商${accountInfo.reName}</span>，您好！</p>
        </div>
        <div class="txt-center clearfix">
            <p>会员号：<span class="style-green">${accountInfo.reId}</span></p>
            <p>累计积分：<span class="style-green">${accountInfo.totalPoints}</span>分</p>
            <p>可兑换积分：<span class="style-green">${accountInfo.remainPoints}</span>分</p>
        </div>
        <div class="txt-center">
            <div class="cup">
                <i class="fa fa-trophy style-green"></i>
                <div id="numInCup" class="style-white">125</div>
            </div>
            <p>您当前累计积分省内排名为：<span class="style-green">${accountInfo.curRank}</span></p>
            <p>距离第<span class="style-green">${accountInfo.nextRank}</span>名只差<span class="style-green">${accountInfo.toUpPersonRemainPoints}</span>分</p>
        </div>
        <div class="txt-center">
            <div class="circle">
                <div class="circle-bl1 style-bg-green"></div>
                <div class="circle-text style-green">${accountInfo.rankPercent}%</div>
            </div>
            <p>已打败<span class="style-green">${accountInfo.rankPercent}%</span>的零售商</p>
            <p>距离升级<span class="style-green">${accountInfo.nextLv}会员</span>只差<span class="style-green">${accountInfo.toNextLvRemainPoints}</span>积分</p>
        </div>
    </div>

    <script src="/DowLoyalty/Resources/html/js/jquery-1.8.2.min.js"></script>
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