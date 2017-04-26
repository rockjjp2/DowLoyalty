<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
    <link href="${pageContext.request.contextPath}/Resources/html/css/Datalist.css" rel="stylesheet" />
    <link href="${pageContext.request.contextPath}/Resources/html/css/paging.css" rel="stylesheet" />
    <script src="${pageContext.request.contextPath}/Resources/html/js/jquery.js"></script>
    <script src="${pageContext.request.contextPath}/Resources/html/js/jquery-3.1.1.min.js"></script>
    <link href="${pageContext.request.contextPath}/Resources/html/css/jquery.datetimepicker.css" rel="stylesheet" />
    <script src="${pageContext.request.contextPath}/Resources/html/js/jquery.datetimepicker.js"></script>
<script>

        
        $(function () {
        
        //iframe自适应高度
  	    $('#iframeContent', window.parent.document).height(300);
        
        //datepicker
        $('#startdate').datetimepicker
        ({
          timepicker: false,
          format: 'Y/m/d',
          step: 10
        });
        $('#enddate').datetimepicker
        ({
          timepicker: false,
          format: 'Y/m/d',
          formatDate: 'Y/m/d',
          // minDate: '-1970/01/01' // yesterday is minimum date
        });
        	
        	
        //搜索条件
        var idOrName;
        var projectId;
        var startDate;
        var endDate;
        
        $("#searchBtn").click(function(){
        	idOrName = $("#txtCardId").val();
        	projectId = $(".projectId").val();
        	startDate = $("#startdate").val();
        	endDate = $("#enddate").val();
        	//alert(idOrName+"-"+projectId+"-"+startDate+"-"+endDate);
        	GetInfo(projectId,startDate,endDate,idOrName,1);
        });
        
        //调用ajax获取搜索结果并画出前台页面
        function GetInfo(projectId,startDate,endDate,condition,num)
        {
        	var search = "idOrName="+condition+"&projectId="+projectId+"&startDate="+startDate+"&endDate="+endDate+"&pageNum="+num;
        	$.ajax({
        		type:"post",
        		url:"exchangerecord",
        		data:search,
        		dataType:"text",
        		success:function(json)
        		{
        			//分割json字符串并转换为json对象，然后获取搜索条件、实际显示页码、最大页码
        			var lJson = eval(json.split("@#$")[0]);
        			/* var selectOption  = lJson[0].idOrName; */
        			var pageNum = lJson[0].page;
        			var maxPageNum = lJson[0].maxPage;
        			var array = eval(json.split("@#$")[1]);
        			var contents = "";
        			var page = "";
        			
        			//判断兑换记录集合是否为空，不为空则画出相应页面
        			if(!jQuery.isEmptyObject(array))
        			{
	        			for(var i = 0; i < array.length;i += 2)
	        			{
	        				contents += "<tr class='table-tr-odd'><td>"+array[i].date+"</td>"+
				        				"<td>"+array[i].retailerName+"</td>"+
				        				"<td>"+array[i].provinceName+"</td>"+
				        				"<td>"+array[i].goodsName+"</td>"+
				        				"<td>"+array[i].amount+"</td></tr>";
	        				if(i+1 < array.length)
	        				{
	        					contents += "<tr class='table-tr-even'><td>"+array[i+1].date+"</td>"+
				        					"<td>"+array[i+1].retailerName+"</td>"+
				        					"<td>"+array[i+1].provinceName+"</td>"+
				        					"<td>"+array[i+1].goodsName+"</td>"+
				        					"<td>"+array[i+1].amount+"</td></tr>";
	        				}
	        			}
	        			page =  "<li class='firstPage' style='cursor: pointer;'>首页</li>"+
    				    "<li class='previous-off' style='cursor: pointer;'>上一页</li>"+
    					"<li class='active'>第"+pageNum+"页</li>"+
    					"<li class='next' style='cursor: pointer;'>下一页</li>"+
    					"<li class='lastPage' style='cursor: pointer;'>末页</li>"+
    					"<li class='active'>共"+maxPageNum+"页</li>"+
    					"<li class='identify-page'><a class='identifyPage' style='cursor: pointer;'>跳转</a>第<input type = 'text' id = 'identifyPage' style = 'width:50px;'/>页</li>";
        			}else{
        				$(".tableContents").html(contents);
	        			$("#pagination-flickr").html(page);
        				alert("未查询到相应的兑换记录！");
        			}
	        			/* $("#txtCardId").val(selectOption); */
	        			$(".tableContents").html(contents);
	        			$("#pagination-flickr").html(page);
	        		
	        			var div_height = $(".content-flickr").height();
	        			if(div_height > 300)
	        			{
	        				$('#iframeContent', window.parent.document).height(div_height+90); //为iframe赋值高度
	        			}
	        			else
	        			{
	        				$('#iframeContent', window.parent.document).height(350);
	        			}
                        
	        			
	        		//上一页点击事件
        			$(".previous-off").click(function()
        	    	        {
        						idOrName = $("#txtCardId").val();
        	    	        	var num = pageNum - 1;
        	    	        	projectId = $(".projectId").val();
        	    	        	startDate = $("#startdate").val();
        	    	        	endDate = $("#enddate").val();
        	    	        	GetInfo(projectId,startDate,endDate,idOrName,num);
        	    	        });
        			
	        		//下一页点击事件
        			$(".next").click(function()
        	    	        {
        						idOrName = $("#txtCardId").val();
        	    	        	var num = pageNum + 1;
        	    	        	projectId = $(".projectId").val();
        	    	        	startDate = $("#startdate").val();
        	    	        	endDate = $("#enddate").val();
        	    	        	GetInfo(projectId,startDate,endDate,idOrName,num);
        	    	        });
	        		
        			//首页点击事件
        			$(".firstPage").click(function()
        	    	        {
        						idOrName = $("#txtCardId").val();
        						projectId = $(".projectId").val();
        	    	        	startDate = $("#startdate").val();
        	    	        	endDate = $("#enddate").val();
        	    	        	GetInfo(projectId,startDate,endDate,idOrName,1);
        	    	        });
        			
        			//末页点击事件
        			$(".lastPage").click(function()
        	    	        {
        						idOrName = $("#txtCardId").val();
        	    	        	projectId = $(".projectId").val();
        	    	        	startDate = $("#startdate").val();
        	    	        	endDate = $("#enddate").val();
        	    	        	GetInfo(projectId,startDate,endDate,idOrName,maxPageNum);
        	    	        });
        			
        			//末页点击事件
        			$(".identifyPage").click(function()
        	    	        {
        						idOrName = $("#txtCardId").val();
        						projectId = $(".projectId").val();
        	    	        	startDate = $("#startdate").val();
        	    	        	endDate = $("#enddate").val();
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
        						GetInfo(projectId,startDate,endDate,idOrName,num);
        	    	        });
        		}
        	});
        }
        });
        
    </script>
    <style>
        /* .datetime{
            width: 20%;
            border: 1px solid #C9CACC;
            height: 22px;
            border-radius: 4px;
        } */

        .datetime {
            width: 16%;
            height: 22px;
            padding: 3px 12px;
            border: 1px solid #C9CACC;
            color: #555;
             border-radius: 4px;
        }
    </style>
