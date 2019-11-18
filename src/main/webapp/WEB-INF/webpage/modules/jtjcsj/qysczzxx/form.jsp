<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp" %>
<html>
<head>
    <title>企业生产装置信息</title>
    <meta name="decorator" content="default"/>
    <script type="text/javascript" src="${ctxStatic}/common/GlobalProvinces_main.js"></script>
    <script type="text/javascript" src="${ctxStatic}/common/GlobalProvinces_extend.js"></script>
    <script type="text/javascript" src="${ctxStatic}/common/initLocation.js"></script>
    <script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=OTVG2uzqudiovwoHlFIs0P8Z3T7ID4K2"></script>
    <script type="text/javascript" src="${ctx}/static/common/initmap.js?v=1.1"></script>
    <script type="text/javascript">
        var usertype =${usertype};

        var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
        var validateForm;

        function doSubmit() {
            $("#inputForm").submit();
        }

        $(function () {
            var flag = true;
            $('#inputForm').form({
                onSubmit: function () {
                    var isValid = $(this).form('validate');
                    if (isValid && flag) {
                        flag = false;
                        $.jBox.tip("正在提交，请稍等...", 'loading', {opacity: 0});
                        return true;
                    }
                    return false;	// 返回false终止表单提交
                },
                success: function (data) {
                    $.jBox.closeTip();
                    if (data == 'success')
                        parent.layer.open({icon: 1, title: '提示', offset: 'rb', content: '操作成功！', shade: 0, time: 2000});
                    else
                        parent.layer.open({icon: 2, title: '提示', offset: 'rb', content: '操作失败！', shade: 0, time: 2000});
                    parent.dg.datagrid('reload');
                    parent.layer.close(index);//关闭对话框。
                }
            });

        });

    </script>
</head>
<body>

