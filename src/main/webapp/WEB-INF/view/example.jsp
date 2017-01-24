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
			var provinceId=$(this).val();
			$.ajax({
				type: "POST",
				url: "createproject/provincesDetail", 
				data:{"provinceID":provinceId},
				dataType:"json",
				success: function(data){
					var jsonBind=eval(data);
					jsonBind.promoters!=null?
							fillPromoterData(jsonBind)
						:$("#promoter").append("<span>无推广员信息</span>");
					jsonBind.retailers!=null?
							fillRetailerData(jsonBind)
						:$("#retailer").append("<span>无零售商信息</span>");
				}
			});
		})
		function fillRetailerData(data){
			$("#retailer span").remove();
			
			$.each(data.retailers,function(index,obj){
			$("#retailer").append("<span>retailerid:"+obj.id+"</spqn>")				
			$("#retailer").append("<span>chineseName:"+obj.chineseName+"</spqn>")
			$("#retailer").append("<span>provinceID:"+obj.provinceID+"</spqn>")
			$("#retailer").append("<span>mobile:"+obj.mobile+"</spqn>")
			$("#retailer").append("<span>wechatID:"+obj.wechatID+"</spqn>")
			$("#retailer").append("<span>email:"+obj.email+"</spqn><br/>")
			})
		}
		function fillPromoterData(data){
			$("#promoter span").remove();
			
			$.each(data.promoters,function(index,obj){
			$("#promoter").append("<span>promoterid:"+obj.id+"</spqn>")				
			$("#promoter").append("<span>chineseName:"+obj.chineseName+"</spqn>")
			$("#promoter").append("<span>wechatID"+obj.wechatID+"</spqn><br/>")
			})
		}
	});
</script>
<title>Insert title here</title>
</head>
<body>
		<select id="provinces">
			<c:choose>
			<c:when test="${not empty provinces}">
				<c:forEach items="${provinces}" var="province">
					<option value="${province.id}">
						<c:out value="${province.name}"></c:out>
					</option>
				</c:forEach>
			</c:when>
			<c:otherwise>
				<option value="">
					无省份信息
				</option>
			</c:otherwise>
			</c:choose>
		</select>
			<div id="promoter">
			</div>
			<div id="retailer">
			</div>
</body>
</html>