</head>
<body>
    <div class="content-flickr">
        <table class="top-table" border="0">
            <tr>
                <td class="top-title" style="font-size: 1em;">兑换列表</td>
                <td style="width:76%">
                	项目名称：<select class = "projectId" style="height: 28px; width: 19.5%; border-radius:4px">
                	<option value = "-1">请选择</option>
                	<c:if test="${not empty projects}">
                	  <c:forEach items="${projects}" var = "project">
                	  <option value="${project.id}">${project.name}</option>
                	  </c:forEach>
                	</c:if>
                	</select>
                    会员名：<input type="text" id="txtCardId" style = "padding-left:0;padding-right:0; width:19%;margin-top:0.5%;float:none;"/>
                
                <input type="button" class="btnOperate" value="查询" id="searchBtn" style = "width:10%;margin-right:17%;margin-top: 0.5%;"/></td>
            </tr>
            <tr>
            <td></td>
              <td style = "width:76%;">
                	<span>时</span><span  style = "margin-left: 4%;">间：</span><input id="startdate" readonly type="text" class="datetime" style = "width:16.3%;"/>
                	<span style = "margin-left: 2.3%;">至</span>
                	<input id="enddate" readonly type="text"class="datetime" style = "margin-left: 3.5%;"/>
              </td>
            </tr>
        </table>
        <table class="content-table" cellspacing="0" border="0">
            <thead>
                <tr>
                    <td width="15%">日期</td>
                    <td width="30%">会员名</td>
                    <td width="20%">省份</td>
                    <td width="20%">礼品</td>
                    <td width="15%">数量</td>
                </tr>
            </thead>
            <tbody class = "tableContents">
               
            </tbody>
        </table>

        <div class="datalist-paging">
            <ul id="pagination-flickr">
            </ul>
        </div>
    </div>
    

</body>

</html>