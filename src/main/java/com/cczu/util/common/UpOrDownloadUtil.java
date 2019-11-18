package com.cczu.util.common;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.FileCleanerCleanup;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileCleaningTracker;

/**
 * 文件上传工具类 ，支持多文件上传
 * @author jason
 *
 */
public class UpOrDownloadUtil {
	/**
	 * 存放文件,主要目的用于先接收到text文本参数后,再从files中执行文件保存
	 */
	List<FileItem> mfiles = new ArrayList<FileItem>();
	/** 存放客户端发送的文本参数 **/
	Map<String, String> textParams = new HashMap<String, String>();
	/** 存储保存后的文件路径 **/
	List<String> filePaths = new ArrayList<String>();

	/**
	 * 文件上传完成后,回调此接口中的方法
	 */
	public interface UpCompleteListener {
		void complete(List<String> filePaths, Map<String, String> text);
	}

	UpCompleteListener mlistener = null;

	private HttpServletRequest request;

	public UpOrDownloadUtil(HttpServletRequest req) {
		this.request = req;
	}

	/**
	 * 用于文件上传,支持多文件上传<br>
	 * @param request
	 * @param fileExt   目标保存文件类型,如: txt xls doc pdf ,如果fileEXt为空或null则默认保存所有文件
	 * @param savePath  设置文件保存目录,默认为项目 WEB-INF/files 下
	 * @return  返回上传的文件的 总数
	 * @throws IOException
	 * @throws FileNotFoundException
	 *            
	 */
	public int upload(String fileExt, String savePath,
			UpCompleteListener mlistener) {
		this.mlistener = mlistener;

		List<String> paths = multipleFileUp(request, fileExt, savePath);
		return paths.size();
	}

	/**
	 * 获取客户端请求的 文本,key 参数名, value 为参数值<br>
	 */
	public Map<String, String> getTextParams() throws SocketTimeoutException {
		if (!textParams.isEmpty()) {
			return textParams;
		}
		ServletContext context = request.getSession().getServletContext();
		// 设置清空临时文件
		FileCleaningTracker fileCleaningTracker = FileCleanerCleanup
				.getFileCleaningTracker(context);
		DiskFileItemFactory factory = new DiskFileItemFactory();
		factory.setFileCleaningTracker(fileCleaningTracker);
		// 获取项目目录
		String projDir = request.getSession().getServletContext().getRealPath("/");

		File tempFile = new File(projDir + "filetemp"); // 创建临时文件目录
		// 如果临时目录不存在则创建
		if (!tempFile.exists()) {
			tempFile.mkdirs();
		}
		factory.setRepository(tempFile); // 设置临时文件目录

		ServletFileUpload upload = new ServletFileUpload(factory);
		upload.setHeaderEncoding("UTF-8");
		try {
			List<FileItem> list = upload.parseRequest(request);
			for (FileItem fileItem : list) {
				if (fileItem.isFormField()) {
					textParams.put(fileItem.getFieldName(),
							fileItem.getString("UTF-8"));
				} else {
					mfiles.add(fileItem);
				}
			}
		} catch (FileUploadException e) { // FileUploadException
											// UnsupportedEncodingException
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) { // FileUploadException
													// UnsupportedEncodingException
			e.printStackTrace();
		}
		return textParams;
	}

