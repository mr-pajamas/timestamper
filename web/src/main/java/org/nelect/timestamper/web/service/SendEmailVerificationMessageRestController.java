package org.nelect.timestamper.web.service;

import org.nelect.timestamper.user.AccountService;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Michael on 2016/7/3.
 */
@RestController
@RequestMapping("/email-verification")
public class SendEmailVerificationMessageRestController extends AbstractRestController {

    @RequestMapping(method = RequestMethod.GET)
    public VerificationCodeHashModel sendVerificationMessage(@RequestParam String email) {

        AccountService accountService = sessionFactory.newSession().getAccountService();

        return new VerificationCodeHashModel(accountService.sendEmailVerificationMessage(email));
    }
}
