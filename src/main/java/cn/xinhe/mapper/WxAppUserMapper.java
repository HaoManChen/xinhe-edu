package cn.xinhe.mapper;

import cn.xinhe.domain.WxAppUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface WxAppUserMapper {
    int deleteByPrimaryKey(String id);

    int insert(WxAppUser record);

    WxAppUser selectByPrimaryKey(String id);

    List<WxAppUser> selectAll();

    int updateByPrimaryKey(WxAppUser record);

    String selectWxUserIdByOpenidAndAppid(@Param("openid") String openid, @Param("appid") String appid);

    int updateWxUserIdByOpenidAndAppid(@Param("wxUserId") String wxUserId, @Param("openid") String openid, @Param("appid") String appid);

    int updateByUserId(@Param("wxUserId") String wxUserId, @Param("oldWxUserId") String oldWxUserId);

}