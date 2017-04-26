<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
    <link href="${pageContext.request.contextPath}/Resources/html/css/Datalist.css" rel="stylesheet" />
    <link href="${pageContext.request.contextPath}/Resources/html/css/paging.css" rel="stylesheet" />
    <script src="${pageContext.request.contextPath}/Resources/html/js/jquery.js"></script>
    <script src="${pageContext.request.contextPath}/Resources/html/js/jquery-3.1.1.min.js"></script>
    
    <script type="text/javascript">
    $(function () {
    	
        //搜索条件
        var idOrName;
        
        //为搜索框绑定输入变化事件
        $(".btnOperate").click(function()
        {
        	idOrName = $("#txtCardId").val();
        	GetInfo(idOrName,1);
        });
        
        //调用ajax获取搜索结果并画出前台页面
        function GetInfo(condition,num)
        {
        	$.ajax({
        		type:"post",
        		url:"infos",
        		data:"idOrName="+condition+"&pageNum="+num,
        		dataType:"text",
        		success:function(json)
        		{
        			//分割json字符串并转换为json对象，然后获取搜索条件、实际显示页码、最大页码
        			var lJson = eval(json.split("@#$")[0]);
        			var selectOption  = lJson[0].idOrName;
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
	        				contents += "<tr class='table-tr-odd'><td>"+array[i].reName+"</td>"+
				        				"<td>"+array[i].reId+"</td>"+
				        				"<td>"+array[i].totalPrice+"</td>"+
				        				"<td>"+array[i].provinceName+"</td>"+
				        				"<td>"+array[i].curRank+"</td>"+
				        				"<td>"+array[i].totalPoints+"</td>"+
				        				"<td>"+array[i].remainPoints+"</td></tr>";
	        				if(i+1 < array.length)
	        				{
	        					contents += "<tr class='table-tr-even'><td>"+array[i+1].reName+"</td>"+
				        					"<td>"+array[i+1].reId+"</td>"+
				        					"<td>"+array[i+1].totalPrice+"</td>"+
				        					"<td>"+array[i+1].provinceName+"</td>"+
				        					"<td>"+array[i+1].curRank+"</td>"+
					        				"<td>"+array[i+1].totalPoints+"</td>"+
					        				"<td>"+array[i+1].remainPoints+"</td></tr>";
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
        				alert("未查询到相应的会员信息！");
        			}
        			/* if(selectOption == 1)
        			{
        				selectOption = null;
        			} */
	        			$("#txtCardId").val(selectOption);
	        			$(".tableContents").html(contents);
	        			$("#pagination-flickr").html(page);
	        			
	        			var div_height = $(".content-flickr").height();
                        $('#iframeContent', window.parent.document).height(div_height+90); //为iframe赋值高度
	        			
	        		//上一页点击事件
        			$(".previous-off").click(function()
        	    	        {
        						idOrName = $("#txtCardId").val();
        	    	        	var num = pageNum - 1;
        	    	        	GetInfo(idOrName,num);
        	    	        });
        			
	        		//下一页点击事件
        			$(".next").click(function()
        	    	        {
        						idOrName = $("#txtCardId").val();
        	    	        	var num = pageNum + 1;
        	    	        	GetInfo(idOrName,num);
        	    	        });
	        		
        			//首页点击事件
        			$(".firstPage").click(function()
        	    	        {
        						idOrName = $("#txtCardId").val();
        	    	        	GetInfo(idOrName,1);
        	    	        });
        			
        			//末页点击事件
        			$(".lastPage").click(function()
        	    	        {
        						idOrName = $("#txtCardId").val();
        	    	        	GetInfo(idOrName,maxPageNum);
        	    	        });
        			
        			//末页点击事件
        			$(".identifyPage").click(function()
        	    	        {
        						idOrName = $("#txtCardId").val();
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
        	    	        	GetInfo(idOrName,num);
        	    	        });
        		}
        	});
        }
        
          $(".synchronize").click(function()
          {
        	  alert("正在同步...");
          	$.ajax
          	(
          	  {
          	    type:'post',
          	    url:'synchronizeRetailerInfo'
          	  }
          	);
          });
          
        });
        
    </script>
</head>
<body>
    <div class="content-flickr">
        <table class="top-table" border="0">
            <tr>
                <td  >会员信息</td>
                <td style="width:50%">
                    <input type="text" id="txtCardId" placeholder="请输入会员名/会员号" />

                </td>
                <td style = "width:14%;"><input type="button" class="btnOperate" value="查询" /></td>
                <c:if test="${IDENTITY eq 'admin' }">
	                <td><input type="button" class="synchronize" value="同步会员信息" /></td>
                </c:if>
            </tr>
        </table>
        <table class="content-table" cellspacing="0">
            <thead>
                <tr>
                    <td width="14%">会员名</td>
                    <td width="14%">会员号</td>
                    <td width="14%">累计销售额</td>
                    <td width="14%">省份</td>
                    <td width="14%">项目内排行</td>
                    <td width="14%">累计积分</td>
                    <td width="14%">剩余积分</td>
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