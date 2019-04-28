package cn.xinhe.dto;

import lombok.Data;

import java.util.Date;

/**
 * @author haoman
 * @Date 2019-03-19 20:07
 */
@Data
public class StudentLessonTotalInfo {
    private String studentLessonId;
    /**
     * 课程名称
     */
    private String lessonName;
    /**
     * 星期几的课
     */
    private Integer lessonDayOfWeek;
    /**
     * 第几节课
     */
    private Integer sequence;

    private String date;

    private String teacherName;


}
