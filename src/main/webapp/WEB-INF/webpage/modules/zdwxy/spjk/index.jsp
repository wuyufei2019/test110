<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>重大危险源视频</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript" src="${ctxStatic}/ckplayer/ckplayer.js"></script>
	<style>
		.col-lg-6 {
			width: 33.3%;
		}

		.lab {
			height: 345px;
			margin-left: 0px;
			margin-right: 0px;
			align-items: unset;
			flex-direction: column;
			background: rgb(255, 255, 255);
			box-shadow: 2px 2px 8px #ececec;
		}

		.margin_ {
			margin-top: 10px;
			margin-bottom: 10px;
			padding-left: 10px;
			padding-right: 10px;
		}

		#block_item .lab {
			height: 716px
		}

		.labwds_ {
			padding: 10px;
			background: rgba(33, 150, 243, 0.16862745098039217);
		}

		.labic_ {
			height: calc(100% - 30px);
		}

		.tit {
			padding: 0 10px 0 6px;
			border-left: 3px solid #34abb8;
			line-height: 15px;
			font-size: 15px;
			color: #0096a7;
			font-weight: 600;
		}
	</style>
</head>
<body>
<div class="easyui-layout" style="height:100%; ">
	<div data-options="region:'west',split:true,border:false,title:'重大危险源视频设备列表'" style="width: 25%;">
		<table id="qydg"></table>
	</div>

	<div data-options="region:'center',split:true,border:false,title:'视频图像'">
		<div class="col-lg-6 col-md-6 col-xs-12 margin_" id="bigone" style="display: none;">
			<div class="lab" id="bigOne_lab">
				<div class="labwds_" style="">
					<span class="tit">视频实时监测</span>
				</div>
				<div class="labic_" style="position: relative;">
					<div id="playlive" class="" style="position: relative;text-align: center;height: 100%;">

					</div>
				</div>
			</div>
		</div>

		<div id="sptx">

		</div>
	</div>
	</div>
</div>

<script type="text/javascript">
	var qydg;
	var qyid = '${qyid}';
	var usertype = '${usertype}';
	var zdwxy = '${zdwxy}';
	var url = '';

	$(function () {
		if (zdwxy != null && zdwxy != '') {
			url = ctx+'/bis/spsbxx/list?zdwxy=1';
		} else {
			url = ctx+'/bis/spsbxx/list';
		}
        qydg=$('#qydg').datagrid({
            method: "post",
            url: url,
            fit : true,
            fitColumns : true,
            border : false,
            idField : 'id',
            striped:true,
            pagination:true,
            rownumbers:true,
            nowrap:false,
            pageNumber:1,
            pageSize : 50,
            pageList : [ 50, 100, 150, 200, 250 ],
            scrollbarSize:5,
            singleSelect:true,
            striped:true,
            columns:[[
				{field:'qyname',title:'企业名称',sortable:false,width:100,align:'center'},
                {field:'m20',title:'摄像头名称',sortable:false,width:80,align:'center'}
            ]],
            onClickRow: function (rowIndex, rowData, rowDomElement){
            	$('#sptx').hide();
            	$('#bigone').css('width', '100%').css('height','100%');
            	$('#bigOne_lab').css('height', '100%');
				$('#bigone').show();
                showVideo(rowData.url,'#playlive');
            },
			onLoadSuccess: function() {
				if (usertype == '1') {
					$(this).datagrid("hideColumn",['qyname']);
				} else {
					$(this).datagrid("autoMergeCells",['qyname']);
				}
			},
            checkOnSelect:false,
            selectOnCheck:false,
            toolbar:'#bis_spsbxx_tb'
        });

		$.ajax({
			type: 'POST',
			url: ctx + '/bis/spsbxx/zdwxySpUrls?qyid='+${qyid},
			dataType: 'json',
			success: function (data) {
				var len = 0;
				var spsbxxData = JSON.parse(data);
				if (spsbxxData.length > 9) {
					len = 9;
				} else if (spsbxxData.length > 0 && spsbxxData.length <= 9) {
					len = spsbxxData.length;
				}
				var html = '';
				for (var  i = 0; i < len; i++) {
					html = '<div class="col-lg-6 col-md-6 col-xs-12 margin_"><div class="lab">'
						  +'<div class="labwds_" style="">'
						  + '    <span class="tit">'+spsbxxData[i].m20+'</span>'
						  + '</div>'
						  + '<div class="labic_" style="position: relative;">'
						  + '    <div id="playlive_'+i+'" class="" style="position: relative;text-align: center;height: 100%;">'
					      + '	 </div>'
						  + '</div></div></div>';
					$('#sptx').append(html);
					showVideo(spsbxxData[i].url, '#playlive_'+i);
				}
			}
		})
	})

    // 显示视频播放器
	function showVideo(url, idname) {
		var videoObject = {
			container: idname,//“#”代表容器的ID，“.”或“”代表容器的class
			variable: 'player',//该属性必需设置，值等于下面的new chplayer()的对象
			autoplay: false, //是否自动播放
			video:url //视频地址
		};
		var player=new ckplayer(videoObject);
	}

</script>

</body>
</html>