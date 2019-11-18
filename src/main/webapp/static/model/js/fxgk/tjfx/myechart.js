        // 基于准备好的dom，初始化echarts实例
        // 指定图表的配置项和数据
        var option = {
            title: {
                text: '风险点统计图'
            },
            toolbox:{
              show :true,
          	  feature : {
                    restore : {show: true},
                    saveAsImage : {show: true}
                }
            },
            tooltip: {},
            legend: {
                data:[]
            },
            xAxis: {
                data: ["蓝","黄","橙","红"],
                name :'风险等级',
                nameTextStyle :{
            		color :'#4488BB'
            	},
            	axisTick  :{
            		show :false,	//是否显示坐标轴刻度。
            		lineStyle :{
            			color :'#4488BB'//刻度线的颜色
            		}
            	},
            	splitLine :{
            		show :true,
            	},
            	axisLine :{
            		lineStyle :{
            			color :'#4488BB',//轴线颜色
            			width :2
            		}
            	},
            	axisLabel :{
            		textStyle :{
            			color :'#383838'//刻度标签文字的颜色
            		}
            	}
            },
            yAxis: {
            	name :'风险点数',
            	nameTextStyle :{
            		color :'#4488BB'
            	},
            	nameGap: 12,//坐标轴名称与轴线之间的距离。
            	axisTick  :{
            		show :false,//是否显示坐标轴刻度。
            	
            	},
            	axisLine :{
            		lineStyle :{
            			color :'#4488BB',//轴线颜色
            			width :2
            		}
            	},
            	axisLabel :{
            		textStyle :{
            			color :'#383838'//刻度标签文字的颜色
            		}
            	},
            },
            series: [{
                itemStyle :{
            		normal :{
            			label:{
            				show: true,//是否展示  
            				position:'top',
            				textStyle:{color:'#5690D2'},
            				formatter : '{c}'
            			},
            		}
            	},
                type: 'bar',
                barMinHeight: 10,
                barWidth : 80,//柱图宽度
                data: []
            }]
        };
        // 使用刚指定的配置项和数据显示图表。
        //myChart.setOption(option); 
        
        // 基于准备好的dom，初始化echarts实例
        //var myChart = echarts.init(document.getElementById('main'));

        // 指定图表的配置项和数据
        var option2 = {
            title: {
                text: '风险点统计图'
            },
            toolbox:{
            	show :true,
            	feature :{
            		magicType :{
            			type : ['line', 'bar', 'stack', 'tiled']
            		},
            		restore :{
            			
            		},
            		saveAsImage :{
            			show :true
            		}
            	}
            },
            tooltip: {},
            legend: {
            	data:['蓝','黄','橙','红']
            },
            xAxis: {
                data: [],
                name :'乡镇',
                nameTextStyle :{
            		color :'#4488BB'
            	},
				axisLabel:{  
             		interval:0,//横轴信息全部显示  
             		formatter:function(value)  
                   {  
                       return value.split("").join("\n");
                   }    
             		
        		},
            	axisTick  :{
            		show :false,	//是否显示坐标轴刻度。
            		lineStyle :{
            			color :'#4488BB'//刻度线的颜色
            		}
            	},
            	splitLine :{
            		show :true,
            	},
            	axisLine :{
            		lineStyle :{
            			color :'#4488BB',//轴线颜色
            			width :2
            		}
            	},
            	axisLabel :{
            		textStyle :{
            			color :'#383838'//刻度标签文字的颜色
            		},
            	    interval: 0  
            	}
            },
            yAxis: {
            	name :'风险点数',
            	nameTextStyle :{
            		color :'#4488BB'
            	},
            	nameGap: 12,//坐标轴名称与轴线之间的距离。
            	axisTick  :{
            		show :false,//是否显示坐标轴刻度。
            	
            	},
            	axisLine :{
            		lineStyle :{
            			color :'#4488BB',//轴线颜色
            			width :2
            		}
            	},
            	axisLabel :{
            		textStyle :{
            			color :'#383838'//刻度标签文字的颜色
            		}
            	}
            },
            series: [{
            	name: '蓝',
                type: 'bar',
                data: [],
                itemStyle :{
                		normal :{
                			label:{
                				show: true,//是否展示  
                				position:'top',
                				textStyle:{color:'#5690D2'},
                				formatter : '{c}'
                			},
                			color :'#5690D2'
                		}
                	}
            },
            {
            	name: '黄',
                type: 'bar',
                data: [],
                itemStyle :{
                		normal :{
                			label:{
                				show: true,//是否展示  
                				position:'top',
                				textStyle:{color:'#5690D2'},
                				formatter : '{c}'
                			},
                			color :'#FFF147'
                		}
                	}
            },
            {
            	name: '橙',
                type: 'bar',
                data: [],
                itemStyle :{
                		normal :{
                			label:{
                				show: true,//是否展示  
                				position:'top',
                				textStyle:{color:'#5690D2'},
                				formatter : '{c}'
                			},
                			color :'#E87C25'
                		}
                	}
            },
            {
            	name: '红',
                type: 'bar',
                data: [],
                itemStyle :{
                		normal :{
                			label:{
                				show: true,//是否展示  
                				position:'top',
                				textStyle:{color:'#5690D2'},
                				formatter : '{c}'
                			},
                			color :'#FF0000'
                		}
                	}
            }]
        };
        
     // 指定图表的配置项和数据
        var option3 = {
            title: {
                text: '风险点统计图'
            },
            toolbox:{
            	show :true,
            	  feature : {
                      restore : {show: true},
                      saveAsImage : {show: true}
                  }
            },
            tooltip: {},
            legend: {
            	data:['蓝','黄','橙','红']
            },
            xAxis: {
                data: ["危险化学品","爆炸性粉尘","重大危险源","受限空间","涉氨场所","生产系统","设备设施","输送管线","操作行为","职业健康","环境条件","施工场所","安全管理","其他"],
                name :'风险分类',
                nameTextStyle :{
            		color :'#4488BB'
            	},
            	axisTick  :{
            		show :false,	//是否显示坐标轴刻度。
            		lineStyle :{
            			color :'#4488BB'//刻度线的颜色
            		}
            	},
            	splitLine :{
            		show :true,
            	},
            	axisLine :{
            		lineStyle :{
            			color :'#4488BB',//轴线颜色
            			width :2
            		}
            	},
            	axisLabel :{
            		textStyle :{
            			color :'#383838'//刻度标签文字的颜色
            		},
            	    interval: 0  
            	}
            },
            yAxis: {
            	name :'风险点数',
            	nameTextStyle :{
            		color :'#4488BB'
            	},
            	nameGap: 12,//坐标轴名称与轴线之间的距离。
            	axisTick  :{
            		show :false,//是否显示坐标轴刻度。
            	
            	},
            	axisLine :{
            		lineStyle :{
            			color :'#4488BB',//轴线颜色
            			width :2
            		}
            	},
            	axisLabel :{
            		textStyle :{
            			color :'#383838'//刻度标签文字的颜色
            		}
            	}
            },
            series: [{
            	name: '蓝',
                type: 'bar',
                data: [],
                barMinHeight: 10,
                itemStyle :{
                		normal :{
                			label:{
                				show: true,//是否展示  
                				position:'top',
                				textStyle:{color:'#5690D2'},
                				formatter : '{c}'
                			},
                			color :'#5690D2'
                		}
                	}
            },
            {
            	name: '黄',
                type: 'bar',
                data: [],
                itemStyle :{
                		normal :{
                			color :'#FFF147',
                			label:{
                				show: true,//是否展示  
                				position:'top',
                				textStyle:{color:'#5690D2'},
                				formatter : '{c}'
                			},
                		}
                	}
            },
            {
            	name: '橙',
                type: 'bar',
                data: [],
                itemStyle :{
                		normal :{
                			color :'#E87C25',
                			label:{
                				show: true,//是否展示  
                				position:'top',
                				textStyle:{color:'#5690D2'},
                				formatter : '{c}'
                			},
                		}
                	}
            },
            {
            	name: '红',
                type: 'bar',
                data: [],
                itemStyle :{
                		normal :{
                			color :'#FF0000',label:{
                				show: true,//是否展示  
                				position:'top',
                				textStyle:{color:'#5690D2'},
                				formatter : '{c}'
                			},
                		}
                	}
            }]
        };

        // 指定图表的配置项和数据
        var option4 = {
            title: {
                text: '风险点统计图'
            },
            toolbox:{
            	show :true,
            	feature :{
            		magicType :{
            			type : ['line', 'bar', 'stack', 'tiled']
            		},
            		restore :{
            			
            		},
            		saveAsImage :{
            			show :true
            		}
            	}
            },
            tooltip: {},
            legend: {
            	data:['蓝','黄','橙','红']
            },
            xAxis: {
                data: ["物体打击","机械伤害","车辆伤害","起重伤害","高处坠落","中毒和窒息","触电","淹溺","灼烫","火灾","坍塌","透水","放炮","冒顶片帮","火药爆炸","瓦斯爆炸","锅炉爆炸","容器爆炸","其它爆炸","其它伤害"],
                name :'风险分类',
                nameTextStyle :{
            		color :'#4488BB'
            	},
            	axisTick  :{
            		show :false,	//是否显示坐标轴刻度。
            		lineStyle :{
            			color :'#4488BB'//刻度线的颜色
            		}
            	},
            	splitLine :{
            		show :true,
            	},
            	axisLine :{
            		lineStyle :{
            			color :'#4488BB',//轴线颜色
            			width :2
            		}
            	},
            	axisLabel :{
            		textStyle :{
            			color :'#383838'//刻度标签文字的颜色
            		},
            	    interval: 0  
            	}
            },
            yAxis: {
            	name :'风险点数',
            	nameTextStyle :{
            		color :'#4488BB'
            	},
            	nameGap: 12,//坐标轴名称与轴线之间的距离。
            	axisTick  :{
            		show :false,//是否显示坐标轴刻度。
            	
            	},
            	axisLine :{
            		lineStyle :{
            			color :'#4488BB',//轴线颜色
            			width :2
            		}
            	},
            	axisLabel :{
            		textStyle :{
            			color :'#383838'//刻度标签文字的颜色
            		}
            	}
            },
            series: [{
            	name: '蓝',
                type: 'bar',
                data: [],
                itemStyle :{
                		normal :{
                			label:{
                				show: true,//是否展示  
                				position:'top',
                				textStyle:{color:'#5690D2'},
                				formatter : '{c}'
                			},
                			color :'#5690D2'
                		}
                	}
            },
            {
            	name: '黄',
                type: 'bar',
                data: [],
                itemStyle :{
                		normal :{
                			color :'#FFF147',
                			label:{
                				show: true,//是否展示  
                				position:'top',
                				textStyle:{color:'#5690D2'},
                				formatter : '{c}'
                			},
                		}
                	}
            },
            {
            	name: '橙',
                type: 'bar',
                data: [],
                itemStyle :{
                		normal :{
                			color :'#E87C25',
                			label:{
                				show: true,//是否展示  
                				position:'top',
                				textStyle:{color:'#5690D2'},
                				formatter : '{c}'
                			},
                		}
                	}
            },
            {
            	name: '红',
                type: 'bar',
                data: [],
                itemStyle :{
                		normal :{
                			color :'#FF0000',label:{
                				show: true,//是否展示  
                				position:'top',
                				textStyle:{color:'#5690D2'},
                				formatter : '{c}'
                			},
                		}
                	}
            }]
        };
