package com.cczu.util.common;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringEscapeUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils extends org.apache.commons.lang3.StringUtils {
	
	public static String lowerFirst(String str){
		if(StringUtils.isBlank(str)) {
			return "";
		} else {
			return str.substring(0,1).toLowerCase() + str.substring(1);
		}
	}
	
	public static String upperFirst(String str){
		if(StringUtils.isBlank(str)) {
			return "";
		} else {
			return str.substring(0,1).toUpperCase() + str.substring(1);
		}
	}

	/**
	 * 替换掉HTML标签方法
	 */
	public static String replaceHtml(String html) {
		if (isBlank(html)){
			return "";
		}
		String regEx = "<.+?>";
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(html);
		String s = m.replaceAll("");
		return s;
	}

	/**
	 * 缩略字符串（不区分中英文字符）
	 * @param str 目标字符串
	 * @param length 截取长度
	 * @return
	 */
	public static String abbr(String str, int length) {
		if (str == null) {
			return "";
		}
		try {
			StringBuilder sb = new StringBuilder();
			int currentLength = 0;
			for (char c : replaceHtml(StringEscapeUtils.unescapeHtml4(str)).toCharArray()) {
				currentLength += String.valueOf(c).getBytes("GBK").length;
				if (currentLength <= length - 3) {
					sb.append(c);
				} else {
					sb.append("...");
					break;
				}
			}
			return sb.toString();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return "";
	}

	/**
	 * 缩略字符串（替换html）
	 * @param str 目标字符串
	 * @param length 截取长度
	 * @return
	 */
	public static String rabbr(String str, int length) {
        return abbr(replaceHtml(str), length);
	}
		
	
	/**
	 * 转换为Double类型
	 */
	public static Double toDouble(Object val){
		if (val == null){
			return 0D;
		}
		try {
			return Double.valueOf(trim(val.toString()));
		} catch (Exception e) {
			return 0D;
		}
	}

	/**
	 * 转换为Float类型
	 */
	public static Float toFloat(Object val){
		return toDouble(val).floatValue();
	}

	/**
	 * 转换为Long类型
	 */
	public static Long toLong(Object val){
		return toDouble(val).longValue();
	}

	/**
	 * 转换为Integer类型
	 */
	public static Integer toInteger(Object val){
		return toLong(val).intValue();
	}
	
	/**
	 * 获得用户远程地址
	 */
	public static String getRemoteAddr(HttpServletRequest request){
		String remoteAddr = request.getHeader("X-Real-IP");
        if (isNotBlank(remoteAddr)) {
        	remoteAddr = request.getHeader("X-Forwarded-For");
        }else if (isNotBlank(remoteAddr)) {
        	remoteAddr = request.getHeader("Proxy-Client-IP");
        }else if (isNotBlank(remoteAddr)) {
        	remoteAddr = request.getHeader("WL-Proxy-Client-IP");
        }
        return remoteAddr != null ? remoteAddr : request.getRemoteAddr();
	}

	/**
	 * 将实体对象的属性转化成符合导出word条件的字符串
	 * 反转html字符并替换“<>&”字符串
	 */
    public static void parseBeanForWord(Object obj) throws Exception {
        Map<String, Object> map = BeanUtils.describe(obj);
        for (Map.Entry<String, Object> entry : map.entrySet()) {
        	Object entryvalue = entry.getValue();
            if(entryvalue!=null&&StringUtils.isNoneBlank(entryvalue.toString())
					&&!"null".equals(entryvalue.toString())&& !org.apache.commons.lang3.StringUtils.isNumeric(entryvalue.toString())){
            	String tmp =parseStringForWord(entryvalue.toString());
                entry.setValue(tmp);
            }
        }
        BeanUtils.populate(obj,map);
    }
	/**
	 * 将字符串转化成符合导出word条件的字符串
	 * 反转html字符并替换“<>&”字符串
	 */
	public static String parseStringForWord(String str) throws Exception {
		return StringEscapeUtils.unescapeHtml4(str).replace("&", "&amp;")
				.replace("<", "&lt;").replace(">", "&gt;");
	}


    /**
     * 将实体对象的属性中的字符str1 替换为str2。换行符替换
     */
    public static void changeObjCharacters(Object obj,String str1,String str2) throws Exception {
        Map<String, Object> map = BeanUtils.describe(obj);
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            Object entryvalue = entry.getValue();
            if(entryvalue!=null){
                String tmp =entryvalue.toString().replace(str1,str2);
                entry.setValue(tmp);
            }
        }
        BeanUtils.populate(obj,map);
    }


    /**
     * 将Map对象的属性中的字符str1 替换为str2。换行符替换
     */
    public static void changeMapCharacters(Map<String, Object> map,String str1,String str2){
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            Object entryvalue = entry.getValue();
            if(entryvalue!=null){
                String tmp =entryvalue.toString().replace(str1,str2);
                entry.setValue(tmp);
            }
        }
    }


}
