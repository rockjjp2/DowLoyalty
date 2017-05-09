//判断填写的注册信息是否满足要求
var code_validate = false;
var mobile_validate = false;
var area_validate = false;

//用全局变量存储验证码code
var validate_code;

//正则表达式
//手机号格式
var mobile_exp = /^1[0-9]{10}$/;
//土地面积格式
var area_exp = /^((0?)|([1-9][0-9]*)*)$/;

// setInterval("validate_code = '';",1000*5);
function get_code()
{
	$.ajax
	(
		{
			type:"post",
			url:"Farmer_validateCode",
			success:function(code)
			{
				 validate_code = code.trim();
			},
			error:function()
			{
				alert("网络错误，请刷新页面!");
			}
		}	
	)
}

$(
    function()
    {
        
    	$("document").ready
        (
            function()
            {
                //加载页面时加载验证码图片
                $("#code_img").attr("src","Farmer_validateCodeImg");
            }
        );
    	
    	//点击验证码图片重新获取验证码
        $("#code_img").click
        (
            function()
            {
                var date = new Date().getTime();
                $("#code_img").attr("src","Farmer_validateCodeImg?date=" + date);
            }
        )
        
        /*加载完验证码图片立即获取验证码*/
        $("#code_img").load
        (
        		function()
        		{
        			get_code();
        		}
        );

        //定时重置验证码

        //ajax查询注册用户名是否重复
        $(".name").change
        (
            function()
            {
                var _name = $(".name").val();
                $.ajax
                ({
                    type:"post",
                    url:"Farmer_validateNameUnique",
                    data:"name=" + _name,
                    dataType:"text",
                    success:function (data)
                    {
                        //data为布尔值，用于判断是否重复
                        if(data != 1)
                        {
                            alert("您的姓名已被注册，请保证手机号不重复！");
                        }
                    },
                    error:function ()
                    {
                        alert("访问服务器出错，请刷新页面!");
                    }
                });
            }
        );

        //验证手机号是否重复
        $(".mobile").change
        (
            function()
            {
                var _mobile = $(".mobile").val();
                //使用正则验证手机号格式
                if(!mobile_exp.test(_mobile))
                {
                    mobile_validate = false;
                    alert("手机号格式不合法！请输入11位手机号！");
                }
                else
                {
                    //调用ajax验证手机号是否重复
                    $.ajax
                    ({
                        type:"post",
                        url:"Farmer_validateMobileUnique",
                        data:"mobile=" + _mobile,
                        dataType:"text",
                        success:function(data)
                        {
                            if(data == 1)
                            {
                                mobile_validate = true;
                            }
                            else
                            {
                                mobile_validate = false;
                                alert("手机号已被注册，请选用别的手机号进行注册！");
                            }
                        },
                        error:function ()
                        {
                            alert("访问服务器出错，请刷新页面!");
                        }
                    });
                }
            }
        );

        //判断验证码是否正确
        $(".code").change
        (
            function()
            {
                var _code = $(".code").val();
                if(validate_code.toLowerCase() == _code || validate_code == _code)
                {
                	code_validate = true;
                }
                else
                {
                	code_validate = false;
                    alert("验证码错误!");
                }
            }
        );

        //验证土地面积格式
        $(".area").change
        (
            function()
            {
                var _area = $(".area").val();
                if(!area_exp.test(_area))
                {
                    area_validate = false;
                    alert("土地面积输入格式不正确！不能有非数字字符！");
                }
                else
                {
                    area_validate = true;
                }
            }
        );

        //点击注册按钮提交表单
        $("#register").click
        (
            function()
            {

                if(!mobile_validate)
                {
                    alert("手机号格式不合法或已被注册！");
                    return false;
                }
                else if(!code_validate)
                {
                    alert("验证码错误！");
                    return false;
                }
                else if(!area_validate)
                {
                    alert("土地面积输入格式不正确！不能有非数字字符！");
                    return false;
                }
                else
                {
                    $(".myForm").submit();
                }
            }
        )
    }
);