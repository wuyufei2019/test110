<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>检查记录管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript" src="${ctx}/static/jquery-easyui-1.4.5/datagrid-detailview.js"></script>
	<script type="text/javascript" src="${ctx}/static/model/js/aqzf/jcjl/index.js?v=1.3"></script>
</head>
<body>

     <form id="inputForm" action="${ctx}/aqzf/jcjl/${action}"  method="post" class="form-horizontal" >
			<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
			  <tbody>
			  	<tr>
					<td class="width-20 active"><label class="pull-right">被检查单位：</label></td>
						<td class="width-30" colspan="3"><input type="text" id="ID2" name="ID2" class="easyui-combobox" value="${jcjl.id2 }" style="height: 30px;width: 100%" data-options="editable:true ,valueField: 'id',textField: 'text',url:'${ctx}/bis/qyjbxx/idjson' "/></td>
				</tr>
				<tr >
					<td class="width-20 active"><label class="pull-right">地址：</label></td>
					<td class="width-30" colspan="3">
						<input type="text" id="M1" name="M1" class="easyui-textbox" value="${jcjl.m1 }"  data-options="validType:'length[0,100]'" style="height: 30px;width: 100%;" />
					</td>
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">法定代表人（负责人）：</label></td>
					<td class="width-30"><input type="text" id="M2" name="M2" class="easyui-textbox" value="${jcjl.m2 }"  data-options=" validType:'length[0,25]' " style="height: 30px;width: 100%" /></td>
					<td class="width-20 active"><label class="pull-right">职务：</label></td>
					<td class="width-30"><input type="text" name="M3" class="easyui-textbox" value="${jcjl.m3 }"  data-options=" validType:'length[0,25]' " style="height: 30px;width: 100%" /></td>
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">联系电话：</label></td>
					<td class="width-30"><input type="text" id="M4" name="M4" class="easyui-textbox" value="${jcfa.lxdh }"  data-options=" validType:'length[0,25]' " style="height: 30px;width: 100%" /></td>
					<td class="width-20 active"><label class="pull-right">检查场所：</label></td>
					<td class="width-30"><input type="text" name="M5" class="easyui-textbox" value="${jcjl.m5 }"  data-options=" validType:'length[0,25]' " style="height: 30px;width: 100%" /></td>
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">检查时间起：</label></td>
					<td class="width-30" ><input id="M6" name="M6" class="easyui-datetimebox" value="${jcjl.m6 }" style="height: 30px;width: 100%;height: 30px;" data-options="editable:false, required:'true' " /></td>
					<td class="width-20 active"><label class="pull-right">检查时间止：</label></td>
					<td class="width-30" ><input id="M7" name="M7" class="easyui-datetimebox" value="${jcjl.m7 }" style="height: 30px;width: 100%;height: 30px;" data-options="editable:false, required:'true' " /></td>
				</tr>
				
				<tr>
					<td class="width-20 active"><label class="pull-right">检查人员：</label></td>
					<td class="width-30" colspan="3"><input type="text" id="M8" name="M8" class="easyui-combobox" value="${jcjl.m8 }" style="width: 100%;height: 30px;" data-options="required:'true', panelHeight:'142px', editable:true ,valueField: 'text', multiple:true, textField: 'text',url:'${ctx}/aqzf/zfry/idlist' "/></td>								
				</tr>

				<tr>
				    <td align="center" colspan="4">
						<a class="btn btn-info btn-sm" data-toggle="tooltip" data-placement="left" onclick="addJcnr()" title="添加检查内容"><i class="fa fa-plus"></i> 添加检查内容</a>
					</td>	
				</tr>
			
				<tr>
					<td class="width-20 active"><label class="pull-right">检查内容：</label></td>
					
					<td class="width-85" colspan="3" >
						<table id="tt" style="width:705px"></table>
						<input type="hidden" id="cznum" name="cznum" value="${cznum}" />	
					</td>
				</tr>	

				<tr>
				    <td align="center" colspan="4">
						<a class="btn btn-info btn-sm" data-toggle="tooltip" data-placement="left" onclick="addNr()" title="其他问题"><i class="fa fa-plus"></i> 其他问题</a>
					</td>	
				</tr>

				<tr>
					<td class="width-20 active"><label class="pull-right">存在问题：</label></td>
					<td class="width-30" colspan="3">
						<table id="czwttable" style="width: 100%;"  border="1" cellspacing="0" cellpadding="0" bordercolor="#e7e7e7"  >
							<tr >
								<td style="text-align: center;width: 70%;">问题</td>
								<td style="text-align: center;width: 15%;">图片(点击查看原图)</td>
								<td style="text-align: center;">操作</td>
							</tr>
						</table>
						
					</td>
				</tr>

				<tr>
					<td class="width-20 active"><label class="pull-right">备注：</label></td>
					<td class="width-30" colspan="3">
						<input type="text" name="M10" class="easyui-textbox" value="${jcjl.m10 }"  data-options="multiline:true ,validType:'length[0,500]'" style="width: 100%;height: 80px;" />
					</td>
				</tr>	
					<input type="hidden" id="nrid" name="nrid"  />
					<input type="hidden" id="lxr" name="lxr"  />
				</tbody>
			</table>
       </form>
 
