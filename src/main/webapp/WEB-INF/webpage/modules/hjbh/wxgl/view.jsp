<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
<title>危险废物特性管理</title>
<meta name="decorator" content="default" />
<script type="text/javascript">
	
</script>
</head>
<body>
   <table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
			  <tbody>
				<tr>
					<td class="width-15 active"><label class="pull-right">废物名称：</label></td>
					<td class="width-35"><input type="text" id="name" name="name" class="easyui-textbox" value="${entity.name}" readonly="readonly"  style="height: 30px;width: 100%;" data-options="required:'true'"/></td>
					<td class="width-15 active"><label class="pull-right">废物类别：</label></td>
					<td class="width-35"><input type="text" id="kind" name="kind" class="easyui-textbox" value="${entity.kind}" readonly="readonly"   style="height: 30px;width: 100%" data-options="required:'true'"/></td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">主要危险性：</label></td>
					<td class="width-35"><input data-options=" multiple:true,data: [
										{value:'1',text:'腐蚀性'},
								        {value:'2',text:'急性毒性'},
								        {value:'3',text:'浸出毒性'},
								        {value:'4',text:'易燃性'},
								        {value:'5',text:'反应性'},
								        {value:'6',text:'含毒性物质'},
								        {value:'7',text:'传染性物质'}
								        ]" type="text" id="danger_type" name="danger_type" class="easyui-combobox" value="${entity.danger_type}" readonly="readonly" style="height: 30px;width: 100%" />
					</td>
					<td class="width-15 active"><label class="pull-right">主要化学组成：</label></td>
					<td class="width-35"><input data-options="" type="text" id="content" name="content" class="easyui-textbox" value="${entity.content}" readonly="readonly"  style="height: 30px;width: 100%" /></td>
				</tr>		
				<tr>
					<td class="width-15 active"><label class="pull-right">废物表现形态：</label></td>
					<td class="width-35"><input data-options="data: [
										{value:'1',text:'固态'},
								        {value:'2',text:'半固态'},
								        {value:'3',text:'液态'},
								        {value:'4',text:'气态'}
								        ]" type="text" id="express_type" name="express_type" class="easyui-combobox" value="${entity.express_type}" readonly="readonly"  style="height: 30px;width: 100%" />
					</td>
					<td class="width-15 active"><label class="pull-right">贮存方式：</label></td>
					<td class="width-35"><input data-options="data: [
										{value:'1',text:'圆桶'},
								        {value:'2',text:'槽罐'},
								        {value:'3',text:'编织袋'}
								        ]" type="text" id="store_type" name="store_type" class="easyui-combobox" value="${entity.store_type}" readonly="readonly"  style="height: 30px;width: 100%" />
					</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">是否提供委托外单位利用处理：</label></td>
					<td class="width-35"><input type='radio' id="other_handler" name='other_handler' readonly="readonly" value='1' class='i-checks' checked='checked' />是
					                    <input type='radio' id="other_handler1" name='other_handler' readonly="readonly" value='0' class='i-checks' />否
					</td>
				     <td class="width-15 active"><label class="pull-right">单位内部处置方法描述：</label></td>
				     <td class="width-35"><input data-options="required:'true'" type="text" id="description" name="description" class="easyui-textbox" value="${entity.description}"  style="height: 30px;width: 100%" disabled="disabled"/></td>
					</div>
				</tr>
				<tr>
				  <c:if test="${action eq 'create'}">
				    <td align="center" colspan="4">
						<a name="addOtherDw" class="btn btn-info btn-sm" data-toggle="tooltip" data-placement="left" onclick="addOtherDw()" title="添加外单位"><i class="fa fa-plus"></i> 添加外单位</a>
					</td>	
				 </c:if>
				</tr>
				</tbody>
			</table>
   <div class="easyui-accordion" id="accordion" border="false">
      <div title="外单位信息" data-options="selected:true" style="padding:10px;">
         <table id="otherdw_dg"></table>
      </div>
   </div>
   <script type="text/javascript">
				$(function() {
				    //根据数据库中的值来为单选按钮添加属性
					if ('${entity.other_handler}' == 0) {
						$('#other_handler1').iCheck('check');
					} else if ('${entity.other_handler}' == 1) {
						$('#other_handler').iCheck('check');
					}
					var d = $('#otherdw_dg').datagrid({
					url : ctx + "/hjbh/otherDw/list/" + '${entity.ID}',
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
						field : 'name',
						title : '单位名称',
						sortable : false,
						width : 100
					}, {
						field : 'location',
						title : '所在地',
						sortable : false,
						width : 100
					}, {
						field : 'permit_num',
						title : '危险废物经营许可证',
						sortable : false,
						width : 80
					}, {
						field : 'description',
						title : '方法描述',
						sortable : false,
						width : 100
					}, {
						field : 'contact_name',
						title : '联系人',
						sortable : false,
						width : 50
					}, {
						field : 'contact_phone',
						title : '联系方式',
						sortable : false,
						width : 50
					}

					] ],
					onLoadSuccess : function() {
					},
					checkOnSelect : false,
					selectOnCheck : false
				});
			});
			</script>
</body>
</html>