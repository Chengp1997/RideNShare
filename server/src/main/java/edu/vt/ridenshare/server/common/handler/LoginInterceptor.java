package edu.vt.ridenshare.server.common.handler;

import com.alibaba.fastjson.JSON;
import edu.vt.ridenshare.server.entity.User;
import edu.vt.ridenshare.server.service.UserService;
import edu.vt.ridenshare.server.util.UserThreadLocal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Component
@Slf4j
public class LoginInterceptor implements HandlerInterceptor {

    @Autowired
    private UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //check the path -> not handler method(not controller method) let go.
        if(! (handler instanceof HandlerMethod))return true;

        //get session
        HttpSession session = request.getSession();

        log.info("==========Login Interceptor: request start=========");
        String requestURI = request.getRequestURI();
        log.info("servletPath"+request.getServletPath());
        log.info("current uri:{}",requestURI);
        log.info("request method:{}",request.getMethod());
        log.info("session:{}", session);
        log.info("sessionId: {}",session.getId());
        User user = (User) session.getAttribute("user");
        log.info("==========Login Interceptor: request end===========\n");
        if (user != null){
            UserThreadLocal.put(user);
            return true;
        }else{
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().println(JSON.toJSONString("Login first"));
            return false;
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        UserThreadLocal.remove();
    }
}
