<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
    <link href="${pageContext.request.contextPath}/Resources/html/css/Datalist.css" rel="stylesheet" />
    <link href="${pageContext.request.contextPath}/Resources/html/css/paging.css" rel="stylesheet" />
    <link href="${pageContext.request.contextPath}/Resources/html/css/gift_Config.css" rel="stylesheet" />
    <script src="${pageContext.request.contextPath}/Resources/html/js/jquery-1.4.2.min.js"></script>
    <script src="${pageContext.request.contextPath}/Resources/html/js/gift.js"></script>
    <style>

            .content-table tr td {
                width: 30%;
            }

        select {
            /*Chrome和Firefox里面的边框是不一样的，所以复写了一下*/
            /*很关键：将默认的select选择框样式清除*/
            appearance: none;
            -moz-appearance: none;
            -o-appearance: 3px;
            -webkit-appearance: none; /*for chrome*/
            /*在选择框的最右侧中间显示小箭头图片*/
            background: url("${pageContext.request.contextPath}/Resources/html/images/smallImg/green-triangle.png") no-repeat scroll right center transparent;
            background-size: 15% 90%;
            /*为下拉小箭头留出一点位置，避免被文字覆盖*/
            padding-right: 6%;
            padding-left: 2%;
            border: 1px solid #ADADAD;
            width: 50%;
            height: 24px;
            float: right;
            border-radius: 2px;
            -webkit-border-radius: 2px;
            -o-border-radius: 2px;
            -moz-border-radius: 2px;
        }

            /*清除ie的默认选择框样式清除，隐藏下拉箭头*/
            select::-ms-expand {
                display: none;
            }


    </style>
    <script>
      

        $(function () {
            var gift = new $gift_control();
            gift.Init();
        })
    </script>
  
</head>
<body>
    <div id="content-flickr">
        <table class="top-table" border="0">
            <tr>
                <td  >礼品配置</td>
                <td></td>
                <td><select id="select-province">
                        <option>湖南省</option>
                        <option>湖南省</option>
                        <option>湖南省</option>
                        <option>湖南省</option>
                        <option>湖南省</option>
                    </select></td>
            </tr>
        </table>
        <table class="content-table" cellspacing="0" border="0">
            <thead>
                <tr>

                    <td>礼品</td>
                    <td>分类</td>
                    <td>操作</td>
                </tr>
            </thead>
            <tbody>
                <tr class="table-tr-odd">
                    <td>面包</td>
                    <td>食品</td>
                    <td>删除</td>
                </tr>
                <tr class="table-tr-even">
                    <td>面包车</td>
                    <td>汽车</td>
                    <td>删除</td>
                </tr>
                <tr class="table-tr-odd">
                    <td>iPhone7</td>
                    <td>电器</td>
                    <td>删除</td>
                </tr>
                <tr class="table-tr-even">
                    <td>洗衣机</td>
                    <td>电器</td>
                    <td>删除</td>
                </tr>
                <tr class="table-tr-odd">
                    <td>面包</td>
                    <td>食品</td>
                    <td>删除</td>
                </tr>
                <tr class="table-tr-even">
                    <td>面包车</td>
                    <td>汽车</td>
                    <td>删除</td>
                </tr>
                <tr class="table-tr-odd">
                    <td>iPhone7</td>
                    <td>电器</td>
                    <td>删除</td>
                </tr>
            </tbody>
        </table>
        <div id="Add-gift" > ＋ <a href="#" id="btnAddgift">添加礼品</a></div>
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

    <div id="dialog_add">
        

    </div>


</body>

</html>