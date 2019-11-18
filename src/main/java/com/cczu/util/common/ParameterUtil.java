package com.cczu.util.common;


import java.util.Map;

/**
 * @Author: luxincai
 * @CreateTime:2019-08-09 14:57
 * @Description: 读取map 中的数据并返回指定类型
 */
public class ParameterUtil {


    /**
     *  返回String
     * @param key 键
     * @param map 数据集
     * @param defaultValue 默认值
     * @return
     */
    public static String toString(String key, Map<String,Object> map,String defaultValue) {
        try {
            return map.get(key).toString();
        }catch (Exception e){
            return defaultValue;
        }
    }

    /**
     *  返回Long
     * @param key 键
     * @param map 数据集
     * @param defaultValue 默认值
     * @return
     */
    public static Long toLong(String key, Map<String,Object> map,Long defaultValue) {
        try {
            return Long.valueOf(map.get(key).toString());
        }catch (Exception e){
            return defaultValue;
        }
    }

    /**
     *  返回Integer
     * @param key 键
     * @param map 数据集
     * @param defaultValue 默认值
     * @return
     */
    public static Integer toInteger(String key, Map<String,Object> map,Integer defaultValue) {
        try {
            return Integer.valueOf(map.get(key).toString());
        }catch (Exception e){
            return defaultValue;
        }
    }


    /**
     *  返回Double
     * @param key 键
     * @param map 数据集
     * @param defaultValue 默认值
     * @return
     */
    public static Double toDouble(String key, Map<String,Object> map,Double defaultValue) {
        try {
            return Double.valueOf(map.get(key).toString());
        }catch (Exception e){
            return defaultValue;
        }
    }

    /**
     *  Float
     * @param key 键
     * @param map 数据集
     * @param defaultValue 默认值
     * @return
     */
    public static Float toFloat(String key, Map<String,Object> map,Float defaultValue) {
        try {
            return Float.valueOf(map.get(key).toString());
        }catch (Exception e){
            return defaultValue;
        }
    }



    /**
     *  toBoolean
     * @param key 键
     * @param map 数据集
     * @param defaultValue 默认值
     * @return
     */
    public static boolean toBoolean(String key, Map<String,Object> map, boolean defaultValue) {
        try {
            String flag = toString(key, map, "");
            if ("true".equals(flag)) {
                return true;
            } else if ("false".equals(flag)) {
                return false;
            }
            return defaultValue;
        }catch (Exception e){
            return defaultValue;
        }
    }






}
