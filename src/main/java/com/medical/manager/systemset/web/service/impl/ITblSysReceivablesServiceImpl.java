/**
 * 
 */
package com.medical.manager.systemset.web.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.medical.manager.systemset.web.dao.ITblSysReceivablesDao;
import com.medical.manager.systemset.web.model.TblSysReceivables;
import com.medical.manager.systemset.web.service.ITblSysReceivablesService;

/**
 * @author jason
 *
 * 2015-12-4 上午11:26:52
 * 
 * @version 1.0.0
 */
@Service("/tblSysReceivablesService")
public class ITblSysReceivablesServiceImpl implements ITblSysReceivablesService {

    @Resource
    private ITblSysReceivablesDao tblSysReceivablesDao;
    
    @Override
    public TblSysReceivables getSysReceivables() {
        List<TblSysReceivables> sysReceivables=this.tblSysReceivablesDao.selectByExample(null);
        if (sysReceivables!=null && sysReceivables.size()>0) {
            return sysReceivables.get(0);
        }
        return null;
    }

    @Override
    public void saveSysReceivables(TblSysReceivables sysReceivables) {
        sysReceivables.setId(1l);
        this.tblSysReceivablesDao.insertSelective(sysReceivables);
    }

    @Override
    public void updateSysReceivables(TblSysReceivables sysReceivables) {
        this.tblSysReceivablesDao.updateByPrimaryKeySelective(sysReceivables);
        
    }

}
