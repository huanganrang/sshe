package jb.controller;

import com.alibaba.fastjson.JSON;
import jb.absx.F;
import jb.interceptors.TokenManage;
import jb.listener.Application;
import jb.pageModel.*;
import jb.service.FmGoodsUserServiceI;
import jb.service.FmShopUserServiceI;
import jb.service.FmUserHobbyServiceI;
import jb.service.FmUserServiceI;
import jb.util.HttpUtil;
import jb.util.easemob.HuanxinUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.net.URLDecoder;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lenovo on 2016/8/15.
 *
 * 用户登陆、注册、个人信息等接口
 */
@Controller
@RequestMapping("/api/apiFmUserController")
public class ApiFmUserController extends BaseController {

    @Autowired
    private FmUserServiceI fmUserService;

    @Autowired
    private FmGoodsUserServiceI fmGoodsUserService;

    @Autowired
    private FmShopUserServiceI fmShopUserService;

    @Autowired
    private FmUserHobbyServiceI fmUserHobbyService;

    @Autowired
    private TokenManage tokenManage;

    /**
     * 用户登陆
     */
    @RequestMapping("/userLogin")
    @ResponseBody
    public Json userLogin(String dtoMobile, String dtoPassword) {
        Json j = new Json();
        try {
            //String url = "http://yizhuisu.com/api/Ba/userLogin";
            String url = "http://t149127q79.51mypc.cn:11169/api/Ba/userLogin";
            Map<String, Object> httpMap = HttpUtil.httpRequestWithHeader(url, "post", "{\"Dto_Mobile\":\""+dtoMobile+"\",\"Dto_Password\":\""+dtoPassword+"\"}");
            Map<String, List<String>> hederMap = (Map<String, List<String>>)httpMap.get("header");
            String retShowMsg = hederMap.get("retShowMsg") == null?null:URLDecoder.decode((hederMap.get("retShowMsg").get(0)), "UTF-8");
            String result = httpMap.get("body") == null?null:httpMap.get("body").toString();
            if(!F.empty(result) && result.contains("In_UserID") && result.contains("In_UserName")) {
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("centerUser", JSON.parseObject(result));
                FmUser fmUser = new FmUser();
                fmUser.setAccount(dtoMobile);
                FmUser fu = fmUserService.get(fmUser);
                if(fu != null && !F.empty(fu.getAccount())) {
                    map.put("farmingUser", fu);
                } else {
                    if(!F.empty(HuanxinUtil.createUser(dtoMobile, dtoPassword))) {
                        fmUser.setHxPassword(dtoPassword);
                        fmUser.setHxStatus(1);
                    } else {
                        fmUser.setHxStatus(2);
                    }
                    fmUser.setAddtime(new Date());
                    fmUser.setUpdatetime(new Date());
                    fmUserService.add(fmUser);
                    map.put("farmingUser", fmUser);
                }
                map.put("tokenId", tokenManage.buildToken(fmUser.getId(), fmUser.getAccount()));
                j.setSuccess(true);
                j.setMsg(SUCCESS_MESSAGE);
                j.setObj(map);
                return j;
            }
            j.setMsg(retShowMsg);
        } catch (Exception e) {
            j.setMsg(Application.getString(EX_0001));
            logger.error("登陆异常", e);
        }

        return j;
    }

    /**
     * 发送短信验证码，dtoVerifyType为1时注册，2时找回密码
     */
    @RequestMapping("/sendVerifyCode")
    @ResponseBody
    public Json sendVerifyCode(String dtoMobile, String dtoVerifyType) {
        Json j = new Json();
        try {
            //String url = "http://yizhuisu.com/api/Ba/sendVerifyCode";
            String url = "http://t149127q79.51mypc.cn:11169/api/Ba/sendVerifyCode";
            Map<String, Object> httpMap = HttpUtil.httpRequestWithHeader(url, "post", "{\"Dto_UserId\":0,\"Dto_Mobile\":\""+dtoMobile+"\",\"Dto_VerifyType\":"+dtoVerifyType+"}");
            Map<String, List<String>> hederMap = (Map<String, List<String>>)httpMap.get("header");
            String retShowMsg = hederMap.get("retShowMsg") == null?null:URLDecoder.decode((hederMap.get("retShowMsg").get(0)), "UTF-8");
            String result = httpMap.get("body") == null?null:httpMap.get("body").toString();
            j.setSuccess(true);
            j.setMsg(retShowMsg);
            j.setObj(result);
        } catch (Exception e) {
            j.setMsg(Application.getString(EX_0001));
            logger.error("获取验证码异常", e);
        }

        return j;
    }

