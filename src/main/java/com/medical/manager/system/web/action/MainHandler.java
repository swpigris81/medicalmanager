/**
 * 系统项目名称
 * com.supermarket.system.web.action
 * MainHandler.java
 * 
 * 2015年7月3日-下午12:02:34
 * 2015版权所有
 * 
 */

package com.medical.manager.system.web.action;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.medical.manager.authentication.web.service.ITblCusPhoneChangeApplyService;
import com.medical.manager.common.CommonEnums;
import com.medical.manager.common.util.DateUtil;
import com.medical.manager.common.util.VerifyCodeImageUtil;
import com.medical.manager.common.web.action.BaseHandler;
import com.medical.manager.customer.web.service.ICusBasicInfoService;
import com.medical.manager.funds.web.service.ITblRechargeTurnoverService;
import com.medical.manager.funds.web.service.ITblWithdrawTurnoverService;
import com.medical.manager.product.web.service.IOrderService;
import com.medical.manager.system.web.model.TblUser;
import com.medical.manager.system.web.service.IMenuService;
import com.medical.manager.systemset.web.model.TblSysInfo;
import com.medical.manager.systemset.web.service.ITblSysInfoService;

/**
 * @description
 * 
 * @time 2015年7月3日 下午12:02:34
 * @author Jason
 * @version 1.0.0
 * 
 */
@Controller
@RequestMapping("/main")
public class MainHandler extends BaseHandler {
    private Logger logger = Logger.getLogger(MainHandler.class);
    private static final String MAIN_PAGE = "main/index";
    @Resource
    private IMenuService menuService;
    @Resource
    private ITblRechargeTurnoverService rechargeTurnoverService;
    @Resource
    private ITblWithdrawTurnoverService withdrawTurnoverService;
    @Resource
    private IOrderService orderService;
    @Resource
    private ICusBasicInfoService cusBasicInfoService;
    @Resource
    private ITblCusPhoneChangeApplyService cusPhoneChangeApplyService;
    @Resource
    private ITblSysInfoService tblSysInfoService;

    /**
     * @description index 用户登录成功之后跳转到的主页
     * @param model
     *            返回页面对象数据
     * @return String
     * @exception
     * @author Jason
     * @since 1.0.0
     */
    @RequestMapping("index.do")
    public String index(HttpServletRequest request, Model model) {
        TblUser user = (TblUser) request.getSession().getAttribute(SESSION_USER);
        if (user == null) {
            logger.info("用户登录超时，重新登录！");
            return "redirect:/index.jsp";
        }
        // 查询用户拥有的权限菜单(第一级菜单)
        List menuList = menuService.findFirstLevelMenuByUserCode(user.getUserCode());
        model.addAttribute("menuList", menuList);
        model.addAttribute("user", user);
        // 查询费用到期预通知
        Integer costExpirePreNotice = this.rechargeTurnoverService.queryCostExpireNotice(null,DateUtil.getNowDate());
        // 查询费用到期通知
        TblSysInfo tblSysInfo=this.tblSysInfoService.querySysInfoByType(CommonEnums.SystemParamType.COST_DUE_NOTICE.id);
        Integer costExpireNotice = this.rechargeTurnoverService.queryCostExpireNotice(DateUtil.getNowDate(),DateUtil.fmtDateToStr(
                DateUtil.addDay(new Date(), tblSysInfo==null?-1:Integer.valueOf(tblSysInfo.getParamValue())), "yyyyMMdd"));
        // 查询未处理订单
        Integer untreatedOrder=this.orderService.queryUntreatedOrder();
        // 查询急救车认证信息审核
        Integer ambulanceAuthentication=this.cusBasicInfoService.queryAmbulanceAuthentication(CommonEnums.CustomType.AMBULANCE.id);
        // 查询120中心认证信息审核
        Integer a120Authentication=this.cusBasicInfoService.queryAmbulanceAuthentication(CommonEnums.CustomType.A120.id);
        //手机号变更申请
        Integer phoneChangeCheck=this.cusPhoneChangeApplyService.queryphoneChangeCheck();
        // 查询等待提现
        Integer waitWithdrawals=this.withdrawTurnoverService.queryWaitWithdrawals();

        model.addAttribute("costExpirePreNotice", costExpirePreNotice==null?0:costExpirePreNotice);
        model.addAttribute("costExpireNotice", costExpireNotice==null?0:costExpireNotice);
        model.addAttribute("ambulanceAuthentication", ambulanceAuthentication==null?0:ambulanceAuthentication);
        model.addAttribute("a120Authentication", a120Authentication==null?0:a120Authentication);
        model.addAttribute("phoneChangeCheck", phoneChangeCheck==null?0:phoneChangeCheck);
        model.addAttribute("waitWithdrawals", waitWithdrawals==null?0:waitWithdrawals);
        model.addAttribute("untreatedOrder", untreatedOrder==null?0:untreatedOrder);
        return MAIN_PAGE;
    }
    
    /**
     * showVerifyCode 生成图片验证码
     * (这里描述这个方法适用条件 – 可选)
     * @param request
     * @param response
     * @exception 
     * @since  1.0.0
     */
    @RequestMapping("showVerifyCode.do")
    public void showVerifyCode(HttpServletRequest request, HttpSession session,
            HttpServletResponse response) {
        logger.info("生成图片验证码");
        session.removeAttribute(IMAGE_VERIFY_CODE);
        
        BufferedImage bi = VerifyCodeImageUtil.generateVerifyCodeImage(null);
        //7.将随机数存在session中
        logger.info("当前验证码：" + VerifyCodeImageUtil.getRandom());
        session.setAttribute(IMAGE_VERIFY_CODE, VerifyCodeImageUtil.getRandom());
        //8.设置响应头通知浏览器以图片的形式打开
        response.setContentType("image/jpeg");//等同于response.setHeader("Content-Type", "image/jpeg");
        //9.设置响应头控制浏览器不要缓存
        response.setDateHeader("expries", -1);
        response.setHeader("Cache-Control", "no-cache");
        response.setHeader("Pragma", "no-cache");
        //10.将图片写给浏览器
        try {
            ImageIO.write(bi, "jpg", response.getOutputStream());
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
    }

}
