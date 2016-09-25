package jb.controller;

import farming.concurrent.CacheKey;
import farming.concurrent.CompletionService;
import farming.concurrent.Task;
import jb.absx.F;
import jb.listener.Application;
import jb.pageModel.*;
import jb.service.FmPurchaseServiceI;
import jb.service.FmPurchaseUserServiceI;
import jb.service.FmShopUserServiceI;
import jb.service.FmUserServiceI;
import jb.service.impl.CompletionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by guxin on 2016/8/14.
 *
 * 采购相关接口
 */
@Controller
@RequestMapping("/api/apiFmPurchaseController")
public class ApiFmPurchaseController extends BaseController {

    @Autowired
    private FmPurchaseServiceI fmPurchaseService;

    @Autowired
    private FmPurchaseUserServiceI fmPurchaseUserServiceI;

    @Autowired
    private FmUserServiceI fmUserServiceI;

    @Autowired
    private FmShopUserServiceI fmShopUserService;

    /**
     * 发布采购(我要采购)
     *
     *参数:name,startPrice,endPrice,unit,minNum,maxNum,status,bornArea,transactionArea,
     * startTime,endTime,images,require,diameter,diameterUnit,color,isPack,pack,formatDesc,
     * voiceUrl,voiceDuration,userId,onlineStatus
     */
    @RequestMapping("/add")
    @ResponseBody
    public Json add(FmPurchase fmPurchase) {
        Json j = new Json();
        try {
            fmPurchaseService.add(fmPurchase);
            j.setSuccess(true);
            j.setMsg(SUCCESS_MESSAGE);
        } catch (Exception e) {
            j.setMsg(Application.getString(EX_0001));
            logger.error("发布采购异常", e);
        }

        return j;
    }

    /**
     * 采购发布记录
     *
     * 可选参数:userId,name,transactionArea,onlineStatus,page,rows,sort,order
     */
    @RequestMapping("/dataGrid")
    @ResponseBody
    public Json dataGrid(FmPurchase fmPurchase, PageHelper ph) {
        Json j = new Json();
        try {
            //根据用户id和采购状态查询采购记录
            DataGrid dg = fmPurchaseService.dataGrid(fmPurchase, ph);
            List<FmPurchase> list = dg.getRows();
            if(list != null && list.size() > 0) {
                final CompletionService completionService = CompletionFactory.initCompletion();
                for (int i=0; i<list.size(); i++) {
                    final FmPurchase fp = list.get(i);
                    //根据采购id查询询价用户id
                    completionService.submit(new Task<FmPurchase, List<FmPurchaseUser>>(fp){
                        @Override
                        public List<FmPurchaseUser> call() throws Exception {
                            FmPurchaseUser fmPurchaseUser = new FmPurchaseUser();
                            fmPurchaseUser.setPurchaseId(fp.getId());
                            List<FmPurchaseUser> fmPurchaseUserList = fmPurchaseUserServiceI.query(fmPurchaseUser);
                            return fmPurchaseUserList;
                        }
                        protected void set(FmPurchase d, List<FmPurchaseUser> fmPurchaseUserList) {
                            if(fmPurchaseUserList != null && fmPurchaseUserList.size() > 0) {
                                for (int m=0; m<fmPurchaseUserList.size(); m++) {
                                    final FmPurchaseUser fmPurchaseUser = fmPurchaseUserList.get(m);
                                    //根据用户id查询用户信息
                                    completionService.submit(new Task<FmPurchaseUser, FmUser>(new CacheKey("user",fmPurchaseUser.getUserId()),fmPurchaseUser){
                                        @Override
                                        public FmUser call() throws Exception {
                                            FmUser fmUser = fmUserServiceI.get(getD().getUserId());
                                            return fmUser;
                                        }
                                        protected void set(FmPurchaseUser d, FmUser v) {
                                            d.setFmUser(v);
                                        }
                                    });
                                }
                                d.setFmPurchaseUserList(fmPurchaseUserList);
                            }
                        }
                    });
                }
                completionService.sync();
            }
            j.setSuccess(true);
            j.setMsg(SUCCESS_MESSAGE);
            j.setObj(dg);
        } catch (Exception e) {
            j.setMsg(Application.getString(EX_0001));
            logger.error("查询采购记录异常", e);
        }

        return j;
    }

    /**
     * 采购记录详情
     *
     * 必要参数:id,userId
     *
     */
    @RequestMapping("/get")
    @ResponseBody
    public Json get(final FmPurchase fmPurchase) {
        Json j = new Json();
        try {
            final String  id = fmPurchase.getId();
            final String  userId = fmPurchase.getUserId();
            if(!F.empty(id) && !F.empty(userId)) {
                final FmPurchase fp = fmPurchaseService.get(id);
                final CompletionService completionService = CompletionFactory.initCompletion();
                //查询询价用户信息
                completionService.submit(new Task<FmPurchase, List<FmPurchaseUser>>(fp){
                    @Override
                    public List<FmPurchaseUser> call() throws Exception {
                        FmPurchaseUser fmPurchaseUser = new FmPurchaseUser();
                        fmPurchaseUser.setPurchaseId(id);
                        List<FmPurchaseUser> fmPurchaseUserList = fmPurchaseUserServiceI.query(fmPurchaseUser);

                        return fmPurchaseUserList;
                    }
                    protected void set(FmPurchase d, List<FmPurchaseUser> fmPurchaseUserList) {
                        if(fmPurchaseUserList != null && fmPurchaseUserList.size() > 0) {
                            d.setFmPurchaseUserCount(fmPurchaseUserList.size());
                            for (int m=0; m<fmPurchaseUserList.size(); m++) {
                                final FmPurchaseUser fmPurchaseUser = fmPurchaseUserList.get(m);
                                //根据用户id查询用户信息
                                completionService.submit(new Task<FmPurchaseUser, FmUser>(fmPurchaseUser){
                                    @Override
                                    public FmUser call() throws Exception {
                                        FmUser fmUser = fmUserServiceI.get(getD().getUserId());
                                        return fmUser;
                                    }
                                    protected void set(FmPurchaseUser d, FmUser v) {
                                        d.setFmUser(v);
                                    }
                                });
                            }
                            d.setFmPurchaseUserList(fmPurchaseUserList);
                        }
                    }
                });
                //非自己的采购详情时添加用户的相关信息
                if(!userId.equals(fp.getUserId())) {
                    completionService.submit(new Task<FmPurchase, FmUser>(fp){
                        @Override
                        public FmUser call() throws Exception {
                            FmUser fmUser = fmUserServiceI.get(fp.getUserId());
                            return fmUser;
                        }
                        protected void set(FmPurchase d, FmUser v) {
                            d.setFmUser(v);
                            //判断是否关注过此发布采购的用户
                            if(v != null) {
                                FmShopUser fmShopUser = new FmShopUser();
                                fmShopUser.setUserId(userId);
                                fmShopUser.setShopId(v.getId());
                                FmShopUser fs = fmShopUserService.get(fmShopUser);
                                if(fs != null) {
                                    d.setIsAttention(true);
                                } else {
                                    d.setIsAttention(false);
                                }
                            }
                        }
                    });
                }
                completionService.sync();
                j.setSuccess(true);
                j.setMsg(SUCCESS_MESSAGE);
                j.setObj(fp);
            }
        } catch (Exception e) {
            j.setMsg(Application.getString(EX_0001));
            logger.error("查询采购记录详情异常", e);
        }

        return j;
    }

}
