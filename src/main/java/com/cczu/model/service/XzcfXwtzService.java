package com.cczu.model.service;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cczu.model.dao.AqzfSetNumberDao;
import com.cczu.model.dao.IBisQyjbxxDao;
import com.cczu.model.dao.IXzcfCommonLaspDao;
import com.cczu.model.dao.XzcfXwtzDao;
import com.cczu.model.entity.AQZF_SetNumberEntity;
import com.cczu.model.entity.BIS_EnterpriseEntity;
import com.cczu.model.entity.XZCF_EnquiryNoteEntity;
import com.cczu.model.entity.XZCF_YbcfLaspEntity;
import com.cczu.sys.comm.utils.DateUtils;
import com.cczu.sys.system.service.ShiroRealm.ShiroUser;
import com.cczu.sys.system.utils.UserUtil;

@Service("XzcfXwtzService")
public class XzcfXwtzService {

	@Resource
	private XzcfXwtzDao xzcfXwtzDao;
	@Resource
	private AqzfSetNumberDao aqzfSetNumberDao;
	@Resource
	private IXzcfCommonLaspDao xzcfcommonlaspdao;
	@Resource
	private IBisQyjbxxDao bisqyjbxxdao;
	
	/**
	 * 查询询问通知list
	 * @param mapData
	 * @return
	 */
	public Map<String, Object> dataGrid(Map<String, Object> mapData) {
		List<Map<String, Object>> list=xzcfXwtzDao.dataGrid(mapData);
		int getTotalCount=xzcfXwtzDao.getTotalCount(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}

	/**
	 * 根据id删除
	 * @param id
	 */
	public void deleteInfo(long id) {
		xzcfXwtzDao.deleteInfo(id);
	}
	/**
	 * 删除后 立案审批的xwflag置0
	 * @param id
	 */
	public void updateLaspInfo(long id) {
		xzcfXwtzDao.updateLaspInfo(id);
	}
	
	/**
	 * 添加询问通知信息
	 * @param zfry
	 */
	public void addInfo(XZCF_EnquiryNoteEntity zfry) {
		zfry.setS1(new Timestamp(System.currentTimeMillis()));
		zfry.setS2(new Timestamp(System.currentTimeMillis()));
		zfry.setS3(0);
		xzcfXwtzDao.addInfo(zfry);
	}

	/**
	 * 根据id查找询问通知信息
	 * @param id
	 * @return
	 */
	public XZCF_EnquiryNoteEntity findById(Long id) {
		return xzcfXwtzDao.findInfoById(id);
	}
	
	/**
	 * 修改
	 * @param zfry
	 */
	public void updateInfo(XZCF_EnquiryNoteEntity zfry) {
		zfry.setS2(new Timestamp(System.currentTimeMillis()));
		zfry.setS3(0);
	    xzcfXwtzDao.updateInfo(zfry);
	}

	/**
	 * 获得文书编号
	 * @return
	 */
	public AQZF_SetNumberEntity findWsbh() {
		AQZF_SetNumberEntity a = aqzfSetNumberDao.findInfor();
		return a;
	}

	/**
	 * 查找符合word的数据
	 * @param id
	 * @return
	 */
	public Map<String, Object> findWord(Long id) {
		return xzcfXwtzDao.findWord(id);
	}
	public Map<String, Object> findWordByLaId(Long laid) {
		return xzcfXwtzDao.findWordByLaId(laid);
	}

	/**
	 * 根据立案id查找询问通知的数据
	 * @param id
	 * @return
	 */
	public Map<String, Object> findById3(Long id) {
		return xzcfXwtzDao.findById3(id);
	}
	
	
	/**
	 * 修改临时的通知书信息
	 * @param zfry
	 */
	public String updateTempTzs(XZCF_EnquiryNoteEntity xwtz){
		//修改前id
		Timestamp t = DateUtils.getSysTimestamp();
		long qyidnew=xwtz.getID2();
		long qyidold=  xzcfXwtzDao.findInfoById(xwtz.getID()).getID2();
		String datasuccess="success";
		ShiroUser su = UserUtil.getCurrentShiroUser();
		xwtz.setID1(su.getId());
		xwtz.setS2(t);
		xzcfXwtzDao.updateInfo(xwtz);
		XZCF_YbcfLaspEntity lasp=xzcfcommonlaspdao.findInfoById(xwtz.getID3());
        if("1".equals(lasp.getTempflag())){
        	//判断修改前后的企业id是否一致
        	if(qyidnew!=qyidold){
        		//修改立案审批的企业相关信息
        		BIS_EnterpriseEntity bis= bisqyjbxxdao.findInfoById(qyidnew);
        		lasp.setDsperson(bis.getM1());
        		lasp.setContact(bis.getM6());
        		lasp.setYbcode(bis.getM9());
        		lasp.setDsaddress(bis.getM8());
        		lasp.setLegalperson(bis.getM5());
        		lasp.setId1(qyidnew);
        	}
        		lasp.setS2(t);
        		lasp.setAyname(xwtz.getM1());
        	
        	xzcfcommonlaspdao.updateInfo(lasp);
		}
		return datasuccess;
	}
	
}
