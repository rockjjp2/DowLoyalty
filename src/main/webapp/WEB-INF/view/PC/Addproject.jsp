<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri = "http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title></title>
    <link href="${pageContext.request.contextPath}/Resources/html/css/Style.css" rel="stylesheet" />
    <script src="${pageContext.request.contextPath}/Resources/html/js/jquery.js"></script>
    <script src="${pageContext.request.contextPath}/Resources/html/js/lrz.mobile.min.js"></script>
    <link href="${pageContext.request.contextPath}/Resources/html/css/Addproject.css?j=4s" rel="stylesheet" />
    <link href="${pageContext.request.contextPath}/Resources/html/css/jquery.datetimepicker.css" rel="stylesheet" />
    <script src="${pageContext.request.contextPath}/Resources/html/js/jquery.datetimepicker.js"></script>
    <script src="${pageContext.request.contextPath}/Resources/html/js/Common.js"></script>
    <script src="${pageContext.request.contextPath}/Resources/html/js/JquerySession.js"></script>
    <script src="${pageContext.request.contextPath}/Resources/html/js/Addproject.js?j=2s"></script>
    <script src="${pageContext.request.contextPath}/Resources/html/js/main.js"></script>
    <script>
    
    /*将礼品数据加入缓存*/
	var giftArray = eval(('${exchangeShopGoods}'));
	var productArray = eval(('${productInfos}'));
	var pointsLevelArray = eval(('${pointsLevels}'));
	var retailerArray = eval(('${retailers}'));
	var promoterArray = eval(('${promoters}'));
	var promoter = eval(('[${promoter}]'));
	var projectId = "${projectId}";
	var projectProvince = eval('[${projectProvince}]');
	//测试
	//var projectId = "10001";
	
	var giftJson = "";
	var productJson = "";
	var pointsLevelJson = "";
	var retailerJson = "";
	var promotersJson = "";
	var promoterJson = "";
	var projectProvinceJson = "";
	 /*-----------------------*/
     $("document").ready(function()
    {
    	 
    	//向缓存中存入活动信息
     	if(null != projectProvince && '' != projectProvince)
    	{
	    	var Province = $.trim(projectProvince[0].provinceId);
	    	var Start_time = $.trim(projectProvince[0].startDate);
	    	var End_time = $.trim(projectProvince[0].endDate);
	    	var Title = $.trim(projectProvince[0].projectTitle);
	    	var Introduce = $.trim(projectProvince[0].description);
	    	projectProvinceJson += '{"Province":"' + Province + '","Start_time":" ' + Start_time + ' ","End_time":" ' + End_time + ' ","Title":" ' + Title + ' ","Introduce":" ' + Introduce + ' "},';
	    	projectProvinceJson = projectProvinceJson.substring(0, projectProvinceJson.length - 1);
	    	hashMap.set("Activity", projectProvinceJson);
    	}
    	 
    	//向缓存中存入礼品配置信息
    	if(null != giftArray && '' != giftArray)
    	{
	    	for(var i = 0;i < giftArray.length;i++)
	    	{
	    		var Gift_ID = $.trim(giftArray[i].goodsId);
	    		var Gift_Name = $.trim(giftArray[i].gName);
	    		var Gift_Number = $.trim(giftArray[i].exPoints);
	    		giftJson += '{"id":"' + Gift_ID + '","name":"' + Gift_Name + '","Number":"' + Gift_Number + '"},';
	    	}
	    	giftJson = giftJson.substring(0, giftJson.length - 1);
	    	giftJson = '[' + giftJson + ']';
	    	hashMap.set("Gift", giftJson);
    	}
		
    	//向缓存中存入产品配置信息
    	if(null != productArray && '' != productArray)
    	{
	    	for(var i = 0;i < productArray.length;i++)
	    	{
	    		var Product_ID = $.trim(productArray[i].productId);
	    		var Product_Name = $.trim(productArray[i].productName);
	    		var Sales = $.trim(productArray[i].points);
	    		productJson += '{"Product_ID":"' + Product_ID + '","Product_Name":"' + Product_Name + '","Sales":"' + Sales + '"},';
	    		
	    		
	    	}
	    	productJson = productJson.substring(0, productJson.length - 1);
	    	productJson = '[' + productJson + ']';
	    	hashMap.set("Product", productJson);
    	}
    	
    	//向缓存中存入积分等级信息
    	if(null != pointsLevelArray && '' != pointsLevelArray)
    	{
	    	for(var i = 0;i < pointsLevelArray.length;i++)
	    	{
	    		var rank = $.trim(pointsLevelArray[i].name);
	    		var gradeLine = $.trim(pointsLevelArray[i].points);
	    		pointsLevelJson += '{"rank":"' + rank + '","gradeLine":"' + gradeLine + '"},';
	    	}
	    	pointsLevelJson = pointsLevelJson.substring(0, pointsLevelJson.length - 1);
	    	pointsLevelJson = '[' + pointsLevelJson + ']';
	    	hashMap.set("Grade", pointsLevelJson);
    	}
    	
    	//向缓存中存入零售商信息
    	if(null != retailerArray && '' != retailerArray)
    	{
	    	for(var i = 0;i < retailerArray.length;i++)
	    	{
	    		var id = $.trim(retailerArray[i].id);
	    		var name = $.trim(retailerArray[i].chineseName);
	    		retailerJson += '{"id":"' + id + '","name":"' + name + '"},';
	    	}
	    	retailerJson = retailerJson.substring(0, retailerJson.length - 1);
	    	retailerJson = '[' + retailerJson + ']';
	    	hashMap.set("People_Content", retailerJson);
    	}
    	
    	//向缓存中存入推广员信息
    	if(null != promoterArray && '' != promoterArray)
    	{
	    	for(var i = 0;i < promoterArray.length;i++)
	    	{
	    		var id = $.trim(promoterArray[i].id);
	    		var name = $.trim(promoterArray[i].chineseName);
	    		promotersJson += '{"id":"' + id + '","name":"' + name + '"},';
	    	}
	    	promotersJson = promotersJson.substring(0, promotersJson.length - 1);
	    	promotersJson = '[' + promotersJson + ']';
	    	hashMap.set("Promoter_Content", promotersJson);
	    	//alert("Promoter_Content="+$.session.get("Promoter_Content"));
    	}
    	
    	//向缓存中存入发货员信息
    	
    	if(null != promoter && '' != promoter)
    	{
	    	var id = $.trim(promoter[0].id);
	    	var name = $.trim(promoter[0].chineseName);
	    	promoterJson += '{"id":"' + id + '","name":"' + name + '"},';
	    	promoterJson = promoterJson.substring(0, promoterJson.length - 1);
	    	hashMap.set("Send_Promoter", promoterJson);
	    	//alert("Send_Promoter="+$.session.get("Send_Promoter"));
    	}
    	
    
    	
    	
     
    }); 
	 
	
	 
	//初始化零售商和推广员信息
	function initRetailerAndPromoter(projectId)
	{
		//零售商
    	$.ajax(
    		{
    			type:"post",
    			url:"initRetailers",
    			data:"projectId="+projectId,
    			dataType:"json",
    			success:function(json)
    			{
    				var content = "<table>";
    				if(null != json && "[]" != json)
    				{
    					for(var i = 65; i < 91; i++)
        				{
        					var ascString = String.fromCharCode(i);
    						for(var key in json)
        					{
        						if(json[key] != null && json[key] != "")
        						{
	        						
	    							if(key == ascString)
	        						{
	    								content += "<tr><td id = '"+ ascString +"'><span style = 'margin-left:-50%;margin-top:20%;color:#B6BF00;'>"+ ascString + "</span></td><td></td><td></td></tr>";
	    								 for(var j = 0; j < json[key].length; j=j+3)
	    									{
	    										if((json[key][j].split("$@#")[0]).length > 10)
	    										{
	    											content += "<tr><td><span style = 'width:208px;cursor: pointer;' data-id = '" + json[key][j].split("$@#")[1] + "' data-content = '" + json[key][j].split("$@#")[0] + "'>" + (json[key][j].split("$@#")[0]).substring(0,10) + "..." + "</span></td>";
	    										}
	    										else
	    										{
		    										content += "<tr><td><span style = 'width:208px;cursor: pointer;' data-id = '" + json[key][j].split("$@#")[1] + "' data-content = '" + json[key][j].split("$@#")[0] + "'>" + json[key][j].split("$@#")[0] + "</span></td>";
	    										}
	    										if(j+1 < json[key].length)
	    										{
	    											if((json[key][j+1].split("$@#")[0]).length > 10)
		    										{
		    											content += "<td><span style = 'width:208px;cursor: pointer;' data-id = '" + json[key][j+1].split("$@#")[1] + "' data-content = '" + json[key][j+1].split("$@#")[0] + "'>" + (json[key][j+1].split("$@#")[0]).substring(0,10) + "..." + "</span></td>";
		    										}
		    										else
		    										{
			    										content += "<td><span style = 'width:208px;cursor: pointer;' data-id = '" + json[key][j+1].split("$@#")[1] + "' data-content = '" + json[key][j+1].split("$@#")[0] + "'>" + json[key][j+1].split("$@#")[0] + "</span></td>";
		    										}
	    											if(j+2 < json[key].length)
	    											{
	    												if((json[key][j+2].split("$@#")[0]).length > 10)
	    	    										{
	    	    											content += "<td><span style = 'width:208px;cursor: pointer;' data-id = '" + json[key][j+2].split("$@#")[1] + "' data-content = '" + json[key][j+2].split("$@#")[0] + "'>" + (json[key][j+2].split("$@#")[0]).substring(0,10) + "..." + "</span></td>";
	    	    										}
	    	    										else
	    	    										{
	    		    										content += "<td><span style = 'width:208px;cursor: pointer;' data-id = '" + json[key][j+2].split("$@#")[1] + "' data-content = '" + json[key][j+2].split("$@#")[0] + "'>" + json[key][j+2].split("$@#")[0] + "</span></td>";
	    	    										}
	    											}
	    											else
		    										{
		    											content += "<td></td>";
		    										}
	    										}
	    										else
	    										{
	    											content += "<td></td>";
	    										}
		    								 content += "</tr>";
	    									}
	        						}
        						}
        					}
    					}
    					content += "</table>";
    					$("#retailerT").html(content);
    					//
    					var height = $(".sub-nav").height() > $('.nav-right').height() ? $(".sub-nav").height() : $('.nav-right').height();
    					$('#iframeContent', window.parent.document).height(height);
    					
        			}
    				if($("#retailerT table").html()=="")
    				{
    					$("#People_Content").html("该活动所在省份下没有任何零售商");
    				}
    			},
    			error:function()
    			{
    				alert("系统异常，请重新载入页面！");
    			}
    		}
    		);
    	
    	//推广员
    	$.ajax(
    		{
    			type:"post",
    			url:"initPromoters",
    			data:"projectId="+projectId,
    			dataType:"json",
    			success:function(json)
    			{
    				var content = "<table>";
    				if(null != json && "[]" != json)
    				{
    					for(var i = 65; i < 91; i++)
        				{
        					var ascString = String.fromCharCode(i);
    						for(var key in json)
        					{
        						if(json[key] != null && json[key] != "")
        						{
	        						
	    							if(key == ascString)
	        						{
	    								content += "<tr><td id = '"+ ascString +"'><span style = 'margin-left:-50%;margin-top:20%;color:#B6BF00;cursor: pointer;'>"+ ascString + "</span></td><td></td><td></td></tr>";
	    								 for(var j = 0; j < json[key].length; j=j+3)
	    									{
	    										if((json[key][j].split("$@#")[0]).length > 10)
	    										{
	    											content += "<tr><td><span style = 'width:208px;cursor: pointer;' data-id = '" + json[key][j].split("$@#")[1] + "' data-content = '" + json[key][j].split("$@#")[0] + "'>" + (json[key][j].split("$@#")[0]).substring(0,10) + "..." + "</span></td>";
	    										}
	    										else
	    										{
		    										content += "<tr><td><span style = 'width:208px;cursor: pointer;' data-id = '" + json[key][j].split("$@#")[1] + "' data-content = '" + json[key][j].split("$@#")[0] + "'>" + json[key][j].split("$@#")[0] + "</span></td>";
	    										}
	    										if(j+1 < json[key].length)
	    										{
	    											if((json[key][j+1].split("$@#")[0]).length > 10)
		    										{
		    											content += "<td><span style = 'width:208px;cursor: pointer;' data-id = '" + json[key][j+1].split("$@#")[1] + "' data-content = '" + json[key][j+1].split("$@#")[0] + "'>" + (json[key][j+1].split("$@#")[0]).substring(0,10) + "..." + "</span></td>";
		    										}
		    										else
		    										{
			    										content += "<td><span style = 'width:208px;cursor: pointer;' data-id = '" + json[key][j+1].split("$@#")[1] + "' data-content = '" + json[key][j+1].split("$@#")[0] + "'>" + json[key][j+1].split("$@#")[0] + "</span></td>";
		    										}
	    											if(j+2 < json[key].length)
	    											{
	    												if((json[key][j+2].split("$@#")[0]).length > 10)
	    	    										{
	    	    											content += "<td><span style = 'width:208px;cursor: pointer;' data-id = '" + json[key][j+2].split("$@#")[1] + "' data-content = '" + json[key][j+2].split("$@#")[0] + "'>" + (json[key][j+2].split("$@#")[0]).substring(0,10) + "..." + "</span></td>";
	    	    										}
	    	    										else
	    	    										{
	    		    										content += "<td><span style = 'width:208px;cursor: pointer;' data-id = '" + json[key][j+2].split("$@#")[1] + "' data-content = '" + json[key][j+2].split("$@#")[0] + "'>" + json[key][j+2].split("$@#")[0] + "</span></td>";
	    	    										}
	    											}
	    											else
		    										{
		    											content += "<td></td>";
		    										}
	    										}
	    										else
	    										{
	    											content += "<td></td>";
	    										}
		    								 content += "</tr>";
	    									}
	        						}
        						}
        					}
    					}
    					content += "</table>";
    					$("#promoterT").html(content);
    					//
    					var height = $(".sub-nav").height() > $('.nav-right').height() ? $(".sub-nav").height() : $('.nav-right').height();
    					$('#iframeContent', window.parent.document).height(height);
        			}
    				if($("#promoterT table").html()=="")
    				{
    					$("#Promoter_Content").html("该活动所在省份下没有任何推广员");
    				}
    			},
    			error:function()
    			{
    				alert("系统异常，请重新载入页面！");
    			}
    		}
    		);
	}
	//--------------Retailers----start-------------
	 /*单击行选中零售商 并将选中的数据记录于session*/
	 $(function(){
		 
		 //编辑状态下根据projectId判断是否初始化projectId
		 //--------------------------------------------------------
		 if(projectId != null && projectId != "")
		 {
			 initRetailerAndPromoter(projectId);
		 }
		//--------------------------------------------------------
		 
		 //点击零售商姓名添加到项目中
        $("#People_Content").on("click", "div table tr td span", function () {
            var session = hashMap.get("People_Content");
            var id = $(this).data('id');
            var name = $(this).data('content');
            var Json = '{"id":"' + id + '","name":"' + name + '"}';
                if (!App.Com.isnullorempty(session)) {

                    var array = jQuery.parseJSON(session);
                    var result = [];
                    for (var i = 0; i < array.length; i++) { if (array[i].id != id) {result.push(array[i]);}else{alert("不能重复添加!");return false;} }
                    
                    result.push(jQuery.parseJSON(Json));
                    hashMap.set("People_Content", JSON.stringify(result) == "[]" ? "" : JSON.stringify(result));
                } else { 

	                var result = [];
	
	                result.push(jQuery.parseJSON(Json));
	                hashMap.set("People_Content", JSON.stringify(result));
                }
                var str = "";
                if((jQuery.parseJSON(Json).name).length > 5)
                {
                	str = "<li data-id = '" + jQuery.parseJSON(Json).id + "' data-content = '" + jQuery.parseJSON(Json).name + "'><p id='clear_p'>x</p><span style='color:#B6BF00;cursor: pointer;'>" + (jQuery.parseJSON(Json).name).substring(0,5)+"..." + "</span></li>";
                }
                else
                {
	                str = "<li data-id = '" + jQuery.parseJSON(Json).id + "' data-content = '" + jQuery.parseJSON(Json).name + "'><p id='clear_p'>x</p><span style='color:#B6BF00;cursor: pointer;'>" + jQuery.parseJSON(Json).name + "</span></li>";
                }
                $("#Person_UL").append(str);
                //
				$('#iframeContent', window.parent.document).height($('.nav-right').height());
                
                //将关系添加进数据库
                var projectWithRetailer = [];
                var relation = '{"projectId":"'+ projectId +'","retailerId":"'+ id +'"}';
                projectWithRetailer.push(relation);
                 $.ajax({
                	type:"post",
        			url:"insertRetailers",
        			data:"array="+projectWithRetailer
                });
                 
        });
	 
			
			//点击全选，将当前列表中的所有名称添加到项目中
			$(".person_submit").click(function () {
				
				var projectWithRetailer = [];

	        	var session;
	        	var result = [];
	        	var _html = "";
	            $("#retailerT table").find("tr").each
	            (
	            		function(index,item)
	            		{
	            			$(item).find("td").each
	            			(
	            				function(indextd,itemtd)
	        	            	{
			            			session = hashMap.get("People_Content");
			            			var id = $(itemtd).find("span").data('id');
			            			if(id != null)
			            			{
				            			var name = $(itemtd).find("span").data('content');
				            			var Json = '{"id":"' + id + '","name":"' + name + '"}';
				            			if (!App.Com.isnullorempty(session)) {
			
				                            var array = jQuery.parseJSON(session);
				                            var index = session.indexOf(Json);
				                            if(index == -1)
				                            {
				                            	array.push(jQuery.parseJSON(Json));
				                            }
				                            result = array;
				                            hashMap.set("People_Content", JSON.stringify(result) == "[]" ? "" : JSON.stringify(result));
				                        } else {
					                        result.push(jQuery.parseJSON(Json));
					                        hashMap.set("People_Content", JSON.stringify(result) == "[]" ? "" : JSON.stringify(result));
				                        }
			            			}
	        	            	}
	            			);
	            		}
	            	);
	            for (var i = 0; i < result.length; i++)
	            {
	            	if((result[i].name).length > 5)
	            	{
	            		_html += "<li data-id = '" + result[i].id + "' data-content = '" + result[i].name + "'><p id='clear_p'>x</p><span style='color:#B6BF00;cursor: pointer;'>" + (result[i].name).substring(0,5)+"..." + "</span></li>";
	            	}
	            	else
	            	{
		            	_html += "<li data-id = '" + result[i].id + "' data-content = '" + result[i].name + "'><p id='clear_p'>x</p><span style='color:#B6BF00;cursor: pointer;'>" + result[i].name + "</span></li>";
	            	}
	          	
	            //拼接项目与零售商关系json数组
    			var relation = '{"projectId":"'+ projectId +'","retailerId":"'+ result[i].id +'"}';
    			
    			projectWithRetailer.push(relation);
	            }
	            $("#Person_UL").html(_html);
	            //
				$('#iframeContent', window.parent.document).height($('.nav-right').height());
	            
	          //  alert(projectWithRetailer);
	            //批量添加
                  $.ajax({
                	type:"post",
        			url:"insertRetailers",
        			data:"array="+projectWithRetailer
                });
	            
	        });
   //     });
        
		//悬停显示label
		$("#retailerT").on("mouseover"," table tr td span",function()
			{
				var name = $(this).data("content");
				if(name != null && name.length > 10)
				{
					$(".tips").css("left",event.x);
					$(".tips").css("top",event.y);
					$(".tips").text(name);
					$(".tips").css("display","block");
				}
				
			}
		);
		$("#retailerT").on("mouseout","table tr td span",function()
				{
					$(".tips").css("display","none");
					$(".tips").text("");
				}
			);
		
		$("#Person_UL").on("mouseover","li span",function()
				{
					var name = $(this).parent().data("content");
					if(name.length > 5)
					{
						$(".tips").css("left",event.x);
						$(".tips").css("top",event.y);
						$(".tips").text(name);
						$(".tips").css("display","block");
					}
				}
			);
		$("#Person_UL").on("mouseout","li span",function()
				{
					$(".tips").css("display","none");
					$(".tips").text("");
				}
			);
		
		//从项目中删除名称
        $("#Person_UL").on("click", "li p", function () {
//          /*关闭*/
        	var session = hashMap.get("People_Content");
            var id = $(this).parent().data('id');
            var name = $(this).parent().data('content');
            var Json = '{"id":"' + id + '","name":"' + name + '"}';
            if (!App.Com.isnullorempty(session)) {
                var array = jQuery.parseJSON(session);
                for (var i = 0; i < array.length; i++) { if (array[i].id == id) { array.splice(i,i+1); } }

                hashMap.set("People_Content", JSON.stringify(array) == "[]" ? "" : JSON.stringify(array));
            }
                $(this).parent().remove();
                //
				$('#iframeContent', window.parent.document).height($('.nav-right').height());
              //将关系从数据库删除
                var projectWithRetailer = [];
                var relation = '{"projectId":"'+ projectId +'","retailerId":"'+ id +'"}';
                projectWithRetailer.push(relation);
                 $.ajax({
                	type:"post",
        			url:"deleteRetailers",
        			data:"array="+projectWithRetailer
                }); 
        });
        
        
        //清空列表
        $("#resetRetailers").click(function()
        		{
        			//-------------------------------------
        			var projectWithRetailer = [];

		        	var session;
		        	var result = [];
		            $("#Person_UL").find("li").each(
		            		function(index,item)
		            		{
		            			session = hashMap.get("People_Content");
		            			var id = $(item).data('id');
		            			var name = $(item).data('content');
		            			var Json = '{"id":"' + id + '","name":"' + name + '"}';
		            			if (!App.Com.isnullorempty(session)) {
		            				var array = jQuery.parseJSON(session);
		                            var idArray = [];
		                            var index = session.indexOf(Json);
		                            if(index == -1)
		                            {
		                            	array.push(jQuery.parseJSON(Json));
		                            }
		                            result = array;
		                        } else {
	
		                        result.push(jQuery.parseJSON(Json));
		                        }
		            			
		            		});
		            for (var i = 0; i < result.length; i++)
		            {
			            //拼接项目与零售商关系json数组
		    			var relation = '{"projectId":"'+ projectId +'","retailerId":"'+ result[i].id +'"}';
		    			projectWithRetailer.push(relation);
		            }
        			//-------------------------------------
        			var people_Content = hashMap.get("People_Content");
        			if (!App.Com.isnullorempty(people_Content)) 
        			{
	        			hashMap.set("People_Content", "");
	        		 	$("#Person_UL").empty();
	        		 	//
	    				$('#iframeContent', window.parent.document).height($('.nav-right').height());
	        		 	
	        		 	  $.ajax({
	 	                	type:"post",
	 	        			url:"deleteRetailers",
	 	        			data:"array="+projectWithRetailer
	 	                }); 
        			}
        		});
	 });
	//--------------end---------------
	
	//------------Promoter--start-------------
	 /*单击行选中推广员 并将选中的数据记录于session*/
	 $(function(){
		 //点击推广员姓名添加到项目中
        $("#Promoter_Content").on("click", "div table tr td span", function () {
            var session = hashMap.get("Promoter_Content");
           // alert("session="+session);
            var id = $(this).data('id');
            var name = $(this).data('content');
            var Json = '{"id":"' + id + '","name":"' + name + '"}';
                if (!App.Com.isnullorempty(session)) {
				
                    var array = jQuery.parseJSON(session);
                    var result = [];
                    for (var i = 0; i < array.length; i++) { if (array[i].id != id) {result.push(array[i]);}else{alert("不能重复添加!");return false;} }
                    
                    result.push(jQuery.parseJSON(Json));
                    hashMap.set("Promoter_Content", JSON.stringify(result) == "[]" ? "" : JSON.stringify(result));
                } else { 
                var result = [];

                result.push(jQuery.parseJSON(Json));
                hashMap.set("Promoter_Content", JSON.stringify(result));
                }
                if((jQuery.parseJSON(Json).name).length > 5)
                {
                	str = "<li data-id = '" + jQuery.parseJSON(Json).id + "' data-content = '" + jQuery.parseJSON(Json).name + "'><p id='clear_p'>x</p><span style='color:#B6BF00;cursor: pointer;'>" + (jQuery.parseJSON(Json).name).substring(0,5)+"..." + "</span></li>";
                }
                else
                {
	                str = "<li data-id = '" + jQuery.parseJSON(Json).id + "' data-content = '" + jQuery.parseJSON(Json).name + "'><p id='clear_p'>x</p><span style='color:#B6BF00;cursor: pointer;'>" + jQuery.parseJSON(Json).name + "</span></li>";
                }
                $("#Promoter_UL").append(str);
                //
				$('#iframeContent', window.parent.document).height($('.nav-right').height());
                
                //将关系添加进数据库
                var projectWithPromoter = [];
                var relation = '{"projectId":"'+ projectId +'","promoterId":"'+ id +'"}';
                projectWithPromoter.push(relation);
                  $.ajax({
                	type:"post",
        			url:"insertPromoters",
        			data:"array="+projectWithPromoter
                });
        
        });
	 
			
			//点击全选，将当前列表中的所有名称添加到项目中
			$(".promoter_submit").click(function () {
				
				var projectWithPromoter = [];

	        	var session;
	        	var result = [];
	        	var _html = "";
	            $("#promoterT table").find("tr").each
	            (
	     	     function(index,item)
	     	     {
	     	      $(item).find("td").each
	     	      (
	     	       function(indextd,itemtd)
	     	       {
	     			session = hashMap.get("Promoter_Content");
	     			var id = $(itemtd).find("span").data('id');
	     			if(id != null)
	     			{
	            			var name = $(itemtd).find("span").data('content');
	            			var Json = '{"id":"' + id + '","name":"' + name + '"}';
	            			if (!App.Com.isnullorempty(session)) {

	                            var array = jQuery.parseJSON(session);
	                            var index = session.indexOf(Json);
	                            if(index == -1)
	                            {
	                            	array.push(jQuery.parseJSON(Json));
	                            }
	                            result = array;
	                            hashMap.set("Promoter_Content", JSON.stringify(result) == "[]" ? "" : JSON.stringify(result));
	                        } else {

	                        result.push(jQuery.parseJSON(Json));
	                        hashMap.set("Promoter_Content", JSON.stringify(result) == "[]" ? "" : JSON.stringify(result));
	                        }
	     			}
	     		   }
	     	      );		
	             }
	     		);
	            for (var i = 0; i < result.length; i++)
	            {
	            	if((result[i].name).length > 5)
	            	{
	            		_html += "<li data-id = '" + result[i].id + "' data-content = '" + result[i].name + "'><p id='clear_p'>x</p><span style='color:#B6BF00;cursor: pointer;'>" + (result[i].name).substring(0,5)+"..." + "</span></li>";
	            	}
	            	else
	            	{
		            	_html += "<li data-id = '" + result[i].id + "' data-content = '" + result[i].name + "'><p id='clear_p'>x</p><span style='color:#B6BF00;cursor: pointer;'>" + result[i].name + "</span></li>";
	            	}
	            //拼接项目与推广员关系json数组
    			var relation = '{"projectId":"'+ projectId +'","promoterId":"'+ result[i].id +'"}';
    			
    			projectWithPromoter.push(relation);
	            }
	            $("#Promoter_UL").html(_html);
	            //
				$('#iframeContent', window.parent.document).height($('.nav-right').height());
	            
	          //  alert(projectWithRetailer);
	            //批量添加
                  $.ajax({
                	type:"post",
        			url:"insertPromoters",
        			data:"array="+projectWithPromoter
                }); 
	            
	        });
        //});
        
		//从项目中删除名称
        $("#Promoter_UL").on("click", "li p", function () {
//          /*关闭*/
        	var session = hashMap.get("Promoter_Content");
            var id = $(this).parent().data('id');
            var name = $(this).parent().data('content');
            var Json = '{"id":"' + id + '","name":"' + name + '"}';
            if (!App.Com.isnullorempty(session)) {
                var array = jQuery.parseJSON(session);
                for (var i = 0; i < array.length; i++) { if (array[i].id == id) { array.splice(i,i+1); } }

                hashMap.set("Promoter_Content", JSON.stringify(array) == "[]" ? "" : JSON.stringify(array));
            }
                $(this).parent().remove();
                //
				$('#iframeContent').height($('.nav-right').height());
                
             //判断发货推广员是否和删除的推广员为同一个人，若是，则也删除
             var send_Promoter = hashMap.get("Send_Promoter");
             if (!App.Com.isnullorempty(send_Promoter)) 
             {
            	 var promoter = jQuery.parseJSON(send_Promoter);
            	 if(promoter.id == id)
            	 {
            		hashMap.set("Send_Promoter", "");
             		$("#Promoters_Id").data("id","");
             		$("#Promoters_Id").html("");
             		
             		//删除数据库里的关系
             		 $.ajax
             		(
	             		{
	             			type:"post",
	            			url:"updateProjectAssistant",
	            			data:"projectId=" + projectId
	             		}
             		); 
            	 }
             }
                
              	//修改数据库
                var projectWithPromoter = [];
                var relation = '{"projectId":"'+ projectId +'","promoterId":"'+ id +'"}';
                projectWithPromoter.push(relation);
                  $.ajax({
                	type:"post",
        			url:"deletePromoters",
        			data:"array="+projectWithPromoter
                });  
        });
        
        
        //清空列表
        $("#resetPromoters").click(function()
        		{
        			//-------------------------------------
        			var projectWithPromoter = [];

		        	var session;
		        	var result = [];
		            $("#Promoter_UL").find("li").each(
		            		function(index,item)
		            		{
		            			session = hashMap.get("Promoter_Content");
		            			var id = $(item).data('id');
		            			var name = $(item).data('content');
		            			var Json = '{"id":"' + id + '","name":"' + name + '"}';
		            			if (!App.Com.isnullorempty(session)) {
	
		                            var array = jQuery.parseJSON(session);
		                            var index = session.indexOf(Json);
		                            if(index == -1)
		                            {
		                            	array.push(jQuery.parseJSON(Json));
		                            }
		                            result = array;
		                        } else {
	
		                        result.push(jQuery.parseJSON(Json));
		                        }
		            			
		            		});
		            for (var i = 0; i < result.length; i++)
		            {
			            //拼接项目与推广员关系json数组
		    			var relation = '{"projectId":"'+ projectId +'","promoterId":"'+ result[i].id +'"}';
		    			projectWithPromoter.push(relation);
		            }
        			//-------------------------------------
        			
        			
        		 	
        			//清空发货推广员
        			var send_Promoter = hashMap.get("Send_Promoter");
        			var promoter_Content = hashMap.get("Promoter_Content");
        			
        			if (!App.Com.isnullorempty(promoter_Content))
        			{
        				hashMap.set("Promoter_Content", "");
        				$("#Promoter_UL").empty();
        				//
        				$('#iframeContent').height($('.nav-right').height());
        				 $.ajax({
	   	                	type:"post",
	   	        			url:"deletePromoters",
	   	        			data:"array="+projectWithPromoter
	   	                });  

        			}
        			
        			if (!App.Com.isnullorempty(send_Promoter))
        			{
        				hashMap.set("Send_Promoter", "");
            		 	$("#Promoters_Id").empty();
            		 	//
        				$('#iframeContent', window.parent.document).height($('.nav-right').height());
        				 $.ajax
                 		(
                     		{
                     			type:"post",
                    			url:"updateProjectAssistant",
                    			data:"projectId=" + projectId
                     		}
                 		); 
        				
        			}
        		 	 
        		});
        
        //点击已选推广员添加发货推广员
        $("#Promoter_UL").on("click", "li span", function ()
        {
        	var session = hashMap.get("Send_Promoter");
        	var id = $(this).parent().data("id");
        	var name = $(this).parent().data("content");
        	var Json = '{"id":"' + id + '","name":"' + name + '"}';
        	var nameString = name;
        	if(name.length > 6)
        	{
        		nameString = name.substring(0,6)+"...";
        	}
        	if (App.Com.isnullorempty(session)) 
        	{
        		hashMap.set("Send_Promoter", Json);
        		$("#Promoters_Id").data("id",id);
        		$("#Promoters_Id").html("<p class = 'li_choose' id='clear_p'>x</p><span data-content = '"+ name +"' style='color:#B6BF00;cursor: pointer;'>" + name + "</span>");
        		//修改数据库
        		 $.ajax
         		(
             		{
             			type:"post",
            			url:"updateProjectAssistant",
            			data:"projectId=" + projectId + "&promoterId=" + id
             		}
         		); 
        	}
        	else if(jQuery.parseJSON(session).id == id)
        	{
        		//alert("不能重复添加!");
        		return false;
        	}
        	else
        	{
        		hashMap.set("Send_Promoter", Json);
        		$("#Promoters_Id").data("id",id);
        		$("#Promoters_Id").html("<p class = 'li_choose' id='clear_p'>x</p><span data-content = '"+ name +"' style='color:#B6BF00;cursor: pointer;'>" + nameString + "</span>");
        		
        		//修改数据库
        		 $.ajax
         		(
             		{
             			type:"post",
            			url:"updateProjectAssistant",
            			data:"projectId=" + projectId + "&promoterId=" + id
             		}
         		); 
        	}
        
        });
        
        //点击删除发货推广员
        $("#Promoters_Id").on("click","p",function()
        {
        	//alert("aaa");
        	if($(this).text() != "")
        	{
        		hashMap.set("Send_Promoter", "");
        		$("#Promoters_Id").data("id","");
        		$("#Promoters_Id").html("");
        		//修改数据库
        		 $.ajax
         		(
             		{
             			type:"post",
            			url:"updateProjectAssistant",
            			data:"projectId=" + projectId
             		}
         		); 
        	}
        });
        
      //悬停显示label
		$("#promoterT").on("mouseover"," table tr td span",function()
			{
				var name = $(this).data("content");
				if(name != null && name.length > 10)
				{
					$(".tips").css("left",event.x);
					$(".tips").css("top",event.y);
					$(".tips").text(name);
					$(".tips").css("display","block");
				}
			}
		);
		$("#promoterT").on("mouseout","table tr td span",function()
				{
					$(".tips").css("display","none");
					$(".tips").text("");
				}
			);
		
		$("#Promoter_UL").on("mouseover","li span",function()
				{
					var name = $(this).parent().data("content");
					if(name.length > 5)
					{
						$(".tips").css("left",event.x);
						$(".tips").css("top",event.y);
						$(".tips").text(name);
						$(".tips").css("display","block");
					}
				}
			);
		$("#Promoter_UL").on("mouseout","li span",function()
				{
					$(".tips").css("display","none");
					$(".tips").text("");
				}
			);
		$("#Promoters_Id").on("mouseover","span",function()
				{
					var name = $(this).data("content");
					if(name.length > 5)
					{
						$(".tips").css("left",event.x);
						$(".tips").css("top",event.y);
						$(".tips").text(name);
						$(".tips").css("display","block");
					}
				}
			);
		$("#Promoters_Id").on("mouseout","span",function()
				{
					$(".tips").css("display","none");
					$(".tips").text("");
				}
			);
        
	 });
	//--------------end---------------
    
        //图片上传预览    IE是用了滤镜。
        function previewImage(file, divid) {
            $('#iframeContent', window.parent.document).height(1600);
            var MAXWIDTH = 318;
            var MAXHEIGHT = 400;
            var div = document.getElementById('' + divid + '');
            if (file.files && file.files[0]) {
                var _fileid = file.id
                var _imgid = file.id + "1";

                div.innerHTML = '<img id=' + _imgid + ' onclick=$("#' + _fileid + '").click()>';
                var img = document.getElementById('' + _imgid + '');
             
                img.onload = function () {
                    var rect = clacImgZoomParam(MAXWIDTH, MAXHEIGHT, img.offsetWidth, img.offsetHeight);
                
                    img.width = rect.width;
                    img.height = rect.height;
                    img.style.marginTop = rect.top + 'px';
                }
                var reader = new FileReader();
                reader.onload = function (evt) {
                        img.src = evt.target.result;
                }
                reader.readAsDataURL(file.files[0]);
            }
            else //兼容IE
            {
                var sFilter = 'filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale,src="';
                file.select();
                var src = document.selection.createRange().text;
                div.innerHTML = '<img id=' + _imgid + '>';
                var img = document.getElementById('' + _imgid + '');
                img.filters.item('DXImageTransform.Microsoft.AlphaImageLoader').src = src;
                var rect = clacImgZoomParam(MAXWIDTH, MAXHEIGHT, img.offsetWidth, img.offsetHeight);
                status = ('rect:' + rect.top + ',' + rect.left + ',' + rect.width + ',' + rect.height);
                div.innerHTML = "<div id=divhead style='width:" + rect.width + "px;height:" + rect.height + "px;margin-top:" + rect.top + "px;" + sFilter + src + "\"'></div>";
            }
        }
        function clacImgZoomParam(maxWidth, maxHeight, width, height) {
            var param = { top: 0, left: 0, width: width, height: height };


            //如超最大的图片大小，则宽高定为限制的高宽
            if (width != maxWidth || height != maxHeight) {
                param.width = maxWidth;
                param.height = maxHeight;
            }

            param.left = Math.round((maxWidth - param.width) / 2);
            param.top = Math.round((maxHeight - param.height) / 2);

            return param;
        }
        
        
    </script>


