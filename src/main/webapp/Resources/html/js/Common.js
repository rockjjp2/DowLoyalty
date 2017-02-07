/******************************************************** 
************公用JS方法
********************************************************/

var App = App || {};

App.WxShare = function (uid, fx_uid, gid, goods_name, goods_img, share_location) {
    //提交数据
    alert(uid, fx_uid, gid, goods_name, goods_img, share_location);
    var subData = {
        "action": "SaveMallShareGoodsData",
        "iswx": App.Com.isWeiXin,
        "uid": uid,
        "gid": gid,
        //分享名
        "goods_name": goods_name,
        //商品图片
        "goods_img": goods_img,
        "share_location": share_location
    }
    $.ajax({
        url: App.Com.getNoCacheUrl(App.wapajax),
        type: 'post',
        data: subData,
        dataType: 'json',
        success: function (data) {
        }
    });
}


App.ChkMemberData = function (strurl, mpwid, acid, wid, openid, ruid, fxuid, gid) {
    //提交数据
    var subData = {
        "action": "ChkMemberData",
        "iswx": App.Com.isWeiXinInt(),
        "mpwid": mpwid,
        "acid": acid,
        //粉丝ID
        "wid": wid,
        "ruid": ruid,
        //Wx OpenID
        "user_openid": openid,
        "fxuid": fxuid,
        "gid": gid
    }
    $.ajax({
        url: App.Com.getNoCacheUrl(App.wapajax),
        type: 'post',
        data: subData,
        dataType: 'json',
        success: function (data) {
            //结束效果
            App.Com.endloading();
            var m = typeof (data) == "object" ? data : $.parseJSON(data);
            if (!m) { alert("请求返回异常"); return; }
            if (m.Statu != 0) {
                if (m.Statu == 2) {
                    //alertMsg(m.Msg, 1, function () {
                    location.href = strurl + "&user_openid=" + openid + "&gid=" + gid;
                    //});
                }
                ////错误提示
                //alert(m.Msg);
                return;
            }
        },
        error: function (status) {
            App.Com.erroloading(status);
        }
    });

}
/*秉钧红人分享*/
App.RedShare = function (strUrl,acid) {
    var index = layer.open({
        type: 1, //page层
        area: ['480px', ''],
        title: false,
        shade: 0.6, //遮罩透明度
        //moveType: 1, //拖拽风格，0是默认，1是传统拖动
        shadeClose: true,
        content: $("#login-layer").html()
    });

    $("#saveBtn").on("click", function () {
        if (App.Com.isnullorempty(login.phone.value)) {
            layer.tips("账号不能为空", "#phone");
            return false;
        }
        if (App.Com.isnullorempty(login.pwd.value)) {
            layer.tips("密码不能为空", "#pwd");
            return false;
        }
        //ajax请求
        $.ajax({
            type: "post",
            url: strUrl,
            data: {
                "action": "BindMember", "type": 0, "acid": acid,
                "phone": login.phone.value, "pwd": login.pwd.value
            },
            dataType: "json",
            cache: false,
            async: false,
            beforeSend: function () {

            },
            success: function (data) {
                if (data.Statu == 0) {
                    login.sign.value = data.JsonData;
                    $("#login").submit()
                    layer.close(index);
                }
            }
        });
    });
    $("#saveBtn2").on("click", function () {
        if (App.Com.isnullorempty(Register.phone.value)) {
            layer.tips("手机号码不能为空", "#phone2");
            return false;
        }
        if (!App.Com.ishone(Register.phone.value)) {
            layer.tips("格式不正确", "#phone2");
            return false;
        }

        if (App.Com.isnullorempty(Register.registercode.value)) {
            layer.tips("注册验证码不能为空", "#registercode");
            return false;
        }
        if (App.cookie.get("MIMEILI_CHECKCODE").toLowerCase() != Register.registercode.value.toLowerCase()) {
            layer.tips("注册验证码不正确", "#registercode");
            return false;
        }
        if (App.Com.isnullorempty(Register.phoneCode.value)) {
            layer.tips("手机验证码不正确", "#phoneCode");
            return false;
        }
        if (App.Com.isnullorempty(Register.pwd.value)) {
            layer.tips("密码不能为空", "#pwd2");
            return false;
        }
        //ajax请求

        $.ajax({
            type: "post",
            url: strUrl,
            data: {
                "action": "BindMember", "type": 1, "acid": acid,
                "phone": Register.phone.value, "pwd": Register.pwd.value, code: Register.phoneCode.value
            },
            dataType: "json",
            cache: false,
            async: false,
            beforeSend: function () {

            },
            success: function (data) {
                if (data.Statu == 0) {
                    Register.sign.value = data.JsonData;
                    $("#Register").submit()
                    layer.close(index);
                } else if (data.Statu == 2) {
                    layer.tips("手机验证码不正确", "#phoneCode");
                }
            }
        });
    });
    //获取验证码
    $("#txtcode").click(function () {
        if (App.Com.isnullorempty(Register.phone.value)) {
            layer.tips("手机号码不能为空", "#phone2");
            return false;
        }
        if (!App.Com.ishone(Register.phone.value)) {
            layer.tips("格式不正确", "#phone2");
            return false;
        }
        var $this = this;
        $.ajax({
            url: strUrl,
            type: 'POST',
            data: { "action": "AppUserCode", acid: g_sys.app_case_id, "phone": Register.phone.value, "shopid": 0 },
            dataType: 'json',
            async: false, //设为false就是同步请求
            success: function (json) {
                if (json.Statu == 0) {
                    App.Com.alertMsg(json.Msg);
                    return false;
                }
                App.Com.Countdown(59, $this);
            }
        });
    });
    $("#NewUser").on("click", function () {
        $("#login").hide();
        $("#Register").show();
    });
    $("#gologin").on("click", function () {
        $("#login").show();
        $("#Register").hide();
    });
}

