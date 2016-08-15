package jb.controller;

import jb.absx.F;
import jb.listener.Application;
import jb.pageModel.*;
import jb.service.FmPurchaseServiceI;
import jb.service.FmPurchaseUserServiceI;
import jb.service.FmUserServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
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

    @RequestMapping("/dataGrid")
    @ResponseBody
    public Json dataGrid(FmPurchase fmPurchase, PageHelper ph) {
        Json j = new Json();
        try {
            if(!F.empty(fmPurchase.getUserId())) {
                //根据用户id和采购状态查询采购记录
                DataGrid dg = fmPurchaseService.dataGrid(fmPurchase, ph);
                List<FmPurchase> list = dg.getRows();
                DataGrid dataGrid = new DataGrid();
                if(list != null && list.size() > 0) {
                    List<FmPurchase> lt = new ArrayList<>();
                    for (int i=0; i<list.size(); i++) {
                        FmPurchase fp = list.get(i);
                        FmPurchaseUser fmPurchaseUser = new FmPurchaseUser();
                        fmPurchaseUser.setIsdeleted(false);
                        fmPurchaseUser.setPurchaseId(fp.getId());
                        //根据采购id查询询价用户id
                        DataGrid dd = fmPurchaseUserServiceI.dataGrid(fmPurchaseUser, null);
                        List<FmPurchaseUser> fmPurchaseUserList = dd.getRows();
                        if(fmPurchaseUserList != null && fmPurchaseUserList.size() > 0) {
                            DataGrid du = new DataGrid();
                            List<FmUser> fuList = new ArrayList<>();
                            for (int m=0; m<fmPurchaseUserList.size(); m++) {
                                //根据用户id查询用户信息
                                FmUser fmUser = fmUserServiceI.get(fmPurchaseUserList.get(m).getUserId());
                                fuList.add(fmUser);
                            }
                            du.setRows(fuList);
                            du.setTotal(new Long(fuList.size()));
                            fp.setFmPurchaseUserDataGrid(du);
                        }
                        lt.add(fp);
                    }
                    dataGrid.setRows(lt);
                    dataGrid.setTotal(new Long(lt.size()));
                }
                j.setSuccess(true);
                j.setMsg(SUCCESS_MESSAGE);
                j.setObj(dataGrid);
            }
        } catch (Exception e) {
            j.setMsg(Application.getString(EX_0001));
            logger.error("查询采购记录异常", e);
        }

        return j;
    }

}
