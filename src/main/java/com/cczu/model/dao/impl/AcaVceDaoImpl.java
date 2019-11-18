package com.cczu.model.dao.impl;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.namespace.QName;

import org.springframework.stereotype.Repository;

import com.cczu.client.sgjs.webservice.SGWebservice;
import com.cczu.client.sgjs.webservice.SGWebserviceSoap;
import com.cczu.model.entity.ACA_VceEntity;
import com.cczu.model.dao.IAcaVceDao;
import com.cczu.util.dao.BaseDao;

@Repository("AcaVceDao")
public class AcaVceDaoImpl extends BaseDao<ACA_VceEntity, Long> implements IAcaVceDao {

	@Override
	public Map<String, Object> dataGrid(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ACA_VceEntity findInfoById(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ACA_VceEntity> findListInfoByUserId(long id1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Object> saveInfo(ACA_VceEntity aca) throws Exception{
		URL wsdlURL = SGWebservice.WSDL_LOCATION;
        QName SERVICE_NAME = new QName("http://tempuri.org/", "SG_webservice");
        SGWebservice ss = new SGWebservice(wsdlURL, SERVICE_NAME);
        SGWebserviceSoap port = ss.getSGWebserviceSoap(); 	
    	float M=Float.parseFloat(aca.getM2());
		float Hc=Float.parseFloat(aca.getM3());
		float Q0=(float) 90;
		float Q1=(float) 44;
		float Q2=(float) 17;
        float objects1 = port.rVCE(M,Hc,Q0);
        float objects2 = port.rVCE(M,Hc,Q1);
        float objects3 = port.rVCE(M,Hc,Q2);
        //调用web Service//输出调用结果  
//        System.out.println(objects1);  
//        System.out.println(objects2);  
//        System.out.println(objects3);  
//        aca.setM4(String.valueOf(Q0));//死亡
//        aca.setM4_1(String.valueOf(Q1));//重伤
//        aca.setM4_2(String.valueOf(Q2));//轻伤
//        aca.setM5(String.valueOf(objects1) );
//        aca.setM5_1(String.valueOf(objects2) );
//        aca.setM5_2(String.valueOf(objects3) );
//        save(aca);
        
        Map<String, Object> map=new HashMap<String, Object>();
        map.put("sw", objects1);
        map.put("zs", objects2);
        map.put("qs", objects3);
        
		return map;
	}

	@Override
	public Map<String, Object> jcsaveInfo(ACA_VceEntity aca) throws Exception{
		URL wsdlURL = SGWebservice.WSDL_LOCATION;
        QName SERVICE_NAME = new QName("http://tempuri.org/", "SG_webservice");
        SGWebservice ss = new SGWebservice(wsdlURL, SERVICE_NAME);
        SGWebserviceSoap port = ss.getSGWebserviceSoap(); 	
    	float M=Float.parseFloat(aca.getM2());
		float Hc=Float.parseFloat(aca.getM3());
		float Q0=(float) 90;
		float Q1=(float) 44;
		float Q2=(float) 17;
        float objects1 = port.rVCE(M,Hc,Q0);
        float objects2 = port.rVCE(M,Hc,Q1);
        float objects3 = port.rVCE(M,Hc,Q2);
        Map<String, Object> map=new HashMap<String, Object>();
        map.put("sw", objects1);
        map.put("zs", objects2);
        map.put("qs", objects3);
        //调用web Service//输出调用结果  
//        System.out.println(objects1);  
//        System.out.println(objects2);  
//        System.out.println(objects3);  
        aca.setM4(String.valueOf(Q0));//死亡
        aca.setM4_1(String.valueOf(Q1));//重伤
        aca.setM4_2(String.valueOf(Q2));//轻伤
        aca.setM5(String.valueOf(objects1) );
        aca.setM5_1(String.valueOf(objects2) );
        aca.setM5_2(String.valueOf(objects3) );
        save(aca);
        System.out.println("化学爆炸（蒸气云爆炸）_id："+aca.getID());
        map.put("id", aca.getID());
        
		return map;
	}

	@Override
	public List<Map<String, Object>> appVce(String str1, String str2)
			throws Exception {
		// TODO Auto-generated method stub
		URL wsdlURL = SGWebservice.WSDL_LOCATION;
        QName SERVICE_NAME = new QName("http://tempuri.org/", "SG_webservice");
        SGWebservice ss = new SGWebservice(wsdlURL, SERVICE_NAME);
        SGWebserviceSoap port = ss.getSGWebserviceSoap(); 	
    	float M=Float.parseFloat(str1);
		float Hc=Float.parseFloat(str2);
		float Q0=(float) 90;
		float Q1=(float) 44;
		float Q2=(float) 17;
        float objects1 = port.rVCE(M,Hc,Q0);
        float objects2 = port.rVCE(M,Hc,Q1);
        float objects3 = port.rVCE(M,Hc,Q2);
		
        List<Map<String,Object>> newlist=new ArrayList<Map<String,Object>>();
	    Map<String,Object> map=new HashMap<String,Object>();
	    map.put("sw", objects1);
		map.put("zs", objects2);
		map.put("qs", objects3);
		newlist.add(map);

		return newlist;
	}
	
//	public static void main(String[] args) throws Exception {  
//        JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();  
//        org.apache.cxf.endpoint.Client client = dcf.createClient("http://218.93.5.74:8001/fw/SG_webservice.asmx?wsdl");  	
//    	float M=(float) 2.5;
//		float Hc=(float) 90;
//		float Q0=(float) 90;
//		float Q1=(float) 44;
//		float Q2=(float) 17;
//        Object[] objects1 = client.invoke("R_VCE",M,Hc,Q0);
//        Object[] objects2 = client.invoke("R_VCE",M,Hc,Q1);
//        Object[] objects3 = client.invoke("R_VCE",M,Hc,Q2);
//        //调用web Service//输出调用结果  
//        System.out.println(objects1[0].toString());  
//        System.out.println(objects2[0].toString());  
//        System.out.println(objects3[0].toString());  
//	} 
	
}
