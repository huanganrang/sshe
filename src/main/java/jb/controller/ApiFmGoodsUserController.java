package jb.controller;

import farming.concurrent.CompletionService;
import farming.concurrent.Task;
import jb.absx.F;
import jb.listener.Application;
import jb.pageModel.*;
import jb.service.FmGoodsServiceI;
import jb.service.FmGoodsUserServiceI;
import jb.service.impl.CompletionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by lenovo on 2016/8/23.
 *
 * 商品收藏相关接口
 */
@Controller
@RequestMapping("/api/apiFmGoodsUserController")
public class ApiFmGoodsUserController extends BaseController {

    @Autowired
    private FmGoodsUserServiceI fmGoodsUserService;

    @Autowired
    private FmGoodsServiceI fmGoodsService;

    /**
     * 收藏查询接口
     *
     * 必要参数:userId
     * 可选参数:page,rows,sort,order
     */
    @RequestMapping("/dataGrid")
    @ResponseBody
    public Json dataGrid(FmGoodsUser fmGoodsUser, PageHelper ph) {
        Json j = new Json();
        try {
            if(!F.empty(fmGoodsUser.getUserId())) {
                //根据用户id查询收藏的商品
                DataGrid dg = fmGoodsUserService.dataGrid(fmGoodsUser, ph);
                List<FmGoodsUser> list = dg.getRows();
                if(list != null && list.size() > 0) {
                    final CompletionService completionService = CompletionFactory.initCompletion();
                    for (int i=0; i<list.size(); i++) {
                        final FmGoodsUser fg = list.get(i);
                        //根据商品id商品信息
                        completionService.submit(new Task<FmGoodsUser, FmGoods>(fg){
                            @Override
                            public FmGoods call() throws Exception {
                                FmGoods fmGoods = fmGoodsService.get(fg.getGoodsId());
                                return fmGoods;
                            }
                            protected void set(FmGoodsUser d, FmGoods v) {
                                d.setFmGoods(v);
                            }
                        });
                    }
                    completionService.sync();
                }
                j.setSuccess(true);
                j.setMsg(SUCCESS_MESSAGE);
                j.setObj(dg);
            }
        } catch (Exception e) {
            j.setMsg(Application.getString(EX_0001));
            logger.error("收藏查询异常", e);
        }

        return j;
    }

    /**
     * 批量删除收藏接口
     *
     * 必要参数:userId,goodsIds
     *
     * goodsIds为商品id，用逗号分隔
     */
    @RequestMapping("/delete")
    @ResponseBody
    public Json delete(FmGoodsUser fmGoodsUser, String goodsIds) {
        Json j = new Json();
        try {
            if(!F.empty(fmGoodsUser.getUserId()) && !F.empty(goodsIds)) {
                if(goodsIds.contains(",")) {
                    String[] strs = goodsIds.split(",");
                    final CompletionService completionService = CompletionFactory.initCompletion();
                    for (String goodsId : strs) {
                        final FmGoodsUser fgu = new FmGoodsUser();
                        fgu.setUserId(fmGoodsUser.getUserId());
                        fgu.setGoodsId(goodsId);
                        completionService.submit(new Task<FmGoodsUser, Boolean>(fgu){
                            @Override
                            public Boolean call() throws Exception {
                                fmGoodsUserService.delete(fgu);
                                return true;
                            }
                        });
                    }
                } else {
                    FmGoodsUser fgu = new FmGoodsUser();
                    fgu.setUserId(fmGoodsUser.getUserId());
                    fgu.setGoodsId(goodsIds);
                    fmGoodsUserService.delete(fgu);
                }
                j.setSuccess(true);
                j.setMsg(SUCCESS_MESSAGE);
            }
        } catch (Exception e) {
            j.setMsg(Application.getString(EX_0001));
            logger.error("批量删除收藏异常", e);
        }

        return j;
    }

}
