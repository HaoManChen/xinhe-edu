package cn.xinhe.service;


import cn.xinhe.dto.wxa.WxAppSessionDTO;

/**微信小程序服务api
 * @author haoman
 * @Date 2018/12/17 下午5:07
 */
public interface WxAppApiService {

    /**
     * 获取session
     * @param code
     * @return
     */
    WxAppSessionDTO getSession(String code);
}
