$.extend($.fn.validatebox.defaults.rules, {
                CHS: {
                    validator: function (value, param) {
                        return /^[\u0391-\uFFE5]+$/.test(value);
                    },
                    message: '请输入汉字'
                },
                english : {// 验证英语
                    validator : function(value) {
                        return /^[A-Za-z]+$/i.test(value);
                    },
                    message : '请输入英文'
                },
                ip : {// 验证IP地址
                    validator : function(value) {
                        return /\d+\.\d+\.\d+\.\d+/.test(value);
                    },
                    message : 'IP地址格式不正确'
                },
                ZIP: {
                    validator: function (value, param) {
                        return /^[0-9]\d{5}$/.test(value);
                    },
                    message: '邮政编码不存在'
                },
                QQ: {
                    validator: function (value, param) {
                        return /^[1-9]\d{4,10}$/.test(value);
                    },
                    message: 'QQ号码不正确'
                },
                mobile: {
                    validator: function (value, param) {
                        return /^(?:13\d|15\d|18\d|17\d)-?\d{5}(\d{3}|\*{3})$/.test(value);
                    },
                    message: '手机号码不正确'
                },
                tel:{
                    validator:function(value,param){
                        return /^(\d{3}-|\d{4}-)?(\d{8}|\d{7})?(-\d{1,6})?$/.test(value);
                    },
                    message:'电话号码不正确'
                },
                mobileAndTel: {
                    validator: function (value, param) {
                        return /(^([0\+]\d{2,3})\d{3,4}\-\d{3,8}$)|(^([0\+]\d{2,3})\d{3,4}\d{3,8}$)|(^([0\+]\d{2,3}){0,1}13\d{9}$)|(^\d{3,4}\d{3,8}$)|(^\d{3,4}\-\d{3,8}$)/.test(value);
                    },
                    message: '请正确输入电话号码'
                },
                number: {
                    validator: function (value, param) {
                        return /^[-+]?[0-9]+(\.[0-9]+)?$/.test(value);
                    },
                    message: '请输入数值'
                },
                money:{
                    validator: function (value, param) {
                        return (/^(([1-9]\d*)|\d)(\.\d{1,2})?$/).test(value);
                     },
                     message:'请输入正确的金额'
 
                },
                mone:{
                    validator: function (value, param) {
                        return (/^(([1-9]\d*)|\d)(\.\d{1,2})?$/).test(value);
                     },
                     message:'请输入整数或小数'
 
                },
                integer:{
                    validator:function(value,param){
                        return /^[+]?[1-9]\d*$/.test(value);
                    },
                    message: '请输入最小为1的整数'
                },
                integ:{
                    validator:function(value,param){
                        return /^[+]?[0-9]\d*$/.test(value);
                    },
                    message: '请输入整数'
                },
                range:{
                    validator:function(value,param){
                        if(/^[1-9]\d*$/.test(value)){
                            return value >= param[0] && value <= param[1]
                        }else{
                            return false;
                        }
                    },
                    message:'输入的数字在{0}到{1}之间'
                },
                minLength:{
                    validator:function(value,param){
                        return value.length >=param[0]
                    },
                    message:'至少输入{0}个字'
                },
                maxLength:{
                    validator:function(value,param){
                        return value.length<=param[0]
                    },
                    message:'最多{0}个字'
                },
                //select即选择框的验证
                selectValid:{
                    validator:function(value,param){
                        if(value == param[0]){
                            return false;
                        }else{
                            return true ;
                        }
                    },
                    message:'请选择'
                },
                idCode:{
                    validator:function(value,param){
                        return /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/.test(value);
                    },
                    message: '请输入正确的身份证号'
                },
                loginName: {
                    validator: function (value, param) {
                        return /^[\u0391-\uFFE5\w]+$/.test(value);
                    },
                    message: '登录名称只允许汉字、英文字母、数字及下划线。'
                },
                equalTo: {
                    validator: function (value, param) {
                        return value == param[0];
                    },
                    message: '两次输入的字符不一至'
                },
                englishOrNum : {// 只能输入英文和数字
                    validator : function(value) {
                        return /^[a-zA-Z0-9_]{1,}$/.test(value);
                    },
                    message : '请输入英文、数字、下划线'
                },
               xiaoshu:{ 
                validator : function(value){ 
                return /^(([1-9]+)|([0-9]+\.[0-9]{1,2}))$/.test(value);
                }, 
                message : '最多保留两位小数！'   
                },
            ddPrice:{
                validator:function(value,param){
                    if(/^[1-9]\d*$/.test(value)){
                        return value >= param[0] && value <= param[1];
                    }else{
                        return false;
                    }
                },
                message:'请输入1到100之间正整数'
            },
            jretailUpperLimit:{
                validator:function(value,param){
                    if(/^[0-9]+([.]{1}[0-9]{1,2})?$/.test(value)){
                        return parseFloat(value) > parseFloat(param[0]) && parseFloat(value) <= parseFloat(param[1]);
                    }else{
                        return false;
                    }
                },
                message:'请输入0到100之间的最多俩位小数的数字'
            },
            rateCheck:{
                validator:function(value,param){
                    if(/^[0-9]+([.]{1}[0-9]{1,2})?$/.test(value)){
                        return parseFloat(value) > parseFloat(param[0]) && parseFloat(value) <= parseFloat(param[1]);
                    }else{
                        return false;
                    }
                },
                message:'请输入0到1000之间的最多俩位小数的数字'
            },
            packformat: {
                validator: function (value) {
                    return /(\w+\.){1,}/.test(value);
                },
                message: '不正确的包名!'
            },
            FUN: {
                validator: function (value, param) {
                	 var fun = param[0];
                     if ($.isFunction(fun)) {
                         return fun(value);
                     }
                     return true;
                },
                message: '{1}'
            }
            });




