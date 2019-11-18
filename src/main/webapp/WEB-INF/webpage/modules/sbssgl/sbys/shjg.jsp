<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
<title>选择审核结果</title>
<meta name="decorator" content="default" />
</head>
<body>
	<form id="inputForm" action="${ctx}/sbssgl/sbys/shjg" method="post" class="form-horizontal">
		<div style="text-align: center;margin-top: 10px;">
			<a class="btn btn-sm" id="pass" data-toggle="tooltip" data-placement="left" onclick="pass()" title="通过" style="background-color: #afa8a8;color: white;"><i class="fa fa-check"></i> 通过</a>
			<a class="btn btn-sm" id="noPass" data-toggle="tooltip" data-placement="left" onclick="noPass()" title="不通过" style="margin-left: 20px;background-color: #afa8a8;color: white;"><i class="fa fa-times"></i> 不通过</a>
		</div>
		<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer">
			<tbody id="appendTr">
			
				<input type="hidden" name="id" value="${id}" />
				<input type="hidden" id="shjg" name="shjg"/>
			</tbody>
		</table>
		<div id="tgMsg" style="color: red; text-align: center;margin-top: 10px;">注：选择通过后会自动添加电子签章，信息不能修改，请仔细核对信息</div>
	</form>

<script type="text/javascript">
var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
var validateForm;
$(function(){
	$("#tgMsg").hide();
})

//通过
function pass(){
	removeBtgyy();//隐藏"不通过原因"输入框
	$("#tgMsg").show();//显示div   
	$("#btgyy").textbox('setValue', '');//清空"不通过原因"输入框中的文字
	$("#shjg").val("1");//设置 id 为 shjg 的隐藏值为 1
	$("#pass").css("backgroundColor", "").css("color", "").addClass("btn-info");
	$("#noPass").css("backgroundColor", "#afa8a8").css("color", "white").removeClass("btn-danger");
}

//不通过
function noPass(){
	addBtgyy();//显示"不通过原因"输入框
	$("#tgMsg").hide();//隐藏div       
	$("#shjg").val("0");//设置 id 为 shjg 的隐藏值为 0
	$("#noPass").css("backgroundColor", "").css("color", "").addClass("btn-danger");
	$("#pass").css("backgroundColor", "#afa8a8").css("color", "white").removeClass("btn-info");
}

//不通过原因输入框
function addBtgyy(){
    var tr =$('<tr id="noPassReason">'
            + '    <td class="width-20 active"><label class="pull-right">不通过原因：</label></td>'
			+ '    <td class="width-80"><input style="width:100%;height: 100px;" id="btgyy" name="btgyy"  class="easyui-textbox" data-options="multiline:true,validType:\'length[0,100]\'" /></td>'
            + '</tr>');
	 $("#appendTr").append(tr);
	 $.parser.parse(tr);	 
}

//删除添加的tr
function removeBtgyy(){
	$("#noPassReason").remove();
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