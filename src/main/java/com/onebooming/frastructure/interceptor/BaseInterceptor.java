package com.onebooming.frastructure.interceptor;

import com.onebooming.frastructure.constant.Types;
import com.onebooming.frastructure.constant.WebConst;
import com.onebooming.frastructure.model.OptionsDomain;
import com.onebooming.frastructure.model.UserDomain;
import com.onebooming.frastructure.service.option.OptionService;
import com.onebooming.frastructure.service.user.UserService;
import com.onebooming.frastructure.utils.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 自定拦截器
 * @author Onebooming
 * @version 1.0
 * @date 2019/12/12 16:29
 */
@Component
public class BaseInterceptor implements HandlerInterceptor {
    private static final Logger LOGGER = LoggerFactory.getLogger(BaseInterceptor.class);
    private static final String USER_AGENT = "user-agent";

    @Autowired
    private UserService userService;

    @Autowired
    private OptionService optionService;

    @Autowired
    private Commons commons;

    @Autowired
    private AdminCommons adminCommons;

    private MapCache cache = MapCache.single();
    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object object) throws Exception{
        String uri = request.getRequestURI();

        LOGGER.info("UserAgent: {}",request.getHeader(USER_AGENT));
        LOGGER.info("用户访问地址: {}, 来路地址: {}", uri, IPKit.getIpAddrByRequest(request));

        //请求拦截处理
        UserDomain user = TaleUtils.getLoginUser(request);
        if(null == user){
            Integer uid = TaleUtils.getCookieUid(request);
            if( null != uid){
                //这里还是有安全隐患，cookie是可以伪造的
                user = userService.getUserInfoById(uid);
                request.getSession().setAttribute(WebConst.LOGIN_SESSION_KEY, user);
            }
        }
        if (uri.startsWith("/admin") && !uri.startsWith("/admin/login") && null == user
                && !uri.startsWith("/admin/css") && !uri.startsWith("/admin/images")
                && !uri.startsWith("/admin/js") && !uri.startsWith("/admin/plugins")
                && !uri.startsWith("/admin/editormd") &&
                !uri.startsWith("/advance/dist/css") && !uri.startsWith("/advance/dist/img")
         && !uri.startsWith("/advance/dist/js") && !uri.startsWith("/advance/plugins")) {
            response.sendRedirect(request.getContextPath() + "/admin/login");
            return false;
        }
        //设置get请求token
        if(request.getMethod().equals("GET")){
            String csrf_token = UUID.UU64();
            //默认存储30分钟
            cache.hset(Types.CSRF_TOKEN.getType(),csrf_token,uri);
            request.setAttribute("_csrf_token", csrf_token);
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request,
                           HttpServletResponse response,
                           Object object,
                           ModelAndView modelAndView) throws Exception{
        OptionsDomain ov = optionService.getOptionByName("site_record");
        request.setAttribute("commons",commons);//一些工具类和公共方法
        request.setAttribute("option",ov);
        request.setAttribute("adminCommons", adminCommons);
        initSiteConfig(request);
    }

    private void initSiteConfig(HttpServletRequest request) {
        if(WebConst.initConfig.isEmpty()){
            List<OptionsDomain> options = optionService.getOptions();
            Map<String, String> querys = new HashMap<String,String>();
            options.forEach(option ->{
                querys.put(option.getName(),option.getValue());
            });
            WebConst.initConfig = querys;
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
