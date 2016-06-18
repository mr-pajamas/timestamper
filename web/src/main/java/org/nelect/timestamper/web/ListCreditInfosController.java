package org.nelect.timestamper.web;

import java.util.List;

import org.nelect.timestamper.partner.CreditInfo;
import org.nelect.timestamper.partner.CreditworthinessService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Michael on 2016/5/31.
 */
@Controller
@RequestMapping("/credit-info")
public class ListCreditInfosController extends AbstractController {

    @RequestMapping(value = "/{type}", method = RequestMethod.GET)
    public ModelAndView listCreditInfos(@PathVariable("type") String type,
                                        @RequestParam("q") String checkIdOrName,
                                        @RequestParam(value = "offset", required = false) Integer offset,
                                        @RequestParam(value = "limit", required = false) Integer limit) {

        CreditworthinessService service = sessionFactory.newSession().getCreditworthinessService();

        if (offset == null) offset = 0;
        if (limit == null) limit = 10;

        List<CreditInfo> creditInfos;

        if ("manufacturers".equalsIgnoreCase(type)) creditInfos = service.findManufacturers(checkIdOrName, offset, limit);
        else creditInfos = service.findProducts(checkIdOrName, offset, limit);

        ModelAndView mav = new ModelAndView("search_kexin");
        mav.addObject("creditInfos", creditInfos);
        mav.addObject("currentQuery", checkIdOrName);

        return mav;
    }
}
