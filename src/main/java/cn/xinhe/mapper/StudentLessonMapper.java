package cn.xinhe.mapper;

import cn.xinhe.domain.StudentLesson;
import cn.xinhe.dto.StudentLessonTotalInfo;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface StudentLessonMapper {
    int deleteByPrimaryKey(String id);

    int insert(StudentLesson record);

    StudentLesson selectByPrimaryKey(String id);

    List<StudentLesson> selectAll();

    int updateByPrimaryKey(StudentLesson record);

    List<StudentLessonTotalInfo> listByStudentIdAndState(@Param("studentId")String studentId,@Param("state")Byte state);

    List<StudentLessonTotalInfo> listOfWeekClass(@Param("nowDateShot") String nowDateShot, @Param("afterWeekDateShot") String afterWeekDateShot, @Param("studentId") String studentId);
}