	/**
	 * 保存上传的文件
	 * @param savedir    文件保存的路径
	 * @return
	 */
	public List<String> getFileParams(String savedir)
			throws SocketTimeoutException {
		if (filePaths.size() > 0) {
			return filePaths;
		}
		getTextParams();
		try {
			String saveFilePath = null;
			File saveFiledDir = new File(savedir);
			// 如果文件不存在则创建
			if (!saveFiledDir.exists()) {
				saveFiledDir.mkdirs();
			}
			for (FileItem fileItem : mfiles) {
				String filename = fileItem.getName();
				// 不包括路径的文件名
				filename = filename.substring(filename
						.lastIndexOf(File.separator) + 1);

				if (filename != null && !filename.equals("")) {
					// 保存在服务器上的文件路径,包括文件名
					String time = (new SimpleDateFormat(
							"yyyy_MM_dd_HH_mm_ss_SSS", Locale.CHINA))
							.format(new Date());

					saveFilePath = saveFiledDir + "\\" + time + "_" + filename;
					InputStream in = fileItem.getInputStream();
					FileOutputStream fos = new FileOutputStream(saveFilePath);
					byte[] buf = new byte[1024];
					int len = -1;
					while ((len = in.read(buf)) != -1) {
						fos.write(buf, 0, len);
						fos.flush();
					}
					// 关闭流
					if (in != null) {
						in.close();
					}
					if (fos != null) {
						fos.close();
					}
					filePaths.add(saveFilePath);
					fileItem.delete();// 删除临时目录中的文件
				}
				fileItem.delete();// 删除临时目录中的文件
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return filePaths;
	}

	/**
	 * 用于文件上传,支持多文件上传<br>
	 * @param request
	 * @param fileExt   目标保存文件类型,如: txt xls doc pdf ,如果fileEXt为空或null则默认保存所有文件
	 * @param savePath  设置文件保存目录,默认为项目 WEB-INF/files 下
	 * @return 返回所有保存在服务器上的全路径集合,包括文件名
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
	private List<String> multipleFileUp(HttpServletRequest request,
			String fileExt, String savePath) {

		String saveFilePath = null;
		List<String> filePaths = new ArrayList<String>();
		Map<String, String> map = new HashMap<String, String>();

		// 创建文件处理工厂,用于生成FileItem对象
		DiskFileItemFactory diskFactory = new DiskFileItemFactory();

		// 设置缓存大小,如果上传文件超过缓存大小，将使用临时目录做为缓存。
		// diskFactory.setSizeThreshold(1024*1024); 不建议设置

		// 设置处理工厂缓存的临时目录,此目录下的文件需要手动删除
		String dir = request.getSession().getServletContext().getRealPath("/");
		// Logger.getLogger("lwl").info("工程目录"+dir);

		File tempfiledir = new File(dir + "filetemp");
		// 文件不存在则创建
		if (!tempfiledir.exists()) {
			tempfiledir.mkdir();
		}
		// 设置缓冲区
		diskFactory.setRepository(tempfiledir);

		// 设置文件实际保存目录
		if (savePath == null || savePath.trim().equals("")) {
			savePath = dir + "WEB-INF/files";
		}

		File saveFiledDir = new File(savePath);
		// 如果文件不存在则创建
		if (!saveFiledDir.exists()) {
			saveFiledDir.mkdirs();
		}
		// 创建request的解析器,它会将数据封装到FileItem对象中
		ServletFileUpload servletFileUpload = new ServletFileUpload(diskFactory);
		/*
		 * servletFileUpload.setProgressListener(new ProgressListener() { public
		 * void update(long pBytesRead, long pContentLength, int arg2) {
		 * System.out.println("已上传：" + pBytesRead + "总大小："+
		 * pContentLength+"不知道"+arg2); } });
		 */

		// 解析保存在request中的数据并返回List<FileItem>集合
		List<FileItem> list = null;
		try {
			servletFileUpload.setHeaderEncoding("UTF-8");
			list = servletFileUpload.parseRequest(request);
			// 遍历list集合,取出每一个输入项的FileItem对象,并取出数据
			for (FileItem fileItem : list) {
				if (fileItem.isFormField()) {
					// 普通文本
					map.put(fileItem.getFieldName(),
							fileItem.getString("UTF-8"));

					System.out.println(fileItem.getFieldName());

				} else {
					// 文件类型
					// Logger.getLogger("lwl").info("文件类型");
					// 包括路径的文件名
					String filename = fileItem.getName();
					// Logger.getLogger("lwl").info("fileItem.getName()"+filename);
					// 不包括路径的文件名
					filename = filename.substring(filename
							.lastIndexOf(File.separator) + 1);

					if (filename != null && !filename.equals("")) {
						if (fileExt != null && !fileExt.trim().equals("")) {
							if (!filename.substring(
									filename.lastIndexOf(".") + 1).equals(
									fileExt)) {
								saveFilePath = null;
								continue;
							}
						}
						// 保存在服务器上的文件路径,包括文件名
						String time = (new SimpleDateFormat(
								"yyyy_MM_dd_HH_mm_ss_SSS", Locale.CHINA))
								.format(new Date());

						saveFilePath = saveFiledDir + "\\" + time + "_"
								+ filename;
						InputStream in = fileItem.getInputStream();
						FileOutputStream fos = new FileOutputStream(
								saveFilePath);
						byte[] buf = new byte[1024];
						int len = -1;
						while ((len = in.read(buf)) != -1) {
							fos.write(buf, 0, len);
							fos.flush();
						}
						// 关闭流
						if (in != null) {
							in.close();
						}
						if (fos != null) {
							fos.close();
						}
						filePaths.add(saveFilePath);
					}
				}
				fileItem.delete();// 删除临时目录中的文件
			}
		} catch (FileUploadException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (mlistener != null) {
			mlistener.complete(filePaths, map);
		}

		return filePaths;
	}

	/**
	 * 文件下载
	 * 
	 * @param response
	 * @param path  文件路径
	 * @param file    将要被下载的文件名,带有后缀名
	 * @param fileShowName     显示个用户的文件名,带有后缀名
	 */
	public void down(HttpServletResponse response, String path, String name,
			String fileShowName) {
		String filePath = path + "\\" + name;
		// Logger.getLogger("lwl").info(filePath);
		File file = new File(filePath);
		try {
			if (!file.exists()) {// 判断文件是否存在
				response.sendError(404, "File not found!");
				return;
			}

			fileShowName = new String(fileShowName.getBytes("gbk"), "ISO8859-1");

			InputStream instream = new FileInputStream(filePath);
			BufferedInputStream bufStream = new BufferedInputStream(instream);
			// 设置输出格式
			response.reset();
			response.setContentType("bin");
			// response.setContentType("application/x-msdownload");
			// response.setHeader("Content-Length", "");
			response.addHeader("Content-Disposition", "attachment; filename=\""
					+ fileShowName + "\"");

			byte[] by = new byte[1024];

			ServletOutputStream servletOut = response.getOutputStream();
			while (bufStream.read(by) > 0) {
				servletOut.write(by);
				servletOut.flush();
			}
			servletOut.close();
			bufStream.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * 文件下载,支持部分文件在线打开
	 * 
	 * @param filePath      文件路径
	 * @param response
	 * @param isOnLine      是否在线打开, 支持txt,doc 视浏览器而定
	 * @throws Exception
	 */
	public void downLoad(String filePath, HttpServletResponse response,
			boolean isOnLine) {
		try {
			File f = new File(filePath);
			if (!f.exists()) {
				response.sendError(404, "File not found!");
				return;
			}
			BufferedInputStream br = new BufferedInputStream(
					new FileInputStream(f));
			byte[] buf = new byte[1024];
			int len = 0;

			response.reset(); // 非常重要
			if (isOnLine) { // 在线打开方式
				URL u = new URL("file:///" + filePath);
				response.setContentType(u.openConnection().getContentType());
				// response.setContentType("bin");
				response.setCharacterEncoding("GBK");
				String fileName = new String(f.getName().getBytes("gbk"),
						"ISO8859-1");

				System.out.println(f.getName());
				// filename = URLEncoder.encode(filename, "UTF-8");//IE浏览器
				response.setHeader("Content-Disposition",
						"inline;charset=ISO8859-1; filename=" + fileName);

			} else { // 纯下载方式

				response.setContentType("application/x-msdownload");
				response.addHeader("Content-Type", "text/html; charset=GBK");

				response.setHeader("Content-Disposition",
						"attachment;filename=" + f.getName());
			}
			OutputStream out = response.getOutputStream();
			while ((len = br.read(buf)) > 0) {
				out.write(buf, 0, len);
				out.flush();
			}
			br.close();
			out.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 删除指定目录下的所有空子文件夹
	 * 
	 * @param rootPath
	 *            要删除的目录 请勿以\\结尾，及最后一层目录后的分隔符不要
	 */
	public static void delEmptyChildsDir(String rootPath) {
		List<File> list = getAllNullDirectorys(new File(rootPath));
		// System.out.println("---------------" + list.size());
		// for (int i = 0; i < list.size(); i++) {
		// System.out.println(list.get(i).getPath());
		// }
		removeNullFile(list, rootPath);
	}

	/**
	 * 递归列出某文件夹下的最深层的空文件夹绝对路径，储存至list
	 * 
	 * @param root
	 * @return
	 */
	private static List<File> getAllNullDirectorys(File root) {
		List<File> list = new ArrayList<File>();
		File[] dirs = root.listFiles();
		if (dirs != null) {
			for (int i = 0; i < dirs.length; i++) {
				if (dirs[i].isDirectory() && dirs[i].listFiles().length == 0) {
					// System.out.println("name:" + dirs[i].getPath());
					list.add(dirs[i]);
				}
				// if(dirs[i].isFile()){
				// System.out.println("文件:"+dirs[i].getPath());
				// }
				list.addAll(getAllNullDirectorys(dirs[i]));
			}
		}
		return list;
	}

	/**
	 * 由最深一层的空文件，向上（父文件夹）递归，删除空文件夹
	 * 
	 * @param list
	 * @param rootPath
	 */
	private static void removeNullFile(List<File> list, String rootPath) {
		if (list == null || list.size() == 0) {
			return;
		}
		List<File> plist = new ArrayList<File>();
		for (int i = 0; i < list.size(); i++) {
			File temp = list.get(i);
			if (temp.isDirectory() && temp.listFiles().length <= 0) {
				temp.delete();
				// System.out.println("parent:" +
				// temp.getParentFile().getPath());
				File pFile = temp.getParentFile();
				if (pFile.getPath().equals(rootPath)) {
					continue;
				}
				if (!plist.contains(pFile)) {// 父目录去重添加
					plist.add(pFile);
				}
			}
		}
		removeNullFile(plist, rootPath);
	}

}
