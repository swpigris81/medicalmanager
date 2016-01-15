/**
 * 
 */
package com.medical.manager.product.web.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.medical.manager.app.common.web.model.TblFundBalance;
import com.medical.manager.app.common.web.service.ITblFundBalanceService;
import com.medical.manager.common.CommonEnums;
import com.medical.manager.common.dao.IBaseDao;
import com.medical.manager.common.util.DateUtil;
import com.medical.manager.customer.web.model.CusBasicInfo;
import com.medical.manager.customer.web.service.ICusBasicInfoService;
import com.medical.manager.product.web.dao.ITblDeliverProductDao;
import com.medical.manager.product.web.dao.ITblExpressDao;
import com.medical.manager.product.web.dao.ITblOrderDao;
import com.medical.manager.product.web.model.TblDeliverProduct;
import com.medical.manager.product.web.model.TblExpress;
import com.medical.manager.product.web.model.TblOrder;
import com.medical.manager.product.web.query.TblExpressExample;
import com.medical.manager.product.web.query.TblOrderExample;
import com.medical.manager.product.web.query.TblOrderExample.Criteria;
import com.medical.manager.product.web.service.IOrderService;

/**
 * @author jason
 *
 * 2015-12-2 上午9:43:26
 * 
 * @version 1.0.0
 */
@Transactional
@Service("orderService")
public class OrderServiceImpl implements IOrderService {
    
	@Resource
	private IBaseDao baseDao;
    @Resource
    private ITblOrderDao tblOrderDao;
    @Resource
    private ITblExpressDao tblExpressDao;
    @Resource
    private ITblDeliverProductDao tblDeliverProductDao;

    @Resource
    private ICusBasicInfoService cusBasicInfoService;
    @Resource
    private ITblFundBalanceService tblFundBalanceService;
    
    @Override
    public List<TblOrder> queryOrderList(Map<String, Object> paramMap) {
        Integer pageNum=(Integer) paramMap.get("page");
        Integer pageSize=(Integer) paramMap.get("rows");
        return this.baseDao.queryWithPage(paramMap, pageNum, pageSize, "queryOrderList");
    }

    @Override
    public void modifyOrder(TblOrder tblOrder) {
        this.tblOrderDao.updateByPrimaryKeySelective(tblOrder);
    }

    @Override
    public void deliverProduct(TblDeliverProduct deliverProduct) {
        TblOrder tblOrder=this.tblOrderDao.selectByPrimaryKey(deliverProduct.getOrderNumber());
        tblOrder.setIsDelivery("Y");
        this.tblOrderDao.updateByPrimaryKeySelective(tblOrder);
        this.tblDeliverProductDao.insert(deliverProduct);
    }

    @Override
    public List<TblDeliverProduct> queryDeliverProductList(Integer page, Integer rows, Map<String, String> paramMap) {
        return this.baseDao.queryWithPage(paramMap, page, rows, "queryDeliverProductList");
    }

    @Override
    public List<TblExpress> queryExpress(TblExpress tblExpress) {
        TblExpressExample example=new TblExpressExample();
        if (tblExpress!=null) {
            com.medical.manager.product.web.query.TblExpressExample.Criteria criteria=example.createCriteria();
            criteria.andExpressNameEqualTo(tblExpress.getExpressName());
        }
        return this.tblExpressDao.selectByExample(example);
    }

    @Override
    public void saveExpress(TblExpress tblExpress) {
        this.tblExpressDao.insert(tblExpress);
    }

    @Override
    public Integer queryUntreatedOrder() {
        TblOrderExample orderExample=new TblOrderExample();
        Criteria criteria= orderExample.createCriteria();
        criteria.andIsDeliveryEqualTo(CommonEnums.YesOrNo.NO.id);
        List<TblOrder> orders=this.tblOrderDao.selectByExample(orderExample);
        if (orders==null) {
            return 0;
        }
        return orders.size();
    }

	/* (non-Javadoc)
	 * @see com.medical.manager.product.web.service.IOrderService#balancePay(java.lang.String, java.lang.String)
	 */
	@Override
	public void balancePay(String orderId, String cusId) throws Exception {

		// 验证cusid
		CusBasicInfo info = cusBasicInfoService.queryById(cusId);
		if (info == null) {
			throw new Exception("客户不存在，客户id错误！");
		}

		// 验证orderid
		TblOrder order = tblOrderDao.findById(orderId);
		if (order == null) {
			throw new Exception("订单不存在，订单号错误！");
		}
		
		// 已付款
		if (CommonEnums.YesOrNo.YES.id.equals(order.getIsPay())) {
			throw new Exception("订单已付款，无需再次付款！");
		}
		
		// 验证余额
		TblFundBalance balance = tblFundBalanceService.findByCusId(Long.valueOf(cusId));
		if (balance == null) {
			throw new Exception("账户未开通！");
		}
		
		// 订单金额大于余额
		if (order.getOrderAmount().compareTo(balance.getBalance()) > 0) {
			throw new Exception("余额不足！");
		}
		
		order.setPayTime(DateUtil.fmtDateToYMDHMS(new Date()));
		order.setIsPay(CommonEnums.YesOrNo.YES.id);
		// 修改订单状态为已支付
		tblOrderDao.updateForPay(order);
		
		// 扣减余额
		tblFundBalanceService.changeBalance(Long.valueOf(cusId),
				order.getOrderAmount(), CommonEnums.YesOrNop.YES.id, "购物支出",
				CommonEnums.YesOrNo.NO.id);
	}

}
