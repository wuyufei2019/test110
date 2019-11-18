<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
<title></title>
<meta name="decorator" content="default" />
</head>
<body>
	<div>
			<form id="inputForm" class="form-horizontal">
				<input type="hidden" name="type" value="1" />
				<input type="hidden" id="ewm" name="ewm" value="${fkyyewm.ewm }" />
				<div style="text-align:center;margin: 20px;">
					<img id="pic" alt="" src="">
				</div>
				<div style="text-align:center;margin: 20px;">
					<a class="btn btn-success"  onclick="scewm()" style="width:120px">随机生成二维码</a>
					<a  class="btn btn-primary"  onclick="saveewm()" style="width:120px">保存二维码</a>				
				</div>
			</form>
	</div>


<script type="text/javascript">
	var flag=true;
	//保存
	function saveewm() {
		if($("#inputForm").form('validate')&&flag){
			confirmx('确定要修改二维码信息吗?', function(index) {
				
				flag=false;
				$.jBox.tip("正在提交，请稍等...",'loading',{opacity:0});
				
				$.ajax({
					type : 'post',
					url : '${ctx}/fkcl/yyewm/bc',
					data : $("#inputForm").serialize(),
					success : function(data) {
						$.jBox.closeTip();
						layer.open({icon:1,title: '提示',offset: 'rb',content: '操作成功！',shade: 0 ,time: 2000 });
						flag=true;
					},
					error : function(data) {
						$.jBox.closeTip();
						layer.open({icon:1,title: '提示',offset: 'rb',content: '操作失败！',shade: 0 ,time: 2000 });
						flag=true;
					}
				});
			});
		}
	}
	
	if('${fkyyewm}'!=null){
		var ewm = '${fkyyewm.ewm}';
		changeimg(ewm);
	}
	 	
	function scewm(){
		var mydate = new Date();
		var uuid = "yy"+mydate.getDay()+ mydate.getHours()+ mydate.getMinutes()+mydate.getSeconds()+mydate.getMilliseconds();
		$("#ewm").val(uuid);
		changeimg(uuid);
	}
	
	function changeimg(ewm){
		$.ajax({
			type : 'get',
			url : ctx + "/fkcl/yyewm/erm?ewm=" + ewm,
			success : function(data) {
				var imgurl = ctx + data;
				$('#pic').attr('src',imgurl);
			}
		});
	}
	
</script>

</body>
</html>