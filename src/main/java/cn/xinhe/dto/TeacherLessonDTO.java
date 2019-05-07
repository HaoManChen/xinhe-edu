package cn.xinhe.dto;

import lombok.Data;

/**
 * @author haoman
 * @Date 2019-05-03 22:00
 */
@Data
public class TeacherLessonDTO {
    private String name;
    private WeekLessonReq lesson;
}
