$(function() {
	var name=BrowserDetect.browser;// 浏览器的名称，例如Firefox，IE
	var version=BrowserDetect.version;//浏览器的版本，比如，7、11
	var ostype=BrowserDetect.OS;//浏览器所宿主的操作系统，比如Windows、Linux
	if(name=="Firefox" || name=="Chrome"){
	}else{
//		alert("温馨提醒为了更好体验使用效果，请使用 Firefox浏览器 或 谷歌浏览器 或 360浏览器极速模式！");
//		$("#username").attr({"readonly":"readonly"});
//		$("#password").attr({"readonly":"readonly"});
//		$("#captcha").attr({"readonly":"readonly"});
//		$("#changeImg").attr({"disabled":"disabled"});
//		$("#loginbtn").attr({"disabled":"disabled"});
	}
	
	//验证码
	$("#validateCode").click(function() {
		$("#validateCode").attr("src",ctx+'/static/images/kaptcha.jpg?data='+Math.random());
	});
	
	$("#loginbtn").click(function() {
		var username = $("input[name=username]").val();
		var password = $("input[name=password]").val();
		var captcha = $("input[name=captcha]").val();
		//用户名
		if(username.length==0){
			$(".login_main_errortip").html("请输入用户名");
			return false;
		}
		//密码
		else if(password.length==0){
			$(".login_main_errortip").html("请输入密码！");
			return false;
		}
		//验证码
		else if(captcha=""||captcha.length==0){
			$(".login_main_errortip").html("请输入验证码！");
			return false;
		}
		
		else{$("#loginlForm").submit();}
	});
});

var BrowserDetect = {
    init: function () {
        this.browser = this.searchString(this.dataBrowser) || "An unknown browser";
        this.version = this.searchVersion(navigator.userAgent)
            || this.searchVersion(navigator.appVersion)
            || "an unknown version";
        this.OS = this.searchString(this.dataOS) || "an unknown OS";
    },
    searchString: function (data) {
        for (var i=0;i<data.length;i++)    {
            var dataString = data[i].string;
            var dataProp = data[i].prop;
            this.versionSearchString = data[i].versionSearch || data[i].identity;
            if (dataString) {
                if (dataString.indexOf(data[i].subString) != -1)
                    return data[i].identity;
            }
            else if (dataProp)
                return data[i].identity;
        }
    },
    searchVersion: function (dataString) {
        var index = dataString.indexOf(this.versionSearchString);
        if (index == -1) return;
        return parseFloat(dataString.substring(index+this.versionSearchString.length+1));
    },
    dataBrowser: [
        {
            string: navigator.userAgent,
            subString: "Chrome",
            identity: "Chrome"
        },
        {     string: navigator.userAgent,
            subString: "OmniWeb",
            versionSearch: "OmniWeb/",
            identity: "OmniWeb"
        },
        {
            string: navigator.vendor,
            subString: "Apple",
            identity: "Safari",
            versionSearch: "Version"
        },
        {
            prop: window.opera,
            identity: "Opera"
        },
        {
            string: navigator.vendor,
            subString: "iCab",
            identity: "iCab"
        },
        {
            string: navigator.vendor,
            subString: "KDE",
            identity: "Konqueror"
        },
        {
            string: navigator.userAgent,
            subString: "Firefox",
            identity: "Firefox"
        },
        {
            string: navigator.vendor,
            subString: "Camino",
            identity: "Camino"
        },
        {        // for newer Netscapes (6+)
            string: navigator.userAgent,
            subString: "Netscape",
            identity: "Netscape"
        },
        {
            string: navigator.userAgent,
            subString: "MSIE",
            identity: "IE",
            versionSearch: "MSIE"
        },
        {
            string: navigator.userAgent,
            subString: "Gecko",
            identity: "Mozilla",
            versionSearch: "rv"
        },
        {         // for older Netscapes (4-)
            string: navigator.userAgent,
            subString: "Mozilla",
            identity: "Netscape",
            versionSearch: "Mozilla"
        }
    ],
    dataOS : [
        {
            string: navigator.platform,
            subString: "Win",
            identity: "Windows"
        },
        {
            string: navigator.platform,
            subString: "Mac",
            identity: "Mac"
        },
        {
               string: navigator.userAgent,
               subString: "iPhone",
               identity: "iPhone/iPod"
        },
        {
            string: navigator.platform,
            subString: "Linux",
            identity: "Linux"
        }
    ]

};
BrowserDetect.init();