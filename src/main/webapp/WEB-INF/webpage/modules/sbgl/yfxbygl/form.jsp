<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
<title>设备预防性保养管理添加</title>
<meta name="decorator" content="default" />
<style type="text/css">
.hr-line-dashed {
    border-top: 2px dashed #987cb9;
    color: #987cb9 ;
    height: 1px;
    margin: 7px 0;
}
</style>
</head>
<body>

   <form id="inputForm" action="${ctx}/sbgl/yfxbygl/${action}" method="post" class="form-horizontal">
      <table class="table table-bordered  table-condensed dataTables-example dataTable no-footer">
         <tbody>
            <tr>
               <td class="width-15 active"><label class="pull-right">设备名称：</label></td>
               <td class="width-35" ><input id="sbname" name="sbname" style="width: 77%;height: 30px;" required ="true" class="easyui-combogrid" value="${sbgl.sbname }" />
                  <span  class="btn btn-success btn-sm  "  style="width: 21%;height: 30px;" onclick="search()" ><i class="fa fa-search"></i> 查询</span></td>
               <td class="width-50" colspan="2"></td>
            </tr>
            <c:if test="${action == 'updata' }">
				<tr>
					<td class="width-15 active"><label class="pull-right">维护项目：</label></td>
					<td class="width-35" ><input id="" name="M1" class="easyui-textbox" value="${sbgl.m1 }" style="width:100%;height: 30px;" data-options="required:true, editable:true"/></td>
					<td class="width-15 active"><label class="pull-right">维护周期：</label></td>
					<td class="width-35" ><input id="" name="M4" class="easyui-combobox" value="${sbgl.m4 }" style="width:100%;height: 30px;" data-options="required:true,	editable:false, panelHeight:80,
						data:[{value:'日',text:'日'},
							  {value:'周',text:'周'},
							  {value:'月',text:'月'},
							  {value:'季',text:'季'},
							  {value:'半年',text:'半年'},
							  {value:'年',text:'年'}]"/></td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">维护要求：</label></td>
					<td class="width-35" colspan="3"><input id="" name="M2" type="text" value="${sbgl.m2 }"   class="easyui-textbox" style="width: 100%;height: 40px;" data-options="multiline:true, validType:'length[0,500]'"></td>					
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">维护方法：</label></td>
					<td class="width-35" colspan="3"><input id="" name="M3" type="text" value="${sbgl.m3 }"   class="easyui-textbox" style="width: 100%;height: 40px;" data-options="multiline:true, validType:'length[0,500]'"></td>					
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">操作者：</label></td>
					<td class="width-35" ><input id="" name="M5" class="easyui-combobox" value="${sbgl.m5 }" style="width:100%;height: 30px;" data-options="panelHeight:'120',	editable:true, valueField: 'text', 
					textField: 'text', url:'${ctx}/system/compuser/userjson'"/></td>
					<td class="width-15 active"><label class="pull-right">维修者：</label></td>
					<td class="width-35" ><input id="" name="M6" class="easyui-combobox" value="${sbgl.m6 }" style="width:100%;height: 30px;" data-options="panelHeight:'120',	editable:true, valueField: 'text', 
					textField: 'text', url:'${ctx}/system/compuser/userjson'"/></td>
				</tr>
            </c:if>
            <c:if test="${action == 'create' }">
            	<tr id="tab">
					<td align="center" colspan="4">
						<a class="btn btn-info btn-sm" data-toggle="tooltip" data-placement="left" onclick="addRw()" title="添加维护项目"><i class="fa fa-plus"></i> 添加维护项目</a>
					</td>	
				</tr>
            </c:if>
            <c:if test="${not empty sbgl.ID}">
               <input type="hidden" name="ID" value="${sbgl.ID }" />
               <input type="hidden" name="qyid" value="${sbgl.qyid}" />
               <input type="hidden" name="S1" value="<fmt:formatDate value="${sbgl.s1}" pattern="yyyy-MM-dd HH:mm:ss"  />" />
               <input type="hidden" name="S3" value="${sbgl.s3}" />
            </c:if>
         </tbody>
      </table>
   </form>
   <script type="text/javascript">
				   $(function() {
						$('#sbname').combogrid({
							panelWidth : '45%',
							idField : 'id', //ID字段 
							textField : 'name',
							fitColumns : true,
							method: "post",
							url:'${ctx}/sbgl/jwx/sbjson',
							striped : true,
							editable : true,
							pagination : true,//是否分页 
							rownumbers : true,//序号 
							collapsible : false,//是否可折叠的 
							nowrap:false,
							pageSize : 10,//每页显示的记录条数，默认为10 
							pageList : [ 10 ],//可以设置每页记录条数的列表 
							method : 'post',
							columns : [ [ 
						        {field:'name',title:'设备名称',sortable:false,width:120,align:'center'},    
						        {field:'type',title:'设备类别',sortable:false,width:50,align:'center',
						            	  formatter: function(value){
						            		  if(value=='tzsb') return "特种设备";
						            		  else if(value=='scsb') return "生产设备  ";
						            		  else if(value=='gygc') return "公用工程";
						            		  else if(value=='aqss') return "安全设施";
						            		  else return ;
						            	  }},   
						        {field:'specification',title:'设备规格',sortable:false,width:50,align:'center'},    
						        {field:'number',title:'设备编号',sortable:false,width:50,align:'center'}
							] ],
					     	onSelect : function (rowIndex, rowData){
					    		if(rowData.id){
					    	  		$('#sbname').combogrid("setValue",rowData.name);
					    		}
							}
						});
				});
				function search(){
					var sbname=$('#sbname').combogrid("getText");
					$('#sbname').combogrid("showPanel");
					$('#sbname').combogrid("grid").datagrid("reload", { 'view_sbname': sbname });
				}
   				//提交处理
				var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
				var validateForm;
				function doSubmit() {
					$("#inputForm").serializeObject();
					$("#inputForm").submit();
				}

				$(function() {
					var flag = true;
					$('#inputForm').form({
						onSubmit : function() {
							var b = $("#sbname").combogrid("getText");
							$.ajax({
								type : "post",
								url : "${ctx}/sbgl/jwx/sbnameyz",
								data : {"m3":b},
								async: false,
								success : function(data) {
									if(data == 'no') {
										$("#sbname").combogrid("setValue","");
										layer.msg("设备名称不存在!请重新选择",{time: 2000});
										return false;
									}
								}
							});
							if('${action}' == 'create') {
								if (count-1 == 0) {
									layer.msg("必须添加维护项目!",{time: 2000});
									return false;
								}
							}
							var isValid = $(this).form('validate');
							if (isValid && flag) {
								flag = false;
								$.jBox.tip("正在提交，请稍等...", 'loading', {
									opacity : 0
								});
								return true;
							}
							return false; // 返回false终止表单提交
						},
						success : function(data) {
							$.jBox.closeTip();
							if (data == 'success')
								parent.layer.open({
									icon : 1,
									title : '提示',
									offset : 'rb',
									content : '操作成功！',
									shade : 0,
									time : 2000
								});
							else
								parent.layer.open({
									icon : 2,
									title : '提示',
									offset : 'rb',
									content : '操作失败！',
									shade : 0,
									time : 2000
								});
							parent.dg.datagrid('reload');
							parent.layer.close(index);//关闭对话框。
						}
					});
				});
				
				var rwid=1;
				var count=1;
				function addRw() {
					var sb= $("#sbname").combobox('getValue');
					if(sb!=null && sb!=''){
						var $li = $(
								'<tr name="all_'+rwid+'">'+
				            	'<td colspan="4" style="text-align: center;"><div class="hr-line-dashed"></div></td>'+
				            	'</tr>'+
								'<tr name="all_'+rwid+'">'+
					            '<td class="width-15 active"><a class="fa fa-minus-square btn-danger btn-outline" onclick="removeTr('+rwid+')"></a><label class="pull-right">维护项目：</label></td>'+
					            '<td class="width-35" ><input id="whxm_'+rwid+'" name="M1" class="easyui-textbox" value="" style="width:100%;height: 30px;" data-options=""/></td>'+
					            '<td class="width-15 active"><label class="pull-right">维护周期：</label></td>'+
					            '<td class="width-35" ><input id="whzq_'+rwid+'" name="M4" class="easyui-combobox" value="" style="width:100%;height: 30px;" data-options=""/></td>'+
					            '</tr>'+
					            '<tr name="all_'+rwid+'">'+
								'<td class="width-15 active"><label class="pull-right">维护要求：</label></td>'+
								'<td class="width-35" colspan="3"><input id="whyq_'+rwid+'" name="M2" type="text" value=""   class="easyui-textbox" style="width: 100%;height: 40px;" data-options=" "></td>'+					
								'</tr>'+
								'<tr name="all_'+rwid+'">'+
								'<td class="width-15 active"><label class="pull-right">维护方法：</label></td>'+
								'<td class="width-35" colspan="3"><input id="whff_'+rwid+'" name="M3" type="text" value=""   class="easyui-textbox" style="width: 100%;height: 40px;" data-options=""></td>'+					
								'</tr>'+
								'<tr name="all_'+rwid+'">'+
					            '<td class="width-15 active"><label class="pull-right">操作者：</label></td>'+
					            '<td class="width-35" ><input id="czz_'+rwid+'" name="M5" class="easyui-combobox" value="" style="width:100%;height: 30px;" data-options=""/></td>'+
					            '<td class="width-15 active"><label class="pull-right">维修者：</label></td>'+
					            '<td class="width-35" ><input id="wxz_'+rwid+'" name="M6" class="easyui-combobox" value="" style="width:100%;height: 30px;" data-options=""/></td>'+
					            '</tr>'
					            );
						$("#tab").after($li);
						$('#whxm_'+rwid).textbox({
							required:true,
							editable:true
				  		});  
						$('#whzq_'+rwid).combobox({   
							required:true,
							editable:false,
							panelHeight:80,
							data:[{value:'日',text:'日'},{value:'周',text:'周'},{value:'月',text:'月'},{value:'季',text:'季'},{value:'半年',text:'半年'},{value:'年',text:'年'}]
				  		});  
						$('#whyq_'+rwid).textbox({   
							multiline:true,
							validType:'length[0,500]'
				  		});  
						$('#whff_'+rwid).textbox({
							multiline:true,
							validType:'length[0,500]'
				  		});
						$('#czz_'+rwid).combobox({
							panelHeight:'120',
							editable:true ,
							valueField: 'text',
							textField: 'text',
							url:'${ctx}/system/compuser/userjson'
				  		});
						$('#wxz_'+rwid).combobox({
							panelHeight:'120',
							editable:true ,
							valueField: 'text',
							textField: 'text',
							url:'${ctx}/system/compuser/userjson'
				  		});
						rwid=rwid+1;
						count=count+1;
					}else{
						layer.msg("您还没有选择设备信息!",{time: 2000});
					}
				}
				//删除指定行的内容
				function removeTr(obj) {
					$("[name=all_"+obj+"]").remove();
					count=count-1;
				}
			</script>
</body>

</html>