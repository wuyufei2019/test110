package com.cczu.model.service;

import com.cczu.model.dao.BisSpsbxxDao;
import com.cczu.model.entity.Bis_VideoEquipmentEntity;
import com.cczu.model.entity.TS_Video;
import com.cczu.sys.comm.mapper.JsonMapper;
import com.cczu.sys.comm.utils.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * @Description: 视频设备信息Service
 * @author: YZH
 * @date: 2017年12月27日
 */
@Transactional(readOnly=true)
@Service("BisSpsbxxService")
public class BisSpsbxxService {
	@Resource
	private BisSpsbxxDao bisSpsbxxDao;
	
	/**
	 * 分页显示
	 * @param mapData
	 * @return
	 */
	public Map<String, Object> dataGrid(Map<String, Object> mapData) {

		List<Map<String, Object>> list=bisSpsbxxDao.dataGrid(mapData);
		int getTotalCount=bisSpsbxxDao.getTotalCount(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}

	public int getTotalCount(Map<String, Object> mapData){
		return bisSpsbxxDao.getTotalCount(mapData);
	}

	/**
	 * 添加
	 * @param bis
	 */
	public void addInfo(Bis_VideoEquipmentEntity bis) {
		bis.setUrl(bis.getPlayaddress()+"/"+bis.getM3()+"/"+bis.getM3()+".m3u8");
		getAddVideoApi(bis);
		bisSpsbxxDao.save(bis);
	}

	/**
	 * 根据id查询详细信息
	 * @param id
	 * @return
	 */
	public Bis_VideoEquipmentEntity findById(Long id) {
		return bisSpsbxxDao.find(id);
	}

	public Map<String, Object> findById2(Long id) {
		return  bisSpsbxxDao.findById(id);
	}

	/**
	 * 更新信息
	 * @param bis
	 */
	public void updateInfo(Bis_VideoEquipmentEntity bis) {
		if (StringUtils.isEmpty(bis.getUrl())) {
			bis.setUrl(bis.getPlayaddress() + "/" + bis.getM3() + "/" + bis.getM3() + ".m3u8");
			getAddVideoApi(bis);
		}
		bisSpsbxxDao.save(bis);
	}

	/**
	 * 删除信息
	 * @param id
	 */
	@Transactional(readOnly=false)
	public void deleteInfo(long id) {
		bisSpsbxxDao.deleteInfo(id);
	}

	/**
	 * 判断名称是否存在
	 * @param name
	 * @param id
	 * @return
	 */
	public boolean isexist(String name,long id){
		List<Bis_VideoEquipmentEntity> list=bisSpsbxxDao.findBy("M3", name);
		bisSpsbxxDao.clear();
		for(Bis_VideoEquipmentEntity ts:list){
			if(ts.getID()!=id)
				return true;
		}
		return false;
	}

	/**
	 * 请求视频流添加接口
	 * @param ts
	 */
	public void getAddVideoApi(Bis_VideoEquipmentEntity ts){
		//主码流rtsp://admin:xq123456@192.168.200.24:554/h264/ch1/main/av_stream
		//子码流rtsp://admin:xq123456@192.168.200.24:554/h264/ch1/sub/av_stream
//		 String rtsp="rtsp://"+ts.getUsername()+":"+ts.getPassword()+"@"+ts.getIp()+":"+ts.getPort()+"/h264/ch1/main/av_stream";
//		 String rtsp="rtsp://"+ts.getUsername()+":"+ts.getPassword()+"@"+ts.getIp()+"/h264/ch1/sub/av_stream";
//		 String path = Global.getVideoApi()+"/api/easyhlsmodule?name="+ts.getName()+"&url="+rtsp;
		String rtsp = ts.getRtsp();
		String path = ts.getApiaddress()+"/api/easyhlsmodule?name="+ts.getM3()+"&url="+rtsp;
		System.out.println(path);
		try {
			URL url = new URL(path);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("POST");
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

	public String getZdwxySpUrlsJson(Map<String, Object> map) {
		return JsonMapper.toJsonString(bisSpsbxxDao.findListByMap(map));
	}

	/**
	 * 重新生成视频流文件
	 * @param
	 * @param
	 * @return
	 */
	public void reset(){
		List<Bis_VideoEquipmentEntity> list=bisSpsbxxDao.findAll();
		for(Bis_VideoEquipmentEntity ts:list){
			updateInfo(ts);
			getAddVideoApi(ts);
		}
	}

	/**
	 * 获取视频数量
	 * @param mapData
	 * @return
	 */
	public int getSpCount(Map<String, Object> mapData) {
		int getTotalCount = bisSpsbxxDao.getTotalCount(mapData);
		return getTotalCount;
	}

}
