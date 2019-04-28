package cn.xinhe.dto;

import cn.xinhe.domain.Student;
import lombok.Data;

import java.util.List;

/**
 * @author haoman
 * @Date 2019-03-22 11:29
 */
@Data
public class MyInfoDTO {
    private String phoneNumber;
    List<Student> studentList;

}
