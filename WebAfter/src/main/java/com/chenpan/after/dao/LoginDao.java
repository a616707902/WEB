package com.chenpan.after.dao;

import com.chenpan.after.network.Response;

import java.util.Map;

/**
 * @作者:陈攀
 * @公司：四川星盾科技股份有限公司
 * @创建时间: 2017/11/21 15:59
 * @描述：
 */
public interface LoginDao {
    Map<String,String> login(String username, String password) throws Exception;
}