</head>
<body>
	<p class = "tips" style = "position:absolute;background-color: white;display:none;"></p>
    <div class="content-flickr">

        <!-- 左侧 -->
        <div class="nav-left">
            <ul class="sub-nav">

                <li class="Activity"><span class="li-Yspan"></span><a href="#Activity"  onclick="return false;" class="a-Ycolor">活动信息</a></li>
                <li class="Person"><span class="li-Nspan"></span><a href="#Person" onclick="return false;" id="ret_bb" class="a-Ncolor">选择零售商</a></li>
                <li class="Person2"><span class="li-Nspan"></span><a href="#Person2" onclick="return false;" id="pro_bb" class="a-Ncolor">选择推广员</a></li>
                <li class="Gift"><span class="li-Nspan"></span><a href="#Gift" onclick="return false;" class="a-Ncolor">礼品配置</a></li>
                <li class="Product"><span class="li-Nspan"></span><a href="#Product" onclick="return false;" class="a-Ncolor">产品配置</a></li>
                <li class="Grade"><span class="li-Nspan"></span><a href="#Grade" onclick="return false;" class="a-Ncolor">等级配置</a></li>
            </ul>
        </div>
        <!-- 右侧 -->
         <div class="nav-right">
        <!--活动信息  -->
        <input type="hidden" id="hiddden_provId" value="${provinceId}">
         <input type="hidden" id="project_id" value="">
        <table id="Activity" class="table actived" style="display:block">
            <tbody id="project_active">
            
                <tr>
                
                    <td style="width: 10%;">项目省份：</td>
                    <td style="width: 80%;">
                    
                        <select id="Province" name="Province" <c:if test='${not empty projectProvince.provinceId }'>disabled="disabled"</c:if>>
                   
							<c:choose>
							<c:when test="${not empty provinces}">
									<option value="-1">请选择省份</option>
								<c:forEach items="${provinces}" var="province">
									<option value='${fn:substring(province,(fn:indexOf(province,"-")+1),(fn:length(province)))}' <c:if test='${projectProvince.provinceId == fn:substring(province,(fn:indexOf(province,"-")+1),(fn:length(province))) }'> selected='selected' </c:if>>
										<c:out value='${fn:substring(province,0,(fn:indexOf(province,"-")))}'></c:out>
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
                        </td>
                    </tr>
                    <tr>
                        <td>开始时间：</td>
                        <td><input type="text" id="Start_time" class="Start_time" name="Start_time" readonly="readonly" placeholder="请选择开始时间" value="${projectProvince.startDate}"/></td>
                    </tr>
                    <tr>
                        <td>结束时间：</td>
                        <td><input type="text" id="End_time" name="End_time" class="End_time" readonly="readonly" placeholder="请选择结束时间" value="${projectProvince.endDate}"/></td>
                    </tr>
                    <tr>
                        <td>项目标题：<input type="hidden" id="edit_id"  name="edit_id" value="${projectId}"></td>
                        <td><input id="Title" name="Title" type="text" placeholder="请输入项目标题" value="${projectProvince.projectTitle}" onblur="is_exist()"/></td>
                    </tr>
                    <tr>
                        <td>活动介绍：</td>
                        <td><textarea id="Introduce" name="Introduce"  rows="3" cols="20" placeholder="请输入活动介绍" >${projectProvince.description}</textarea></td>
                    </tr>
                    <tr>
                        <td>海报图片：</td>
                        <td style="text-align: left">
                            <div id="preview">
                            <c:if test="${msg ==1 }">
                                <img id="Poster_img1" border="0" src="data:image/jpg;base64,${projectProvince.placardBase64}" width="90" height="90" onclick="$('#Poster_img').click();">
                            </c:if>
                           <c:if test="${msg !=1 }">
                                <img id="Poster_img1" border="0" src="${pageContext.request.contextPath}/Resources/html/images/smallImg/photo_icon.png" width="90" height="90" onclick="$('#Poster_img').click();">
                            </c:if>
                            </div>
                            <input type="file" onchange="previewImage(this, 'preview')" style="display: none;" class="Poster_img" id="Poster_img" name="Poster_img" >
                        </td>
                    </tr>
                    <tr>
                        <td>项目背景图：</td>
                        <td style="text-align: left">
                            <div id="previewB">
                            <c:if test="${msg ==1 }">
                                <img id="Back_img1" border="0" src="data:image/jpg;base64,${projectProvince.backgroundBase64}" width="90" height="90" onclick="$('#Back_img').click();">
                            </c:if>
                            <c:if test="${msg !=1 }">
                                <img id="Back_img1" border="0" src="${pageContext.request.contextPath}/Resources/html/images/smallImg/photo_icon.png" width="90" height="90" onclick="$('#Back_img').click();">
                            </c:if>
                            </div>
                            <input type="file" onchange="previewImage(this, 'previewB')" style="display: none;" class="Back_img" id="Back_img" name="Back_img" >
                            &nbsp;<span style="color:red">请上传尺寸400（高）*318（宽）的图片</span>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="3" style="text-align:left"><button type="button" id="Activity_submit" class="active_button" >下一步</button></td>
                    </tr>
                </tbody>
            </table>
            <!-- 选择零售商 -->
            <div id="Person" class="actived">
                <input class="" id="resetRetailers" type="button" value="清空">
                <fieldset>
                    <div class="div-left field-div">已选:</div>
                    <div class="div-left" style="width:90%;">
                        <ul id="Person_UL">
                        <c:if test="${not empty retailers}">
                        <c:forEach items="${retailers }" var="r">
                       <c:if test="${fn:length(r.chineseName) gt 5 }">
                        <li data-id = "${r.id }" data-content = "${r.chineseName }"><p id="clear_p">x</p><span style="color:#B6BF00;cursor: pointer;">${fn:substring(r.chineseName,0,5) }...</span></li>
                        </c:if>
                        <c:if test="${fn:length(r.chineseName) le 5 }">
                        <li data-id = "${r.id }" data-content = "${r.chineseName }"><p id="clear_p">x</p><span style="color:#B6BF00;cursor: pointer;">${r.chineseName }</span></li>
                       </c:if>
                        </c:forEach>
                        </c:if>
                        </ul>
                    </div>
                </fieldset>
                <button id="Person_submit">下一步</button>
                
                 <!-- start -->
                <fieldset>
                <div id="People_Content" class="table_content">
                    <div id="retailerT" style = "color:black;">
                    </div>
                    <table style='width:100%;'>
                    	<tbody>
                    		<tr>
                    			<td style='width:50%;'>
                    				<button id='Person_Submit' class='person_submit'>全选</button>
                    				</td>
                    			</tr>
                    		</tbody>
                    	</table>
                </div>
                </fieldset>
			<!-- end -->
            </div>
           
            <!-- 选择推广员 -->
            <div id="Person2" class="actived">
                <input class="choosePeople" id="resetPromoters" type="button" value="清空">
                <fieldset>
                    <div class="div-left field-div">已选:</div>
                    <div class="div-left" style="width:90%;">
                        <ul id="Promoter_UL">
                        <c:if test="${not empty promoters}">
                           <c:forEach items="${promoters}" var = "p">
                          <c:if test="${fn:length(p.chineseName) gt 5 }">
                           <li data-id = "${p.id }" data-content = "${p.chineseName }"><p id="clear_p">x</p><span style="color:#B6BF00;cursor: pointer;">${fn:substring(p.chineseName,0,5) }...</span></li>
                           </c:if>
                           <c:if test="${fn:length(p.chineseName) le 5 }">
                        <li data-id = "${p.id }" data-content = "${p.chineseName }"><p id="clear_p">x</p><span style="color:#B6BF00;cursor: pointer;">${p.chineseName }</span></li>
                          </c:if>
                       </c:forEach>
                       </c:if>
                        </ul>
                    </div>
                </fieldset>
                <div style="color: red; margin-left:10%;margin-top:10px">*请单击已选推广员以选择一个发货推广员</div>
                <fieldset>
                    <div class="div-left field-div" style="width:15%;margin-bottom: 25px;margin-top: 25px;">发货推广员:</div>
                     <c:choose>
                     <c:when test="${not empty promoter}">
                     <div class="div-left field-choose" style="margin-bottom: 25px;margin-top: 25px;" id="Promoters_Id" data-id = "${promoter.id }" data-content = "${promoter.chineseName}"><p class = 'li_choose' id="clear_p">x</p><span style="color:#B6BF00;margin-left:-15px;cursor: pointer;" data-content = "${promoter.chineseName}">${promoter.chineseName}</span></div>
                     </c:when>
                     <c:otherwise>
                     <div class="div-left field-choose" style="margin-bottom: 25px;margin-top: 25px;" id="Promoters_Id" data-id = ""></div>
                     </c:otherwise>
                     </c:choose>
                    
                    <%-- <input type="text" id="Promoters_Id" style="display:none" value="${ promoter.id}"/> --%>
                </fieldset>
                <button id="Person_submit2">下一步</button>
                
                 <!-- start -->
                <fieldset>
                <div id="Promoter_Content" class="table_content">
                    <div id="promoterT" style = "color:black;">
                    </div>
                    <table style='width:100%;'>
                    	<tbody>
                    		<tr>
                    			<td style='width:50%;'>
                    				<button id='Person_Submit' class='promoter_submit'>全选</button>
                    			</td>
                    		</tr>
                    	</tbody>
                    </table>
                </div>
                </fieldset>
			<!-- end -->
            </div>

            <!-- 礼品配置 -->
            <table id="Gift" class="table actived">
                <thead>
                    <tr>
                         <th width="40%">礼品</th>
                        <th width="40%">兑换积分</th>
                        <th width="20%">操作</th>
                    </tr>
                    <tr>
                        <td class="td_line" colspan="3"></td>
                    </tr>
                </thead>
                <tbody>
                
                <!-- 载入礼品配置 -->
                <c:choose>
                 <c:when test="${not empty exchangeShopGoods}">
                 	<c:forEach var="goods" items="${exchangeShopGoods}">
                 		<tr>
	                    <td><a>${goods.gName }</a></td>
	                    <td>${goods.exPoints }</td>
                        <td><a class="delete" data-giftid = "${goods.goodsId }">删除</a></td>
                    </tr>
                 	</c:forEach>
                 </c:when>
                </c:choose>
                    <tr class="add">
                        <td colspan="3" class="text-left" style="width: 30%;"><a id="addGift" class="popUp"><img src="${pageContext.request.contextPath}/Resources/html/images/smallImg/Capture.png" style="width: 5%;" /><div style="width: 20%;">添加礼品</div></a><button id="Gift_submit">下一步</button></td>
                    <td style="width:83%;"></td>                   </tr>
                </tbody>
            </table>

            <!-- 产品配置 -->
            <table id="Product" class="table actived">
                <thead>
                    <tr>
                         <th width="20%">产品类别</th>
                        <th width="20%">产品家族</th>
                        <th width="20%">产品名称</th>
                        <th width="20%">1积分对应销售额</th>
                        <th width="20%">操作</th>
                    </tr>
                    <tr>
                        <td class="td_line" colspan="5"></td>
                    </tr>
                </thead>
                <tbody>
                 <!-- 载入产品配置 -->
                <c:choose>
                 <c:when test="${not empty productInfos}">
                 	<c:forEach var="product" items="${productInfos}">
                 		<tr>
                       	<td><a>${product.productCategory }</a></td>
	                    <td>${product.productFamily }</td>
	                    <td>${product.productName }</td>
	                    <td>${product.salesAmount }</td>
                        <td><a class="delete" data-productid= "${product.productId}">删除</a></td>
                    </tr>
                 	</c:forEach>
                 </c:when>
                </c:choose>
                    <tr class="add">
                        <td colspan="5" class="text-left"><a id="addProduct" class="popUp"><img src="${pageContext.request.contextPath}/Resources/html/images/smallImg/Capture.png" style="width: 5%;" /><div>添加产品</div></a><button id="Product_submit">下一步</button></td>
                    </tr>
                </tbody>
            </table>

            <!-- 等级配置 -->
            <table id="Grade" class="table actived">
                <thead>
                    <tr>
                         <th width="40%">等级</th>
                        <th width="40%">积分标线</th>
                        <th width="20%">操作</th>
                    </tr>
                    <tr>
                        <td class="td_line" colspan="3"></td>
                    </tr>
                </thead>
                <tbody>
                 <!-- 载入积分等级配置 -->
                <c:choose>
                 <c:when test="${not empty pointsLevels}">
                 	<c:forEach var="pointsLevel" items="${pointsLevels}">
                 		<tr>
                        <td><a>${pointsLevel.name }</a></td>
                    	<td>${pointsLevel.points }</td>
                        <td><a class="delete" data-rank= "${pointsLevel.name }">删除</a></td>
                    </tr>
                 	</c:forEach>
                 </c:when>
                </c:choose>
                    <tr class="add">
                        <td colspan="4" style="text-align: left;"><a id="configGrade" class="popUp"><img src="${pageContext.request.contextPath}/Resources/html/images/smallImg/Capture.png" style="width: 5%;" /><div>添加条目</div></a><button id="Grade_submit">完成</button></td>
                    </tr>
                </tbody>
            </table>
        </div>

    </div>
    <!-- 显示内容 -->
    <div id="layer" style="display:none">