    /**
     * 用户注册、找回密码，dtoOperateType为1时注册，为2时找回密码
     */
    @RequestMapping("/userRegister")
    @ResponseBody
    public Json userRegister(String dtoMobile, String dtoPassword, String dtoVerificationCode, String dtoOperateType) {
        Json j = new Json();
        try {
            //String url = "http://yizhuisu.com/api/Ba/userRegister";
            String url = "http://t149127q79.51mypc.cn:11169/api/Ba/userRegister";
            Map<String, Object> httpMap = HttpUtil.httpRequestWithHeader(url, "post", "{\"Dto_Mobile\":\""+dtoMobile+"\",\"Dto_Password\":\""+dtoPassword+"\",\"Dto_VerificationCode\":\""+dtoVerificationCode+"\",\"Dto_OperateType\":"+dtoOperateType+"}");
            Map<String, List<String>> hederMap = (Map<String, List<String>>)httpMap.get("header");
            String retShowMsg = hederMap.get("retShowMsg") == null?null:URLDecoder.decode((hederMap.get("retShowMsg").get(0)), "UTF-8");
            String result = httpMap.get("body") == null?null:httpMap.get("body").toString();
            if("1".equals(dtoOperateType)) {
                if(!F.empty(result) && result.contains("true")) {
                    FmUser fmUser = new FmUser();
                    fmUser.setAccount(dtoMobile);
                    fmUser.setAddtime(new Date());
                    fmUser.setUpdatetime(new Date());
                    if(!F.empty(HuanxinUtil.createUser(dtoMobile, dtoPassword))) {
                        fmUser.setHxPassword(dtoPassword);
                        fmUser.setHxStatus(1);
                    } else {
                        fmUser.setHxStatus(2);
                    }
                    fmUserService.add(fmUser);
                    j.setObj(fmUser);
                    j.setSuccess(true);
                }
            } else {
                j.setObj(result);
                j.setSuccess(true);
            }
            j.setMsg(retShowMsg);
        } catch (Exception e) {
            j.setMsg(Application.getString(EX_0001));
            String errorMg = "";
            if("1".equals(dtoOperateType)) {
                errorMg = "用户注册异常";
            } else if("2".equals(dtoOperateType)) {
                errorMg = "找回密码异常";
            }
            logger.error(errorMg, e);
        }

        return j;
    }

    /**
     * 修改密码接口
     */
    @RequestMapping("/userResetPassword")
    @ResponseBody
    public Json userResetPassword(String dtoID, String dtoMobile, String dtoOldPassword, String dtoPassword) {
        Json j = new Json();
        try {
            //String url = "http://yizhuisu.com/api/BA/userResetPassword";
            String url = "http://t149127q79.51mypc.cn:11169/api/BA/userResetPassword";
            Map<String, Object> httpMap = HttpUtil.httpRequestWithHeader(url, "post", "{\"Dto_ID\":"+dtoID+",\"Dto_Mobile\":\""+dtoMobile+"\",\"Dto_OldPassword\":\""+dtoOldPassword+"\",\"Dto_Password\":\""+dtoPassword+"\"}");
            Map<String, List<String>> hederMap = (Map<String, List<String>>)httpMap.get("header");
            String retShowMsg = hederMap.get("retShowMsg") == null?null:URLDecoder.decode((hederMap.get("retShowMsg").get(0)), "UTF-8");
            String result = httpMap.get("body") == null?null:httpMap.get("body").toString();
            j.setSuccess(true);
            j.setMsg(retShowMsg);
            j.setObj(result);
        } catch (Exception e) {
            j.setMsg(Application.getString(EX_0001));
            logger.error("修改密码异常", e);
        }

        return j;
    }

    /**
     * 获取个人信息接口
     */
    @RequestMapping("/get")
    @ResponseBody
    public Json get(FmUser fmUser) {
        Json j = new Json();
        try {
            if(!F.empty(fmUser.getId())) {
                FmUser fu = fmUserService.get(fmUser.getId());
                j.setSuccess(true);
                j.setMsg(SUCCESS_MESSAGE);
                j.setObj(fu);
            }
        } catch (Exception e) {
            j.setMsg(Application.getString(EX_0001));
            logger.error("获取个人资料异常", e);
        }

        return j;
    }

