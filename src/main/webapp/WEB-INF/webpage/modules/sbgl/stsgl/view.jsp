<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
<title>三同时管理</title>
<meta name="decorator" content="default" />
<script type="text/javascript">
	
</script>
</head>
<body>
   <table id="tablecont" class="table table-bordered dataTable" style="margin:auto;">
      <tbody>
         <tr>
            <td class="width-20 active"><label class="pull-right">项目名称：</label></td>
            <td class="width-30" colspan="3"><div>${entity.projectname }</div></td>
         </tr>
         <tr>
            <td class="width-20 active"><label class="pull-right">项目简介：</label></td>
            <td class="width-30" colspan="3"><div>${entity.projectproduce }</div></td>
         </tr>
         <tr>
            <td class="width-20 active"><label class="pull-right">批准部门：</label></td>
            <td class="width-30"><div>${entity.approvedept }</div></td>
            <td class="width-20 active"><label class="pull-right">批准日期：</label></td>
            <td class="width-30"><div>
                  <fmt:formatDate value="${entity.approvetime}" pattern="yyyy-MM-dd" />
               </div></td>
         </tr>
         <tr>
            <td class="width-20 active"><label class="pull-right">建设日期：</label></td>
            <td class="width-30"><div>
                  <fmt:formatDate value="${entity.constructiontime}" pattern="yyyy-MM-dd" />
               </div></td>
            <td class="width-20 active"><label class="pull-right">完成日期：</label></td>
            <td class="width-30"><div>

                  <fmt:formatDate value="${entity.finishtime}" pattern="yyyy-MM-dd" />
               </div></td>
         </tr>
      </tbody>
   </table>
   <div class="easyui-accordion" id="accordion" border="false">
      <div title="附件信息" data-options="selected:true" style="padding:10px;">
         <table id="file_dg"></table>
      </div>
   </div>
   <script type="text/javascript">
   $(function(){

	var d = $('#file_dg').datagrid({
    	url :ctx+"/sbgl/stsgl/filelist/" + '${entity.ID}',
    	fitColumns : true,
		border : true,
		striped : true,
		rownumbers : false,
		nowrap : false,
		idField : 'id',
		scrollbarSize : 0,
		singleSelect : true,
		striped : true,
		columns : [ [ {
			field : 'type',
			title : '文件类型',
			sortable : false,
			width : 100,
			align : 'center',
			formatter: function(value){
				var type;
				if(value==1){
					type = "立项审批文件";
				}else if(value==2){
					type ="可行性研究报告";
				}else if(value==3){
					type ="预评价报告";
				}else if(value==4){
					type ="安全设施设计";
				}else if(value==5){
					type ="项目试生产方案";
				}else if(value==6){
					type ="安全设施竣工验收";
				}else if(value==7){
					type ="设计、施工、监理单位的相关资质";
				}else{
					type ="其他材料";
				}
				return type;
			}
		}, {
			field : 'contjson',
			title : '文件基本信息',
			sortable : false,
			width : 200,
			formatter: function(value){
				return value.replace("}","").replace("{","").replace(/\"/g,"");	
			}
		}, {
			field : 'url',
			title : '文件名称',
			sortable : false,
			width : 100,
			align : 'center',
			formatter: function(value){
				var html="";
				if(value){
        			var sp = value.split("||");
        			html="<a class='fa fa-file-word-o btn-danger btn-outline' target='_blank' style='margin:1px auto' href='"+sp[0]+"'> "+sp[1]+"</a>"; 
        		}
        		return html; 
			}
		}
		] ],
		checkOnSelect : false,
		selectOnCheck : false,
		toolbar:'#tb'
    });
   });
</script>
</body>
</html>