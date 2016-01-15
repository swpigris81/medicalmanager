/**
 * 系统项目名称
 * com.medical.manager.common.util
 * SerialNumberUtil.java
 * 
 * 2015年12月18日-上午9:52:34
 * 2015版权所有
 * 
 */
package com.medical.manager.common.util;

import java.util.Random;

/**
 * @description 这里是方法描述
 *
 * @time 2015年12月18日 上午9:52:34
 * @author Json
 * @version 1.0.0
 */
public class SerialNumberUtil {
	/** 自定义补全字符 **/
	private static final char[] FILL_CHAR = { 'a', 'b', 'd', 'e', 'f', 'g',
			'h', 'i', 'j', 'm', 'n', 'q', 'r', 't' };
	/** 自定义进制所有字符 **/
	private static final String RADIX_STR = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	
	/**
	 * 根据id生成邀请码，id必须为数字。长度小于minLength时，自动补全至minLength长度
	 * @param num ID
	 * @param minLength 随机码最小长度
	 * @return 随机码
	 */
	public static String toSerialNumber(long num, int minLength) {
		return to36RadixString(num, minLength);
	}

	/**
	 * 根据id生成邀请码，id为自增数字，36进制转换，生成字符串小于最小长度时会补全<br>
	 * 类似16进制转换 除16取余数得最低1位，然后把商继续除得第2位，直到商等于0<br>
	 * 1)65036 除 16，余数 12(C)，商4064<br>
	 * 2)4064 除 16，余数 0(0)，商254<br>
	 * 3)254 除 16，余数14(E)，商15<br>
	 * 4)15除16，余数 15(F)，商0，结束<br>
	 * 5)得16进制为 FE0C
	 *
	 * @author Json
	 * @date 2015年12月22日 下午5:13:30
	 * @param num 		根据此数计算邀请码
	 * @param minLength	 最小长度，小于最小长度是，填充自定义随机字符 void
	 */
	private static String to36RadixString(long num, int minLength) {
		String serialNumber = "";
		char[] arr = RADIX_STR.toCharArray();
		int radix = RADIX_STR.length();
		long quotient = num / radix;	// 商
		long remainder = num % radix;	// 余数
		serialNumber = String.valueOf(arr[(int) remainder]);
		while (quotient != 0) {
			remainder = quotient % radix;	// 余数
			quotient = quotient / radix;	// 商
			serialNumber += arr[(int) remainder];
		}
		if (serialNumber.length() < minLength) {
			int fillLen = minLength - serialNumber.length();
			Random rand = new Random();
			for (int i = 0; i < fillLen; i++) {
				serialNumber += FILL_CHAR[rand.nextInt(FILL_CHAR.length)];
			}
		}

		return serialNumber;
	}

	public static String reverse(String str) {
		char[] arr = str.toCharArray();
		String s = "";
		for (int i = arr.length - 1; i >= 0; i--) {
			s += String.valueOf(arr[i]);
		}
		return s;
	}

	public static void main(String[] args) {
		System.out.println(to36RadixString(4000, 4));
	}
}
