package com.cczu.util.common;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @ClassName: Parameter
 * @Description: 查询参数类
 * @author jason
 * @date 2017年5月26日
 *
 */
public class Parameter extends HashMap<String, Object> {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * 构造类，例：new Parameter(id, parentIds)
	 * @param values 参数值
	 */
	public Parameter(Object... values) {
		if (values != null){
			for (int i=0; i<values.length; i++){
				put("p"+(i+1), values[i]);
			}
		}
	}
	
	/**
	 * 构造类，例：new Parameter(new Object[][]{{"id", id}, {"parentIds", parentIds}})
	 * @param parameters 参数二维数组
	 */
	public Parameter(Object[][] parameters) {
		if (parameters != null){
			for (Object[] os : parameters){
				if (os.length == 2){
					put((String)os[0], os[1]);
				}
			}
		}
	}
	
	
	/**
	 * 构造类 通过map对象
	 * @param map 参数集合
	 */
	public Parameter(Map<String, Object> map) {
		for (Map.Entry<String, Object> entry : map.entrySet()) {  
			if(entry.getValue()!=null&&!entry.getValue().equals(""))
			put(entry.getKey(), entry.getValue());
		}
	}

}
