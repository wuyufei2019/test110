package com.cczu.model.controller;

import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.comm.utils.Global;
import com.cczu.sys.comm.utils.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLDecoder;

/**
 * 安全知识库-平台资源库controller
 * 
 * @author wbth
 * @date 2019年7月5日
 */
@Controller
@RequestMapping("sekb/ptzyk")
public class PageSekbPtzykController extends BaseController {

	/**
	 * 列表显示页面
	 *
	 * @param model
	 */
	@RequestMapping(value = "index")
	public String index(Model model) {
		return "sekb/ptzyk/index";
	}
	
	/**
	 * 列表显示页面
	 * 
	 * @param model
	 */
	@RequestMapping(value = "search")
	public String search(HttpServletRequest request, Model model) {
		model.addAttribute("m2", request.getParameter("m2"));
		model.addAttribute("m1_1", request.getParameter("effectLevel"));
		model.addAttribute("wfwurl", Global.getWfwurl());
		return "sekb/ptzyk/searchIndex";
	}

	/**
	 * 列表显示页面
	 *
	 * @param model
	 */
	@RequestMapping(value = "index2")
	public String index2(Model model) {
		model.addAttribute("wfwurl", Global.getWfwurl());
		return "sekb/ptzyk/searchIndex";
	}

	/**
	 * 获取远程服务器pdf的文件流
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/getPdf")
	@ResponseBody
	public void  getPdfInfo(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
		String pdfUrl= URLDecoder.decode(request.getParameter("pdfurl"), "UTF-8");;
		if(!StringUtils.isBlank(pdfUrl)){
			InputStream fis=null;
			try {
				try {
					request.setCharacterEncoding("utf-8");
					response.setCharacterEncoding("utf-8");
					URL url=new URL(pdfUrl);
					//打开请求连接
					URLConnection connection = url.openConnection();
					HttpURLConnection conn=(HttpURLConnection) connection;
					conn.setConnectTimeout(20000);
					conn.setReadTimeout(20000);
					conn.connect();
					response.setContentType("application/pdf");
					ServletOutputStream sos=response.getOutputStream();
					fis=conn.getInputStream();
					int b;
					while((b=fis.read())!=-1){
						sos.write(b);
					}
					sos.close();
					fis.close();
				} catch (IOException e) {
					System.out.println(e.getMessage());
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}

}
