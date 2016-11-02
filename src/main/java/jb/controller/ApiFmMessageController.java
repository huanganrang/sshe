package jb.controller;

import jb.absx.F;
import jb.listener.Application;
import jb.pageModel.*;
import jb.service.FmMessageServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by lenovo on 2016/8/26.
 * <p/>
 * 发布商品类接口
 */
@Controller
@RequestMapping("/api/apiFmMessageController")
public class ApiFmMessageController extends BaseController {

    @Autowired
    private FmMessageServiceI fmMessageService;


    /**
     * @param fmMessage
     * @return
     */
    @RequestMapping("/get")
    @ResponseBody
    public Json get(FmMessage fmMessage) {
        Json j = new Json();
        try {
            if (!F.empty(fmMessage.getId())) {


                j.setObj(fmMessageService.get(fmMessage.getId()));
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
     * 获取消息list
     * <p/>
     * 可选参数:userId,name,grade,bornArea,page,rows,sort,order
     */
    @RequestMapping("/dataGrid")
    @ResponseBody
    public Json dataGrid(FmMessage fmMessage,String tokenId, PageHelper ph) {
        Json j = new Json();
        try {
            SessionInfo sessionInfo = getSession(tokenId);
            if (sessionInfo != null) {
                fmMessage.setToUser(sessionInfo.getId());
            } else {
                fmMessage.setToUser("-1");
            }
            fmMessage.setExtCfg("forClient");
            DataGrid dataGrid = fmMessageService.dataGrid(fmMessage, ph);
            j.setSuccess(true);
            j.setMsg(SUCCESS_MESSAGE);
            j.setObj(dataGrid);
        } catch (Exception e) {
            j.setMsg(Application.getString(EX_0001));
            logger.error("消息搜索异常", e);
        }

        return j;
    }

}
