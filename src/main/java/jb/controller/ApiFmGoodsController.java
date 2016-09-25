package jb.controller;

import farming.concurrent.CacheKey;
import farming.concurrent.CompletionService;
import farming.concurrent.Task;
import jb.absx.F;
import jb.listener.Application;
import jb.pageModel.*;
import jb.service.FmGoodsServiceI;
import jb.service.FmUserServiceI;
import jb.service.impl.CompletionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lenovo on 2016/8/26.
 *
 * 发布商品类接口
 */
@Controller
@RequestMapping("/api/apiFmGoodsController")
public class ApiFmGoodsController extends BaseController {

    @Autowired
    private FmGoodsServiceI fmGoodsService;

    @Autowired
    private FmUserServiceI fmUserServiceI;

    /**
     * 发布商品接口
     *
     *参数:name,price,unit,minBatch,status,bornArea,storage,offlineTime,contactor,contactorMobile,goalArea,preOnlineTime,
     * transactionArea,sendTime,transactionTime,carNo,images,description,formatDesc,onlineStatus,userId,grade,extFields
     *
     */
    @RequestMapping("/add")
    @ResponseBody
    public Json add(FmGoods fmGoods) {
        Json j = new Json();
        try {
            fmGoodsService.add(fmGoods);
            j.setSuccess(true);
            j.setMsg(SUCCESS_MESSAGE);
        } catch (Exception e) {
            j.setMsg(Application.getString(EX_0001));
            logger.error("发布商品异常", e);
        }

        return j;
    }

    /**
     * 编辑商品信息接口
     *
     * 参数:name,price,unit,minBatch,status,bornArea,storage,offlineTime,contactor,contactorMobile,goalArea,preOnlineTime,
     * transactionArea,sendTime,transactionTime,carNo,images,description,formatDesc,onlineStatus,userId,grade,extFields
     */
    @RequestMapping("/edit")
    @ResponseBody
    public Json edit(FmGoods fmGoods) {
        Json j = new Json();
        try {
            if(!F.empty(fmGoods.getId())) {
                fmGoodsService.edit(fmGoods);
                j.setSuccess(true);
                j.setMsg(SUCCESS_MESSAGE);
            }
        } catch (Exception e) {
            j.setMsg(Application.getString(EX_0001));
            logger.error("编辑商品信息异常", e);
        }

        return j;
    }

    /**
     * 获取商品信息接口
     *
     * 参数:name,price,unit,minBatch,status,bornArea,storage,offlineTime,contactor,contactorMobile,goalArea,preOnlineTime,
     * transactionArea,sendTime,transactionTime,carNo,images,description,formatDesc,onlineStatus,userId,grade,extFields
     */
    @RequestMapping("/get")
    @ResponseBody
    public Json get(FmGoods fmGoods) {
        Json j = new Json();
        try {
            if(!F.empty(fmGoods.getId())) {
                FmGoods fmGoods1 = fmGoodsService.get(fmGoods.getId());

                final CompletionService completionService = CompletionFactory.initCompletion();

                completionService.submit(new Task<FmGoods, FmUser>(fmGoods1) {
                    @Override
                    public FmUser call() throws Exception {
                        return fmUserServiceI.get(getD().getUserId());
                    }

                    protected void set(FmGoods d, FmUser fmUser) {
                        d.setFmUser(fmUser);
                    }
                });

                completionService.submit(new Task<FmGoods, Integer>(fmGoods1) {
                    @Override
                    public Integer call() throws Exception {
                        fmGoodsService.updateAccess(getD().getId());
                        return getD().getAccessNum() == null ? 1 : getD().getAccessNum() + 1;
                    }

                    protected void set(FmGoods d, Integer num) {
                        d.setAccessNum(num);
                    }
                });

                completionService.sync();

                j.setObj(fmGoods1);
                j.setSuccess(true);
                j.setMsg(SUCCESS_MESSAGE);
            }
        } catch (Exception e) {
            j.setMsg(Application.getString(EX_0001));
            logger.error("获取商品信息异常", e);
        }

        return j;
    }

    /**
     * 首页商品搜索接口
     *
     * 可选参数:userId,name,grade,bornArea,page,rows,sort,order
     */
    @RequestMapping("/dataGrid")
    @ResponseBody
    public Json dataGrid(FmGoods fmGoods, PageHelper ph) {
        Json j = new Json();
        try {
            Map<String, DataGrid> resultMap = new HashMap<String, DataGrid>();
            if(F.empty(fmGoods.getStatus())){
                //现货
                fmGoods.setStatus("GS20");
                DataGrid dg_spotGoods = fmGoodsService.dataGrid(fmGoods, ph);
                resultMap.put("spotGoods", dg_spotGoods);
                //在途
                fmGoods.setStatus("GS30");
                DataGrid dg_onTheWayGoods = fmGoodsService.dataGrid(fmGoods, ph);
                resultMap.put("onTheWayGoods", dg_onTheWayGoods);
                //预售
                fmGoods.setStatus("GS10");
                DataGrid dg_presellGoods = fmGoodsService.dataGrid(fmGoods, ph);
                resultMap.put("presellGoods", dg_presellGoods);
            }else{

                DataGrid dataGrid = fmGoodsService.dataGrid(fmGoods, ph);
                if("GS20".equals(fmGoods.getStatus())){
                    resultMap.put("spotGoods", dataGrid);
                }else if("GS30".equals(fmGoods.getStatus())){
                    resultMap.put("onTheWayGoods", dataGrid);
                }else if("GS10".equals(fmGoods.getStatus())){
                    resultMap.put("presellGoods", dataGrid);
                }
            }


            j.setSuccess(true);
            j.setMsg(SUCCESS_MESSAGE);
            j.setObj(resultMap);
        } catch (Exception e) {
            j.setMsg(Application.getString(EX_0001));
            logger.error("商品搜索异常", e);
        }

        return j;
    }

}
