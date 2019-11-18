<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>安全会议信息</title>
	<meta name="decorator" content="default"/>
      <script type="text/javascript" src="${ctx}/static/model/js/targetmanger/safetyculture/aqhy/qy_index.js?v=1.2"></script>
</head>
<body >
<!-- 工具栏 -->
<div id="target_aqhy_tb" style="padding:5px;height:auto">
	<form id="searchFrom"  style="margin-bottom: 8px;" class="form-inline pull-left">				
	<div class="form-group">
    <c:if test="${ usertype eq 'isbloc' }">
        <input type="text"  name="view_qyname" style="height:30px" class="easyui-textbox"data-options="width:200,prompt: '企业名称'" />
    </c:if>
      <input type="text"  name="view_theme" style="height:30px" class="easyui-textbox" data-options="prompt: '会议主题'"/>
      <input name="view_type"  style="height: 30px;" class="easyui-combobox"  
                   data-options="prompt: '会议类型',editable: false,panelHeight:'auto', valueField:'value',textField:'text',
                   data:[{value:1,text:'公司级'},{value:2,text:'部门级'},{value:3,text:'临时会议'},{value:4,text:'其他会议'}]" />
    </div>
	</form>
   <div class="pull-left">
         <button  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="search()" ><i class="fa fa-search"></i> 查询</button>
         <button  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="reset()" ><i class="fa fa-refresh"></i> 重置</button>
      </div>
	<div class="row">
	<div class="col-sm-12">
		<div class="pull-left">
		 	<shiro:hasPermission name="target:aqhy:add">
		       	  <button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="add()" title="添加"><i class="fa fa-plus"></i> 安全会议</button> 
			</shiro:hasPermission> 
			<shiro:hasPermission name="target:aqhy:update">
			    <button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="upd()" title="修改"><i class="fa fa-file-text-o"></i> 修改</button> 
			</shiro:hasPermission>
			<shiro:hasPermission name="target:aqhy:delete">
				<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="del()" title="删除"><i class="fa fa-trash-o"></i> 删除</button> 
			</shiro:hasPermission>
			<shiro:hasPermission name="target:aqhy:view">
        		<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="view()" title="查看"><i class="fa fa-search-plus"></i> 查看</button> 
        	</shiro:hasPermission>
         
        <%-- 	<shiro:hasPermission name="target:aqhy:export">
        		<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="fileexport()" title="导出"><i class="fa fa-external-link"></i> 导出</button> 
        	</shiro:hasPermission> --%>
	       <button class="btn btn-white btn-sm " data-toggle="tooltip" data-placement="left" onclick="refresh()" title="刷新"><i class="glyphicon glyphicon-repeat"></i> 刷新</button>
			</div>
	</div>
  </div>
</div>
<table id="target_aqhy_dg"></table> 
  <div id="layer_delay" style="display:none;height:100%">
         <table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
           <tbody>
            <tr>
             <td class="width-20 active"><label class="pull-right">推迟时间至:</label></td>
               <td class="width-80"><input id="time" name="time" class="easyui-datetimebox"
                     style="width:100%;height: 30px;" data-options="required:true" ></td>
            </tr>
            <tr>
             <td class="width-20 active"><label class="pull-right">推迟原因:</label></td>
               <td class="width-80"><input id="delayreason" name="delayreason" type="text" class="easyui-textbox"
                     style="width: 100%;height: 50px;" data-options="multiline:true,validType:'length[0,100]'" /></td>
            </tr>
            </tbody>
            </table>
  </div>
  <div id="layer_feedback" style="display:none;height:100%">
         <table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
           <tbody>
            <tr>
             <td class="width-20 active"><label class="pull-right">督办事项反馈:</label></td>
               <td class="width-80"><input id="feedback" name="feedback" type="text" class="easyui-textbox"
                     style="width: 100%;height: 50px;" data-options="required: true,multiline:true,validType:'length[0,1000]'" /></td>
            </tr>
            </tbody>
            </table>
  </div>
