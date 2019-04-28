package cn.xinhe.mapper;

import cn.xinhe.domain.WxUser;
import cn.xinhe.dto.WxAppUserInfo;

import java.util.List;

public interface WxUserMapper {
    int deleteByPrimaryKey(String id);

    int insert(WxUser record);

    WxUser selectByPrimaryKey(String id);

    List<WxUser> selectAll();

    int updateByPrimaryKey(WxUser record);

    String selectWxUserIdByUnionid(String unionid);

    Integer countOfWxUserId(String wxUserId);

    void updateByWxUserId(WxUser wxUser);

    void update(WxAppUserInfo userInfo);
}