App.Com = {
    isWeiXin: function () {
        var ua = window.navigator.userAgent.toLowerCase();
        if (ua.match(/MicroMessenger/i) == 'micromessenger') {
            return true;
        } else {
            return false;
        }
    },
    isWeiXinInt: function () {
        var ua = window.navigator.userAgent.toLowerCase();
        if (ua.match(/MicroMessenger/i) == 'micromessenger') {
            return 1;
        } else {
            return 0;
        }
    },
    /*60秒倒计时*/
    Countdown: function (time, obj) {
        var time;
        if (App.Com.isnullorempty(time)) {
            time = 59;
        }
        if (time == 0) {
            $(obj).removeAttr("disabled");
            $(obj).val("获取验证码");
            time = 60;
        } else {
            $(obj).attr("disabled", true);
            $(obj).val("重新发送(" + time + ")");
            time--;
            setTimeout(function () {
                App.Com.Countdown(time, $(obj))
            }, 950);
        }
    },
    /*加载效果*/
    statrloading: function () {
        $(".showbox").fadeIn();
    },
    /*结束效果*/
    endloading: function () {
        $(".showbox").fadeOut();
    },
    /*错误提示*/
    erroloading: function (state) {
        //alert("服务器错误,错误值:" + state.status);
        $(".showbox").fadeOut();
    },
    /* 是否为空 */
    isnullorempty: function (str) {
        if (str == null) return true;
        if (str.length == 0) return true;
        if (/^\s*$/i.test(str)) return true;
        if (str == undefined) return true;
        return false;
    },
    /*周灿 添加于20160414*/
    /* 是否为空或undefined */
    isnulloremptyorundefined: function (str) {
        if (str == null) return true;
        if (str.length == 0) return true;
        if (/^\s*$/i.test(str)) return true;
        if (str == undefined) return true;
        return false;
    },
    /* 是否是带两位小数的数字 */
    isdemice: function (str) {
        return /^[0-9]*[\.][0-9]{0,2}$/.test(str);
    },
    /* 是否是数字 */
    isnumber: function (str) {
        return /^[0-9]*$/.test(str);
    },
    /*判断手机号码格式*/
    ishone: function (str) {
        return str.match(/^[1][3-8]\d{9}$|^([6|9])\d{7}$|^[0][9]\d{8}$|^[0][0][8][8][6][9]\d{8}$|^[6]([8|6])\d{5}$/);
        //return str.match(/^0?(13[0-9]|15[0-9]|18[0-9]|14[57]|17[067])[0-9]{8}$/);
    },
    /*验证身份证号码*/
    iscard: function (str) {
        return str.match(/^[1-9]\d{7}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}|[1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}([0-9]|X|x)$/);
    },
    /*判断不能为数字开头*/
    istestd: function (str) {
        if (/^\d.*$/.test(str)) {
            return true;
        }
        return false;
    },
    /*判断用户名只能为_,英文字母,数字*/
    istestw: function (str) {
        if (/^\w+$/.test(str)) {
            return true;
        }
        return false;
    },
    /*判断用户名只能英文字母开头*/
    istesta_z: function (str) {
        if (!/^([a-z]|[A-Z])[0-9a-zA-Z_]+$/.test(str)) {
            return true;
        }
        return false;
    },
    /*验证邮箱格式 返回true和false*/
    isemail: function (str) {
        return str.match(/^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/);
    },
    /*验证银行卡号是否标准*/
    isbanck: function (str) {
        var strBin = "10,18,30,35,37,40,41,42,43,44,45,46,47,48,49,50,51,52,53,54,55,56,58,60,62,65,68,69,84,87,88,94,95,98,99";
        if (strBin.indexOf(str.substring(0, 2)) == -1) {
            return true;
        }
        return false;
    },
    /*去除开始字符*/
    trimStart: function (trimStr) {
        if (!trimStr) { return this; }
        var temp = this;
        while (true) {
            if (temp.substr(0, trimStr.length) != trimStr) {
                break;
            }
            temp = temp.substr(trimStr.length);
        }
        return temp;
    },
    /*出去结束字符*/
    trimEnd: function (trimStr) {
        if (!trimStr) { return this; }
        var temp = this;
        while (true) {
            if (temp.substr(temp.length - trimStr.length, trimStr.length) != trimStr) {
                break;
            }
            temp = temp.substr(0, temp.length - trimStr.length);
        }
        return temp;
    },
    /*解码*/
    decode: function (str) {
        return decodeURIComponent(str);
    },
    ispassword: function (str) {
        return /^[0-9a-zA-Z_]{6,16}$/.test(str);
    },
    /*int转型*/
    parseInt: function (str) {
        return parseInt(str);
    },
    /*Float转型*/
    parseFloat: function (str) {
        return parseFloat(str);
    },
    // 获取地址栏参数的方法 变量名称 如:id*/
    getParameter: function (param) {
        var query = window.location.search;
        var iLen = param.length;
        var iStart = query.indexOf(param);
        if (iStart == -1)
            return "";
        iStart += iLen + 1;
        var iEnd = query.indexOf("&", iStart);
        if (iEnd == -1)
            return query.substring(iStart);
        return query.substring(iStart, iEnd);
    },
    // 获取url中param参数的值
    getUrlParameter: function (url, param) {
        //如果Url为空，则获取当前窗口Url参数
        if (!url) {
            url = location.href;
        }
        //如果没有参数刚返回空
        if (url.indexOf('?') < 0) {
            return null;
        }
        var params = url.split('?')[1];
        var query = params.split('&');
        for (var i = 0; i < query.length; i++) {
            var kv = query[i].split('=');
            if (kv[0] == param) {
                return kv[1];
            }
        }
        return null;
    },
    // 设置url中param的值
    setParameter: function (url, param, value) {
        //如果没有参数
        if (url.indexOf('?') < 0) {
            return url + "?" + param + "=" + value;
        }
        var urlbox = url.split('?');
        var fileurl = urlbox[0];
        var params = "";

        //加入&，方便正则匹配
        var query = "&" + urlbox[1];
        var p = new RegExp("(^|&" + param + ")=[^&]*");
        //如果存在传入的参数，则替换，否则添加到参数后
        alert(p.test(query));
        if (p.test(query)) {
            params = "?" + query.replace(p, "$1=" + value).substring(1);
        } else {

            if (!query) {
                params = '?' + param + '=' + value;
            } else {
                params = '?' + query.substring(1) + '&' + param + '=' + value;
            }
        }
        return fileurl + params;
    },
    // 通过对象obj来选择标签ele是否可用 */
    showchange: function (ele, obj) {
        ele.prop({ disabled: obj.prop("checked") ? false : true });
    },
    //格式化Url，添加随机数
    getNoCacheUrl: function (str) {
        if (App.Com.isnullorempty(str)) return "";
        if (str.indexOf('?') < 0)
            return str + "?rv=" + new Date().getTime();
        else
            return str + "&rv=" + new Date().getTime();
    },
    htmlEncode: function (html) {
        html = html.replace(/>/g, "&gt;");
        html = html.replace(/</g, "&lt;");
        html = html.replace(/  /g, " &nbsp;");
        html = html.replace(/  /g, " &nbsp;");
        html = html.replace(/\"/g, "&quot;");
        html = html.replace(/'/g, "&#39;");
        //html = html.replace("\n", "<br/> ");
        return html;
    },
    htmlDecode: function (text) {
        text = text.replace(/&gt;/g, ">");
        text = text.replace(/&lt;/g, "<");
        text = text.replace(/&nbsp;/g, " ");
        text = text.replace(/ &nbsp;/g, "  ");
        text = text.replace(/&quot;/g, "\"");
        text = text.replace(/&#39;/g, "'");
        //text = text.replace("<br/> ", "\n");
        return text;
    },
    getLocalTime: function (timestamp, format) {
        if (!timestamp) {
            datetime = new Date();
        } else {
            datetime = new Date(parseInt(timestamp) * 1000);
        }
        return datetime.FormatDataTime(format);
        //return datetime;
    },
    CurTime: function () {
        return Date.parse(new Date()) / 1000;
    },
    DateToUnix: function (string) {
        /**              
             * 日期 转换为 Unix时间戳
             * @param <string> 2014-01-01 20:20:20  日期格式              
             * @return <int>        unix时间戳(秒)              
             */
        var f = string.split(' ', 2);
        var d = (f[0] ? f[0] : '').split('-', 3);
        var t = (f[1] ? f[1] : '').split(':', 3);
        return (new Date(
        parseInt(d[0], 10) || null,
        (parseInt(d[1], 10) || 1) - 1,
        parseInt(d[2], 10) || null,
        parseInt(t[0], 10) || null,
        parseInt(t[1], 10) || null,
        parseInt(t[2], 10) || null
        )).getTime() / 1000;
    },
    /*数字转换*/
    number: function (num) {
        var N = [
            "零", "一", "二", "三", "四", "五", "六", "七", "八", "九"
        ];
        function convertToChinese(num) {
            var str = num.toString();
            var len = num.toString().length;
            var C_Num = [];
            for (var i = 0; i < len; i++) {
                C_Num.push(N[str.charAt(i)]);
            }
            return C_Num.join('');
        }

        var chinese = convertToChinese(num);
        return chinese;
    },
    UnixToDate: function (unixTime, isFull, timeZone) {
        /**              
             * 时间戳转换日期              
             * @param <int> unixTime    待时间戳(秒)              
             * @param <bool> isFull    返回完整时间(Y-m-d 或者 Y-m-d H:i:s)              
             * @param <int>  timeZone   时区              
             */        if (typeof (timeZone) == 'number') {
    unixTime = parseInt(unixTime) + parseInt(timeZone) * 60 * 60;
}
        //周灿修改 20151217
        var time = new Date(unixTime * 1000);
        var ymdhis = "";
        ymdhis += time.getFullYear() + "-";
        ymdhis += (time.getMonth() + 1) + "-";
        ymdhis += time.getDate();
        if (isFull === true) {
            ymdhis += " " + time.getHours() + ":";
            ymdhis += time.getMinutes() + ":";
            ymdhis += time.getSeconds();
        }
        return ymdhis;
    },
    alertMsg: function (opts) {
        var default_opts = {
            title: '提示信息：',
            content: '这里是提示内容',
            callback: function () { return false; },
            num: 1
        }
        var opts = $.extend(default_opts, opts);
        var modal = '<div id="alertModal" class="modal bs-example-modal-sm fade" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel" style="display:block">';
        modal += '<div class="modal-dialog modal-sm" style="width:300px;">';
        modal += '<div class="modal-content">';
        modal += '<div class="modal-header">';
        modal += '<button type="button" class="close alertModalClose"><span aria-hidden="true">×</span></button>';
        modal += '<h4 class="modal-title" id="mySmallModalLabel">' + opts.title + '</h4>';
        modal += '</div>';
        modal += '<div class="modal-body">';
        modal += '<p style="text-align:center;">' + opts.content + '</p>';
        modal += '</div>';
        modal += '<div class="modal-footer" style="background:#efefef">';
        if (opts.num == 1) {
            modal += '<button type="button" class="btn determine alertModalClose">确定</button>';
        } else {
            modal += '<button type="button" class="btn determine alertModalClose">确定</button>';
            modal += '<button type="button" class="btn cancel closes alertModalClose">取消</button> ';
        }
        modal += '</div></div></div></div>';
        modal += '<div id="alertModalBg" class="modal-backdrop fade"></div>';
        if ($('#alertModal').length == 0) {
            $("body").append(modal);
        }
        setTimeout(function () { $("#alertModal,#alertModalBg").addClass("in"); }, 0);
        $(".alertModalClose").on("click", function () {
            var _this = $(this);
            $("#alertModal,#alertModalBg").removeClass("in");
            setTimeout(function () {
                $("#alertModal,#alertModalBg").remove();
                if (!_this.hasClass("close") && !_this.hasClass("closes")) {
                    if (typeof (opts.callback) == 'function') {
                        opts.callback();
                    }
                }
            }, 300)
        });
    },
    GetDefaultImgUrl: function (imgurl, showimg) {
        if (!App.Com.isnullorempty(imgurl)) {
            if (imgurl.indexOf("https") < 0 && imgurl.indexOf("http") < 0) {
                imgurl = showimg + imgurl;
            }
        } else {
            imgurl = "http://image.ufxiao.com/dmall/noimg.png";
        }
        return imgurl;
    }
}
/*周灿于20160422添加*/
App.cookie = {
    set: function (name, value, expires, path, domain) {
        if (typeof expires == "undefined") {
            expires = new Date(new Date().getTime() + 3600 * 1000);
        }
        document.cookie = name + "=" + escape(value) + ((expires) ? "; expires=" + expires.toGMTString() : "") + ((path) ? "; path=" + path : "; path=/") + ((domain) ? ";domain=" + domain : "");
    },
    get: function (name) {
        var arr = document.cookie.match(new RegExp("(^| )" + name + "=([^;]*)(;|$)"));
        if (arr != null) {
            return unescape(arr[2]);
        }
        return null;
    },
    clear: function (name, path, domain) {
        if (this.get(name)) {
            document.cookie = name + "=" + ((path) ? "; path=" + path : "; path=/") + ((domain) ? "; domain=" + domain : "") + ";expires=Fri, 02-Jan-1970 00:00:00 GMT";
        }
    }
}


Date.prototype.FormatDataTime = function (format) {
    /*
     * eg:format="YYYY-MM-dd HH:mm:ss";
     */
    if (!format) { format = "yyyy-MM-dd HH:mm:ss"; }
    var o = {
        "M+": this.getMonth() + 1, // month
        "d+": this.getDate(), // day
        "H+": this.getHours(), // hour
        "m+": this.getMinutes(), // minute
        "s+": this.getSeconds(), // second
        "q+": Math.floor((this.getMonth() + 3) / 3), // quarter
        "S": this.getMilliseconds()
        // millisecond
    }
    if (/(y+)/.test(format)) {
        format = format.replace(RegExp.$1, (this.getFullYear() + "")
                .substr(4 - RegExp.$1.length));
    }
    for (var k in o) {
        if (new RegExp("(" + k + ")").test(format)) {
            format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k] : ("00" + o[k]).substr(("" + o[k]).length));
        }
    }
    return format;
}