<script type="text/javascript">

	
	$(function()
	{
		/*礼品下拉框加载*/
		$("#addGift").click(function()
		{
					var tr = "<input type='text' id='GiftNumber' style='width: 73%'  autocomplete='off'/>";
					$("#GiftNumber_td").html(tr);
			$.ajax({
				type:"post",
				url:"GoodsInfo",
				dataType:"json",
				success:function(json)
				{
					var contents = "<option value='0'>请选择礼品</option>";
					for(var i = 0; i < json.length;i++)
					{
						contents += "<option value='" + json[i].id + "'>" + json[i].name + "</option>";
					}
					$("#selectGift").html(contents);
				}
			});
		});
		
		/*产品下拉框加载*/
		$("#addProduct").click(function()
		{
			var tr = "<input type='text' id='Sales' style='width: 73%'  autocomplete='off'/>";
		    $("#Sales_td").html(tr);
			$.ajax({
				type:"post",
				url:"ProductCategory",
				dataType:"json",
				success:function(json)
				{
					var contents = "<option value='0'>请选择产品类别</option>";
					for(var i = 0; i < json.length;i++)
					{
						contents += "<option value='" + json[i].id + "'>" + json[i].name + "</option>";
					}
					$("#selectType").html(contents);
					$("#selectFamily").html("<option value='0'>请选择产品家族</option>");
					$("#selectProduct").html("<option value='0'>请选择产品</option>");
				}
			});
		});
		
		/*选择产品目录，加载相应的产品家族*/
		$("#selectType").change(function()
		{
			var categoryId = $("#selectType").val();
			var contents = "<option value='0'>请选择产品家族</option>";
			if(categoryId != 0)
			{
				$.ajax({
					type:"post",
					url:"ProductFamily",
					data:"categoryId="+categoryId,
					dataType:"json",
					success:function(json)
					{
						for(var i = 0; i < json.length;i++)
						{
							contents += "<option value='" + json[i].id + "'>" + json[i].name + "</option>";
						}
						$("#selectFamily").html(contents);
						$("#selectProduct").html("<option value='0'>请选择产品</option>");
						
					}
				});
			}
			else
			{
				$("#selectFamily").html(contents);
				$("#selectProduct").html("<option value='0'>请选择产品</option>");
			}
		});
		
		/*选择产品家族，加载相应的产品*/
		$("#selectFamily").change(function()
				{
			
					var familyId = $("#selectFamily").val();
					var contents = "<option value='0'>请选择产品</option>";
					if(familyId != 0)
					{
						$.ajax({
							type:"post",
							url:"Product",
							data:"familyId="+familyId,
							dataType:"json",
							success:function(json)
							{
								contents = "<option value='0'>请选择产品</option>";
								for(var i = 0; i < json.length;i++)
								{
									contents += "<option value='" + json[i].id + "'>" + json[i].name + "</option>";
								}
								if(json.length > 1)
								{
									contents += "<option value='all'>全部</option>";
								}
							$("#selectProduct").html(contents);
							}
						});
					}
					else
					{
						$("#selectProduct").html(contents);
					}
				});
		
	});
	
	 /*等级配置*/
	 $("#configGrade").click(function(){
		 var tr1 = "<input type='text' id='rank' placeholder='请输入等级名称'  autocomplete='off'/>";
		 var tr2 = "<input type='text' id='gradeLine' placeholder='请输入积分标线'  autocomplete='off'/>";
		 $("#rank_td").html(tr1);
		 $("#gradeLine_td").html(tr2);
	 })
	
