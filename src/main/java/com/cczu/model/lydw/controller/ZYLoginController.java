package com.cczu.model.lydw.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @Author: luxincai
 * @CreateTime:2019-11-07 13:16
 * @Description: todo
 */
@Controller
@RequestMapping("lydw")
public class ZYLoginController {


    /**
     * 登录页面
     * @return
     */
    @RequestMapping(value = "/login")
    public String zyLogin(HttpServletRequest request){
        String vcode = request.getParameter("vcode");
        String tenantId = request.getParameter("tenantId");
        String userId = request.getParameter("userId");
        String password = "ctyw201910221014";
        String token = getSHA256StrJava(tenantId + userId + password + vcode);
        return "redirect: http://112.25.70.11:9966/position/embeded/main.html?token="+token+"&tenantId=sc19100014&userId=ctyw&theme=&navigation=nav";
    }


    /**
     * 利用java原生的摘要实现SHA256加密
     * @param str 加密后的报文
     * @return
     */
    public static String getSHA256StrJava(String str){
        MessageDigest messageDigest;
        String encodeStr = "";
        try {
            messageDigest = MessageDigest.getInstance("SHA-256");
            messageDigest.update(str.getBytes("UTF-8"));
            encodeStr = byte2Hex(messageDigest.digest());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return encodeStr;
    }
    /**
     * 将byte转为16进制
     * @param bytes
     * @return
     */
    private static String byte2Hex(byte[] bytes){
        StringBuffer stringBuffer = new StringBuffer();
        String temp = null;
        for (int i=0;i<bytes.length;i++){
            temp = Integer.toHexString(bytes[i] & 0xFF);
            if (temp.length()==1){
                //1得到一位的进行补0操作
                stringBuffer.append("0");
            }
            stringBuffer.append(temp);
        }
        return stringBuffer.toString();
    }
}
