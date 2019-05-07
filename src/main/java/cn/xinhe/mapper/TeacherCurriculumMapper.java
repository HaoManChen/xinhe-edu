package cn.xinhe.mapper;

import cn.xinhe.domain.TeacherCurriculum;
import cn.xinhe.dto.TeacherAllLessonDTO;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface TeacherCurriculumMapper {
    int deleteByPrimaryKey(String id);

    int insert(TeacherCurriculum record);

    TeacherCurriculum selectByPrimaryKey(String id);

    List<TeacherCurriculum> selectAll();

    int updateByPrimaryKey(TeacherCurriculum record);

    List<TeacherAllLessonDTO> getAllToTeacherAllLessonDTO();

    List<TeacherAllLessonDTO> getSimpleTeacherLessonByTeacherId(String teacherId);

    List<TeacherAllLessonDTO> getSimpleCurriculumLessonByCurriculumId(String curriculumId);

    void insertAll(List<TeacherCurriculum> teacherCurriculumList);
}