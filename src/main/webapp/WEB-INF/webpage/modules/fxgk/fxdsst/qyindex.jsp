<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>企业风险四色图管理</title>
	<meta name="decorator" content="default"/>
</head>
<body>
	<!-- 企业风险四色图 -->
	<div id="sst_div">
		<span class="btn btn-warning btn-sm" id="lastImg" style="height: 30px;width: 60px;position: absolute;top: 20px;right: 115px;" onclick="showLastImg(this)">上一张</span>
		<span class="btn btn-info btn-sm" id="nextImg" style="height: 30px;width: 60px;position: absolute;top: 20px;right: 50px;" onclick="showNextImg(this)">下一张</span>
		<img id="sst_img" style="width: 100%;"/>
	</div>

	<!-- 提示信息 -->
	<div id="sst_msg" style="text-align: center;display: none">
		<span style="font-size: 25px;color: red;"><strong>请在企业信息中上传企业四色图</strong></span>
	</div>

<script>
	var fxssts;

	$(function () {
		var fxsst = '${qyfxsst}';
		if (fxsst) {// 如果企业风险四色图存在
			fxssts = fxsst.split(',');
			var firstImgUrl = fxssts[0].split('||')[0];
			$('#sst_img').prop('src', firstImgUrl);// 显示第一张图片
			$('#sst_img').prop('name', 0);// 把img标签的name属性值设置为第一张图片在fxssts中的下标
			$('#lastImg').hide();// 隐藏上一张按钮
		} else {// 如果不存在，则隐藏按钮，显示提示信息
			$('#sst_div').hide();
			$('#sst_msg').show();
		}
	})

	// 显示上一张图片
	function showLastImg(obj) {
		var curIndex = parseInt($(obj).next().next().prop('name'));// 获取当前图片在fxssts中的下标
		var lastIndex = parseInt(curIndex - 1);// 获取上一张图片在fxssts中的下标
		if (fxssts[lastIndex] != undefined && fxssts[lastIndex] != null){// 如果上一张图片存在
			var lastImgUrl = fxssts[lastIndex].split('||')[0];
			$('#sst_img').prop('src', lastImgUrl);// 把img标签的src属性设置为上一张图片url
			$('#sst_img').prop('name', lastIndex);// 把img标签的name属性设置为上一张图片的下标
			if (lastIndex == 0) {// 如果显示的是第一张图片，则隐藏'上一张'按钮
				$('#lastImg').hide();
			}
			$('#nextImg').show();
		}
	}

	// 显示下一张图片
	function showNextImg(obj) {
		var curIndex = parseInt($(obj).next().prop('name'));// 获取当前图片在fxssts中的下标
		var nextIndex = parseInt(curIndex + 1);// 获取下一张图片在fxssts中的下标
		if (fxssts[nextIndex] != undefined && fxssts[nextIndex] != null){// 如果下一张图片存在
			var nextImgUrl = fxssts[nextIndex].split('||')[0];
			$('#sst_img').prop('src', nextImgUrl);// 把img标签的src属性设置为下一张图片url
			$('#sst_img').prop('name', nextIndex);// 把img标签的name属性设置为下一张图片的下标
			if ((nextIndex + 1) == fxssts.length) {// 如果显示的是最后一张图片，则隐藏'下一张'按钮
				$('#nextImg').hide();
			}
			$('#lastImg').show();// 显示'上一张'按钮
		}
	}

</script>
</body>
</html>