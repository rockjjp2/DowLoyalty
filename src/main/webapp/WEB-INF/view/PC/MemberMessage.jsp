<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title></title>
    <link href="${pageContext.request.contextPath}/Resources/html/css/Datalist.css" rel="stylesheet" />
    <link href="${pageContext.request.contextPath}/Resources/html/css/paging.css" rel="stylesheet" />
    <script src="${pageContext.request.contextPath}/Resources/html/js/jquery.js"></script>
    <style>
        select {
            /*Chrome和Firefox里面的边框是不一样的，所以复写了一下*/
            /*很关键：将默认的select选择框样式清除*/
            appearance: none;
            -moz-appearance: none;
            -o-appearance: 3px;
            -webkit-appearance: none; /*for chrome*/
            /*在选择框的最右侧中间显示小箭头图片*/
            background: url("${pageContext.request.contextPath}/Resources/html/images/smallImg/green-triangle.png") no-repeat scroll right center transparent;
            background-size: 18% 90%;
            /*为下拉小箭头留出一点位置，避免被文字覆盖*/
            padding-right: 6%;
            padding-left: 2%;
            border: 1px solid #ADADAD;
            width: 80%;
            height: 24px;
            float: right;
            border-radius: 2px;
            -webkit-border-radius: 2px;
            -o-border-radius: 2px;
            -moz-border-radius: 2px;
            padding-left: 20px;
            margin-top:5.5px
             
        }

            /*清除ie的默认选择框样式清除，隐藏下拉箭头*/
            select::-ms-expand {
                display: none;
            }

        .btnOperate {
        margin-top:5.5px;
        }
    </style>
    <script type="text/javascript">
    function showMessage(pageNow){
    	$.ajax({
 			type: "POST",
 			url:"expointsajax",
 			data: {"pageNow":pageNow},
 			dataType: "json",
 			success:function(data){
				var json=eval(data);
				//$("#messagecontent tr").remove();
				var Str= "";
				if(json.messageVos != null && json.messageVos.length != 0)
	            {
					$.each(json.messageVos,function (index,entity){
						Str+="<tr class='table-tr-odd'><td valign='top' id='"+entity.id+"' name='"+entity.retailerId+"'>"+entity.retailerName+"</td>";
						Str+="<td valign='top'>"+entity.submitTime+"</td><td style='text-align:left'>"+(entity.message == null ? '' : entity.message)+"</td>";
			            //项目选择
			            if(entity.projects != null && entity.projects.length != 0)
			            {
			            	Str+="<td valign='top'><select>"; 
			            	$.each(entity.projects,function (index,prj){
				            	Str=Str+"<option value='"+prj.id+"'>"+prj.name+"</option>";
				            }) 
				             Str+="</select></td>";
				            Str+="<td valign='top'><input type='number' value='0' style='width:60px;padding-left:16px'  min='0'/></td>";
				            Str+="<td valign='top'><input type='button' value='保存' class='btnOperate' onclick='saveclick(this)'></td></tr>";
			            }
			            else
			            {
			            	Str+="<td valign='top'><select><option value=''>未参加任何活动</option></select></td>"; 
			            	Str+="<td valign='top'><input type='number' readonly value='0' style='width:60px;padding-left:16px'  min='0'/></td>";
				            Str+="<td valign='top'><input type='button' disabled value='保存' class='btnOperate' style = 'background-color:gray;' onclick='saveclick(this)'></td></tr>";
			            }
					})
	            }
				$("#messagecontent").html(Str);
				//分页选择
				$("#pagination-flickr li").remove();
				//后台传来当前页与总页数信息
				var nowPage=json.nowPage;
				var totalPage=json.totalPage;
				
				var page =  "<li class='firstPage' style='cursor: pointer;'>首页</li>"+
				    "<li class='previous-off' style='cursor: pointer;'>上一页</li>"+
					"<li class='active' id='nowpage'>第"+nowPage+"页</li>"+
					"<li class='next' style='cursor: pointer;'>下一页</li>"+
					"<li class='lastPage' style='cursor: pointer;'>末页</li>"+
					"<li class='active'>共"+totalPage+"页</li>"+
					"<li class='identify-page'><a class='identifyPage' style='cursor: pointer;'>跳转</a>第<input type = 'text' id = 'identifyPage' style = 'width:50px;'/>页</li>";
					
					$("#pagination-flickr").append(page);  
                 
               //上一页点击事件
               if(nowPage!=1){
	     			$(".previous-off").click(function(){
	     				var lastPage = nowPage - 1;
	     				showMessage(lastPage);
	     	    	});
               }
	        	//下一页点击事件
	        	if(nowPage!=totalPage){
	     			$(".next").click(function(){
	     				showMessage(nowPage+1);
	     	    	});
	        	}
	        		
     			//首页点击事件
     			$(".firstPage").click(function(){
     				showMessage(1);
     	    	});
     			//末页点击事件
     			$(".lastPage").click(function(){
     				showMessage(totalPage);
     	    	});
     			
     			//手动输入页面事件
     			$(".identifyPage").click(function(){
     				num = $("#identifyPage").val();
     				var exp = /^[0-9]+$/;
     				if(!exp.test(num)){
     					alert("输入的必须是有效数字");
     					return false;
     				}
     				if(num>totalPage||num==0){
     					alert("请输入正确的页码");
     					return false;
     				}
     				showMessage(num);
     	    	});
      		
				/* //当前页面取得的当前页
				var page_nowPage=$(".active").text();
				//ajsx拼出页面字段
				var pageStr1=""
				if(totalPage==1){
					pageStr1+="<li class='active'>1</li>";
				}else {
					//当前页不为第一页则有上一页按钮
					if(nowPage!=1){
						pageStr1+="<li class='previous-off'><a href='?page=1'>上一页</a></li>"
					}
					for(var i=1;i<=totalPage;i++){
						if(i==nowPage){
						pageStr1+="<li class='active'>"+i+"</li>";
						}else{
						pageStr1+="<li><a href='?page="+i+"'>"+i+"</a></li>";
						}
					}
					//当前页不为最后页则有下一页按钮
					if(nowPage!=totalPage){
						pageStr1+="<li class='next'><a href='?page=8'>下一页</a></li>"
					}
				}
				//页码选择，拼接到页面
				$("#pagination-flickr").append(pageStr1); */
				//重新计算高度
				var div_height = $(".content-flickr").height();
                $('#iframeContent', window.parent.document).height(div_height + 400); //为iframe赋值高度
 			}
 		   })
    }
    function saveclick(obj){
    	var trNode =obj.parentNode.parentNode;
    	var id=trNode.childNodes[0].id;
    	var retailerid=trNode.childNodes[0].getAttribute("name");
    	var projectId=trNode.childNodes[3].childNodes[0].value;
    	var point=trNode.childNodes[4].childNodes[0].value;
    	var exp = /^[0-9]+$/;
		if(!exp.test(point)){
			alert("请输入正确的分数");
			return false;
		}
    	$.ajax({
 			type: "POST",
 			url:"addpoints",
 			data: {"id":id,"retailerId":retailerid,"projectId":projectId,"point":point},
 			success:function(){
 				var page_nowPage=$("#nowpage").text();
 				var page_nowPage = page_nowPage.replace(/[^0-9]/ig,""); 
 				showMessage(page_nowPage);
 			},
 			error:function(){
 				alert("系统正忙，请稍后再试");
 			}
		})
    }
    $(function(){
    	showMessage(1);
    }) 
    </script>
