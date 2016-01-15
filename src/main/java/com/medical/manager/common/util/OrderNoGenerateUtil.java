
/**
 * 系统项目名称
 * com.supermarket.common.util
 * OrderNoGenerateUtil.java
 * 
 * 2015年11月26日-下午5:14:22
 * 2015
 * 
 */
 
package com.medical.manager.common.util;


/**
 * 
 * OrderNoGenerateUtil 订单号生成器
 * 
 * 2015年11月26日 下午5:14:22
 * 
 * @version 1.0.0
 * 
 */
public class OrderNoGenerateUtil {
    private static int ORDER_SEQ_NO = 99999;
    private int current = 0;
    private static  OrderNoGenerateUtil instance;
    private OrderNoGenerateUtil() {
    }
    
    public static OrderNoGenerateUtil getinstance() {
        if(instance == null){
            instance = new OrderNoGenerateUtil();
        }
        return instance;
    }
    private synchronized int getNextSeqNo(){
        current ++;
        if(current > ORDER_SEQ_NO){
            current = 0;
        }
        return current;
    }
    public String getOrderNo(String memberCode){
        StringBuffer orderNo = new StringBuffer();
        orderNo.append(DateUtil.getTime()).append(memberCode);
        orderNo.append(String.format("%05d", getNextSeqNo()));
        return orderNo.toString();
    }
    public static void main(String[] args) {
        OrderNoGenerateUtil util = OrderNoGenerateUtil.getinstance();
        System.out.println(util.getOrderNo("1111"));
    }
}
