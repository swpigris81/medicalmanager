/**
 * 系统项目名称
 * com.medical.manager.common.util
 * DistanceUtil.java
 * 
 * 2016年1月13日-上午10:05:37
 * 2015版权所有
 * 
 */
package com.medical.manager.common.util;

/**
 * @description 计算距离工具类
 *
 * @time 2016年1月13日 上午10:05:37
 * @author Json
 * @version 1.0.0
 */
public class DistanceUtil {

	private static final double EARTH_RADIUS = 6378.137;

	private static double rad(double d) {
		return d * Math.PI / 180.0;
	}

	/**
	 * 这里是方法描述：计算两个经纬度之间的距离，返回单位km
	 *
	 * @author wusj
	 * @time 2016年1月13日 上午10:07:27
	 * @param longitude1 经度1
	 * @param latitude1 纬度1
	 * @param longitude2 经度2
	 * @param latitude2  纬度2
	 * @return double
	 */
	public static double GetDistance(double longitude1, double latitude1,
			double longitude2, double latitude2) {
		double radLat1 = rad(latitude1);
		double radLat2 = rad(latitude2);
		double a = radLat1 - radLat2;
		double b = rad(longitude1) - rad(longitude2);
		double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2)
				+ Math.cos(radLat1) * Math.cos(radLat2)
				* Math.pow(Math.sin(b / 2), 2)));
		s = s * EARTH_RADIUS;
		s = Math.round(s * 10000) / 10000;
		return s;
	}
	
	public static void main(String[] args) {
		System.out.println(GetDistance(0, 0, 30, 0));
	}
}
