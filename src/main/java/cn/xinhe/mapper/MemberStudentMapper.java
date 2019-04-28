package cn.xinhe.mapper;

import cn.xinhe.domain.MemberStudent;
import java.util.List;

public interface MemberStudentMapper {
    int deleteByPrimaryKey(String id);

    int insert(MemberStudent record);

    MemberStudent selectByPrimaryKey(String id);

    List<MemberStudent> selectAll();

    int updateByPrimaryKey(MemberStudent record);
}