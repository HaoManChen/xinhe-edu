package cn.xinhe.service.impl;



import cn.xinhe.domain.WxAppUser;
import cn.xinhe.domain.WxUser;
import cn.xinhe.dto.WxAppUserInfo;
import cn.xinhe.mapper.WxAppUserMapper;
import cn.xinhe.mapper.WxUserMapper;
import cn.xinhe.service.WxUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.UUID;

/**
 * @author haoman
 * @Date 2018/12/13 上午11:08
 */
@Service
public class WxUserServiceImpl implements WxUserService {
    @Autowired
    WxUserMapper wxUserMapper;
    @Autowired
    WxAppUserMapper wxAppUserMapper;
    @Override
    public WxAppUser updateWxAppUser(String openid, String appid, String unionid) {
        WxAppUser wxAppUser = new WxAppUser();
        wxAppUser.setOpenid(openid);
        wxAppUser.setAppid(appid);
        wxAppUser.setId(UUID.randomUUID().toString().replaceAll("-",""));
        WxUser wxUser = new WxUser();
        String oldWxUserId = wxAppUserMapper.selectWxUserIdByOpenidAndAppid(openid,appid);
        if (!StringUtils.isEmpty(unionid)){
            String existWxUserId = wxUserMapper.selectWxUserIdByUnionid(unionid);
            if (StringUtils.isEmpty(existWxUserId)){
                if (StringUtils.isEmpty(oldWxUserId)){
                    wxAppUser.setWxUserId(UUID.randomUUID().toString().replaceAll("-",""));
                    wxAppUserMapper.insert(wxAppUser);
                    wxUser.setId(wxAppUser.getWxUserId());
                    wxUser.setUnionid(unionid);
                    wxUserMapper.insert(wxUser);
                }else {
                    wxAppUser.setWxUserId(oldWxUserId);
                    wxUser.setId(wxAppUser.getWxUserId());
                    wxUser.setUnionid(unionid);
                    int existCount = wxUserMapper.countOfWxUserId(oldWxUserId);
                    if (existCount>0){
                        wxUserMapper.updateByWxUserId(wxUser);
                    }else {
                        wxUserMapper.insert(wxUser);
                    }
                }
            }else {
                if (StringUtils.isEmpty(oldWxUserId)){
                    wxAppUser.setWxUserId(existWxUserId);
                    wxAppUserMapper.insert(wxAppUser);
                }else {
                    if (!oldWxUserId.equals(existWxUserId)){
                        wxAppUser.setWxUserId(existWxUserId);
                        wxAppUserMapper.updateWxUserIdByOpenidAndAppid(wxAppUser.getWxUserId(),openid,appid);
                    }else {
                        wxAppUser.setWxUserId(existWxUserId);
                    }
                }
            }
        }else {
            if (StringUtils.isEmpty(oldWxUserId)){
                wxAppUser.setWxUserId(UUID.randomUUID().toString().replaceAll("-",""));
                wxAppUserMapper.insert(wxAppUser);
                wxUser.setId(wxAppUser.getWxUserId());
                wxUserMapper.insert(wxUser);
            }else {
                wxAppUser.setWxUserId(oldWxUserId);
            }
        }
        return wxAppUser;
    }
    @Override
    @Transactional (rollbackFor = RuntimeException.class)
    public boolean updateByUserId(String wxUserId, String oldWxUserId) {
        int updateByUserId = wxAppUserMapper.updateByUserId(wxUserId,oldWxUserId);
        int deleteByPrimaryKey = wxUserMapper.deleteByPrimaryKey(oldWxUserId);
        return updateByUserId == deleteByPrimaryKey && updateByUserId == 1;
    }

    @Override
    @Transactional
    public void update(WxAppUserInfo userInfo) {
        wxUserMapper.update(userInfo);
    }

}
