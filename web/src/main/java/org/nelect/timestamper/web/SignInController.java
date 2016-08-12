package org.nelect.timestamper.web;

import org.nelect.timestamper.Principal;
import org.nelect.timestamper.user.AccountService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.WebRequest;

import static org.apache.commons.lang3.StringUtils.isBlank;

/**
 * Created by Michael on 2016/7/5.
 */
@Controller
@RequestMapping("/sign-in")
public class SignInController extends AbstractController {

    @RequestMapping(method = RequestMethod.GET)
    public String showForm() {
        return "login";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String signIn(@RequestParam(required = false) String email,
                         @RequestParam(required = false) String mobile,
                         @RequestParam String password,
                         WebRequest webRequest) {

        if (isBlank(email) && isBlank(mobile)) throw new IllegalArgumentException("邮箱和手机不能同时为空");

        Principal principal;
        AccountService accountService = sessionFactory.newSession().getAccountService();
        if (isBlank(email)) {
            principal = accountService.authenticateByMobile(mobile, password);
        } else {
            principal = accountService.authenticateByEmail(email, password);
        }

        webRequest.setAttribute("principal", new DiscriminatedPrincipal(principal),
            RequestAttributes.SCOPE_GLOBAL_SESSION);

        return "redirect:/";
    }
}
