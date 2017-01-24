<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="Resources/html/js/jquery-3.1.1.min.js"></script>
<script type="text/javascript">
	$(function(){
		$("#provinces").change(function(){
			$("#promoter span").remove();
			var provinceId=$(this).val();
			$.ajax({
				type: "POST",
				url: "provincesDetail", 
				data:{"provinceID":provinceId},
				dataType:"json",
				success: function(data){
					var jsonBind=eval(data);
					jsonBind.promoters!=null?
							promoterData(jsonBind)
						:$("#promoter").append("<span>no promoters data,please check database</spqn>")
				}
			});
		})
		function promoterData(data){
			
			$("#promoter").append("<span>"+data.promoters[0].chineseName+"</spqn>")
		}
	});
</script>
<title>Insert title here</title>
</head>
<body>
		<select id="provinces">
			<c:forEach items="${provinces}" var="province">
				<option value="${province.id}">
					<c:out value="${province.name}"></c:out>
				</option>
			</c:forEach>
		</select>
			<div id="promoter">
			</div>
			<div id="retailer">
			</div>
</body>
</html>