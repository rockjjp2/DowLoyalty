<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix='fmt' uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
    <link href="${pageContext.request.contextPath}/Resources/html/css/Datalist.css" rel="stylesheet" />
    <link href="${pageContext.request.contextPath}/Resources/html/css/paging.css" rel="stylesheet" />
     <script src="${pageContext.request.contextPath}/Resources/html/js/jquery.js"></script>
    <link href="${pageContext.request.contextPath}/Resources/html/css/jquery.datetimepicker.css" rel="stylesheet" />
    <script src="${pageContext.request.contextPath}/Resources/html/js/jquery.datetimepicker.js"></script>
    <script>
        $(function () {
            $('#startdate').datetimepicker({

                timepicker: false,
                format: 'Y/m/d',
                
                step: 10
            });
            $('#enddate').datetimepicker({
                timepicker: false,
                format: 'Y/m/d',
                formatDate: 'Y/m/d',
               // minDate: '-1970/01/01' // yesterday is minimum date
            });
            
            //分页问题
            //查询按钮触发事件
			$("#history_btnSearch").click(function(){
				search(1);
			});
			//获取后台传入的projectId
        	var msg = document.getElementById("in_pro").value;
            if(msg !=0){
        	    $("#pro_name").val(msg);
        	    search(1);
            }
            function search(num){
                var projectId = $("#pro_name").val();
             	var startDate = document.getElementById("startdate").value;
             	var endDate =  document.getElementById("enddate").value;
             	var retailerName =document.getElementById("txtCardName").value;
             	var page ="";
            	$.ajax({
             		type:"POST",
             		url:"querySaleRecord",
             		data:{"projectId":projectId,"startDate":startDate,"endDate":endDate,"retailerName":retailerName,"pageNum":num},
             		dataType:"json",
             		success:function(data){
             			var json = eval(data);
             			var page ="";
             			$(".tableContents tr").remove();
             			if(!jQuery.isEmptyObject(json.webSales)){
             			$.each(json.webSales,function(index,entity){
             				//项目名
             				if(entity.projectName == null){
             					var str = "<tr class='table-tr-odd'> <td>"
             				}else{
             					var str = "<tr class='table-tr-odd'> <td>"+entity.projectName
             				}
             				var str2 = "</td><td>"+entity.submitDate+
             				"</td><td>"+entity.retaileName +
             				"</td><td>"+entity.categoryName+
             				"</td><td>"+entity.familyName+
             				"</td><td>"+entity.productName+
             				"</td><td>"+entity.amount+
             				"</td><td>"+entity.totalPrice+"</td></tr>"
             			    $(".tableContents").append(str+str2);
                           
             			});
                 		
             			$("#pro_name").val(json.projectId);
             		 	$("#pagination-flickr li").remove(); 
             			var pageNum = json.pageNum;
             			var maxPageNum = json.maxPageNum;
             		     page =  "<li class='firstPage' style='cursor: pointer;'>首页</li>"+
    				    "<li class='previous-off' style='cursor: pointer;'>上一页</li>"+
    					"<li class='active'>第"+pageNum+"页</li>"+
    					"<li class='next' style='cursor: pointer;'>下一页</li>"+
    					"<li class='lastPage' style='cursor: pointer;'>末页</li>"+
    					"<li class='active'>共"+maxPageNum+"页</li>"+
    					"<li class='identify-page'><a class='identifyPage' style='cursor: pointer;'>跳转</a>第<input type = 'text' id = 'identifyPage' style = 'width:50px;'/>页</li>";
                     
             			$("#pagination-flickr").append(page);  	
             			}else{
             				$("#pagination-flickr li").remove(); 
                 			alert("没有查询到相应的历史销售记录！");
             			}
             			
                      //上一页点击事件
            			$(".previous-off").click(function()
            	    	        {
            				var lastPage = pageNum - 1;
                    		search(lastPage);
            	    	        });
            			
    	        		//下一页点击事件
            			$(".next").click(function()
            	    	        {
            				search(pageNum + 1);
            	    	        });
    	        		
            			//首页点击事件
            			$(".firstPage").click(function()
            	    	        {
            				search(1);
            	    	        });
            			
            			//末页点击事件
            			$(".lastPage").click(function()
            	    	        {
            				search(maxPageNum);
            	    	        });
            			
            			//手动输入页面事件
            			$(".identifyPage").click(function()
            	    	        {
            						
            						num = $("#identifyPage").val();
            						var exp = /^[0-9]+$/;
            						if(!exp.test(num))
            						{
            							alert("输入的必须是有效数字");
            							return false;
            						}
            						if(num>maxPageNum||num==0){
                     					alert("请输入正确的页码");
                     					return false;
                     				}
            						search(num);
            	    	        });
                        
                        var div_height = $(".content-flickr").height();
                        $('#iframeContent', window.parent.document).height(div_height + 90); //为iframe赋值高度
                        
             		}
             	})
             	
            }
            
        })
            $(function()
                	{
                		$("#output_but").click(function()
                		{
                			var projectId = $("#pro_name").val();
                          	var startDate = document.getElementById("startdate").value;
                          	var endDate =  document.getElementById("enddate").value;
                          	var retailerName =document.getElementById("txtCardName").value;
                          	if($.trim($("#pagination-flickr").html()) == "")
                          	{
                          		return false;
                          	}
                			location.href = "exportExcel?projectId="+projectId+"&startDate="+startDate+"&endDate="+endDate+"&retailerName="+retailerName;
                		});
                	});
    </script>
    <style>
        .datetime, #txtCardName {
            width: 20%;
            border: 1px solid #C9CACC;
            height: 22px;
            border-radius: 4px;
        }

        .datetime {
            width: 20%;
            height: 22px;
            padding: 3px 12px;
            border: 1px solid #C9CACC;
            color: #555;
             border-radius: 4px;
        }
        #history_btnSearch {
            width: 10%;
        }
    </style>
    
