<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>二道门实时监测</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript" src="${ctx}/static/model/js/zxjkyj/edm/index.js?v=1.0"></script>
</head>
<body >
<!-- 安监和集团公司界面 -->
<c:if test="${usertype != '1'}">
	<div id="zxjkyj_edm_tb" style="padding:5px;height:auto">
		<div class="row">
			<div class="col-sm-12">

				<div class="pull-left">
					<form id="searchFrom" action="" style="margin-bottom: 8px;" class="form-inline">
						<input type="text" id="ssjc_edm_qy_name" name="ssjc_edm_qy_name" class="easyui-combobox"  style="height: 30px;width:250px;" data-options="editable:true ,valueField: 'text',textField: 'text',url:'${ctx}/zxjkyj/edm/qyjson',prompt: '企业名称',
							onLoadSuccess:function(data){
								var text='接入实时数据企业数：'+data.length;
								$('#shuzi').append(text);
							}
						 "/>
					</form>
				</div>

				<div class="pull-left">
					<button  class="btn btn-info btn-sm" onclick="search()" ><i class="fa fa-search"></i> 查询</button>
					<button  class="btn btn-danger btn-sm" onclick="reset()" ><i class="fa fa-refresh"></i> 全部</button>
					<button class="btn btn-primary btn-sm" onclick="refresh()"><i class="glyphicon glyphicon-repeat"></i> 刷新</button>
					<label id="shuzi" style="font-size: 15px;color:#FF00FF"></label>
				</div>
			</div>
		</div>
	</div>

	<table id="zxjkyj_edm_dg"></table>


	<div id="selectQyPanel" style="display: none;width: 100%;height: 100%;">
		<table id="qyDatagrid">	</table>
	</div>
</c:if>

<!-- 企业界面（非集团公司） -->
<c:if test="${usertype == '1'}">
	<div id="zxjkyj_edm_tb" style="padding:5px;height:auto">
		<div class="row">
			<div class="col-sm-12">
				<div class="pull-left">
					<form id="searchFrom" action="" style="margin-bottom: 8px;" class="form-inline">
						<input type="text" id="ygcode" name="ygcode" style="height: 30px;" class="easyui-textbox" data-options="prompt: '员工工号'"/>
						<input type="text" id="ygname" name="ygname" style="height: 30px;width: 120px;" class="easyui-textbox" data-options="prompt: '员工姓名'"/>
						<input type="text" id="edmname" name="edmname" style="height: 30px;" class="easyui-textbox" data-options="prompt: '二道门名称'"/>
						<input name="starttime" class="easyui-datebox"  style="height: 30px;width: 120px;" data-options="editable:false,prompt: '采集时间'" />~
						<input name="finishtime" class="easyui-datebox"  style="height: 30px;width: 120px;" data-options="editable:false,prompt: '采集时间'" />
					</form>
				</div>
				<div class="pull-left">
					<button  class="btn btn-info btn-sm" onclick="search()" ><i class="fa fa-search"></i> 查询</button>
					<button  class="btn btn-danger btn-sm" onclick="reset()" ><i class="fa fa-refresh"></i> 全部</button>
					<button class="btn btn-primary btn-sm" onclick="refresh()"><i class="glyphicon glyphicon-repeat"></i> 刷新</button>
					<label id="shuzi1" style="font-size: 15px;color:#FF00FF"></label>
				</div>
			</div>
		</div>
	</div>

	<table id="zxjkyj_edm_dg"></table>
</c:if>

<script>
	var usertype = '${usertype}';
	$(function(){
		$.ajax({
			type: 'POST',
			url: ctx + '/zxjkyj/edm/tj',
			dataType: 'json',
			success: function(data) {
				var text='<strong>当前进厂人次：'+ data.jcCount +' 当前出厂人次：'+ data.ccCount +' 当前在场人员数量：'+ data.zcCount + '</strong>';
				$('#shuzi1').html(text);
			}
		})
	})

</script>
</body>
</html>