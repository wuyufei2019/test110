package com.cczu.model.service;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cczu.model.dao.FxgkFxxxDao;
import com.cczu.model.dao.FxgkFxzpzDao;
import com.cczu.sys.comm.utils.DateUtils;
import com.cczu.sys.comm.utils.StringUtils;

/**
 * 事故风险Service
 *
 */
@Transactional(readOnly=true)
@Service("SgfxfxytService")
public class FxgkFxjgService {
	@Resource
	private FxgkFxxxDao fxgkFxxxDao;
	
	@Resource
	private FxgkFxzpzDao fxgkfxzpzdao;
	

	
	//查询企业所有信息,分页查询
	public Map<String,Object> dateGrid(Map<String, Object> mapData){
		List<Map<String, Object>> listm=fxgkFxxxDao.getAllRLQylistDataGrid(mapData);
		int getTotalCount=fxgkFxxxDao.getTotalCountList(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", listm);
		map.put("total", getTotalCount);
		return map;
	}
	
	
	/**
	 * 根据id1查询该企业所有平面点
	 * @return
	 */
	public List<Map<String, Object>> findPmtByQyid(Long id) {
		return fxgkFxxxDao.findPmtByQyid(id);
	}
	
	/**
	 * 生成平面图并下载
	 * @param id 企业id
	 * @param str 项目物理路径
	 * @return
	 */
	public void xzPmtByQyid(Long id,String str,HttpServletResponse response) throws Exception{
		OutputStream os = response.getOutputStream();// 取得输出流        
	    response.reset();// 清空输出流        
	    response.setHeader("Content-disposition", "attachment; filename="+ new String((DateUtils.getDateRandom()+".png").getBytes("UTF-8"),"ISO8859-1"));  // 设定输出文件头        
	    response.setContentType("application/x-msdownload");//("application/msexcel");// 定义输出类型      
		  
		List<Map<String, Object>> list=fxgkFxxxDao.findPmtByQyid(id);
		if(list.size()>0){
			Map<String, Object> map=list.get(0);
			String srcImage[]=map.get("pmt").toString().split("[||]");
			String srcImageFile=str+srcImage[0];
			//获取企业平面图作为底图
            Image src = ImageIO.read(new File(srcImageFile));
            int wideth = src.getWidth(null);
            int height = src.getHeight(null);
            BufferedImage image = new BufferedImage(wideth, height,
                    BufferedImage.TYPE_INT_RGB);
            Graphics2D g = image.createGraphics();
            g.drawImage(src, 0, 0, wideth, height, null);
            //循环向底图上添加风险点图标
			for(Map<String, Object> m:list){
				float locx=StringUtils.toFloat(m.get("locx"));
				float locy=StringUtils.toFloat(m.get("locy"));
				if(locx==0||locy==0)
					continue;
				int color=Integer.parseInt(m.get("color").toString());
				String pressImg="";
				String colorimg="";
				if(color==1){
					colorimg="static/model/images/fxgk/red.png";
				}else if(color==2){
					colorimg="static/model/images/fxgk/ora.png";
				}else if(color==3){
					colorimg="static/model/images/fxgk/yel.png";
				}else if(color==4){
					colorimg="static/model/images/fxgk/blu.png";
				}
				pressImg = str + colorimg;
	            // 水印文件
	            Image src_biao = ImageIO.read(new File(pressImg));
	            int wideth_biao = src_biao.getWidth(null);
	            int height_biao = src_biao.getHeight(null);
	            g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP,1));
	            //不断循环布点
	            g.drawImage(src_biao, (int)(wideth * locx),(int)(height * locy), wideth_biao, height_biao, null);
			}
			 // 水印文件结束
            g.dispose();
            ImageIO.write((BufferedImage) image,  "JPEG", os);
		}
	}
	
	//根据企业id查询企业所有信息
	public Map<String,Object> getQyData(Map<String, Object> mapData){
		Map<String, Object> map=fxgkFxxxDao.getAllRLQylistDataGrid(mapData).get(0);
		return map;
	}
	//根据企业id查询企业的所有事故类型
	public String getSgtypeByQyid(Map<String, Object> mapData){
		List<Map<String, Object>> list=fxgkFxxxDao.getSgtypeByqyid(mapData.get("qyid").toString());
		Map<String, Object> remap = new HashMap<String, Object>();
		for(Map<String, Object> map:list){
			String[] strs=map.get("m8").toString().split(",");
			for(String s:strs){
				remap.put(s,s);
			}
		}
		return remap.keySet().toString();
	}

}  