<form id="inputForm" action="${ctx}/jtjcsj/qysczzxx/${action}" method="post" class="form-horizontal">
    <table class="table table-bordered  table-condensed dataTables-example dataTable no-footer">
        <tbody>
        <c:if test="${usertype != '1' and action eq 'create'}">
            <tr>
                <td class="width-20 active"><label class="pull-right">企业名称：</label></td>
                <td class="width-30" colspan="3">
                    <input value="${qysczzxx.qyid }" id="_qyid" name="qyid" style="width: 100%;height: 30px;"
                           class="easyui-combobox"
                           data-options="required:'true',valueField: 'id',textField: 'text',url:'${ctx}/bis/qyjbxx/idjson'"/>
                </td>
            </tr>
        </c:if>
        <c:if test="${usertype != '1' and action eq 'update'}">
            <tr>
                <td class="width-20 active"><label class="pull-right">企业名称：</label></td>
                <td class="width-30" colspan="3">
                    <input value="${qysczzxxxx.qyid }" id="_qyid" name="qyid" style="width: 100%;height: 30px;"
                           class="easyui-combobox"
                           data-options="editable:false, disabled:true, valueField: 'id',textField: 'text',url:'${ctx}/bis/qyjbxx/idjson'"/>
                </td>
            </tr>
        </c:if>
		
		
		<tr>
			<td class="width-20 active"><label class="pull-right">设备编码：</label></td>
            <td class="width-30" colspan="3">
                <input name="equipcode" class="easyui-combobox" value="${qysczzxx.equipcode }" style="width: 100%;height: 30px;"
                       data-options="panelHeight:'120px',required:'true',valueField: 'text',textField: 'text',url:'${ctx}/jtjcsj/sbxx/equipCodeJson'"/>
            </td>
		</tr>
		
        <tr>
            <td class="width-20 active"><label class="pull-right">生产单元区域标识：</label></td>
            <td class="width-30">
                <input name="prodcellid" class="easyui-textbox" value="${qysczzxx.prodcellid }" style="width: 100%;height: 30px;"
                       data-options="validType:'length[0,32]'"/>
            </td>
			<td class="width-20 active"><label class="pull-right">重大危险源名称：</label></td>
            <td class="width-30">
                <input name="hazardcode" class="easyui-combobox" value="${qysczzxx.hazardcode }" style="width: 100%;height: 30px;"
                 		data-options="editable : false, panelHeight:100,valueField:'id', textField:'text',url: '${ctx}/bis/hazard/hazardCodeJson'"/>
            </td>
        </tr>
        
 		<tr>
            <td class="width-20 active"><label class="pull-right">生产装置名称：</label></td>
            <td class="width-30">
                <input name="proddevname" class="easyui-textbox" value="${qysczzxx.proddevname }" style="width: 100%;height: 30px;"
                 		data-options="required:'true',validType:'length[0,100]'"/>
            </td>
			<td class="width-20 active"><label class="pull-right">平台最高层数：</label></td>
            <td class="width-30">
                <input name="platformtiers" class="easyui-textbox" value="${qysczzxx.platformtiers }" style="width: 100%;height: 30px;"
                 		data-options="validType:['number','length[0,50]']"/>
            </td>
		</tr>
		
		<tr>
			<tr>
			<td class="width-20 active"><label class="pull-right">重点监管危险化工工艺名称：</label></td>
            <td class="width-30" colspan="3">
                <input id="chemartart" name="chemartart" class="easyui-combobox" value="${qysczzxx.chemartart }" style="width: 100%;height: 30px;"
                 		data-options="required:'true', editable:false ,valueField: 'id',textField: 'text',url:'${ctx}/tcode/dict/gwgy',
							    onSelect: function(rec){
							   	    $('#chemartart').combobox('setValue',rec.text);
									$('#chemartid').val(rec.id);
							    }"/>
            </td>
		</tr>
		
		</tr>
		
		<tr>
			<td class="width-20 active"><label class="pull-right">涉及重点监管工艺：</label></td>
            <td class="width-30" colspan="3">
                <input name="importart" class="easyui-textbox" value="${qysczzxx.importart }" style="width: 100%;height: 80px;"
                 		data-options="multiline:true,validType:'length[0,500]'"/>
            </td>
		</tr>
		
		
	 	<tr>
            <td class="width-20 active"><label class="pull-right">调度室电话：</label></td>
            <td class="width-30">
                <input name="ctrltel" class="easyui-textbox" value="${qysczzxx.ctrltel }" style="width: 100%;height: 30px;"
                 		data-options="validType:['number','length[0,50]']"/>
            </td>
            <td class="width-20 active"><label class="pull-right">是否正常状态：</label></td>
            <td class="width-30">
                <input name="isnormal" class="easyui-combobox" value="${qysczzxx.isnormal }" style="width: 100%;height: 30px;"
                 		data-options="required:'true',panelHeight:'auto',data:[{value:'0',text:'否'},{value:'1',text:'是'}]"/>
            </td>
		</tr>
		
		<tr>
			<td class="width-15 active"><label class="pull-right">经纬度：</label></td>
			<td colspan="3">
				<span><label>经度</label>
				<input id="longitude" name="longitude" value="${qysczzxx.longitude}" class="easyui-textbox" readonly="readonly" data-options="" style="width:100px;height:30px;"/>
				<label>纬度</label> 
				<input id="latitude" name="latitude" value="${qysczzxx.latitude }" class="easyui-textbox" readonly="readonly" data-options="" style="width:100px;height:30px;"/>
				<a class="btn btn-primary" onclick="showMapXY( )" style="width:60px;">定位</a></span></td>
		</tr>
		
		<tr>
			<td class="width-20 active"><label class="pull-right">负责人：</label></td>
            <td class="width-30" colspan="3">
                <input name="dutypsn" class="easyui-textbox" value="${qysczzxx.dutypsn }" style="width: 100%;height: 30px;"
                 		data-options="validType:'length[0,100]'"/>
            </td>		
		</tr>
		
		
		<tr>
			<td class="width-20 active"><label class="pull-right">生产工艺或存储情况简介：</label></td>
            <td class="width-30" colspan="3">
                <input name="craftintroduction" class="easyui-textbox" value="${qysczzxx.craftintroduction }" style="width: 100%;height: 80px;"
                 		data-options="multiline:true,validType:'length[0,100]'"/>
            </td>			
		</tr>
		
		<tr>
			<td class="width-20 active"><label class="pull-right">安全措施：</label></td>
            <td class="width-30" colspan="3">
                <input name="safemeasures" class="easyui-textbox" value="${qysczzxx.safemeasures }" style="width: 100%;height: 80px;"
                 		data-options="multiline:true,validType:'length[0,500]'"/>
            </td>			
		</tr>
		
		
		<input type="hidden" id="chemartid" name="chemartid" value="${qysczzxx.chemartid}" />
        <c:if test="${not empty qysczzxx.id}">
            <input type="hidden" name="ID" value="${qysczzxx.id}"/>
            <input type="hidden" name="chmstorid" value="${qysczzxx.chmstorid}"/>
            <input type="hidden" name="qyid" value="${qysczzxx.qyid}"/>
            <input type="hidden" name="creator" value="${qysczzxx.creator}"/>
            <input type="hidden" name="companycode" value="${qysczzxx.companycode}"/>
            <input type="hidden" name="proddevid" value="${qysczzxx.proddevid}"/>
            <input type="hidden" name="parkid" value="${qysczzxx.parkid}"/>
            <input type="hidden" name="districtcode" value="${qysczzxx.districtcode}"/>
            <input type="hidden" name="createtime"
                   value="<fmt:formatDate value="${qysczzxx.createtime}" pattern="yyyy-MM-dd HH:mm:ss"  />"/>
            <input type="hidden" name="status" value="${qysczzxx.status}"/>
        </c:if>
        </tbody>
    </table>
