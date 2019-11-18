<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
<title>jha风险评估管理</title>
<meta name="decorator" content="default" />
    <style>
        .cap{
            display: none;
            cursor:pointer;
        }
    </style>
</head>
<body >
   <form id="inputForm" class="form-horizontal">
      <table id="rwtable" class="table table-bordered">
        <%-- <c:if test="${action eq 'create' }">
            <tfoot>
               <tr>
                  <td align="center" colspan="4"><a class="btn btn-info btn-sm" data-toggle="tooltip" data-placement="left" onclick="addaction()"
                        title="新增活动">
                        <i class="fa fa-plus"></i>新增活动
                     </a></td>
               </tr>
            </tfoot>
         </c:if> --%>

         <!--
             Captain of team36  18/12/04
             input 勾选加样式
          -->
         <tbody class="w36">
         <tr class="w36">
            <td colspan="16" style="width: 100%;text-align: center"><input id="m2" name="m2" class="easyui-combobox" style="width: 100px;height: 30px;" value="${ndbb.m1 }" 
							data-options="required:'true', editable:false, panelHeight: '150px'" />年设备保养计划表</td>
         </tr>

         <tr>
            <td class="w7" style="width: 7%;text-align: center;"><label class="">部门名称</label></td>
            <td class="w17" style="width: 16%;text-align: center;"><input id="deptid" name="deptid" type="text" value="${entity.deptid }"
                class="easyui-combobox" style="width: 100%;height: 30px;" data-options="required:true,editable : false ,
              panelHeight:100,valueField:'id', textField:'text',url: '${ctx}/system/department/deptjson'"></td>
            <td colspan="14" class="w74" style="width: 74%;text-align: right;"><label class="">编制日期&nbsp;&nbsp;&nbsp;<input name="analysistime"
                id="analysistime" style="width: 100%;height: 30px;margin-left:10px" class="easyui-datebox" value="${entity.analysistime }" /></label></td>
         </tr>

         <tr>
            <td rowspan="2" class="w7" style="width: 7%;text-align: center;"><label class="">序号</label></td>
            <td rowspan="2" class="w17" style="width: 16%;text-align: center;"><label class="">设备编号</label></td>
            <td rowspan="2" class="w17" style="width: 16%;text-align: center;"><label class="">设备名称</label></td>
            <td rowspan="2" class="w17" style="width: 16%;text-align: center;"><label class="">型号与规格</label></td>
            <td colspan="12" class="w42" style="width: 42%;text-align: center;"><label class="">保养月份</label></td>
         </tr>

         <tr>
            <td class="w3-5" style="width: 3.5%;text-align: center;"><label class="">1</label></td>
            <td class="w3-5" style="width: 3.5%;text-align: center;"><label class="">2</label></td>
            <td class="w3-5" style="width: 3.5%;text-align: center;"><label class="">3</label></td>
            <td class="w3-5" style="width: 3.5%;text-align: center;"><label class="">4</label></td>

            <td class="w3-5" style="width: 3.5%;text-align: center;"><label class="">5</label></td>
            <td class="w3-5" style="width: 3.5%;text-align: center;"><label class="">6</label></td>
            <td class="w3-5" style="width: 3.5%;text-align: center;"><label class="">7</label></td>
            <td class="w3-5" style="width: 3.5%;text-align: center;"><label class="">8</label></td>

            <td class="w3-5" style="width: 3.5%;text-align: center;"><label class="">9</label></td>
            <td class="w3-5" style="width: 3.5%;text-align: center;"><label class="">10</label></td>
            <td class="w3-5" style="width: 3.5%;text-align: center;"><label class="">11</label></td>
            <td class="w3-5" style="width: 3.5%;text-align: center;"><label class="">12</label></td>
         </tr>

         <!-- content -->
         <c:forEach items="[30]" begin="0" step="1" var="item" varStatus="status">
         <tr>
             <td class="w7" style="width: 7%;text-align: center;">status.count</td>
             <td class="w17" style="width: 16%;text-align: center;">沿着路灯</td>
             <td class="w17" style="width: 16%;text-align: center;">一个人</td>
             <td class="w17" style="width: 16%;text-align: center;">走回家</td>

             <td class="w3-5 inp" style="width: 3.5%;text-align: center;cursor: pointer"><input class="cap" type="checkbox" value="1" style="display: none"/></td>
             <td class="w3-5 inp" style="width: 3.5%;text-align: center;cursor: pointer"><input class="cap" type="checkbox" value="2" style="display: none"/></td>
             <td class="w3-5 inp" style="width: 3.5%;text-align: center;cursor: pointer"><input class="cap" type="checkbox" value="3" style="display: none"/></td>
             <td class="w3-5 inp" style="width: 3.5%;text-align: center;cursor: pointer"><input class="cap" type="checkbox" value="4" style="display: none"/></td>

             <td class="w3-5 inp" style="width: 3.5%;text-align: center;cursor: pointer"><input class="cap" type="checkbox" value="5" style="display: none"/></td>
             <td class="w3-5 inp" style="width: 3.5%;text-align: center;cursor: pointer"><input class="cap" type="checkbox" value="6" style="display: none"/></td>
             <td class="w3-5 inp" style="width: 3.5%;text-align: center;cursor: pointer"><input class="cap" type="checkbox" value="7" style="display: none"/></td>
             <td class="w3-5 inp" style="width: 3.5%;text-align: center;cursor: pointer"><input class="cap" type="checkbox" value="8" style="display: none"/></td>

             <td class="w3-5 inp" style="width: 3.5%;text-align: center;cursor: pointer"><input class="cap" type="checkbox" value="9" style="display: none"/></td>
             <td class="w3-5 inp" style="width: 3.5%;text-align: center;cursor: pointer"><input class="cap" type="checkbox" value="10" style="display: none"/></td>
             <td class="w3-5 inp" style="width: 3.5%;text-align: center;cursor: pointer"><input class="cap" type="checkbox" value="11" style="display: none"/></td>
             <td class="w3-5 inp" style="width: 3.5%;text-align: center;cursor: pointer"><input class="cap" type="checkbox" value="12" style="display: none"/></td>
         </tr>
         </c:forEach>

         <!-- content --><%--
         <c:if test="${list.length > 0}">
             <c:forEach items="list" begin="0" step="1" var="item" varStatus="status">
                 <tr>
                     <td class="w7" style="width: 7%;text-align: center;">${status.count}</td>
                     <td class="w17" style="width: 16%;text-align: center;">${item.a}</td>
                     <td class="w17" style="width: 16%;text-align: center;">${item.b}</td>
                     <td class="w17" style="width: 16%;text-align: center;">${item.c}</td>

                     <td class="w3-5 inp" style="width: 3.5%;text-align: center;cursor: pointer"><input class="input" name="cap${status.count}" type="checkbox" value="1" style="display: none"/></td>
                     <td class="w3-5 inp" style="width: 3.5%;text-align: center;cursor: pointer"><input class="input" name="cap${status.count}" type="checkbox" value="2" style="display: none"/></td>
                     <td class="w3-5 inp" style="width: 3.5%;text-align: center;cursor: pointer"><input class="input" name="cap${status.count}" type="checkbox" value="3" style="display: none"/></td>
                     <td class="w3-5 inp" style="width: 3.5%;text-align: center;cursor: pointer"><input class="input" name="cap${status.count}" type="checkbox" value="4" style="display: none"/></td>

                     <td class="w3-5 inp" style="width: 3.5%;text-align: center;cursor: pointer"><input class="input" name="cap${status.count}" type="checkbox" value="5" style="display: none"/></td>
                     <td class="w3-5 inp" style="width: 3.5%;text-align: center;cursor: pointer"><input class="input" name="cap${status.count}" type="checkbox" value="6" style="display: none"/></td>
                     <td class="w3-5 inp" style="width: 3.5%;text-align: center;cursor: pointer"><input class="input" name="cap${status.count}" type="checkbox" value="7" style="display: none"/></td>
                     <td class="w3-5 inp" style="width: 3.5%;text-align: center;cursor: pointer"><input class="input" name="cap${status.count}" type="checkbox" value="8" style="display: none"/></td>

                     <td class="w3-5 inp" style="width: 3.5%;text-align: center;cursor: pointer"><input class="input" name="cap${status.count}" type="checkbox" value="9" style="display: none"/></td>
                     <td class="w3-5 inp" style="width: 3.5%;text-align: center;cursor: pointer"><input class="input" name="cap${status.count}" type="checkbox" value="10" style="display: none"/></td>
                     <td class="w3-5 inp" style="width: 3.5%;text-align: center;cursor: pointer"><input class="input" name="cap${status.count}" type="checkbox" value="11" style="display: none"/></td>
                     <td class="w3-5 inp" style="width: 3.5%;text-align: center;cursor: pointer"><input class="input" name="cap${status.count}" type="checkbox" value="12" style="display: none"/></td>
                 </tr>
             </c:forEach>
         </c:if>--%>
         </tbody>
      </table>

    <div class="easyui-accordion" id="accordion" border="false">
       <!-- <div title="风险活动" data-options="selected:true" style="padding:10px;">
           <table id="fxhd_dg"></table>
       </div> -->
    </div>
   </form>
   <script type="text/javascript">
       /*
            cap
            checkbox 选中加样式
        */
       $(document).ready(function () {
           /*
           $('.input').click(function(e){
               $(this).find(':checkbox').click();
               if($(this).find(':checkbox').is(':checked')){
                   $(this).css('background-color','rgb(36, 198, 200)');
               }else{
                   $(this).css('background-color','#FFF');
               }
           });
           */

           $('.inp').click(function(e){
               $(this).find(':checkbox').click();
               if($(this).find(':checkbox').is(':checked')){
                   $(this).css('background-color','rgb(36, 198, 200)');
               }else{
                   $(this).css('background-color','#FFF');
               }
           });
       });
       
       $(function(){
       	   var curYear = new Date().getFullYear();
	       var data = [];
	       for (var  i = (curYear - 3); i < (curYear + 3); i++) {
		       data.push({value: i, text: i});
	       }
	       $("#m2").combobox('loadData', data);
       	
       })


				var time;
				var dgdata = [];
				var dg;
				var data;//datagrid参数
				var action = '${action}';
				$(function() {
					data = {
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
							field : 'workstep',
							title : '工作步骤',
							sortable : false,
							width : 100
						}, {
							field : 'potentialhazard',
							title : '潜在危害',
							sortable : false,
							width : 100
						}, {
							field : 'mainresult',
							title : '主要后果',
							sortable : false,
							width : 100
						}, {
							field : 'safetymeasure',
							title : '现有安全措施',
							sortable : false,
							width : 100
						}, {
							field : 'possibility',
							title : '可能性(等级)',
							sortable : false,
							width : 50
						},  {
							field : 'exposefrequency',
							title : '暴露频率(等级)',
							sortable : false,
							width : 50
						}, {
							field : 'severity',
							title : '严重度(等级)',
							sortable : false,
							width : 50
						}, {
							field : 'riskvalue',
							title : '风险值',
							sortable : false,
							width : 50
						}, {
							field : 'risklevel',
							title : '风险等级',
							sortable : false,
							width : 50
						}, {
							field : 'improvemeasure',
							title : '改进措施',
							sortable : false,
							width : 100
						}, {
							field : 'operation',
							title : '操作',
							sortable : false,
							width : 50,
							formatter : function(value, row) {
								return "<a class='btn btn-warning btn-xs' onclick='updaction(" + JSON.stringify(row) + ")'>修改</a>"
								+ "<a class='btn btn-danger btn-xs' onclick='deleterow(" + row.time + ")'>删除</a>";
							}
						}

						] ],
						onLoadSuccess : function() {
						},
						checkOnSelect : false,
						selectOnCheck : false
					};
					if (action == 'create') {
						$("#analysisper").textbox("setValue", '${username}');
						$("#analysistime").datebox("setValue", new Date().format("yyyy-MM-dd"));
					}
				});
				function deleterow(rowtime) {
					top.layer.confirm('删除后无法恢复您确定要删除？', {icon: 3, title:'提示'}, function(tmpindex){
						for ( var index in dgdata) {
							if (dgdata[index].time == rowtime) {
								dgdata.splice(index, 1);
							}
						}
						dg.datagrid("loadData", dgdata);
						if (dgdata.length == 0) {
							$('#accordion').accordion('remove', 0);
						}
						top.layer.close(tmpindex);
					});
				}
				function addaction() {
					openDialog("添加风险活动信息", ctx + "/fxpg/jha/actioncreate/", "800px", "400px", "");
				}
				function updaction(row) {
					time = row.time;
					openDialog("修改风险活动信息", ctx + "/fxpg/jha/actionupdate?time=" + row.time + "&data=" + encodeURIComponent(JSON.stringify(row)), "800px", "400px", "");
				}
				var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
				function doSubmit() {
					var obj = $("#inputForm").serializeObject();
					var data = (action == 'create') ? {
						"list" : JSON.stringify(dgdata),
						"entity" : JSON.stringify(obj)
					} : obj;
					/* console.log("list:" +JSON.stringify(dgdata)+", entity:"+JSON.stringify(obj)); */
					$.ajax({
						type : 'POST',
						url : "${ctx}/fxpg/jha/" + action,
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