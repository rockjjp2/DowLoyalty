<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title></title>
    <link href="${pageContext.request.contextPath}/Resources/html/css/IntegralItem.css" rel="stylesheet" />
    <script src="${pageContext.request.contextPath}/Resources/html/js/jquery.js"></script>
    <script src="${pageContext.request.contextPath}/Resources/html/js/lrz.mobile.min.js"></script>
    <script src="${pageContext.request.contextPath}/Resources/html/js/IntegralItem.js?a=2"></script>
    <script src="${pageContext.request.contextPath}/Resources/html/js/main.js"></script>
    <script>
    
    $(function () {
 
        var userId = "${userid}";
        $.ajax
        (
        	{
        		type:"post",
        		url:"getProjectDetails",
        		data:"userId="+userId,
        		dataType:"json",
        		success:function(projects)
        		{
        			var content = "<ul>";
        	    	//var image = null;
        	    	for(var i in projects)
        	    	{
        	    		var base64 = projects[i].backgroundBase64;
        	    		content += "<li id='"+projects[i].id+"' style='margin-left: 1.5%;background-image:url(data:image/jpg;base64,"+ base64 +");background-size: cover'>"+
        	    		"<div class='integralitem-content' ><p class=iintegralitem-content-titlei style = 'font-size: 1.9em;'>"+projects[i].name+"</p>"+
        	    		"<p><a href='salerecord?projectId="+projects[i].id+"' target='_self'><span class='Sales-img'></span> &nbsp;&nbsp;销售记录</a></p>"+
        	    		"<p><a href='project?projectId="+projects[i].id+"' target='_self'><span class='detail-img'></span> &nbsp;&nbsp;活动详情</a> </p></div></li>";
        	    	}
        	    	content += "</ul>";
        	    	$("#IntegralItemHome").html(content);
        	    	var _integralItem = new $IntegralItem();
        	        _integralItem.Init();
        	      
        	      //iframe自适应高度
        	      $('#iframeContent', window.parent.document).height(50);
        	      $('#iframeContent', window.parent.document).height($('#iframeContent', window.parent.document).height()+400*(projects.length % 3 == 0 ? projects.length / 3 : projects.length / 3 + 1));
        		}
        	}
        );
    	
    })
        
        
    </script>

</head>
<body>
    <div class="content-flickr">
        <div id="IntegralItemHome">
        </div>
    </div>
</body>
</html>

    