<script type="text/javascript">
var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
var validateForm;
//得到当前日期
function myformatter(date){  
    var y = date.getFullYear();  
    var m = date.getMonth()+1;  
    var d = date.getDate();  
    return y+'-'+(m<10?('0'+m):m)+'-'+(d<10?('0'+d):d);  
} 

	//下拉关联信息
	$("#ID2").combobox({
		onSelect: function(){
			var id=$('#ID2').combobox('getValue');
			$.ajax({
				type:'get',
				url: '${ctx}/bis/qyjbxx/qydetail/'+id,
				success : function(data) {
					var d=JSON.parse(data);
					$("#M1").textbox('setValue',d.m33);	
					$("#M2").textbox('setValue',d.m5);	
					$("#M4").textbox('setValue',d.m6);	
					$("#lxr").textbox('setValue',d.m6_1);	
				}
			});
		}
	});
	
	
function doSubmit(){
	 var options = $("#ID2").combobox('options');  
     var data = $("#ID2").combobox('getData');/* 下拉框所有选项 */  
     var value = $("#ID2").combobox('getValue');/* 用户输入的值 */  
     var b = false;/* 标识是否在下拉列表中找到了用户输入的字符 */  
     for (var i = 0; i < data.length; i++) {  
        if (data[i][options.valueField] == value) {  
            b=true;  
           	 break;  
        }  
    }  
	if(b==false){
			layer.open({title: '提示',offset: 'auto',content: '所填企业不存在，请去一企一档添加。',shade: 0 ,time: 2000 });
			return;
		}
	$("#inputForm").serializeObject();
	$("#inputForm").submit(); 
}
$(function(){
    //设置默认时间  ;
    var whbh='${whbh}';
	var curr_time = new Date();
	if('${action}'=='create'){    
		$("#M6").datebox("setValue",myformatter(curr_time)); 	
		$("#M7").datebox("setValue",myformatter(curr_time)); 
	}
    //第一次添加时,设置默认时间  ;
	var curr_time = new Date();
	if('${action}'=='create'){    
		$("#M6").datebox("setValue",myformatter(curr_time)); 	
		$("#M7").datebox("setValue",myformatter(curr_time)); 
	}

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
	    	if(data=='success')
	    		parent.layer.open({icon:1,title: '提示',offset: 'rb',content: '操作成功！',shade: 0 ,time: 2000 });
	    	else
	    		parent.layer.open({icon:2,title: '提示',offset: 'rb',content: '操作失败！',shade: 0 ,time: 2000 });
	    	parent.dg.datagrid('reload');
	    	parent.layer.close(index);//关闭对话框。
	    }    
	});
});

    // 负责预览图的销毁
    function removeFile(fileid) {
    	$("#"+fileid).remove();
    	$("#pic_"+fileid).remove();
    	$("#url_"+fileid).remove();
    };
    
    
</script>
</body>
</html>