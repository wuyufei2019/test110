<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>类别分类添加</title>
	<meta name="decorator" content="default"/>
	<style type="text/css">
	.BMap_cpyCtrl  
    {  
        display:none;   
    }  
    .anchorBL{  
        display:none;   
    }  
    .ball {
    width: 10px;
    height: 10px;
    background: red;
    border-radius: 50%;
    position: absolute;
	} 
	.wrap{
    background: #ccc;
    position: relative;
	} 
	</style>
	<style type="text/css">
 
</style>
</head>
<body>

     <form id="inputForm" action="${ctx}/zdgl/flfg/${action}"  method="post" class="form-horizontal" >
			<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
			  <tbody>
				<input type="hidden" name="ID" value="${entity.id }" >
				<input type="hidden" name="ID1" value="${entity.id1}" />
				<tr>
					<td class="width-15 active"><label class="pull-right">类别名称：</label></td>
				    <td class="width-35">
				    	<input id="M1" name="M1" class="easyui-textbox" style="width: 250px;height: 30px;" value="${entity.m1 }" data-options="required:'true',validType:'length[0,100]'" />
				    </td>
				</tr>
				<tr>
			        <td class="width-15 active"><label class="pull-right">所属上级:</label></td>
			        <td class="width-35"><input id="Pid" name="Pid" value="${entity.pid}" class="easyui-combotree" style="width:250px;height: 30px;" data-options="required:'required',url: '${ctx}/zdgl/flfg/treelist'"/></td>
			    </tr>
			</tbody>
		</table>
      </form>
<script type="text/javascript">
	if(parent.parentPermId){
		$('#Pid').val(parent.parentPermId);
	}else{
		$('#Pid').val(0);
	}
	
	var index = parent.layer.getFrameIndex(window.name); //获取窗口索引

	function doSubmit(){
		$("#inputForm").serializeObject();
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
		    	parent.dg.treegrid('reload');
		    	parent.dg.treegrid('clearChecked');
		    	parent.layer.close(index);//关闭对话框。
		    }    
		});
	
	});
	</script>
</body>
</html>