/**
 * 
 */
package com.medical.manager.systemset.web.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Service;

import com.medical.manager.common.CommonEnums;
import com.medical.manager.systemset.web.dao.ITblSysInfoDao;
import com.medical.manager.systemset.web.model.TblSysInfo;
import com.medical.manager.systemset.web.query.TblSysInfoExample;
import com.medical.manager.systemset.web.query.TblSysInfoExample.Criteria;
import com.medical.manager.systemset.web.query.TblSysInfoParam;
import com.medical.manager.systemset.web.service.ITblSysInfoService;

/**
 * @author jason
 *
 * 2015-12-4 上午11:16:06
 * 
 * @version 1.0.0
 */
@Service("tblSysInfoService")
public class ITblSysInfoServiceImpl implements ITblSysInfoService {

    @Resource
    private ITblSysInfoDao tblSysInfoDao;
    
    @Override
    public List<TblSysInfo> getSysInfo() {
        return this.tblSysInfoDao.selectByExample(null);
    }

    @SuppressWarnings("unchecked")
    @Override
    public void saveOrUpdateSysInfo(TblSysInfoParam sysInfoParam) throws Exception {
		Map<String, String> dataMap=BeanUtils.describe(sysInfoParam);
        List<TblSysInfo> sysInfos=new ArrayList<TblSysInfo>();
        TblSysInfo sysInfo=null;
        for (Entry<String, String> entry : dataMap.entrySet()) {
        	if("class".equals(entry.getKey())){
        		continue;
        	}
            sysInfo=new TblSysInfo();
            sysInfo.setParamName(CommonEnums.SystemParamType.keyOf(entry.getKey()));
            sysInfo.setParamTyle(entry.getKey());
            sysInfo.setParamValue(entry.getValue());
            if (CommonEnums.SystemParamType.AMBULANCE_MONTH_USE_COST.id.equals(entry.getKey())) {
            	sysInfo.setRecharge(dataMap.get("a1301"));
            	sysInfo.setFree(dataMap.get("a1302"));
			}
            if (CommonEnums.SystemParamType.A120_PROVINCE_MONTH_USE_COST.id.equals(entry.getKey())) {
            	sysInfo.setRecharge(dataMap.get("a1401"));
            	sysInfo.setFree(dataMap.get("a1402"));
			}
            if (CommonEnums.SystemParamType.A120_CITY_MONTH_USE_COST.id.equals(entry.getKey())) {
            	sysInfo.setRecharge(dataMap.get("a1501"));
            	sysInfo.setFree(dataMap.get("a1502"));
			}
            if (CommonEnums.SystemParamType.A120_COUNTY_MONTH_USE_COST.id.equals(entry.getKey())) {
            	sysInfo.setRecharge(dataMap.get("a1601"));
            	sysInfo.setFree(dataMap.get("a1602"));
			}
            if (CommonEnums.SystemParamType.HOSPITAL_MONTH_USE_COST.id.equals(entry.getKey())) {
                sysInfo.setRecharge(dataMap.get("a1701"));
                sysInfo.setFree(dataMap.get("a1702"));
            }
            sysInfos.add(sysInfo);
        }
        List<TblSysInfo> sysInfoDatas=this.getSysInfo();
//    	if (sysInfoDatas!=null && sysInfoDatas.size()==28) {
//			this.tblSysInfoDao.updateBatch(sysInfos);
//		}else {
			this.tblSysInfoDao.deleteByExample(null);
			this.tblSysInfoDao.insertBatch(sysInfos);
//		}
        
    }

    @Override
    public TblSysInfo querySysInfoByType(String type) {
        TblSysInfoExample example=new TblSysInfoExample();
        Criteria criteria=example.createCriteria();
        criteria.andParamTyleEqualTo(type);
        List<TblSysInfo> tblSysInfos=this.tblSysInfoDao.selectByExample(example);
        if (tblSysInfos!=null && tblSysInfos.size()>0) {
            return tblSysInfos.get(0);
        }
        return null;
    }

}
