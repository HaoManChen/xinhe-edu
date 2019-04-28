package cn.xinhe.dto;

import lombok.Data;

/**
 * @author haoman
 * @Date 2019-04-28 17:37
 */
@Data
public class TeacherAllLessonDTO {
    private String TeacherName;
    private String level;
    private String teacherId;
    private String curriculumId;
    private String curriculumName;
    private Integer dayOfWeek;
    private Integer sequence;

}