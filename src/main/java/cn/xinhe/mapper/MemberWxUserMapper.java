package cn.xinhe.mapper;

import cn.xinhe.domain.MemberWxUser;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface MemberWxUserMapper {
    int deleteByPrimaryKey(String id);

    int insert(MemberWxUser record);

    MemberWxUser selectByPrimaryKey(String id);

    List<MemberWxUser> selectAll();

    int updateByPrimaryKey(MemberWxUser record);

    int countOfWxUserId(String wxUserId);
}