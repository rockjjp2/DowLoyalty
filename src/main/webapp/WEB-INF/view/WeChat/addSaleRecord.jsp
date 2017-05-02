<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>忠诚度计划</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/Resources/html/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/Resources/html/css/font-awesome.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/Resources/html/css/basic.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/Resources/html/css/addSaleRecord.css"/>
    <script src="${pageContext.request.contextPath}/Resources/html/js/jquery-1.8.2.min.js"></script>
    <script src="${pageContext.request.contextPath}/Resources/html/js/addSaleRecord.js"></script>
    <script type="text/javascript">
    	$(function(){
    		$("#selectProject").change(function(){
    		var projectId=this.value;
    		   $.ajax({
    			   type: "POST",
    			   url:"ProjectChangeAjax",
    			   data: {"projectId":projectId},
    			   dataType: "json",
    			   success:function(data){
						var json=eval(data);
						//产品类别
						$("#retailerSelect option").remove();
						$("#retailerSelect").append("<option value='0'>--请选择零售商--</option>");
						if(json.message != "-1")
						{
							$.each(json.retailers,function (index,entity){
								var Str1="<option value='"+entity.id+"'>"+entity.chineseName+"</option>"
								$("#retailerSelect").append(Str1);
	   						});
						}
						$("#productcategorsselect option").remove();
						$("#productcategorsselect").append("<option value='0'>--请选择产品类别--</option>");
						if(json.message != "-1")
						{
							$.each(json.productCategories,function (index,entity){
								var Str1="<option value='"+entity.id+"'>"+entity.name+"</option>"
								$("#productcategorsselect").append(Str1);
	   						});
						}
						$("#productfamilyselect option").remove();
						$("#productfamilyselect").append("<option value='0'>--请选择产品家族--</option>");
						$("#productselect option").remove();
						$("#productselect").append("<option value='0'>--请选择产品--</option>");
						if(json.message == "-1")
						{
							$("#submitSalesRecord").attr("disabled",true);
							$("#submitSalesRecord").css("background-color","gray");
							alert("活动还未开始");
						}
						else
						{
							$("#submitSalesRecord").attr("disabled",false);
							$("#submitSalesRecord").css("background-color","");
						}
    			   }
    		   })
    		})
    		$("#productcategorsselect").change(function(){
    			var categoryId=this.value;
    			var projectId=$("#selectProject").val();
    			$.ajax({
     			   type: "POST",
     			   url:"ProductCatChangeAjax",
     			   data: {"categoryId":categoryId,"projectId":projectId},
     			   dataType: "json",
     			   success:function(data){
     				  var json=eval(data);
						//产品家族
  					$("#productfamilyselect option").remove();
  					$("#productfamilyselect").append("<option value='0'>--请选择产品家族--</option>");
						$.each(json.productFamilies,function (index,entity){
							var Str1="<option value='"+entity.id+"'>"+entity.name+"</option>"
							$("#productfamilyselect").append(Str1);
  					});
     			   }
    			})
    		})
    		$("#productfamilyselect").change(function(){
    			var familyId=this.value;
    			var projectId=$("#selectProject").val();
    			var categoryId=$("#productcategorsselect").val();
    			$.ajax({
     			   type: "POST",
     			   url:"ProductFamChangeAjax",
     			   data: {"familyId":familyId,"projectId":projectId,"categoryId":categoryId},
     			   dataType: "json",
     			   success:function(data){
     				  var json=eval(data);
     				  //产品
					$("#productselect option").remove();
					$("#productselect").append("<option value='0'>--请选择产品--</option>");
							$.each(json,function (index,entity){
							var Str1="<option value='"+entity.id+"'>"+entity.name+"</option>"
							$("#productselect").append(Str1);
					});
     			   }
    			})
    		})
    		$("#submitSalesRecord").click(function(){
    			var projectId=$("#selectProject").val();
    			var startDate = $("#selectProject").find("option:selected").data("time");
    			var retailerId=$("#retailerSelect").val();
    			var categoryId=$("#productcategorsselect").val();
    			var familyId=$("#productfamilyselect").val();
    			var productId=$("#productselect").val();
    			var nums=$("#nums").val()-0;
    			var sales=$("#sales").val()-0;
    			//数字验证
    			var exp = /^[0-9]+$/;
    			if(startDate != null && startDate != "")
    			{
    				var date = new Date(startDate);
    				if(date > new Date())
    				{
    					alert("活动还未开始！");
    					return false;
    				}
    			}
    			if("0"==retailerId){
    				alert("请选择零售商信息！");
    			}else if("0"==productId){
    				alert("请选择产品！");
    			}else if(nums==0 ||!exp.test(nums)){
    				alert("请正确填写数量！");
    			}else if(sales==0 ||!exp.test(sales)){
    				alert("请正确填写销售额！");
    			}else{
    				$("#submitSalesRecord").attr("disable",true);
    				$.ajax({
    	     			   type: "POST",
    	     			   url:"AddSaleRecordDo",
    	     			   data: {"projectId":projectId,"retailerId":retailerId,
	    					"familyId":familyId,"productId":productId,
	    					"nums":nums,"sales":sales},
    	     			   dataType: "text",
    	     			   success:function(data){
    	     				  window.location.href="addSalesrecord?msg=1";
    	     			   },
	    					error:function(){
	    						alert("系统服务正忙，请稍后再试！");
	    					}
    				});
    	    	}
    		})
    		$("#sear_ret").click(function(){
    			var projectId=$("#selectProject").val();
    			var name=$("#retailerName").val();
    			$.post("searchReatilerAjax",//url
    					{"projectId":projectId,"name":name,},//data
    					function(data){
    						var json=eval(data);
    						$("#retailerTable tr").remove();
    	    				   $.each(json,function (index,entity){
    	    					   var Str1="<tr><td data-id='"+entity.id+"'data-content='"+entity.chineseName+"'>";
    	    					   var Str2="<input type='radio' id='"+entity.id+"' data-id='"+entity.id+"'class='radio_121' name='radio_Retailers' value='"+entity.chineseName+"'/></td>"
    	    					   var Str3="<td>"+entity.chineseName+"</td><td>"+entity.mobile+"</td><br/></tr>";
    	    					   $("#retailerTable").append(Str1+Str2+Str3);
    	    					});
    						
    					},"json");
    		})
    	})
    </script>
    <style>
        select {
            border: 1px solid #B6BF00;
            width:30%;
            height:25px;
            border-radius:4px;
        }

        /*弹出层*/
        .output {
            position: absolute;
            top: 15%;
            left: 7%;
            width: 80%;
            border: 15px solid #EBF7B3;
            z-index: 1002;
            border-radius: 16px;
            height:35%;
        }

        .Dialog-close {
            float: right;
            font-size: 26px;
            font-weight: 100;
            line-height: 1;
            color: #fff;
            text-shadow: 0 1px 0 #fff;
            background: #ACB317;
            height: 35px;
            width: 35px;
            border-radius: 100%;
            padding-bottom: 5px;
            margin: 0;
            margin-top: -30px;
            margin-right: -30px;
        }

        .hide {
            position: absolute;
            top: 0%;
            left: 0%;
            width: 100%;
            height: 100%;
            background-color: #f5f5f5;
            z-index: 1001;
            -moz-opacity: 0.8;
            opacity: .80;
        }

        .line {
            border: 2px dashed #C9CB92;
            background-color: #FFFFFF;
            text-align: center;
            margin-left: -4px;
            height:100%;
        }

        #People_Content tr th {
            text-align: center ;
            /*border-bottom:1px solid #CCC;*/
        }
        /*#People_Content tr td {
         border-bottom:1px dashed #CCC
        }*/

        #People_header {
            margin: 2%;
        }
    </style>
