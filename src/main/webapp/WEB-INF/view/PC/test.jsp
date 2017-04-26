<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="/DowLoyalty/Resources/html/js/jquery.js"></script>
<script type="text/javascript">
	
	$(function()
		{
			$(".submit").click(function()
					{
				var formData = new FormData();
				var file = $("#file")[0].files[0];
				alert(file);
				formData.append("file",file);
				$.ajax
				(
					{
						type:"post",
						url:"/DowLoyalty/Web/saveImage",
						data:formData,
						processData: false,
						contentType: false,
					    async: false,
						success:function(msg)
						{
							alert(msg);
							location.reload();
						},
						error:function()
						{
							alert("异常");
						}
					}
				);
					});
		
		});
	
	
	
</script>
</head>
<body>
	<form id = "myForm" enctype="multipart/form-data">
	
	<input type = "file" id = "file" name = "file">
	</form>
	<button class = "submit" >提交</button>
	<c:forEach items="${images}" var = "image">
		<img alt="" src="data:image/jpg;base64,${image.base64}">
	</c:forEach>
	
</body>
</html>