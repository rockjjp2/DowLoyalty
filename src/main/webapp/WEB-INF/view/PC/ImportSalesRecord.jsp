<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
   <%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="${pageContext.request.contextPath}/Resources/html/js/jquery-3.1.1.min.js"></script>
    <link href="${pageContext.request.contextPath}/Resources/html/css/Datalist.css" rel="stylesheet" />
    <link href="${pageContext.request.contextPath}/Resources/html/css/paging.css" rel="stylesheet" />
<script type="text/javascript" >
		
	 window.onload=function(){ 
		$("#uploadButton").click(function(){
			$("#uploadExcel").click();
		});
		$("#uploadExcel").change(function(){
			var fileName=$("#uploadExcel").val();
				var d1 = /\.[^\.]+$/.exec(fileName);
				if (d1 != ".xls" && d1 != ".xlsx") {
					alert("请选择xls或者xlsx格式文件!");
				}else{
				$("#uploadButton").val(fileName);
				}
		});
		$("#uploadEmployeeExcel").click(function(){
			var excel = $("#uploadExcel").val();
			if(excel ==null || excel == ""){
				 
				alert("请选择上传文件!");
				return false;
			 }
		});
		
		 var div_height = $(".content-flickr").height();
         $('#iframeContent', window.parent.document).height(div_height + 90); //为iframe赋值高度
      
		 document.getElementById("downloadTemplate").onclick=function(){
			document.location = "${pageContext.request.contextPath}/Resources/files/importSaleRecordTemplate.xlsx";
		}
			/* function submits(){
				document.getElementById("uploadExcel")=document.getElementById("uploadExcel1").value;
			}
		  */
		}
		
</script>

    <style>
        a {
            color: #3B4952;
        }
    </style>
    
</head>
<body>
    <div class="content-flickr" style="height:400px">
        <table class="top-table">
            <tr>
                <td  >导入销售记录</td>
                <td>
               <!--  <a href="javascript:void(0)" type="button" class="btnOperate" onclick="downloadImportTem()">下载模版</a> -->
                </td>
                <td>
                <form name="upload" action="importSaleRecord" method="post" enctype="multipart/form-data">  
					<input id="uploadExcel" name="uploadExcel" type="file"  class="btnOperate" value="选择导入文件" style="width:40%;display: none" />
					<input src="" id="uploadEmployeeExcel" type="submit"  class="btnOperate" value="导入" style="margin-left: 5px;width:10%;" /> 
				</form>
			<input id="uploadButton" name="uploadExcel1" type="button" class="btnOperate" value="选择导入文件" style="width:40%"/>
            </td></tr>
        </table>
        <table class="content-table" cellspacing="0">
            <thead>
                <tr>
                    <td width="20%">项目名</td>
                    <td width="10%">时间</td>
                    <td width="15%">零售商名</td>
                    <td width="10%">产品家族</td>
                    <td width="15%">产品类别</td>
                    <td width="10%">产品</td>
                    <td width="10%">数量</td>
                    <td width="10%">销售额</td>
                </tr>
            </thead>
            
            <tbody>
             <c:if test="${not empty errorData}">
            <h6>数据导入失败，错误信息如下：</h6>
              <c:forEach items="${errorData }" var="e">
               <h5>${e}<br/></h5>
               </c:forEach>
            </c:if>
            <c:if test="${empty errorData}">
            <c:forEach items="${newSalesRecord}" var="ns" >
                <tr class="table-tr-odd">
                    <td>${ns.projectName}</td>
                    <td>${ns.submitDate}</td>
                    <td>${ns.retaileName }</td>
                    <td>${ns.categoryName }</td>
                    <td>${ns.familyName}</td>
                    <td>${ns.productName}</td>
                    <td>${ns.amount}</td>
                    <td>${ns.totalPrice }</td>
                </tr>
                </c:forEach>
                </c:if>
            </tbody>
        </table>
       
    </div>
    
    <div style="padding:15px 0px 0px 3%;"><img align="middle" src="${pageContext.request.contextPath}/Resources/html/images/smallImg/down.png" />&nbsp;&nbsp;<a id="downloadTemplate" href="#" style="font-family: 微软雅黑;font-size:15px" >下载模板</a></div>
        <div style="text-align:center">
          </div>

</body>

</html>

