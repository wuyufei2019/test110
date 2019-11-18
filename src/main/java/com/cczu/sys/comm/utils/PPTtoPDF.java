package com.cczu.sys.comm.utils;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.poi.hslf.model.Slide;
import org.apache.poi.hslf.model.TextRun;
import org.apache.poi.hslf.usermodel.RichTextRun;
import org.apache.poi.hslf.usermodel.SlideShow;

import com.itextpdf.text.Document;
import com.itextpdf.text.Image;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfWriter;

public class PPTtoPDF {
	
	/**
	 * ppt转图片 
	 * @param filepath ppt文件路径
	 * @param outpath 生成图片保存路径
	 * @return List 图片完整路径集合
	 */
	public static List<String> doPPTtoImage(String filepath,String outpath) {
		List<String> imgpathlist=new ArrayList<String>();
		
		File file = new File(filepath);
		if (!checkFile(file)) {
			System.out.println("指定的文件不是ppt文档!");
			return null;
		}
		try {
			FileInputStream is = new FileInputStream(file);
			SlideShow ppt = new SlideShow(is);
			is.close();
			Dimension pgsize = ppt.getPageSize();
			Slide[] slide = ppt.getSlides();
			for (int i = 0; i < slide.length; i++) {
				TextRun[] truns = slide[i].getTextRuns();
				for (int k = 0; k < truns.length; k++) {
					RichTextRun[] rtruns = truns[k].getRichTextRuns();
					for (int l = 0; l < rtruns.length; l++) {
						rtruns[l].setFontIndex(1);
						rtruns[l].setFontName("宋体");
					}
				}
				BufferedImage img = new BufferedImage(pgsize.width, pgsize.height, BufferedImage.TYPE_INT_RGB);
				Graphics2D graphics = img.createGraphics();
				graphics.setPaint(Color.white);
				graphics.fill(new Rectangle2D.Float(0, 0, pgsize.width,
						pgsize.height));
				slide[i].draw(graphics);
				// 这里设置图片的存放路径和图片的格式(jpeg,png,bmp等等),注意生成文件路径
				String random=DateUtils.getDateRandom();
				FileOutputStream out = new FileOutputStream(outpath+random+".png");
				javax.imageio.ImageIO.write(img, "png", out);
				imgpathlist.add(outpath+random+".png");
				out.close();
				
			}
			return imgpathlist;
		} catch (FileNotFoundException e) {
			System.out.println(e);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 检查文件是否为PPT
	 * @param file
	 * @return
	 */
	public static boolean checkFile(File file) {
		boolean isppt = false;
		String filename = file.getName();
		String suffixname = null;
		if (filename != null && filename.indexOf(".") != -1) {
			suffixname = filename.substring(filename.indexOf("."));
			if (suffixname.equals(".ppt")) {
				isppt = true;
			}
		} 
		return isppt;
	}
	
	 /**
	  * 图片转PDF
	  * @param filepaths 图片路径集合
	  * @param outpath PDF保存文件夹
	  * @return PDF文件名
	  */
	public static String imgtopdf(List<String> filepaths,String outpath){
		  Document document =null;
		  String filename=DateUtils.getDateRandom()+".pdf";
	        try {
	        	 Image image = Image.getInstance(filepaths.get(0));
	             float width = image.getWidth();
	             float height = image.getHeight();
	             document = new Document(null, 0, 0, 0, 0);
	             document.setPageSize(new Rectangle(width,height));
	             PdfWriter.getInstance(document, new FileOutputStream(outpath+filename));
	            document.open();
	            
	            for(int i=0;i<filepaths.size();i++) {
	            Image jpg = Image.getInstance(filepaths.get(i));
	            document.add(jpg);
	            }   
	            document.close();
		        return filename;
	        }
	        catch(Exception e) {
	            System.err.println(e.getMessage());
	        }
	        document.close();
	        return "";
	}
	
	/**
	 * PPT转PDF
	 * @param filepath ppt文件完整路径
	 * @param tempPath 生成图片临时保存路径 已“/”结尾 ，例 "d:/tempPath/"
	 * @param outpath  PDF保存文件夹 已“/”结尾 ，例 "d:/upload/"
	 * @return  PDF文件名
	 */
	public static String ppttopdf(String filepath,String tempPath,String outpath){
		List<String> list=doPPTtoImage(filepath, tempPath);
		String pdfpath=imgtopdf(list, outpath);
		return pdfpath;
		
	}
	
	public static void main(String[] args) {
 
	}
}
