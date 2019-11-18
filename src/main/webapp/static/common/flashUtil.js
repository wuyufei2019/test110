//加载启动flash插件
var userAgent = navigator.userAgent; // 取得浏览器的userAgent字符串
if (window.ActiveXObject) {
	var s = new ActiveXObject('ShockwaveFlash.ShockwaveFlash');
	if (!s) {
		if (userAgent.indexOf("Chrome") > -1) {//谷歌浏览器判断
			window.top.location = 'http://www.adobe.com/go/getflashplayer';
		}
		if (userAgent.indexOf("Firefox") > -1) {//狐火浏览器判断
			window.top.open('http://www.adobe.com/go/getflashplayer');
		}
	}
} else {
	var s = navigator.plugins['Shockwave Flash'];
	if (!s) {
		if (userAgent.indexOf("Chrome") > -1) {//谷歌浏览器判断
			window.top.location = 'http://www.adobe.com/go/getflashplayer';
		}
		if (userAgent.indexOf("Firefox") > -1) {//狐火浏览器判断
			window.top.open('http://www.adobe.com/go/getflashplayer');//跳到下载页面
		}
	}
}