package jb.controller;

import jb.absx.F;
import jb.listener.Application;
import jb.pageModel.FmFeedback;
import jb.pageModel.FmUser;
import jb.pageModel.Json;
import jb.service.FmFeedbackServiceI;
import jb.service.FmUserServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by lenovo on 2016/8/26.
 *
 * 意见反馈
 */
@Controller
@RequestMapping("/api/apiFmFeedbackController")
public class ApiFmFeedbackController extends BaseController {

    @Autowired
    private FmFeedbackServiceI fmFeedbackService;

    @Autowired
    private FmUserServiceI fmUserService;

    /**
     * 意见反馈
     *
     *参数:content,userId
     *
     */
    @RequestMapping("/add")
    @ResponseBody
    public Json add(FmFeedback fmFeedback) {
        Json j = new Json();
        try {
            if (!F.empty(fmFeedback.getUserId())) {
                FmUser fmUser = fmUserService.get(fmFeedback.getUserId());
                if (fmUser != null) {
                    fmFeedback.setUserAccount(fmUser.getAccount());
                    fmFeedback.setMobile(fmUser.getPhone());
                }
            }
            fmFeedbackService.add(fmFeedback);
            j.setSuccess(true);
            j.setMsg(SUCCESS_MESSAGE);
        } catch (Exception e) {
            j.setMsg(Application.getString(EX_0001));
            logger.error("反馈意见异常", e);
        }

        return j;
    }

}