    /**
     * 修改个人信息接口
     */
    @RequestMapping("/edit")
    @ResponseBody
    public Json edit(FmUser fmUser) {
        Json j = new Json();
        try {
            if(!F.empty(fmUser.getId())) {
                fmUserService.edit(fmUser);
                j.setSuccess(true);
                j.setMsg(SUCCESS_MESSAGE);
            }
        } catch (Exception e) {
            j.setMsg(Application.getString(EX_0001));
            logger.error("修改个人资料异常", e);
        }

        return j;
    }

    /**
     * 关联
     *
     * @return
     */
    @RequestMapping("/relation")
    @ResponseBody
    public Json relation(String userId, String bizId, String bizType) {
        Json j = new Json();
        try {
            if (!F.empty(userId) && !F.empty(bizId) && !F.empty(bizType)) {

                if("FmGoodsUserServiceI".equals(bizType)){
                    FmGoodsUser fmGoodsUser = new FmGoodsUser();
                    fmGoodsUser.setUserId(userId);
                    fmGoodsUser.setGoodsId(bizId);
                    FmGoodsUser old =  fmGoodsUserService.get(fmGoodsUser);
                    if(old == null){
                        fmGoodsUserService.add(fmGoodsUser);
                    }else{
                        j.fail();
                        j.setMsg("已关联上");
                        return j;
                    }
                }else if("FmShopUserServiceI".equals(bizType)){
                    FmShopUser fmShopUser = new FmShopUser();
                    fmShopUser.setShopId(bizId);
                    fmShopUser.setUserId(userId);
                    FmShopUser old = fmShopUserService.get(fmShopUser);
                    if(old == null){
                        fmShopUserService.add(fmShopUser);
                    }else{
                        j.fail();
                        j.setMsg("已关联上");
                        return j;
                    }
                }

                j.setSuccess(true);
                j.setMsg(SUCCESS_MESSAGE);
            }
        } catch (Exception e) {
            j.setMsg(Application.getString(EX_0001));
            logger.error("关联异常", e);
        }

        return j;
    }

    /**
     * 取消关联
     *
     * @return
     */
    @RequestMapping("/disRelation")
    @ResponseBody
    public Json disRelation(String userId, String bizId, String bizType) {
        Json j = new Json();
        try {
            if (!F.empty(userId) && !F.empty(bizId) && !F.empty(bizType)) {
                if("FmGoodsUserServiceI".equals(bizType)){
                    FmGoodsUser fmGoodsUser = new FmGoodsUser();
                    fmGoodsUser.setUserId(userId);
                    fmGoodsUser.setGoodsId(bizId);
                    fmGoodsUserService.delete(fmGoodsUser);
                }else if("FmShopUserServiceI".equals(bizType)){
                    FmShopUser fmShopUser = new FmShopUser();
                    fmShopUser.setShopId(bizId);
                    fmShopUser.setUserId(userId);
                    fmShopUserService.delete(fmShopUser);
                }
                j.setSuccess(true);
                j.setMsg(SUCCESS_MESSAGE);
            }
        } catch (Exception e) {
            j.setMsg(Application.getString(EX_0001));
            logger.error("取消关联异常", e);
        }

        return j;
    }


    @RequestMapping("/saveOrUpdate")
    @ResponseBody
    public Json saveOrUpdateHobby(String userId,String goodsIds) {
        Json j = new Json();
        try {
            if (!F.empty(userId) && !F.empty(goodsIds)) {
                FmUserHobby fmUserHobby = new FmUserHobby();
                fmUserHobby.setUserId(userId);
                fmUserHobbyService.delete(fmUserHobby);
                String[] goodsIdList = goodsIds.split("[;|]");
                for (String s : goodsIdList) {
                    fmUserHobby = new FmUserHobby();
                    fmUserHobby.setGoodName(s);
                    fmUserHobby.setUserId(userId);
                    fmUserHobbyService.add(fmUserHobby);
                }
                j.setSuccess(true);
                j.setMsg(SUCCESS_MESSAGE);
            }
        } catch (Exception e) {
            j.setMsg(Application.getString(EX_0001));
            logger.error("取消关联异常", e);
        }

        return j;
    }


    @RequestMapping("/getHobbyList")
    @ResponseBody
    public Json getHobbyList(String userId) {
        Json j = new Json();
        try {
            if (!F.empty(userId)) {
                j.setObj(fmUserHobbyService.query(userId));
                j.setSuccess(true);
                j.setMsg(SUCCESS_MESSAGE);
            }
        } catch (Exception e) {
            j.setMsg(Application.getString(EX_0001));
            logger.error("取消关联异常", e);
        }

        return j;
    }

}
