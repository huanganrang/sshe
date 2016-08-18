package jb.controller;

import jb.listener.Application;
import jb.pageModel.*;
import jb.service.FmAdServiceI;
import jb.service.FmMarketServiceI;
import jb.service.FmMarqueeServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by zhi.huang on 2016/8/14.
 *
 *
 */
@Controller
@RequestMapping("/api/apiFmAdController")
public class ApiFmAdController extends BaseController {


    @Autowired
    private FmMarqueeServiceI fmMarqueeService;

    @Autowired
    private FmAdServiceI fmAdService;

    @RequestMapping("/marquee")
    @ResponseBody
    public Json marquee(FmMarquee fmMarquee, PageHelper ph) {
        Json j = new Json();
        try {
            j.setObj(fmMarqueeService.dataGrid(fmMarquee,ph));
            j.setSuccess(true);
            j.setMsg(SUCCESS_MESSAGE);
        } catch (Exception e) {
            j.setMsg(Application.getString(EX_0001));
            logger.error("轮播图异常", e);
        }

        return j;
    }

    @RequestMapping("/dataGrid")
    @ResponseBody
    public Json dataGrid(FmAd fmAd,PageHelper ph) {
        ph.setHiddenTotal(true);
        Json j = new Json();
        try {
            j.setObj(fmAdService.dataGrid(fmAd,ph));
            j.setSuccess(true);
            j.setMsg(SUCCESS_MESSAGE);
        } catch (Exception e) {
            j.setMsg(Application.getString(EX_0001));
            logger.error("广告异常", e);
        }

        return j;
    }
}
