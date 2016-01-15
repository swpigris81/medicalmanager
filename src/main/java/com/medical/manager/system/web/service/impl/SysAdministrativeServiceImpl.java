package com.medical.manager.system.web.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.medical.manager.system.web.dao.ISysAdministrativeDao;
import com.medical.manager.system.web.model.TblSysArea;
import com.medical.manager.system.web.model.TblSysCity;
import com.medical.manager.system.web.model.TblSysProvince;
import com.medical.manager.system.web.service.ISysAdministrativeService;

/**
 * 
 * SysAdministrativeServiceImpl 行政区划
 * 
 * 2015年12月3日 下午3:04:26
 * 
 * @version 1.0.0
 *
 */
@Service("sysAdministrativeService")
public class SysAdministrativeServiceImpl implements ISysAdministrativeService {
    @Resource
    private ISysAdministrativeDao sysAdministrativeDao;

    @Override
    public List<TblSysProvince> findAllProvince() {
        return sysAdministrativeDao.findAllProvince();
    }

    @Override
    public List<TblSysCity> findCityByProvinceid(Integer provinceid) {
        return sysAdministrativeDao.findCityByProvinceid(provinceid);
    }

    @Override
    public List<TblSysArea> findCountyByCityid(Integer cityid) {
        return sysAdministrativeDao.findAreaByCityId(cityid);
    }
}