</head>
<body>
    <div class="content-flickr" >
        <table class="content-table" cellspacing="0" border="0" style="margin-top:30px">
            <thead>
                <tr>
                    <td width="7%">会员名</td>
                    <td width="18%">留言时间</td>
                    <td width="37%">留言内容</td>
                    <td width="15%">项目</td>
                    <td width="15%">奖励积分</td>
                    <td width="8%"></td>
                </tr>
            </thead>
            <tbody id="messagecontent">
               <tr class="table-tr-odd" style = "display:none;">
                    <td valign="top">Becky</td>
                    <td valign="top">2017.01.20</td>
                    <td style="text-align:left">此处显示的用户项目留言，根据留言您可以给予相应的积分。请等待系统加载留言数据。</td>
                    <td valign="top"><select><option>四川柑橘</option></select> </td>
                    <td valign="top"><input type="number" value="0" style="width:30px;padding-left:16px" /></td>
                    <td valign="top"><input type="button" value="保存" class="btnOperate"></td>
                </tr>
 <!--                <tr class="table-tr-odd">
                    <td valign="top">Becky</td>
                    <td valign="top">2017.01.20</td>
                    <td style="text-align:left">积分项目这个活动很好，给我奖励个积分吧.</td>
                    <td valign="top"><select><option>四川柑橘</option></select> </td>
                    <td valign="top"><input type="number" value="0" style="width:30px;padding-left:16px" /></td>
                    <td valign="top"><input type="button" value="保存" class="btnOperate"></td>
                </tr>
                <tr class="table-tr-odd">
                    <td valign="top">Becky</td>
                    <td valign="top">2017.01.20</td>
                    <td style="text-align:left">积分项目这个活动很好，给我奖励个积分吧.</td>
                    <td valign="top"><select><option>四川柑橘</option></select> </td>
                    <td valign="top"><input type="number" value="0" style="width:30px;padding-left:16px" /></td>
                    <td valign="top"><input type="button" value="保存" class="btnOperate"></td>
                </tr>
                <tr class="table-tr-odd">
                    <td valign="top">Becky</td>
                    <td valign="top">2017.01.20</td>
                    <td style="text-align:left">积分项目这个活动很好，给我奖励个积分吧.</td>
                    <td valign="top"><select><option>四川柑橘</option></select> </td>
                    <td valign="top"><input type="number" value="0" style="width:30px;padding-left:16px" /></td>
                    <td valign="top"><input type="button" value="保存" class="btnOperate"></td>
                </tr>
                <tr class="table-tr-odd">
                    <td valign="top">Becky</td>
                    <td valign="top">2017.01.20</td>
                    <td style="text-align:left">积分项目这个活动很好，给我奖励个积分吧.</td>
                    <td valign="top"><select><option>四川柑橘</option></select> </td>
                    <td valign="top"><input type="number" value="0" style="width:30px;padding-left:16px" /></td>
                    <td valign="top"><input type="button" value="保存" class="btnOperate"></td>
                </tr>
                <tr class="table-tr-odd">
                    <td valign="top">Becky</td>
                    <td valign="top">2017.01.20</td>
                    <td style="text-align:left">积分项目这个活动很好，给我奖励个积分吧.</td>
                    <td valign="top"><select><option>四川柑橘</option></select> </td>
                    <td valign="top"><input type="number" value="0" style="width:30px;padding-left:16px" /></td>
                    <td valign="top"><input type="button" value="保存" class="btnOperate"></td>
                </tr>
                <tr class="table-tr-odd">
                    <td valign="top">Becky</td>
                    <td valign="top">2017.01.20</td>
                    <td style="text-align:left">积分项目这个活动很好，给我奖励个积分吧.</td>
                    <td valign="top"><select><option>四川柑橘</option></select> </td>
                    <td valign="top"><input type="number" value="0" style="width:30px;padding-left:16px" /></td>
                    <td valign="top"><input type="button" value="保存" class="btnOperate"></td>
                </tr>
                <tr class="table-tr-odd">
                    <td valign="top">Becky</td>
                    <td valign="top">2017.01.20</td>
                    <td style="text-align:left">积分项目这个活动很好，给我奖励个积分吧.</td>
                    <td valign="top"><select><option>四川柑橘</option></select> </td>
                    <td valign="top"><input type="number" value="0" style="width:30px;padding-left:16px" /></td>
                    <td valign="top"><input type="button" value="保存" class="btnOperate"></td>
                </tr> -->


            </tbody>
        </table>
        <div class="datalist-paging" >
            <ul id="pagination-flickr">
               <!--  <li class="previous-off">上一页</li>
                <li class="">1</li>
                <li><a href="?page=2">2</a></li>
                <li><a href="?page=3">3</a></li>
                <li><a href="?page=4">4</a></li>
                <li><a href="?page=5">5</a></li>
                <li><a href="?page=6">6</a></li>
                <li><a href="?page=7">7</a></li>
                <li class="next"><a href="?page=8">下一页</a></li> -->
				<li class="firstPage" style="cursor: pointer;">首页</li>
				<li class="previous-off" style="cursor: pointer;">上一页</li>
				<li class="active">第1页</li>
				<li class="next" style="cursor: pointer;">下一页</li>
				<li class="lastPage" style="cursor: pointer;">末页</li>
				<li class="active">共1页</li>
				<li class="identify-page"><a class="identifyPage" style="cursor: pointer;">跳转</a>第<input
					type="text" id="identifyPage" style="width: 50px;" />页</li>
			</ul>
        </div>
    </div>


</body>
</html>
