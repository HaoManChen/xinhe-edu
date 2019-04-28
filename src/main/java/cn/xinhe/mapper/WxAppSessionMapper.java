package cn.xinhe.mapper;

import cn.xinhe.domain.WxAppSession;
import cn.xinhe.dto.MemberDTO;
import cn.xinhe.dto.WxAppUserDTO;
import cn.xinhe.dto.wxa.WxAppSessionDTO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface WxAppSessionMapper {
    int deleteByPrimaryKey(String wxAppSessionId);

    int insert(WxAppSession record);

    WxAppSession selectByPrimaryKey(String wxAppSessionId);

    List<WxAppSession> selectAll();

    int updateByPrimaryKey(WxAppSession record);

    int countOfAppidAndOpenid(@Param("appid") String appid,
                              @Param("openid") String openid);
    int updateByAppidAndOpenid(WxAppSession session);

    MemberDTO getMemberIdAndOpenidAndAppidBySessionId(String sessionId);

    WxAppUserDTO selectMemberIdBySessionId(String sessionId);
}