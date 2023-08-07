package com.jsp.jboard.interceptor;

import com.jsp.jboard.session.SessionConst;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

@Slf4j
public class LoginCheckInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        if (session == null || session.getAttribute(SessionConst.LOGIN_MEMBER) == null) {
            // 로그인 성공하고 나면 다시 요청 했던 곳으로 보내주장
            String requestURI = request.getRequestURI();
            response.sendRedirect("/user/login?redirectURL=" + requestURI);
            return false;
        }
        return true;
    }
}
