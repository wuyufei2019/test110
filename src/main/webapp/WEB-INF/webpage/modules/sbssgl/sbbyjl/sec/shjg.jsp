<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
<title>选择审核结果</title>
<meta name="decorator" content="default" />
</head>
<body>
	<form id="inputForm" action="${ctx}/sbssgl/sbbytasksec/shjg" method="post" class="form-horizontal">
		<div style="text-align: center;margin-top: 10px;">
			<a class="btn btn-sm" id="pass" data-toggle="tooltip" data-placement="left" onclick="pass()" title="完成" style="background-color: #afa8a8;color: white;"><i class="fa fa-check"></i> 完成</a>
			<a class="btn btn-sm" id="noPass" data-toggle="tooltip" data-placement="left" onclick="noPass()" title="未完成" style="margin-left: 20px;background-color: #afa8a8;color: white;"><i class="fa fa-times"></i> 未完成</a>
		</div>
		<input type="hidden" name="id" value="${id}" />
		<input type="hidden" id="shjg" name="shjg" />
		<div id="tgMsg" style="color: red; text-align: center;margin-top: 10px;">注：选择通过后会自动添加电子签章，请仔细核对信息</div>
	</form>

<script type="text/javascript">
var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
var validateForm;
$(function(){
	$("#tgMsg").hide();
})

//完成
function pass(){
	$("#tgMsg").show();//显示div   
	$("#shjg").val("1"); //设置 id 为 shjg 的隐藏值为 1
	$("#pass").css("backgroundColor", "").css("color", "").addClass("btn-info");
	$("#noPass").css("backgroundColor", "#afa8a8").css("color", "white").removeClass("btn-danger");
}

//未完成
function noPass(){
	$("#tgMsg").hide();//隐藏div      
	$("#shjg").val("0");//设置 id 为 shjg 的隐藏值为 0
	$("#noPass").css("backgroundColor", "").css("color", "").addClass("btn-danger");
	$("#pass").css("backgroundColor", "#afa8a8").css("color", "white").removeClass("btn-info");
}

function doSubmit(){
	$("#inputForm").submit(); 	
}

$(function(){
	var flag=true;
	$('#inputForm').form({    
	    onSubmit: function(){    
	    	var isValid = $(this).form('validate');
	    	if(isValid&&flag){
	    		flag=false;
	    		$.jBox.tip("正在提交，请稍等...",'loading',{opacity:0});
	    		return true;
	    	}
			return false;	// 返回false终止表单提交
	    },    
	    success:function(data){ 
	    	$.jBox.closeTip();
	    	if(data=='success'){
	    		parent.layer.open({icon:1,title: '提示',offset: 'rb',content: '操作成功！',shade: 0 ,time: 2000 });
	    	}else{
	    		parent.layer.open({icon:2,title: '提示',offset: 'rb',content: '操作失败！',shade: 0 ,time: 2000 });
			}
	    	parent.dg.datagrid('reload');
	    	parent.layer.close(index);//关闭对话框。
	    }    
	});
});
</script>
</body>
</html>