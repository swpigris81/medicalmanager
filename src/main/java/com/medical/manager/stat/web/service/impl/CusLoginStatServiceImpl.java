/**
 * 系统项目名称
 * com.medical.manager.stat.web.model
 * TblCusLoginSeqServiceImpl.java
 * 
 * 2015年12月7日-下午8:19:25
 * 2015版权所有
 * 
 */

package com.medical.manager.stat.web.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.medical.manager.common.dao.IBaseDao;
import com.medical.manager.common.util.StringUtil;
import com.medical.manager.stat.web.dao.ICusLoginStatDao;
import com.medical.manager.stat.web.dto.CusLoginStatDto;
import com.medical.manager.stat.web.service.ICusLoginStatService;

/**
 * @description 用户登陆流水
 * 
 * @time 2015年12月7日 下午8:19:25
 * @author Jason
 * @version 1.0.0
 * general_stat_item
 */

@Transactional
@Service("tblCusLoginSeqService")
public class CusLoginStatServiceImpl implements ICusLoginStatService{

	@Resource
	private IBaseDao<CusLoginStatDto> baseDao;
	@Resource
	private ICusLoginStatDao cusLoginStatDao;
	
	/* (non-Javadoc)
	 * @see com.medical.manager.customer.web.service.ITblCusLoginSeqService#queryStatLoginList(com.medical.manager.customer.web.model.TblCusLoginSeq, java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public List<CusLoginStatDto> queryStatLoginList(
			CusLoginStatDto loginStatDto, Integer page, Integer rows) {
		Map<String, String> map = new HashMap<String, String>(2);
		if (!StringUtil.isEmpty(loginStatDto.getRealName()))
			map.put("realName", loginStatDto.getRealName());
		
		if (!StringUtil.isEmpty(loginStatDto.getNickname()))
			map.put("nickname", loginStatDto.getNickname());
		
		if (!StringUtil.isEmpty(loginStatDto.getLastLoginTimeStart()))
			map.put("lastLoginTimeStart", loginStatDto.getLastLoginTimeStart());
		
		if (!StringUtil.isEmpty(loginStatDto.getLastLoginTimeEnd()))
			map.put("lastLoginTimeEnd", loginStatDto.getLastLoginTimeEnd());
		return baseDao.queryWithPage(map, page, rows, "queryStatLoginList");
	}
}
