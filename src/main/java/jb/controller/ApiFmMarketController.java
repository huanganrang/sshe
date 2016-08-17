package jb.controller;

import jb.listener.Application;
import jb.pageModel.FmMarket;
import jb.pageModel.FmPurchase;
import jb.pageModel.Json;
import jb.service.FmMarketServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by guxin on 2016/8/14.
 *
 * 市场接口
 */
@Controller
@RequestMapping("/api/apiFmMarketController")
public class ApiFmMarketController extends BaseController {


    @Autowired
    private FmMarketServiceI fmMarketService;

    @RequestMapping("/add")
    @ResponseBody
    public Json add(FmMarket fmMarket) {
        Json j = new Json();
        try {
            fmMarketService.add(fmMarket);
            j.setSuccess(true);
            j.setMsg(SUCCESS_MESSAGE);
        } catch (Exception e) {
            j.setMsg(Application.getString(EX_0001));
            logger.error("添加市场异常", e);
        }

        return j;
    }

    @RequestMapping("/list")
    @ResponseBody
    public Json query(FmMarket fmMarket) {
        Json j = new Json();
        try {
            j.setObj(fmMarketService.query(fmMarket));
            j.setSuccess(true);
            j.setMsg(SUCCESS_MESSAGE);
        } catch (Exception e) {
            j.setMsg(Application.getString(EX_0001));
            logger.error("添加市场异常", e);
        }

        return j;
    }
}
