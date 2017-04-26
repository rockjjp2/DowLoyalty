<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title></title>
    <link href="${pageContext.request.contextPath}/Resources/html/css/Style.css" rel="stylesheet" />
    <link href="${pageContext.request.contextPath}/Resources/html/css/Addproject.css" rel="stylesheet" />
    <script src="${pageContext.request.contextPath}/Resources/html/js/jquery.js"></script>
    <link href="${pageContext.request.contextPath}/Resources/html/css/jquery.datetimepicker.css" rel="stylesheet" />
    <script src="${pageContext.request.contextPath}/Resources/html/js/jquery.datetimepicker.js"></script>
    <script src="${pageContext.request.contextPath}/Resources/html/js/Common.js"></script>
    <script src="${pageContext.request.contextPath}/Resources/html/js/JquerySession.js"></script>
    <script src="${pageContext.request.contextPath}/Resources/html/js/Addproject.js"></script>
    <style>
        #goback {
            text-decoration: none;
            cursor: pointer;
            color: #000;
            font-family: 宋体;
            font-size: 1.1em;
            font-weight: 900;
        }
    </style>
    <!-- <script>
        $(function () {
            $(".nav-right").css("height", $(window).height()-40);
        })
    </script> -->
</head>
<body>
    <!--<div style=" padding-top: 10px; padding-bottom: 10px; margin-top: 0; height:40px;"> <a id="goback" href="IntegralItem.html" target="_self">&lt;<span style="font-size: 0.9em; font-family: 宋体; font-weight: 400; "> 返回</span></a></div>-->
    <!-- 左侧 -->
    <div class="nav-left">
        <ul class="sub-nav">
            <li><span class="li-Yspan"></span><a href="#Activity" onclick="return false;" class="a-Ycolor">活动信息</a></li>
            <li class="Person"><span class="li-Nspan"></span><a href="#Person" onclick="return false;" class="a-Ncolor">零售商</a></li>
            <li class="Person2"><span class="li-Nspan"></span><a href="#Person2" onclick="return false;" class="a-Ncolor">推广员</a></li>
            <li><span class="li-Nspan"></span><a href="#Gift" onclick="return false;" class="a-Ncolor">礼品配置</a></li>
            <li><span class="li-Nspan"></span><a href="#Product" onclick="return false;" class="a-Ncolor">产品配置</a></li>
            <li><span class="li-Nspan"></span><a href="#Grade" onclick="return false;" class="a-Ncolor">等级配置</a></li>
        </ul>
    </div>
    <!-- 右侧 -->
    <div class="nav-right">
        <!--活动信息  -->
        <input type="hidden" id="edit_id"  name="edit_id" value="${projectId}">
        <table id="Activity" class="table actived" style="display:block;text-align:left">
            <tbody>
                <tr>
                    <td style="width: 15%;">项目省份：</td>
                    <td style="width: 80%;">
                        ${projectProvince.provinceName }
                    </td>
                </tr>
                <tr>
                    <td>开始时间：</td>
                    <td>${projectProvince.startDate}</td>
                </tr>
                <tr>
                    <td>结束时间：</td>
                    <td>${projectProvince.endDate}</td>
                </tr>
                <tr>
                    <td>项目标题：</td>
                    <td>${projectProvince.projectTitle}</td>
                </tr>
                <tr>
                    <td>活动介绍：</td>
                    <td>${projectProvince.description}</td>
                </tr>
                 <tr>
                    <td>海报图片：</td>
                    <td style="text-align: left">
                            <img id="Poster_img1" border="0" src="data:image/jpg;base64,${projectProvince.placardBase64}" width="90" height="90">
                    </td>
                </tr>
                <tr>
                    <td>项目背景图：</td>
                    <td style="text-align: left">
                        <img id="Back_img1" border="0" src="data:image/jpg;base64,${projectProvince.backgroundBase64}" width="90" height="90">
                    </td>
                </tr>
            </tbody>
        </table>
        <!-- 参与的零售商 -->
        <div id="Person" class="actived">
            <fieldset>
                <div class="div-left field-div">已选:</div>
                <div class="div-left" style="width:90%;">
                    <ul id="Person_UL">
                       <c:forEach items="${retailers}" var="r">
                        <li><span>${r.chineseName }</span></li>
                        </c:forEach>
                    </ul>
                </div>
            </fieldset>
        </div>


        <!-- 参与的推广员 -->
        <div id="Person2" class="actived">
            <fieldset>
                <div class="div-left field-div">已选:</div>
                <div class="div-left" style="width:90%;">
                    <ul id="Promoter_UL" class="li_choose">
                        <c:forEach items="${promoters}" var = "p">
                        <li><span>${p.chineseName }</span></li>
                       </c:forEach>
                    </ul>
                </div>
            </fieldset>
             <fieldset>
                <div class="div-left field-div" style="width:15%">发货推广员:</div>
                <div class="div-left field-choose">${promoter.chineseName}</div>
            </fieldset>
        </div>
        <!-- 礼品配置 -->
        <table id="Gift" class="table actived">
            <thead>
                <tr>
                    <th width="50%">礼品</th>
                    <th width="50%">兑换积分</th>

                </tr>
                <tr>
                    <td class="td_line" colspan="2"></td>
                </tr>
            </thead>
            <tbody>
            <c:if test="${not empty exchangeShopGoods}">
            <c:forEach items="${exchangeShopGoods}" var="goods">
                <tr>
                    <td><a>${goods.gName }</a></td>
                    <td>${goods.exPoints }</td>
                </tr>
                </c:forEach>
            </c:if>
            </tbody>
        </table>

        <!-- 产品配置 -->
        <table id="Product" class="table actived">
            <thead>
                <tr>
                    <th style="width:25%">产品类别</th>
                    <th style="width:25%">产品家族</th>
                    <th style="width:25%">产品</th>
                    <th style="width:25%">1积分对应销售额</th>
                </tr>
                <tr>
                    <td class="td_line" colspan="4"></td>
                </tr>
            </thead>
            <tbody>
            <c:if test="${not empty productInfos}">
            <c:forEach items="${productInfos}" var="product">
                <tr>
                    <td><a>${product.productCategory }</a></td>
                    <td>${product.productFamily }</td>
                    <td>${product.productName }</td>
                    <td>${product.salesAmount }</td>
                </tr>
                </c:forEach>
            </c:if>
            </tbody>
        </table>


        <!-- 等级配置 -->
        <table id="Grade" class="table actived">
            <thead>
                <tr>
                    <th width="50%">等级</th>
                    <th width="50%">积分标线</th>
                </tr>
                <tr>
                    <td class="td_line" colspan="2"></td>
                </tr>
            </thead>
            <tbody>
            <c:if test="${not empty pointsLevels}">
            <c:forEach items="${pointsLevels}" var="pointsLevel">
                <tr>
                    <td><a>${pointsLevel.name }</a></td>
                    <td>${pointsLevel.points }</td>
                </tr>
                </c:forEach>
            </c:if>
            </tbody>
        </table>
    </div>

    <!-- 显示内容 -->
    <div id="layer" style="display:none">
        <div class="output">
            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
            <div class="line">
                <table style="border-spacing: 15px;">
                    <tr>
                        <td style="width: 20%; text-align: right;">零售商:</td>
                        <td style="width:50%"><input style="width:90%;" type="text" /></td>
                        <td style="width:30%"><input style="margin:0;" type="button" value="查询" /> </td>
                    </tr>
                </table>

                <table id="People_Content" class="table_content" border="0" cellpadding="0" cellspacing="0">
                    <thead>
                        <tr>
                            <th style="width:10%;"></th>
                            <th style="width:40%;">零售商</th>
                            <th style="width:40%;">省份</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td data-id="1" data-content="财付通"><input type="checkbox" onclick="return false;" id="checkbox_a1" class="chk_1" disabled="disabled" /><label for="checkbox_a1"></label></td>
                            <td>财付通</td>
                            <td>上海</td>
                        </tr>
                        <tr>
                            <td data-id="2" data-content="支付宝"><input type="checkbox" onclick="return false;" id="checkbox_a2" class="chk_1" disabled="disabled" /><label for="checkbox_a2"></label></td>
                            <td>支付宝</td>
                            <td>广州</td>
                        </tr>
                        <tr>
                            <td data-id="3" data-content="泰迪"><input type="checkbox" onclick="return false;" id="checkbox_a3" class="chk_1" disabled="disabled" /><label for="checkbox_a3"></label></td>
                            <td>泰迪</td>
                            <td>北京</td>
                        </tr>
                        <tr>
                            <td data-id="4" data-content="奥迪"><input type="checkbox" onclick="return false;" id="checkbox_a4" class="chk_1" disabled="disabled" /><label for="checkbox_a4"></label></td>
                            <td>奥迪</td>
                            <td>北京</td>
                        </tr>
                    </tbody>
                </table>

                <table style="width:100%;">
                    <tbody>
                        <tr>
                            <td style="width:50%;"><button id="Person_Submit" class="person_submit">确定</button></td>
                            <td style="width:50%;">
                                <div class="paging">
                                    <ul id="pagination-flickr" class="pagination">
                                        <li class="previous-off">上一页</li>
                                        <li class="active">1</li>
                                        <li><a href="?page=2">2</a></li>
                                        <li><a href="?page=3">3</a></li>
                                        <li><a href="?page=4">4</a></li>
                                        <li><a href="?page=5">5</a></li>
                                        <li><a href="?page=6">6</a></li>
                                        <li><a href="?page=7">7</a></li>
                                        <li class="next"><a href="?page=8">下一页</a></li>
                                    </ul>
                                </div>
                            </td>
                        </tr>
                    </tbody>
                </table>
            </div>
            <!--<div class="line">
                <table style="border-spacing: 15px;">
                    <tr>
                        <td style="width:20%; text-align: right;">礼品:</td>
                        <td style="width:80%"><input style="" type="text" /></td>
                    </tr>
                </table>
            </div>-->
        </div>

        <div class="hide"></div>
    </div>
</body>
</html>
