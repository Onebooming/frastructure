package com.onebooming.frastructure.controller.admin;


import com.onebooming.frastructure.constant.LogActions;
import com.onebooming.frastructure.constant.WebConst;
import com.onebooming.frastructure.controller.BaseController;
import com.onebooming.frastructure.exception.BusinessException;
import com.onebooming.frastructure.model.UserDomain;
import com.onebooming.frastructure.service.log.LogService;
import com.onebooming.frastructure.service.user.UserService;
import com.onebooming.frastructure.utils.APIResponse;
import com.onebooming.frastructure.utils.IPKit;
import com.onebooming.frastructure.utils.TaleUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 登录相关接口
 *
 * @author Onebooming
 * @version 1.0
 * @date 2019/12/21 12:39 下午
 */
@Api("登录相关接口")
@Controller
@RequestMapping(value = "/admin")
public class AuthController extends BaseController {

    private static final Logger LOGGER = LogManager.getLogger(AuthController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private LogService logService;

    @ApiOperation("跳转登录页")
    @GetMapping(value = "/login")
    public String login(){
        return "admin/login";
    }

    @ApiOperation("登录")
    @PostMapping(value = "/login")
    @ResponseBody
    public APIResponse toLogin(
            HttpServletRequest request,
            HttpServletResponse response,
            @ApiParam(name = "username", value = "用户名", required = true)
            @RequestParam(name = "username", required = true)
            String username,
            @ApiParam(name = "password", value = "密码", required = true)
            @RequestParam(name = "password", required = true)
            String password,
            @ApiParam(name = "remeber_me", value = "记住我", required = false)
            @RequestParam(name = "remeber_me", required = false)
            String remeber_me
    ){

        String ip= IPKit.getIpAddrByRequest(request); // 获取ip并过滤登录时缓存的bug
        Integer error_count = cache.hget("login_error_count",ip);
        try {
            UserDomain userInfo = userService.login(username, password);
            /**
             * 1、request.getSession()可以帮你得到HttpSession类型的对象，通常称之为session对象，session对象的作用域为一次会话，通常浏览器不关闭，保存的值就不会消失，当然也会出现session超时。服务器里面可以设置session的超时时间，web.xml中有一个session time out的地方，tomcat默认为30分钟
             * 2、session.setAttribute("key",value)；是session设置值的方法，原理同java中的HashMap的键值对，意思也就是key现在为“user”；存放的值为userName，userName应该为一个String类型的变量吧？看你自己的定义。
             * 3、可以使用session.getAttribute("key");来取值，以为着你能得到userName的值。
             * 这里LOGIN_SESSION_KEY = "login_user"，就是key为"login_user"，属性值为userInfo
             */
            request.getSession().setAttribute(WebConst.LOGIN_SESSION_KEY, userInfo);
            //如果选择"记住我"的话，则设置一个cookie，记住用户名和密码
            if (StringUtils.isNotBlank(remeber_me)) {
                TaleUtils.setCookie(response, userInfo.getUid());
            }
            logService.addLog(LogActions.LOGIN.getAction(), null, request.getRemoteAddr(), userInfo.getUid());
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            error_count = null == error_count ? 1 : error_count + 1;
            if (error_count > 3) {
                return APIResponse.fail("您输入密码已经错误超过3次，请10分钟后尝试");
            }
            cache.hset("login_error_count", ip,error_count, 10 * 60); // 加入ip的过滤
            String msg = "登录失败";
            if (e instanceof BusinessException) {
                msg = e.getMessage();
            } else {
                LOGGER.error(msg, e);
            }
            return APIResponse.fail(msg);
        }

        return APIResponse.success();

    }

    /**
     * 注销
     *
     * @param session
     * @param response
     */
    @RequestMapping("/logout")
    public void logout(HttpSession session, HttpServletResponse response, org.apache.catalina.servlet4preview.http.HttpServletRequest request) {
        session.removeAttribute(WebConst.LOGIN_SESSION_KEY);
        Cookie cookie = new Cookie(WebConst.USER_IN_COOKIE, "");
        cookie.setValue(null);
        cookie.setMaxAge(0);// 立即销毁cookie
        cookie.setPath("/");
        response.addCookie(cookie);
        try {
            response.sendRedirect("/admin/login");
        } catch (IOException e) {
            e.printStackTrace();
            LOGGER.error("注销失败", e);
        }
    }


}
