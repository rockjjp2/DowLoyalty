<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
    <title>忠诚度计划</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/Resources/html/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/Resources/html/css/basic.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/Resources/html/css/deliverList.css"/>
    <script src="${pageContext.request.contextPath}/Resources/html/js/jquery-1.8.2.min.js"></script>
    <script type="text/javascript">
    	$(function(){
    		Deliver(0,"");
    		$("#pending").click(function(){
    		Deliver(0,"");
    		});
    		$("#complete").click(function(){
    		Deliver(1,"已");
    		});
    	});
    	function Deliver(status,Str){
    		$.ajax({
  			   type: "POST",
  			   url:"deliverAjax",
  			   data: {"status":status},
  			   dataType: "json",
  			   success:function(data){
				  $("#contain div[name=datadeliver]").remove();
  				  var json=eval(data);
					$.each(json,function (index,entity){
					var Str1="<div class='clearfix item' name='datadeliver'><div class='data col-xs-8 col-xs-offset-1'>";
					var Str2="<h5>"+entity.provinceName+"零售商 "+entity.retailerName+"</h5>";
					var Str3="<p class='prize'>"+entity.goodsName+"*"+entity.exchangAmount+"</p></div>";
					if("已"==Str){
					var Str4="<button name='sendgoods' class='col-xs-3 style-bg-green' id="+entity.exchangId+" disable>已发货</button></div>";
					}else{
					var Str4="<button name='sendgoods' class='col-xs-3 style-bg-green' id="+entity.exchangId+" onclick='Cilck(this)'>发货</button></div>";
					}
					$("#contain").append(Str1+Str2+Str3+Str4);
					});
  			   }
 			})
    	}
 			function Cilck(Object){
    			$.ajax({
    	  			   type: "POST",
    	  			   url:"doDeliverAjax",
    	  			   data: {"key":Object.id},
    	  			   success:function(){
    						alert("发货成功");
    						Deliver(0,"");
    	  			   },
    	  			   error:function(){
    	  				   alert("系统正忙，请稍后再试。");
    	  			   }
    	 			})
    		} 
    </script>
</head>
<body>
    <header>
        <img src="${pageContext.request.contextPath}/Resources/html/images/backImg.png" alt=""/>
        <h4>发货列表</h4>
    </header>

    <div class="container" id="contain">
        <div class="clearfix">
            <div class="menu-btn col-xs-8 col-xs-offset-2">
                <div class="tag col-xs-6 style-btn-green style-bg-green" id="pending">待发货</div>
                <div class="tag col-xs-6 style-btn-green" id="complete">已发货</div>
            </div>
        </div>
		
<!--         <div class="clearfix item">
            <div class="data col-xs-8 col-xs-offset-1">
                <h5>xx省经销商2</h5>

                <p class="prize">滚筒洗衣机</p>
            </div>
            <button class="col-xs-3 style-bg-green">发货</button>
        </div> -->
    </div>
    <script type="text/javascript">
        $(".tag").click(function(){
            $(".tag").removeClass("style-bg-green");
            $(this).addClass("style-bg-green");
        })
    </script>
</body>

</html>