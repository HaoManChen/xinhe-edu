package cn.xinhe.mapper;

import cn.xinhe.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface MemberMapper {
    int deleteByPrimaryKey(String id);

    int insert(Member record);

    Member selectByPrimaryKey(String id);

    List<Member> selectAll();

    int updateByPrimaryKey(Member record);

    Member getByPhone(String mobile);
}