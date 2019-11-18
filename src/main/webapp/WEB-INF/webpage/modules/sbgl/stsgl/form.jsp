<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
<meta name="decorator" content="default" />
<title></title>

</head>
<body>
		<form id="inputForm" action="${ctx}/sbgl/stsgl/${action}" method="post">
			<table id="tablecont" class="table table-bordered dataTable" style="margin:auto;">
			<tbody>
				<tr>
					<td class="width-20 active"><label class="pull-right">项目名称：</label></td>
					<td class="width-30" colspan="3"><div>
							<input name="projectname" style="width: 100%;height: 30px;" class="easyui-textbox easyui-validatebox" value="${entity.projectname }" data-options="required:'true',validType:'length[0,30]'" />
						</div></td>
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">项目简介：</label></td>
					<td class="width-30" colspan="3"><div>
							<input name="projectproduce" class="easyui-textbox easyui-validatebox" value="${entity.projectproduce }" style="width: 100%;height: 80px;" data-options="multiline : true,validType:['length[0,250]']" />
						</div></td>
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">批准部门：</label></td>
					<td class="width-30"><div>
							<input name="approvedept" class="easyui-textbox easyui-validatebox" value="${entity.approvedept }" style="width: 100%;height: 30px;" data-options="required:'true'" />
						</div></td>
					<td class="width-20 active"><label class="pull-right">批准日期：</label></td>
					<td class="width-30"><div>
							<input name="approvetime" class="easyui-datebox" value="${entity.approvetime }" style="width: 100%;height: 30px;" data-options="required:'true'" />
						</div></td>
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">建设日期：</label></td>
					<td class="width-30"><div>
						<input name="constructiontime" class="easyui-datebox" value="${entity.constructiontime }" style="width: 100%;height: 30px;" data-options="required:'true'" />
					</div></td>
                  <td class="width-20 active"><label class="pull-right">完成日期：</label></td>
                     <td class="width-30"><div>
                        <input name="finishtime" class="easyui-datebox" value="${entity.finishtime }" style="width: 100%;height: 30px;" data-options="required:'true'" />
                  </div></td>
				</tr>
            <c:if test="${action eq 'create' }">
               <tfoot>
                  <tr>
                     <td align="center" colspan="4"><a class="btn btn-info btn-sm" data-toggle="tooltip" data-placement="left" onclick="addfile()"
                           title="添加附件">
                           <i class="fa fa-plus"></i>添加附件
                        </a></td>
                  </tr>
                </tfoot>
              </c:if>

				<c:if test="${not empty entity.ID}">
					<input type="hidden" name="ID" value="${entity.ID}" />
					<input type="hidden" name="ID1" value="${entity.ID1}" />
					<input type="hidden" name="S1"
						value='<fmt:formatDate value="${entity.s1}" pattern="yyyy-MM-dd HH:mm:ss"/>' />
					<input type="hidden" name="S3" value="${entity.s3}" />
				</c:if>
				</tbody>
			</table>
            <div class="easyui-accordion" id="accordion" border="false"> </div>
		</form>
<script type="text/javascript">
	var usertype=${usertype};
	var action='${action}'
	var COUNT=0;//附件总数
	var Table_Dg;//easyui datagrid
	var Table_Data=[];//datagrid 数据
	var Datagrid_Setting;//datagrid参数
	var $ = jQuery,filelist=[],fileuploader=[];
	function addfile() {
		openDialog("添加附件", ctx + "/sbgl/stsgl/addfile/", "650px", "350px", "");
	}
	$(function() {
		Datagrid_Setting = {
			fitColumns : true,
			border : true,
			striped : true,
			rownumbers : true,
			nowrap : false,
			idField : 'id',
			scrollbarSize : 0,
			singleSelect : true,
			striped : true,
			columns : [ [ {
				field : 'type',
				title : '文件类型',
				sortable : false,
				width : 100,
				align : 'center',
				formatter: function(value){
					var type;
					if(value==1){
						type = "立项审批文件";
					}else if(value==2){
						type ="可行性研究报告";
					}else if(value==3){
						type ="预评价报告";
					}else if(value==4){
						type ="安全设施设计";
					}else if(value==5){
						type ="项目试生产方案";
					}else if(value==6){
						type ="安全设施竣工验收";
					}else if(value==7){
						type ="设计、施工、监理单位的相关资质";
					}else{
						type ="其他材料";
					}
					return type;
				}
			}, {
				field : 'contjson',
				title : '文件基本信息',
				sortable : false,
				width : 200,
				formatter: function(value){
					return value.replace("}","").replace("{","").replace(/\"/g,"");	
				}
			}, {
				field : 'url',
				title : '文件名称',
				sortable : false,
				width : 100,
				align : 'center',
				formatter: function(value){
					var html="";
					if(value){
	        			var sp = value.split("||");
	        			html="<a class='fa fa-file-word-o btn-danger btn-outline' target='_blank' style='margin:1px auto' href='"+sp[0]+"'> "+sp[1]+"</a>"; 
	        		}
	        		return html; 
				}
			}, {
				field : 'operation',
				title : '操作',
				sortable : false,
				width : 50,
				align : 'center',
				formatter: function(value,row){
					var html="";
	        		html="<a class='btn btn-xs fa fa-trash-o btn-danger' target='_blank' onclick='deleterow(" + row.time + ")'>删除</a>"; 
	        		return html; 
				}
			}
			] ],
			onLoadSuccess : function() {
			},
			checkOnSelect : false,
			selectOnCheck : false
		};
	});
	//删除行
	function deleterow(rowtime) {
		top.layer.confirm('删除后无法恢复您确定要删除？', {icon: 3, title:'提示'}, function(tmpindex){
			for ( var index in Table_Data) {
				if (Table_Data[index].time == rowtime) {
					//删除元素
					Table_Data.splice(index, 1);
				}
			}
			Table_Dg.datagrid("loadData", Table_Data);
			if (Table_Data.length == 0) {
				$('#accordion').accordion('remove', 0);
			}
			top.layer.close(tmpindex);
		});
	}
	var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
	function doSubmit() {
		var obj = $("#inputForm").serializeObject();
		//序列化提交对象
		var data = (action=="create")?{
			"list" : JSON.stringify(Table_Data),
			"entity" : JSON.stringify(obj)
		}:obj;
		console.log(data);
		$.ajax({
			type : 'POST',
			url : "${ctx}/sbgl/stsgl/" + action,
			data : data,
			success : function(data) {
				$.jBox.closeTip();
				if (data == 'success') {
					parent.layer.open({
						icon : 1,
						title : '提示',
						offset : 'rb',
						content : '操作成功！',
						shade : 0,
						time : 2000
					});
					parent.dg.datagrid('reload');
					parent.layer.close(index);//关闭对话框。
				} else {
					parent.layer.open({
						icon : 2,
						title : '提示',
						offset : 'rb',
						content : '操作失败！',
						shade : 0,
						time : 2000
					});
				}
			},
			beforeSend : function() {
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
</script>


</body>
</html>