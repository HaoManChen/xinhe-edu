package cn.xinhe.mapper;

import cn.xinhe.domain.Student;
import cn.xinhe.domain.StudentPay;
import cn.xinhe.dto.StudentDTO;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface StudentMapper {
    int deleteByPrimaryKey(String id);

    int insert(Student record);

    Student selectByPrimaryKey(String id);

    List<StudentDTO> selectAll();

    int updateByPrimaryKey(Student record);

    List<Student> getByMemberId(String memberId);

}