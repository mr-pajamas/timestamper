package org.nelect.timestamper.web.admin;

import javax.servlet.http.*;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * Created by Michael on 2016/7/13.
 */
public class AdminInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession(false);
        if (session != null) {
            Boolean admin = (Boolean) session.getAttribute("admin");
            if (admin != null && admin) return true;
        }

        response.sendError(403);
        return false;
    }
}
