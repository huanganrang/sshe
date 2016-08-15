package jb.controller;

import jb.listener.Application;
import jb.pageModel.FmPurchase;
import jb.pageModel.Json;
import jb.service.FmPurchaseServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by guxin on 2016/8/14.
 */
@Controller
@RequestMapping("/api/apiFmPurchaseController")
public class ApiFmPurchaseController extends BaseController {


    @Autowired
    private FmPurchaseServiceI fmPurchaseService;

    @RequestMapping("/add")
    @ResponseBody
    public Json add(FmPurchase fmPurchase) {
        Json j = new Json();
        try {
            fmPurchase.setIsdeleted(false);
            fmPurchaseService.add(fmPurchase);
            j.setSuccess(true);
            j.setMsg(SUCCESS_MESSAGE);
        } catch (Exception e) {
            j.setMsg(Application.getString(EX_0001));
            logger.error("发布采购异常", e);
        }

        return j;
    }

}
