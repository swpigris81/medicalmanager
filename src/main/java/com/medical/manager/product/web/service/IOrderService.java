/**
 * 
 */
package com.medical.manager.product.web.service;

import java.util.List;
import java.util.Map;

import com.medical.manager.product.web.model.TblDeliverProduct;
import com.medical.manager.product.web.model.TblExpress;
import com.medical.manager.product.web.model.TblOrder;

/**
 * @author Jason
 *
 * 2015-12-2 上午9:36:20
 * 
 * @version 1.0.0
 */
public interface IOrderService {

    /**
     * 查询订单信息
     * (这里描述这个方法适用条件 – 可选)
     * @param paramMap
     * @return
     * @exception
     * @since 1.0.0
     */
    List<TblOrder> queryOrderList(Map<String, Object> paramMap);

    /**
     * 修改订单状态
     * (这里描述这个方法适用条件 – 可选)
     * @param tblOrder
     * @exception
     * @since 1.0.0
     */
    void modifyOrder(TblOrder tblOrder);

    /**
     * 订单发货
     * (这里描述这个方法适用条件 – 可选)
     * @param deliverProduct
     * @exception
     * @since 1.0.0
     */
    void deliverProduct(TblDeliverProduct deliverProduct);

    /**
     * 查询发货信息
     * (这里描述这个方法适用条件 – 可选)
     * @param page
     * @param rows
     * @param paramMap
     * @return
     * @exception
     * @since 1.0.0
     */
    List<TblDeliverProduct> queryDeliverProductList(Integer page, Integer rows, Map<String, String> paramMap);

    /**
     * 查询快递公司
     * (这里描述这个方法适用条件 – 可选)
     * @return
     * @exception
     * @since 1.0.0
     */
    List<TblExpress> queryExpress(TblExpress tblExpress);

    /**
     * 新增快递
     * (这里描述这个方法适用条件 – 可选)
     * @param tblExpress
     * @exception
     * @since 1.0.0
     */
    void saveExpress(TblExpress tblExpress);

    /**
     * 查询未处理的订单
     * (这里描述这个方法适用条件 – 可选)
     * @return
     * @exception
     * @since 1.0.0
     */
    Integer queryUntreatedOrder();

	/**
	 * 这里是方法描述：余额支付订单
	 *
	 * @date 2015年12月24日 下午3:22:56
	 * @param orderId	订单id
	 * @param cusId		客户id
	 * @throws Exception
	 * void
	 */
	void balancePay(final String orderId, final String cusId) throws Exception;
}
