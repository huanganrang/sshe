package jb.controller;

import farming.concurrent.CacheKey;
import farming.concurrent.CompletionService;
import farming.concurrent.Task;
import jb.listener.Application;
import jb.pageModel.*;
import jb.service.FmAdServiceI;
import jb.service.FmGoodsServiceI;
import jb.service.FmMarketServiceI;
import jb.service.FmMarqueeServiceI;
import jb.service.impl.CompletionFactory;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

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

    @Autowired
    private FmGoodsServiceI fmGoodsService;

    @RequestMapping("/marquee")
    @ResponseBody
    public Json marquee(FmMarquee fmMarquee, PageHelper ph) {
        ph.setHiddenTotal(true);
        Json j = new Json();
        try {
            DataGrid dataGrid = fmMarqueeService.dataGrid(fmMarquee, ph);
            j.setObj(dataGrid);
            if (!CollectionUtils.isEmpty(dataGrid.getRows())) {
                final CompletionService completionService = CompletionFactory.initCompletion();
                for (FmMarquee fmMarquee1 : (List<FmMarquee>) dataGrid.getRows()) {
                    completionService.submit(new Task<FmMarquee, FmGoods>(new CacheKey("goods",fmMarquee1.getGoodsId()),fmMarquee1) {
                        @Override
                        public FmGoods call() throws Exception {
                            FmGoods user = fmGoodsService.get(getD().getLoginId());
                            return user;
                        }

                        protected void set(FmMarquee d, FmGoods v) {
                            if (v != null)
                                d.setFmGoods(v);
                        }

                    });
                }
                completionService.sync();
            }
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
            DataGrid dataGrid = fmAdService.dataGrid(fmAd, ph);
            j.setObj(dataGrid);
            if (!CollectionUtils.isEmpty(dataGrid.getRows())) {
                final CompletionService completionService = CompletionFactory.initCompletion();
                for (FmAd fmAd1 : (List<FmAd>) dataGrid.getRows()) {
                    completionService.submit(new Task<FmAd, FmGoods>(new CacheKey("goods",fmAd1.getGoodsId()),fmAd1) {
                        @Override
                        public FmGoods call() throws Exception {
                            FmGoods user = fmGoodsService.get(getD().getLoginId());
                            return user;
                        }

                        protected void set(FmAd d, FmGoods v) {
                            if (v != null)
                                d.setFmGoods(v);
                        }

                    });
                }
                completionService.sync();
            }
            j.setSuccess(true);
            j.setMsg(SUCCESS_MESSAGE);
        } catch (Exception e) {
            j.setMsg(Application.getString(EX_0001));
            logger.error("广告异常", e);
        }

        return j;
    }
}
