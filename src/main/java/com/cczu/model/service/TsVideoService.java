package com.cczu.model.service;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.cczu.util.entity.TreeNode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cczu.model.dao.TsVideoDao;
import com.cczu.model.entity.TS_Video;
import com.cczu.sys.comm.mapper.JsonMapper;
import com.cczu.sys.comm.utils.Global;

/**
 * 视频监控Service
 * @author jason
 * @date 2017年9月6日
 */
@Transactional(readOnly=true)
@Service("TsVideoService")
public class TsVideoService  {
	@Resource
	TsVideoDao tsVideoDao;

	public void addInfo(TS_Video bis) {
		bis.setUrl(Global.getVideoHttp()+"/"+bis.getName()+"/"+bis.getName()+".m3u8");
		getAddVideoApi(bis);
		tsVideoDao.save(bis);
	}
	
	public void updateInfo(TS_Video bis) {
		bis.setUrl(Global.getVideoHttp()+"/"+bis.getName()+"/"+bis.getName()+".m3u8");
		getAddVideoApi(bis);
		tsVideoDao.save(bis);
	}
	
	public void deleteInfo(long id) {	
		TS_Video ts=tsVideoDao.find(id);
		getDeleteVideoApi(ts.getName());
		tsVideoDao.deleteInfo(id);
	}
	
	public TS_Video findById(long id){
		return tsVideoDao.find(id);
	}
	
	public List<TS_Video> findAllList(){
		List<TS_Video> list=tsVideoDao.findAll();
		return list;
	}
	
	/**
	 * 根据企业id查询视频监控
	 * @param qyid
	 * @return
	 */
	public String findByQyid(long qyid){
		List<TS_Video> list=tsVideoDao.findByQyid(qyid);
		List<Map<String, Object>> rslist=new ArrayList<>();
		for(TS_Video ts:list){
			Map<String, Object> map=new HashMap<String, Object>();
			map.put("url", ts.getUrl());
			map.put("id", ts.getID());
			rslist.add(map);
		}
		return JsonMapper.getInstance().toJson(rslist);
	}
	
	/**
	  * 查询有视频的企业列表
	  * @return
	  */
	 public List<Map<String, Object>> findQyList(Map<String, Object> mapData){
		 return tsVideoDao.findQyList(mapData);
	 }
	 /**
	  * 查询有视频的企业列表
	  * @return
	  */
	 public List<Map<String, Object>> findQyMapList(Map<String, Object> mapData){
		 return tsVideoDao.findQyMapList(mapData);
	 }
	 
	 /**
	  * 查询视频监控列表
	  * @param mapData
	  * @return
	  */
	 public List<Map<String, Object>> findSpList(Map<String, Object> mapData){
		 return tsVideoDao.findSpList(mapData);
	 }
	 
	 /**
	  * 企业视频信息datalist
	  * @param mapData
	  * @return
	  */
	 public Map<String, Object> dataGrid(Map<String, Object> mapData) {
			
			List<Map<String, Object>> list=tsVideoDao.dataGrid(mapData);
			int getTotalCount=tsVideoDao.getTotalCount(mapData);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("rows", list);
			map.put("total", getTotalCount);
			return map;
	}
	 
	 /**
	  * 判断名称是否存在
	  * @param name
	  * @param id
	  * @return
	  */
	 public boolean isexist(String name,long id){
		 List<TS_Video> list=tsVideoDao.findBy("name", name);
		 tsVideoDao.clear();
		 for(TS_Video ts:list){
			 if(ts.getID()!=id)
				 return true;
		 }
		 return false;
	 }
	 
	 /**
	  * 重新生成视频流文件
	  * @param name
	  * @param id
	  * @return
	  */
	 public void reset(){	
		 List<TS_Video> list=tsVideoDao.findAll();
		 for(TS_Video ts:list){
			 updateInfo(ts);
			 getAddVideoApi(ts);
		 } 
	 }
	 
	 /**
	  * 请求视频流添加接口
	  * @param ts
	  */
	 public void getAddVideoApi(TS_Video ts){
		 //主码流rtsp://admin:xq123456@192.168.200.24:554/h264/ch1/main/av_stream
		 //子码流rtsp://admin:xq123456@192.168.200.24:554/h264/ch1/sub/av_stream
//		 String rtsp="rtsp://"+ts.getUsername()+":"+ts.getPassword()+"@"+ts.getIp()+":"+ts.getPort()+"/h264/ch1/main/av_stream";
		 String rtsp="rtsp://"+ts.getUsername()+":"+ts.getPassword()+"@"+ts.getIp()+"/h264/ch1/sub/av_stream";
		 String path = Global.getVideoApi()+"/api/easyhlsmodule?name="+ts.getName()+"&url="+rtsp;
		 System.out.println(path);
		 try {
		    	URL url = new URL(path);
		    	HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		    	conn.setRequestMethod("GET");
		    	conn.setConnectTimeout(5 * 1000);
		    	conn.getContent();
//		    	InputStream inStream = conn.getInputStream();
//		    	System.out.println(inStream.toString());
//		    	ByteArrayOutputStream outStream = new ByteArrayOutputStream();
//		    	byte[] buffer = new byte[1024];
//		    	int len = 0;
//		    	while ((len = inStream.read(buffer)) != -1)
//		    	{
//		    		outStream.write(buffer, 0, len);
//		    	}
//		    	//得到返回的结果
//		    	String res = outStream.toString();
//		    	System.out.println(res);
	    	}catch(Exception ex){
	    		
	    	}
	 }
	 
	 /**
	  * 请求视频流删除接口
	  * @param ts
	  */
	 public void getDeleteVideoApi(String name){
//		 String path = Global.getVideoApi()+"/api/StopHLS?n1="+name;
//		 
//		 try {
//		    	URL url = new URL(path);
//		    	HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//		    	conn.setRequestMethod("GET");
//		    	conn.setConnectTimeout(5 * 1000);
//		    	conn.getContent();
//	    	}catch(Exception ex){
//	    		
//	    	}
	 }
	 
	 public String getqylistapp(Map<String, Object> tmap) {
			return JsonMapper.toJsonString(tsVideoDao.getqylistapp(tmap));
		}


	/**
	 * 部门页面list内容
	 * @param map
	 * @return
	 */
	public String getJson_list(Map<String, Object> map) {
		List<Map<String,Object>> list=tsVideoDao.dataGrid2(map);
		List<TreeNode> list2=new ArrayList<TreeNode>();
		if(list.size()>0){
			for(Map<String,Object> vedio: list){
				TreeNode dto=new TreeNode();
				dto.setId(vedio.get("id").toString());
				if(vedio.get("fid").toString().equals("0"))
					dto.setPid("0");
				else
					dto.setPid(vedio.get("fid").toString());
				dto.setName(vedio.get("name").toString());//上级名称
				list2.add(dto);
			}
		}
		return commJsonListTree(list2);
	}

	/**
	 * 迭代共通处理
	 * @return
	 */
	public String commJsonListTree(List<TreeNode> list){
		List<TreeNode> nodeList = new ArrayList<TreeNode>();
		for(TreeNode dto1 : list){
			boolean mark = false;
			for(TreeNode dto2 : list){
				if( dto1.getPid().equals(dto2.getId())){
					mark = true;
					if(dto2.getChildren() == null)
						dto2.setChildren(new ArrayList<TreeNode>());
					dto2.getChildren().add(dto1);
					break;
				}
			}
			if(!mark){
				nodeList.add(dto1);
			}
		}
		//转为json格式
		return JsonMapper.getInstance().toJson(nodeList);
	}
	 	 
}
