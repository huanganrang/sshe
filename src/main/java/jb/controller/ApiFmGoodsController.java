package jb.controller;

import jb.absx.F;
import jb.listener.Application;
import jb.pageModel.DataGrid;
import jb.pageModel.FmGoods;
import jb.pageModel.Json;
import jb.pageModel.PageHelper;
import jb.service.FmGoodsServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
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