//autoMergeCellAndCells实现效果
$.extend($.fn.datagrid.methods, {
    autoMergeCells : function (jq, fields) {
        return jq.each(function () {
            var target = $(this);
            if (!fields) {
                fields = target.datagrid("getColumnFields");
            }
            var rows = target.datagrid("getRows");
            var i = 0,
            j = 0,
            temp = {};
            
            for (i; i < rows.length; i++) {
                var row = rows[i];
                j = 0;
                for (j; j < fields.length; j++) {
                    var field = fields[j];
                    var tf = temp[field];
                    if (!tf) {
                        tf = temp[field] = {};
                        tf[row[field]] = [i];
                    } else {
                        var tfv = tf[row[field]];
                        if (tfv) {
                            tfv.push(i);
                        } else {
                            tfv = tf[row[field]] = [i];
                        }
                    }
                }
            }
            $.each(temp, function (field, colunm) {
                $.each(colunm, function () {
                    var group = this;
                    
                    if (group.length > 1) {
                        var before,
                        after,
                        megerIndex = group[0];
                        for (var i = 0; i < group.length; i++) {
                            before = group[i];
                            after = group[i + 1];
                            if (after && (after - before) == 1) {
                                continue;
                            }
                            var rowspan = before - megerIndex + 1;
                            if (rowspan > 1) {
                                target.datagrid('mergeCells', {
                                    index : megerIndex,
                                    field : field,
                                    rowspan : rowspan
                                });
                            }
                            if (after && (after - before) != 1) {
                                megerIndex = after;
                            }
                        }
                    }
                });
            });
        });
    },
    autoMergeCellAndCells : function (jq, fields) {
        return jq.each(function () {
            var target = $(this);
            if (!fields) {
                fields = target.datagrid("getColumnFields");
            }
            var cfield = fields[0];
            if (!cfield) {
                return;
            }
            var rows = target.datagrid("getRows");
            var i = 0,
            j = 0,
            temp = {};
            for (i; i < rows.length; i++) {
                var row = rows[i];
                j = 0;
                var tf = temp[cfield];
                if (!tf) {
                    tf = temp[cfield] = {};
                    tf[row[cfield]] = [i];
                
                } else {
                    var tfv = tf[row[cfield]];
                    if (tfv) {
                        tfv.push(i);
                    } else {
                        tfv = tf[row[cfield]] = [i];
                        
                    }
                }
            }
            
            $.each(temp, function (field, colunm) {
                $.each(colunm, function () {
                    var group = this;
                    
                    if (group.length > 1) {
                        var before,
                        after,
                        megerIndex = group[0];
                        for (var i = 0; i < group.length; i++) {
                            before = group[i];
                            after = group[i + 1];
                            if (after && (after - before) == 1) {
                                continue;
                            }
                            var rowspan = before - megerIndex + 1;
                            if (rowspan > 1) {
                                for(var j=0;j<fields.length;j++){
                                    target.datagrid('mergeCells', {
                                        index : megerIndex,
                                        field : fields[j],
                                        rowspan : rowspan
                                   });
                                }
                            }
                            if (after && (after - before) != 1) {
                                megerIndex = after;
                            }
                        }
                    }
                });
            });
        });
    }
});
//启用行编辑
$.extend($.fn.datagrid.methods, {
	editCell: function(jq,param){
		return jq.each(function(){
			var opts = $(this).datagrid('options');
			var fields = $(this).datagrid('getColumnFields',true).concat($(this).datagrid('getColumnFields'));
			for(var i=0; i<fields.length; i++){
				var col = $(this).datagrid('getColumnOption', fields[i]);
				col.editor1 = col.editor;
				if (fields[i] != param.field){
					col.editor = null;
				}
			}
			$(this).datagrid('beginEdit', param.index);
			for(var i=0; i<fields.length; i++){
				var col = $(this).datagrid('getColumnOption', fields[i]);
				col.editor = col.editor1;
			}
		});
	}
});