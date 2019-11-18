package com.cczu.model.service;

import com.cczu.model.dao.FxgkYjczkDao;
import com.cczu.model.entity.FXGK_Yjczk;
import com.cczu.sys.comm.utils.DateUtils;
import com.cczu.sys.comm.utils.ExinExcel;
import com.cczu.sys.comm.utils.ExportExcel;
import com.cczu.sys.system.entity.User;
import com.cczu.sys.system.utils.UserUtil;
import org.apache.commons.lang3.StringEscapeUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 
 * @Description: 应急处置卡信息Service
 * @author: wbth
 * @date: 2019年8月28日
 */
@Transactional(readOnly=true)
@Service("FxgkYjczkService")
public class FxgkYjczkService {
	//处理富文本
	private static final String regEx_style = "<style[^>]*?>[\\s\\S]*?<\\/style>"; // 定义style的正则表达式
	private static final String regEx_html = "<[^>]+>"; // 定义HTML标签的正则表达式
	private static final String regEx_space = "\\s*|\t|\r|\n";//定义空格回车换行符

	@Resource
	private FxgkYjczkDao fxgkYjczkDao;
	
	/**
	 * 分页显示
	 * @param mapData
	 * @return
	 */
	public Map<String, Object> dataGrid(Map<String, Object> mapData) {
		
		List<FXGK_Yjczk> list=fxgkYjczkDao.dataGrid(mapData);
		int getTotalCount=fxgkYjczkDao.getTotalCount(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}

	/**
	 * 安监分页显示
	 * @param mapData
	 * @return
	 */
	public Map<String, Object> dataGrid2(Map<String, Object> mapData) {

		List<Map<String, Object>> list=fxgkYjczkDao.dataGrid2(mapData);
		int getTotalCount=fxgkYjczkDao.getTotalCount2(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}
	
	public int getTotalCount(Map<String, Object> mapData){
		return fxgkYjczkDao.getTotalCount(mapData);
	}

	
	public void addInfo(FXGK_Yjczk bis) {
		fxgkYjczkDao.save(bis);
	}
	
	//根据id查询详细信息
	public FXGK_Yjczk findById(Long id) {
		
		return fxgkYjczkDao.find(id);
	}

	//根据id查询word中要导出的信息
	public Map<String, Object> getWord(Long id) {
		Map<String, Object> map = fxgkYjczkDao.findMapById(id);
		if(map.get("m2") != null && map.get("m2") != ""){
			String newM2 = "";
			String oldM2 = map.get("m2").toString();
			String[] zs = oldM2.split("</p>");
			for(int i=0;i<zs.length;i++){
				newM2 += StringEscapeUtils.escapeHtml3(this.getTextFromHtml(zs[i]))+"<w:p />";
			}
			map.put("m2", newM2);
		}
		if(map.get("m3") != null && map.get("m3") != ""){
			String newM3 = "";
			String oldM3 = map.get("m3").toString();
			String[] zs = oldM3.split("</p>");
			for(int i=0;i<zs.length;i++){
				newM3 += StringEscapeUtils.escapeHtml3(this.getTextFromHtml(zs[i]))+"<w:p />";
			}
			map.put("m3", newM3);
		}
		return map;
	}

	public String delHTMLTag(String htmlStr) {
		Pattern p_style = Pattern.compile(regEx_style, Pattern.CASE_INSENSITIVE);
		Matcher m_style = p_style.matcher(htmlStr);
		htmlStr = m_style.replaceAll(""); // 过滤style标签

		Pattern p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
		Matcher m_html = p_html.matcher(htmlStr);
		htmlStr = m_html.replaceAll(""); // 过滤html标签

		Pattern p_space = Pattern.compile(regEx_space, Pattern.CASE_INSENSITIVE);
		Matcher m_space = p_space.matcher(htmlStr);
		htmlStr = m_space.replaceAll(""); // 过滤空格回车标签
		return htmlStr.trim(); // 返回文本字符串
	}

	public String getTextFromHtml(String htmlStr){
		htmlStr = this.delHTMLTag(htmlStr);
		htmlStr = htmlStr.replaceAll("&nbsp;", "");
		// htmlStr = htmlStr.substring(0, htmlStr.indexOf("。")+1);
		return htmlStr;
	}
	
	//更新信息
	public void updateInfo(FXGK_Yjczk bis) {
		fxgkYjczkDao.save(bis);
	}
	
	//删除信息
	@Transactional(readOnly=false)
	public void deleteInfo(long id) {
		fxgkYjczkDao.deleteInfo(id);
	}

	/**
	 * 导出
	 * @param response
	 * @param mapData
	 */
	public void exportExcel(HttpServletResponse response, Map<String, Object> mapData) {
		String fileName="应急处置卡信息表.xls";
		List<Map<String, Object>> list=fxgkYjczkDao.getExport(mapData);
		String[] title=mapData.get("coltext").toString().split(",");
		String[] keys=mapData.get("colval").toString().split(",");
		new ExportExcel(fileName, title, keys, list, response, true);
	}

	/**
	 * 导入
	 * @param map
	 */
	public Map<String,Object> exinExcel(Map<String, Object> map) {
		// TODO Auto-generated method stub
		Map<String,Object> resultmap = new HashMap<String, Object>();
		int result = 0;
		ExinExcel exin = new ExinExcel();
		List<List<Object>> list = exin.exinExcel(map.get("filename").toString(), (InputStream) map.get("content"));
		int i = 0, error = 0;
		if (list.size() > 3) {
			result = 0;
			resultmap.put("total", list.size()-3);
			resultmap.put("returncode", "success");
			for (List<Object> bis : list) {
				if(i<=2){ //跳过前三行
					i++;
					continue;
				}
				try{
					FXGK_Yjczk hxny = new FXGK_Yjczk();
					Timestamp t = DateUtils.getSysTimestamp();
					User user=UserUtil.getCurrentUser();//获取当前登录用户的对象
					long id2=user.getId();
					hxny.setS1(t);
					hxny.setS2(t);
					hxny.setS3(0);
					hxny.setQyid(Long.valueOf(map.get("qyid").toString()));
					hxny.setM1(bis.get(0).toString());
					hxny.setM2(bis.get(1).toString());
					hxny.setM3(bis.get(2).toString());
					fxgkYjczkDao.save(hxny);
					result++;
				}catch(Exception e){
					error++;
				}
				resultmap.put("success",result);
				resultmap.put("error", error);
			}
		}else if(list.size()==3){
			resultmap.put("success",result);
			resultmap.put("error", error);
			resultmap.put("returncode", "warn");
		}else if(list.size()<3){
			resultmap.put("success",result);
			resultmap.put("error", error);
			resultmap.put("returncode", "ext");
		}
		if(Integer.valueOf(resultmap.get("success").toString())==0){
			resultmap.put("returncode", "warn");
		}
		return resultmap;

	}

}
