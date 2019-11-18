<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
<title>添加字段</title>
<meta name="decorator" content="default" />
</head>
<body>
   <form id="inputForm" class="form-horizontal">
      <table id="rwtable" class="table table-bordered">
         <tbody>
            <tr>
               <td class="width-15 active"><label class="pull-right">字段英文名：</label></td>
               <td class="width-35"><input name="col_ename" type="text" class="easyui-textbox" style="width: 100%;height: 30px;"
                     data-options="required:'true' , validType :['englishOrNum','FUN[ValidateName,\'字段名称重复\']']">
               <td class="width-15 active"><label class="pull-right">字段中文含义：</label></td>
               <td class="width-35"><input name="col_cname" style="width: 100%;height: 30px;" class="easyui-textbox"/></td>
            </tr>
            <tr>
               <td class="width-15 active"><label class="pull-right">数据类型：</label></td>
                <td class="width-35"><input id="col_datatype" name="col_datatype" type="text" class="easyui-combobox" style="width: 100%;height: 30px;"
                     data-options="valueField: 'value',textField: 'text',panelHeight:'auto',editable:false,
                     data:[{value :'varchar',text:'String'},{value :'int',text:'Integer'},{value :'bigint',text:'Long'},
                     {value :'datetime',text:'Timestamp'},{value :'float',text:'Float'},{value :'numeric(18,2)',text:'Double'}]"/>
                     <input type="hidden" id="col_propertytype" name="col_propertytype"/>
               <td class="width-15 active"><label class="pull-right">是否在list页面展示：</label></td>
               <td class="width-35"><input type="radio" value="true" class="i-checks" name="col_viewable" checked="checked"/>展示
                                    <input type="radio" value="false" class="i-checks" name="col_viewable"/>不展示</td></td>
               </td>
            </tr>
            <tr>
               <td class="width-15 active"><label class="pull-right">可否为空/必填项：</label></td>
               <td class="width-35"><input type="radio" value="true" class="i-checks" name="col_nullable" checked="checked"/>可为空 
                                    <input type="radio" value="false" class="i-checks" name="col_nullable"/>不为空</td></td>
               </td>
               <td class="width-15 active"><label class="pull-right">是否为查询条件：</label></td>
               <td class="width-35"><input type="radio" value="true" class="i-checks" name="col_serach"/>是
                                    <input type="radio" value="false" class="i-checks" name="col_serach" checked="checked" />否</td></td>
            </tr>
         </tbody>
      </table>
   </form>
   <script type="text/javascript">
   $(function(){
       $("#col_datatype").combobox({
	   		onSelect : function(rec){
	   		    $("#col_propertytype").val(rec.text);
	   		    if(rec.text=="String"){
	   				$("#datatype").remove();
	   				$('#rwtable').append("<tr id='datatype'> <td class='width-15 active'>"
	   					+"<label class='pull-right'>字符长度：</label></td> <td class='width-35'>"
	   					+"<input id='col_length' name='col_length' type='text' class='easyui-textbox' style='width: 100%;height: 30px;' data-options='required: true,validType:\"integ\"'>"
	   					+"<td class='width-15 active'><label class='pull-right'>显示类型：</label></td>"
	   					+"<td class='width-35'><input id='col_showtype' name='col_showtype' type='text' class='easyui-combobox' style='width: 100%;height: 30px;' data-options='valueField: \"value\",textField: \"text\",panelHeight:\"auto\",editable:false, "
	   					+"data:[{value :\"textbox\",text:\"Textbox 文本框\"},{value :\"combobox\",text:\"Combobox 下拉框\"},{value :\"combotree\",text:\"Combotree 下拉树\"},{value :\"combogrid\",text:\"Combogrid 下拉网格\"},{value :\"file\",text:\"文件附件\"},{value :\"image\",text:\"图片附件\"}]'> </tr>");
	   					$.parser.parse('#datatype'); // 解析
	   				    $("#col_showtype").combobox({
      	   			   		onSelect : function(rec){
      	   			   		    if(rec.value=="combobox"){
      	   			   				$('#rwtable').append("<tr id='combo_set'> <td class='width-15 active'><label class='pull-right'>多选：</label></td>"
      	   			   					+" <td class='width-35'><input type='radio' value='true' class='i-checks' name='col_multiple' />是 "
      	   			   					+"<input type='radio' value='false' class='i-checks' name='col_multiple' checked='checked'/>否</td></td> </td> "
      	   			   					+"<td class='width-15 active'><label class='pull-right'>可编辑：</label></td> <td class='width-35'>"
      	   			   					+"<input type='radio' value='true' class='i-checks' checked='checked' name='col_editable'/>是 "
      	   			   					+"<input type='radio' value='false' class='i-checks' name='col_editable'/>否</td></td> </tr>");
      	   			   			 	 $('input').iCheck({
      								    radioClass: 'iradio_square-green'
      								  }); 
      	   			   		    }else{
      	   			   				$("#combo_set").remove();
      	   			   		    }
      	   			   		}
	   		      		 });
	   				  $("#col_showtype").combobox("select","textbox");
	   		    }else if(rec.text=="Timestamp"){
      	   			$("#datatype").remove();
         			$("#combo_set").remove();
         			$('#rwtable').append("<tr id='datatype'> <td class='width-15 active'><label class='pull-right'>显示类型：</label></td>"
   					+"<td class='width-35'><input id='col_showtype' name='col_showtype' type='text' class='easyui-combobox' style='width: 100%;height: 30px;' data-options='valueField: \"value\",textField: \"text\",panelHeight:\"auto\",editable:false, "
   					+"data:[{value :\"datebox\",text:\"Datebox 日期框\"},{value :\"datetimebox\",text:\"Datetimebox 日期时间框\"}]'> </tr>");
   					$.parser.parse('#datatype'); // 解析
   					$("#col_showtype").combobox("setValue","datebox");
	   		    }else{
	   				$("#datatype").remove();
	   				$("#combo_set").remove();
	   		    }
	   		}
       });
       $("#col_datatype").combobox("select","varchar");
       
   });
   
   function ValidateName(val){
       for(var value of parent.G_coldata){
	   		if(value.col_ename==val){
	   		    return false;
	   		}
   		}
       return true;
   }
    var layerindex = parent.layer.getFrameIndex(window.name); //获取窗口索引
    function doSubmit(){
       	var isValid = $("#inputForm").form('validate');
       	if(!isValid)
       		return false;
       	var obj=$("#inputForm").serializeObject();
   		parent.G_coldata.push(obj);
   		parent.dg.datagrid("loadData",parent.G_coldata);
   		$("#inputForm").form("clear");
   		$("#datatype").remove();
   		$("[name='col_nullable']:first").iCheck('check');
   		$("[name='col_serach']:last").iCheck('check');
   		$("[name='col_viewable']:first").iCheck('check');
   		$("#col_datatype").combobox("select","varchar");
    }
    
   </script>
</body>
</html>