package cn.shopping.lstsm_kgc.controller;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class SessionInterceptor extends HandlerInterceptorAdapter {

    /**
     * @param request  请求对象
     * @param response 响应对象
     * @param handler  前置对象
     * @return 返回false=拦截  返回true=继续执行请求
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        if ("OPTIONS".equals(request.getMethod())) {
            return true;
        }
        // session expire
        if (request.getSession(false) == null || request.getHeader("Authorization") == null) {
            response.sendError(HttpStatus.UNAUTHORIZED.value(), "登录超时，请重新登录");
            return false;
        }
        // validate token in session
        else if (request.getSession().getAttribute("token") != null) {
            String token = request.getHeader("Authorization");
            if (token.equals("Bearer " + request.getSession().getAttribute("token").toString())) {
                return true;
            } else {
                response.sendError(HttpStatus.UNAUTHORIZED.value(), "登录令牌无效，请重新登录");
                return false;
            }
        }
        // invalid session
        else {
            response.sendError(HttpStatus.UNAUTHORIZED.value(), "登录会话异常，请重新登录");
            return false;
        }
    }
}