</head>
<body>
   <div class="content-flickr">
   <input type="hidden" id="in_pro" value="${projectId}">
    <div id="history_div" >
        <table class="top-table">
        
            <tr>
                <td class="top-title" style="font-size: 1em;">历史销售记录</td>
                <td style="width:70%">
                    项目名称：
                    <select id="pro_name" name="pro_name"  style="height: 28px; width: 23.5%; border-radius:4px"><option value="0">---请选择--</option>
                   
                    <c:forEach items="${projects }" var="pj">
            
            <option name ="pro_id" value="${pj.id}">${pj.name}</option>
            </c:forEach>
                    </select>
                    &nbsp;&nbsp;&nbsp;会员名：<input type="text" id="txtCardName"  name = "retailername" style="height: 26px; width: 23.5%"/>
                    <input type="button" class="btnOperate" id="history_btnSearch" value="查询" style=" margin-top: 4px; " />
                </td>
                <td>
                	<button id="output_but" class = "btnOperate" style = "width:100px;">导出报表</button>
                </td>
            </tr>
            <tr>
                <td ></td>
                <td style="width:70%">
                    时&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;间： <input id="startdate" type="text"  name="startdate" class="datetime" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;至&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    <input id="enddate" type="text" name="enddate" class="datetime" />
                </td>
            </tr>
		
        </table>
        </div>
        <table class="content-table" cellspacing="0">
            <thead>
            <tr>
                    <td width="20%">项目名</td>
                    <td width="10%">时间</td>
                    <td width="15%">零售商名</td>
                    <td width="15%">产品类别</td>
                    <td width="10%">产品家族</td>
                    <td width="10%">产品</td>
                    <td width="10%">数量</td>
                    <td width="10%">销售额</td>
                </tr>
            </thead>
            <tbody class = "tableContents">
             <c:forEach items="${salesRecords }" var="s" >
                <tr class="table-tr-odd">
                    <td> ${s.projectName}</td>
                    <td>${s.submitDate}</td>
                    <td>${s.retaileName }</td>
                    <td>${s.categoryName}</td>
                    <td>${s.familyName}</td>
                    <td>${s.productName}</td>
                    <td>${s.amount}</td>
                    <td>${s.totalPrice}</td>
                </tr>
                </c:forEach>
            </tbody>
        </table>
         <div class="datalist-paging">
            <ul id="pagination-flickr">
            </ul>
        </div>
       <div style="clear:both"></div>
            <br/>
            <br/>
            <br/>
            <br/>
            <br/>
    </div>


</body>

</html>