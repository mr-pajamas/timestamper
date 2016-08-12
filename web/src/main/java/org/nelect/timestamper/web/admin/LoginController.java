package org.nelect.timestamper.web.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.WebRequest;

/**
 * Created by Michael on 2016/7/13.
 */
@Controller
@RequestMapping(value = "/admin/login")
public class LoginController extends AdminController {

    @RequestMapping(method = RequestMethod.GET)
    public String showForm() {
        return "admin-login";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String login(@RequestParam String password,
                        WebRequest webRequest) {

        if (password.equals(timestamperConfig.getProperty("admin.password"))) {
            webRequest.setAttribute("admin", true, RequestAttributes.SCOPE_GLOBAL_SESSION);
            return "redirect:/admin/dashboard";
        } else {
            return "admin-login";
        }
    }
}
