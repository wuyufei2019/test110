<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
<title>设备保养计划添加</title>
<meta name="decorator" content="default" />
</head>
<body>

   <form id="inputForm" action="${ctx}/sbgl/sbbyjh/${action}" method="post" class="form-horizontal">
      <table class="table table-bordered  table-condensed dataTables-example dataTable no-footer">
         <tbody>
            <tr>
               <td class="width-15 active" align="center" colspan="4">保养计划信息</td>
            </tr>
            <tr>
               <td class="width-15 active"><label class="pull-right">保养计划名称：</label></td>
               <td class="width-35" ><input id="M6" name="M6" class="easyui-textbox" value="${sbgl.m6 }"
                     style="width:100%;height: 30px;" data-options="required:true"/></td>
               <td class="width-15 active"><label class="pull-right">制定人：</label></td>
               <td class="width-35" ><input id="" name="M7" class="easyui-combobox" value="${sbgl.m7 }"
                     style="width:100%;height: 30px;" data-options="panelHeight:'120',editable:true ,valueField: 'text',textField: 'text',url:'${ctx}/system/compuser/userjson'"/></td>
            </tr>
            <tr>
               <td class="width-15 active"><label class="pull-right">制定日期：</label></td>
               <td class="width-35" ><input id="M8" name="M8" value="${sbgl.m8 }" class="easyui-datebox" style="width: 100%;height: 30px;"/></td>
            </tr>
            <tr>
               <td class="width-15 active" align="center" colspan="4">设备基本信息</td>
            </tr>
            <tr>
               <td class="width-15 active"><label class="pull-right">设备名称：</label></td>
               <td class="width-35">
                  <input id="sbname" name="sbname" style="width: 79%;height: 30px;" required ="true" class="easyui-combogrid" value="${sbgl.sbname }" />
                  <span  class="btn btn-success btn-sm  "  style="width: 19%;height: 30px;" onclick="search()" ><i class="fa fa-search"></i> 查询</span>
               </td>
               <td class="width-15 active"><label class="pull-right">设备类别：</label></td>
               <td class="width-35" ><input id="sblb" name="sblb" readonly="true" class="easyui-combobox" value="${sbgl.sblb }"
                   style="width:100%;height: 30px;" data-options="panelHeight:100,valueField:'value', textField:'text',data:[{value:'tzsb',text:'特种设备'},
                   {value:'scsb',text:'生产设备'},{value:'aqss',text:'安全设施'}]"/>
               </td>
            </tr>
            <tr>
               <td class="width-15 active"><label class="pull-right">设备编号：</label></td>
               <td class="width-35" ><input id="sbbh" name="sbbh" class="easyui-textbox" value="${sbgl.sbbh }"
                     style="width:100%;height: 30px;" data-options="editable:false"/></td>
               <td class="width-15 active"><label class="pull-right">设备型号：</label></td>
               <td class="width-35" ><input id="sbxh" name="sbxh" class="easyui-textbox" value="${sbgl.sbxh }"
                     style="width:100%;height: 30px;" data-options="editable:false"/></td>
            </tr>
         	<tr>
				<td align="center" colspan="4">
					<a class="btn btn-info btn-sm" data-toggle="tooltip" data-placement="left" onclick="addRw()" title="添加保养内容"><i class="fa fa-plus"></i> 添加保养内容</a>
				</td>	
			</tr>
			<tr>
				<td class="width-15 active"><label class="pull-right">内容设置：</label></td>
				<td class="width-35" colspan="3">
					<table id="rwtable" style="width: 100%;"  border="1" cellspacing="0" cellpadding="0" bordercolor="#e7e7e7" >
						<tr >
							<td style="text-align: center;width: 37%;">保养项目</td>
							<td style="text-align: center;width: 37%;">保养级别</td>
							<td style="text-align: center;">操作</td>
						</tr>
			         	<c:if test="${action == 'create' }">
			         	
			         	</c:if>
					</table>	
				</td>
			</tr>
			<tr>
               <td class="width-15 active" align="center" colspan="4">设备保养周期（h）</td>
            </tr>
        	<tr>
               <td class="width-15 active"><label class="pull-right">例行保养：</label></td>
               <td class="width-35" ><input id="" name="M3" class="easyui-textbox" value="${sbgl.m3 }"
                     style="width:100%;height: 30px;" data-options="required:true, editable:true, validType:'integ'"/></td>
               <td class="width-15 active"><label class="pull-right">一级保养：</label></td>
               <td class="width-35" ><input id="" name="M4" class="easyui-textbox" value="${sbgl.m4 }"
                     style="width:100%;height: 30px;" data-options="required:true, editable:true, validType:'integ'"/></td>
            </tr>
            <tr id="ID1">
               <td class="width-15 active"><label class="pull-right">二级保养：</label></td>
               <td class="width-35" ><input id="" name="M5" class="easyui-textbox" value="${sbgl.m5 }"
                     style="width:100%;height: 30px;" data-options="required:true, editable:true, validType:'integ'"/></td>
            </tr>
            <c:if test="${not empty sbgl.id}">
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
				    	  		$('#sblb').combobox("setValue","");
				    	  		$('#sbbh').textbox("setValue","");
				    	  		$('#sbxh').textbox("setValue","");
				    	  		$('#sblb').combobox("setValue",rowData.type);
				    	  		$('#sbbh').textbox("setValue",rowData.number);
				    	  		$('#sbxh').textbox("setValue",rowData.specification);
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
							if ('${action}' == 'create') {
								var jhname = $("#M6").val();
								$.ajax({
									type : "post",
									url : "${ctx}/sbgl/sbbyjh/jhnameyz",
									data : {"m6":jhname},
									async: false,
									success : function(data) {
										if(data == 'yes') {
											$("#M6").textbox("setValue","");
											layer.msg("设备保养计划名称已存在!请重新填写！",{time: 2000});
											return false;
										}
									}
								});
							}
							if (count-1 == 0) {
								layer.msg("必须添加保养内容!",{time: 2000});
								return false;
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
				var a = '';
				if ('${action}' == 'update') {
					var nullid = '0';
					var a = '<input type="hidden" name="ID" value="'+nullid+'" />'
				}
				function addRw() {
					var sb= $("#sbname").combobox('getValue');
					if(sb!=null && sb!=''){
						var $list= $("#rwtable tr").eq(-1);
						var $li = $(
									'<tr style="height:40px" >'+
									'<td style="width:37%" align="center">'+a+
									'<input data-options="" type="text" id="ri1_'+rwid+'" name="M1" class="easyui-textbox" value=""  style="width:95%;height: 30px;" />'+
									'</td>'+
									'<td style="width:37%" align="center">'+
									'<input id="ri2_'+rwid+'" name="M2" class="easyui-combobox" style="width: 80%;height: 30px;" value=""/>' +
									'</td>'+
									'<td align="center">'+
									'<button class="btn btn-info btn-sm" style="width:80px" data-toggle="tooltip" data-placement="left" onclick="removeTr($(this).parent().parent())" title="删除"><i class="fa fa-trash-o"></i> 删除</button>'+
									'</td>'+
									'</tr>'
						            );
						$list.after( $li );
						$('#ri1_'+rwid).textbox({   
							required:true
				  		});  
						$('#ri2_'+rwid).combobox({
							required:true,
							editable:false,
							panelHeight:80,
							data:[{value:'0',text:'例行保养'},{value:'1',text:'一级保养'},{value:'2',text:'二级保养'}]
				  		});  
				  		rwid=rwid+1;
				  		count=count+1;
					}else{
						layer.msg("您还没有选择设备信息!",{time: 2000});
					}
					
				}
				if ('${action}' == 'update') {
					var arrid='';
					var sbgl1 = '${sbgl1}';
					sbgl1=JSON.parse(sbgl1);
				        $.each(sbgl1, function(idx, obj) {
				        	arrid=arrid+','+obj.id;
				            var $list= $("#rwtable tr").eq(-1);
							var $li = $(
									'<tr style="height:40px" >'+
									'<td style="width:37%" align="center"><input type="hidden" name="ID" value="'+obj.id+'" />'+
									'<input data-options="" type="text" id="ri1_'+rwid+'" name="M1" class="easyui-textbox" value="'+obj.m1+'"  style="width:95%;height: 30px;" />'+
									'</td>'+
									'<td style="width:37%" align="center">'+
									'<input id="ri2_'+rwid+'" name="M2" class="easyui-combobox" style="width: 80%;height: 30px;" value="'+obj.m2+'"/>' +
									'</td>'+
									'<td align="center">'+
									'<button class="btn btn-info btn-sm" style="width:80px" data-toggle="tooltip" data-placement="left" onclick="removeTr($(this).parent().parent())" title="删除"><i class="fa fa-trash-o"></i> 删除</button>'+
									'</td>'+
									'</tr>'
						            );
							$list.after( $li );
							$('#ri1_'+rwid).textbox({   
								required:true
					  		});  
							$('#ri2_'+rwid).combobox({
								required:true,
								editable:false,
								panelHeight:80,
								data:[{value:'0',text:'例行保养'},{value:'1',text:'一级保养'},{value:'2',text:'二级保养'}]
					  		});  
			  				rwid=rwid+1;
			  				count=count+1;
				        });
				        var arr = arrid.replace(",","");
				        $("#ID1").after('<input type="hidden" name="arrid" value="'+arr+'" />')
				}
				//删除指定行的内容
				function removeTr(obj) {
					obj.remove();
					count=count-1;
				}
				
			</script>
</body>

</html>