</head>
<body>
    <header>
        <img src="${pageContext.request.contextPath}/Resources/html/images/backImg.png" alt=""/>
        <h4>增加销售记录</h4>
    </header>

    <div class="container">
    <span>${MSG}</span>
        <div class="clearfix item">
            <label for="project" class="col-xs-4 col-xs-offset-1">项目名</label>
            <select id="selectProject"><option value="0">--请选择--</option>
            <c:forEach items="${projects}" var="pj">
            <option value="${pj.id}" data-time = "${pj.startDate}">${pj.name}</option>
            </c:forEach>
            </select>
        </div>
        <div class="clearfix item">
            <label for="dealer" class="col-xs-4 col-xs-offset-1">零售商名</label>
            <select id="retailerSelect"><option value='0'>--请选择零售商--</option>
				<c:forEach items="${retailers}" var="retailer">
            		<option value="${retailer.id}">${retailer.chineseName}</option>
	            </c:forEach>
            </select>
            <input class="choosePeople popUp" style="background-color: #B6BF00;color:#fff;border-radius: 0.3rem;"  id="Retailers" type="button" value="搜索零售商">
            <label id="lblRetailers" hidden=""></label>
        </div>
        <div class="clearfix item">
            <label for="dealer" class="col-xs-4 col-xs-offset-1">产品类别</label>
            <select id="productcategorsselect"><option value="0">--请选择产品类别--</option>
              <c:forEach items="${productcategors }" var="c">
            <option value="${c.id}">${c.name}</option>
            </c:forEach>
            </select>
        </div>
        <div class="clearfix item">
            <label for="dealer" class="col-xs-4 col-xs-offset-1">产品家族</label>
            <select id="productfamilyselect"><option value="0">--请选择产品家族--</option>

            </select>
        </div>
        <div class="clearfix item">
            <label for="product" class="col-xs-4 col-xs-offset-1">产品</label>
            <select id="productselect"><option value="0">--请选择产品--</option>
            </select>
        </div>
        <!--<div class="clearfix item">
            <label for="norms" class="col-xs-4 col-xs-offset-1">规格</label>
            <input id="norms" type="text" class="col-xs-7" placeholder="请选择规格"/>
        </div>-->
        <div class="clearfix item">
            <label for="nums" class="col-xs-4 col-xs-offset-1">数量</label>
            <input id="nums" type="text" class="col-xs-7" placeholder="请输入数量"/>
        </div>
        <div class="clearfix item">
            <label for="sales" class="col-xs-4 col-xs-offset-1">销售额</label>
            <input id="sales" type="text" class="col-xs-7" placeholder="请输入销售额" />
        </div>
        <div class="clearfix" style=" margin-left: 2em;">
            <button class="col-xs-4 col-xs-offset-4 style-bg-green" id="submitSalesRecord">增加记录</button>
        </div>

        <!--选择零售商-->
        <div id="layer" style="display: none">
            <div class="output retailer" >
                <button type="button" class="Dialog-close" data-dismiss="modal" aria-hidden="true" id="close">×</button>
                <div class="line">
                    <table id="People_header" style="border-spacing: 15px;" border="0">
                        <tr>
                            <td style="width: 20%; text-align: right;">零售商:</td>
                            <td style="width:50%"><input id ="retailerName" style="width: 90%; border: 1px solid #ccc; margin-bottom: 2%;border-radius:3px" type="text" /></td>
                            <td style="width:30%"><input id="sear_ret"  style="margin: 0; width: 80%; margin-bottom: 2%; border-radius: 3px; background-color: #B6BF00; color: #fff; " type="button" value="查询" > </td>
                        </tr>
                    </table>
                    <div style="overflow-y: scroll; height: 70%">
                        <table id="People_Content" class="table_content" border="0" cellpadding="0" cellspacing="0" width="100%" style="height:100px;overflow-y:auto">
                            <thead>
                                <tr>
                                    <th style="width:10%;"></th>
                                    <th style="width:40%;">零售商</th>
                                    <th style="width:40%;">手机号</th>
                                </tr>
                            </thead>
                            <tbody id="retailerTable">
                            </tbody>
                        </table>
							
                    </div>
                    <button id="Person_Submit" class="person_submit" style="width: 25%;  background-color: #B6BF00; border: 1px solid #B6BF00;color:#fff;margin-top:10px ">确定</button>
                </div>
            </div>
        </div>
       </div>

    <script src="${pageContext.request.contextPath}/Resources/html/js/jquery-1.8.2.min.js"></script>
    <script type="text/javascript">
        /*点击菜单-页面跳转*/
        $(".menu").click(function(){
            var pageID = $(this).attr("id");
            location.href = pageID;
        });
        
        $("#sear_ret").click(function(){
        	var name=document.getElementById("retailerName").value;
        });
    </script>
</body>
</html>