<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
<title>发放标准管理</title>
<meta name="decorator" content="default" />
<style type="text/css">
.textred {
   color: red;
}
</style>
</head>
<body>
	<form id="inputForm" action="${ctx}/lbyp/ffbz/${action}" method="post" class="form-horizontal">
		<table id="rwtable" class="table table-bordered">
			<c:if test="${action eq 'create' }">
			<tfoot>
				<tr>
				<td align="center" colspan="4">
                  <a class="btn btn-info btn-sm" data-toggle="tooltip" data-placement="left"
						onclick="addtr()" title="其他用品"><i class="fa fa-plus"></i>添加用品</a>
                  <a class="btn btn-primary btn-sm" data-toggle="tooltip" data-placement="left"
                   onclick="viewtemplate()" title="查看省内标准"><i class="fa fa-search-plus"></i>查看省内标准</a>
                  <!-- <a class="btn btn-success btn-sm" data-toggle="tooltip" data-placement="left"
                  onclick="exintemplate()" title="导入省内标准"><i class="fa fa-plus"></i>导入省内标准</a> --></td>
				</tr>
			</tfoot>
			</c:if>
			<tbody>
				<tr>
					<td class="width-15 active"><label class="pull-right">工种(岗位)名称：</label></td>
					<td class="width-85" colspan="3"><input name="jobtype" id="jobtype" style="width: 100%;height: 30px;"
						class="easyui-combobox" value="${entity.jobtype }"
						data-options="required:'true',panelHeight:'100',valueField: 'text',textField: 'text',url: '${ctx}/tcode/dict/gwgz' " /></td>
               </tr>
            <c:if test="${action eq 'update'}">
				<tr>
					<td class="width-15 active"><label class="pull-right">用品名称：</label></td>
					<td class="width-35" colspan="3"><input name="goodsname" id="goodsname" style="width: 100%;height: 30px;"
						class="easyui-textbox" value="${entity.goodsname }"
						data-options="required:true" /></td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">发放数量：</label></td>
					<td class="width-35"><input name="amount" id="amount" style="width: 100%;height: 30px;" class="easyui-textbox"
						value="${entity.amount }" data-options=" required : true,validType:'number' " /></td>
					<td class="width-15 active"><label class="pull-right">发放周期：</label></td>
					<td class="width-35"><input
						id="cyclemonth" name="cyclemonth" style="width: 90%;height: 30px;" class="easyui-textbox"
						value="${entity.cyclemonth}"
						data-options="required:'true',validType:'integer'" />&nbsp;&nbsp;月
						</td>
				</tr> 
            </c:if>

				<c:if test="${not empty entity.ID}">
					<input type="hidden" name="ID" value="${entity.ID}" />
					<input type="hidden" name="ID1" value="${entity.ID1}" />
					<input type="hidden" name="S1" value="<fmt:formatDate value="${entity.s1}" pattern="yyyy-MM-dd HH:mm:ss"  />" />
					<input type="hidden" name="S3" value="${entity.s3}" />
				</c:if>
			</tbody>
		</table>
      <div class="easyui-accordion" id="accordion" border="false">
      </div>
	</form>
	<script type="text/javascript">
		var count = 0;
		//手动添加劳保用品
		function addtr() {
			if(!$("#jobtype").combobox("getValue")){
				layer.msg("请选择工种");
				return ;
			}
			count++;
			var html = "<tr class='delete' name='tr"+count+"'><td class='width-15 active'>"
					+ "<label class='pull-right'><a class='fa fa-times-circle btn-danger btn-outline' onclick='removeadd("+count+")'></a>  用品名称：</label></td>"
					+ "<td class='width-35' colspan='3'>"
					+ "<input name='goodsname' id='goodsname"+count+"' style='width: 100%;height: 30px;' class='easyui-combobox'  data-options=\"required:'true',panelHeight:'100',valueField: 'text',textField: 'text',url: '${ctx}/tcode/dict/lbyp' \" /></td></tr>"
					+ "<tr class='delete' name='tr"+count+"'><td class='width-15 active'>"
					+ "<label class='pull-right'>发放数量：</label></td>"
					+ "<td class='width-35'>"
					+ "<input name='amount' style='width: 100%;height: 30px;' class='easyui-textbox'value='1' data-options=\" required : true,validType:'number'\"/></td>"
					+ "<td class='width-15 active'>"
					+ "<label class='pull-right'>发放周期：</label>"
					+ "</td><td class='width-35'>"
					+"<input name='cyclemonth' data-options=\"required:true,validType:'integer'\" style='width: 90%;height: 30px;' class='easyui-textbox' />&nbsp;&nbsp;月</td>"
					+ "</tr>";
			var $list = $("#rwtable tr").eq(-1);
			$list.after(html);
			$.parser.parse("[name='tr"+count+"']");
		}

		//工种选择后
		$("#jobtype").combobox({
			onSelect : function(rec) {
				$(".delete").remove();
				$.ajax({
					  url: "${ctx}/lbyp/ffbz/matchtemplate",
					  type: 'POST',
					  data: {"jobname":rec.text},
					  success: function(data){
						  deal(data);
					  },
					  dataType: "json"
					});
			}
		});

		//自动添加江苏省标准劳保用品发放
		 function deal(data){
			 if($('#accordion').accordion('getPanel',0))
					$('#accordion').accordion('remove',0);
			 if(data.length>0){
				var html="";
				var gval="";
				for(var index in data){
					var textred="";
					html += "<tr name='templatetr"+index+"'><td class='width-15 active'>"
					+ "<label class='pull-right '><a class='fa fa-times-circle btn-danger btn-outline' onclick='remove("+index+")'></a>  用品名称：</label></td>"
					+ "<td class='width-35' colspan='3'>"
					+ "<input name='goodsname' id='templateid2"+index+"' style='width: 100%;height: 30px;' class='easyui-textbox' value='"+data[index].goodsname+"' data-options=\"readonly:'true'\" /></td></tr>"
					+ "<tr  name='templatetr"+index+"'><td class='width-15 active'>"
					+ "<label class='pull-right '>发放数量：</label></td>"
					+ "<td class='width-35'>"
					+ "<input name='amount' style='width: 100%;height: 30px;' class='easyui-textbox' value='1' data-options=\" required : true,validType:'number'\"/></td>"
					+ "<td class='width-15 active'>"
					+ "<label class='pull-right '>发放周期：</label>"
					+ "</td><td class='width-35'>"
					+"<input name='cyclemonth' data-options=\"required:true,validType:'integer'\" style='width: 90%;height: 30px;' class='easyui-textbox' value='"+data[index].cyclemonth+"' />&nbsp;&nbsp;月</td>"
					+ "</tr>";
				}
				$('#accordion').accordion({height : 'auto'});//调整datagrid高度
				$('#accordion').accordion('add', {
					title: '江苏省标准(红色用品为本公司缺少的标准用品，可根据公司实际情况修改，添加标准以外的用品点击上方添加按钮)',
					content: '<table class="table table-bordered">'+html+'</table>',
					selected: true,
				});
			 }
			
		 }
		 //移除省内标准生成的物品列
		function remove(index){
			var val=$("#templateid2"+index).textbox("getValue");
			$("tr[name='templatetr"+index+"']").remove();
		}

        //移除手动添加生成的物品列
		function removeadd(index){
			$("tr[name='tr"+index+"']").remove();
		}

		//查看省内标准
		function viewtemplate(){
			if(!$("#jobtype").combobox("getValue")){
				layer.msg("请选择工种");
				return ;
			}
			var jobname=$("#jobtype").combobox("getText");
			top.layer.open({
				type: 2,  
				content: ctx+"/lbyp/ffbz/viewtemplate/"+jobname,
				area: ['900px', '360px'],
				title:false,
				closeBtn :0,
				btn: ['关闭']
			});
		}

		//验证是否有添加重复的物品
		function validGoods(){
			 var goods=$("[name='gooodsname']");
			 var goodsvalues=[];
			 var valid=true;
			 for(var i=0;i<goods.length;i++){
				 for(var index in goodsvalues){
					 if(goodsvalues[index]==goods[i].value){
						 valid=false;
						 break ;
					 }
				 }
				 goodsvalues.push(goods[i].value);
			 }
			 return valid;
		}
		var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
		function doSubmit() {
			$("#inputForm").submit();
		}
		$(function() {
			var flag = true;
			$('#inputForm').form({
				onSubmit : function() {
					 if(!validGoods()){
					 	layer.msg("添加了重复用品，请检查");
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
							content : '操作成功',
							shade : 0,
							time : 5000
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
	</script>


</body>
</html>