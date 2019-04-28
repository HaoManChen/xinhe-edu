package cn.xinhe.domain;

import lombok.Data;

import java.io.Serializable;
@Data
public class StudentPay implements Serializable {
    private String id;

    private Integer amount;

    private Integer totalLesson;

    private Integer usedLesson;

    private String teacherCurriculumId;

    private String studentId;

    private static final long serialVersionUID = 1L;
}