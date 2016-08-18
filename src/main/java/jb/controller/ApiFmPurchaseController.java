package jb.controller;

import farming.concurrent.CompletionService;
import farming.concurrent.Task;
import jb.absx.F;
import jb.listener.Application;
import jb.pageModel.*;
import jb.service.FmPurchaseServiceI;
import jb.service.FmPurchaseUserServiceI;
import jb.service.FmUserServiceI;
import jb.service.impl.CompletionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Vector;

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
     * 必要参数:userId
     * 可选参数:onlineStatus,isdeleted,page,rows,sort,order
     */
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
                    Vector vector = new Vector();
                    final CompletionService completionService = CompletionFactory.initCompletion();
                    for (int i=0; i<list.size(); i++) {
                        final FmPurchase fp = list.get(i);
                        //根据采购id查询询价用户id
                        completionService.submit(new Task<Vector, FmPurchase>(vector){
                            @Override
                            public FmPurchase call() throws Exception {
                                FmPurchaseUser fmPurchaseUser = new FmPurchaseUser();
                                fmPurchaseUser.setIsdeleted(false);
                                fmPurchaseUser.setPurchaseId(fp.getId());
                                DataGrid dd = fmPurchaseUserServiceI.dataGrid(fmPurchaseUser, null);
                                List<FmPurchaseUser> fmPurchaseUserList = dd.getRows();
                                if(fmPurchaseUserList != null && fmPurchaseUserList.size() > 0) {
                                    DataGrid du = new DataGrid();
                                    Vector vt = new Vector();
                                    final CompletionService cls = CompletionFactory.initCompletion();
                                    for (int m=0; m<fmPurchaseUserList.size(); m++) {
                                        final String userId = fmPurchaseUserList.get(m).getUserId();
                                        //根据用户id查询用户信息
                                        cls.submit(new Task<Vector, FmUser>(vt){
                                            @Override
                                            public FmUser call() throws Exception {
                                                FmUser fmUser = fmUserServiceI.get(userId);
                                                return fmUser;
                                            }
                                            protected void set(Vector d, FmUser v) {
                                                d.add(v);
                                            }
                                        });
                                    }
                                    cls.sync();
                                    du.setRows(vt);
                                    du.setTotal(new Long(vt.size()));
                                    fp.setFmPurchaseUserDataGrid(du);
                                }
                                return fp;
                            }
                            protected void set(Vector d, FmPurchase v) {
                                d.add(v);
                            }
                        });
                    }
                    completionService.sync();
                    dataGrid.setRows(vector);
                    dataGrid.setTotal(new Long(vector.size()));
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
