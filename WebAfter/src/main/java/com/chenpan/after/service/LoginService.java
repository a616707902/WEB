package com.chenpan.after.service;

import com.chenpan.after.network.Response;

/**
 * @作者:陈攀
 * @公司：四川星盾科技股份有限公司
 * @创建时间: 2017/11/21 15:57
 * @描述：
 */
public interface LoginService {


    Response login(String username, String password) ;
}
