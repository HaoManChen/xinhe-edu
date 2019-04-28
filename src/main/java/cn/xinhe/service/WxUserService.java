package cn.xinhe.service;


import cn.xinhe.domain.WxAppUser;
import cn.xinhe.dto.WxAppUserInfo;

public interface WxUserService {
    WxAppUser updateWxAppUser(String openid, String appid, String unionid);
    boolean updateByUserId(String wxUserId, String oldWxUserId);

    /**
     * Description:
     * @param userInfo
     */
    void update(WxAppUserInfo userInfo);



}
