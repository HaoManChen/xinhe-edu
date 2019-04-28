package cn.xinhe.service;

import cn.xinhe.domain.WxAppSession;
import cn.xinhe.dto.WxAppUserDTO;
import cn.xinhe.dto.wxa.WxAppSessionDTO;

/**
 * @author haoman
 * @Date 2019/2/5 下午12:55
 */
public interface WxAppSessionService {
    WxAppSession saveSession(WxAppSessionDTO rawSession);
    WxAppUserDTO getMemberIdBySessionId(String sessionId);
}
