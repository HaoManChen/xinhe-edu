package cn.xinhe.mapper;

import cn.xinhe.domain.TeacherCurriculum;
import java.util.List;

public interface TeacherCurriculumMapper {
    int deleteByPrimaryKey(String id);

    int insert(TeacherCurriculum record);

    TeacherCurriculum selectByPrimaryKey(String id);

    List<TeacherCurriculum> selectAll();

    int updateByPrimaryKey(TeacherCurriculum record);
}