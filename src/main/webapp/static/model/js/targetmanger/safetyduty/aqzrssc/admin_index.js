var dg;
$(function() {
    dg = $('#target_aqzrssc_dg').datagrid({
        method : "post",
        url : ctx + '/target/aqzrssc/qylist',
        fit : true,
        fitColumns : true,
        border : false,
        idField : 'id',
        striped : true,
        pagination : true,
        rownumbers : true,
        nowrap : false,
        pageNumber : 1,
        pageSize : 50,
        scrollbarSize : 5,
        singleSelect : true,
        striped : true,
        columns : [ [ {
            field : 'id',
            title : 'id',
            checkbox : true,
            width : 50,
            align : 'center'
        }, {
            field : 'year',
            title : '年份',
            sortable : false,
            width : 50,
            align : 'center'
        },{
            field : 'title',
            title : '标题',
            sortable : false,
            width : 100,
            align : 'center'
        },{
            field : 'gwname',
            title : '岗位名称',
            sortable : false,
            width : 80,
            align : 'center'
        }, {
            field : 'name',
            title : '姓名',
            sortable : false,
            width : 50,
            align : 'center'
        }, {
            field : 'phone',
            title : '电话',
            sortable : false,
            width : 80,
            align : 'center'
        },{
            field : 'signtime',
            title : '责任书签订时间',
            sortable : false,
            width : 80,
            align : 'center',
            formatter : function (value){
                if(value) return new Date(value).format("yyyy-MM-dd");
                else return ;
            }
        }, {
            field : 'zrsurl',
            title : '责任书下载',
            sortable : false,
            width : 100,
            align : 'center',
            formatter : function(value) {
                if (value) {
                    var urls = value.split(",");
                    var html = "";
                    for ( var index in urls) {
                        html += "<a class='fa fa-file-word-o btn-danger btn-outline' target='_blank' style='margin:1px auto' href='" + urls[index].split("||")[0] + "'> 附件" + (parseInt(index) + 1) + "</a><br>";
                    }
                    return html;
                }else return "";
            }
        }, {
            field : 'url',
            title : '责任书上传',
            sortable : false,
            width : 100,
            align : 'center',
            formatter : function(value,row) {
                if (!row.id) {
                	return "<a class='btn btn-info btn-xs' onclick='addzrs("+row.aid+", "+row.uid+")'>上传责任书</a>";
                } else{
                    if(value){
                        var urls = value.split(",");
                        var html = "";
                        for ( var index in urls) {
                            html += "<a class='fa fa-file-pdf-o btn-danger btn-outline' target='_blank' style='margin:1px auto' href='" + urls[index].split("||")[0] + "'> 附件" + (parseInt(index) + 1) + "</a><br>";
                        }
                        return html;
                    }
                }
            }
        } ] ],
        onDblClickRow : function(rowdata, rowindex, rowDomElement) {
            view();
        },
        onLoadSuccess: function(){
            $(this).datagrid("autoMergeCells",['year']);
        },
        checkOnSelect : false,
        selectOnCheck : false,
        toolbar : '#target_aqzrssc_tb'
    });
});
