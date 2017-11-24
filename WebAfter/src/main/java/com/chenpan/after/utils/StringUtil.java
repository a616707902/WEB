package com.chenpan.after.utils;

  
/**
 * 文件描述：字符串工具类
 * 作者：陈攀
 * 时间：2017/9/1
 * 部门：技术部
 * 修改人：
 * 修改时间：
 */
public class StringUtil {

	/**
	 * 给定字符串是否为空或空串
	 * @param str
	 * @return
	 */
	public static boolean isNotEmpty(String str) {
		if (str != null && str.length() != 0) {
			return true;
		}
		return false;
	}

	/**
	 * 给定字符串是否为空或空串
	 * @param str
	 * @return
	 */
	public static boolean isEmpty(String str) {
		if (str != null && str.length() != 0) {
			return false;
		}
		return true;
	}
}
