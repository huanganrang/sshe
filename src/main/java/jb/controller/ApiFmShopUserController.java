package jb.controller;

import farming.concurrent.CompletionService;
import farming.concurrent.Task;
import jb.absx.F;
import jb.listener.Application;
import jb.pageModel.*;
import jb.service.FmGoodsServiceI;
import jb.service.FmPurchaseServiceI;
import jb.service.FmShopUserServiceI;
import jb.service.FmUserServiceI;
import jb.service.impl.CompletionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by lenovo on 2016/8/25.
 *
 * 关注相关接口
 */
@Controller
@RequestMapping("/api/apiFmShopUserController")
public class ApiFmShopUserController extends BaseController {

    @Autowired
    private FmShopUserServiceI fmShopUserService;

    @Autowired
    private FmUserServiceI fmUserServiceI;

    @Autowired
    private FmPurchaseServiceI fmPurchaseService;

    @Autowired
    private FmGoodsServiceI fmGoodsService;

    /**
     * 查询关注的商家接口
     *
     * 必要参数:userId
     * 可选参数:page,rows,sort,order
     */
    @RequestMapping("/dataGrid")
    @ResponseBody
    public Json dataGrid(FmShopUser fmShopUser, PageHelper ph) {
        Json j = new Json();
        try {
            if(!F.empty(fmShopUser.getUserId())) {
                //根据用户id查询关注的商家用户id
                DataGrid dg = fmShopUserService.dataGrid(fmShopUser, ph);
                List<FmShopUser> list = dg.getRows();
                if(list != null && list.size() > 0) {
                    final CompletionService completionService = CompletionFactory.initCompletion();
                    for (int i=0; i<list.size(); i++) {
                        final FmShopUser fsu = list.get(i);
                        //根据商家用户id查询用户信息
                        completionService.submit(new Task<FmShopUser, FmUser>(fsu){
                            @Override
                            public FmUser call() throws Exception {
                                FmUser fmUser = fmUserServiceI.get(fsu.getShopId());
                                return fmUser;
                            }
                            protected void set(FmShopUser d, FmUser v) {
                                d.setFmShopUser(v);
                            }
                        });
                        //根据商家用户id查询用户采购记录
                        completionService.submit(new Task<FmShopUser, List<FmPurchase>>(fsu){
                            @Override
                            public List<FmPurchase> call() throws Exception {
                                FmPurchase fp = new FmPurchase();
                                fp.setUserId(fsu.getShopId());
                                List<FmPurchase> fmShopPurchaseList = fmPurchaseService.query(fp);
                                return fmShopPurchaseList;
                            }
                            protected void set(FmShopUser d, List<FmPurchase> v) {
                                d.setFmShopPurchaseList(v);
                            }
                        });
                        //根据商家用户id查询用户发布的商品
                        completionService.submit(new Task<FmShopUser, List<FmGoods>>(fsu){
                            @Override
                            public List<FmGoods> call() throws Exception {
                                FmGoods fg = new FmGoods();
                                fg.setUserId(fsu.getShopId());
                                List<FmGoods> fmShopGoodsList = fmGoodsService.query(fg);
                                return fmShopGoodsList;
                            }
                            protected void set(FmShopUser d, List<FmGoods> v) {
                                d.setFmShopGoodsList(v);
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
            logger.error("查询关注的商家异常", e);
        }

        return j;
    }

}
