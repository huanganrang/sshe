package jb.controller;

import jb.listener.Application;
import jb.pageModel.FmAuthApply;
import jb.pageModel.Json;
import jb.service.FmAuthApplyServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by lenovo on 2016/8/15.
 *
 * 实名认证接口
 */
@Controller
@RequestMapping("/api/apiFmAuthApplyController")
public class ApiFmAuthApplyController extends BaseController {

    @Autowired
    private FmAuthApplyServiceI fmAuthApplyService;

    @RequestMapping("/add")
    @ResponseBody
    public Json add(FmAuthApply fmAuthApply) {
        Json j = new Json();
        try {
            fmAuthApplyService.add(fmAuthApply);
            j.setSuccess(true);
            j.setMsg(SUCCESS_MESSAGE);
        } catch (Exception e) {
            j.setMsg(Application.getString(EX_0001));
            logger.error("申请实名认证异常", e);
        }

        return j;
    }

}
