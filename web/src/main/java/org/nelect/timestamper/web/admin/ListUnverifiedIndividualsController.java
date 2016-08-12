package org.nelect.timestamper.web.admin;

import java.util.LinkedList;
import java.util.List;

import org.nelect.timestamper.Principal;
import org.nelect.timestamper.admin.UserFilter;
import org.nelect.timestamper.admin.UserManagementService;
import org.nelect.timestamper.web.DiscriminatedPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Michael on 2016/7/18.
 */
@Controller
@RequestMapping("/admin/unverified-individuals")
public class ListUnverifiedIndividualsController extends AdminController {

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView listUsers(@RequestParam(defaultValue = "0") Integer offset,
                                  @RequestParam(defaultValue = "0") Integer limit) {

        UserManagementService userManagementService = sessionFactory.newAdminSession().getUserManagementService();

        List<DiscriminatedPrincipal> discriminatedUsers = new LinkedList<>();

        UserFilter filter = new UserFilter();
        filter.setIdentityType(UserFilter.IdentityType.INDIVIDUAL);
        filter.setUnverified(true);

        List<Principal> users = userManagementService.findUsers(filter, offset, limit);

        for (Principal user : users) {
            discriminatedUsers.add(new DiscriminatedPrincipal(user));
        }

        ModelAndView mav = new ModelAndView("admin-unverified-individuals");
        mav.addObject("users", discriminatedUsers);

        return mav;
    }
}
