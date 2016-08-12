package org.nelect.timestamper.web.admin;

import org.nelect.timestamper.admin.UserManagementService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Michael on 2016/7/18.
 */
@Controller
@RequestMapping("/admin/verify-user")
public class VerifyUserController extends AdminController {

    @RequestMapping(method = RequestMethod.GET)
    public String pass(@RequestParam("type") String userType,
                       @RequestParam String userId) {
        UserManagementService userManagementService = sessionFactory.newAdminSession().getUserManagementService();

        userManagementService.verify(userId);

        if (userType.equalsIgnoreCase("INDIVIDUAL"))
            return "redirect:/admin/unverified-individuals";
        else
            return "redirect:/admin/unverified-organizations";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String fail(@RequestParam("type") String userType,
                       @RequestParam String userId,
                       @RequestParam String failReasons) {
        UserManagementService userManagementService = sessionFactory.newAdminSession().getUserManagementService();

        userManagementService.verify(userId, failReasons);

        if (userType.equalsIgnoreCase("INDIVIDUAL"))
            return "redirect:/admin/unverified-individuals";
        else
            return "redirect:/admin/unverified-organizations";
    }
}