Number.prototype.ToFixed =  String.prototype.ToFixed =function () {
    try {
        return parseFloat(this).toFixed(2);
    } catch (e) {
        return 0.00;
    }

}

Number.prototype.toFixed = String.prototype.toFixed = function(d){
  var s=this+""; 
    if(!d)d=0;     
    if(s.indexOf(".")==-1)s+="."; 
    s+=new Array(d+1).join("0");     
    if(new RegExp("^(-|\\+)?(\\d+(\\.\\d{0,"+(d+1)+"})?)\\d*$").test(s)) 
    { 
      var s="0"+RegExp.$2,pm=RegExp.$1,a=RegExp.$3.length,b=true;       
      if(a==d+2){ 
        a=s.match(/\d/g); 
        if(parseInt(a[a.length-1])>4) 
        { 
          for(var i=a.length-2;i>=0;i--){ 
            a[i]=parseInt(a[i])+1;             
            if(a[i]==10){ 
              a[i]=0; 
              b=i!=1; 
            }else break; 
          } 
        } 
        s=a.join("").replace(new RegExp("(\\d+)(\\d{"+d+"})\\d$"),"$1.$2");         
      }if(b)s=s.substr(1); 
      return (pm+s).replace(/\.$/,""); 
    }return this+"";     
}


