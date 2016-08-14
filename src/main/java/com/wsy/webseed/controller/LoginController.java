package com.wsy.webseed.controller;

import com.wsy.webseed.service.RBACService;
import com.wsy.webseed.util.Operation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping(value = "/login")
public class LoginController {
    private Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private RBACService rbacService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String loginIn() {
        return "login/login";
    }

    @RequestMapping(value = "/", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String login(HttpServletRequest request) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String result = "";
        final UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        try {

            final Subject currentUser = SecurityUtils.getSubject();
            currentUser.login(token);
            if (currentUser.isAuthenticated()) {
                result = Operation.result(Operation.successCode, "登录成功");
                LOGGER.info("系统日志:[{}] 登录成功.", username);
            } else {
                result = Operation.result(Operation.failCode, "登录失败");
                LOGGER.warn("系统日志:[{}] 登录失败.", username);
                token.clear();
            }

        } catch (AuthenticationException e) {
            result = Operation.result(Operation.failCode, "用户名或密码错误");
            LOGGER.warn("系统日志:[{}] 登录失败.", username);
        } catch (Exception e) {
            LOGGER.error("登录服务不可用: {}", e);
            result = Operation.result(Operation.failCode, "登录服务不可用");
        }

        return result;
    }

    @RequestMapping(value = "/logout", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String logout(HttpServletRequest request) {
        String result = Operation.result(Operation.failCode, "注销失败");
        try {
            HttpSession session = request.getSession();
            session.invalidate();
            result = Operation.result(Operation.successCode, "注销成功");
            LOGGER.debug("user logout sucess");
        } catch (Exception e) {
            LOGGER.error("logout服务不可用:{}",e);
        }
        return result;
    }

}