<script type="text/javascript">
var qyid= '${qyid}';
var usertype='${usertype}';
//弹窗增加
function add(u) {
	openDialog("添加安全会议信息",ctx+"/target/aqhy/create/","830px", "400px","");
}
//弹窗增加
function addCont(id) {
	openDialog("添加会议纪要信息", ctx + "/target/aqhy/addcont/" + id, "800px", "400px", "");
}
function addFeedback(id) {
	layer.open({
		type: 1, 
		zIndex :0,
		area: ['500px', '200px'],
		title:'督办事项反馈',
		content: $("#layer_feedback"),
		btn: ['确定','关闭'],
	    success: function(layero, index){
	    	$.parser.parse("#layer_feedback");
	      },
		yes: function(index, layero){
			valid("${ctx}/target/aqhy/feedback/"+id, {"feedback":$("#feedback").textbox("getValue") },index );
		},
		cancel: function(index){ 
		}
	}); 
}
function delay(id) {
	confirmx("确定推迟会议吗？", function() {
		layer.open({
			type: 1, 
			zIndex :0,
			area: ['500px', '200px'],
			title:'推迟会议',
			content: $("#layer_delay"),
			btn: ['确定','关闭'],
		    success: function(layero, index){
		    	$.parser.parse("#layer_delay");
		    	var date=new Date();
		    	date.setHours(date.getHours() + 2);//当前时间推迟两小时
		    	$("#time").datetimebox("setValue", date.format("yyyy-MM-dd hh:00:00"));
		      },
			yes: function(index, layero){
				valid("${ctx}/target/aqhy/delay/"+id, {"time":$("#time").datetimebox("getValue"),"reason":$("#delayreason").textbox("getValue")}, index);
			},
			cancel: function(index){ 
			}
		}); 
	  });
	}

function valid(url,data,index){
	$.ajax({
		type:'post',
		url:url,
		data :data,
		async :false,
		success: function(data){
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
		}
	});
		dg.datagrid('reload');
		layer.close(index);//关闭对话框。
}
	//删除
	function del() {
		var row = dg.datagrid('getChecked');
		if (row == null || row == '') {
			layer.msg("请至少勾选一行记录！", {
				time : 1000
			});
			return;
		}

		var ids = "";
		for (var i = 0; i < row.length; i++) {
			if (ids == "") {
				ids = row[i].id;
			} else {
				ids = ids + "," + row[i].id;
			}
		}

		top.layer.confirm('删除后无法恢复您确定要删除？', {
			icon : 3,
			title : '提示'
		}, function(index) {
			$.ajax({
				type : 'get',
				url : ctx + "/target/aqhy/delete/" + ids,
				success : function(data) {
					layer.alert(data, {
						offset : 'rb',
						shade : 0,
						time : 2000
					});
					top.layer.close(index);
					dg.datagrid('reload');
					dg.datagrid('clearChecked');
					dg.datagrid('clearSelections');
				}
			});
		});

	}

	//弹窗修改
	function upd() {
		var row = dg.datagrid('getSelected');
		if (row == null) {
			layer.msg("请选择一行记录！", {
				time : 1000
			});
			return;
		}
		openDialog("修改安全会议信息", ctx + "/target/aqhy/update/" + row.id, "800px", "400px", "");
	}
	//弹窗修改
	function update(u) {
		openDialog("修改安全会议信息", ctx + "/target/aqhy/update/" + u, "800px", "400px", "");
	}
	
	

	//查看
	function view() {
		var row = dg.datagrid('getSelected');
		if (row == null) {
			layer.msg("请选择一行记录！", {
				time : 1000
			});
			return;
		}

		openDialogView("查看安全会议信息", ctx + "/target/aqhy/view/" + row.id, "800px", "400px", "");

	}

	//创建查询对象并查询
	function search() {
		var obj = $("#searchFrom").serializeObject();
		dg.datagrid('load', obj);
	}

	function reset() {
		$("#searchFrom").form("clear");
		var obj = $("#searchFrom").serializeObject();
		dg.datagrid('load', obj);
	}
</script>

</body>
</html>