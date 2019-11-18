<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>相关方评定信息设置</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript" src="${ctx}/static/model/js/zyaqgl/xggl/xgfpd/setting/index.js?v=1.1"></script>
</head>
<body >
<!-- 工具栏 -->
<div id="zyaqgl_xgfpd_setting_tb" style="padding:5px;height:auto">
      <div class="row">
         <div class="col-sm-12">
            <div class="pull-left">
               <span id="divper">
                  <button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="add()" title="评定">
                     <i class="fa fa-file-text-o"></i> 添加
                  </button>
                  <button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="upd()" title="修改">
                     <i class="fa fa-file-text-o"></i> 修改
                  </button>
                  <button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="del()" title="删除">
                     <i class="fa fa-trash-o"></i> 删除
                  </button>
               </span>
            </div>
         </div>
      </div>

   </div>
<table id="zyaqgl_xgfpd_setting_dg"></table> 
</body>
</html>