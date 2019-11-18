<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
<title>劳保用品发放管理</title>
<meta name="decorator" content="default" />
<script type="text/javascript" src="${ctx}/static/model/js/lbyp/ffgl/index.js?v=1.1"></script>
<style type="text/css">
 body .self-class .layui-layer-btn .layui-layer-btn2{background:#23C6C8;color:white }
</style>
</head>
<body>
   <!-- 工具栏 -->
   <div id="lbyp_ffgl_tb" style="padding:5px;height:auto">
      <form id="searchFrom" action="" style="margin-bottom: 8px;" class="form-inline">

         <div class="form-group">
            <%-- <input name="employeename" class="easyui-combobox" style="height: 30px;"
                    data-options="prompt: '部门名称',panelHeight:100,valueField:'text', textField:'text',url: '${ctx}/system/department/deptjson'"> --%>
            <input type="text" name="job_name" class="easyui-combobox" style="height: 30px;"
               data-options="prompt: '岗位（工种）名称',panelHeight:'100',valueField: 'text',textField: 'text',url:'${ctx}/tcode/dict/gwgz'" />
            <input type="text" name="employeename" class="easyui-textbox" style="height: 30px;"
               data-options="prompt: '员工姓名'" />
            <input type="text" id="expiration" name="expiration" style="height:30px" class="easyui-combobox"
               data-options="panelHeight: 'auto' ,prompt: '领取状况',editable:false,data: [
								       {value:'0',text:'已领取'},
								       {value:'1',text:'待领取'}]" />
            <span class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="search()"><i class="fa fa-search"></i> 查询</span> <span
               class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="reset()"><i class="fa fa-refresh"></i> 全部</span>
         </div>
      </form>
        <shiro:hasPermission name="lbyp:ffgl:add">
               <button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="addAll()" title="添加">
                  <i class="fa fa-plus"></i> 批量发放
               </button>
            </shiro:hasPermission>
   </div>
   <table id="lbyp_ffgl_dg"></table>

   <script type="text/javascript">
		var qyid = '${qyid}';
		var expirationcount = '${expirationcount}';
	</script>
</body>
</html>