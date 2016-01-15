
/**
 * 系统项目名称
 * com.medical.manager.propaganda.web.service.impl
 * AppSlideServiceImpl.java
 * 
 * 2015年12月4日-下午2:20:55
 * 2015
 * 
 */
 
package com.medical.manager.propaganda.web.service.impl;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.medical.manager.common.dao.IBaseDao;
import com.medical.manager.propaganda.web.dao.IPropaAppSlideDao;
import com.medical.manager.propaganda.web.model.TblPropaAppSlide;
import com.medical.manager.propaganda.web.service.IAppSlideService;


/**
 * 
 * AppSlideServiceImpl
 * 
 * 2015年12月4日 下午2:20:55
 * 
 * @version 1.0.0
 * 
 */
@Service("appSlideService")
@Transactional
public class AppSlideServiceImpl implements IAppSlideService {
    @Resource
    private IBaseDao<TblPropaAppSlide> baseDao;
    @Resource
    private IPropaAppSlideDao propaAppSlideDao;
    
    @Override
    public List<TblPropaAppSlide> queryWithPaging(Integer pageNum, Integer pageSize, Map paramMap) {
        return baseDao.queryWithPage(paramMap, pageNum, pageSize, "findAppSlide");
    }

    @Override
    public void doSaveNewAppSlide(TblPropaAppSlide appSlide) {
        propaAppSlideDao.addNewAppSlide(appSlide);
    }

    @Override
    public void doUpdateAppSlide(TblPropaAppSlide appSlide) {
        deleteSlideImg(Arrays.asList(appSlide.getId()));
        propaAppSlideDao.updateAppSlide(appSlide);
    }

    @Override
    public void doDeleteAppSlide(List slideIdList) {
        deleteSlideImg(slideIdList);
        propaAppSlideDao.deleteAppSlide(slideIdList);
    }
    /**
     * <p>Discription:[删除图片]</p>
     * @param slideIdList
     * @author: Jason
     * @update: 2015年12月5日 Jason[变更描述]
     */
    private void deleteSlideImg(List slideIdList){
        List<TblPropaAppSlide> slideList = propaAppSlideDao.findAppSlideByIds(slideIdList);
        if(slideList != null){
            for(TblPropaAppSlide slide : slideList){
                File file = new File(slide.getImageLocation());
                if(file.exists()){
                    file.delete();
                }
            }
        }
    }
}
