package com.cczu.sys.comm.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class ExinExcel {

	public ExinExcel() {
	}

	/// 根据字符串路径进行导入
	public List<List<Object>> exinExcel(String f) {
		String e = f.lastIndexOf('.') == -1 ? "" : f.substring(f.lastIndexOf('.') + 1);
		if ("xls".equals(e)) {
			return readExcel(f);
		} else if ("xlsx".equals(e)) {
			return readExcel(f);
		} else {
			List<Object> l = new ArrayList<Object>();
			l.add("不支持文件");
			List<List<Object>> ll = new ArrayList<List<Object>>();
			ll.add(l);
			return ll;
		}
	}

	/// 根据文件进行导入
	public List<List<Object>> exinExcel(File file) {
		String f = file.getName();
		String e = f.lastIndexOf('.') == -1 ? "" : f.substring(f.lastIndexOf('.') + 1);
		if ("xls".equals(e)) {
			return readExcel(f);
		} else if ("xlsx".equals(e)) {
			return readExcel(f);
		} else {
			List<Object> l = new ArrayList<Object>();
			l.add("不支持文件");
			List<List<Object>> ll = new ArrayList<List<Object>>();
			ll.add(l);
			return ll;
		}
	}

	/// 根据文件进行导入
	public List<List<Object>> exinExcel(String name, InputStream istream) {
		String e = name.lastIndexOf('.') == -1 ? "" : name.substring(name.lastIndexOf('.') + 1);
		if ("xls".equals(e)) {
			return readExcel(istream);
		} else if ("xlsx".equals(e)) {
			return readExcel(istream);
		} else {
			List<Object> l = new ArrayList<Object>();
			l.add("不支持文件");
			List<List<Object>> ll = new ArrayList<List<Object>>();
			ll.add(l);
			return ll;
		}
	}

	List<List<Object>> readExcel(String dir) {
		InputStream iss = null;
		try {
			iss = new FileInputStream(dir);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			iss = null;
			e.printStackTrace();
		}
		if (iss != null) {
			return read2003Exel(iss);
		} else

		{
			return null;
		}
	}

	List<List<Object>> readExcel(InputStream istream) {
		return read2003Exel(istream);
	}

	/// 读取xls
	List<List<Object>> read2003Exel(InputStream iss) {
		List<List<Object>> list = new LinkedList<List<Object>>();
		try {
			HSSFWorkbook work = new HSSFWorkbook(iss);
			HSSFSheet sheet = work.getSheetAt(0);
			Object obj = null;
			HSSFRow row = null;
			HSSFCell cell = null;
			int count = 0;
			int colNum = 0;
			for (int i = sheet.getFirstRowNum(); i <= sheet.getLastRowNum(); i++) {
				row = sheet.getRow(i);
				if (row == null) {
					continue;
				} else if (row.getCell(0) == null) {
					continue;
				} else {
					count++;
				}
				if (i == 1) {
					colNum = row.getLastCellNum();
				}
				List<Object> link = new LinkedList<Object>();
				for (int j = row.getFirstCellNum(); j <= colNum; j++) {
					cell = row.getCell(j);
					if (cell == null) {
						cell = row.getCell(0);
						cell.setCellType(HSSFCell.CELL_TYPE_BLANK);
					}
					DecimalFormat df = new DecimalFormat("0");// 格式化 number
					// 字符
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 格式化日期字符串
					//DecimalFormat nf = new DecimalFormat("0.00");// 格式化数字
					switch (cell.getCellType()) {
					case HSSFCell.CELL_TYPE_STRING:
						obj = cell.getStringCellValue();
						break;
					case HSSFCell.CELL_TYPE_NUMERIC:
						if ("@".equals(cell.getCellStyle().getDataFormatString())) {
							obj = df.format(cell.getNumericCellValue());
						} else if (HSSFDateUtil.isCellDateFormatted(cell)) {
							obj = sdf.format(HSSFDateUtil.getJavaDate(cell.getNumericCellValue())).toString();
						} else { // 纯数字
							obj = String.valueOf(cell.getNumericCellValue());
						}
						break;
					case HSSFCell.CELL_TYPE_BOOLEAN:
						obj = cell.getBooleanCellValue();
						break;
					case HSSFCell.CELL_TYPE_BLANK:
						obj = "";
						break;
					default:
						obj = cell.toString();
					}
					link.add(obj);
				}
				list.add(link);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	/// 读取xlsx
	// List<List<Object>> read2007Exel(String dir) {
	//
	// }
}
