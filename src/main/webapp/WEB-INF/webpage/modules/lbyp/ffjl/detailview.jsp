<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
<title>劳保用品发放记录信息</title>
<meta name="decorator" content="default" />
<script type="text/javascript"
	src="${ctx}/static/model/js/lbyp/ffjl/viewindex.js?v=1.0"></script>
</head>
<body>
	<!-- 工具栏 -->
	<div id="lbyp_ffjl_tb" style="padding:5px;height:auto">
		<form id="searchFrom" action="" style="margin-bottom: 8px;"
			class="form-inline">

			<div class="form-group">
				<input type="text" name="view_goods_name" class="easyui-textbox" style="height: 30px;" data-options="prompt: '用品名称'" />
				领取时间 ：
				 <input type="text" name="view_starttime" class="easyui-datebox" style="height: 30px;" data-options="prompt: '开始时间'" />至
				 <input type="text" name="view_endtime" class="easyui-datebox" style="height: 30px;" data-options="prompt: '结束时间'" />
				 <span class="btn btn-primary btn-rounded btn-outline btn-sm" onclick="search()"><i class="fa fa-search"></i> 查询</span>
				 <span class="btn btn-primary btn-rounded btn-outline btn-sm" onclick="reset()"><i class="fa fa-refresh"></i> 全部</span>
			</div>
		</form>
	</div>


	<table id="dgdata"></table>
	<script type="text/javascript">
		
	</script>
</body>
</html>