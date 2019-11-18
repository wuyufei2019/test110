package com.cczu.model.xfssgl.service;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cczu.model.xfssgl.dao.XfssglSbfbDao;
import com.cczu.sys.comm.utils.DateUtils;
import com.cczu.sys.comm.utils.StringUtils;

/**
 * 
 * @author wbth
 * @date 2018年4月23日
 */
@Transactional(readOnly=true)
@Service("XfssglSbfbService")
public class XfssglSbfbService {
	@Resource
	private XfssglSbfbDao xfssglSbfbDao;
	
	//获取消防设施
	public List<Map<String, Object>> getAllByQyid(Map<String, Object> mapData) {
		List<Map<String, Object>> list=xfssglSbfbDao.getAllByQyid(mapData);
		return list;
	}
	
	/**
	  * 查询有消防设施的企业列表
	  * @return
	  */
	 public List<Map<String, Object>> findQyList(Map<String, Object> mapData){
		 return xfssglSbfbDao.findQyList(mapData);
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
		  
		List<Map<String, Object>> list=xfssglSbfbDao.findPmtByQyid(id);
		if(list.size()>0){
			Map<String, Object> map=list.get(0);
			String srcImage[]=map.get("pmt").toString().split("[||]");
			String srcImageFile=str+srcImage[0];
			/*String srcImageFile=(str+(srcImage[0].substring(6)));*///本地测试
			//获取企业平面图作为底图
            Image src = ImageIO.read(new File(srcImageFile));
            int wideth = src.getWidth(null);
            int height = src.getHeight(null);
            BufferedImage image = new BufferedImage(wideth, height,
                    BufferedImage.TYPE_INT_RGB);
            Graphics2D g = image.createGraphics();
            g.drawImage(src, 0, 0, wideth, height, null);
            //循环向底图上添加消防设施图标
			for(Map<String, Object> m:list){
				float locx=StringUtils.toFloat(m.get("x"));
				float locy=StringUtils.toFloat(m.get("y"));
				if(locx==0||locy==0)
					continue;
				//icon为图标名称
				String icon = m.get("icon").toString();
				String ys = "";
				//根据设施名称寻找对应名称的图标
		        if (icon != null && !"".equals(icon)) ys = icon;
		        else ys = "其他";
				String pressImg="";
				String sbimg="";
				sbimg="static/model/images/xfssgl/"+ys;
				pressImg = str + sbimg;
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
}
