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
						<td class="width-30" colspan="3"><input id="dw" type="text"  class="easyui-textbox" value="${jcfa.qyname }"  data-options="readonly:'true' " style="height: 30px;width: 100%" /></td>
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
					<td class="width-30"><input type="text" name="M4" class="easyui-textbox" value="${jcfa.lxdh }"  data-options=" validType:'length[0,25]' " style="height: 30px;width: 100%" /></td>
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
					<td class="width-30" colspan="3"><input type="text" id="M8" name="M8" class="easyui-combobox" value="${jcjl.m8 }" style="width: 100%;height: 30px;" data-options="required:'true', panelHeight:'142px', editable:false ,valueField: 'text', multiple:true, textField: 'text',url:'${ctx}/aqzf/zfry/idlist' "/></td>								
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
								<td style="text-align: center;width: 70%;  ">问题</td>
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
					<input type="hidden" id="nrid" name="nrid" value="${jcfa.m6}" />
					<input type="hidden" name="ID1" value="${jcfa.id}" />
					<input type="hidden" name="ID2" value="${jcfa.id2}" />
					<input type="hidden" id="wtnum" name="wtnum" />		
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
	
function doSubmit(){
	$("#inputForm").serializeObject();
	$("#inputForm").submit(); 
}
$(function(){
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

    var jcwtallids = "";
	var faid;
	faid= '${jcfa.m6}';
	if(faid == null||faid == undefined){
	faid = 0;
	}
	var target=$('#tt').datagrid({
		method : "get",
		view: detailview,
		width:705,
		nowrap:false,
		singleSelect:true,
		url:ctx+'/aqzf/jcjl/nrlist/'+faid,
		columns:[[
			//{field:'jcdy',title:'检查单元',width:100},
			{field:'m2',title:'检查内容',width:535},
			{field:'m3',title:'操作',width:143,
				formatter: function(value, rowData, rowIndex){
                	return '符合<input checked="checked" type=radio style=width:20px;height:20px name=ra_' + rowData.id + ' id=ra_1_' + rowData.id + ' value=1 ' + 'onclick="collapseView(' + 
						rowIndex+' )" />' +
                	'不符合<input type=radio style=width:20px;height:20px name=ra_' + rowData.id + ' id=ra_2_' + rowData.id + ' value=0 ' + ' onclick="openView(' + rowIndex + ')" />';
            	}
			},
			{field:'ID',title:'id',width:50,hidden:'true'}
		]], 
        detailFormatter:function(rowIndex, rowData){
            jcwtallids +="jcwt_" + rowData.id+",";
			return '<div id="ddv-' + rowIndex + '" ><table style="width: 98%;">' +
					'<input type="hidden" name="jcnrid_' + rowData.id + '" value="' + rowData.id + '" />' +
					'<input type="hidden" id="jcnr_' + rowData.id + '" value="' + rowData.jcnr + '" />' +
					'<tr>'+
					'<td>' +
					'检查问题：' +	
					'</td>'+									
					'</tr>'+
					'<tr>'+
					'<td>' +
					'<input type="text" id="jcwt_' + rowData.id + '" name="jcwt_' + rowData.id + '" style="width: 667px;height: 30px;" />' +	
					'</td>'+									
					'</tr>'+
					'<tr>'+
					'<td>' +
					'图片：' +	
					'</td>'+									
					'</tr>'+
					'<tr>'+
					'<td>' +
					'<div id="uploader_ws_'+rowData.id+'" style="height: 42px;">'+
					'<input type="hidden" name="url_'+rowData.id+'" id="url_'+rowData.id+'" value="'+rowData.pic+'" />'+
					'<div id="fileList_'+rowData.id+'" class="uploader-list" ></div>'+
					'<a style="margin:2px" class="btn btn-success btn-xs" onclick="openPicForm(' + 
						rowData.id+','+rowIndex+' )">上传图片</a>'+
					'</div>'+
					'</td>'+									
					'</tr>'+
					'</table></div>';    
		},
		onLoadSuccess : function(rowdata, rowindex, rowDomElement) {	
		   	if(jcwtallids.length>0){
				jcwtallids = jcwtallids.substr(0,jcwtallids.length - 1);
			}
			var jcwtid = jcwtallids.split(",");
			for (x in jcwtid){
				$("#"+jcwtid[x]).combobox({
					editable:true ,
	    			valueField:'id',
	    			textField:'text',
	    			//multiple:true ,  
        			//multiline:true ,
        			//separator:'||',
	    			panelHeight:'135px',
	    			url:'${ctx}/aqzf/wfxw/idlist'
				});
			}
		}
	});
	
	
	
	if('${action}' == 'create'){
		$('#M1').textbox("setValue", '${jcfa.m1}');
		$('#M2').textbox("setValue", '${jcfa.dbr}');
	}
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