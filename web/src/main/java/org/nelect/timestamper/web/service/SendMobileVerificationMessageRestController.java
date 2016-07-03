package org.nelect.timestamper.web.service;

import org.nelect.timestamper.user.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Michael on 2016/7/2.
 */
@RestController
@RequestMapping("/mobile-verification")
public class SendMobileVerificationMessageRestController extends AbstractRestController {

    @RequestMapping(method = RequestMethod.GET)
    public VerificationCodeHashModel sendVerificationMessage(@RequestParam String mobile) {

        AccountService accountService = sessionFactory.newSession().getAccountService();

        return new VerificationCodeHashModel(accountService.sendMobileVerificationMessage(mobile));
    }
}
