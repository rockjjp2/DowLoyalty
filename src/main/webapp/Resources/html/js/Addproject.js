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

var hashMap = {   
	    set : function(key,value){this[key] = value},   
	    get : function(key){return this[key]},   
	    contains : function(key){return this.Get(key) == null?false:true},   
	    remove : function(key){delete this[key]}   
	}

	function uploadImg(formData)
	{
		$.ajax({
			url : 'subProjectActive',
			type : 'POST',
			cache : false,
			data : formData,
			processData : false,
			contentType : false,
			success : function(returndata) {
				if (returndata.trim() == "exist") {
					alert("项目名已存在！");
					location.href = "createproject";
				} else{
					projectId = returndata.trim();
					initRetailerAndPromoter(projectId);

					page.$hide();
					page.$Show("Person");
				}
			}
		});
	}

$(function () {
	
    page.$Init();
});

var page = {
    $Init: function () {
        /*加载时间控件*/
        page.$Time();
        /*选择器*/
        page.$Empty();
        /*提交方法*/
        page.$Submit();
        /*选择人员*/
        page.$People();
        /*删除*/      /*新增——Manyu*/
        //  page.$delete();
        /*关闭*/
        page.$close();
        /*清空缓存*/
        $.session.clear();
    },

    
    
    
    /*加载时间控件*/
    $Time: function () {
        $('#End_time').datetimepicker({

            timepicker: false,
            format: 'Y/m/d',
            formatDate: 'Y/m/d',
            step: 10,
            minDate: '-1969/12/31' 
        });

        $('#Start_time').datetimepicker({
            timepicker: false,
            format: 'Y/m/d',
            formatDate: 'Y/m/d',
            step: 10,
            minDate: '-1969/12/31' // yesterday is minimum date
        });
    },

    /*选择器*/
    $Empty: function () {
        $(".sub-nav").on("click", "li", function () {

        	 var Activity = hashMap.get("Activity");
        	 var edit_id = $.trim($('#edit_id').val());
        	 if(!App.Com.isnullorempty(Activity) || !App.Com.isnullorempty(edit_id)){
        		 
        		 $(".sub-nav li span").addClass('li-Nspan');
        		 $(".sub-nav li a").addClass('a-Ncolor');
        		 $(this).children('span').removeClass();
        		 $(this).children('a').removeClass();
        		 $(this).children('span').addClass('li-Yspan');
        		 $(this).children('a').addClass('a-Ycolor');
        		 
        		 page.$hide();
        		 
        		 var href = $(this).children('a').attr('href');
        		 
        		 var rightHeight =   $(href).height();//创建新项目右边高度。
        		 var _height = rightHeight < 400 ? 400 : rightHeight;//由于要把左边的菜单显示出来 所以至少要800
        		 $('#iframeContent', window.parent.document).height(_height + 50); //为iframe赋值高度
        		 
        		 $(href).show();
        		 
        	 }
        		

        });
    },


    
    /*submit 提交*/
    $Submit: function () {
        /*活动信息*/
        $("#Activity_submit").click(function () {
            var Province = $("#Province").find("option:selected").text();//省份
            var Start_time = $.trim($(".Start_time").val());//开始时间
            var End_time = $.trim($(".End_time").val());//结束时间
            var Title = $.trim($("#Title").val());//标题
            var Introduce = $.trim($("#Introduce").val());//介绍
            if(projectId == "")
            {
            	 var Poster_img = $.trim($("#Poster_img").val());//海报图片
                 var Back_img = $.trim($("#Back_img").val());//项目背景图片
            }
            else
            {
            	var Poster_img = $.trim($("#Poster_img1").val());//海报图片
                var Back_img = $.trim($("#Back_img1").val());//项目背景图片
            }
           
            var edit_id = $.trim($('#edit_id').val());
           
            if (App.Com.isnullorempty(Province) || Province == "请选择省份") { alert("请选择省份！"); return false; }
            if (App.Com.isnullorempty(Start_time)) { alert("请选择开始时间！"); return false; }
            if (App.Com.isnullorempty(End_time)) { alert("请选择结束时间！"); return false; }
            var mydate = new Date();
            var mydate1 = new Date();
            if(!App.Com.isnullorempty(Start_time)){
            var applydate1 = mydate.setFullYear(Start_time.split('/')[0],Start_time.split('/')[1].replace(/\b(0+)/gi,""),Start_time.split('/')[2].replace(/\b(0+)/gi,""));
            var applydate2 = mydate1.setFullYear(mydate1.getFullYear(),mydate1.getMonth()+1,mydate1.getDate());
            if(applydate2 - applydate1>=0){alert("开始日期应大于当前日期！"); return false;}
            }
            if (End_time < Start_time) { alert("请确保结束时间大于开始时间！"); return false;}
            
            if (App.Com.isnullorempty(Title)) { alert("请填写标题！"); return false; }
        
            if (App.Com.isnullorempty(Introduce)) { alert("请填写介绍！"); return false; }
            if(App.Com.isnullorempty(edit_id)){
              if (App.Com.isnullorempty(Poster_img)) { alert("请选择海报图片！"); return false; }
              if (App.Com.isnullorempty(Back_img)) { alert("请选择背景图片！"); return false; }
            }

            
          /*  window.location.href="submitActiveInformation";*/

            var Json = '[{"Province":"' + Province + '","Start_time":" ' + Start_time + ' ","End_time":" ' + End_time + ' ","Title":" ' + Title + ' ","Introduce":" ' + Introduce + ' "}]';
            hashMap.set("Activity", Json);
            
            var Provinces = $('#Province').val();
            var formData = new FormData();
			formData.append('edit_id',edit_id);
			formData.append('Province', Provinces);
			formData.append('Start_time', Start_time);
			formData.append('End_time', End_time);
			formData.append('Title', Title);
			formData.append('Introduce', Introduce);

			if(projectId == "")
			{
				lrz($('#Poster_img')[0].files[0], { width: 640, quality: 0.8 }, function (rst) {              
	                var blob = dataURItoBlob(rst.base64);  // 上一步中的函数
	                formData.append("file", blob);
	                
	                lrz($('#Back_img')[0].files[0], { width: 640, quality: 0.8 }, function (rst) {              
	                    var blob = dataURItoBlob(rst.base64);  // 上一步中的函数
	                    formData.append("file1", blob);
	                    
	                    uploadImg(formData);
	                });
	            });
			}
			else
			{
				if(!App.Com.isnullorempty($.trim($('#Poster_img').val())) && App.Com.isnullorempty($.trim($('#Back_img').val())))
				{
					lrz($('#Poster_img')[0].files[0], { width: 640, quality: 0.8 }, function (rst) {              
		                var blob = dataURItoBlob(rst.base64);  // 上一步中的函数
		                formData.append("file", blob);
		                
		                uploadImg(formData);
		            });
				}
				else if(App.Com.isnullorempty($.trim($('#Poster_img').val())) && !App.Com.isnullorempty($.trim($('#Back_img').val())))
				{
	                lrz($('#Back_img')[0].files[0], { width: 640, quality: 0.8 }, function (rst) {              
	                    var blob = dataURItoBlob(rst.base64);  // 上一步中的函数
	                    formData.append("file1", blob);
	                    
	                    uploadImg(formData);
	                });
				}
				else
				{
					lrz($('#Poster_img')[0].files[0], { width: 640, quality: 0.8 }, function (rst) {              
		                var blob = dataURItoBlob(rst.base64);  // 上一步中的函数
		                formData.append("file", blob);
		                
		                lrz($('#Back_img')[0].files[0], { width: 640, quality: 0.8 }, function (rst) {              
		                    var blob = dataURItoBlob(rst.base64);  // 上一步中的函数
		                    formData.append("file1", blob);
		                    
		                    uploadImg(formData);
		                });
		            });
				}
			}
			
			
        });

        /*参与人员(零售商)*/
        $("#Person_submit").click(function () {
            var PeopleSession = hashMap.get("People_Content");//零售商
            if (App.Com.isnullorempty(PeopleSession)) { alert("请选择零售商！"); return false; }
            page.$hide();
            page.$Show("Person2");
        });

        /*参与人员(推广员)*/
        $("#Person_submit2").click(function () {
            var PromoterSession = hashMap.get("Promoter_Content");//推广员
            var Send_Promoter = hashMap.get("Send_Promoter");//被选发货推广员ID

            if (App.Com.isnullorempty(PromoterSession)) { alert("请选择推广员！"); return false; }
            if (App.Com.isnullorempty(Send_Promoter)) { alert("请选择发货推广员！"); return false; }

            page.$hide();
            page.$Show("Gift");
        });

        /*选择礼品*/
        $("#Gift_submit").click(function () {
            var GiftSession = hashMap.get("Gift");//礼品
            if (App.Com.isnullorempty(GiftSession)) { alert("请添加礼品！"); return false; }
            page.$hide();
            page.$Show("Product");
        });

        /*选择产品*/
        $("#Product_submit").click(function () {
            var ProductSession = hashMap.get("Product");//礼品

            if (App.Com.isnullorempty(ProductSession)) { alert("请添加产品！"); return false; }

            page.$hide();
            page.$Show("Grade");
        });

        /*完成*/
        $("#Grade_submit").click(function () {
            var Activity = hashMap.get("Activity");
            var People = hashMap.get("People_Content");
            var Promoter = hashMap.get("Promoter_Content");
            var Send_Promoter = hashMap.get("Send_Promoter");
            var Gift = hashMap.get("Gift");
            var Product = hashMap.get("Product");

            var Grade = hashMap.get("Grade");

            if (App.Com.isnullorempty(Activity)) { alert("请完成活动信息内容填写!"); return false; }
            if (App.Com.isnullorempty(People)) { alert("请完成参与人员-零售商选择!"); return false; }
            if (App.Com.isnullorempty(Promoter)) { alert("请完成参与人员-推广员选择!"); return false; }
            if (App.Com.isnullorempty(Send_Promoter)) { alert("请完成参与人员-发货员选择!"); return false; }
            if (App.Com.isnullorempty(Gift)) { alert("请完成礼品配置!"); return false; }
            if (App.Com.isnullorempty(Product)) { alert("请完成产品配置!"); return false; }

            if (App.Com.isnullorempty(Grade)) { alert("请完成等级配置!"); return false; }
 
            
           alert("项目创建成功");
           location.href = "createproject";
        });
    },

    /*选择层*/
    $People: function () {


        /*显示层*/     /*修改——Manyu*/
        $(".popUp").click(function () {
            addID = $(this).attr("id");
            // console.log(addID);
            $("#layer").css("display", "block");
            $(".output").css("display", "none");
            /*点击不同按钮显示对应弹出层*/
            switch (addID) {
                case "Retailers":
                    $(".retailer").css("display", "block");
                    break;
                case "Promoters":
                    $(".promoter").css("display", "block");
                    break;
                case "addGift":
                    $(".gift").css("display", "block");
                    break;
                case "addProduct":
                    $(".product").css("display", "block");
                    break;
                case "configIntegral":
                    $(".integral").css("display", "block");
                    break;
                case "configGrade":
                    $(".grade").css("display", "block");
                    break;
            }

        });

        var exp = /^[1-9][0-9]*$/;
        /*添加礼品*/
        $("#Gift_Submit").click(function () {
            var Gift_ID = $("#selectGift option:selected").val();
            var Gift_Name = $("#selectGift option:selected").text();
            var Gift_Number = $.trim($("#GiftNumber").val());
            if (Gift_ID < 1) { alert("请选择礼品"); return false; }
            if (App.Com.isnullorempty(Gift_Number)) { alert("请填写兑换积分！"); return false; }
            if (!exp.test(Gift_Number)){alert("输入不合法！");return false;}
            var Json = '{"id":"' + Gift_ID + '","name":" ' + Gift_Name + ' ","Number":" ' + Gift_Number + ' "}';

            var session = hashMap.get("Gift");

            if (!App.Com.isnullorempty(session)) {
                var array = jQuery.parseJSON(session);
                for (var i = 0; i < array.length; i++) { if ($.trim(array[i]["id"]) == Gift_ID) { alert("该礼品已经添加过！请重新选择"); return false; } }

                session = session.substring(0, session.length - 1);
                session = session.substring(1, session.Length);
                Json = session + ',' + Json;

            }

            Json = '[' + Json + ']';
            hashMap.set("Gift", Json);
            var newGift = "<tr>" +
                  "<td>" + Gift_Name + "</td>" +
                  "<td>" + Gift_Number + "</td>" +
                  "<td><a class='delete' data-giftid='" + Gift_ID + "'>删除</a></td>" +
                  "</tr>";
            
            /*调用ajax添加礼品记录*/
            $.ajax({
            	type:"post",
            	url:"addExchangeShopGoods",
            	data:"projectId="+projectId+"&goodsId="+Gift_ID+"&points="+Gift_Number,
            	success:function(msg)
            	{
            		alert(msg);
            	},
            	error:function()
            	{
            		alert("添加失败");
            	}
            });
            
            $("#Gift .add").before(newGift);
            
            var div_height = $(".nav-right").height();
            $('#iframeContent', window.parent.document).height(div_height + 90);
            
            page.$close();
            
            $("#GiftNumber").val("");

        });

        /*删除礼品*/
        $("#Gift").on("click", ".delete", function () {

            var Gift_ID = $(this).data("giftid");

            var array = jQuery.parseJSON(hashMap.get("Gift"));
            var result = [];
            for (var i = 0; i < array.length; i++) {
                if ($.trim(array[i]["id"]) != Gift_ID)
                    result.push(array[i]);
            }

            /*调用ajax删除礼品记录*/
            $.ajax({
            	type:"post",
            	url:"deleteExchangeShopGoods",
            	data:"projectId="+projectId+"&goodsId="+Gift_ID,
            	success:function(msg)
            	{
            		alert(msg);
            	},
            	error:function()
            	{
            		alert("添加失败");
            	}
            });
            
            hashMap.set("Gift", JSON.stringify(result) == "[]" ? "" : JSON.stringify(result));

            $(this).parent().parent().remove();
            
            var div_height = $(".nav-right").height();
            $('#iframeContent', window.parent.document).height(div_height + 90);
        });

        /*添加产品*/
        $("#Product_Submit").click(function () {
            var Type_ID = $.trim($("#selectType option:selected").val());
            var Type_Name = $.trim($("#selectType option:selected").text());
            var Family_ID = $.trim($("#selectFamily option:selected").val());
            var Family_Name = $.trim($("#selectFamily option:selected").text());
            var Product_ID = $.trim($("#selectProduct option:selected").val());
            var Product_Name = $.trim($("#selectProduct option:selected").text());
            var Sales = $.trim($("#Sales").val());
            var Ids = [];
            var Names = [];
            var Json = "";
            var newGift = "";
            $("#selectProduct option").each(function()
            {
            	if($(this).val() != "all" && $(this).val() != "0")
            	{
            		Ids.push($.trim($(this).val()));
            		Names.push($.trim($(this).text()));
            	}
            });

            if (Type_ID < 1) { alert("请选择产品类别"); return false; }
            if (Family_ID < 1) { alert("请选择产品家族"); return false; }
            if (Product_ID < 1) { alert("请选择产品"); return false; }
            if (App.Com.isnullorempty(Sales)) { alert("请填写1积分对应销售额！"); return false; }
            if (!exp.test(Sales)){alert("输入不合法！");return false;}
            
            var session = hashMap.get("Product");
            if("all" == Product_ID)
            {
            	if (!App.Com.isnullorempty(session)) {
                    var array = jQuery.parseJSON(session);
                    for (var i = 0; i < array.length; i++) 
                    {
                    	for(var j in Ids)
                    	{
                    		if($.trim(array[i]["Product_ID"]) == Ids[j])
                    		{
                    			Ids.splice(j,1);
                    			Names.splice(j,1);
                    		}
                    	}
                    }
                    for(var i in Ids)
                    {
                    	Json += '{"Type_ID":"' + Type_ID + '","Family_ID":" ' + Family_ID + '","Product_ID":"' + Ids[i] + '","Product_Name":"' + Names[i] + '","Sales":"' + Sales + '"},';
                    }
                    Json = Json.substring(0, Json.length - 1);
                    session = session.substring(0, session.length - 1);
                    session = session.substring(1, session.Length);
                    Json = session + ',' + Json;
                }
            	else 
            	{
            		for(var i in Ids)
                    {
                    	Json += '{"Type_ID":"' + Type_ID + '","Family_ID":" ' + Family_ID + '","Product_ID":"' + Ids[i] + '","Product_Name":"' + Names[i] + '","Sales":"' + Sales + '"},';
                    }
                    Json = Json.substring(0, Json.length - 1);
				}
            	for(var i in Ids)
                {
                	newGift += "<tr>" +
                    "<td>" + Type_Name + "</td>" +
                    "<td>" + Family_Name + "</td>" +
                    "<td>" + Names[i] + "</td>" +
                    "<td>" + Sales + "</td>" +
                    "<td><a class='delete' data-productid='" + Ids[i] + "'>删除</a></td>" +
                    "</tr>";
                }
            }
            else
            {
            	Json = '{"Type_ID":"' + Type_ID + '","Family_ID":" ' + Family_ID + '","Product_ID":"' + Product_ID + '","Product_Name":"' + Product_Name + '","Sales":"' + Sales + '"}';
            	if (!App.Com.isnullorempty(session)) {
                    var array = jQuery.parseJSON(session);
                    for (var i = 0; i < array.length; i++) { if ($.trim(array[i]["Product_ID"]) == Product_ID) { alert("该产品已经添加过！请重新选择"); return false; } }

                    session = session.substring(0, session.length - 1);
                    session = session.substring(1, session.Length);
                    Json = session + ',' + Json;
                }
            	 newGift = "<tr>" +
                 "<td>" + Type_Name + "</td>" +
                 "<td>" + Family_Name + "</td>" +
                 "<td>" + Product_Name + "</td>" +
                 "<td>" + Sales + "</td>" +
                 "<td><a class='delete' data-productid='" + Product_ID + "'>删除</a></td>" +
                 "</tr>";
            }
            Json = '[' + Json + ']';
            hashMap.set("Product", Json);
            
            //alert("Ids="+Ids+"\n"+"\n"+"Names="+Names+"\n"+"newGift="+newGift);
            //调用ajax添加产品积分记录
            $.ajax({
            	type:"post",
            	url:"addProductPoints",
            	data:"projectId="+projectId+"&productId="+Product_ID+"&salesAmount="+Sales+"&addArray="+Ids,
            	success:function(msg)
            	{
            		alert(msg);
            	},
            	error:function()
            	{
            		alert("添加失败");
            	}
            });
            
            $("#Product .add").before(newGift);
            
            var div_height = $(".nav-right").height();
            $('#iframeContent', window.parent.document).height(div_height + 90);
            
            page.$close();
        });

        /*删除产品*/
        $("#Product").on("click", ".delete", function () {

            var Product_ID = $(this).data("productid");

            var array = jQuery.parseJSON(hashMap.get("Product"));
            var result = [];
            for (var i = 0; i < array.length; i++) {
                if ($.trim(array[i]["Product_ID"]) != Product_ID)
                    result.push(array[i]);
            }

            hashMap.set("Product", JSON.stringify(result) == "[]" ? "" : JSON.stringify(result));

            /*调用ajax删除产品记录*/
            $.ajax({
            	type:"post",
            	url:"deletePorductPoints",
            	data:"projectId="+projectId+"&productId="+Product_ID,
            	success:function(msg)
            	{
            		alert(msg);
            	},
            	error:function()
            	{
            		alert("添加失败");
            	}
            });
            
            $(this).parent().parent().remove();
            
            var div_height = $(".nav-right").height();
            $('#iframeContent', window.parent.document).height(div_height + 90);
        });

        /*添加条目*/
        $("#Grade_Submit").click(function () {

            var rank = $.trim($("#rank").val());
            var gradeLine = $.trim($("#gradeLine").val());

            if (App.Com.isnullorempty(rank)) { alert("请填写等级！"); return false; }
            if (App.Com.isnullorempty(gradeLine)) { alert("请填写积分标线！"); return false; }
            if (!exp.test(gradeLine)){alert("输入不合法！");return false;}
            var Json = '{"rank":"' + rank + '","gradeLine":" ' + gradeLine + '"}';

            var session = hashMap.get("Grade");

            if (!App.Com.isnullorempty(session)) {
                var array = jQuery.parseJSON(session);
                for (var i = 0; i < array.length; i++) { if ($.trim(array[i]["rank"]) == rank) { alert("该等级已经添加过！请重新添加"); return false; } }

                session = session.substring(0, session.length - 1);
                session = session.substring(1, session.Length);
                Json = session + ',' + Json;

            }

            Json = '[' + Json + ']';
            hashMap.set("Grade", Json);
            var newGift = "<tr>" +
                  "<td>" + rank + "</td>" +
                  "<td>" + gradeLine + "</td>" +
                  "<td><a class='delete' data-rank='" + rank + "'>删除</a></td>" +
                  "</tr>";
            
            /*调用ajax添加积分等级记录*/
            $.ajax({
            	type:"post",
            	url:"addPointsLevel",
            	data:"projectId="+projectId+"&name="+rank+"&points="+gradeLine,
            	success:function(msg)
            	{
            		alert(msg);
            	},
            	error:function()
            	{
            		alert("添加失败");
            	}
            });
            
            $("#Grade .add").before(newGift);
            
            var div_height = $(".nav-right").height();
            $('#iframeContent', window.parent.document).height(div_height + 90);
            
            page.$close();
        });

        /*删除条目*/
        $("#Grade").on("click", ".delete", function () {

            var rank = $(this).data("rank");
            var array = jQuery.parseJSON(hashMap.get("Grade"));
            var result = [];
            for (var i = 0; i < array.length; i++) {
                if ($.trim(array[i]["rank"]) != rank)
                    result.push(array[i]);
            }

            hashMap.set("Grade", JSON.stringify(result) == "[]" ? "" : JSON.stringify(result));

            /*调用ajax删除积分等级记录*/
            $.ajax({
            	type:"post",
            	url:"deletePointsLevel",
            	data:"projectId="+projectId+"&name="+rank,
            	success:function(msg)
            	{
            		alert(msg);
            	},
            	error:function()
            	{
            		alert("添加失败");
            	}
            });
            
            $(this).parent().parent().remove();
            
            var div_height = $(".nav-right").height();
            $('#iframeContent', window.parent.document).height(div_height + 90);
        });

        /*关闭*/
        $(".close").click(function () { page.$close(); });

    },

    /*关闭*/
    $close: function () {
        $("#layer").css("display", "none");
    },

    /*显示层*/
    $Show: function (Actived) {
        $('#' + Actived).show();

        $(".sub-nav li span").addClass('li-Nspan');
        $(".sub-nav li a").addClass('a-Ncolor');
        $('.' + Actived).children('span').removeClass();
        $('.' + Actived).children('a').removeClass();
        $('.' + Actived).children('span').addClass('li-Yspan');
        $('.' + Actived).children('a').addClass('a-Ycolor');

    },

    /*隐藏层*/
    $hide: function () {
        $(".actived").removeAttr("style");
        $(".actived").css("display", "none");
    },

}