</form>
	    <div id="enterprise_dlg" style="width:100%;height:100%; text-align:center;display: none;" >
		<div><span style="color: red;margin: 0 10px;">点击地图标注位置!</span>
		<input class="easyui-searchbox" style="width:300px" data-options="prompt:'请输入搜索条件',searcher:function search(value,name){ addmap(value); }" />
		</div>
		<div id="bis_enterprise_dlg_map" style="width:100%;height: 285px;"></div>
		</div>
<script type="text/javascript">
    
var locx ='${qysczzxx.longitude}';
var locy ='${qysczzxx.latitude}';

//弹出地图界面
function showMapXY( ){
	layer.open({
	    type: 1,  
	    area: ['500px', '400px'],
	    title: '标注坐标',
        maxmin: true, 
        shift: 1,
        shade :0,
	    content: $('#enterprise_dlg'),
	    btn: ['确定', '关闭'],
	    success: function(layero, index){
	    	addmap("");
	    },
	    yes: function(index, layero){
	    	$("#longitude").textbox("setValue", locx);//经度
			$("#latitude").textbox("setValue", locy);//纬度
			layer.close(index);
		  },
		  cancel: function(index){ 
	       }
	});
}

function addmap(searchcon){	
	initMap("bis_enterprise_dlg_map",locx,locy);
	map.setDefaultCursor("crosshair");//设置地图默认的鼠标指针样式
	var local = new BMap.LocalSearch(map, {
		renderOptions:{map: map}
	});
	local.search(searchcon);
	var marker = new BMap.Marker(point); //创建marker对象
	map.addOverlay(marker); //在地图中添加marker
	
	map.addEventListener("click", function(e){	
		locx=e.point.lng;
		locy=e.point.lat;
		var now_point =  new BMap.Point(e.point.lng, e.point.lat );
		marker.setPosition(now_point);//设置覆盖物位置
		marker.setAnimation(BMAP_ANIMATION_BOUNCE); //跳动的动画
	});
	
}
    
</script>
</body>
</html>