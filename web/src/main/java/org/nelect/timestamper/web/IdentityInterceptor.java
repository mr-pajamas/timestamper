package org.nelect.timestamper.web;

import javax.servlet.http.*;

import org.nelect.timestamper.Principal;
import org.nelect.timestamper.SessionFactory;
import org.nelect.timestamper.user.IdentityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * Created by Michael on 2016/7/6.
 */
public class IdentityInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    protected SessionFactory sessionFactory;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession(false);
        if (session != null) {
            DiscriminatedPrincipal discriminatedPrincipal = (DiscriminatedPrincipal) session.getAttribute("principal");
            if (discriminatedPrincipal != null && discriminatedPrincipal.getType() == DiscriminatedPrincipal.Type.UNVERIFIED) {
                Principal principal = discriminatedPrincipal.getValue();
                IdentityService identityService = sessionFactory.newSession(principal).getIdentityService();
                principal = identityService.getIdentity();
                discriminatedPrincipal = new DiscriminatedPrincipal(principal);
                session.setAttribute("principal", discriminatedPrincipal);
            }
        }

        return true;
    }
}
