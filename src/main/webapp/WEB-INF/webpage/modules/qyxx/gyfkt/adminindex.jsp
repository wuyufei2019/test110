<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%><html>
<head>
<title>工艺方块图</title>
<meta name="decorator" content="default" />
<script type="text/javascript" src="${ctx}/static/model/js/qyxx/gyfkt/adminindex.js?v=1.1"></script>
</head>
<body>

   <div id="tb" style="padding:5px;height:auto">
      <div>
         <form id="searchFrom" class="form-inline" style="margin-bottom: 8px;">
            <input name="bis_gyfkt_qyname" class="easyui-combobox" style="height: 30px;"
               data-options="width:150 ,valueField: 'text' , textField: 'text',url:'${ctx}/bis/qyjbxx/idjson',prompt:'企业名称' " />
            <input type="text" name="bis_gyfkt_name" style="height: 30px;" class="easyui-textbox"data-options="prompt:'工艺名称'" />
         <span class="btn btn-primary btn-rounded btn-outline btn-sm" data-toggle="tooltip" data-placement="left" onclick="search()" title="查询">
            <i class="fa fa-search"></i> 查询
         </span>
         <span class="btn btn-primary btn-rounded btn-outline btn-sm" data-toggle="tooltip" data-placement="left" onclick="reset()" title="重置">
            <i class="fa fa-refresh"></i> 全部
         </span>
         </form>
         <div class="row">
            <div class="col-sm-12">
               <div class="pull-left">

                  <shiro:hasPermission name="bis:gyfkt:add">
                     <button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="add()" title="添加">
                        <i class="fa fa-plus"></i> 添加
                     </button>
                  </shiro:hasPermission>
                  <shiro:hasPermission name="bis:gyfkt:update">
                     <button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="upd()" title="修改">
                        <i class="fa fa-file-text-o"></i> 修改
                     </button>
                  </shiro:hasPermission>
                  <shiro:hasPermission name="bis:gyfkt:delete">
                     <button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="del()" title="删除">
                        <i class="fa fa-trash-o"></i> 删除
                     </button>
                  </shiro:hasPermission>
                  <shiro:hasPermission name="bis:gyfkt:view">
                     <button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="view()" title="查看">
                        <i class="fa fa-search-plus"></i> 查看
                     </button>
                  </shiro:hasPermission>
                  <button class="btn btn-white btn-sm " data-toggle="tooltip" data-placement="left" onclick="refresh()" title="刷新">
                     <i class="glyphicon glyphicon-repeat"></i> 刷新
                  </button>
               </div>
               <div class="pull-right">
                  
               </div>
            </div>
         </div>
      </div>
   </div>
   <table id="dg"></table>
</body>
</html>