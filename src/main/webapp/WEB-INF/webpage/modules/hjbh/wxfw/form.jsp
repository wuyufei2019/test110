<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
<title>危险废物产生管理</title>
<meta name="decorator" content="default" />
</head>
<body>
   <form id="inputForm" class="form-horizontal">
      <table id="rwtable" class="table table-bordered">
         <c:if test="${action eq 'create' }">
            <tfoot>
               <tr>
                  <td align="center" colspan="4"><a class="btn btn-info btn-sm" data-toggle="tooltip" data-placement="left" onclick="adddetail()"
                        title="新增产生记录">
                        <i class="fa fa-plus"></i>新增废物产生记录
                     </a></td>
               </tr>
            </tfoot>
         </c:if>
         <tbody>
            <tr>
               <td class="width-15 active"><label class="pull-right">年度：</label></td>
               <td class="width-35"><input id="year" name="year" type="text" value="${entity.year }" class="easyui-combobox"
                     style="width: 100%;height: 30px;"
                     data-options="required:true,panelHeight:100,valueField:'year', textField:'year'"></td>
               <td class="width-15 active"><label class="pull-right">填报人：</label></td>
               <td class="width-35"><input name="informant" id="informant" style="width: 100%;height: 30px;" class="easyui-textbox"
                     value="${entity.informant }"></td>
            </tr>
            <tr>
               <td class="width-15 active"><label class="pull-right">联系电话：</label></td>
               <td class="width-35" ><input name="phone" id="phone" style="width: 100%;height: 30px;" class="easyui-textbox"
                     value="${entity.phone }" data-options="" /></td>
               <td class="width-15 active"><label class="pull-right">填报日期：</label></td>
               <td class="width-35" ><input name="recordtime" id="recordtime" style="width: 100%;height: 30px;" class="easyui-datebox"
                     value="${entity.recordtime }" data-options="" /></td>
            </tr>
            <tr>
               <td class="width-15 active"><label class="pull-right">单位负责人：</label></td>
               <td class="width-35"><input name="principal" id="principal" style="width: 100%;height: 30px;" class="easyui-textbox"
                     value="${entity.principal }" data-options="validType:'length[0,25]'" /></td>
            </tr>

            <c:if test="${not empty entity.ID}">
               <input type="hidden" name="ID" value="${entity.ID}" />
               <input type="hidden" name="qyid" value="${entity.qyid}" />
               <input type="hidden" name="S1" value="<fmt:formatDate value="${entity.s1}" pattern="yyyy-MM-dd HH:mm:ss"  />" />
               <input type="hidden" name="S3" value="${entity.s3}" />
            </c:if>
         </tbody>
      </table>
      <div class="easyui-accordion" id="accordion" border="false">
      </div>
   </form>
   <script type="text/javascript">
	$(function(){
      	$("#year").combobox("loadData", yeardata);
      	$("#year").combobox("setValue" ,startYear-1);
      	if('${action}'!='update'){
         	$("#informant").textbox('setValue','${username}');
         	$("#phone").textbox('setValue','${phone}');
         	$("#recordtime").datebox('setValue',new Date().format("yyyy-MM-dd"));
      	}
	})
	//年度下拉框数据
	var yeardata = [];
	var thisYear;
	var startYear=new Date().getUTCFullYear()+1;
	for(var i=0;i<5;i++){
		thisYear=startYear-i;
		yeardata.push({"year":thisYear});
	}
	var TIME;//添加记录的时间变量
	var TableData = [];//datagrid的全局数据
	var dg;
	var action = '${action}';//当前页面的操作名称
	var DatagridSetting = {//datagrid参数
			fitColumns : true,
			border : true,
			striped : true,
			rownumbers : true,
			nowrap : false,
			idField : 'id',
			scrollbarSize : 0,
			singleSelect : true,
			striped : true,
			columns : [ [ 
			{field : 'trashname',title : '废物名称',align : 'center',width : 40}, 
			{field : 'trashkind',title : '废物类别',align : 'center',width : 40}, 
			{field : 'resource',title : '产生源',align : 'center',width : 60}, 
			{field : 'unit',title : '计量单位',align : 'center',width : 30}, 
			{field : 'direction',title : '废物流向',align : 'center',width : 70}, 
			{field : 'm1',title : '外单位处置的企业',align : 'center',width : 70}, 
			{field : 'm2',title : '内部利用处理量',align : 'center',width : 40}, 
			{field : 'm3',title : '委托利用处理量',align : 'center',width : 40}, 
			{field : 'm4',title : '累计贮存量',align : 'center',width : 40}, 
			{field : 'm5',title : '年度产生量',align : 'center',width : 40},
			{field : 'operation',title : '操作',align : 'center',width : 60,
				formatter : function(value, row) {
					return "<a class='btn btn-warning btn-xs' onclick='upddetail(" + JSON.stringify(row) + ")'>修改</a>"
					+ "<a class='btn btn-danger btn-xs' onclick='deleterow(" + row.time + ")'>删除</a>";
				}
			}
			] ],
			onLoadSuccess : function() {
			},
			checkOnSelect : false,
			selectOnCheck : false
		};

		function deleterow(rowtime) {
			top.layer.confirm('删除后无法恢复您确定要删除？', {icon: 3, title:'提示'}, function(tmpindex){
				for ( var index in TableData) {
					if (TableData[index].time == rowtime) {
						TableData.splice(index, 1);
					}
				}
				dg.datagrid("loadData", TableData);
				if (TableData.length == 0) {
					$('#accordion').accordion('remove', 0);
				}
				top.layer.close(tmpindex);
			});
		}
		
		
		function adddetail() {
			openDialog("添加危险废物产生记录信息", ctx + "/hjbh/wxfw/detailcreate/", "90%", "90%", "");
		}
		
		
		function upddetail(row) {
			TIME = row.time;
			openDialog("修改危险废物产生记录信息", ctx + "/hjbh/wxfw/detailupdate?time=" + row.time + "&data=" + encodeURIComponent(JSON.stringify(row)), "800px", "360px", "");
		}
		
		
		var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
		function doSubmit() {
			var obj = $("#inputForm").serializeObject();
			var data = (action == 'create') ? {
				"list" : JSON.stringify(TableData),
				"entity" : JSON.stringify(obj)
			} : obj;
			$.ajax({
				type : 'POST',
				url : "${ctx}/hjbh/wxfw/" + action,
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