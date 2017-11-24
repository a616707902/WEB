package com.chenpan.after.controller;

import com.chenpan.after.bean.User;
import com.chenpan.after.network.Response;
import com.chenpan.after.service.LoginService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * @作者:陈攀
 * @公司：四川星盾科技股份有限公司
 * @创建时间: 2017/11/20 17:02
 * @描述：
 */
@RestController
public class LoginController {
    @Resource
    LoginService loginService;

    @RequestMapping(value = "login/login", method = RequestMethod.GET, produces = "application/json")
    public Response login( String loginname, String password, HttpServletRequest request) {
        String username = request.getParameter("loginname");
        String pwd = request.getParameter("password");
        return loginService.login(loginname, password);
    }
  /*  @RequestMapping(value = "login/login", method = RequestMethod.POST,produces = "application/json")
    public Response loginpost( @RequestParam String loginname, @RequestParam String password, HttpServletRequest request) {
        String username = request.getParameter("loginname");
        String pwd = request.getParameter("password");
        return loginService.login(loginname, password);
    }*/
 /*   @RequestMapping(value = "login/login", method = RequestMethod.POST,produces = "application/json")
    public Response loginobj( @RequestBody @Valid User user) {
//        String username = request.getParameter("loginname");
//        String pwd = request.getParameter("password");
        return loginService.login(user.getLoginname(), user.getPassword());
    }*/
}