</script>

        <!--礼品配置-->
        <div class="output outputSelect gift" style="display: none">
            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
            <div class="line">
                <table style="border-spacing: 15px;">
                    <tr>
                        <td>
                            <label for="">礼品：</label>
                        </td>
                        <td>
                            <select name="" id="selectGift">
                                <option value="0">请选择礼品</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label for="">兑换积分：</label>
                        </td>
                        <td id="GiftNumber_td">
                            <input type="text" id="GiftNumber" style="width: 73%" />
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2">
                            <button id="Gift_Submit" class="config">确定</button>
                        </td>
                    </tr>
                </table>

            </div>
        </div>

        <!--产品配置-->
        <div class="output outputSelect product" style="display: none; width: 65%; left: 15%;">
            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
            <div class="line">
                <table style="border-spacing: 15px;">
                    <tr>
                        <td>
                            <label for="selectType">产品类别：</label>
                        </td>
                        <td>
                            <select name="" class="selectType" id="selectType">
                                <option value="0" hidden>请选择产品类别</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label for="selectType">产品家族：</label>
                        </td>
                        <td>
                            <select name="" class="selectFamily" id="selectFamily">
                                <option value="0" hidden>请选择产品家族</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label for="">产品：</label>
                           
                        </td>
                        <td>
                            <select name="" class="selectProduct" id="selectProduct">
                             <option value="0" hidden>请选择产品</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label for="">1积分对应销售额：</label>
                        </td>
                        <td id="Sales_td">
                            <input type="text" id="Sales" style="width: 73%"/>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2">
                            <button id="Product_Submit" class="config">确定</button>
                        </td>
                    </tr>
                </table>

            </div>
        </div>

        <!--积分配置-->
        <div class="output outputSelect integral" style="display: none;">
            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
            <div class="line">
                <table style="border-spacing: 15px;">
                    <tr>
                        <td>
                            <label for="">产品类别：</label>
                        </td>
                        <td>
                            <select name="" class="selectType">
                            <option value="0" hidden>请选择产品类别</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label for="">产品家族：</label>
                        </td>
                        <td>
                            <select name="" class="selectFamily">
                            <option value="0" hidden>请选择产品类别</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label for="">产品：</label>
                        </td>
                        <td>
                            <select name="" class="selectProduct">
                            <option value="0" hidden>请选择产品类别</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label for="sales">1积分对应销售额：</label>
                        </td>
                        <td>
                            <input id="sales" type="text" placeholder="请输入销售额">
                        </td>

                    </tr>
                    <tr>
                        <td colspan="2">
                            <button id="Integral_Submit" class="config">确定</button>
                        </td>
                    </tr>
                </table>

            </div>
        </div>

        <!--等级配置-->
        <div class="output outputSelect grade" style="display: none">
            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
            <div class="line">
                <table style="border-spacing: 15px;">
                    <tr>
                        <td>
                            <label for="">等级：</label>
                        </td>
                        <td id="rank_td">
                            <input id="rank" type="text" placeholder="请输入等级名称" autocomplete="off">
                        </td>

                    </tr>
                    <tr>
                        <td>
                            <label for="">积分标线：</label>
                        </td>
                        <td id="gradeLine_td">
                            <input id="gradeLine" type="text" placeholder="请输入积分标线" autocomplete="off">
                        </td>

                    </tr>
                    <tr>
                        <td colspan="2">
                            <button id="Grade_Submit" class="config">确定</button>
                        </td>
                    </tr>
                </table>

            </div>
        </div>

        <div class="hide"></div>
    </div>
</body>
</html>
