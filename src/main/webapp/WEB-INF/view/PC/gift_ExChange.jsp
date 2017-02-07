<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
    <link href="/DowLoyalty/Resources/html/css/Datalist.css" rel="stylesheet" />
    <link href="/DowLoyalty/Resources/html/css/paging.css" rel="stylesheet" />
</head>
<body>
    <div class="content-flickr" style="height:400px">
        <table class="top-table">
            <tr>
                <td  >兑换列表</td>
                <td>
                    <input type="text" id="txtCardId" placeholder="请输入会员名/会员号" />
                
                </td>
                <td><input type="button" class="btnOperate" value="导出报表" /></td>
            </tr>
        </table>
        <table class="content-table" cellspacing="0">
            <thead>
                <tr>
                    <td>日期</td>
                    <td>会员名</td>
                    <td>省份</td>
                    <td>礼品</td>
                    <td class="thead_event">数量</td>
                </tr>
            </thead>
            <tbody>
                <tr class="table-tr-odd">
                    <td>2017.01.19</td>
                    <td>Booking</td>
                    <td>Booking</td>
                    <td>Booking</td>
                    <td>100</td>
                </tr>
                <tr class="table-tr-even">
                    <td>2017.01.19</td>
                    <td>Booking</td>
                    <td>Booking</td>
                    <td>Booking</td>
                    <td>100</td>
                </tr>
                <tr class="table-tr-odd">
                    <td>2017.01.19</td>
                    <td>Booking</td>
                    <td>Booking</td>
                    <td>Booking</td>
                    <td>100</td>
                </tr>
                <tr class="table-tr-even">
                    <td>2017.01.19</td>
                    <td>Booking</td>
                    <td>Booking</td>
                    <td>Booking</td>
                    <td>100</td>
                </tr>
                <tr class="table-tr-odd">
                    <td>2017.01.19</td>
                    <td>Booking</td>
                    <td>Booking</td>
                    <td>Booking</td>
                    <td>100</td>
                </tr>
                <tr class="table-tr-even">
                    <td>2017.01.19</td>
                    <td>Booking</td>
                    <td>Booking</td>
                    <td>Booking</td>
                    <td>100</td>
                </tr>
                <tr class="table-tr-odd" id="endtr">
                    <td>2017.01.19</td>
                    <td>Booking</td>
                    <td>Booking</td>
                    <td>Booking</td>
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