/**
 * 
 */
package com.medical.manager.product.web.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.medical.manager.common.pager.PagerInfo;
import com.medical.manager.common.web.action.BaseHandler;
import com.medical.manager.product.web.model.TblDeliverProduct;
import com.medical.manager.product.web.model.TblExpress;
import com.medical.manager.product.web.model.TblOrder;
import com.medical.manager.product.web.service.IOrderService;

/**
 * @author jason
 * 
 *         2015-12-2 下午2:59:30
 * 
 * @version 1.0.0
 */
@Controller
@RequestMapping("/order")
public class OrderHandler extends BaseHandler {

    private static Logger logger = Logger.getLogger(OrderHandler.class);

    @Resource
    private IOrderService orderService;

    /**
     * 跳转到订单管理主页
     * 
     * @param session
     * @return
     */
    @RequestMapping("/orderIndex.do")
    public String orderIndex(HttpSession session,Model model) {
        String timeout = logginTimeOut(session);
        if (timeout != null) {
            return timeout;
        }
        List<TblExpress> expresses=this.orderService.queryExpress(null);
        model.addAttribute("expresses", expresses);
        return "product/order/orderList";
    }

    /**
     * 查询订单信息 (这里描述这个方法适用条件 – 可选)
     * 
     * @param response
     * @param paramMap
     * @exception
     * @since 1.0.0
     */
    @RequestMapping("/queryOrderList.do")
    public void queryOrderList(HttpServletResponse response, Integer page, Integer rows, String isPay,
            String beginDate, String endDate, String isDelivery) {
        logger.info("查询订单信息");
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("page", page);
        paramMap.put("rows", rows);
        paramMap.put("isPay", isPay);
        if (StringUtils.isNoneBlank(beginDate)) {
            beginDate += " 00:00:00";
        }
        if (StringUtils.isNoneBlank(endDate)) {
            endDate += " 23:59:59";
        }
        paramMap.put("beginDate", beginDate);
        paramMap.put("endDate", endDate);
        paramMap.put("isDelivery", isDelivery);
        try {
            List<TblOrder> tblOrders = this.orderService.queryOrderList(paramMap);
            writeMsg(response, JSON.toJSONString(PagerInfo.converFromPage((Page<TblOrder>) tblOrders)));
        } catch (Exception e) {
            logger.error("查询订单信息失败", e);
            writeFailMsg(response, "查询订单信息失败");
        }

    }

    /**
     * 修改订单状态 (这里描述这个方法适用条件 – 可选)
     * 
     * @param response
     * @param tblOrder
     * @exception
     * @since 1.0.0
     */
    @RequestMapping("/modifyOrder.do")
    public void modifyOrder(HttpServletResponse response, TblOrder tblOrder) {
        logger.info("修改订单状态");
        try {
            this.orderService.modifyOrder(tblOrder);
            writeSuccessMsg(response, "修改成功！");
        } catch (Exception e) {
            logger.error("修改失败！", e);
            writeFailMsg(response, "修改失败");
        }
    }

    /**
     * 发货 (这里描述这个方法适用条件 – 可选)
     * 
     * @param response
     * @param deliverProduct
     * @exception
     * @since 1.0.0
     */
    @RequestMapping("/deliverProduct.do")
    public void deliverProduct(HttpServletResponse response, TblDeliverProduct deliverProduct) {
        logger.info("订单发货");
        try {
            this.orderService.deliverProduct(deliverProduct);
            writeSuccessMsg(response, "发货成功！");
        } catch (Exception e) {
            logger.error("修改失败！", e);
            writeFailMsg(response, "发货失败");
        }
    }

    /**
     * 跳转到发货信息主页 (这里描述这个方法适用条件 – 可选)
     * 
     * @param session
     * @return
     * @exception
     * @since 1.0.0
     */
    @RequestMapping("/deleverProductIndex.do")
    public String deleverProductIndex(HttpSession session) {
        String timeout = logginTimeOut(session);
        if (timeout != null) {
            return timeout;
        }
        return "product/order/deliverProductList";
    }

    /**
     * 查询发货信息 (这里描述这个方法适用条件 – 可选)
     * 
     * @param response
     * @param page
     * @param rows
     * @param beginDate
     * @param endDate
     * @param phoneNumber
     * @param expressNo
     * @exception
     * @since 1.0.0
     */
    @RequestMapping("/queryDeliverProductList.do")
    public void queryDeliverProductList(HttpServletResponse response, Integer page, Integer rows, String beginDate,
            String endDate, String phoneNumber, String expressNo) {
        logger.info("查询发货信息");
        Map<String, String> paramMap=new HashMap<String, String>();
        if (StringUtils.isNoneBlank(beginDate)) {
            paramMap.put("beginDate", beginDate+" 00:00:00");
        }
        if (StringUtils.isNoneBlank(endDate)) {
            paramMap.put("endDate", endDate+" 23:59:59");
        }
        paramMap.put("phoneNumber", phoneNumber);
        paramMap.put("expressNo", expressNo);
        try {
            List<TblDeliverProduct> deliverProducts= this.orderService.queryDeliverProductList(page,rows,paramMap);
            writeMsg(response, JSON.toJSONString(PagerInfo.converFromPage((Page<TblDeliverProduct>) deliverProducts)));
        } catch (Exception e) {
            logger.error("查询发货信息失败！", e);
            writeFailMsg(response, "查询发货信息失败！");
        }
        
    }
    
    /**
     * 新增快递
     * (这里描述这个方法适用条件 – 可选)
     * @param response
     * @param tblExpress
     * @exception
     * @since 1.0.0
     */
    @RequestMapping("/saveExpress.do")
    public void saveExpress(HttpServletResponse response,TblExpress tblExpress){
        logger.info("保存快递");
        List<TblExpress>tblExpresses=this.orderService.queryExpress(tblExpress);
        if (tblExpresses!=null && tblExpresses.size()>0) {
            writeFailMsg(response, "快递公司已存在");
            return;
        }
        try {
            this.orderService.saveExpress(tblExpress);
            writeSuccessMsg(response, "新增快递成功");
        } catch (Exception e) {
            logger.error("新增快递失败", e);
            writeFailMsg(response, "新增快递失败");
        }
    }

}
