package com.cczu.sys.comm.utils;

import java.util.Map;

import com.google.common.collect.Maps;

/**
 * 全局配置类
 * @author jason
 */
public class Global {
	
	/**
	 * 保存全局属性值
	 */
	private static Map<String, String> map = Maps.newHashMap();
	
	/**
	 * 文件分割符
	 */
	public final static String FILE_SPLIT_CHAR="||";
	
	/**
	 * 属性文件加载对象
	 */
	private static PropertiesLoader propertiesLoader = new PropertiesLoader("config.properties");
	
	/**
	 * 获取配置
	 */
	public static String getConfig(String key) {
		String value = map.get(key);
		if (value == null){
			value = propertiesLoader.getProperty(key);
			map.put(key, value);
		}
		return value;
	}

	/**
	 * 获取管理端根路径
	 */
	public static String getAdminPath() {
		return getConfig("adminPath");
	}
	
	/**
	 * 获取前端根路径
	 */
	public static String getFrontPath() {
		return getConfig("frontPath");
	}
	
	/**
	 * 获取URL后缀
	 */
	public static String getUrlSuffix() {
		return getConfig("urlSuffix");
	}
	
	
	/**
	 * 获取视频web服务器地址
	 */
	public static String getVideoHttp() {
		return getConfig("videohttp");
	}
	
	
	/**
	 * easydawn视频服务器接口地址
	 */
	public static String getVideoApi() {
		return getConfig("videoapi");
	}

	/**
	 * 安全生产执法获取市简称
	 * @return
	 */
	public static String getAbbreviation() {
		return getConfig("abbreviation");
	}
	
	/**
	 * 获取H5发布地址
	 * @return
	 */
	public static String getH5url() {
		return getConfig("h5url");
	}
	
	/**
	 * 法律法规list接口地址
	 */
	public static String getWfwurl() {
		return getConfig("wfwurl");
	}
	
}

