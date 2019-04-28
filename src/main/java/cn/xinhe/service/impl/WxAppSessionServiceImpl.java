package cn.xinhe.service.impl;

import cn.xinhe.domain.WxAppSession;
import cn.xinhe.dto.WxAppUserDTO;
import cn.xinhe.dto.wxa.WxAppSessionDTO;
import cn.xinhe.mapper.WxAppSessionMapper;
import cn.xinhe.service.WxAppSessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * @author haoman
 * @Date 2019/2/5 下午1:26
 */
@Service
public class WxAppSessionServiceImpl implements WxAppSessionService {
    @Autowired
    WxAppSessionMapper wxAppSessionMapper;

    @Override
    public WxAppSession saveSession(WxAppSessionDTO rawSession) {
        WxAppSession session = new WxAppSession();
        session.setWxAppSessionId(UUID.randomUUID().toString().replaceAll("-",""));
        session.setAppid(rawSession.getAppid());
        session.setOpenid(rawSession.getOpenid());
        session.setSessionKey(rawSession.getSessionKey());
        int existsCount = wxAppSessionMapper.countOfAppidAndOpenid(session.getAppid(),session.getOpenid());
        if (existsCount>0){
            wxAppSessionMapper.updateByAppidAndOpenid(session);
        }
        else {
            wxAppSessionMapper.insert(session);
        }
        return session;
    }

    @Override
    public WxAppUserDTO getMemberIdBySessionId(String sessionId) {
        return wxAppSessionMapper.selectMemberIdBySessionId(sessionId);
    }

}
