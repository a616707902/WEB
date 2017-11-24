package com.chenpan.after.service.impl;

import com.chenpan.after.dao.LoginDao;
import com.chenpan.after.network.Response;
import com.chenpan.after.service.LoginService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @作者:陈攀
 * @公司：四川星盾科技股份有限公司
 * @创建时间: 2017/11/21 15:58
 * @描述：
 */

@Service
public class LoginServiceImpl implements LoginService {
    @Resource
    LoginDao loginDao;

    public Response login(String username, String password) {
        try {
            Map<String, String> loginMap = loginDao.login(username, password);
            if (loginMap != null) {
                return new Response().success(loginMap);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new Response().failure("登录失败");
        }
        return new Response().failure("登录失败");
    }
}
