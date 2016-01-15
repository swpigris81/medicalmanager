/**
 * 系统项目名称
 * com.medical.manager.customer.web.service
 * ITblCusLoginSeqService.java
 * 
 * 2015年12月7日-下午8:19:25
 * 2015版权所有
 * 
 */

package com.medical.manager.stat.web.service;

import java.util.List;

import com.medical.manager.stat.web.dto.CusLoginStatDto;

/**
 * @description 用户登陆流水
 * 
 * @time 2015年12月7日 下午8:19:25
 * @author Jason
 * @version 1.0.0
 * 
 */

public interface ICusLoginStatService {

	/**
	 * 查询用户登录流水
	 *
	 * @author wusj
	 * @date 2015年12月7日 下午3:32:42
	 * @param loginStatDto
	 * @param page
	 * @param rows
	 * @return
	 * List<CusLoginStatDto>
	 */
	List<CusLoginStatDto> queryStatLoginList(CusLoginStatDto loginStatDto, Integer page,
			Integer rows);

}
