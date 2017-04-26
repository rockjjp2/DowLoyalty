<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
 <title></title>
    <link href="${pageContext.request.contextPath}/Resources/html/css/Datalist.css" rel="stylesheet" />
    <link href="${pageContext.request.contextPath}/Resources/html/css/paging.css" rel="stylesheet" />
    <script src="${pageContext.request.contextPath}/Resources/html/js/jquery-1.4.2.min.js"></script>
    <style>
        a {
            text-decoration: none;
            cursor: pointer;
            color: #000;
            font-family: 宋体;
            font-size: 1.1em;
            font-weight:900;
        }
        
    </style>
    
    <script type="text/javascript">
    	
    </script>
</head>
<body>
    <a href="IntegralItem.html" target="_self">&lt;<span style="font-size: 0.9em; font-family: 宋体; font-weight: 400; "> 返回</span></a>
    <div class="content-flickr" style="height:400px">
        <table class="top-table">
            <tr>
                <td class="top-title" >销售记录</td>
                <td style="width:70%">
                    <input type="text" id="txtCardId" name = "NameOrId" placeholder="请输入会员名/会员号" />

                </td>
                <td><input type="button" class="btnOperate" value="导出报表" /></td>
            </tr>
        </table>
        <table class="content-table" cellspacing="0">
            <thead>
                <tr>
                    <td>会员名</td>
                    <td>会员号</td>
                    <td>日期</td>
                    <td>产品类别</td>
                    <td>产品家族</td>
                    <td>产品</td>
                    <td>数量</td>
                    <td>销售额</td>
                </tr>
            </thead>
            <tbody>
                <tr class="table-tr-odd">
                    <td>Becky</td>
                    <td>20170119</td>
                    <td>2017.01.19</td>
                    <td>Booking</td>
                    <td>Booking</td>
                    <td>Booking</td>
                    <td>100</td>
                    <td>100</td>
                </tr>
                <tr class="table-tr-even">
                    <td>Becky</td>
                    <td>20170119</td>
                    <td>2017.01.19</td>
                    <td>Booking</td>
                    <td>Booking</td>
                    <td>Booking</td>
                    <td>100</td>
                    <td>100</td>
                </tr>
                <tr class="table-tr-odd">
                    <td>Becky</td>
                    <td>20170119</td>
                    <td>2017.01.19</td>
                    <td>Booking</td>
                    <td>Booking</td>
                    <td>Booking</td>
                    <td>100</td>
                    <td>100</td>
                </tr>
                <tr class="table-tr-even">
                    <td>Becky</td>
                    <td>20170119</td>
                    <td>2017.01.19</td>
                    <td>Booking</td>
                    <td>Booking</td>
                    <td>Booking</td>
                    <td>100</td>
                    <td>100</td>
                </tr>
                <tr class="table-tr-odd">
                    <td>Becky</td>
                    <td>20170119</td>
                    <td>2017.01.19</td>
                    <td>Booking</td>
                    <td>Booking</td>
                    <td>Booking</td>
                    <td>100</td>
                    <td>100</td>
                </tr>
                <tr class="table-tr-even">
                    <td>Becky</td>
                    <td>20170119</td>
                    <td>2017.01.19</td>
                    <td>Booking</td>
                    <td>Booking</td>
                    <td>Booking</td>
                    <td>100</td>
                    <td>100</td>
                </tr>
                <tr class="table-tr-odd" id="endtr">
                    <td>Becky</td>
                    <td>20170119</td>
                    <td>2017.01.19</td>
                    <td>Booking</td>
                    <td>Booking</td>
                    <td>Booking</td>
                    <td>100</td>
                    <td>100</td>
                </tr>
            </tbody>
        </table>
        <div class="datalist-paging">
            <ul id="pagination-flickr">
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
    </div>


</body>

</html>