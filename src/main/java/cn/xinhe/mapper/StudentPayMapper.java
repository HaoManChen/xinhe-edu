package cn.xinhe.mapper;

import cn.xinhe.domain.StudentPay;
import cn.xinhe.dto.StatisticsInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface StudentPayMapper {
    int deleteByPrimaryKey(String id);

    int insert(StudentPay record);

    StudentPay selectByPrimaryKey(String id);

    List<StudentPay> selectAll();

    int updateByPrimaryKey(StudentPay record);

    List<StatisticsInfo> getStatisticsByStudentId(String studentId);

    List<StudentPay> listOfStudentId(String studentId);

    StudentPay getByStudentIdAndTeacherCurriculumId(String teacherCurriculumId, String studentId);

    StudentPay getByStudentIdAndLessonId(@Param("lessonId") String lessonId,@Param("studentId") String studentId);
}