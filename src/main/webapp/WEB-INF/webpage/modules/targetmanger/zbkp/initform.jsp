<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
<title>指标考评管理</title>
<meta name="decorator" content="default" />
</head>
<body>

	<form id="inputForm" method="post" class="form-horizontal">
		<table class="table table-bordered">

			<tbody>
				<tr>
					<td class="width-15 active"><label class="pull-right">年度：</label></td>
					<td class="width-35"><input id="year"
						class="easyui-combobox" style="width: 100%;height: 30px;"
						data-options="editable: false, valueField:'year',textField:'year',panelHeight:'auto' "></td>
					<td class="width-15 active"><label class="pull-right">责任部门：</label></td>
					<td class="width-35"><input id="deptid" value="${deptid }" <shiro:lacksRole name="companyadmin">readonly= "true" </shiro:lacksRole>
						style="width: 100%;height: 30px;" class="easyui-combobox"
						data-options="required : true , editable : false ,panelHeight:100,valueField:'id', textField:'text',url: '${ctx}/system/department/deptjson'"></td>
				</tr>

				<tr>
					<td class="width-15 active"><label class="pull-right">季度：</label></td>
					<td class="width-35"><input id="quarter" name="quarter"
						type="text" class="easyui-combobox"
						style="height: 30px;width:100%;"
						data-options="editable: false,required:true,panelHeight:'auto',data:[{value:'1',text:'第一季度'},{value:'2',text:'第二季度'},{value:'3',text:'第三季度'},{value:'4',text:'第四季度'}] "></td>

				</tr>
				<tr>

				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">评定人：</label></td>
					<td class="width-35"><input id="M1" name="m1"
						 class="easyui-textbox"
						style="width: 100%;height: 30px;"
						data-options="validType:'length[0,10]'"></td>
					<td class="width-15 active"><label class="pull-right">评定日期：</label></td>
					<td class="width-35"><input id="pddate" name="m2" required="true"
						 class="easyui-datebox"
						style="width: 100%;height: 30px;" /></td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">备注：</label></td>
					<td class="width-35" colspan="3"><input name="m3" type="text"
						 class="easyui-textbox"
						style="width: 100%;height: 30px;"
						data-options="multiline:true,validType:'length[0,250]' "></td>
				</tr>
			</tbody>
		</table>
		
	</form>
		<div class="easyui-accordion" id="accordion" border="false">
		</div>
	<script type="text/javascript">
		var data = [];
		var dg;
		var thisYear;
		var startYear = new Date().getUTCFullYear() + 1;
		for (var i = 0; i < 3; i++) {
			thisYear = startYear - i;
			data.push({
				"year" : thisYear
			});
		}
		$(function() {
			if ('${action}' == 'create') {
				$("#M1").textbox("setValue", '${username}');
				$("#year").combobox("loadData", data);
				$("#year").combobox("setValue", startYear - 1);
			}
			$("#pddate").datebox("setValue", new Date().format("yyyy-MM-dd"));
		
		});	
		$("#quarter").combobox({
			onSelect : function(rec) {
				if($("#year").combobox("getValue")&&$("#deptid").combobox("getValue"))
					deal();
			}
		});
		$("#year").combobox({
			onSelect : function(rec) {
				if($("#quarter").combobox("getValue")&&$("#deptid").combobox("getValue"))
				deal();
			}
		});
		$("#deptid").combobox({
			onSelect : function(rec) {
				if($("#quarter").combobox("getValue")&&$("#year").combobox("getValue"))
				deal();
			}
		});
		 function deal(){
				$('#accordion').accordion({height : '250px'});//调整datagrid高度
				if($('#accordion').accordion('getPanel','达标考评'))
					$('#accordion').accordion('remove','达标考评');
				$('#accordion').accordion('add', {
					title: '达标考评',
					content: '<table id="target_dg"></table>',
					selected: true,
				});
				   dg = $('#target_dg').datagrid({
					method : "post",
					queryParams:{"year" :$("#year").combobox("getValue"),"deptid" : $("#deptid").combobox("getValue"),"quarter" : $("#quarter").combobox("getValue")},
					url : '${ctx}/target/mbkp/quarter',
					fitColumns : true,
					border : false,
					idField : 'id',
					striped : true,
					rownumbers : true,
					nowrap : false,
					scrollbarSize : 0,
					singleSelect : true,
					onClickCell: onClickCell,
					striped : true,
					columns : [ [ {
						field : 'id',
						title : 'id',
						hidden : true,
					},
					{
						field : 'tname',
						title : '指标名称',
						width : 150,
						align : 'center'
					},{
						field : 'targetval',
						title : '指标值',
						width : 50,
						align : 'center'
					},{
						field : 'completion',
						title : '完成值(点击输入)',
						width : 50,
						editor:'text',
						align : 'center'
					},{
						field : 'result',
						title : '考评结果',
						width : 100,
						align : 'center',formatter : function (value, row){
							return "<input type='radio' name='db"+row.id+"'  value='1' class='i-checks' "+((!value||value==1)?"checked='checked'":"")+" />达标"+
							       "<input type='radio' name='db"+row.id+"' value='0' class='i-checks' "+(value==0?"checked='checked'":"")+"/>不达标";
						}
					}

					] ],
					 onLoadSuccess: function(){
						  $('input').iCheck({
							    radioClass: 'iradio_square-green'
							  }); 
					    },
					checkOnSelect : false,
					selectOnCheck : false
				});
		 }
		//提交处理
		var layerindex = parent.layer.getFrameIndex(window.name); //获取窗口索引
		function doSubmit() {
			var obj=$("#inputForm").serializeObject();
			var rows=dg.datagrid("getData").rows;
			var list=[];
		    for (var index in rows){
		    	var e={"id": rows[index].zpid ,"id2" : rows[index].id ,"completion" : rows[index].completion,"m4" : $("input[name='db"+rows[index].id+"']:checked").val()};
		    	list.push(Object.assign(e,obj));//合并对象
		    }
		    $.ajax({  
                type : 'POST',  
                url : "${ctx}/target/mbkp/create",  
                dataType:"text", 
                contentType: "application/json",  
                data: JSON.stringify(list),  
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
					parent.layer.close(layerindex);//关闭对话框。
                },
                beforeSend : function(){
                	var isValid = $("#inputForm").form('validate');
					if (isValid) {
						$.jBox.tip("正在提交，请稍等...", 'loading', {
							opacity : 0
						});
						return true;
					}
					return false; // 返回false终止表单提交
                }
            });  
		}
		
		$.extend($.fn.datagrid.methods, {
			editCell: function(jq,param){
				return jq.each(function(){
					var opts = dg.datagrid('options');
					var fields = dg.datagrid('getColumnFields',true).concat(dg.datagrid('getColumnFields'));
					for(var i=0; i<fields.length; i++){
						var col = dg.datagrid('getColumnOption', fields[i]);
						col.editor1 = col.editor;
						if (fields[i] != param.field){
							col.editor = null;
						}
					}
					dg.datagrid('beginEdit', param.index);
					for(var i=0; i<fields.length; i++){
						var col = dg.datagrid('getColumnOption', fields[i]);
						col.editor = col.editor1;
					}
				});
			}
		});

		var editIndex = undefined;
		function endEditing(){
			if (editIndex == undefined){return true}
			if (dg.datagrid('validateRow', editIndex)){
				dg.datagrid('endEdit', editIndex);
				editIndex = undefined;
			    $('input').iCheck({
				    radioClass: 'iradio_square-green'
				}); 
				return true;
			} else {
			    $('input').iCheck({
				    radioClass: 'iradio_square-green'
				}); 
				return false;
			}
		}
		function onClickCell(index, field){
		/* 	var row = dg.datagrid('getData').rows[index];
			alert(row.shcd); */
			if (endEditing()){
				dg.datagrid('selectRow', index)
						.datagrid('editCell', {index:index,field:field});
				editIndex = index;
			}
		}		
	</script>
</body>

</html>