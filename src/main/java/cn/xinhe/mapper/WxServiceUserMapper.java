package cn.xinhe.mapper;

import cn.xinhe.domain.WxServiceUser;
import java.util.List;

public interface WxServiceUserMapper {
    int deleteByPrimaryKey(String id);

    int insert(WxServiceUser record);

    WxServiceUser selectByPrimaryKey(String id);

    List<WxServiceUser> selectAll();

    int updateByPrimaryKey(WxServiceUser record);
}