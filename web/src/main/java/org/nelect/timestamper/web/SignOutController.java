package org.nelect.timestamper.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.WebRequest;

/**
 * Created by Michael on 2016/7/13.
 */
@Controller
@RequestMapping("/sign-out")
public class SignOutController extends AbstractController {

    @RequestMapping(method = RequestMethod.GET)
    public String signOut(WebRequest webRequest) {
        webRequest.removeAttribute("principal", RequestAttributes.SCOPE_GLOBAL_SESSION);
        return "redirect:/sign-in";
    }
}
