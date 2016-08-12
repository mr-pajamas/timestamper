package org.nelect.timestamper.web.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.WebRequest;

/**
 * Created by Michael on 2016/7/13.
 */
@Controller
@RequestMapping(value = "/admin/logout")
public class LogoutController extends AdminController {

    @RequestMapping(method = RequestMethod.GET)
    public String logout(WebRequest webRequest) {
        webRequest.removeAttribute("admin", RequestAttributes.SCOPE_GLOBAL_SESSION);
        return "redirect:/admin/login";
    }
}