function alertMsg(msg, btn_len, callback) {
    var html = '<div class="alert"><p></p><div class="alert-btn"><input type="button" value="确认" class="alert-btn-true"><input type="button" class="alert-btn-false" value="取消"></div></div>';
    if ($(".alert").length < 1) $("body").append(html);
    btn_len == 2 ? $(".alert-btn-false").show() : $(".alert-btn-false").hide();
    $(".alert p").html(msg);

    $(".alert").fadeIn();
    $(".alert-btn-true").unbind('click').click(function () {
        hidealert();
        if (callback) { callback(); }

    });
    $(".alert-btn-false").unbind('click').click(function () {
        hidealert();
    });
    function hidealert() {
        $(".alert").hide();
    }
}

/*
//分页控件
//示例
var tempobj = $(".nav_page").pageNav({ pageIndex: 1, pageCount: 10, loadDataFun: function (index) { alert(index); } });
取当前页：tempobj.pageIndex
*/
$.fn.pageNav = function (options) {
    var _this = this;

    var pageNavHtml = "<table class=\"nav_page\">";
    pageNavHtml += "<tbody>";
    pageNavHtml += "<tr>";
    pageNavHtml += "<td>";
    pageNavHtml += "<input readonly=\"readonly\" class=\"n_first poi btn_first\" data-value=\"1\" data-name=\"record_page_r\" type=\"text\" /></td>";
    pageNavHtml += "<td>";
    pageNavHtml += "<input readonly=\"readonly\" class=\"n_prev poi btn_prev\" data-name=\"record_page_r\" data-value=\"1\" type=\"text\" /></td>";
    pageNavHtml += "<td>第</td>";
    pageNavHtml += "<td>";
    pageNavHtml += "<span class=\"pageIndex\" >1</span></td>";
    pageNavHtml += "<td>页/<span class=\"pageCount\">0</span>页</td>";
    pageNavHtml += "<td>";
    pageNavHtml += "<input readonly=\"readonly\" class=\"n_next poi btn_next\" data-name=\"record_page_r\" data-value=\"2\" type=\"text\" /></td>";
    pageNavHtml += "<td>";
    pageNavHtml += "<input readonly=\"readonly\" class=\"n_last poi btn_last\" data-name=\"record_page_r\" data-value=\"3\" type=\"text\" /></td>";
    pageNavHtml += "</tr>";
    pageNavHtml += "</tbody>";
    pageNavHtml += "</table>";


    var defaults = {
        pageIndex: 1,           //当前页
        pageCount: 1,           //总页数
        btnFirst: $(this).find(".btn_first"),   //首页按钮对象
        btnPrev: $(this).find(".btn_prev"),   //上一页按钮对象
        btnNext: $(this).find(".btn_next"),   //下一页按钮对象
        btnLast: $(this).find(".btn_last"),   //尾页按钮对象
        //boxpageIndex: $(this).find(".pageIndex"),   //当前页容器
        //boxpageCount: $(this).find(".pageCount"),   //页总数容器
        pageNav: pageNavHtml,
        loadDataFun: function (pindex) { }//载入数据方法
    };
    var opts = jQuery.extend(defaults, options);
    this.pageIndex = opts.pageIndex;
    this.pageCount = opts.pageCount;

    //初始化分页HTML
    if (opts.pageNav) {
        $(_this).html(opts.pageNav);
    }
    var _reBindPage = function () {
        if (!opts.boxpageIndex) {
            opts.boxpageIndex = $(_this).find(".pageIndex")
        }
        opts.boxpageIndex.html(_this.pageIndex);
        if (!opts.boxpageCount) {
            opts.boxpageCount = $(_this).find(".pageCount")
        }
        opts.boxpageCount.html(_this.pageCount);
    }
    //首页
    if (opts.btnFirst) {
        opts.btnFirst.live("click", function () {
            //如果当前为第一页，则返回
            if (_this.pageIndex <= 1) { return; }
            _this.pageIndex = 1;
            _reBindPage();
            opts.loadDataFun(_this.pageIndex);
        });
    }
    //上一页
    if (opts.btnPrev) {
        opts.btnPrev.live("click", function () {
            //如果当前为第一页，则返回
            if (_this.pageIndex <= 1) { return; }
            _this.pageIndex--;
            if (_this.pageIndex <= 0) { _this.pageIndex = 1; }
            _reBindPage();
            opts.loadDataFun(_this.pageIndex);
        });
    }
    //下一页
    if (opts.btnNext) {
        opts.btnNext.live("click", function () {
            //如果当前为最后一页，则返回
            if (_this.pageIndex == _this.pageCount) { return; }
            _this.pageIndex++;
            if (_this.pageIndex > _this.pageCount) { _this.pageIndex = _this.pageCount; }
            _reBindPage();
            opts.loadDataFun(_this.pageIndex);
        });
    }
    //尾页
    if (opts.btnLast) {
        opts.btnLast.live("click", function () {
            //如果当前为最后一页，则返回
            if (_this.pageIndex == _this.pageCount) { return; }
            _this.pageIndex = _this.pageCount;
            _reBindPage();
            opts.loadDataFun(_this.pageIndex);
        });
    }
    _reBindPage();
    //绑定分页
    this.bindPageData = function (pageindex, pagecount) {
        _this.pageIndex = pageindex;
        _this.pageCount = pagecount;
        //if (pageindex == pagecount && pageindex == 1) {
        //    _this.hide();
        //}
        //else {
        //    _this.show();
        //}
        _reBindPage();
    }
    //显示分页导航
    this.show = function () {
        $(_this).show();
    }
    //隐藏分页导航
    this.hide = function () {
        $(_this).hide();
    }

    return _this;
};
