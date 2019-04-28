package cn.xinhe.dto;

import lombok.Data;

/**
 * @author haoman
 * @Date 2019-03-20 15:35
 */
@Data
public class StatisticsInfo {
    /**
     * 课程名
     */
    private String className;
    /**
     * 总课时数
     */
    private Integer totalLesson;
    /**
     * 已使用的课时数
     */
    private Integer usedLesson;
}
