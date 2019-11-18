package com.cczu.model.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import com.cczu.model.entity.AQPX_ItembankEntity;
import com.cczu.model.entity.BIS_StorageEntity;
import com.cczu.sys.comm.persistence.Page;
import com.cczu.sys.comm.persistence.PropertyFilter;

public interface IAqpxStkxxService {
	
	public Page<AQPX_ItembankEntity> search(Page<BIS_StorageEntity> page,
			List<PropertyFilter> filters);
	
	/**
	 * 添加试题库试题
	 * @param ai
	 */
	public void addInfo(AQPX_ItembankEntity ai);
	
	/**
	 * 修改
	 * @param ai
	 */
	public void updateInfo(AQPX_ItembankEntity ai);

	/**
	 * 删除
	 * @param id
	 */
	public void deleteInfo(Long id);
	
	/**
	 * 通过企业id查询所有试题库信息
	 * @param qyid
	 * @return
	 */
	public AQPX_ItembankEntity findAll(Long qyid);
	
	/**
	 * 通过课程id查询该课程所有的试题
	 * @param kcid
	 * @return
	 */
	public AQPX_ItembankEntity findkc(Long kcid);
	
	/**
	 * 出题
	 * @param kcid
	 * @return
	 */
	public List<AQPX_ItembankEntity> getst(Long kcid);
	
	/**
	 * 出单选题
	 * @param kcid
	 * @param dxsum
	 * @return
	 */
	public List<AQPX_ItembankEntity> getdx(Long kcid,int dxsum);
	
	/**
	 * 出多选题
	 * @param kcid
	 * @param dsxsum
	 * @return
	 */
	public List<AQPX_ItembankEntity> getdsx(Long kcid,int dsxsum);
	
	/**
	 * 出填空题
	 * @param kcid
	 * @param tksum
	 * @return
	 */
	public List<AQPX_ItembankEntity> gettk(Long kcid, int tksum);
	
	/**
	 * 出判断题
	 * @param kcid
	 * @param pdsum
	 * @return
	 */
	public List<AQPX_ItembankEntity> getpd(Long kcid, int pdsum);
	
	/**
	 * 联合出题
	 * @param kcid
	 * @param dxsum
	 * @param dsxsum
	 * @param tksum
	 * @param pdsum
	 * @return
	 */
	public List<AQPX_ItembankEntity> getkt(Long kcid, int dxsum, int dsxsum,
			int tksum, int pdsum);
	
	
	/**
	 * 通过id查信息的
	 * @param id
	 * @return
	 */
	public List<AQPX_ItembankEntity> getstxx(Long id);
	
	/**
	 * 查询条件
	 * @param mapData
	 * @return
	 */
	public String content(Map<String, Object> mapData);

	/**
	 * 分页的
	 * @param mapData
	 * @return
	 */
	public Map<String, Object> dataGrid(Map<String, Object> mapData);
	
	/**
	 * 通过id查询信息
	 * @param id
	 * @return
	 */
	public AQPX_ItembankEntity findByid(Long id);
	
	/**
	 * 导出excel
	 * @param mapData
	 * @return
	 */
	public void exportExcel(HttpServletResponse response,
			Map<String, Object> mapData) ;

	/**
	 * 导出的条件2
	 * @param mapData
	 * @return
	 */
	public String content2(Map<String, Object> mapData);
	
	public Map<String, Object> findbym(Long id1, String m1);
	
	
	/**
	 * 根据课程ID和出卷规则随机生成试卷
	 * @param kcid 课程id
	 * @param dxsum 单选数量
	 * @param dsxsum 多选数量
	 * @param tksum 填空数量
	 * @param pdsum 判断数量
	 * @return
	 */
	public Map<String, List<AQPX_ItembankEntity>> getSjByGz(Long kcid, int dxsum,int dsxsum,int tksum, int pdsum);
	
	/**
	 * 根据课程id和企业id判断试题库题量是否满足出试卷
	 * @param kcid
	 * @param qyid
	 * @return
	 */
	public boolean STisOrNot(Long kcid,Long qyid);
	
	/**
	 * 导入
	 */
	public Map<String,Object> exinExcel(Map<String, Object> map);
	
	/**
	 * 判断是否可以生产试卷
	 * @param mapData
	 * @return
	 */
	public String STgzIsOrNot(Map<String, String> mapData);
}
