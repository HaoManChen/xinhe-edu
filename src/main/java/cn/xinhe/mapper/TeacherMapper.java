package cn.xinhe.mapper;

import cn.xinhe.domain.Teacher;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface TeacherMapper {
    int deleteByPrimaryKey(String id);

    int insert(Teacher record);

    Teacher selectByPrimaryKey(String id);

    List<Teacher> selectAll();

    int updateByPrimaryKey(Teacher record);
}