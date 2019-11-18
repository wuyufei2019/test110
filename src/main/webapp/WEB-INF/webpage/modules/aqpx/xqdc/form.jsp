<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
<title>发起调查问卷</title>
<meta name="decorator" content="default" />
</head>
<body>
	<form id="inputForm" action="${ctx}/aqpx/xqdc/${action}" method="post" class="form-horizontal">
		<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer">
			<tbody>
				<tr>
					<td class="width-20 active"><label class="pull-right">培训调查主题：</label></td>
					<td class="width-80" ><input name="pxzt" style="width: 100%;height: 30px;" class="easyui-textbox"
						value="" data-options="required:'true',validType:'length[0,100]'" /></td>
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">培训课程投票项：</label></td>
					<td class="width-80" >
						<div id="kcNames">
							<input id="kcnames" type="hidden" name="kcnames" />
							<div id="kcList"></div>
							<a href="javascript:void(0)" class="btn btn-info btn-sm" data-toggle="tooltip" data-placement="left" onclick="addkc()" title="添加课程"><i class="fa fa-plus"></i> 添加课程投票项</a>
						</div>
					</td>
				</tr>
			</tbody>
		</table>
	</form>

<script type="text/javascript">
var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
var validateForm;
var kcid = 1;
var kcmap= {};
//添加课程
function addkc(){
	layer.open({
	    type: 2,  
	    area: ['400px', '150px'],
	    title: '添加课程',
        maxmin: false, 
	    content: ctx + "/aqpx/xqdc/addkc" ,
	    btn: ['确定', '关闭'],
	    yes: function(index, layero){
	    	var  $list= $("#kcList");
	    	var iframeWin = layero.find('iframe')[0];
	    	var kcname=iframeWin.contentWindow.getkcname();
			if(kcname == ''||kcname == null||kcname == undefined){
				layer.msg("请填写课程名称！",{time: 2000});
				return;
			}
	    	
			var $li = $("<div id=\"kc_"
					+ kcid
					+ "\" style=\"margin-bottom: 10px;\">"
					+ "<a style=\"color:#337ab7;text-decoration:none;cursor:pointer;margin-right:20px;display:inline-block;\">"
					+ kcname
					+ "</a>"
					+ "<span class=\"ss\" onClick=\"removeKc('kc_"
					+ kcid
					+ "')\" style=\"cursor: pointer;\">删除</span>"
					+ " </div>");
			$list.append($li);
			
			kcmap[kcid]=kcname;
			kcid++;
			
			$("#kcnames").val($("#kcnames").val()+kcname+"||");
			layer.close(index);
			},
	cancel: function(index){}
	}); 	
}

//删除预览课程
function removeKc(fileid) {
	var kc = $("#kcnames").val();
	$("#kcnames").val(kc.replace(kcmap[fileid.split("_")[1]] + "||", ""));
	$("#" + fileid).remove();
};

function doSubmit(){
	$("#inputForm").serializeObject();
	$("#inputForm").submit(); 
}

$(function(){
	var flag=true;
	$('#inputForm').form({    
	    onSubmit: function(){    
	    	var isValid = $(this).form('validate');
	    	var kcnames = $("#kcnames").val();
	    	if(kcnames == ''||kcnames == null){
	    		layer.msg("培训课程不能为空！",{time: 2000});
	    		return false;
	    	}
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