<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="${pageContext.request.contextPath}/Resources/html/js/jquery-3.1.1.min.js"></script>
<script src="${pageContext.request.contextPath}/Resources/html/js/jquery.js"></script>
<script src="${pageContext.request.contextPath}/Resources/html/js/lrz.mobile.min.js"></script>
<script type="text/javascript">
function dataURItoBlob(base64Data) {
    var byteString;
    if (base64Data.split(',')[0].indexOf('base64') >= 0)
        byteString = atob(base64Data.split(',')[1]);
    else
        byteString = unescape(base64Data.split(',')[1]);
    var mimeString = base64Data.split(',')[0].split(':')[1].split(';')[0];
    var ia = new Uint8Array(byteString.length);
    for (var i = 0; i < byteString.length; i++) {
        ia[i] = byteString.charCodeAt(i);
    }

    return new Blob([ia], { type: mimeString });
}

    $(function()
        {
    	    //导入礼品表格
    	    $(".submit").click(function()
    	        {
    	            var file = $(".excel")[0].files[0];
    	            var formData = new FormData();
    	            formData.append("file",file);
    	            
    	            $.ajax(
    	                {
    	                    type:"post",
    	                    url:"importExcel",
    	                    data:formData,
    	                    processData : false,
	          				contentType : false,
	          				dataType:"text",
    	                    success:function(msg)
    	                    {
    	                        alert(msg);
    	                    },
    	                    error:function()
    	                    {
    	                    	alert("导入异常");
    	                    }
    	                });
    	        });
    	    
    	    //导入相应图片
    	    $("#submit").click(function()
        	    {
        	        var goodsId = $(".goods").val();
        	        var goodsName = $(".goods").find("option:selected").text();
        	        var formData = new FormData();
        	        formData.append("goodsId",goodsId);
        	        formData.append("goodsName",goodsName);
        	        
        	        lrz($('.goodsImg')[0].files[0], { width: 640, quality: 0.8 }, function (rst) {              
                        
        	        	var blob = dataURItoBlob(rst.base64);  // 上一步中的函数
        	        	formData.append("file",blob);
                        
	        	        $.ajax(
	        	            {
	        	              type:"post",
	        	              url:"importImg",
	        	              data:formData,
	        	              processData : false,
	          				  contentType : false,
	          				  dataType:"text",
	        	              success:function(msg)
	        	              {
	        	                  alert(msg);
	        	              },
	    	                  error:function()
	    	                  {
	    	                      alert("导入异常");
	    	                  }
	        	            });
        	        });
        	    });
    	    
    	  //导入推广员表格
    	    $(".confirm").click(function()
    	        {
    	            var file = $(".promoter")[0].files[0];
    	            var formData = new FormData();
    	            formData.append("file",file);
    	            
    	            $.ajax(
    	                {
    	                    type:"post",
    	                    url:"importPromoters",
    	                    data:formData,
    	                    processData : false,
	          				contentType : false,
	          				dataType:"text",
    	                    success:function(msg)
    	                    {
    	                        alert(msg);
    	                    },
    	                    error:function()
    	                    {
    	                    	alert("导入异常");
    	                    }
    	                });
    	        });
        });
</script>
</head>
<body>
    <h5>导入商品信息</h5>
    <form enctype="multipart/form-data">
      <input type="file" class = "excel"/>
      <button class = "submit">确定</button>
    </form>
    <br/>
    <br/>
    <br/>
    <br/>
    <h5>导入礼品对应图片</h5>
    <form enctype="multipart/form-data">
      <select class = "goods">
        <option value = "-1">---请选择对应礼品---</option>
        <c:if test="${not empty goodsList}">
          <c:forEach items="${goodsList}" var = "goods">
            <option value = "${goods.id}">${goods.name}</option>
          </c:forEach>
        </c:if>
      </select>
      <input type = "file" class = "goodsImg">
      <button id = "submit">确定</button>
    </form>
    <br/>
    <br/>
    <br/>
    <br/>
    <h5>导入推广员信息</h5>
    <form enctype="multipart/form-data">
      <input type="file" class = "promoter"/>
      <button class = "confirm">确定</button>
    </form>
</body>
</html>