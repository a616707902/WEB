package com.chenpan.after.utils;

import java.util.Collection;

/**
 * 文件描述：工具类
 * 作者：陈攀
 * 时间：2017/9/1
 * 部门：技术部
 * 修改人：
 * 修改时间：
 */
public class ListUtil {
	/**
	 * 判断是否为空
	 * @param c
	 * @return
	 */
	public static boolean isNotEmpty(Collection<?> c){
		if (c != null && c.size() != 0 ) {
			return true;
		}
		return false;
	}
}
