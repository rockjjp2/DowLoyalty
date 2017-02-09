<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
    <title>DOW</title>
    <link rel="stylesheet" href="/DowLoyalty/Resources/html/css/font-awesome.min.css"/>
    <link rel="stylesheet" href="/DowLoyalty/Resources/html/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="/DowLoyalty/Resources/html/css/basic.css"/>
    <link rel="stylesheet" href="/DowLoyalty/Resources/html/css/giftInfo.css"/>
</head>
<body>
    <header>
        <img src="/DowLoyalty/Resources/html/images/backImg.png" alt=""/>
        <h4>礼品详情</h4>
    </header>

    <div class="container">
        <div class="clearfix">
            <div class="img-div col-xs-6 col-xs-offset-3">
                <img src="/DowLoyalty/Resources/html/images/deliverInfo.png" alt=""/>
            </div>
        </div>

        <div class="txt-center">
            <h5>1000元加油卡</h5>
            <p class="style-gray"><span class="style-green" id="score">10</span> 积分</p>
            <p class="style-gray">100元加油卡，共10张，可以在任何加油站使用</p>
        </div>

        <div class="numChange txt-center">
            <label class="col-xs-3 col-xs-offset-2" for="amount">购买数量：</label>
            <div id="minus" class="col-xs-1 style-bg-gray">
                <i class="fa fa-minus style-deepGray"></i>
            </div>
            <input id="amount" type="text" class="col-xs-2 style-bg-gray txt-center" value="">
            <div id="add" class="col-xs-1 style-bg-gray">
                <i class="fa fa-plus style-deepGray"></i>
            </div>
        </div>

        <div class="clearfix">
            <button id="convert" class="col-xs-6 col-xs-offset-3 style-bg-green">兑换</button>
        </div>
    </div>
    <script src="/DowLoyalty/Resources/html/js/jquery-1.8.2.min.js"></script>
    <script type="text/javascript">
        $(function(){
            $("#amount").val(1);
            var num;
            numOperate();
            convertPopup();
        });

        function numOperate(){
            num = parseInt($("#amount").val());
            $("#minus").click(function(){
                num -= 1;
                $("#amount").val(num);
            });
            $("#add").click(function(){
                num += 1;
                $("#amount").val(num);
            });

        };

        function convertPopup() {
            $("#convert").click(function(){
                num = parseInt($("#amount").val());
                var form;
                var cover = $("<div id='cover' class='clearfix'></div>");
                if(num <= 0){
                    form = $("<div id='contain' class='col-xs-10 col-xs-offset-1'>" +
                            "<h5 class='col-xs-10 col-xs-offset-1'>礼品数量不得小于1件！</h5>" +
                            "<button id='removeAll' class='col-xs-6 col-xs-offset-3 style-bg-green'>确定</button>" +
                            "</div>");
                }else{
                    form = $("<div id='contain' class='col-xs-10 col-xs-offset-1'>" +
                            "<h5 class='col-xs-10 col-xs-offset-1'>确定兑换该礼品？</h5>" +
                            "<button id='doubleConvert' class='col-xs-4 dBtn style-bg-green'>兑换</button>" +
                            "<button id='removeAll' class='col-xs-4 dBtn style-bg-green'>取消</button>" +
                            "</div>");
                }
                $("html").append(cover).append(form);
                operatePopup(cover,form);
            })
        }

        function operatePopup(cover,form){
            $("#removeAll").click(function(){
                cover.remove();
                form.remove();
            });
            $("#doubleConvert").click(function(){
                location.href = "giftStore.html";
            })
        }


//        function convertPopup(){
//            $("#convert").click(function(){
//                var cover = $("<div id='cover'></div>");
//                var form = $("<div id='form' class='col-xs-10 col-xs-offset-1'>" +
//                        "<label for='address'>地址</label>" +
//                        "<input type='text' id='address' style='width:100%'/>" +
//                        "<label for='contacts'>联系人</label>" +
//                        "<input id='contacts' type='text' style='width:100%'/>" +
//                        "<label for='tel'>电话</label>" +
//                        "<input id='tel' type='text' style='width:100%'/>" +
//                        "<button class='col-xs-8 col-xs-offset-2 style-bg-green'>确认兑换</button>" +
//                        "</div>");
//
//                $("html").append(cover).append(form);
//            })
//        }

    </script>
</body>

</html>